<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.bank.model.Transaction" %>
<!DOCTYPE html>
<html>
<head>
    <title>Transactions</title>
</head>
<body>
    <h1>Last 10 Transactions</h1>
    <table border="1">
        <tr>
            <th>Transaction ID</th>
            <th>Type</th>
            <th>Amount</th>
            <th>Date</th>
        </tr>
        <%
            Object transactionsObj = request.getAttribute("transactions");
            if (transactionsObj instanceof List<?>) {
                List<?> rawList = (List<?>) transactionsObj;
                boolean allTransactions = true;
                for (Object obj : rawList) {
                    if (!(obj instanceof Transaction)) {
                        allTransactions = false;
                        break;
                    }
                }
                if (allTransactions) {
                    List<Transaction> transactions = (List<Transaction>) rawList;
                    for (Transaction transaction : transactions) {
                        out.println("<tr>");
                        out.println("<td>" + transaction.getTransactionId() + "</td>");
                        out.println("<td>" + transaction.getTransactionType() + "</td>");
                        out.println("<td>" + transaction.getAmount() + "</td>");
                        out.println("<td>" + transaction.getTransactionDate() + "</td>");
                        out.println("</tr>");
                    }
                } else {
                    out.println("<tr><td colspan='4'>Error: Data is not of type Transaction</td></tr>");
                }
            } else {
                out.println("<tr><td colspan='4'>No transactions found</td></tr>");
            }
        %>
    </table>
    <form action="TransactionServlet" method="post">
        <input type="hidden" name="action" value="print">
        <input type="submit" value="Print">
    </form>
</body>
</html>