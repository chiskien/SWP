/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDao;
import dao.BookDao;
import dao.ChapterDao;
import dao.FrameDao;
import dao.NotificationDao;
import entity.Account;
import entity.Book;
import entity.Chapter;
import entity.Frame;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hoang
 */
@WebServlet(name = "AddChapterServlet", urlPatterns = {"/AddChapterServlet"})
public class AddChapterServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            String chapterName = request.getParameter("chapterName");
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            Book book = new BookDao().getOne(bookId);
            Chapter newChapter = Chapter.builder()
                    .bookId(bookId)
                    .chapterName(chapterName)
                    .status(1)
                    .build();
            
            new ChapterDao().add(newChapter);
            
            File f = new File(getServletContext().getRealPath("/") + "asset\\img\\book\\"+ book.getName() + "\\" + chapterName);
            for(int i = 0; i < f.list().length; i++){
                String name =chapterName+"/"+ f.list()[i];
                Frame frame = Frame.builder()
                        .bookId(bookId)
                        .chapterId(new ChapterDao().getIdWithName(chapterName, bookId))
                        .imgName(name)
                        .status(1).build();
                new FrameDao().add(frame);
            }
            HttpSession session = request.getSession();
            int accountId = (int)session.getAttribute("accountId");
            
            request.setAttribute("message", "Add success");
            List<Account> accounts = new AccountDao().getAllFollowNotification(bookId);
            for(Account acc :accounts){
                new NotificationDao().add(acc.getAccountId(),book.getName()+" just had a new chapter! Check it out");
                System.out.println(acc.getAccountId()+"$$$$$");
            }
            request.getRequestDispatcher("PublisherAddChapter?accountId="+accountId).forward(request, response);
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
