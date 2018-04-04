import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DeleteContact extends HttpServlet {

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      Person person = (Person) Dao.get(Integer.parseInt(request.getParameter("personId")), "Person");
      ContactInformation contactInformation = person.getContactInformation();
      Dao.delete(contactInformation);
      person.setContactInformation(null);
      response.sendRedirect("removeSuccessful.jsp");
   }

   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      doGet(request, response);
   }
}