/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Notification;
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
public class NotificationDao {
    public boolean add(int receiverId, String content){
        String query = "INSERT INTO Notifications(ReceiverID, Content, Status, ReceiverType) values(?,?,?,?)";
        int check = 0;
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setObject(1, receiverId);
            ps.setObject(2, content);
            ps.setInt(3,1);
            ps.setObject(4, "user");

            check = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check > 0;
    }
    public List<Notification> getAllWithAccount(int accountId){
        String query = "SELECT * FROM notifications WHERE receiverId = ? order by NotificationID desc";
        int check = 0;
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setObject(1, accountId);
            List<Notification> ls = new ArrayList();
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Notification temp = Notification.builder()
                        .content(rs.getString("content"))
                        .status(rs.getInt("status"))
                        .build();
                ls.add(temp);
            }

            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
        
    }
    
    public boolean addNoti(int receiverId, String content, String type){
        String query = "INSERT INTO Notifications(ReceiverID, Content, Status, ReceiverType) values(?,?,?,?)";
        int check = 0;
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setObject(1, receiverId);
            ps.setObject(2, content);
            ps.setInt(3,1);
            ps.setObject(4, type);

            check = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check > 0;
    }
}
