/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Thai;

import Thai.entity.Report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.SQLServerConnection;

/**
 * @author thaip
 */
public class TReportDao {
    ResultSet rs;

    //get all report information
    public List<Report> getAll() {
        List<Report> list = new ArrayList<Report>();
        String sql = "SELECT * FROM report";
        try (Connection conn = SQLServerConnection.GetConnection();
             PreparedStatement ps = conn.prepareCall(sql)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                Report rp = Report.builder()
                        .reportId(rs.getInt("reportId"))
                        .accountId(rs.getInt("accountId"))
                        .bookId(rs.getInt("bookId"))
                        .commentId(rs.getInt("commentId"))
                        .content(rs.getString("content"))
                        .status(rs.getInt("status"))
                        .type(rs.getString("type"))
                        .build();
                list.add(rp);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Report getById(int id) {
        String sql = "SELECT * FROM report WHERE reportId =?";
        try (Connection conn = SQLServerConnection.GetConnection();
             PreparedStatement ps = conn.prepareCall(sql)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return Report.builder()
                        .reportId(rs.getInt("reportId"))
                        .accountId(rs.getInt("accountId"))
                        .bookId(rs.getInt("bookId"))
                        .commentId(rs.getInt("commentId"))
                        .content(rs.getString("content"))
                        .status(rs.getInt("status"))
                        .build();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void changeReportStartToDone(List<Report> list) {
        String sql = "UPDATE report SET status = ? WHERE reportID = ? ";
        try (Connection conn = SQLServerConnection.GetConnection();
             PreparedStatement ps = conn.prepareCall(sql)) {
            for (Report temp : list) {
                ps.setInt(2, temp.getReportId());
                ps.setInt(1, 1);
                ps.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void reportBook(int reportId, int accountId, int bookId, int commentId,
                           String content, int status, String type) {
        String  sql = "INSERT INTO report([reportId],[accountId],[bookId],[commentId]," +
                "[content],[status],[type]) values (?,?,?,?,?,?,?)";
        try (Connection connection = SQLServerConnection.GetConnection()) {
            PreparedStatement ps = connection.prepareCall(sql);
            if (type.equalsIgnoreCase("book")) {

            } else if(type.equalsIgnoreCase("comment")) {

            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

}