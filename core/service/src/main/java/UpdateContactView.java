import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Locale;
import javax.servlet.annotation.WebServlet;

@WebServlet("/updateContactView")
public class UpdateContactView extends HttpServlet {

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      ContactInformation contactInformation = (ContactInformation) Dao.get(Integer.parseInt(request.getParameter("personId")), "ContactInformation");
      String title = "Update Contact Information";
      request.setAttribute("personId", request.getParameter("personId"));
      request.setAttribute("contactInformation", contactInformation);
      request.setAttribute("title", title);
      request.getRequestDispatcher("updateContact.jsp").forward(request, response);
   }
}