/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Translator;
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
public class TranslatorDao implements IMethod<Translator> {

    @Override
    public List<Translator> getAll() {
        String query = "SELECT * FROM translator";
        List<Translator> ls = new ArrayList();
        
        try(Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Translator tl = Translator.builder()
                        .translatorId(rs.getInt(1))
                        .name(rs.getString(2))
                        .linkFanpage(rs.getString(3))
                        .donationAccount(rs.getString(4))
                        .build();
                ls.add(tl);
            }
            return ls;
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Translator getOne(int id) {
        String query = "SELECT * FROM translator WHERE  translatorId = ?";
        try(Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)){
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Translator tl = Translator.builder()
                        .translatorId(rs.getInt(1))
                        .name(rs.getString(2))
                        .linkFanpage(rs.getString(3))
                        .donationAccount(rs.getString(4))
                        .build();
                return tl;
            }
        }
        catch(SQLException ex ){
            ex.printStackTrace();
        }
        return null;
    }
    public Integer getOne(String name){
        String query = "SELECT * FROM translator WHERE  name = ?";
        try(Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)){
            
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("translatorId");
            }
        }
        catch(SQLException ex ){
            ex.printStackTrace();
        }
        return null;
    }
    
    
    @Override
    public boolean add(Translator obj) {
        String query = "INSERT INTO translator VALUES (?,?,?)";
        int check = 0;
        try(Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)){
            ps.setObject(1,obj.getName() );
            ps.setObject(2,obj.getLinkFanpage() );
            ps.setObject(3,obj.getDonationAccount() );
            check = ps.executeUpdate();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public boolean remove(int id) {
        String query = "DELETE FROM translator WHERE translatorId = ?";
        int check = 0;
        try(Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)){
            ps.setObject(1, id);
            check = ps.executeUpdate();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public boolean update(int id, Translator obj) {
        String query = "UPDATE translator SET "
                + "name = ?, "
                + "linkFanpage = ?, "
                + "donationAccount = ? "
                + "WHERE translatorId = ?";
        int check = 0;
        try(Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)){
            ps.setObject(1,obj.getName() );
            ps.setObject(2,obj.getLinkFanpage() );
            ps.setObject(3,obj.getDonationAccount() );
            ps.setObject(4, id);
            
            check = ps.executeUpdate();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return check > 0;
    }
   public Translator getOneWithBookId(int bookId){
       String query = "select a.* from translator a, book b\n" +
"where a.translatorId = b.translatorId and b.id = ?";
        try(Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)){
            
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Translator tl;
                tl = Translator.builder()
                        .translatorId(rs.getInt("translatorId"))
                        .name(rs.getString("name"))
                        .linkFanpage(rs.getString("linkFanpage"))
                        .donationAccount(rs.getString("donationAccount"))
                        .imgName(rs.getString("imgName"))
                        .build();
                return tl;
            }
        }
        catch(SQLException ex ){
            ex.printStackTrace();
        }
        return null;
   }
}
