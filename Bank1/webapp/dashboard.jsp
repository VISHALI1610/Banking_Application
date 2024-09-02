<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.bank.model.Customer" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Dashboard</title>
</head>
<body>
    <%
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            response.sendRedirect("log.jsp");
            return;
        }
    %>
    <h1>Welcome, <%= customer.getFullName() %></h1>
    <p>Account No: <%= customer.getAccountNo() %></p>
    <p>Balance: <%= customer.getBalance() %></p>
    <form action="TransactionServlet" method="post">
        <input type="hidden" name="action" value="deposit">
        <label>Deposit Amount:</label><input type="number" name="amount" required><br>
        <input type="submit" value="Deposit">
    </form>
    <form action="TransactionServlet" method="post">
        <input type="hidden" name="action" value="withdraw">
        <label>Withdraw Amount:</label><input type="number" name="amount" required><br>
        <input type="submit" value="Withdraw">
    </form>
    <form action="TransactionServlet" method="post">
        <input type="hidden" name="action" value="close">
        <input type="submit" value="Close Account">
    </form>
    <a href="TransactionServlet?action=view">View Transactions</a>
    <form action="CustomerServlet" method="post">
        <input type="hidden" name="action" value="logout">
        <input type="submit" value="Logout">
    </form>
</body>
</html>

