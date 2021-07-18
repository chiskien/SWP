/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import jdbc.SQLServerConnection;

/**
 *
 * @author hoang
 */
public class AccountDao {

    public List<String> getAllEmail(){
        String query = "SELECT email from account";
        int check = 0;
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();
            List<String> ls = new ArrayList();
            while (rs.next()) {
                ls.add(rs.getString("email"));
            }
            return ls;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Account login(String mail, String pass) {
        String query = "SELECT TOP 1 * FROM account WHERE email = ? AND password = ?";
        int check = 0;
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setString(1, mail);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Account.builder()
                        .accountId(rs.getInt(1))
                        .email(rs.getString(2))
                        .password(rs.getString(3))
                        .status(rs.getInt(4))
                        .role(rs.getInt(5))
                        .activeCode(rs.getString(6))
                        .name(rs.getString(7))
                        .build();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean register(Account acc) {
        int check = 0;
        String query = "INSERT INTO account VALUES (?,?,?,?,?,?)";        
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setObject(1, acc.getEmail());
            ps.setObject(2, acc.getPassword());
            ps.setObject(3, 2);
            ps.setObject(4, 1);
            
            ps.setObject(5, acc.getActiveCode());
            ps.setObject(6,acc.getName());
            check = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return check > 0;
    }
    public Account getOne(int id){
        String query = "SELECT * FROM account WHERE accountId = ?";
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
         
            if (rs.next()) {
                Account acc = Account.builder()
                        .accountId(rs.getInt("accountId"))
                        .email(rs.getString("email"))
                        .password(rs.getString("password"))
                        .name(rs.getString("name"))
                        .activeCode(rs.getString("activeCode"))
                        .role(rs.getInt("role"))
                        .status(rs.getInt("status"))
                        .build();
                return acc;
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public String getActivateCode(String email){
        String query ="SELECT activeCode from account where email = ?";
        try(Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)){
            ps.setObject(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString("activeCode");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
    public Account getOne(String email){
        String query = "SELECT * FROM account WHERE email = ?";
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setObject(1, email);
            ResultSet rs = ps.executeQuery();
         
            if (rs.next()) {
                Account acc = Account.builder()
                        .accountId(rs.getInt("accountId"))
                        .email(rs.getString("email"))
                        .password(rs.getString("password"))
                        .name(rs.getString("name"))
                        .activeCode(rs.getString("activeCode"))
                        .role(rs.getInt("role"))
                        .status(rs.getInt("status"))
                        .build();
                return acc;
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public boolean authenSuccess(String email){
        String query = "UPDATE account SET status = 1 WHERE email = ?";
        int check = 0;
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setObject(1, email);
            check = ps.executeUpdate();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return check >0;
    }
    public List<Account> getAllFollowNotification(int bookId){
        String query = "SELECT a.* FROM account a, following b WHERE a.accountId = b.accountId AND b.bookId = ?";
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setObject(1, bookId);
            ResultSet rs = ps.executeQuery();
            List<Account> ls = new ArrayList();
            while (rs.next()) {
                Account acc = Account.builder()
                        .accountId(rs.getInt("accountId"))
                        .build();
                ls.add(acc);
            }
            return ls;
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
