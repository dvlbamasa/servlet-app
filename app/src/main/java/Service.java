import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;
import java.util.Set;
import java.util.Iterator;

public class Service {
	
	public static void promptSuccess(HttpServletRequest request, HttpServletResponse response, String url, String operation, String title) throws ServletException, IOException{
		RequestDispatcher rd = request.getRequestDispatcher(url);        
		String prompt = "Successfully " + operation + " a " + title + "!";
		if (url.equals("/listPersons.jsp")) {
			List<Person> persons = (List<Person>) Dao.getList("Person");
			request.setAttribute("subTitle", "List of Persons");
			request.setAttribute("persons", persons);
			if (title.equals("Contact Information")) {
				request.setAttribute("orderType", "contacts");
			}
			else {
				request.setAttribute("orderType", "persons");
			}
		}
		else {
			List<Role> roles = (List<Role>) Dao.getList("Role");
			request.setAttribute("roles", roles);
		}
		request.setAttribute("title", title);
		request.setAttribute("prompt", prompt);
		rd.forward(request, response);
    }

    public static void promptError(String property, HttpServletRequest request, HttpServletResponse response, String title, RequestDispatcher requestDispatcher) throws ServletException, IOException{
        RequestDispatcher rd = requestDispatcher;
        String prompt = "Invalid " + property + " Input! Please try again.";
        List<Role> roles = (List<Role>) Dao.getList("Role");
        if (title.equals("Update Person")) {
            Person person = (Person) Dao.get(Integer.parseInt(request.getParameter("id")), "Person");
            long [] checkedBox =  getRolesCheckBox(roles, person);
            request.setAttribute("person", person);
            request.setAttribute("checkedBox", checkedBox);
        }
        request.setAttribute("roles", roles);
        request.setAttribute("title", title);
        request.setAttribute("prompt", prompt);
        rd.forward(request, response);
    }


    public static long [] getRolesCheckBox(List<Role> roles, Person person) {
      long [] checkedBox = new long [roles.size()];
      int counter = 0;
      for (Role role : roles) {
          Set<Role> setRoles = person.getRoles();
          Iterator<Role> iterator = setRoles.iterator();
          while (iterator.hasNext()) {
              Role setRole = iterator.next();
              if (setRole.getId() == role.getId()) {
                  checkedBox[counter++] = role.getId();
              }
          }
      }
      return checkedBox;
    }

    public static long [] getPersonsCheckBox(List<Person> persons, Role role) {
      long [] checkedBox = new long [persons.size()];
      int counter = 0;
      for (Person person : persons) {
          Set<Person> setPersons = role.getPersons();
          Iterator<Person> iterator = setPersons.iterator();
          while (iterator.hasNext()) {
              Person setPerson = iterator.next();
              if (setPerson.getId() == person.getId()) {
                checkedBox[counter++] = person.getId();
              }
          }
      }
      return checkedBox;
    }
}