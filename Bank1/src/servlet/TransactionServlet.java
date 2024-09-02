package com.bank.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import com.bank.dao.CustomerDAO;
import com.bank.dao.TransactionDAO;
import com.bank.model.Customer;
import com.bank.model.Transaction;

@WebServlet("/TransactionServlet")
public class TransactionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        TransactionDAO transactionDAO = new TransactionDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            response.sendRedirect("log.jsp?error=Session Expired. Please Login Again.");
            return;
        }
        
        if ("deposit".equals(action)) {
            double amount = Double.parseDouble(request.getParameter("amount"));
            customer.setBalance(customer.getBalance() + amount);
            Transaction transaction = new Transaction();
            transaction.setAccountNo(customer.getAccountNo());
            transaction.setTransactionType("Deposit");
            transaction.setAmount(amount);
            boolean status = transactionDAO.saveTransaction(transaction);
            if (status) {
                customerDAO.updateCustomer(customer);
                response.sendRedirect("dashboard.jsp?success=Amount Deposited Successfully");
            } else {
                response.sendRedirect("dashboard.jsp?error=Error Depositing Amount");
            }
        } else if ("withdraw".equals(action)) {
            double amount = Double.parseDouble(request.getParameter("amount"));
            if (customer.getBalance() >= amount) {
                customer.setBalance(customer.getBalance() - amount);
                Transaction transaction = new Transaction();
                transaction.setAccountNo(customer.getAccountNo());
                transaction.setTransactionType("Withdraw");
                transaction.setAmount(amount);
                boolean status = transactionDAO.saveTransaction(transaction);
                if (status) {
                    customerDAO.updateCustomer(customer);
                    response.sendRedirect("dashboard.jsp?success=Amount Withdrawn Successfully");
                } else {
                    response.sendRedirect("dashboard.jsp?error=Error Withdrawing Amount");
                }
            } else {
                response.sendRedirect("dashboard.jsp?error=Insufficient Balance");
            }
        } else if ("close".equals(action)) {
            if (customer.getBalance() == 0) {
                boolean status = customerDAO.deleteCustomer(customer.getAccountNo());
                if (status) {
                    session.invalidate();
                    response.sendRedirect("log.jsp?success=Account Closed Successfully");
                } else {
                    response.sendRedirect("dashboard.jsp?error=Error Closing Account");
                }
            } else {
                response.sendRedirect("dashboard.jsp?error=Withdraw All Money Before Closing Account");
            }
        }
    }
}
