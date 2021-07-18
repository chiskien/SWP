/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.Thai.*;
import entity.*;
import Thai.entity.*;

import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thaip
 */
@WebServlet(name = "Search", urlPatterns = {"/AdminSearch"})
public class Search extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    TAccountDao acc = new TAccountDao();
    TBookDao book = new TBookDao();
    TTranslator trans = new TTranslator();
    TReportDao report = new TReportDao();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchType = request.getParameter("searchType");
        String searchValue = request.getParameter("searchValue");
        List list = null;        
        switch(searchType){
            case "Account": {
                list = acc.getAllByName(searchValue);
                break;}
            case "Book":{ 
                list = book.getAllWithName(searchValue);
                break;}
            case "Translator": {
                list = trans.getAllByName(searchValue);
                break;}
            case "Report":{ 
                Report rep = report.getById(Integer.parseInt(searchValue));
                list.add(rep);
                break;}
        }
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(2400);
        session.setAttribute("result", list);
        session.setAttribute("type", searchType+"Search");
        response.sendRedirect("/myConnectDB/adminPage.jsp");
//        request.setAttribute("result", list);
//        request.getRequestDispatcher("").forward(request, response);
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
