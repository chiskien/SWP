/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Product;
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
public class ProductDao implements IMethod<Product> {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public List<Product> getAll() {
        List<Product> ls = new ArrayList();
        String query = "Select * from product";
        try(Connection con = SQLServerConnection.GetConnection();
                PreparedStatement ps = con.prepareStatement(query)){
            rs = ps.executeQuery();
            while(rs.next()){
                Product pro = Product.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .authorId(rs.getInt("authorId"))
                        .translatorId(rs.getInt("translatorId"))
                        .totalChap(rs.getInt("totalChap"))
                        .totalLike(rs.getInt("totalLike"))
                        .totalView(rs.getInt("totalView"))
                        .status(rs.getString("status"))
                        .description(rs.getString("description"))
                        .build();
                ls.add(pro);
            }
            return ls;
        }
        catch(SQLException ex){
            System.out.println("co loi");
        }
        return null;
    }

    @Override
    public Product getOne(int id) {
        String query = "SELECT * FROM product WHERE id = ? ";
        try(Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)){
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if(rs.next()){
                Product pro = Product.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .authorId(rs.getInt("authorId"))
                        .translatorId(rs.getInt("translatorId"))
                        .totalChap(rs.getInt("totalChap"))
                        .totalLike(rs.getInt("totalLike"))
                        .totalView(rs.getInt("totalView"))
                        .status(rs.getString("status"))
                        .description(rs.getString("description"))
                        .build();
                return pro;
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean add(Product obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(int id, Product obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
