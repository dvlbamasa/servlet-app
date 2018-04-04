import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;

public class UpdatePersonView extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      response.setContentType("text/html");

      PrintWriter out = response.getWriter();
      String title = "List of Persons";
      String docType =
         "<!doctype html public \"-//w3c//dtd html 4.0 " +
         "transitional//en\">\n";

      Person person = (Person) Dao.get(Integer.parseInt(request.getParameter("personId")), "Person");
      List<Role> roles = (List<Role>) Dao.getList("Role");

      out.println(docType + 
         "<html>\n" +
            "<body>\n" + 
               "Please Fill out the New Contact Information Form: <br/><br/>\n" + 
                  "<form action = \"UpdatePerson\" method = \"POST\">\n" +
                  "<input type=\"hidden\" name=\"id\" value=\"" + request.getParameter("personId") + "\"/>" +
                  "First Name:<br/>" + 
                    "<input type = \"text\" name = \"first_name\" value=\"" + person.getName().getFirstName() +"\" required><br/> " +
                    "Middle Name:<br/> " + 
                    "<input type = \"text\" name = \"middle_name\" value=\"" + person.getName().getMiddleName() +"\"  required/><br/>" +
                    "Last Name:<br/> " +
                    "<input type = \"text\" name = \"last_name\" value=\"" + person.getName().getFirstName() +"\"  required/><br/>" +
                    "Gender:<br/>" +
                    "<input type = \"radio\" name = \"gender\" value=\"male\" " + (person.getGender().toString().equals("MALE") ? "checked" : "") +" required/> Male" +
                    "<input type = \"radio\" name = \"gender\" value=\"female\" " + (person.getGender().toString().equals("FEMALE") ? "checked" : "") + " required/> Female<br/>" +
                    
                    "Birthday:<br/>" +
                    "<input type = \"date\" name = \"birthday\" value=\"" + person.getBirthday() +"\"  required/><br/>" +
                    "GWA:<br/> " +
                    "<input type = \"number\" min=\"1\" max=\"3\" step = \"0.01\" name = \"gwa\" value=\"" + person.getGwa() +"\"  required/><br/>" +
                    "Currently Employed:<br/>" +
                    "<input type = \"radio\" name = \"currently_employed\" value=\"y\" " + (person.getCurrentlyEmployed() ? "checked" : "") + " required/> Yes" +
                    "<input type = \"radio\" name = \"currently_employed\" value=\"n\" " + (!person.getCurrentlyEmployed() ? "checked" : "") + " required/> No<br/> " +
                    "Date Hired:<br/> " +
                    "<input type = \"date\" name = \"date_hired\" value=\"" + person.getDateHired() +"\"  required/><br/><br/>" +
                    "Address Information<br/><br/>" +
                    "Street Number:<br/> " +
                    "<input type = \"text\" name = \"street_no\" value=\"" + person.getAddress().getStreetNo() +"\"  required/><br/>" +
                    "Barangay:<br/> " +
                    "<input type = \"text\" name = \"barangay\"  value=\"" + person.getAddress().getBarangay() +"\" required/><br/>" +
                    "Municipality:<br/> " +
                    "<input type = \"text\" name = \"municipality\"  value=\"" + person.getAddress().getMunicipality() +"\" required/><br/>" +
                    "Zip Code:<br/> " +
                    "<input type = \"text\" name = \"zip_code\" value=\"" + person.getAddress().getZipCode() +"\" required/><br/><br/>"+
                    "Contact Information<br/><br/>" + 
                    "Landline:<br/>" + 
                    "<input type = \"text\" name = \"landline\" value=\"" + (person.getContactInformation() == null ? "" : person.getContactInformation().getLandline()) +"\" required/><br/>" + 
                    "Mobile Number:<br/>" +
                    "<input type = \"text\" name = \"mobile_number\" value=\"" + (person.getContactInformation() == null ? "" : person.getContactInformation().getMobileNumber()) +"\" required/><br/>" +
                    "Email Address:<br/>" +
                    "<input type = \"text\" name = \"email\" value=\"" + (person.getContactInformation() == null ? "" : person.getContactInformation().getEmail()) +"\" required/><br/><br/>" +  
                    "Check the Roles you want to add to this person:<br/>" +
                    getRolesCheckBox(roles) +
                    "<input type = \"submit\" value = \"Submit\" />" +
               "</form>" +
             "</body>" +
         "</html>");


   }

    public String getRolesCheckBox(List<Role> roles) {
      String roleList = "";
      for (Role role : roles) {
          roleList = roleList.concat("<input type=\"checkbox\" name=\"" + role.getId() +"\"/>" + role.getName());
      }
      roleList = roleList.concat("<br/><br/>");
      return roleList;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      doGet(request, response);
    }
}