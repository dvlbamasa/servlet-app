import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Set;
import java.util.HashSet;

public class UpdatePerson extends HttpServlet {

   private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      response.setContentType("text/html");
      Set<Role> roles = new HashSet<Role>();
      Person person = (Person) Dao.get(Integer.parseInt(request.getParameter("id")), "Person");
      Name personName = new Name(request.getParameter("first_name"), request.getParameter("middle_name"), request.getParameter("last_name"));
      Address personAddress = new Address(Integer.parseInt(request.getParameter("street_no")), request.getParameter("barangay"), request.getParameter("municipality"), Integer.parseInt(request.getParameter("zip_code")));
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
      person.setRoles(roles);

      Dao.update(person);
      response.sendRedirect("addSuccessful.jsp");
   }

   public void addPersonRoles(Person person) {

   }

   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      doGet(request, response);
   }
}