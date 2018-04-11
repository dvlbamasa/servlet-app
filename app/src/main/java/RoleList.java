import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;
import java.util.Collections;
import javax.servlet.annotation.WebServlet;

@WebServlet("/roleList")
public class RoleList extends HttpServlet {

   public void init() throws ServletException {
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      List<Role> roles = (List<Role>) Dao.getList("Role");   
      String title = "Role";
      request.setAttribute("roles", roles);
      request.setAttribute("title", title);
      request.getRequestDispatcher("listRoles.jsp").forward(request, response);
   }
}