import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;
import javax.servlet.annotation.WebServlet;

@WebServlet("/addRoleView")
public class AddRoleView extends HttpServlet {

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      String title = "Add Role";
      List<Person> persons = (List<Person>) Dao.getList("Person");
      
      request.setAttribute("persons", persons);
      request.setAttribute("title", title);
      request.getRequestDispatcher("addRole.jsp").forward(request, response);
   }
}