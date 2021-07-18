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
public class HBookDao {

    public static int getFirstChapterId(int bookId) {
        String query = "select min(chapterid) as min from chapter where bookid = ?";
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("min");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public List<Book> getAllBookmarkedBook(int accountId) {
        String query = "SELECT a.*, c.chapterName, c.chapterId as chapterIds FROM book a, bookmark b, chapter c WHERE b.accountId = ? AND b.bookId = a.id and b.chapterId = c.chapterId ";
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
                        .bookmarkedChapter(rs.getString("chapterName"))
                        .bookmarkedChapterId(rs.getInt("chapterIds"))
                        .build();
                books.add(pro);
            }
            return books;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public List<Integer> getAllHistoryBook(int accountId) {
        String query = "SELECT DISTINCT bookId FROM history WHERE accountId = ?";
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            List<Integer> ids = new ArrayList();
            while (rs.next()) {
                ids.add(rs.getInt("bookId"));
            }
            return ids;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    

}
