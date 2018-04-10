import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;
import javax.servlet.annotation.WebServlet;

@WebServlet("/contactList")
public class ContactList extends HttpServlet {

   public void init() throws ServletException {
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      String title = "Contact Information";
      List<Person> persons = (List<Person>) Dao.getList("Person");
      if (persons.isEmpty()) {
         response.sendRedirect("noPersons.jsp");
      }
      else {
         request.setAttribute("orderType", "contacts");
         request.setAttribute("subTitle", "List of Persons");
         request.setAttribute("persons", persons);
         request.setAttribute("title", title);
         request.getRequestDispatcher("listPersons.jsp").forward(request, response);  
      }
   }
}