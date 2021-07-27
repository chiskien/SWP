/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.hoang_dao;

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
 * @author hoang
 */
public class FollowDao {
    public static int getTotalFollow(int bookId)  {
        String query = "SELECT count(*) as count FROM following WHERE bookId = ?";
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
    public static boolean getFollowOrNot(int bookId, int accountId){
        String query = "SELECT count(*) as count FROM following WHERE bookId = ? and accountId = ?";
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
                ps.setInt(1, bookId);
                ps.setInt(2, accountId);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    return rs.getInt("count")>0;
                }
                return false;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public static boolean addFollow(int bookId, int accountId, int add){
        String query ="";
        if(add > 0){
            query = "INSERT INTO following(bookId,accountId) values (?,?)";
        }
        else{
            query = "DELETE FROM following WHERE bookId = ? AND accountid = ?";
        }
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
                ps.setInt(1, bookId);
                ps.setInt(2,accountId);
                int a = ps.executeUpdate();
                return a>0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public List<Book> getAllUserFollow(int accountId){
        String query = "SELECT a.* FROM book a, following  b WHERE a.Id = b.bookId AND b.accountId = ?";
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
                ps.setInt(1, accountId);
                ResultSet rs = ps.executeQuery();
                List<Book> books = new ArrayList();
            while (rs.next()) {
                Book pro = Book.builder()
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
                books.add(pro);
            }
            return books;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
