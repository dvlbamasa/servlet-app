import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddContact extends HttpServlet {

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      ContactInformation personContactInformation = new ContactInformation(request.getParameter("landline"), request.getParameter("mobile_number"), request.getParameter("email"));
      Person person = (Person) Dao.get(Integer.parseInt(request.getParameter("id")), "Person");
      person.setContactInformation(personContactInformation);
      personContactInformation.setPerson(person);
      Dao.update(person);
      response.sendRedirect("addSuccessful.jsp");
   }

   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      doGet(request, response);
   }
}