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
public class HCommentDao {
    public static int getTotalComment(int bookId){
        String query = "SELECT count(commentId) as count FROM comment WHERE bookId = ?";
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
                ps.setInt(1, bookId);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    return rs.getInt("count");
            }
                

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}
