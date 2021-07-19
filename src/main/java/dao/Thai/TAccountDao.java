/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Thai;
import entity.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.SQLServerConnection;

/**
 *
 * @author thaip
 */
public class TAccountDao {
    ResultSet rs ;
    public List<Account> getAll(){
        List<Account> list = new ArrayList<Account>();
        String sql ="SELECT * FROM account";
        try(Connection conn = SQLServerConnection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(sql);)
        {
            rs =ps.executeQuery();
            while(rs.next()){
                Account acc = Account.builder()
                        .accountId(rs.getInt("accountId"))
                        .name(rs.getString("name"))
                        .email(rs.getString("email"))
                        .role(rs.getInt("role"))
                        .status(rs.getInt("status"))
                        .build();
                list.add(acc);
            }
            return list;
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    
    public List<Account> getAllByName(String name)
    {
        List<Account>list = new ArrayList<Account>();
        String sql = "SELECT * FROM account WHERE name LIKE ? ORDER BY name";
                name = "%"+name+"%";
        try(Connection conn = SQLServerConnection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setString(1, name);
            rs= ps.executeQuery();
            while (rs.next()) {
                Account acc = Account.builder()
                        .accountId(rs.getInt("accountId"))
                        .name(rs.getString("name"))
                        .email(rs.getString("email"))
                        .role(rs.getInt("role"))
                        .status(rs.getInt("status"))
                        .build();
                list.add(acc);
            }
            return list;
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public void updateAccount(Account ac){
        String sql ="UPDATE account "
                + "SET name = ?, "
                + "email    = ?, "
                + "status     = ?, "
                + "[role]     = ? "
                + "WHERE accountId =?";
        try(Connection conn = SQLServerConnection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setString(1, ac.getName());
            ps.setString(2, ac.getEmail());
            ps.setInt(3, ac.getStatus());
            ps.setInt(4, ac.getRole());
            ps.setInt(5, ac.getAccountId());
            ps.executeUpdate();
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        } 
    }
    
        public boolean remove(int id) {
        String query = "DELETE FROM account WHERE accountId = ?";
        int check = 0;
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setInt(1, id);
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check > 0;
    }

        
        public boolean updateState(int id,int state)
        {
            String sql = "UPDATE account SET "
                + "status = ?"                
                + "WHERE accountId = ?";
            int check = 0;
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setObject(1, state);
            ps.setObject(2, id);

            check = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check > 0;
        }
        
        public boolean punishAccount(int accId)
        {
            String sql ="UPDATE account SET status = 0 WHERE accountId = ?";
            int check =0;
            try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setObject(1, accId);
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
            
            return check>0;
        }
        
       
}