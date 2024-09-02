package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.bank.model.Customer;
import com.bank.util.DBConnection;
import com.bank.util.PasswordUtil;

public class CustomerDAO {
    public boolean registerCustomer(Customer customer) {
        boolean status = false;
        try (Connection connection = DBConnection.getConnection()) {
            String query = "INSERT INTO Customer (full_name, address, mobile_no, email_id, account_type, balance, dob, id_proof, account_no, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, customer.getFullName());
            ps.setString(2, customer.getAddress());
            ps.setString(3, customer.getMobileNo());
            ps.setString(4, customer.getEmailId());
            ps.setString(5, customer.getAccountType());
            ps.setDouble(6, customer.getBalance());
            ps.setDate(7, new java.sql.Date(customer.getDob().getTime()));
            ps.setString(8, customer.getIdProof());
            ps.setString(9, customer.getAccountNo());
            ps.setString(10, PasswordUtil.hashPassword(customer.getPassword()));
            status = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public boolean deleteCustomer(String accountNo) {
        boolean status = false;
        try (Connection connection = DBConnection.getConnection()) {
            String query = "DELETE FROM Customer WHERE account_no = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, accountNo);
            status = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public boolean updateCustomer(Customer customer) {
        boolean status = false;
        try (Connection connection = DBConnection.getConnection()) {
            String query = "UPDATE Customer SET full_name = ?, address = ?, mobile_no = ?, email_id = ?, account_type = ?, dob = ?, id_proof = ? WHERE account_no = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, customer.getFullName());
            ps.setString(2, customer.getAddress());
            ps.setString(3, customer.getMobileNo());
            ps.setString(4, customer.getEmailId());
            ps.setString(5, customer.getAccountType());
            ps.setDate(6, new java.sql.Date(customer.getDob().getTime()));
            ps.setString(7, customer.getIdProof());
            ps.setString(8, customer.getAccountNo());
            status = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public Customer getCustomer(String accountNo) {
        Customer customer = null;
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM Customer WHERE account_no = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, accountNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setFullName(rs.getString("full_name"));
                customer.setAddress(rs.getString("address"));
                customer.setMobileNo(rs.getString("mobile_no"));
                customer.setEmailId(rs.getString("email_id"));
                customer.setAccountType(rs.getString("account_type"));
                customer.setBalance(rs.getDouble("balance"));
                customer.setDob(rs.getDate("dob"));
                customer.setIdProof(rs.getString("id_proof"));
                customer.setAccountNo(rs.getString("account_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM Customer";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setFullName(rs.getString("full_name"));
                customer.setAddress(rs.getString("address"));
                customer.setMobileNo(rs.getString("mobile_no"));
                customer.setEmailId(rs.getString("email_id"));
                customer.setAccountType(rs.getString("account_type"));
                customer.setBalance(rs.getDouble("balance"));
                customer.setDob(rs.getDate("dob"));
                customer.setIdProof(rs.getString("id_proof"));
                customer.setAccountNo(rs.getString("account_no"));
                customers.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }
}
