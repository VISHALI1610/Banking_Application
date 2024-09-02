package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.bank.model.Admin;
import com.bank.util.DBConnection;

public class AdminDAO {
    public Admin login(String username, String password) {
        Admin admin = null;
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM Admin WHERE username = ? AND password = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                admin = new Admin();
                admin.setAdminId(rs.getInt("admin_id"));
                admin.setUsername(rs.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }
}
