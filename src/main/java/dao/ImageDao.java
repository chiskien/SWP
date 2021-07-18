/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Image;
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
public class ImageDao  {
    public List<Image> getAllWithId(int id){
        String query = "SELECT * FROM image where productId = ?";
        try(Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            List<Image> ls = new ArrayList();
            while(rs.next()){
                Image temp = Image.builder()
                        .imgId(rs.getInt(1))
                        .bookId(rs.getInt(2))
                        .imgName(rs.getString(3))
                        .build();
                ls.add(temp);
            }
            return ls;
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
