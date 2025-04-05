<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/createAccount.css">
</head>
<body>
<h2>Create New Account</h2>
<pre>
<html:form action="createAccount" styleClass="login-form">
<bean:message key="label.user.name" /><br><html:text property="name"></html:text><br><font color="red"><html:errors property="name" /></font><br>
<bean:message key="label.user.city" /><br><html:text property="city"></html:text><br><font color="red"><html:errors property="city" /></font><br>
<bean:message key="label.user.phone" /><br><html:text property="phone"></html:text><br><font color="red"><html:errors property="phone" /></font><br>
<bean:message key="label.user.username" /><br><html:text property="userName"></html:text><br><font color="red"><html:errors property="userName" /></font><br>
<bean:message key="label.user.email" /><br><html:text property="email"></html:text><br><font color="red"><html:errors property="email" /></font><br>
<bean:message key="label.user.password" /><br><html:password property="password"></html:password><br><font color="red"><html:errors property="password" /></font><br>
<bean:message key="label.user.confirmpassword" /><br><html:password property="confirmPassword"></html:password><br><font color="red"><html:errors property="confirmPassword" /></font><br>
<html:submit styleClass="submit-btn"><bean:message key="label.user.button.save" /></html:submit>
</html:form>
</pre>
</body>
</html>