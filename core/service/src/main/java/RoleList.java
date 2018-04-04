import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;
import java.util.Collections;

public class RoleList extends HttpServlet {

   public void init() throws ServletException {
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      response.setContentType("text/html");

      PrintWriter out = response.getWriter();
      String title = "List of Roles";
      String docType =
         "<!doctype html public \"-//w3c//dtd html 4.0 " +
         "transitional//en\">\n";
      List<Role> roles = (List<Role>) Dao.getList("Role");
      String updateList = "";
      if (request.getParameter("update_list").equals("Update Role")) {
         updateList = "update";
      }

      out.println(docType +
         "<html>\n" +
            "<head><title>" + title + "</title>\n" + 
            "<link rel=\"stylesheet\" href=\"style.css\">\n" +
            "</head>\n" +
            "<body>\n" +
               "<h3 align = \"center\">" + title + "</h3>\n" +
               printRoleInfo(roles) + "<br/><br/>" +
               (!updateList.equals("update") ? "" :
                  "Choose the role's id you want to " + updateList + ":<br/>" +
                  "<form action=\"UpdateRoleView\" method=\"GET\">\n" +
                  getRoleIdDropDown(roles) +
                  "<button type=\"submit\">"+ request.getParameter("update_list") + "</button>\n") +
               "<a href=\"index.html\">Back to Homepage</a>" +
            "</body>" +
         "</html>"
      );   
   }

   public String getRoleIdDropDown(List<Role> roles) {
      String dropDown = "<select id=\"roleId\" name=\"roleId\">\n";
      for(Role role : roles) {
            dropDown = dropDown.concat("<option value=\"" + role.getId() + "\">" + role.getId() + "</option>\n");
      }
      dropDown = dropDown.concat("</select>");
      return dropDown;
   }

   public String printRoleInfo(List<Role> roles) {
      String table = "<table id=\"t01\">\n" +
                     "<tr>\n" +
                        "<th>Id</th>\n" +
                        "<th>Role Name</th>\n" +                         
                     "</tr>\n";
      for (Role role : roles) {
         table = table.concat(getRoleInfo(role));
      }
      table = table.concat("</table>");
      return table;
   }

   public String getRoleInfo(Role role) {
      String roleInfo = "<tr>\n" +
                              "<td>" + role.getId() + "</td>\n" +
                              "<td>" + role.getName() + "</td>\n" +
                        "</tr>";
      return roleInfo;

   }

   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      doGet(request, response);
   }
}