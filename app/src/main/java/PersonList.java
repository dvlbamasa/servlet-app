import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;
import java.util.Collections;
import javax.servlet.annotation.WebServlet;

@WebServlet("/personList")
public class PersonList extends HttpServlet {

   public void init() throws ServletException {
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      String title = "Person";
      List<Person> persons = (List<Person>) Dao.getList("Person");
      if (persons.isEmpty()) {
         response.sendRedirect("noPersons.jsp");
      }
      else {
         request.setAttribute("orderType", "person");
         request.setAttribute("subTitle", "List of Persons");
         request.setAttribute("persons", persons);
         request.setAttribute("title", title);
         request.getRequestDispatcher("listPersons.jsp").forward(request, response);
      }
   }
}