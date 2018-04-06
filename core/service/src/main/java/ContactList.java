import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;
import javax.servlet.annotation.WebServlet;

@WebServlet("/ContactList")
public class ContactList extends HttpServlet {

   public void init() throws ServletException {
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      response.setContentType("text/html");

      PrintWriter out = response.getWriter();
      String title = "Contact Information";
      String docType =
         "<!doctype html public \"-//w3c//dtd html 4.0 " +
         "transitional//en\">\n";
      List<Person> persons = (List<Person>) Dao.getList("Person");
      if (persons.isEmpty()) {
         response.sendRedirect("noPersons.jsp");
      }
      else {
         out.println(docType +
         "<html>\n" +
            "<head><title>" + title + "</title>\n" + 
            "<link rel=\"stylesheet\" href=\"style.css\">\n" +
            "</head>\n" +
            "<body>\n" +
               "<h1>" + title + "</h1><br/>\n" +
               "<h2>List of Persons</h2>\n" +
               PersonOrderedList.printPersonInfo("contacts") + "<br/><br/>" +
               "<a href=\"index.html\">Back to Homepage</a>" +
            "</body>" +
         "</html>"
         );   
      }
   }
}