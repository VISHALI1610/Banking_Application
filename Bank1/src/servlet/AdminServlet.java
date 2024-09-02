package com.bank.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.bank.dao.AdminDAO;
import com.bank.model.Admin;
import java.io.IOException;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        AdminDAO adminDAO = new AdminDAO();
        
        if ("login".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Admin admin = adminDAO.login(username, password);
            if (admin != null) {
                HttpSession session = request.getSession();
                session.setAttribute("admin", admin);
                response.sendRedirect("dashboard.jsp");
            } else {
                response.sendRedirect("login.jsp?error=Invalid Username or Password");
            }
        }
    }
}
