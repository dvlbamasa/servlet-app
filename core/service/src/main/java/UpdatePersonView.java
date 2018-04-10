import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;
import javax.servlet.annotation.WebServlet;


@WebServlet("/updatePersonView")
public class UpdatePersonView extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      Person person = (Person) Dao.get(Integer.parseInt(request.getParameter("personId")), "Person");
      List<Role> roles = (List<Role>) Dao.getList("Role");
      long [] checkedBox =  Service.getRolesCheckBox(roles, person);
      String title = "Update Person";
      request.setAttribute("title", title);
      request.setAttribute("person", person);
      request.setAttribute("roles", roles);
      request.setAttribute("checkedBox", checkedBox);
      request.getRequestDispatcher("updatePerson.jsp").forward(request, response);
   }

}