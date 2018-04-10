import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/addContactView")
public class AddContactView extends HttpServlet {

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      String title = "Add Contact Information";
      request.setAttribute("personId", request.getParameter("personId"));
      request.setAttribute("title", title);
      request.getRequestDispatcher("addContact.jsp").forward(request, response);
   }
}