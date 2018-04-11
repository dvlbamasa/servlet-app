import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;
import javax.servlet.annotation.WebServlet;

@WebServlet("/addPersonView")
public class AddPersonView extends HttpServlet {

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      String title = "Add Person";
      List<Role> roles = (List<Role>) Dao.getList("Role");
      
      request.setAttribute("roles", roles);
      request.setAttribute("title", title);
      request.getRequestDispatcher("addPerson.jsp").forward(request, response);
   }
}