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
import entity.History;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "BookWithCategoryServlet", urlPatterns = {"/BookWithCategoryServlet"})
public class BookWithCategoryServlet extends HttpServlet {

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
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            List<Book> listBook = new BookDao().getAllWithCategoryId(categoryId);
            List<Category> lsCategory = new CategoryDao().getAll();
            List<History> lsHistory = new HistoryDao().getBook_DateHistoryWithAccountId(1);
            int bookIdArray [] = new int [lsHistory.size()];
            for(History i: lsHistory){
                bookIdArray[lsHistory.indexOf(i)] = i.getBookId();
            }
            List<Book> lsBookHistory = new BookDao().getAllWithIds(bookIdArray);
//            ls.forEach(System.out::println);
            List<Book> lsBookTopView = new BookDao().getTopView();
            List<Category> filterCategory = new ArrayList();
            filterCategory.add(new CategoryDao().getOne(categoryId));
            request.setAttribute("lsTopView", lsBookTopView);
            request.setAttribute("lsHistory", lsHistory);
            request.setAttribute("data", listBook);
            request.setAttribute("lsCategory", lsCategory);
            request.setAttribute("account", request.getAttribute("account"));
            request.setAttribute("lsBookHistory",lsBookHistory);
            request.setAttribute("filterCategory",filterCategory);
            request.getRequestDispatcher("bookFilter.jsp").forward(request, response);
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
