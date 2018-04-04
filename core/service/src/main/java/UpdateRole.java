import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class UpdateRole extends HttpServlet {

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      Role role = (Role) Dao.get(Integer.parseInt(request.getParameter("id")), "Role");
      role.setName(request.getParameter("role_name"));
      Dao.update(role);
      response.sendRedirect("addSuccessful.jsp");
   }

   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      doGet(request, response);
   }
}