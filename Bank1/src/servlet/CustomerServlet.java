package com.bank.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.bank.dao.CustomerDAO;
import com.bank.model.Customer;
import java.io.IOException;

@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        CustomerDAO customerDAO = new CustomerDAO();
        
        if ("register".equals(action)) {
            Customer customer = new Customer();
            customer.setFullName(request.getParameter("full_name"));
            customer.setAddress(request.getParameter("address"));
            customer.setMobileNo(request.getParameter("mobile_no"));
            customer.setEmailId(request.getParameter("email_id"));
            customer.setAccountType(request.getParameter("account_type"));
            customer.setBalance(Double.parseDouble(request.getParameter("balance")));
            customer.setDob(java.sql.Date.valueOf(request.getParameter("dob")));
            customer.setIdProof(request.getParameter("id_proof"));
            customer.setAccountNo(request.getParameter("account_no"));
            customer.setPassword(request.getParameter("password"));
            boolean status = customerDAO.registerCustomer(customer);
            if (status) {
                response.sendRedirect("register_customer.jsp?success=Customer Registered Successfully");
            } else {
                response.sendRedirect("register_customer.jsp?error=Error Registering Customer");
            }
        } else if ("delete".equals(action)) {
            String accountNo = request.getParameter("account_no");
            boolean status = customerDAO.deleteCustomer(accountNo);
            if (status) {
                response.sendRedirect("manage_customer.jsp?success=Customer Deleted Successfully");
            } else {
                response.sendRedirect("manage_customer.jsp?error=Error Deleting Customer");
            }
        } else if ("update".equals(action)) {
            Customer customer = new Customer();
            customer.setFullName(request.getParameter("full_name"));
            customer.setAddress(request.getParameter("address"));
            customer.setMobileNo(request.getParameter("mobile_no"));
            customer.setEmailId(request.getParameter("email_id"));
            customer.setAccountType(request.getParameter("account_type"));
            customer.setDob(java.sql.Date.valueOf(request.getParameter("dob")));
            customer.setIdProof(request.getParameter("id_proof"));
            customer.setAccountNo(request.getParameter("account_no"));
            boolean status = customerDAO.updateCustomer(customer);
            if (status) {
                response.sendRedirect("manage_customer.jsp?success=Customer Updated Successfully");
            } else {
                response.sendRedirect("manage_customer.jsp?error=Error Updating Customer");
            }
        }
    }
}
