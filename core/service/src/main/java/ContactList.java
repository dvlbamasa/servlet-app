import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;

public class ContactList extends HttpServlet {

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
      String contactMethod = "";
      List<Person> updatedContacts = null;
      if (request.getParameter("contact_method").equals("Add Contact")) {
         contactMethod = "add";
         updatedContacts = persons.stream()
                     .filter(person->person.getContactInformation() == null)
                     .collect(Collectors.toList()); 
         title = title.concat(" with No Contact Information");
      }
      else {
         updatedContacts = persons.stream()
                     .filter(person->person.getContactInformation() != null)
                     .collect(Collectors.toList()); 
         title = title.concat(" with Contact Information");
         if (request.getParameter("contact_method").equals("Update Contact")) {
            contactMethod = "update";
         }
         else if (request.getParameter("contact_method").equals("Delete Contact")) {
            contactMethod = "delete";
         }
      }

      if (updatedContacts.isEmpty() && contactMethod.equals("add")) {
         response.sendRedirect("noContacts.jsp");
      }
      else if (updatedContacts.isEmpty() && !contactMethod.equals("add")){
         response.sendRedirect("noContacts.jsp");  
      }
      else {
         out.println(docType +
         "<html>\n" +
            "<head><title>" + title + "</title>\n" + 
            "<link rel=\"stylesheet\" href=\"style.css\">\n" +
            "</head>\n" +
            "<body>\n" +
               "<h3 align = \"center\">" + title + "</h3>\n" +
               PersonOrderedList.printPersonInfo("contacts", updatedContacts) + "<br/><br/>" +
               "Choose the person's id you want to " + contactMethod + " the contact:<br/>" +
               "<form action=\"" + (contactMethod.equals("delete") ? "DeleteContact" : (contactMethod.equals("add") ? "AddContactView" : "UpdateContactView")) +"\" method=\"GET\">\n" +
               getPersonIdDropDown(updatedContacts) +
               "<button type=\"submit\">" + request.getParameter("contact_method") + "</button>\n"+
               "</form>\n" +
               "<a href=\"index.html\">Back to Homepage</a>" +
            "</body>" +
         "</html>"
         );   
      }
   }

   public String getPersonIdDropDown(List<Person> persons) {
      String dropDown = "<select id=\"personId\" name=\"personId\">\n";
      for (Person person : persons) {
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