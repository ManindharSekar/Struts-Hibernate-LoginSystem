<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" type="text/css" href="css/forgotPassword.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Forgot Passworsd Page</h2>
<html:form action="forgotPassword" styleClass="login-form">
<bean:message key="label.user.username" /><html:text property="userName"></html:text><font color="red"><html:errors property="userName"/></font><br>
<bean:message key="label.user.email" /><br><html:text property="email"></html:text><br><font color="red"><html:errors property="email"/></font>
<html:submit value="Forgot Password" styleClass="submit-btn"/>

</html:form>

<a href="login.do">Back to Login</a>
</body>
</html>