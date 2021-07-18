/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Frame;
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
public class FrameDao implements IMethod<Frame> {
    public List<Frame> getAll(int bookId, int chapterId){
        String query = "SELECT * FROM frame WHERE bookId = ? AND chapterId = ? ";
        try(Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)){
            ps.setInt(1, bookId);
            ps.setInt(2, chapterId);
            ResultSet rs = ps.executeQuery();
            List<Frame> lsFrame = new ArrayList();
            while(rs.next()){
                Frame temp = Frame.builder()
                        .bookId(bookId)
                        .chapterId(chapterId)
                        .frameId(rs.getInt("frameId"))
                        .imgName(rs.getString("imgName"))
                        .status(rs.getInt("status"))
                        .build();
                lsFrame.add(temp);
            }
            return lsFrame;
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Frame> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Frame getOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(Frame obj) {
        String query = "INSERT INTO frame(bookId, chapterId, imgName, status) VALUES (?,?,?,?)";
        int check = 0;
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setObject(1, obj.getBookId());
            ps.setObject(2, obj.getChapterId());
            ps.setObject(3, obj.getImgName());
            ps.setObject(4, obj.getStatus());
            

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
    public boolean update(int id, Frame obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }

    
}
