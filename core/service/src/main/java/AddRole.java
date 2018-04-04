import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddRole extends HttpServlet {

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      Role role = new Role(request.getParameter("role_name"));
      Dao.create(role);
      response.sendRedirect("addSuccessful.jsp");
   }

   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      doGet(request, response);
   }
}