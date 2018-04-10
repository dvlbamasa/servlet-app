import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;
import javax.servlet.annotation.WebServlet;

@WebServlet("/updateRoleView")
public class UpdateRoleView extends HttpServlet {

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      Role role = (Role) Dao.get(Integer.parseInt(request.getParameter("roleId")), "Role");
      List<Person> persons =  (List<Person>) Dao.getList("Person");
      long [] checkedBox = Service.getPersonsCheckBox(persons, role);
      String title = "Update Role";
      request.setAttribute("title", title);
      request.setAttribute("persons", persons);
      request.setAttribute("role", role);
      request.setAttribute("checkedBox", checkedBox);
      request.getRequestDispatcher("updateRole.jsp").forward(request, response);
   }
}