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
public class HBookMarkDao {

    public static boolean isBookMarked(int bookId, int accountId) {
        String query = "SELECT count(*) as count FROM bookmark WHERE bookId = ? and accountId = ?";
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setInt(1, bookId);
            ps.setInt(2, accountId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("count") > 0;
            }
            return false;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean addBookMark(int bookId, int chapterId, int accountId, int add) {
        String query = "";
        if (add > 0) {
            query = "INSERT INTO bookmark(bookId,chapterid,accountId) values (?,?,?)";
        } else {
            query = "DELETE FROM bookmark WHERE bookId = ? AND accountid = ?";
        }
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            if (add > 0) {
                ps.setInt(1, bookId);
                if (chapterId < 0) {
                    ps.setInt(2, HBookDao.getFirstChapterId(bookId));
                } else {
                    ps.setInt(2, chapterId);
                }
                ps.setInt(3, accountId);
            } else {
                ps.setInt(1, bookId);
                ps.setInt(2, accountId);
            }
            int a = ps.executeUpdate();
            return a > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean addBookMark2(int bookId, int chapterId, int accountId) {
        String query = "";
        query = "INSERT INTO bookmark(bookId,chapterid,accountId) values (?,?,?)";

        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setInt(1, bookId);
            ps.setInt(2, chapterId);
            ps.setInt(3, accountId);
            removeOldBookMark(bookId, accountId);
            int a = ps.executeUpdate();
            return a > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private static boolean removeOldBookMark(int bookId, int accountId) {
        String query = "DELETE FROM bookmark WHERE bookId = ? and accountId = ?";
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setInt(1, bookId);
            ps.setInt(2, accountId);
            int a = ps.executeUpdate();
            return a > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
}
