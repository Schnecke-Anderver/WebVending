package servlets;

import entity.Client;
import entity.Journal;
import entity.User;
import entity.Product;
import java.io.IOException;
import static java.lang.Integer.parseInt;
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
import session.UserRolesFacade;

/**
 *
 * @author Dilerom
 */
@WebServlet(name = "/ManagerServlet", urlPatterns = {
    "/addProductForm",
    "/createProduct",    
    "/editProductForm",
    "/editProduct",
   
})
public class ManagerServlet extends HttpServlet {
    @EJB
    private ProductFacade productFacade;
    @EJB
    private ClientFacade clientFacade;
    @EJB
    private JournalFacade journalFacade;
    @EJB private UserRolesFacade userRolesFacade;

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
        boolean isRole = userRolesFacade.isRole("MANAGER",authUser);
        if(!isRole){
            request.setAttribute("info", "У вас нет прав для доступа!");
            request.getRequestDispatcher("/loginForm").forward(request, response);
            return;
        }
        
        Product product;
        Client client;
        Journal journal;
        String path = request.getServletPath();
        switch (path) {
            case "/addProduct":
               request.getRequestDispatcher("/WEB-INF/addProductForm.jsp").forward(request, response);
                break;
            case "/createProduct":
                String name = request.getParameter("name");
                String price = request.getParameter("price");
                String quantity = request.getParameter("quantity");  
                if("".equals(name) || name == null
                            || "".equals(price) || price == null
                            || "".equals(quantity) || quantity == null){
                        request.setAttribute("name", name);
                        request.setAttribute("price", price);
                        request.setAttribute("quantity", quantity);
                        request.setAttribute("info", "Заполните все поля.");            
                request.getRequestDispatcher("/WEB-INF/addProductForm.jsp").forward(request, response);
                break;
            }
            product = new Product(name,Integer.parseInt(price), Integer.parseInt(quantity));
            
            productFacade.create(product);
            
            request.setAttribute("info", "Товар \""+ name + "\" добавлен!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/editProductForm":
                String productId = request.getParameter("productId");
                product = productFacade.find(Long.parseLong(productId));
                request.setAttribute("product", product);
                request.getRequestDispatcher("/WEB-INF/editProductForm.jsp").forward(request, response);
                break;
            case "/editProduct":
                productId = request.getParameter("productId");
                name = request.getParameter("name");
                price = request.getParameter("price");
                quantity = request.getParameter("quantity");
                
                if("".equals(name) || name == null
                        || "".equals(price) || price == null
                        || "".equals(quantity) || quantity == null){
                    request.setAttribute("info", "Поля не должны быть пустыми");
                    request.getRequestDispatcher("/editProductForm").forward(request, response);
                    break;
                }
                product = productFacade.find(Long.parseLong(productId));
                product.setName(name);
                product.setPrice(price);
                product.setQuantity(quantity);
                productFacade.edit(product);
                request.setAttribute("productId", productId);
                request.setAttribute("info", "Запись отредактирована");
                request.getRequestDispatcher("/editProductForm").forward(request, response);
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