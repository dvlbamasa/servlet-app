import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;

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
                    "<input type = \"text\" name = \"role_name\" value=\"" + role.getName() + "\"required/><br/><br/>" + 
                    "Check the Persons you want to add to this role:<br/>" +
                    getPersonsCheckBox(persons) +

                    "<input type = \"submit\" value = \"Submit\" />" +
               "</form>" +
             "</body>" +
         "</html>");
   }

   public String getPersonsCheckBox(List<Person> persons) {
      String personList = "";
      for (Person person : persons) {
          personList = personList.concat("<input type=\"checkbox\" name=\"" + person.getId() +"\"/>" + person.getName().getFirstName() + " " + person.getName().getLastName());
      }
      personList = personList.concat("<br/><br/>");
      return personList;
    }

   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      doGet(request, response);
   }
}