// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;
import java.util.Collections;

// Extend HttpServlet class
public class PersonListGwa extends HttpServlet {

   public void init() throws ServletException {
   }

   // Method to handle GET method request.
   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      // Set response content type
      response.setContentType("text/html");

      PrintWriter out = response.getWriter();
      String title = "List of Persons Ordered by GWA";
      String docType =
         "<!doctype html public \"-//w3c//dtd html 4.0 " +
         "transitional//en\">\n";
         
      out.println(docType +
         "<html>\n" +
            "<head><title>" + title + "</title>\n" + 
            "<link rel=\"stylesheet\" href=\"style.css\">\n" +
            "</head>\n" +
            "<body>\n" +
               "<h3 align = \"center\">" + title + "</h3>\n" +
               printPersonInfo() +
            "</body>" +
         "</html>"
      );
   }

   public String printPersonInfo() {
      List<Person> persons = (List<Person>) Dao.getList("Person");
               Collections.sort(persons, (person1, person2) -> {
                  return Float.compare(person1.getGwa(), person2.getGwa());
               });

      String table = "<table style=\"width:100\">\n" +
                     "<tr>\n" +
                        "<th>Id</th>\n" +
                        "<th>First Name</th>\n" +                         
                        "<th>Middle Name</th>\n" +
                        "<th>Last Name</th>\n" +
                        "<th>Gender</th>\n" +
                        "<th>Birthday</th>\n" +
                        "<th>GWA</th>\n" +
                        "<th>Currently Employed</th>\n" +
                        "<th>Date Hired</th>\n" +
                        "<th>Street Number</th>\n" +
                        "<th>Barangay</th>\n" +
                        "<th>Municipal</th>\n" +
                        "<th>Zip Code</th>\n" +
                        "<th>Landline</th>\n" +
                        "<th>Mobile Number</th>\n" +
                        "<th>Email</th>\n" +
                     "</tr>\n";
      for(Person person : persons) {
            table = table.concat(getPersonInfo(person));
      }
      table = table.concat("</table>");
      return table;
   }

   public String getPersonInfo(Person person) {
      String personInfo = "<tr>\n" +
                              "<td>" + person.getId() + "</td>\n" +
                              "<td>" + person.getName().getFirstName()+"</td>\n" +
                              "<td>" + person.getName().getMiddleName()+"</td>\n" +
                              "<td>" + person.getName().getLastName()+"</td>\n" +
                              "<td>" + person.getGender()+"</td>\n" +
                              "<td>" + person.getBirthday()+"</td>\n" +
                              "<td>" + person.getGwa()+"</td>\n" +
                              "<td>" + person.getCurrentlyEmployed()+"</td>\n" +
                              "<td>" + person.getDateHired()+"</td>\n" +
                              "<td>" + person.getAddress().getStreetNo()+"</td>\n" +
                              "<td>" + person.getAddress().getBarangay()+"</td>\n" +
                              "<td>" + person.getAddress().getMunicipality()+"</td>\n" +
                              "<td>" + person.getAddress().getZipCode()+"</td>\n" +
                              "<td>" + person.getContactInformation().getLandline()+"</td>\n" +
                              "<td>" + person.getContactInformation().getMobileNumber()+"</td>\n" +
                              "<td>" + person.getContactInformation().getEmail()+"</td>\n" +
                           "</tr>\n";
      return personInfo;

   }

   // Method to handle POST method request.
   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      doGet(request, response);
   }
}