/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Client;
import entity.User;
import java.io.IOException;
//import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.ClientFacade;
import session.UserFacade;
import entity.Role;
import entity.UserRoles;
import session.RoleFacade;
import session.UserRolesFacade;
/**
 *
 * @author Dilerom
 */
@WebServlet(name = "LoginServlet", loadOnStartup = 1, urlPatterns = {
    "/loginForm", 
    "/login",
    "/logout",
    "/registrationForm",
    "/registration",
})
public class LoginServlet extends HttpServlet {
    @EJB
    private UserFacade userFacade;
    @EJB
    private ClientFacade clientFacade;
    
    @EJB private RoleFacade roleFacade;
    @EJB private UserRolesFacade userRolesFacade;

    @Override
    public void init() throws ServletException {
        super.init(); 
        if(userFacade.findAll().size() > 0) return;
        //Client client = new Client("Olga", "Egorova", "12345678",);
        //clientFacade.create(client);
        User user = new User("admin", "12345", client);
        userFacade.create(user);

        Role role = new Role("ADMIN");
        roleFacade.create(role);
        UserRoles userRoles = new UserRoles(role, user);
        userRolesFacade.create(userRoles);

        role = new Role("MANAGER");
        roleFacade.create(role);
        userRoles = new UserRoles(role, user);
        userRolesFacade.create(userRoles);

        role = new Role("CLIENT");
        roleFacade.create(role);
        userRoles = new UserRoles(role, user);
        userRolesFacade.create(userRoles);

    }
    
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
        String path = request.getServletPath();
        switch (path) {
            case "/loginForm":
                request.getRequestDispatcher("/WEB-INF/loginForm.jsp").forward(request, response);
                break;
            case "/login":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                User user = userFacade.findByLogin(login);
                if(user == null){
                    request.setAttribute("info", "Неправильный логин или пароль");
                    request.getRequestDispatcher("/WEB-INF/loginForm").forward(request, response);
                    break;
                }
                if(!password.equals(user.getPassword())){
                    request.setAttribute("info", "Неправильный логин или пароль");
                    request.getRequestDispatcher("/WEB-INF/loginForm").forward(request, response);
                    break;
                }
                HttpSession httpSession = request.getSession(true);
                httpSession.setAttribute("user", user);
                request.setAttribute("info", "Вы вошли как " + user.getLogin());
                request.setAttribute("active", "index");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/logout":
                httpSession = request.getSession(false);
                if(httpSession != null){
                    httpSession.invalidate();
                    request.setAttribute("info", "Вы вышли!");
                }
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/registrationForm":
                request.setAttribute("active", "registration");
                request.getRequestDispatcher("/WEB-INF/registrationForm.jsp").forward(request, response);
                break;                
                
            case "/registration":
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String phone = request.getParameter("phone");
                String cash = request.getParameter("cash");
                login = request.getParameter("login");
                password = request.getParameter("password");
                if("".equals(firstname) || firstname == null
                        || "".equals(lastname) || lastname == null
                        || "".equals(phone) || phone == null
                        || "".equals(cash) || cash == null
                        || "".equals(login) || login == null
                        || "".equals(password) || password == null){
                    request.setAttribute("firstname", firstname);
                    request.setAttribute("lastname", lastname);
                    request.setAttribute("phone", phone);
                    request.setAttribute("cash", cash);
                    request.setAttribute("login", login);
                    request.setAttribute("info", "Заполните все поля");
                    request.getRequestDispatcher("/WEB-INF/registrationForm.jsp").forward(request, response);
                    break;
                }
                Client client = new Client(firstname, lastname, phone, cash);
                clientFacade.create(client);
                user = new User(login, password, client);
                userFacade.create(user);
                Role role = roleFacade.findByName("CLIENT");
                UserRoles userRoles = new UserRoles(role, user);
                userRolesFacade.create(userRoles);
                request.setAttribute("info", "Клиент \"" + client.getFirstname() +" "+ client.getLastname()+ "\" добавлен");
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