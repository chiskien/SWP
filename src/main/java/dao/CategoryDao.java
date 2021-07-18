/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Category;
import entity.Category;
import entity.Category_BookNum;
import entity.Product;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.SQLServerConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author hoang
 */
public class CategoryDao implements IMethod<Category> {

    public List<Category> getAllWithBookId(int id) {
        String query = "SELECT * FROM category a, book_category b"
                + " WHERE b.bookId = ? and a.categoryId = b.categoryId ";
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            List<Category> ls = new ArrayList();
            while (rs.next()) {
                Category temp = Category.builder()
                        .categoryId(rs.getInt(1))
                        .categoryName(rs.getString(2))
                        .build();
                ls.add(temp);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Category> getAll() {
        List<Category> ls = new ArrayList();
        String query = "Select * FROM category ORDER BY categoryName";
        try (Connection con = SQLServerConnection.GetConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category pro = Category.builder()
                        .categoryId(rs.getInt(1))
                        .categoryName(rs.getString(2))
                        .build();
                ls.add(pro);
            }
            return ls;
        } catch (SQLException ex) {
            System.out.println("co loi");
        }
        return null;
    }

    public List<Category> getAllWithIds(int[] ids) {
        List<Category> lsCategory = new ArrayList();
        for (int id : ids) {
            String query = "SELECT * FROM category WHERE categoryId = ?";
            try (Connection cn = SQLServerConnection.GetConnection();
                    PreparedStatement ps = cn.prepareStatement(query)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Category book = Category.builder()
                            .categoryId(rs.getInt("categoryId"))
                            .categoryName(rs.getString("categoryName"))
                            .build();
                    lsCategory.add(book);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return lsCategory;
    }

    @Override
    public Category getOne(int id) {
        String query = "SELECT * FROM category where categoryId = ? ";
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Category pro = Category.builder()
                        .categoryId(rs.getInt(1))
                        .categoryName(rs.getString(2))
                        .build();
                return pro;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean add(Category obj) {
        String query = "INSERT INTO category(categoryName) VALUES (?)";
        int check = 0;
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setObject(1, obj.getCategoryName());

            check = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check > 0;
    }

    public Integer getIdWithName(String name) {
        String query = "SELECT top(1) categoryId FROM category where categoryName = ? ";
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("categoryId");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean addCate_Book(int categoryId, int bookId) {
        int check = 0;
        String query = "INSERT INTO book_category(bookId,categoryId) VALUES (?,?)";
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setObject(1, bookId);
            ps.setObject(2, categoryId);
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check > 0;

    }

    @Override
    public boolean remove(int id) {
        String query = "DELETE FROM category WHERE id = ?";
        int check = 0;
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setInt(1, id);
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public boolean update(int id, Category obj) {
        String query = "UPDATE book SET categoryName = ? WHERE categoryId = ?";

        int check = 0;
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setObject(1, id);
            ps.setObject(2, obj.getCategoryName());

            check = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check > 0;
    }

    public boolean updateBook_Category(int bookId, int[] categoryId) {
        int check = 0;
        try (Connection cn = SQLServerConnection.GetConnection()) {
            PreparedStatement ps = cn.prepareStatement("Delete from book_category where bookId = ?");
            ps.setObject(1, bookId);
            check += ps.executeUpdate();
            
            for(int i = 0;i<categoryId.length;i++){
                ps = cn.prepareStatement("insert into book_category(bookid,categoryid) values(?,?)");
                ps.setObject(1, bookId);
                ps.setObject(2, categoryId[i]);
                check += ps.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check>0;
    }

    public void crawlCategoryList(String pageUrl) throws IOException {
        Document doc = Jsoup.connect(pageUrl).get();
        Elements categories = doc.select(".manga-mega-menu a");
        ArrayList<Category> cate = new ArrayList();
        for (Element e : categories) {
            cate.add(Category.builder()
                    .categoryName(e.html())
                    .build());

        }
        List<Category> lsExistCate = new CategoryDao().getAll();

        for (Category c : cate) {
            int check = 0;
            for (Category ca : lsExistCate) {
                if (ca.getCategoryName().equals(c.getCategoryName())) {
                    check = 1;
                    break;
                }
            }
            if (check == 0) {
                new CategoryDao().add(c);
            }
        }
    }

    public List<Category_BookNum> getAllAndBookNum() {
        List<Category_BookNum> ls = new ArrayList();
        String query = "select count(*) as bookNum , a.categoryid as categoryId, b.categoryName as categoryName from  book_category a, category b\n"
                + "where a.categoryId = b.categoryId\n"
                + "group by a.categoryid,\n"
                + " b.categoryName";
        try (Connection con = SQLServerConnection.GetConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category temp = Category.builder()
                        .categoryName(rs.getString("categoryName"))
                        .categoryId(rs.getInt("categoryId"))
                        .build();
                Category_BookNum pro = Category_BookNum.builder()
                        .category(temp)
                        .bookNum(rs.getInt("bookNum"))
                        .build();
                ls.add(pro);
            }
            return ls;
        } catch (SQLException ex) {
            System.out.println("co loi");
        }
        return null;
    }

    public Integer getId(String categoryName) {
        String query = "select * from category where categoryName = ?";
        try (Connection con = SQLServerConnection.GetConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, categoryName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("categoryId");
            }
        } catch (SQLException ex) {
            System.out.println("co loi");
        }
        return null;
    }

    public ArrayList<String> getAllNameWithBookId(int id) {
        String query = "select categoryName from category a, book_category b where b.bookid = ? and b.categoryId = a.categoryId";
        try (Connection con = SQLServerConnection.GetConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            ArrayList<String> arr = new ArrayList();
            while (rs.next()) {
                arr.add(rs.getString("categoryName"));
            }
            return arr;
        } catch (SQLException ex) {
            System.out.println("co loi");
        }
        return null;
    }
//    public boolean addCate_Book(int bookId, int [] categoryId){
//        
//    }

    public boolean isIn(String c, ArrayList<String> lsBookCategory) {
        for (String cate : lsBookCategory) {
            if (cate.equals(c)) {
                return true;
            }
        }
        return false;
    }

}
