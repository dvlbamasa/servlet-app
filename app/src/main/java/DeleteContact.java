import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/deleteContact")
public class DeleteContact extends HttpServlet {

   	public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    	Person person = (Person) Dao.get(Integer.parseInt(request.getParameter("personId")), "Person");
		ContactInformation contactInformation = person.getContactInformation();
		Dao.delete(contactInformation);
		person.setContactInformation(null);
		Service.promptSuccess(request, response,"/listPersons.jsp", "Deleted", "Contact Information");
    }
}