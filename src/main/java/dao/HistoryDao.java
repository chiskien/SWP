/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Day_View;
import entity.History;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import jdbc.SQLServerConnection;

/**
 *
 * @author hoang
 */
public class HistoryDao implements IMethod<History> {

    public List<History> getAllWithAccountId(int accountId) {
        String query = "SELECT * FROM history WHERE accountId = ?";
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            List<History> lsHistory = new ArrayList();
            while (rs.next()) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(rs.getDate("date"));
                History temp = History.builder()
                        .accountId(accountId)
                        .bookId(rs.getInt("bookId"))
                        .chapterId(rs.getInt("chapterId"))
                        .date(cal)
                        .build();
                lsHistory.add(temp);
            }
            return lsHistory;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<History> getBook_DateHistoryWithAccountId(int accountId) {
        String query = "select a.bookId, a.date,a.historyId from history a, history b\n"
                + "                where  a.bookId = b.bookId and a.accountId = ? and b.accountId = ?\n"
                + "                group by a.bookid, a.date,a.historyid\n"
                + "                having a.date = max(b.date) and a.historyId = max(b.historyId)\n"
                + "                order by a.date desc, a.historyId desc";
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setInt(1, accountId);
            ps.setInt(2, accountId);
            ResultSet rs = ps.executeQuery();
            List<History> lsHistory = new ArrayList();
            while (rs.next()) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(rs.getDate("date"));
                History temp = History.builder()
                        .accountId(accountId)
                        .bookId(rs.getInt("bookId"))
                        .date(cal)
                        .build();
                lsHistory.add(temp);
            }
            return lsHistory;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<History> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public History getOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(History obj) {
        String query = "INSERT INTO history VALUES (?,?,?,?)";
        int check = 0;
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            java.sql.Date date = new java.sql.Date(obj.getDate().getTimeInMillis());
            ps.setInt(1, obj.getAccountId());
            ps.setInt(2, obj.getBookId());
            ps.setInt(3, obj.getChapterId());
            ps.setDate(4, date);
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public boolean remove(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(int id, History obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean delete(int bookId, int accountId) {
        int check = 0;
        String query = "DELETE FROM history WHERE bookId = ? AND accountId = ?";
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setObject(1, bookId);
            ps.setObject(2, accountId);
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check > 0;
    }

    public ArrayList<Day_View> getDay_View() {
        String query = "select date, count(*) views  from history\n"
                + "group by date";
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();
            ArrayList<Day_View> ls = new ArrayList();
            while (rs.next()) {
                Day_View temp = Day_View.builder()
                        .date(rs.getDate("date"))
                        .views(rs.getInt("views"))
                        .build();
                ls.add(temp);
            }
            return ls;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
