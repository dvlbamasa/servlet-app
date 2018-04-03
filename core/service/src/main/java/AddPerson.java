// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Locale;

// Extend HttpServlet class
public class AddPerson extends HttpServlet {

   private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH);

   // Method to handle GET method request.
   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      // Set response content type
      response.setContentType("text/html");

      Name personName = new Name(request.getParameter("first_name"), request.getParameter("middle_name"), request.getParameter("last_name"));
      Address personAddress = new Address(Integer.parseInt(request.getParameter("street_no")), request.getParameter("barangay"), request.getParameter("municipality"), Integer.parseInt(request.getParameter("zip_code")));
      ContactInformation personContactInformation = new ContactInformation(request.getParameter("landline"), request.getParameter("mobile_number"), request.getParameter("email"));
      Dao.create(personAddress);

      Person person = new Person(personName, (request.getParameter("gender").trim().equals("male") ? Gender.MALE : Gender.FEMALE), personAddress, java.sql.Date.valueOf(LocalDate.parse(request.getParameter("birthday"), formatter)), Float.parseFloat(request.getParameter("gwa")), 
                        java.sql.Date.valueOf(LocalDate.parse(request.getParameter("date_hired"), formatter)), (request.getParameter("currently_employed").trim().equals("y") ? true : false));
      personContactInformation.setPerson(person);
      person.setContactInformation(personContactInformation);
      Dao.create(person);
      response.sendRedirect("addSuccessful.jsp");
   }

   // Method to handle POST method request.
   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      doGet(request, response);
   }
}