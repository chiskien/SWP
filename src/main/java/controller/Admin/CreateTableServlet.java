/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Admin;

import dao.BookDao;
import dao.Thai.TAccountDao;
import dao.Thai.TReportDao;
import dao.TranslatorDao;
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
@WebServlet(name = "CreateTableServlet", urlPatterns = {"/TableServlet/*"})
public class CreateTableServlet extends HttpServlet {

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
    BookDao boo = new BookDao();
    TReportDao rep = new TReportDao();
    TranslatorDao trans = new TranslatorDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURI();
        String fileName = url.substring(url.lastIndexOf('/') + 1);
        String finalPage = "/myConnectDB/adminPage.jsp";
        List list = null;
        String type = "";
        switch (fileName) {
            case "Book":
                list = boo.getAll();
                type = "Book";
                break;
            case "Translator":
                list = trans.getAll();
                type = "Translator";
                break;
            case "Account":
                list = acc.getAll();
                type = "Account";
                break;
            case "Report":
                list = rep.getAll();
                type = "Report";
                break;
            default:
                finalPage = "Error.html";
                break;
        }
        //Testing Object
//        PrintWriter out = response.getWriter();
//        for(int i=0;i<list.size();i++)
//        {
//            out.print(list.get(i).toString()+"\n");
//        }
//        request.setAttribute("RequestList", list);
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(2400);
        session.setAttribute("RequestList", list);
        session.setAttribute("type", type);
        response.sendRedirect(finalPage);
//        request.getRequestDispatcher("/adminPage.jsp").forward(request, response);
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
