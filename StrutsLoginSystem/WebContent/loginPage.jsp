<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
 <link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
<h2>LogIn Page</h2>

<html:form action="/login"  styleClass="login-form">
<bean:message key="label.user.username" /><html:text property="userName"></html:text>

<bean:message key="label.user.password" /><html:password property="password"></html:password>
                 <a href="forgotPassword.do">forgot Password?</a>
<font color="red"><html:errors property="login" /></font><br>
<html:submit property="submitButton" styleClass="submit-btn"><bean:message key="label.user.button.login" /></html:submit>

<html:submit property="createAccountButton" styleClass="submit-btn"><bean:message key="label.user.button.createaccount" /></html:submit>
</html:form>

</body>
</html>