<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Login</title>
</head>
<body>
    <h1>Admin Login</h1>
    <form action="AdminServlet" method="post">
        <input type="hidden" name="action" value="login">
        <label>Username:</label><input type="text" name="username" required><br>
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
