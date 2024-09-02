<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register Customer</title>
</head>
<body>
    <h1>Register Customer</h1>
    <form action="CustomerServlet" method="post">
        <input type="hidden" name="action" value="register">
        <label>Full Name:</label><input type="text" name="full_name" required><br>
        <label>Address:</label><input type="text" name="address" required><br>
        <label>Mobile No:</label><input type="text" name="mobile_no" required><br>
        <label>Email ID:</label><input type="email" name="email_id" required><br>
        <label>Account Type:</label>
        <select name="account_type" required>
            <option value="Saving">Saving</option>
            <option value="Current">Current</option>
        </select><br>
        <label>Initial Balance:</label><input type="number" name="balance" min="1000" required><br>
        <label>Date of Birth:</label><input type="date" name="dob" required><br>
        <label>ID Proof:</label><input type="text" name="id_proof" required><br>
        <input type="submit" value="Register">
    </form>
    <%
        if (request.getParameter("success") != null) {
            out.println("<p style='color:green'>" + request.getParameter("success") + "</p>");
        } else if (request.getParameter("error") != null) {
            out.println("<p style='color:red'>" + request.getParameter("error") + "</p>");
        }
    %>
</body>
</html>
