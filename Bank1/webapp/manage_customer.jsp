<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Customers</title>
</head>
<body>
    <h1>Manage Customers</h1>
    <form action="CustomerServlet" method="post">
        <input type="hidden" name="action" value="update">
        <label>Account No:</label><input type="text" name="account_no" required><br>
        <label>Full Name:</label><input type="text" name="full_name"><br>
        <label>Address:</label><input type="text" name="address"><br>
        <label>Mobile No:</label><input type="text" name="mobile_no"><br>
        <label>Email ID:</label><input type="email" name="email_id"><br>
        <label>Account Type:</label>
        <select name="account_type">
            <option value="Saving">Saving</option>
            <option value="Current">Current</option>
        </select><br>
        <label>Date of Birth:</label><input type="date" name="dob"><br>
        <label>ID Proof:</label><input type="text" name="id_proof"><br>
        <input type="submit" value="Update">
    </form>
    <form action="CustomerServlet" method="post">
        <input type="hidden" name="action" value="delete">
        <label>Account No:</label><input type="text" name="account_no" required><br>
        <input type="submit" value="Delete">
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
