/**
 * Алгоритм создания Web приложения на Java
 * 
 * 1. Создать WebApplication в NetBeans
 * 2. Создать сущностные классы c аннотациями в пакете entity раздела Source Packages
 * 3. Создать базу данных и настроить persistence.xml.
 * 4. Создать сессионные Java Enterprice Beans для каждого сущностного класса 
 *    с помощью помощника NetBeans
 * 5. Создать странички jsp в разделе Web Pages (/web). 
 *    Обязательная папка WEB-INF служит для сокрытия ресурсов от прямого доступа
 * 6. Создать сервлет "MyServlet" в пакете servlets раздела Source Packages.
 * 7. Настроить параметр аннотации @WebServlet(urlPatterns={...})
 *    При запросе от клиента содержащего эти параметры будет выполняться метод
 *    ProcessRequest сервлета "MyServlet", который управляется веб контейнером
 * 8. Получить текущий запрос из запроса "path"
 * 9. Обработать запрос в switch и с помощью метода getRequestDispatcher()
 *    отдать страничку jsp с данными клиенту. 
 *    Например:
 *    request.getRequestDispatcher("/WEB-INF/addBookForm.jsp").forward(request, response);
 * 10. Чтобы получить объект класса -фасада, необходимо применять аннотацию @EJB 
 *    для полей класса "MyServlet". С помощью этой аннотации контейнер сервера приложений внедрит
 *    указанный объект в поле.  
 * 
 */

import entity.Client;
import entity.Journal;
import entity.User;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import javax.ejb.EJB;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.ClientFacade;
import session.JournalFacade;
import session.ProductFacade;

/**
 *
 * @author Dilerom
 */
@WebServlet(name = "/MyServlet", urlPatterns = {
   // "/addProduct",
   // "/createProduct",
    "/listProducts",
   // "/addClient",
  //  "/createClient",
    //"/listClients",
    "/saleOfProductForm",
    "/saleOfProduct"
})
public class MyServlet extends HttpServlet {
    @EJB
    private ProductFacade productFacade;
    @EJB
    private ClientFacade clientFacade;
    @EJB
    private JournalFacade journalFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession httpSession = request.getSession(false);
        if(httpSession == null){
            request.setAttribute("info", "Авторизуйтесь, пожалуйста!");
            request.getRequestDispatcher("/loginForm").forward(request, response);
            return;
        }
        User authUser = (User) httpSession.getAttribute("user");
        if(authUser == null){
            request.setAttribute("info", "Авторизуйтесь, пожалуйста!");
            request.getRequestDispatcher("/loginForm").forward(request, response);
            return;
        }
        Product product;
        Client client;
        Journal journal;
        String path = request.getServletPath();
        switch (path) {
            
            case "/listProducts":
                List<Product> listProducts = productFacade.findAll();
                request.setAttribute("listProducts", listProducts);
                  request.getRequestDispatcher("/WEB-INF/listProducts.jsp").forward(request, response);
                break;
           /* case "/addClient":
                request.getRequestDispatcher("/WEB-INF/addClientForm.jsp").forward(request, response);
                break;
            case "/createClient":
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String phone = request.getParameter("phone");
                String cash = request.getParameter("cash");
                if("".equals(firstname) || firstname == null
                            || "".equals(lastname) || lastname == null
                            || "".equals(phone) || phone == null                     
                            || "".equals(cash) || cash == null){
                    request.setAttribute("firstname", firstname);
                    request.setAttribute("lastname", lastname);
                    request.setAttribute("phone", phone);
                    request.setAttribute("cash", cash);
                    request.setAttribute("info", "Заполните все поля");
                    request.getRequestDispatcher("/WEB-INF/addClientForm.jsp").forward(request, response);
                    break;
                }
                client = new Client(firstname, lastname, phone, cash);
                clientFacade.create(client);
                request.setAttribute("info", "Клиент \"" + client.getFirstname() +" "+ client.getLastname()+ "\" добавлен");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
                */
                            
            case "/saleOfProductForm":
                listProducts = productFacade.findAll();
                request.setAttribute("listProducts", listProducts);
                List <Client> listClients = clientFacade.findAll();
                
                request.setAttribute("listClients", listClients);
                request.getRequestDispatcher("/WEB-INF/saleOfProductForm.jsp").forward(request, response);
                break;
            case "/saleOfProduct":
                String productId = request.getParameter("productId");
                if("".equals(productId) || productId == null){
                    request.setAttribute("info", "Введите данные");
                    request.getRequestDispatcher("/saleOfProductForm").forward(request, response);
                }
                product = productFacade.find(Long.parseLong(productId));                
                String clientId = request.getParameter("clientId");
                if("".equals(clientId) || clientId == null){
                    request.setAttribute("info", "Введите данные");
                    request.getRequestDispatcher("/saleOfProductForm").forward(request, response);
                }
                client = clientFacade.find(Long.parseLong(clientId)); 
                String countStr = request.getParameter("count");
                if("".equals(countStr) || countStr == null){
                    request.setAttribute("info", "Выберите количество");
                    request.getRequestDispatcher("/saleOfProductForm").forward(request, response);
                }
                Integer count = Integer.parseInt(countStr);
                // сюда вставить проверку на состоятельность клиента и корректно дописать сделку
                //реализована возможность покупать по несколько предметов "count"
                if(!(client.getCash() - product.getPrice()*count >= 0)){
                    request.setAttribute("info" ,"У Вас не хватает денег! Но не отчаивайтесь! Вы можете продать душу в магазине напротив.");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                    break;
                }// проверить кoличество товара
                if(!(product.getQuantity() - count >= 0)){
                    request.setAttribute("info", "Этот товар закончился.");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                    break;                
                }
                client.setCash(client.getCash() - product.getPrice());
                clientFacade.edit(client);
                product.setQuantity(product.getQuantity() - count);
                productFacade.edit(product); //сохраняю в базе
                journal = new Journal(product, client, new GregorianCalendar().getTime());

                journalFacade.create(journal);
                request.setAttribute("info", "\"" + product.getName()+"\" выдан покупателю");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
                                  
               
        }       
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
