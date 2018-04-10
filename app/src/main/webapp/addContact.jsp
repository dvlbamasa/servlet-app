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
        Please Fill out the New Contact Information Form: <br/><br/> 
        <form action = "addContact" method = "POST">
            Contact Information<br/><br/> 
            Landline:<br/> 
            <input type="hidden" name="id" value="<c:out value='${personId}'/>"/>
            <input type = "text" name = "landline" maxlength="20" required/><br/> 
            Mobile Number:<br/>
            <input type = "text" name = "mobile_number" maxlength="20" required/><br/>
            Email Address:<br/>
            <input type = "text" name = "email" maxlength="30" required/><br/><br/>  
            
            <input type = "submit" value = "Add Contact" />
        </form>
    </body>
</html>