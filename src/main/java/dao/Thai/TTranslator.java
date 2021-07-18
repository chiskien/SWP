/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Thai;
import dao.TranslatorDao;
import entity.Account;
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
 * @author thaip
 */
public class TTranslator {
    ResultSet rs ;
    public List<Translator> getAllByName(String name)
    {
        List<Translator>list = new ArrayList<Translator>();
        String sql = "SELECT * FROM translator WHERE name like ? ORDER BY name";
        name = "%"+name+"%";
        try(Connection conn = SQLServerConnection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setString(1, name);
            rs= ps.executeQuery();
            while (rs.next()) {
                Translator acc = Translator.builder()
                        .translatorId(rs.getInt("translatorId"))
                        .name(rs.getString("name"))
                        .linkFanpage(rs.getString("linkFanpage"))
                        .donationAccount(rs.getString("donationAccount"))
                        .imgName(rs.getString("imgName"))
                        .status(rs.getBoolean("status"))
                        .build();
                list.add(acc);
            }
            return list;
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
        
    public boolean punishTranslator(Translator trans)
    {
        String query ="UPDATE translator SET "
                + "status =0"
                + "WHERE translatorId =?";
        int check =0;
        try(Connection conn = SQLServerConnection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);){
        ps.setInt(1, trans.getTranslatorId());
        check=ps.executeUpdate();
            
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return check>0;
    }
}
