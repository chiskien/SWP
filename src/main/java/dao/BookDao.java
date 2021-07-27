/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.hoang_dao.FollowDao;
import dao.hoang_dao.HCommentDao;
import entity.Book;
import entity.Category;
import entity.Chapter;
import entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import jdbc.SQLServerConnection;

/**
 * @author hoang
 */
public class BookDao implements IMethod<Book> {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public ArrayList<String> getAllName() {
        ArrayList<String> ls = new ArrayList();
        String query = "Select name from book";
        try (Connection con = SQLServerConnection.GetConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                ls.add(rs.getString("name"));
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Integer getRate(int bookId) {

        String query = "Select avg(mark) as mark from rating where bookId = ?";
        try (Connection con = SQLServerConnection.GetConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, bookId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("mark");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getAllAuthor() {
        ArrayList<String> ls = new ArrayList();
        String query = "Select name from book";
        try (Connection con = SQLServerConnection.GetConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                ls.add(rs.getString("name"));
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Book> getAllWithTranslator(int translatorId) {
        List<Book> ls = new ArrayList();
        String query = "Select * from book where translatorId = ?";
        try (Connection con = SQLServerConnection.GetConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, translatorId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book pro = Book.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .build();
                ls.add(pro);
            }
            return ls;
        } catch (SQLException ex) {
            System.out.println("co loi");
        }
        return null;
    }

    @Override
    public List<Book> getAll() {
        List<Book> ls = new ArrayList();
        String query = "Select * from book";
        try (Connection con = SQLServerConnection.GetConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                Book pro = Book.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .authorId(rs.getInt("authorId"))
                        .translatorId(rs.getInt("translatorId"))
                        .totalChap(rs.getInt("totalChap"))
                        .appear(rs.getInt("appear"))
                        .totalView(rs.getInt("totalView"))
                        .status(rs.getString("status"))
                        .description(rs.getString("description"))
                        .imgName(rs.getString("imgName"))
                        .latestUpdate(new BookDao().getLatestUpdateWithBookId(rs.getInt("id")))
                        .build();
                ls.add(pro);
            }
            return ls;
        } catch (SQLException ex) {
            System.out.println("co loi");
        }
        return null;
    }

    public List<Book> getAllWithCategoryId(int id) {
        String query = "SELECT * FROM book a, book_category b "
                + "WHERE a.id = b.bookId and b.categoryId = ?   ";
        try (Connection cn = SQLServerConnection.GetConnection();
             PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            List<Book> ls = new ArrayList();
            while (rs.next()) {
                Book book = Book.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .authorId(rs.getInt("authorId"))
                        .translatorId(rs.getInt("translatorId"))
                        .totalChap(rs.getInt("totalChap"))
                        .appear(rs.getInt("appear"))
                        .totalView(rs.getInt("totalView"))
                        .status(rs.getString("status"))
                        .description(rs.getString("description"))
                        .imgName(rs.getString("imgName"))
                        .latestUpdate(new BookDao().getLatestUpdateWithBookId(rs.getInt("id")))
                        .build();
                ls.add(book);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Book getOne(int id) {
        String query = "SELECT * FROM book WHERE id = ? ";
        try (Connection cn = SQLServerConnection.GetConnection();
             PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Book pro = Book.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .authorId(rs.getInt("authorId"))
                        .translatorId(rs.getInt("translatorId"))
                        .totalChap(rs.getInt("totalChap"))
                        .appear(rs.getInt("appear"))
                        .totalView(rs.getInt("totalView"))
                        .status(rs.getString("status"))
                        .description(rs.getString("description"))
                        .imgName(rs.getString("imgName"))
                        .latestUpdate(new BookDao().getLatestUpdateWithBookId(rs.getInt("id")))
                        .build();
                return pro;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Book getOneWithName(String name) {
        String query = "SELECT * FROM book WHERE name = ? ";
        try (Connection cn = SQLServerConnection.GetConnection();
             PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setString(1, name);
            rs = ps.executeQuery();

            if (rs.next()) {
                Book pro = Book.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .authorId(rs.getInt("authorId"))
                        .translatorId(rs.getInt("translatorId"))
                        .totalChap(rs.getInt("totalChap"))
                        .appear(rs.getInt("appear"))
                        .totalView(rs.getInt("totalView"))
                        .status(rs.getString("status"))
                        .description(rs.getString("description"))
                        .imgName(rs.getString("imgName"))
                        .build();
                return pro;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean add(Book obj) {
        String query = "INSERT INTO book(name,authorId,translatorId,status,description,imgName) VALUES (?,?,?,?,?,?)";
        int check = 0;
        try (Connection cn = SQLServerConnection.GetConnection();
             PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setObject(1, obj.getName());
            ps.setObject(2, obj.getAuthorId());
            ps.setObject(3, obj.getTranslatorId());
            ps.setObject(4, obj.getStatus());
            ps.setObject(5, obj.getDescription());
            ps.setObject(6, obj.getImgName());

            check = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean remove(int id) {
        String query = "DELETE FROM book WHERE id = ?";
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

    public boolean update(int id, Book obj) {
        String query = "UPDATE book SET "
                + "name = ?, "
                + "authorId = ?, "
                + "translatorId = ?, "
                + "status = ?,"
                + "description = ?, "
                + "imgName = ? "
                + "WHERE id = ?";
        int check = 0;
        try (Connection cn = SQLServerConnection.GetConnection();
             PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setObject(1, obj.getName());
            ps.setObject(2, obj.getAuthorId());
            ps.setObject(3, obj.getTranslatorId());
            ps.setObject(4, obj.getStatus());
            ps.setObject(5, obj.getDescription());
            ps.setObject(6, obj.getImgName());
            ps.setObject(7, id);

            check = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check > 0;
    }

    public boolean update2(int id, String name, int translatorId, String description, String imgName) {
        String query = "UPDATE book SET "
                + "name = ?, "
                + "translatorId = ?, "
                + "description = ?, "
                + "imgName = ? "
                + "WHERE id = ?";
        int check = 0;
        try (Connection cn = SQLServerConnection.GetConnection();
             PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setObject(1, name);
            ps.setObject(2, translatorId);
            ps.setObject(3, description);
            ps.setObject(4, imgName);
            ps.setObject(5, id);


            check = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return check > 0;
    }

    public List<Book> getAllWithIds(int[] ids) {
        List<Book> lsBook = new ArrayList();
        for (int id : ids) {
            String query = "SELECT * FROM book WHERE id = ?";
            try (Connection cn = SQLServerConnection.GetConnection();
                 PreparedStatement ps = cn.prepareStatement(query)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Book book = Book.builder()
                            .id(rs.getInt("id"))
                            .name(rs.getString("name"))
                            .authorId(rs.getInt("authorId"))
                            .translatorId(rs.getInt("translatorId"))
                            .totalChap(rs.getInt("totalChap"))
                            .appear(rs.getInt("appear"))
                            .totalView(rs.getInt("totalView"))
                            .status(rs.getString("status"))
                            .description(rs.getString("description"))
                            .imgName(rs.getString("imgName"))
                            .latestUpdate(new BookDao().getLatestUpdateWithBookId(rs.getInt("id")))
                            .build();
                    lsBook.add(book);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return lsBook;
    }

    public List<Book> getTopView() {
        String query = "SELECT TOP 12 * FROM book\n"
                + "ORDER BY totalView desc";
        try (Connection cn = SQLServerConnection.GetConnection();
             PreparedStatement ps = cn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            List<Book> ls = new ArrayList();
            while (rs.next()) {
                Book pro = Book.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .authorId(rs.getInt("authorId"))
                        .translatorId(rs.getInt("translatorId"))
                        .totalChap(rs.getInt("totalChap"))
                        .appear(rs.getInt("appear"))
                        .totalView(rs.getInt("totalView"))
                        .status(rs.getString("status"))
                        .description(rs.getString("description"))
                        .imgName(rs.getString("imgName"))
                        .latestUpdate(new BookDao().getLatestUpdateWithBookId(rs.getInt("id")))
                        .totalFollow(FollowDao.getTotalFollow(rs.getInt("id")))
                        .totalComment(HCommentDao.getTotalComment(rs.getInt("id")))
                        .build();
                ls.add(pro);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Book> getAllWithFilter(List<Category> filterCategory, String filterSort, String filterStatus) {
        String query = "";
        String queryHead = "";
        String subQueryCategory = "AND a.bookId in (SELECT bookId FROM book_category WHERE categoryId = ?) ";
        String subQuerySort = "";
        String subQuery = "";
        if (filterSort != null) {
            switch (filterSort) {
                case ("A-Z"):
                    queryHead = "SELECT b.* FROM book_category a, book b WHERE a.bookId = b.id AND categoryId = ? ";
                    subQuerySort = " ORDER BY name asc ";
                    break;
                case ("Z-A"):
                    queryHead = "SELECT b.* FROM book_category a, book b WHERE a.bookId = b.id AND categoryId = ? ";
                    subQuerySort = " ORDER BY name desc ";
                    break;
                case ("Newest"):
                    queryHead = "SELECT b.*,max(c.dateOfPublic) max FROM book_category a, book b, chapter c WHERE c.bookId = a.bookId and a.bookId = b.id AND categoryId = ? ";
                    subQuerySort = "group by b.authorId,b.description,b.id,b.imgName,b.name,b.status,b.totalChap,b.appear,b.totalView,b.translatorId\n"
                            + " order by max desc, b.id desc";
                    break;
                case ("Oldest"):
                    queryHead = "SELECT b.*,max(c.dateOfPublic) max FROM book_category a, book b, chapter c WHERE c.bookId = a.bookId and a.bookId = b.id AND categoryId = ? ";
                    subQuerySort = "group by b.authorId,b.description,b.id,b.imgName,b.name,b.status,b.totalChap,b.appear,b.totalView,b.translatorId\n"
                            + " order by max asc,b.id asc ";
                    break;
                case ("Most View"):
                    queryHead = "SELECT b.* FROM book_category a, book b WHERE a.bookId = b.id AND categoryId = ? ";
                    subQuerySort = " ORDER BY totalView desc ";
                    break;
            }
        }
        if (filterCategory.size() > 0) {
            for (int i = 0; i < filterCategory.size(); i++) {
                if (i == 0) {
                    query += queryHead;
                } else {
                    query += subQueryCategory;
                }
            }
        } else {
            query = "select * from book ";
        }

        query += subQuerySort;
        try (Connection cn = SQLServerConnection.GetConnection();
             PreparedStatement ps = cn.prepareStatement(query)) {
            int count = 1;
            for (int i = 0; i < filterCategory.size(); i++) {
                System.out.println(filterCategory.get(i).getCategoryId());
                ps.setInt(i + 1, filterCategory.get(i).getCategoryId());
                count++;
            }
            System.out.println(query);
            ResultSet rs = ps.executeQuery();
            List<Book> lsBook = new ArrayList();

            while (rs.next()) {
                Book pro = Book.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .authorId(rs.getInt("authorId"))
                        .translatorId(rs.getInt("translatorId"))
                        .totalChap(rs.getInt("totalChap"))
                        .appear(rs.getInt("appear"))
                        .totalView(rs.getInt("totalView"))
                        .status(rs.getString("status"))
                        .description(rs.getString("description"))
                        .imgName(rs.getString("imgName"))
                        .latestUpdate(new BookDao().getLatestUpdateWithBookId(rs.getInt("id")))
                        .build();
                lsBook.add(pro);
            }
            return lsBook;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getLatestUpdateWithBookId(int id) {
        Chapter latestChapter = new ChapterDao().getLatestChapterWithBookId(id);
        Calendar latestChapterDate = Calendar.getInstance();
        if (latestChapter != null) {
            latestChapterDate = latestChapter.getDateOfPublic();
        }
        Calendar cur = Calendar.getInstance();
        long dif = (long) ((cur.getTimeInMillis() - latestChapterDate.getTimeInMillis()) / 999.999);

        int year = (int) (dif / 60 / 60 / 24 / 365);
        if (year > 0) {
            if (year > 1) {
                return year + " years";
            } else {
                return "1 year";
            }
        }
        int month = (int) (dif / 60 / 60 / 24 / 30);
        if (month > 0) {
            if (month > 1) {
                return month + " months";
            } else {
                return "1 month";
            }
        }
        int day = (int) (dif / 60 / 60 / 24);
        if (day > 0) {
            if (day > 1) {
                return day + " days";
            } else {
                return "1 day";
            }
        }
        return "New!!";

    }

    public ArrayList<Book> getAllWithName(String name) {
        String query = "SELECT * FROM book "
                + "WHERE name LIKE ?";
        try (Connection cn = SQLServerConnection.GetConnection();
             PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setObject(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            ArrayList<Book> arr = new ArrayList();
            while (rs.next()) {
                Book temp = Book.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .authorId(rs.getInt("authorId"))
                        .translatorId(rs.getInt("translatorId"))
                        .totalChap(rs.getInt("totalChap"))
                        .appear(rs.getInt("appear"))
                        .totalView(rs.getInt("totalView"))
                        .status(rs.getString("status"))
                        .description(rs.getString("description"))
                        .imgName(rs.getString("imgName"))
                        .latestUpdate(new BookDao().getLatestUpdateWithBookId(rs.getInt("id")))
                        .build();
                arr.add(temp);

            }
            return arr;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Book> getAllWithTranslatorId(int id) {
        String query = "SELECT * FROM book  "
                + "WHERE translatorId = ?";
        try (Connection cn = SQLServerConnection.GetConnection();
             PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            List<Book> ls = new ArrayList();
            while (rs.next()) {
                Book book = Book.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .authorId(rs.getInt("authorId"))
                        .translatorId(rs.getInt("translatorId"))
                        .totalChap(rs.getInt("totalChap"))
                        .appear(rs.getInt("appear"))
                        .totalView(rs.getInt("totalView"))
                        .status(rs.getString("status"))
                        .description(rs.getString("description"))
                        .imgName(rs.getString("imgName"))
                        .latestUpdate(new BookDao().getLatestUpdateWithBookId(rs.getInt("id")))
                        .build();
                ls.add(book);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Integer getIdWithName(String name) {
        String query = "SELECT top(1) id from book WHERE name = ?";
        try (Connection cn = SQLServerConnection.GetConnection();
             PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setObject(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<Book> getAllNewest() {
        String query = "SELECT b.*,max(c.dateOfPublic) max FROM book_category a, book b, chapter c WHERE c.bookId = a.bookId and a.bookId = b.id \n"
                + "group by b.authorId,b.description,b.id,b.imgName,b.name,b.status,b.totalChap,b.appear,b.totalView,b.translatorId\n"
                + "order by max desc,b.id desc";
        ArrayList<Book> ls = new ArrayList();
        try (Connection con = SQLServerConnection.GetConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                Book pro = Book.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .authorId(rs.getInt("authorId"))
                        .translatorId(rs.getInt("translatorId"))
                        .totalChap(rs.getInt("totalChap"))
                        .appear(rs.getInt("appear"))
                        .totalView(rs.getInt("totalView"))
                        .status(rs.getString("status"))
                        .description(rs.getString("description"))
                        .imgName(rs.getString("imgName"))
                        .latestUpdate(new BookDao().getLatestUpdateWithBookId(rs.getInt("id")))
                        .build();
                ls.add(pro);
            }
            return ls;
        } catch (SQLException ex) {
            System.out.println("co loi");
        }
        return null;
    }

    public ArrayList<Book> getAllPagnition(int pageNum, int bookNum) {

        String query = "SELECT b.*,max(c.dateOfPublic) max FROM book_category a, book b, chapter c WHERE c.bookId = a.bookId and a.bookId = b.id \n"
                + "group by b.authorId,b.description,b.id,b.imgName,b.name,b.status,b.totalChap,b.appear,b.totalView,b.translatorId\n"
                + "order by max desc,b.id desc OFFSET ? ROWS FETCH NEXT ? ROW ONLY";
        try (Connection cn = SQLServerConnection.GetConnection();
             PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setObject(1, (pageNum - 1) * bookNum);
            ps.setObject(2, bookNum);
            ResultSet rs = ps.executeQuery();
            ArrayList<Book> ls = new ArrayList();
            while (rs.next()) {
                Book pro = Book.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .authorId(rs.getInt("authorId"))
                        .translatorId(rs.getInt("translatorId"))
                        .totalChap(rs.getInt("totalChap"))
                        .appear(rs.getInt("appear"))
                        .totalView(rs.getInt("totalView"))
                        .status(rs.getString("status"))
                        .description(rs.getString("description"))
                        .imgName(rs.getString("imgName"))
                        .latestUpdate(new BookDao().getLatestUpdateWithBookId(rs.getInt("id")))
                        .build();
                ls.add(pro);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<Book> getAllWithManagementFilter(List<Category> filterCategory, String filterSort, int filterAppear) {
        String query = "";
        String queryHead = "";
        String subQueryCategory = "AND a.bookId in (SELECT bookId FROM book_category WHERE categoryId = ?) ";
        String subQuerySort = "";
        String subQuery = "";
        if (filterSort != null) {
            switch (filterSort) {
                case ("A-Z"):
                    queryHead = "SELECT b.* FROM book_category a, book b WHERE a.bookId = b.id AND categoryId = ? ";
                    subQuerySort = " ORDER BY name asc ";
                    break;
                case ("Z-A"):
                    queryHead = "SELECT b.* FROM book_category a, book b WHERE a.bookId = b.id AND categoryId = ? ";
                    subQuerySort = " ORDER BY name desc ";
                    break;
                case ("Newest"):
                    queryHead = "SELECT b.*,max(c.dateOfPublic) max FROM book_category a, book b, chapter c WHERE c.bookId = a.bookId and a.bookId = b.id AND categoryId = ? ";
                    subQuerySort = "group by b.authorId,b.description,b.id,b.imgName,b.name,b.status,b.totalChap,b.appear,b.totalView,b.translatorId\n"
                            + " order by max desc, b.id desc";
                    break;
                case ("Oldest"):
                    queryHead = "SELECT b.*,max(c.dateOfPublic) max FROM book_category a, book b, chapter c WHERE c.bookId = a.bookId and a.bookId = b.id AND categoryId = ? ";
                    subQuerySort = "group by b.authorId,b.description,b.id,b.imgName,b.name,b.status,b.totalChap,b.appear,b.totalView,b.translatorId\n"
                            + " order by max asc,b.id asc ";
                    break;
                case ("Most View"):
                    queryHead = "SELECT b.* FROM book_category a, book b WHERE a.bookId = b.id AND categoryId = ? ";
                    subQuerySort = " ORDER BY totalView desc ";
                    break;
            }
        }
        if (filterCategory.size() > 0) {
            for (int i = 0; i < filterCategory.size(); i++) {
                if (i == 0) {
                    query += queryHead;
                } else {
                    query += subQueryCategory;
                }
            }
        } else {
            query = "select * from book ";
        }

        query += subQuerySort;
        try (Connection cn = SQLServerConnection.GetConnection();
             PreparedStatement ps = cn.prepareStatement(query)) {
            int count = 1;
            for (int i = 0; i < filterCategory.size(); i++) {
                ps.setInt(i + 1, filterCategory.get(i).getCategoryId());
                count++;
            }
            System.out.println(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Book> lsBook = new ArrayList();

            while (rs.next()) {
                Book pro = Book.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .authorId(rs.getInt("authorId"))
                        .translatorId(rs.getInt("translatorId"))
                        .totalChap(rs.getInt("totalChap"))
                        .appear(rs.getInt("appear"))
                        .totalView(rs.getInt("totalView"))
                        .status(rs.getString("status"))
                        .description(rs.getString("description"))
                        .imgName(rs.getString("imgName"))
                        .latestUpdate(new BookDao().getLatestUpdateWithBookId(rs.getInt("id")))
                        .build();
                lsBook.add(pro);
            }
            return lsBook;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean changeBookAppear(int id) {
        String query = "UPDATE book SET appear = ? WHERE id = ?";
        int check = 0;
        try (Connection cn = SQLServerConnection.GetConnection();
             PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setObject(2, id);
            Book temp = new BookDao().getOne(id);
            if (temp.getAppear() == 0) {
                ps.setObject(1, 1);
            } else if (temp.getAppear() == 1) {
                ps.setObject(1, 0);
            }
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check > 0;
    }

    public List<Book> getMostFollowingBook() {
        String query = "SELECT bookId,b.name,count(*) as NumberOfFollowers from following join book b on b.id = following.bookId " +
                "group by bookId,b.name order by NumberOfFollowers desc ";
        List<Book> list = new ArrayList<>();
        try (Connection cn = SQLServerConnection.GetConnection()) {
            assert cn != null;
            PreparedStatement ps = cn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Book b = Book.builder().
                        id(rs.getInt(1)).
                        name(rs.getString(2)).
                        totalFollow(rs.getInt(3)).
                        build();
                list.add(b);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
//    public static void main(String[] args) {
//        BookDao dao = new BookDao();
//        List<Book> list = dao.getMostFollowingBook();
//        for (Book b : list) {
//            System.out.println(b.getId()+" - " + b.getName()+" - "+b.getTotalFollow());
//        }
//    }
}

