import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Set;
import java.util.List;
import java.util.Iterator;
import javax.servlet.annotation.WebServlet;

@WebServlet("/updateRole")
public class UpdateRole extends HttpServlet {

   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      Role role = (Role) Dao.get(Integer.parseInt(request.getParameter("id")), "Role");
      String[] checkedPersons = request.getParameterValues("personsCheckBox");
      boolean exists = false;
      for (Person person : role.getPersons()) {
         Set<Role> setRoles = person.getRoles();
         Iterator<Role> iterator = setRoles.iterator();
         while (iterator.hasNext()) {
            Role setRole = iterator.next();
            if (setRole.getId() == role.getId()) {
               iterator.remove();
               Dao.update(person);
            }
         }
      }
      role.setName(request.getParameter("role_name"));
      Dao.update(role);
      if (checkedPersons != null) {
         for (String personId : checkedPersons) {
            Person person = (Person) Dao.get(Integer.parseInt(personId), "Person");
            person.getRoles().add(role);
            Dao.update(person);
         } 
      }
      Service.promptSuccess(request, response, "/listRoles.jsp", "Updated", "Role");
   }
}