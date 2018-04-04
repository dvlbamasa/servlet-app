import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddContactView extends HttpServlet {

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      response.setContentType("text/html");

      PrintWriter out = response.getWriter();
      String title = "List of Persons";
      String docType =
         "<!doctype html public \"-//w3c//dtd html 4.0 " +
         "transitional//en\">\n";


      out.println(docType + 
         "<html>\n" +
            "<body>\n" + 
               "Please Fill out the New Contact Information Form: <br/><br/>\n" + 
                  "<form action = \"AddContact\" method = \"POST\">\n" +
                    "Contact Information<br/><br/>" + 
                    "Landline:<br/>" + 
                    "<input type=\"hidden\" name=\"id\" value=\"" + request.getParameter("personId") + "\"/>" +
                    "<input type = \"text\" name = \"landline\" required/><br/>" + 
                    "Mobile Number:<br/>" +
                    "<input type = \"text\" name = \"mobile_number\" required/><br/>" +
                    "Email Address:<br/>" +
                    "<input type = \"text\" name = \"email\" required/><br/><br/>" +  
                    
                    "<input type = \"submit\" value = \"Submit\" />" +
               "</form>" +
             "</body>" +
         "</html>");


   }

   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      doGet(request, response);
   }
}