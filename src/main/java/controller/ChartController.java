/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BookDao;
import dao.CategoryDao;
import dao.HistoryDao;
import entity.Book;
import entity.Category;
import entity.Category_BookNum;
import entity.Day_View;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hoang
 */
@WebServlet(name = "ChartController", urlPatterns = {"/ChartController"})
public class ChartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            List<Book> lsBookView = new BookDao().getAll();
            lsBookView.sort(new Comparator<Book>() {
                @Override
                public int compare(Book t, Book t1) {
                    if (t.getTotalView() > t1.getTotalView()) {
                        return -1;
                    } else if (t.getTotalView() == t1.getTotalView()) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            });
            lsBookView.forEach(System.out::println);
            int otherPer = 0;
            int totalPer = 0;
            int lsBookRemoveIndex = lsBookView.size() - 1;
            for (Book temp : lsBookView) {
                totalPer += temp.getTotalView();
            }
            System.out.println(totalPer);
            for (int i = lsBookView.size() - 1; i >= 0; i--) {
                otherPer += lsBookView.get(i).getTotalView();
                System.out.println("#" + (float) otherPer / totalPer);
                if (otherPer > totalPer / 10) {
                    otherPer -= lsBookView.get(i).getTotalView();
                    lsBookRemoveIndex = i + 1;
                    break;
                }
            }
            while (lsBookView.size() > lsBookRemoveIndex) {
                lsBookView.remove(lsBookView.size() - 1);
            }

            System.out.println("@@");
            for (Book temp : lsBookView) {
                if (temp.getName().length() >= 20) {
                    String xname = temp.getName().substring(0, 20);
                    xname += "...";
                    System.out.println(xname);
                    temp.setName(xname);
                }
            }
            lsBookView.forEach(System.out::println);
            lsBookView.add(Book.builder()
                    .name("Others")
                    .totalView(otherPer)
                    .build());
            Book[] bls = lsBookView.toArray(new Book[lsBookView.size()]);

            //category: 
            List<Category_BookNum> lsCategory = new CategoryDao().getAllAndBookNum();
            lsCategory.sort(new Comparator<Category_BookNum>() {
                @Override
                public int compare(Category_BookNum t, Category_BookNum t1) {
                    if (t.getBookNum() > t1.getBookNum()) {
                        return -1;
                    } else if (t.getBookNum() == t1.getBookNum()) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            });
            lsCategory.forEach(System.out::println);
            otherPer = 0;
            totalPer = 0;
            lsBookRemoveIndex = lsCategory.size() - 1;
            for (Category_BookNum temp : lsCategory) {
                totalPer += temp.getBookNum();
            }
            System.out.println(totalPer);
            for (int i = lsCategory.size() - 1; i >= 0; i--) {
                otherPer += lsCategory.get(i).getBookNum();
                System.out.println("#" + (float) otherPer / totalPer);
                if (otherPer > totalPer / 5) {
                    otherPer -= lsCategory.get(i).getBookNum();
                    lsBookRemoveIndex = i + 1;
                    System.out.println(lsBookRemoveIndex+"()");
                    break;
                }
            }
            while (lsCategory.size() > lsBookRemoveIndex) {
                lsCategory.remove(lsCategory.size() - 1);
            }

            
            Category temp = Category.builder()
                    .categoryName("Others")
                    .build();
            lsCategory.add(Category_BookNum.builder()
                    .category(temp)
                    .bookNum(otherPer)
                    .build());
            Category_BookNum[] cbls = lsCategory.toArray(new Category_BookNum[lsCategory.size()]);
            System.out.println("@@");
            lsCategory.forEach(System.out::println);
            
            
            //view_day
            ArrayList<Day_View> lsDayView = new HistoryDao().getDay_View();
            Day_View [] dvls = lsDayView.toArray(new Day_View[lsDayView.size()]);
            
            
            
            request.setAttribute("lsDay_View", dvls);
            request.setAttribute("lsCategory_BookNum", cbls);
            request.setAttribute("lsBook", bls);
            request.getRequestDispatcher("chart.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
