import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;
import java.util.Collections;

public class PersonList extends HttpServlet {

   public void init() throws ServletException {
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      response.setContentType("text/html");

      PrintWriter out = response.getWriter();
      String title = "List of Persons";
      String docType =
         "<!doctype html public \"-//w3c//dtd html 4.0 " +
         "transitional//en\">\n";
      List<Person> persons = (List<Person>) Dao.getList("Person");
      String updateDelete = "";
      if (request.getParameter("update_delete").equals("Delete Person")) {
         updateDelete = "delete";
      }
      else if (request.getParameter("update_delete").equals("Update Person")) {
         updateDelete = "update";
      }

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
               "<h3 align = \"center\">" + title + "</h3>\n" +
               PersonOrderedList.printPersonInfo("none", null) + "<br/><br/>" +
               "Choose the person's id you want to " + updateDelete + ":<br/>" +
               "<form action=\"" + (updateDelete.equals("delete") ? "DeletePerson" : "UpdatePersonView") + "\" method=\"GET\">\n" +
               getPersonIdDropDown(persons) +
               "<button type=\"submit\">"+ request.getParameter("update_delete") + "</button>\n"+
               "</form>\n" +
               "<a href=\"index.html\">Back to Homepage</a>" +
            "</body>" +
         "</html>"
         );   
      }
   }

   public String getPersonIdDropDown(List<Person> persons) {
      String dropDown = "<select id=\"personId\" name=\"personId\">\n";
      for(Person person : persons) {
            dropDown = dropDown.concat("<option value=\"" + person.getId() + "\">" + person.getId() + "</option>\n");
      }
      dropDown = dropDown.concat("</select>");
      return dropDown;
   }

   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      doGet(request, response);
   }
}