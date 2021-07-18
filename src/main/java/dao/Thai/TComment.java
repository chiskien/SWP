/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Thai;

import Thai.entity.Report;
import dao.AccountDao;
import entity.Comment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jdbc.SQLServerConnection;

/**
 *
 * @author thaip
 */
public class TComment {
    ResultSet rs ;
     public Comment GetOneByReport(Report rep)
     {
         String query= "SELECT * FROM comment WHERE commentId = ?";
         try(Connection conn = SQLServerConnection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);){
             ps.setInt(1, rep.getCommentId());
             rs = ps.executeQuery();
             while(rs.next())
             {
                 Comment temp = Comment.builder()
                        .commentId(rs.getInt("commentId"))
                        .bookId(rs.getInt("bookId"))
                        .chapterId(rs.getInt("chapterId"))
                        .account(new AccountDao().getOne(rs.getInt("accountId")))
                        .content(rs.getString("content"))
                        .date(rs.getDate("date"))
                        .build();
                 return temp;
             }
         }catch(SQLException ex)
         {
             ex.printStackTrace();
         }
         return null;
     }
}
