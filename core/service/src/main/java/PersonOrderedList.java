import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;
import java.util.Collections;
import javax.servlet.annotation.WebServlet;

@WebServlet("/PersonOrderedList")
public class PersonOrderedList extends HttpServlet {

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      String orderType = "";
      response.setContentType("text/html");

      PrintWriter out = response.getWriter();
      
      List<Person> persons = (List<Person>) Dao.getList("Person");

      if (persons.isEmpty()) {
         response.sendRedirect("noPersons.jsp");
      }

      String title = "";

      String docType =
         "<!doctype html public \"-//w3c//dtd html 4.0 " +
         "transitional//en\">\n";

      if (request.getParameter("order_type") != null ) {
         if (request.getParameter("order_type").equals("GWA")) {
            orderType = "gwa";
         }
         else if (request.getParameter("order_type").equals("Date Hired")) {
            orderType = "date_hired";
         }
         else if (request.getParameter("order_type").equals("Last Name")) {
            orderType = "last_name";
         }
         title = "List of Persons Ordered by " + request.getParameter("order_type");
      }
      else {
         title = "List of Persons";
      }

      out.println(docType +
         "<html>\n" +
            "<head><title>Person</title>\n" + 
            "<link rel=\"stylesheet\" href=\"style.css\">\n" +
            "</head>\n" +
            "<body>\n" +
               "<h1>Person</h1><br/>\n" +
               "<h2>" + title + "</h2>\n" +
               PersonList.getOrderTypeDropDown() + 
               printPersonInfo(orderType) + "<br/>" +
               "<form action=\"AddPersonView\"><button type=\"submit\">Add Person</button></form><br/><br/>" +
               "<a href=\"index.html\">Back to Homepage</a>" +
            "</body>" +
         "</html>"
      );
   }

   public static String printPersonInfo(String orderType) {
      List<Person> persons = null;
      if (orderType.equals("gwa")) {
         persons = (List<Person>) Dao.getList("Person");
         Collections.sort(persons, (person1, person2) -> {
            return Float.compare(person1.getGwa(), person2.getGwa());
         });
      }
      else if (orderType.equals("date_hired")) {
         persons = (List<Person>) Dao.getOrderedList("Person", "dateHired");
      }
      else if (orderType.equals("last_name")) {
         persons = (List<Person>) Dao.getOrderedList("Person", "name.lastName");
      }
      else {
         persons = (List<Person>) Dao.getList("Person");
      }
      String table = "<table id=\"t01\">\n" +
                     "<tr>\n" +
                        "<th>Operation</th>\n" +
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
                        "<th>Roles</th>\n" +
                     "</tr>\n";
      for(Person person : persons) {
            table = table.concat(getPersonInfo(person, orderType));
      }
      table = table.concat("</table>");
      return table;
   }

   public static String getPersonInfo(Person person, String orderType) {
      String personInfo = "<tr>\n" +
                              (orderType.equals("contacts") ? 
                                 (person.getContactInformation() == null ? 
                                    "<td>" + 
                                       "<a href=\"AddContactView?personId="+ person.getId() +"\" >Add_Contact</a><br/>"+ 
                                    "</td>\n" : 
                                    "<td>" + 
                                       "<a href=\"UpdateContactView?personId="+ person.getId() +"\" >Update_Contact</a><br/>"+
                                       "<a href=\"DeleteContact?personId="+ person.getId() +"\" >Delete_Contact</a><br/>"+ 
                                    "</td>\n") :
                                 "<td>" + 
                                    "<a href=\"UpdatePersonView?personId="+ person.getId() +"\" >Update</a><br/>"+ 
                                    "<a href=\"DeletePerson?personId="+ person.getId() +"\" >Delete</a>"+ 
                                 "</td>\n") +
                              "<td>" + person.getId() + "</td>\n" +
                              "<td>" + person.getName().getFirstName()+"</td>\n" +
                              "<td>" + person.getName().getMiddleName()+"</td>\n" +
                              "<td>" + person.getName().getLastName()+"</td>\n" +
                              "<td>" + person.getGender()+"</td>\n" +
                              "<td>" + person.getBirthday().toString().substring(0,10)+"</td>\n" +
                              "<td>" + person.getGwa()+"</td>\n" +
                              "<td>" + person.getCurrentlyEmployed()+"</td>\n" +
                              "<td>" + person.getDateHired().toString().substring(0,10)+"</td>\n" +
                              "<td>" + person.getAddress().getStreetNo()+"</td>\n" +
                              "<td>" + person.getAddress().getBarangay()+"</td>\n" +
                              "<td>" + person.getAddress().getMunicipality()+"</td>\n" +
                              "<td>" + person.getAddress().getZipCode()+"</td>\n" +
                              "<td>" + ((person.getContactInformation() == null) ? "" : person.getContactInformation().getLandline()) +"</td>\n" +
                              "<td>" + ((person.getContactInformation() == null) ? "" : person.getContactInformation().getMobileNumber()) +"</td>\n" +
                              "<td>" + ((person.getContactInformation() == null) ? "" : person.getContactInformation().getEmail())+"</td>\n" +
                              getRolesRow(person) +
                           "</tr>\n";
      return personInfo;
   }

   public static String getRolesRow(Person person) {
      String roles = "<td>";
      for (Role role : person.getRoles()) {
         roles = roles.concat(" " + role.getName() + " ");
      }
      roles = roles.concat("</td>\n");
      return roles;
   }
}