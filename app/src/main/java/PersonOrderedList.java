import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;
import java.util.Collections;
import javax.servlet.annotation.WebServlet;

@WebServlet("/personOrderedList")
public class PersonOrderedList extends HttpServlet {

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      String subTitle = "";
      String title = "Person";
      List<Person> persons = null;

      if (request.getParameter("order_type") != null ) {
         if (request.getParameter("order_type").equals("GWA")) {
            persons = (List<Person>) Dao.getList("Person");
            Collections.sort(persons, (person1, person2) -> {
               return Float.compare(person1.getGwa(), person2.getGwa());
            });
         }
         else if (request.getParameter("order_type").equals("Date Hired")) {
            persons = (List<Person>) Dao.getOrderedList("Person", "dateHired");
         }
         else if (request.getParameter("order_type").equals("Last Name")) {
            persons = (List<Person>) Dao.getOrderedList("Person", "name.lastName");
         }
         subTitle = "List of Persons Ordered by " + request.getParameter("order_type");
      }
      else {
         persons = (List<Person>) Dao.getList("Person");
         subTitle = "List of Persons";
      }

      request.setAttribute("orderType", "persons");
      request.setAttribute("subTitle", subTitle);
      request.setAttribute("persons", persons);
      request.setAttribute("title", title);
      request.getRequestDispatcher("listPersons.jsp").forward(request, response);
   }
}