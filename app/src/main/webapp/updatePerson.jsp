<html>
    <body>
    	Please Fill out the Person Information Form: <br/><br/>
      	<form action = "AddPerson" method = "POST">
	        First Name:<br/>
	        <input type = "text" name = "first_name" value="<%= request.getParameter("personId")%>" required><br/> 
	        Middle Name:<br/> 
	        <input type = "text" name = "middle_name" required/><br/>
	        Last Name:<br/> 
	        <input type = "text" name = "last_name" required/><br/>
	        Gender:<br/>
	        <input type = "radio" name = "gender" value="male" /> Male
	        <input type = "radio" name = "gender" value="female" /> Female<br/>
	        
	        Birthday:<br/>
	        <input type = "date" name = "birthday" value="mm/dd/yyyy" required/><br/>
	        GWA:<br/> 
	        <input type = "number" min="1" max="3" step = "0.01" name = "gwa" required/><br/>
	        Currently Employed:<br/>
	        <input type = "radio" name = "currently_employed" value="y" required/> Yes
	        <input type = "radio" name = "currently_employed" value="n"  required/> No<br/> 
	        Date Hired:<br/> 
	        <input type = "date" name = "date_hired" value="mm/dd/yyyy" required/><br/><br/>
	        Address Information<br/><br/>
	        Street Number:<br/> 
	        <input type = "text" name = "street_no" required/><br/>
	        Barangay:<br/> 
	        <input type = "text" name = "barangay" required/><br/>
	        Municipality:<br/> 
	        <input type = "text" name = "municipality" required/><br/>
	        Zip Code:<br/> 
	        <input type = "text" name = "zip_code" required/><br/>
	        Contact Information<br/><br/>
	        Landline:<br/> 
	        <input type = "text" name = "landline" required/><br/>
	        Mobile Number:<br/> 
	        <input type = "text" name = "mobile_number" required/><br/>
	        Email Address:<br/> 
	        <input type = "text" name = "email" required/><br/><br/> 
	        
	        <input type = "submit" value = "Submit" />
     	</form>
    </body>
</html>