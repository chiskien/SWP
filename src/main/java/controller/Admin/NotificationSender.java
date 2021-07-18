/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Admin;

import dao.AccountDao;
import dao.NotificationDao;
import dao.Thai.TAccountDao;
import dao.TranslatorDao;
import entity.Account;
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
@WebServlet(name = "NotificationSender", urlPatterns = {"/NotificationSender"})
public class NotificationSender extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    NotificationDao noDao = new NotificationDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Account> acclist = null;
        List<Translator> translist = null;
        String type = request.getParameter("page");
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(2400);
        session.setAttribute("type", type);
        int acc = Integer.parseInt(request.getParameter("account"));
        int trans = Integer.parseInt(request.getParameter("translator"));
        if(acc == 0 && trans == 0){
            response.sendRedirect("/myConnectDB/adminPage.jsp");
            return;
        }
        if(acc == 100){
            acclist = new TAccountDao().getAll();
        } else if(acc == 0){
        }else{
            Account a = new AccountDao().getOne(acc);
            acclist.add(a);
        }
        if(trans == 100){
            translist = new TranslatorDao().getAll();
        } else if(trans == 0){
        }else{
            Translator t = new TranslatorDao().getOne(trans);
            translist.add(t);
        }
        String content = request.getParameter("content");
        if (acclist != null) {
            for (Account temp : acclist) {
                noDao.addNoti(temp.getAccountId(), content, "user");
            }
        }
        if (translist != null) {
            for (Translator temp : translist) {
                noDao.addNoti(temp.getTranslatorId(), content, "translator");
            }
        }
        response.sendRedirect("/myConnectDB/adminPage.jsp");
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
