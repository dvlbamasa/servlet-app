import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Set;
import java.util.HashSet;
import javax.servlet.annotation.WebServlet;

@WebServlet("/updatePerson")
public class UpdatePerson extends HttpServlet {

   private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      if (!Util.validateInputDate(request.getParameter("birthday"))) {
         Service.promptError("Birthday", request, response, "Update Person", getServletContext().getRequestDispatcher("/updatePerson.jsp"));
      } 
      else if (!Util.validateInputDate(request.getParameter("date_hired"))) {
         Service.promptError("Date Hired", request, response, "Update Person", getServletContext().getRequestDispatcher("/updatePerson.jsp"));
      }
      else if (!Util.validateInputInt(request.getParameter("street_no"))) {
         Service.promptError("Street Number", request, response, "Update Person", getServletContext().getRequestDispatcher("/updatePerson.jsp"));
      }
      else if (!Util.validateInputInt(request.getParameter("zip_code"))) {
         Service.promptError("Zip Code", request, response, "Update Person", getServletContext().getRequestDispatcher("/updatePerson.jsp"));
      }
      else {
         response.setContentType("text/html");
         Set<Role> roles = new HashSet<Role>();
         Person person = (Person) Dao.get(Integer.parseInt(request.getParameter("id")), "Person");
         Name personName = new Name(request.getParameter("first_name"), request.getParameter("middle_name"), request.getParameter("last_name"));
         Address personAddress = new Address(Integer.parseInt(request.getParameter("street_no")), request.getParameter("barangay"), request.getParameter("municipality"), Integer.parseInt(request.getParameter("zip_code")));
         String[] checkedRoles = request.getParameterValues("rolesCheckBox");
         person.setName(personName);
         person.setAddress(personAddress);
         person.setGender(("male".equals(request.getParameter("gender").trim()) ? Gender.MALE : Gender.FEMALE));
         person.setBirthday(java.sql.Date.valueOf(LocalDate.parse(request.getParameter("birthday"), formatter)));
         person.setGwa(Float.parseFloat(request.getParameter("gwa")));
         person.setDateHired(java.sql.Date.valueOf(LocalDate.parse(request.getParameter("date_hired"), formatter)));
         person.setCurrentlyEmployed((request.getParameter("currently_employed").trim().equals("y") ? true : false));
         if (person.getContactInformation() != null) {
            person.getContactInformation().setLandline(request.getParameter("landline"));
            person.getContactInformation().setMobileNumber(request.getParameter("mobile_number"));
            person.getContactInformation().setEmail(request.getParameter("email"));
         }
         else {
            ContactInformation personContactInformation = new ContactInformation(request.getParameter("landline"), request.getParameter("mobile_number"), request.getParameter("email"));
            person.setContactInformation(personContactInformation);
            personContactInformation.setPerson(person);
         }

         if (checkedRoles != null) {
            for (String roleId : checkedRoles) {
               Role role = (Role) Dao.get(Integer.parseInt(roleId), "Role");
               roles.add(role);
            }
            person.setRoles(roles);
         }
         else {
            person.getRoles().clear();
         }
         Dao.update(person);
         Service.promptSuccess(request, response, "/listPersons.jsp", "Updated", "Person");
      }
   }
}