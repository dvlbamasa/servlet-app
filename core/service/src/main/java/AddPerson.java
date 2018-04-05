import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Set;
import java.util.HashSet;

public class AddPerson extends HttpServlet {

   private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      if (!Util.validateInputDate(request.getParameter("birthday"))) {
         promptError("Birthday", request, response, "/addPerson.html");
      } 
      else if (!Util.validateInputInt(request.getParameter("street_no"))) {
         promptError("Street Number", request, response, "/addPerson.html");
      }
      else if (!Util.validateInputInt(request.getParameter("zip_code"))) {
         promptError("Zip Code", request, response, "/addPerson.html");
      }
      else if (!Util.validateInputDate(request.getParameter("date_hired"))) {
         promptError("Date Hired", request, response, "/addPerson.html");
      }
      else {
         response.setContentType("text/html");
         Set<Role> roles = new HashSet<Role>();
         Name personName = new Name(request.getParameter("first_name"), request.getParameter("middle_name"), request.getParameter("last_name"));
         Address personAddress = new Address(Integer.parseInt(request.getParameter("street_no")), request.getParameter("barangay"), request.getParameter("municipality"), Integer.parseInt(request.getParameter("zip_code")));
         ContactInformation personContactInformation = new ContactInformation(request.getParameter("landline"), request.getParameter("mobile_number"), request.getParameter("email"));
         Dao.create(personAddress);

         Person person = new Person(personName, ("male".equals(request.getParameter("gender").trim()) ? Gender.MALE : Gender.FEMALE), personAddress, java.sql.Date.valueOf(LocalDate.parse(request.getParameter("birthday"), formatter)), Float.parseFloat(request.getParameter("gwa")), 
                           java.sql.Date.valueOf(LocalDate.parse(request.getParameter("date_hired"), formatter)), (request.getParameter("currently_employed").trim().equals("y") ? true : false));
         personContactInformation.setPerson(person);
         person.setContactInformation(personContactInformation);
         Dao.create(person);
         person.setRoles(roles);
         response.sendRedirect("addSuccessful.jsp");
      }
      
   }

   public void promptError(String property, HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException{
      PrintWriter out= response.getWriter();
      RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
      out.println("<font color=red>Invalid " + property + " Input! Please try again.</font><br/>");
      rd.include(request, response);
   }

   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      doGet(request, response);
   }
}