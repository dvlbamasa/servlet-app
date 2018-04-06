import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;
import java.util.Set;
import javax.servlet.annotation.WebServlet;
import java.util.Iterator;

@WebServlet("/UpdatePersonView")
public class UpdatePersonView extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      response.setContentType("text/html");

      PrintWriter out = response.getWriter();
      String docType =
         "<!doctype html public \"-//w3c//dtd html 4.0 " +
         "transitional//en\">\n";

      Person person = (Person) Dao.get(Integer.parseInt(request.getParameter("personId")), "Person");
      List<Role> roles = (List<Role>) Dao.getList("Role");

      out.println(docType + 
         "<html>\n" +
            "<head>\n" +
              "<title>Update Person</title>\n" +
            "</head>\n" +
            "<body>\n" + 
               "<h1>Update Person</h1><br/>"+
               "Please Fill out the New Contact Information Form: <br/><br/>\n" + 
                  "<form action = \"UpdatePerson\" method = \"GET\">\n" +
                  "<input type=\"hidden\" name=\"id\" value=\"" + request.getParameter("personId") + "\"/>" +
                  "First Name:<br/>" + 
                    "<input type = \"text\" name = \"first_name\" value=\"" + person.getName().getFirstName() +"\" maxlength=\"20\" required><br/> " +
                    "Middle Name:<br/> " + 
                    "<input type = \"text\" name = \"middle_name\" value=\"" + person.getName().getMiddleName() +"\" maxlength=\"20\" required/><br/>" +
                    "Last Name:<br/> " +
                    "<input type = \"text\" name = \"last_name\" value=\"" + person.getName().getLastName() +"\" maxlength=\"20\" required/><br/>" +
                    "Gender:<br/>" +
                    "<input type = \"radio\" name = \"gender\" value=\"male\" " + (person.getGender().toString().equals("MALE") ? "checked" : "") +" required/> Male" +
                    "<input type = \"radio\" name = \"gender\" value=\"female\" " + (person.getGender().toString().equals("FEMALE") ? "checked" : "") + " required/> Female<br/>" +
                    
                    "Birthday:<br/>" +
                    "<input type = \"date\" name = \"birthday\" value=\"" + person.getBirthday().toString().substring(0,10) +"\"  required/><br/>" +
                    "GWA:<br/> " +
                    "<input type = \"number\" min=\"1\" max=\"3\" step = \"0.01\" name = \"gwa\" value=\"" + person.getGwa() +"\"  required/><br/>" +
                    "Currently Employed:<br/>" +
                    "<input type = \"radio\" name = \"currently_employed\" value=\"y\" " + (person.getCurrentlyEmployed() ? "checked" : "") + " required/> Yes" +
                    "<input type = \"radio\" name = \"currently_employed\" value=\"n\" " + (!person.getCurrentlyEmployed() ? "checked" : "") + " required/> No<br/> " +
                    "Date Hired:<br/> " +
                    "<input type = \"date\" name = \"date_hired\" value=\"" + person.getDateHired().toString().substring(0,10) +"\"  required/><br/><br/>" +
                    "Address Information<br/><br/>" +
                    "Street Number:<br/> " +
                    "<input type = \"text\" name = \"street_no\" value=\"" + person.getAddress().getStreetNo() +"\"  required/><br/>" +
                    "Barangay:<br/> " +
                    "<input type = \"text\" name = \"barangay\"  value=\"" + person.getAddress().getBarangay() +"\" maxlength=\"20\" required/><br/>" +
                    "Municipality:<br/> " +
                    "<input type = \"text\" name = \"municipality\"  value=\"" + person.getAddress().getMunicipality() +"\" maxlength=\"20\" required/><br/>" +
                    "Zip Code:<br/> " +
                    "<input type = \"text\" name = \"zip_code\" value=\"" + person.getAddress().getZipCode() +"\" required/><br/><br/>"+
                    "Contact Information<br/><br/>" + 
                    "Landline:<br/>" + 
                    "<input type = \"text\" name = \"landline\" value=\"" + (person.getContactInformation() == null ? "" : person.getContactInformation().getLandline()) +"\" maxlength=\"20\" required/><br/>" + 
                    "Mobile Number:<br/>" +
                    "<input type = \"text\" name = \"mobile_number\" value=\"" + (person.getContactInformation() == null ? "" : person.getContactInformation().getMobileNumber()) +"\" maxlength=\"20\" required/><br/>" +
                    "Email Address:<br/>" +
                    "<input type = \"text\" name = \"email\" value=\"" + (person.getContactInformation() == null ? "" : person.getContactInformation().getEmail()) +"\" maxlength=\"30\" required/><br/><br/>" +  
                    getRolesCheckBox(roles, person) +
                    "<input type = \"submit\" value = \"Update Person\" />" +
               "</form>" +
             "</body>" +
         "</html>");


   }

    public String getRolesCheckBox(List<Role> roles, Person person) {
      String roleList = "Check the Roles you want to set to this person:<br/>\n";
      boolean checked = false;
      for (Role role : roles) {
          checked = false;
          Set<Role> setRoles = person.getRoles();
          Iterator<Role> iterator = setRoles.iterator();
          while (iterator.hasNext()) {
              Role setRole = iterator.next();
              if (setRole.getId() == role.getId()) {
                  checked = true;
              }
          }
          roleList = roleList.concat("<input type=\"checkbox\" name=\"roles\" value=\"" + role.getId() +"\" " + (checked ? "checked" : "") + " />" + role.getName() + "<br/>");
      }
      roleList = roleList.concat("<br/><br/>");
      return roleList;
    }
}