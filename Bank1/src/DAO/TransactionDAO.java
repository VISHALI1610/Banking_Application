package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.bank.model.Transaction;
import com.bank.util.DBConnection;

public class TransactionDAO {
    public boolean saveTransaction(Transaction transaction) {
        boolean status = false;
        try (Connection connection = DBConnection.getConnection()) {
            String query = "INSERT INTO Transaction (account_no, transaction_type, amount) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, transaction.getAccountNo());
            ps.setString(2, transaction.getTransactionType());
            ps.setDouble(3, transaction.getAmount());
            status = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public List<Transaction> getTransactions(String accountNo, int limit) {
        List<Transaction> transactions = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM Transaction WHERE account_no = ? ORDER BY transaction_date DESC LIMIT ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, accountNo);
            ps.setInt(2, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionId(rs.getInt("transaction_id"));
                transaction.setAccountNo(rs.getString("account_no"));
                transaction.setTransactionType(rs.getString("transaction_type"));
                transaction.setAmount(rs.getDouble("amount"));
                transaction.setTransactionDate(rs.getTimestamp("transaction_date"));
                transactions.add(transaction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
