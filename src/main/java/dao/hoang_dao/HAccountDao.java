/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.hoang_dao;

import entity.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jdbc.SQLServerConnection;

/**
 *
 * @author hoang
 */
public class HAccountDao {
    public Account getAccountInfo(int accountId){
        String query = "SELECT * FROM account WHERE accountId = ?";
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account temp = Account.builder()
                        .accountId(accountId)
                        .email(rs.getString("email"))
                        .name(rs.getString("name"))
                        .password(rs.getString("password"))
                        .build();
                return temp;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public boolean UpdateAccount(int accountId, String name, String email, String password){
        String query = "UPDATE account SET name = ?, email = ?, password = ? where accountId = ?";
        int check = 0;
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setObject(1, name);
            ps.setObject(2,email);
            ps.setObject(3, password);
            ps.setObject(4, accountId);
            check = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check > 0;
    }
}
