/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDao;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hoang
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
            String mail = request.getParameter("email");
            String pass = request.getParameter("password");
            Account acc = new AccountDao().login(mail, pass);

            if (acc != null) {

                if (acc.getStatus() == 2) {
                    request.setAttribute("message", "Your account has not been activated");
                    request.setAttribute("email", mail);
                    request.setAttribute("password", pass);
                    request.getRequestDispatcher("loginFailed2.jsp").forward(request, response);
                }
                if (acc.getStatus() == 0) {
                    request.setAttribute("message", "Your account has been deactivated");
                    request.setAttribute("email", mail);
                    request.setAttribute("password", pass);
                    request.getRequestDispatcher("loginFailed2.jsp").forward(request, response);
                }
                if (acc.getStatus() == 1) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", acc.getName());
                    session.setAttribute("accountId", acc.getAccountId());
                    session.setAttribute("role",acc.getRole());
                    if (request.getParameter("remember") != null) {
                        Cookie c = new Cookie("email", mail);
                        c.setMaxAge(3600 * 24 * 30);
                        Cookie c2 = new Cookie("password", pass);
                        c2.setMaxAge(3600 * 24 * 30);
                        response.addCookie(c);
                        response.addCookie(c2);
                    }
                    if(acc.getRole()==2){
                        request.getRequestDispatcher("PublisherAddChapter?accountId="+acc.getAccountId()).forward(request, response);
                    } else if(acc.getRole()==3){
                        request.getRequestDispatcher("ChartController").forward(request,response);
                    }
                    response.sendRedirect("BookServlet");
                }
            } else {
                request.setAttribute("email", mail);
                request.setAttribute("message", "Wrong password or email");
                request.getRequestDispatcher("loginFailed2.jsp").forward(request, response);
            }

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
