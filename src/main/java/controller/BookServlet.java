/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BookDao;
import dao.CategoryDao;
import dao.HistoryDao;
import dao.NotificationDao;
import entity.Book;
import entity.Category;
import entity.History;
import entity.Notification;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "BookServlet", urlPatterns = {"/BookServlet"})
public class BookServlet extends HttpServlet {

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

            List<Book> ls = new BookDao().getAllNewest();

            List<Category> lsCategory = new CategoryDao().getAll();
            HttpSession session = request.getSession(false);
            List<History> lsHistory = new HistoryDao().getBook_DateHistoryWithAccountId(1);
            boolean check = false;
            if (session != null) {
                if (session.getAttribute("user") != null) {
                    lsHistory = new HistoryDao().getBook_DateHistoryWithAccountId((int) session.getAttribute("accountId"));
                }

            }

            int bookIdArray[] = new int[lsHistory.size()];
            for (History i : lsHistory) {
                bookIdArray[lsHistory.indexOf(i)] = i.getBookId();
            }
            List<Book> lsBookHistory = new BookDao().getAllWithIds(bookIdArray);
//            ls.forEach(System.out::println);
            List<Book> lsBookTopView = new BookDao().getTopView();

            int page = 1;

            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
                switch (request.getParameter("action")) {
                    case ("-1"):
                        page -= 1;
                        break;
                    case ("1"):
                        page = 1;
                        break;
                    case ("2"):
                        page = 2;
                        break;
                    case ("3"):
                        page = 3;
                        break;
                    case ("-2"):
                        page += 1;
                        break;
                }
            }
            int bookNum = 12;
            ls = new BookDao().getAllPagnition(page, bookNum);
            List<Notification> lsNotification = new ArrayList();
            if (session != null) {
                if (session.getAttribute("accountId") != null) {
                    lsNotification = new NotificationDao().getAllWithAccount((int) session.getAttribute("accountId"));
                    request.setAttribute("lsNotification", lsNotification);
                }
            }

            request.setAttribute("page", page);
            request.setAttribute("lsTopView", lsBookTopView);
            request.setAttribute("lsHistory", lsHistory);
            request.setAttribute("data", ls);
            request.setAttribute("lsCategory", lsCategory);
            request.setAttribute("lsBookHistory", lsBookHistory);
            request.getRequestDispatcher("index2.jsp").forward(request, response);
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
