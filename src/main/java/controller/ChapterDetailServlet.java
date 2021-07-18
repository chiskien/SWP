/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BookDao;
import dao.CategoryDao;
import dao.ChapterDao;
import dao.CommentDao;
import dao.FrameDao;
import dao.HistoryDao;
import entity.Book;
import entity.Chapter;
import entity.Comment;
import entity.Frame;
import entity.History;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
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
@WebServlet(name = "ChapterDetailServlet", urlPatterns = {"/ChapterDetailServlet"})
public class ChapterDetailServlet extends HttpServlet {

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
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            int chapterId = Integer.parseInt(request.getParameter("chapterId"));
            if (request.getSession(false).getAttribute("accountId") != null) {
                int accountId = (int) request.getSession(false).getValue("accountId");

                Calendar date = Calendar.getInstance();
                date.setTimeInMillis(System.currentTimeMillis());
                new HistoryDao().add(History.builder()
                        .accountId(accountId)
                        .bookId(bookId)
                        .chapterId(chapterId)
                        .date(date)
                        .build());
            }
            Chapter chapter = new ChapterDao().getOne(bookId, chapterId);
            List<Frame> lsFrame = new FrameDao().getAll(bookId, chapterId);
            List<Comment> lsComment = new CommentDao().getAllWithBookId(bookId);
            Book book = new BookDao().getOne(bookId);

            request.setAttribute("lsComment", lsComment);
            request.setAttribute("lsFrame", lsFrame);
            request.setAttribute("book", book);
            request.setAttribute("chapter", chapter);
            request.setAttribute("lsCategory", new CategoryDao().getAll());
            request.getRequestDispatcher("chapterDetail.jsp").forward(request, response);
        } catch (Exception ex) {
//            response.sendRedirect("error.jsp");
            ex.printStackTrace(System.out);
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
