<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
 <link rel="stylesheet" type="text/css" href="css/resetPassword.css">
</head>
<body>
<h2>Reset Password</h2>
  <html:form action="/resetPassword" method="POST" styleClass="login-form">
           

           <bean:message key="label.user.newpassword"/> <html:password property="newPassword" size="25" maxlength="50" /><font color="red"><html:errors property="newPassword"/></font><br>
            <br/><br/>

            <bean:message key="label.user.confirmpassword"/><html:password property="confirmPassword" size="25" maxlength="50" /><font color="red"><html:errors property="confirmPassword"/></font><br>
            <br/><br/>

            <html:submit value="Reset Password" styleClass="submit-btn"/>
        </html:form>

        <!-- Link to login page -->
        <a href="login.do">Back to Login</a>

</body>
</html>