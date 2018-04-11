import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/addRole")
public class AddRole extends HttpServlet {

   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      Role role = new Role(request.getParameter("role_name"));
      String[] checkedPersons = request.getParameterValues("personsCheckBox");
      Dao.create(role);
      if (checkedPersons != null) {
         for (String personId : checkedPersons) {
            Person person = (Person) Dao.get(Integer.parseInt(personId), "Person");
            person.getRoles().add(role);
            Dao.update(person);
         } 
      }
      Service.promptSuccess(request, response, "/listRoles.jsp", "Added", "Role");
   }
}