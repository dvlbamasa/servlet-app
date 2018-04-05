import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Locale;

public class UpdateContactView extends HttpServlet {

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      response.setContentType("text/html");

      PrintWriter out = response.getWriter();
      String title = "List of Persons";
      String docType =
         "<!doctype html public \"-//w3c//dtd html 4.0 " +
         "transitional//en\">\n";
      ContactInformation contactInformation = (ContactInformation) Dao.get(Integer.parseInt(request.getParameter("personId")), "ContactInformation");


      out.println(docType + 
         "<html>\n" +
            "<body>\n" + 
               "Please Update the Contact Information Form: <br/><br/>\n" + 
                  "<form action = \"UpdateContact\" method = \"POST\">\n" +
                    "Contact Information<br/><br/>" + 
                    "Landline:<br/>" + 
                    "<input type=\"hidden\" name=\"id\" value=\"" + request.getParameter("personId") + "\"/>" +
                    "<input type = \"text\" name = \"landline\" value=\"" + contactInformation.getLandline() + "\" maxlength=\"20\" required/><br/>" + 
                    "Mobile Number:<br/>" +
                    "<input type = \"text\" name = \"mobile_number\"  value=\"" + contactInformation.getMobileNumber() + "\" maxlength=\"20\" required/><br/>" +
                    "Email Address:<br/>" +
                    "<input type = \"text\" name = \"email\"  value=\"" + contactInformation.getEmail() + "\" maxlength=\"30\" required/><br/><br/>" +  
                    
                    "<input type = \"submit\" value = \"Update Contact\" />" +
               "</form>" +
             "</body>" +
         "</html>");


   }

   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      doGet(request, response);
   }
}