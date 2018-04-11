<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
	<head>
      	<title><c:out value="${title}"></c:out></title> 
     	 <link rel="stylesheet" href="style.css">
   	</head>
    <body>
    	<h1><c:out value="${title}"></c:out></h1><br/>
    	<c:if test="${prompt ne null}">
         	<font color=red><c:out value="${prompt}"></c:out></font><br/><br/>
     	</c:if>
    	Please Fill out the New Person Information Form: <br/><br/>
      	<form action = "updatePerson" method = "POST">
	        <input type="hidden" name="id" value="<c:out value='${person.id}'/>"/>
	        First Name:<br/>
	        <input type = "text" name = "first_name" maxlength="20" value="<c:out value='${person.name.firstName}'/>" required><br/> 
	        Middle Name:<br/> 
	        <input type = "text" name = "middle_name" maxlength="20" value="<c:out value='${person.name.middleName}'/>" required/><br/>
	        Last Name:<br/> 
	        <input type = "text" name = "last_name" maxlength="20" value="<c:out value='${person.name.lastName}'/>" required/><br/>
	        Gender:<br/>
	        <c:if test="${person.gender eq 'MALE'}"> 
	        	<input type = "radio" name = "gender" value="male" checked/> Male
	        	<input type = "radio" name = "gender" value="female" /> Female<br/>
	    	</c:if>
	    	<c:if test="${person.gender eq 'FEMALE'}"> 
	        	<input type = "radio" name = "gender" value="male"/> Male
	        	<input type = "radio" name = "gender" value="female" checked/> Female<br/>
	    	</c:if>
	        
	        Birthday (YYYY-MM-DD):<br/>
	        <input type = "date" name = "birthday" value="<c:out value='${fn:substring(person.birthday,0,10)}'/>" required/><br/>
	        GWA:<br/> 
	        <input type = "number" min="1" max="3" step = "0.01" name = "gwa" value="<c:out value='${person.gwa}'/>" required/><br/>
	        Currently Employed:<br/>

	        <c:if test="${person.currentlyEmployed eq true}">
	        	<input type = "radio" name = "currently_employed" value="y" checked required/> Yes
	        	<input type = "radio" name = "currently_employed" value="n"  required/> No<br/> 
	        </c:if>
	        <c:if test="${person.currentlyEmployed eq false}">
	        	<input type = "radio" name = "currently_employed" value="y" required/> Yes
	        	<input type = "radio" name = "currently_employed" value="n" checked required/> No<br/> 
	        </c:if>
	        Date Hired (YYYY-MM-DD):<br/> 
	        <input type = "date" name = "date_hired" value="<c:out value='${fn:substring(person.dateHired,0,10)}'/>" required/><br/><br/>
	        Address Information<br/><br/>
	        Street Number:<br/> 
	        <input type = "text" name = "street_no" value="<c:out value='${person.address.streetNo}'/>" required/><br/>
	        Barangay:<br/> 
	        <input type = "text" name = "barangay" value="<c:out value='${person.address.barangay}'/>" maxlength="20" required/><br/>
	        Municipality:<br/> 
	        <input type = "text" name = "municipality" value="<c:out value='${person.address.municipality}'/>" maxlength="20" required/><br/>
	        Zip Code:<br/> 
	        <input type = "text" name = "zip_code" value="<c:out value='${person.address.zipCode}'/>" required/><br/><br/>
	        Contact Information<br/><br/>
	        Landline:<br/> 
	        <c:if test="${person.contactInformation eq null}">
		        <input type = "text" name = "landline" maxlength="20" required/><br/>
		        Mobile Number:<br/> 
		        <input type = "text" name = "mobile_number" maxlength="20" required/><br/>
		        Email Address:<br/> 
		        <input type = "text" name = "email" maxlength="30" required/><br/><br/> 
		    </c:if>
		    <c:if test="${person.contactInformation ne null}">
		    	<input type = "text" name = "landline" maxlength="20" value="<c:out value='${person.contactInformation.landline}'/>" required/><br/>
		        Mobile Number:<br/> 
		        <input type = "text" name = "mobile_number" maxlength="20" value="<c:out value='${person.contactInformation.mobileNumber}'/>" required/><br/>
		        Email Address:<br/> 
		        <input type = "text" name = "email" maxlength="30" value="<c:out value='${person.contactInformation.email}'/>" required/><br/><br/> 
		    </c:if>
		    Check the Roles you want to set to this person:<br/>
		    <c:forEach items="${requestScope.roles}" var="role">
		    	<input type="checkbox" name="rolesCheckBox" value="<c:out value='${role.id}'/>" 
			    	<c:forEach items="${checkedBox}" var="checked">
			    		<c:if test="${role.id == checked}">
			    			checked
			    		</c:if>
			    	</c:forEach>
		    	/><c:out value="${role.name}"/><br/>
	        </c:forEach><br/>
	        <input type = "submit" value = "Update Person" />
     	</form>
    </body>
</html>