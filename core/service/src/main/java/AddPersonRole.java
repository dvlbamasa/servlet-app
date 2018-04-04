import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddPersonRole extends HttpServlet {

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      Role role = new Role(request.getParameter("role_name"));
      response.sendRedirect("addSuccessful.jsp");
   }

   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      doGet(request, response);
   }
}