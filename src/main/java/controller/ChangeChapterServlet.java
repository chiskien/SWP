/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BookDao;
import dao.CategoryDao;
import dao.ChapterDao;
import dao.FrameDao;
import dao.HistoryDao;
import entity.Book;
import entity.Chapter;
import entity.Frame;
import entity.History;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
@WebServlet(name = "ChangeChapterServlet", urlPatterns = {"/ChangeChapterServlet"})
public class ChangeChapterServlet extends HttpServlet {

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
            int status = Integer.parseInt(request.getParameter("status"));
            request.setAttribute("lsCategory", new CategoryDao().getAll());
            Chapter newChapter = null;

            if (status == 1) {
                newChapter = new ChapterDao().getPrevious(bookId, chapterId);
            } else if (status == 0) {
                newChapter = new ChapterDao().getNext(bookId, chapterId);
            }
            if (newChapter == null) {
                List<Frame> lsFrame = new FrameDao().getAll(bookId, chapterId);
                Book book = new BookDao().getOne(bookId);
                Chapter curChap = new ChapterDao().getOne(bookId, chapterId);
                request.setAttribute("lsFrame", lsFrame);
                request.setAttribute("book", book);
                request.setAttribute("chapter", curChap);
                request.getRequestDispatcher("chapterDetail.jsp").forward(request, response);
                System.out.println("hello");
            } else {
                int accountId = 1;
                Calendar date = Calendar.getInstance();
                date.setTimeInMillis(System.currentTimeMillis());
                List<Frame> lsFrame = new FrameDao().getAll(bookId, newChapter.getChapterId());
                Book book = new BookDao().getOne(bookId);
                new HistoryDao().add(History.builder()
                        .accountId(accountId)
                        .bookId(bookId)
                        .chapterId(newChapter.getChapterId())
                        .date(date)
                        .build());
                request.setAttribute("lsFrame", lsFrame);
                request.setAttribute("book", book);
                request.setAttribute("chapter", newChapter);
                request.getRequestDispatcher("chapterDetail.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            response.sendRedirect("error.jsp");
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
