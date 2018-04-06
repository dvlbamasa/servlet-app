import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;
import java.util.Collections;
import javax.servlet.annotation.WebServlet;

@WebServlet("/PersonList")
public class PersonList extends HttpServlet {

   public void init() throws ServletException {
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      response.setContentType("text/html");

      PrintWriter out = response.getWriter();
      String title = "Person";
      String docType =
         "<!doctype html public \"-//w3c//dtd html 4.0 " +
         "transitional//en\">\n";
      List<Person> persons = (List<Person>) Dao.getList("Person");

      if (persons.isEmpty()) {
         response.sendRedirect("noPersons.jsp");
      }
      else {
         out.println(docType +
         "<html>\n" +
            "<head><title>" + title + "</title>\n" + 
            "<link rel=\"stylesheet\" href=\"style.css\">\n" +
            "</head>\n" +
            "<body>\n" +
               "<h1>" + title + "</h1><br/>\n" +
               "<h2>List of Persons</h2>\n" +
               getOrderTypeDropDown() +
               PersonOrderedList.printPersonInfo("none") + "<br/>" +
               "<form action=\"AddPersonView\"><button type=\"submit\">Add Person</button></form><br/><br/>" +
               "<a href=\"index.html\">Back to Homepage</a>" +
            "</body>" +
         "</html>"
         );   
      }
   }

   public static String getOrderTypeDropDown() {
      String dropDown = "Pick Order:<br/>" +
                        "<form action=\"PersonOrderedList\" method=\"GET\">\n" +
                           "<select id=\"order_type\" name=\"order_type\">\n" +
                              "<option value=\"GWA\">GWA</option>\n" +
                              "<option value=\"Date Hired\">Date Hired</option>\n" +
                              "<option value=\"Last Name\">Last Name</option>\n" +
                           "</select>\n" +
                           "<button type=\"submit\">Select</button><br/><br/>\n"+
                        "</form>\n";
      return dropDown;
   }
}