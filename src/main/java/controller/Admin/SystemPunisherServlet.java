/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Admin;

import Thai.entity.Report;
import dao.BookDao;
import dao.CommentDao;
import dao.NotificationDao;
import dao.Thai.TAccountDao;
import dao.Thai.TBookDao;
import dao.Thai.TComment;
import dao.Thai.TReportDao;
import dao.Thai.TTranslator;
import dao.TranslatorDao;
import entity.Book;
import entity.Translator;
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
 * @author thaip
 */
@WebServlet(name = "SystemPunisherServlet", urlPatterns = {"/SystemPunisherServlet"})
public class SystemPunisherServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    TAccountDao accDao = new TAccountDao();
    TTranslator transDao = new TTranslator();
    BookDao bookDao = new BookDao();
    TBookDao tbookDao = new TBookDao();
    NotificationDao noDao = new NotificationDao();
    TReportDao reDao = new TReportDao();
    CommentDao cmtDao = new CommentDao();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int aid = Integer.valueOf(request.getParameter("aid"));
        Report temp = reDao.getById(aid);
        switch (temp.getType()) {
            case "comment":
                int accId = new TComment().GetOneByReport(temp).getAccount().getAccountId();
                accDao.punishAccount(accId);
                String commentpenalty = "you have been penalized for breaking the rules of the site in a comment on "
                        + (new TComment().GetOneByReport(temp).getPostTime()).toString();
                noDao.addNoti(accId, commentpenalty, "user");
                cmtDao.remove(temp.getCommentId());
                break;
            case "book":
                Book boo = bookDao.getOne(temp.getBookId());
                tbookDao.removeBook(boo);
                Translator trans = new TranslatorDao().getOneWithBookId(boo.getId());
                transDao.punishTranslator(trans);
                String bookPenalty = "you have been penalized for breaking the rules of the site in the book :"
                        + boo.getName();
                noDao.addNoti(trans.getTranslatorId(), bookPenalty, "translator");
                break;
            default:
                String penaltyNoti = "you have been penalized for breaking the rules of the site based on report from others user";
                break;
        }
//        String type = "Report";
        reDao.changeReportStatrToDone(temp);
//        HttpSession session = request.getSession();
//        session.setMaxInactiveInterval(2400);
//        session.setAttribute("type", type);
        request.getRequestDispatcher("TableServlet/Report").forward(request, response);
        //response.sendRedirect("/myConnectDB/adminPage.jsp");
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
