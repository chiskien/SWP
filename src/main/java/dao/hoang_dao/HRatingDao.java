/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.hoang_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jdbc.SQLServerConnection;

/**
 *
 * @author hoang
 */
public class HRatingDao {
    public boolean isRated(int accountId, int bookId){
        String query = "SELECT * FROM rating WHERE accountId = ? and bookId = ?";
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setInt(1, accountId);
            ps.setInt(2, bookId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public boolean updateRating(int accountId, int bookId, double mark){
        int check = -1;
        String query = "";
        
        if (!this.isRated(accountId, bookId)) {
            query = "INSERT INTO rating(mark,accountId,bookId) values (?,?,?)";
        } else {
            query = "UPDATE rating SET mark = ? WHERE accountId = ? and bookId = ?";
        }
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setDouble(1,mark );
            ps.setInt(2, accountId);
            ps.setInt(3, bookId);
            
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check >0;
    }
    public double getMark( int bookId){
        String query = "SELECT SUM(mark) as avg, count(*) as count FROM rating WHERE bookId = ? ";
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                System.out.println(rs.getDouble("avg")/rs.getDouble("count")*100);
                double a = ((double)(Math.round(rs.getDouble("avg")/rs.getDouble("count")*100)))/100;
                return a;
            }
            else return 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}
