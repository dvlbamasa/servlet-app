import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Set;
import java.util.Iterator;

public class UpdateRole extends HttpServlet {

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      Role role = (Role) Dao.get(Integer.parseInt(request.getParameter("id")), "Role");
      role.setName(request.getParameter("role_name"));
      String[] checkedPersons = request.getParameterValues("persons");
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
      if (checkedPersons != null) {
         for (String personId : checkedPersons) {
            Person person = (Person) Dao.get(Integer.parseInt(personId), "Person");
            person.getRoles().add(role);
            Dao.update(person);
         } 
      }
      Dao.update(role);
      response.sendRedirect("addSuccessful.jsp");
   }

   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      doGet(request, response);
   }
}