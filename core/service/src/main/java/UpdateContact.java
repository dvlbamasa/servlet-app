import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class UpdateContact extends HttpServlet {

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      ContactInformation personContactInformation = (ContactInformation) Dao.get(Integer.parseInt(request.getParameter("id")), "ContactInformation");
      personContactInformation.setLandline(request.getParameter("landline"));
      personContactInformation.setMobileNumber(request.getParameter("mobile_number"));
      personContactInformation.setEmail(request.getParameter("email"));
      Dao.update(personContactInformation);
      response.sendRedirect("addSuccessful.jsp");
   }

   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      doGet(request, response);
   }
}