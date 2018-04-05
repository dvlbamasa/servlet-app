<%@ page import="src.main.java.Person, Role, java.util.List"%>
<html>
    <body>
    	Please Fill out the Role Information Form: <br/><br/>
      	<form action = "AddRole" method = "POST">
	        Role Name:<br/>
	        <input type = "text" name = "role_name" required><br/> 
	        <%
	        	List<Person> persons =  (List<Person>) Dao.getList("Person");
	        	String personList = "";
			    for (Person person : persons) {
		    	    personList = personList.concat("<input type=\"checkbox\" name=\"persons\" value=\"" + person.getId() +"\"/>" + person.getName().getFirstName() + " " + person.getName().getLastName() + "<br/>");
			    }
			    personList = personList.concat("<br/><br/>");	
			    out.println(personList);
	        %>
	        <input type = "submit" value = "Add Role" />
     	</form>
    </body>
</html>
