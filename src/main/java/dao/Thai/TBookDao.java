/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Thai;

import dao.BookDao;
import entity.Book;
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
public class TBookDao {
    
    public  List<Book> getAllWithName(String name){
        String sql ="SELECT * FROM book WHERE name LIKE ? ORDER BY name";
        name = "%"+name+"%";
        try( Connection conn = SQLServerConnection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(sql);){
           ps.setString(1, name);
           ResultSet rs=ps.executeQuery();
           List<Book>list = new ArrayList<Book>();
           while(rs.next()){
               Book bk = Book.builder()
                       .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .authorId(rs.getInt("authorId"))
                        .translatorId(rs.getInt("translatorId"))
                        .totalChap(rs.getInt("totalChap"))
                        .appear(rs.getInt("appear"))
                        .totalView(rs.getInt("totalView"))
                        .status(rs.getString("status"))
                        .description(rs.getString("description"))
                        .imgName(rs.getString("imgName"))
                        .latestUpdate(new BookDao().getLatestUpdateWithBookId(rs.getInt("id")))
                        .build();
               list.add(bk);
           }
           return list;
        }catch(SQLException ex)
            {
              ex.printStackTrace();
            }
        return null;
    }
    
     public boolean updateState(int id, int state) {
        String query = "UPDATE book SET "
                + "appear = ?"                
                + "WHERE id = ?";
        int check = 0;
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setObject(1, state);
            ps.setObject(2, id);

            check = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check > 0;
    }
   
     public boolean removeBook(Book book)
     {
         String query = "UPDATE book SET"
                 + "appear = 0"
                 + "WHERE id = ?";
         int check =0;
         try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setInt(1, book.getId());
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check > 0;
     }
}
