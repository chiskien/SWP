/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Comment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import jdbc.SQLServerConnection;

/**
 *
 * @author hoang
 */
public class CommentDao {
    public List<Comment> getAllWithBookId(int bookId) {
        String query = "SELECT * FROM comment WHERE bookId = ?";
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            List<Comment> ls = new ArrayList();
            while (rs.next()) {
                
                Comment temp = Comment.builder()
                        .commentId(rs.getInt("commentId"))
                        .bookId(rs.getInt("bookId"))
                        .chapterId(rs.getInt("chapterId"))
                        .account(new AccountDao().getOne(rs.getInt("accountId")))
                        .content(rs.getString("content"))
                        .date(rs.getDate("date"))
                        .build();
                ls.add(temp);
            }
            Collections.sort(ls);
            return ls;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public boolean post(String comment,int accountId, int bookId, int chapterId ){
        String query = "INSERT INTO comment VALUES (?,?,?,?,?)";
        int check = 0;
        try(Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)){
            ps.setInt(1, bookId);
            ps.setInt(2, chapterId);
            ps.setInt(3, accountId);
            ps.setString(4, comment);
            ps.setDate(5, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
            
            check = ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return check > 0;
    }
    
    public boolean remove(int id) {
        String query = "DELETE FROM comment WHERE commentId = ?";
        int check = 0;
        try(Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)){
            ps.setInt(1, id);
            check = ps.executeUpdate();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return check > 0;
    }
}
