import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/DeletePerson")
public class DeletePerson extends HttpServlet {

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      Person person = (Person) Dao.get(Integer.parseInt(request.getParameter("personId")), "Person");
      Dao.delete(person);
      response.sendRedirect("PersonList");
   }
}