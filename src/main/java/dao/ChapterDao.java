/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Chapter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import jdbc.SQLServerConnection;

/**
 *
 * @author hoang
 */
public class ChapterDao implements IMethod<Chapter> {

    public int getView(int bookId, int chapterId) {
        String query = "SELECT count(*) as count FROM history WHERE bookId = ? and chapterId = ?";
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setInt(1, bookId);
            ps.setInt(2, chapterId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("count");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public List<Chapter> getAllWithBookId(int bookId) {
        String query = "SELECT * FROM chapter WHERE bookId = ?";
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            List<Chapter> ls = new ArrayList();
            while (rs.next()) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(rs.getDate("dateOfPUblic"));
                Chapter temp = Chapter.builder()
                        .bookId(rs.getInt("bookId"))
                        .chapterId(rs.getInt("chapterId"))
                        .chapterName(rs.getString("chapterName"))
                        .dateOfPublic(cal)
                        .status(rs.getInt("status"))
                        .view(this.getView(bookId, rs.getInt("chapterId")))
                        .build();
                ls.add(temp);
            }
            Collections.sort(ls, new Comparator<Chapter>() {
                @Override
                public int compare(Chapter t, Chapter t1) {
                    Double a = Double.parseDouble(t.getChapterName().split("[ :]")[1]);

                    Double b = Double.parseDouble(t1.getChapterName().split("[ :]")[1]);
                    return -b.compareTo(a);
                }

            });
            return ls;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Chapter getPrevious(int bookId, int chapterId) {
        List<Chapter> lsChapter = getAllWithBookId(bookId);
        int current = 0;
        for (Chapter a : lsChapter) {
            if (a.getChapterId() == chapterId) {
                current = lsChapter.indexOf(a);
                break;
            }
        }
        if (current == 0) {
            return null;
        } else {
            return lsChapter.get(current - 1);
        }
    }

    public Chapter getNext(int bookId, int chapterId) {
        List<Chapter> lsChapter = getAllWithBookId(bookId);
        int current = lsChapter.size() - 1;
        for (Chapter a : lsChapter) {
            if (a.getChapterId() == chapterId) {
                current = lsChapter.indexOf(a);
                break;
            }
        }
        if (current == lsChapter.size() - 1) {
            return null;
        } else {
            return lsChapter.get(current + 1);
        }
    }

    public Chapter getOne(int bookId, int chapterId) {
        String query = "SELECT * FROM chapter WHERE bookId = ? AND chapterId = ?";
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setInt(1, bookId);
            ps.setInt(2, chapterId);
            ResultSet rs = ps.executeQuery();
            Chapter temp;
            if (rs.next()) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(rs.getDate("dateOfPUblic"));
                temp = Chapter.builder()
                        .bookId(rs.getInt("bookId"))
                        .chapterId(rs.getInt("chapterId"))
                        .chapterName(rs.getString("chapterName"))
                        .dateOfPublic(cal)
                        .status(rs.getInt("status"))
                        .build();
                return temp;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public Chapter getLatestChapterWithBookId(int bookId) {
        String query = "select top 1 * from chapter "
                + "where bookid = ? "
                + "order by dateOfPublic desc";
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(rs.getDate("dateOfPUblic"));
                Chapter temp = Chapter.builder()
                        .bookId(rs.getInt("bookId"))
                        .chapterId(rs.getInt("chapterId"))
                        .chapterName(rs.getString("chapterName"))
                        .dateOfPublic(cal)
                        .status(rs.getInt("status"))
                        .build();
                System.out.println(temp);
                return temp;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public Integer getIdWithName(String name, int bookId) {
        String query = "SELECT top(1) chapterId from chapter WHERE chapterName = ? and bookId =?";
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setObject(1, name);
            ps.setObject(2, bookId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("chapterId");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Chapter> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Chapter getOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(Chapter obj) {
        String query = "INSERT INTO chapter(bookId, chapterName, dateOfPublic, status) VALUES (?,?,?,?)";
        int check = 0;
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setObject(1, obj.getBookId());
            ps.setObject(2, obj.getChapterName());
            ps.setObject(3, new Date(Calendar.getInstance().getTimeInMillis()));
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
    public boolean update(int id, Chapter obj) {
        String query = "UPDATE chapter SET chapterName = ?, status = ? WHERE bookId = ? and chapterId = ?";
        int check = 0;
        try (Connection cn = SQLServerConnection.GetConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setObject(1, obj.getChapterName());
            ps.setInt(2, obj.getStatus());
            ps.setInt(3, obj.getBookId());
            ps.setInt(4, id);
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check > 0;
    }

}
