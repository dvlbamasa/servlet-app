import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet("/addContact")
public class AddContact extends HttpServlet {

   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      ContactInformation personContactInformation = new ContactInformation(request.getParameter("landline"), request.getParameter("mobile_number"), request.getParameter("email"));
      Person person = (Person) Dao.get(Integer.parseInt(request.getParameter("id")), "Person");
      person.setContactInformation(personContactInformation);
      personContactInformation.setPerson(person);
      Dao.update(person);
      Service.promptSuccess(request, response, "/listPersons.jsp", "Added", "Contact Information");
   }
}