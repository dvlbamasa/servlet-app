import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Set;
import java.util.List;
import java.util.HashSet;
import javax.servlet.annotation.WebServlet;

@WebServlet("/addPerson")
public class AddPerson extends HttpServlet {

   private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      if (!Util.validateInputDate(request.getParameter("birthday"))) {
         Service.promptError("Birthday", request, response, "Add Person", getServletContext().getRequestDispatcher("/addPerson.jsp"));
      } 
      else if (!Util.validateInputInt(request.getParameter("street_no"))) {
         Service.promptError("Street Number", request, response, "Add Person", getServletContext().getRequestDispatcher("/addPerson.jsp"));
      }
      else if (!Util.validateInputInt(request.getParameter("zip_code"))) {
         Service.promptError("Zip Code", request, response, "Add Person", getServletContext().getRequestDispatcher("/addPerson.jsp"));
      }
      else if (!Util.validateInputDate(request.getParameter("date_hired"))) {
         Service.promptError("Date Hired", request, response, "Add Person", getServletContext().getRequestDispatcher("/addPerson.jsp"));
      }
      else {
         Set<Role> roles = new HashSet<Role>();
         Name personName = new Name(request.getParameter("first_name"), request.getParameter("middle_name"), request.getParameter("last_name"));
         Address personAddress = new Address(Integer.parseInt(request.getParameter("street_no")), request.getParameter("barangay"), request.getParameter("municipality"), Integer.parseInt(request.getParameter("zip_code")));
         ContactInformation personContactInformation = new ContactInformation(request.getParameter("landline"), request.getParameter("mobile_number"), request.getParameter("email"));
         Dao.create(personAddress);
         String[] checkedRoles = request.getParameterValues("rolesCheckBox");

         Person person = new Person(personName, ("male".equals(request.getParameter("gender").trim()) ? Gender.MALE : Gender.FEMALE), personAddress, java.sql.Date.valueOf(LocalDate.parse(request.getParameter("birthday"), formatter)), Float.parseFloat(request.getParameter("gwa")), 
                           java.sql.Date.valueOf(LocalDate.parse(request.getParameter("date_hired"), formatter)), (request.getParameter("currently_employed").trim().equals("y") ? true : false));
         personContactInformation.setPerson(person);
         person.setContactInformation(personContactInformation);

         if (checkedRoles != null) {
            for (String roleId : checkedRoles) {
               Role role = (Role) Dao.get(Integer.parseInt(roleId), "Role");
               roles.add(role);
            }
            person.setRoles(roles);
         }
         else {
            person.setRoles(roles);
            person.getRoles().clear();
         }

         Dao.create(person);
         person.setRoles(roles);
         Service.promptSuccess(request, response, "/listPersons.jsp", "Added", "Person");
      }

   }
}