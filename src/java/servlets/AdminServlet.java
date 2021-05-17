
package servlets;

import entity.Client;
import entity.Role;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.ProductFacade;
import session.JournalFacade;
import session.ClientFacade;
import session.RoleFacade;
import session.UserFacade;
import session.UserRolesFacade;

/**
 *
 * @author Dilerom
 */
@WebServlet(name = "AdminServlet", urlPatterns = {
    "/listClients",
    "/adminPanel",
    "/addNewRole",
})
public class AdminServlet extends HttpServlet {
    
    @EJB
    private ProductFacade productFacade;
    @EJB
    private ClientFacade clientFacade;
    @EJB
    private JournalFacade journalFacade;
    @EJB private UserFacade userFacade;
    @EJB private UserRolesFacade userRolesFacade;
    @EJB private RoleFacade roleFacade;

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
        HttpSession httpSession = request.getSession(false);
        if(httpSession == null){
            request.setAttribute("info", "У вас нет прав для доступа!");
            request.getRequestDispatcher("/loginForm").forward(request, response);
            return;
        }
        User authUser = (User) httpSession.getAttribute("user");
        if(authUser == null){
            request.setAttribute("info", "У вас нет прав для доступа!");
            request.getRequestDispatcher("/loginForm").forward(request, response);
            return;
        }
        boolean isRole = userRolesFacade.isRole("ADMIN",authUser);
        if(!isRole){
            request.setAttribute("info", "У вас нет прав для доступа!");
            request.getRequestDispatcher("/loginForm").forward(request, response);
            return;
        }
        String path = request.getServletPath();
        switch (path) {
            case "/listClients":
                List<Client> listClients = clientFacade.findAll();
                request.setAttribute("listClients", listClients);
                request.getRequestDispatcher("/WEB-INF/listClients.jsp").forward(request, response);
                break;
            case "/adminPanel":
                Map<User,String> usersMap = new HashMap<>();
                List<User> listUsers = userFacade.findAll();
                for(User user : listUsers){
                    // if("admin".equals(user.getLogin())) continue;
                    usersMap.put(user, userRolesFacade.getTopRole(user));
                }
                request.setAttribute("usersMap", usersMap);
                request.setAttribute("listRoles", roleFacade.findAll());
                request.getRequestDispatcher("/WEB-INF/adminPanel.jsp").forward(request, response);
                break;
            case "/addNewRole":
                String userId = request.getParameter("userId");
                String roleId = request.getParameter("roleId");
                if("".equals(userId) || userId == null
                        || "".equals(roleId) || roleId == null){
                    request.setAttribute("info", "Выберите все поля");
                    request.getRequestDispatcher("/adminPanel").forward(request, response);
                    break;
                }
                User user = userFacade.find(Long.parseLong(userId));
                Role role = roleFacade.find(Long.parseLong(roleId));
                if(!"admin".equals(user.getLogin())){
                    userRolesFacade.setNewRole(role.getRoleName(), user);
                }
                request.getRequestDispatcher("/adminPanel").forward(request, response);
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

   