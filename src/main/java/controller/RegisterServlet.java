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
import java.util.List;
import java.util.Random;
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
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

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
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String rePassword = request.getParameter("rePassword");
            String name = request.getParameter("name");
            System.out.println(email+password+rePassword+name);

            List<String> lsMail = new AccountDao().getAllEmail();
            if (email.equals("") || password.equals("") || rePassword.equals("") || name.equals("")) {
                request.setAttribute("message", "You must fill all fields");
                request.setAttribute("email", email);
                request.setAttribute("password", password);
                request.setAttribute("rePassword", rePassword);
                request.setAttribute("name", name);
                request.getRequestDispatcher("Register.html").forward(request, response);
            } else if (!password.equals(rePassword)) {
                request.setAttribute("message", "Repassword does not match");
                request.setAttribute("email", email);
                request.setAttribute("password", password);
                request.setAttribute("rePassword", rePassword);
                request.setAttribute("name", name);
                request.getRequestDispatcher("Register.html").forward(request, response);
            } else if (lsMail.contains(email)) {
                request.setAttribute("message", "Email is exsisted");
                request.setAttribute("email", email);
                request.setAttribute("password", password);
                request.setAttribute("rePassword", rePassword);
                request.setAttribute("name", name);
                request.getRequestDispatcher("Register.html").forward(request, response);
            } else {
                String hoang = "123456789abcdefghijklmnopqrstuvwsyz";
                String ran = "";
                for (int i = 0; i < 6; i++) {
                    ran += hoang.charAt(new Random().nextInt(hoang.length()));
                }
                Account temp = Account.builder()
                        .email(email)
                        .password(password)
                        .name(name)
                        .activeCode(ran)
                        .build();
                new AccountDao().register(temp);
                
                String mail = temp.getEmail();
                
                request.setAttribute("toMail", mail);
                request.setAttribute("account", temp);
                request.getRequestDispatcher("AuthenRegisterServlet").forward(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
//            response.sendRedirect("error.jsp");
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
