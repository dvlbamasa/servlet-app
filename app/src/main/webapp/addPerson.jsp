<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<head>
      	<title><c:out value="${title}"></c:out></title> 
     	 <link rel="stylesheet" href="style.css">
   	</head>
    <body>
    	<h1><c:out value="${title}"></c:out></h1><br/>
    	<c:if test="${prompt ne null}">
         	<font color=red><c:out value="${prompt}"></c:out></font><br/><br/>
     	</c:if>
    	Please Fill out the Person Information Form: <br/><br/>
      	<form action = "addPerson" method = "POST">
	        First Name:<br/>
	        <input type = "text" name = "first_name" maxlength="20" required><br/> 
	        Middle Name:<br/> 
	        <input type = "text" name = "middle_name" maxlength="20" required/><br/>
	        Last Name:<br/> 
	        <input type = "text" name = "last_name" maxlength="20" required/><br/>
	        Gender:<br/>
	        <input type = "radio" name = "gender" value="male" /> Male
	        <input type = "radio" name = "gender" value="female" /> Female<br/>
	        
	        Birthday (YYYY-MM-DD):<br/>
	        <input type = "date" name = "birthday" required/><br/>
	        GWA:<br/> 
	        <input type = "number" min="1" max="3" step = "0.01" name = "gwa" required/><br/>
	        Currently Employed:<br/>
	        <input type = "radio" name = "currently_employed" value="y" required/> Yes
	        <input type = "radio" name = "currently_employed" value="n"  required/> No<br/> 
	        Date Hired (YYYY-MM-DD):<br/> 
	        <input type = "date" name = "date_hired" required/><br/><br/>
	        Address Information<br/><br/>
	        Street Number:<br/> 
	        <input type = "text" name = "street_no" required/><br/>
	        Barangay:<br/> 
	        <input type = "text" name = "barangay" maxlength="20" required/><br/>
	        Municipality:<br/> 
	        <input type = "text" name = "municipality" maxlength="20" required/><br/>
	        Zip Code:<br/> 
	        <input type = "text" name = "zip_code" required/><br/><br/>
	        Contact Information<br/><br/>
	        Landline:<br/> 
	        <input type = "text" name = "landline" maxlength="20" required/><br/>
	        Mobile Number:<br/> 
	        <input type = "text" name = "mobile_number" maxlength="20" required/><br/>
	        Email Address:<br/> 
	        <input type = "text" name = "email" maxlength="30" required/><br/><br/> 
	        Check the Roles you want to set to this person:<br/>

	        <c:forEach items="${requestScope.roles}" var="role">
	        	<input type="checkbox" name="rolesCheckBox" value="<c:out value='${role.id}'/>"/><c:out value="${role.name}"/><br/>
	        </c:forEach>
	        <br/>

	        <input type = "submit" value = "Add Person" />
     	</form>
    </body>
</html>