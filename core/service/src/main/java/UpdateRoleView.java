import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;
import java.util.Set;
import java.util.Iterator;

public class UpdateRoleView extends HttpServlet {

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      response.setContentType("text/html");

      PrintWriter out = response.getWriter();
      String docType =
         "<!doctype html public \"-//w3c//dtd html 4.0 " +
         "transitional//en\">\n";
      Role role = (Role) Dao.get(Integer.parseInt(request.getParameter("roleId")), "Role");
      List<Person> persons =  (List<Person>) Dao.getList("Person");
      out.println(docType + 
         "<html>\n" +
            "<body>\n" + 
               "Please Update the Role Information Form: <br/><br/>\n" + 
                  "<form action = \"UpdateRole\" method = \"POST\">\n" +
                    "Contact Information<br/><br/>" + 
                    "Role Name:<br/>" + 
                    "<input type=\"hidden\" name=\"id\" value=\"" + request.getParameter("roleId") + "\"/>" +
                    "<input type = \"text\" name = \"role_name\" value=\"" + role.getName() + "\" maxlength=\"20\" required/><br/><br/>" + 
                    (persons.size() == 0 ? "" :  getPersonsCheckBox(persons, role)) +
                    "<input type = \"submit\" value = \"Update Role\" />" +
               "</form>" +
             "</body>" +
         "</html>");
   }

   public String getPersonsCheckBox(List<Person> persons, Role role) {
      String personList = "Check the Persons you want to add to this role:<br/>";
      boolean checked = false;
      for (Person person : persons) {
          checked = false;
          Set<Person> setPersons = role.getPersons();
          Iterator<Person> iterator = setPersons.iterator();
          while (iterator.hasNext()) {
              Person setPerson = iterator.next();
              if (setPerson.getId() == person.getId()) {
                  checked = true;
              }
          }
          personList = personList.concat("<input type=\"checkbox\" name=\"persons\" value=\"" + person.getId() +"\" " + (checked ? "checked" : "") + " />" + person.getName().getFirstName() + " " + person.getName().getLastName() + "<br/>");
      }
      personList = personList.concat("<br/><br/>");
      return personList;
    }

   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      doGet(request, response);
   }
}