<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Login</title>
</head>
<body>
    <h1>Customer Login</h1>
    <form action="CustomerServlet" method="post">
        <input type="hidden" name="action" value="login">
        <label>Account No:</label><input type="text" name="account_no" required><br>
        <label>Password:</label><input type="password" name="password" required><br>
        <input type="submit" value="Login">
    </form>
    <%
        if (request.getParameter("error") != null) {
            out.println("<p style='color:red'>" + request.getParameter("error") + "</p>");
        }
    %>
</body>
</html>