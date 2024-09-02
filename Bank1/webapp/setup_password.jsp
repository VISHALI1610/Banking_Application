<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Set Up Password</title>
</head>
<body>
    <h1>Set Up New Password</h1>
    <form action="CustomerServlet" method="post">
        <input type="hidden" name="action" value="setupPassword">
        <label>Account No:</label><input type="text" name="account_no" required><br>
        <label>Temporary Password:</label><input type="password" name="temp_password" required><br>
        <label>New Password:</label><input type="password" name="new_password" required><br>
        <input type="submit" value="Set Up Password">
    </form>
    <%
        if (request.getParameter("error") != null) {
            out.println("<p style='color:red'>" + request.getParameter("error") + "</p>");
        } else if (request.getParameter("success") != null) {
            out.println("<p style='color:green'>" + request.getParameter("success") + "</p>");
        }
    %>
</body>
</html>
