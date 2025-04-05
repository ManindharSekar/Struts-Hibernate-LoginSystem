<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Employee Details</title>
    <script type="text/javascript">
        function addRow() {
            var table = document.getElementById("employeeTable");
            var rowCount = table.rows.length;

            var row = table.insertRow(rowCount);

            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            var cell3 = row.insertCell(2);
            var cell4 = row.insertCell(3);
            var cell5 = row.insertCell(4);
            var cell6 = row.insertCell(5);
            var cell7 = row.insertCell(6);

            cell1.innerHTML = '<input type="text" name="id" />'; 
            cell2.innerHTML = '<input type="text" name="address" />';
            cell3.innerHTML = '<input type="text" name="city" />';
            cell4.innerHTML = '<input type="text" name="state" />';
            cell5.innerHTML = '<input type="text" name="pincode" />';
            cell6.innerHTML = '<button type="button" onclick="deleteRow(this)">Delete</button>';
            cell7.innerHTML = '<button type="button" onclick="updateRow(this)">Update</button>';
        }

        function deleteRow(button) {
            var row = button.parentNode.parentNode;
            row.parentNode.removeChild(row);
        }

        function updateRow(button) {
            var row = button.parentNode.parentNode;
            var cells = row.getElementsByTagName('td');

            var id = cells[0].getElementsByTagName('input')[0].value;
            var address = cells[1].getElementsByTagName('input')[0].value;
            var city = cells[2].getElementsByTagName('input')[0].value;
            var state = cells[3].getElementsByTagName('input')[0].value;
            var pincode = cells[4].getElementsByTagName('input')[0].value;

            alert("Updating employee with ID: " + id + "\nAddress: " + address);
        }
    </script>
</head>
<body>
    <h2>Employee Info</h2>

    <html:form action="find.do" method="post">
        <html:text property="name" />
        <html:submit>Find</html:submit>
        <font color="red"><html:errors property="error.user.name.required" /></font>
        <font color="red"><html:errors property="name" /></font>
    </html:form>

    <br><br>

    <button type="button" onclick="addRow()">Add New Row</button>

    <br><br>

    <html:form action="save.do" method="post">
        <table id="employeeTable" border="1">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Pin No</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="emp" items="${employeeForm.employeeList}" varStatus="status">
                    <tr>
                        <td><html:text property="id" value="${emp.id}" readonly="true" /></td>
                        <td><html:text property="address" value="${emp.address}" /></td>
                        <td><html:text property="city" value="${emp.city}" /></td>
                        <td><html:text property="state" value="${emp.state}" /></td>
                        <td><html:text property="pincode" value="${emp.pincode}" /></td>
                        <td>
                            <html:form action="delete.do" method="post">
                                <html:hidden property="id" value="${emp.id}" />
                                <html:submit>Delete</html:submit>
                            </html:form>
                        </td>
                        <td>
                            <html:form action="update.do" method="post">
                                <html:hidden property="id" value="${emp.id}" />
                                <html:submit>Update</html:submit>
                            </html:form>
                        </td>
                    </tr>
                </c:forEach>
                <logic:empty name="employeeForm" property="employeeList">
                    <tr><td colspan="7">No records found.</td></tr>
                </logic:empty>
            </tbody>
        </table>
        <html:submit>Save</html:submit>
    </html:form>
</body>
</html>

