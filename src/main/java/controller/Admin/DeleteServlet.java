/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Admin;

import dao.Thai.TAccountDao;
import dao.Thai.TBookDao;
import dao.TranslatorDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thaip
 */
@WebServlet(name = "DeleteServlet", urlPatterns = {"/DeleteServlet/*"})
public class DeleteServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    TAccountDao tacc = new TAccountDao();
    TAccountDao acc = new TAccountDao();
    TBookDao boo = new TBookDao();
    TranslatorDao trans = new TranslatorDao();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURI();
        String fileName = url.substring(url.lastIndexOf('/') + 1);
        String finalPage="/myConnectDB/TableServlet/"+fileName;
        int objectID = Integer.parseInt(request.getParameter("id"));
        int changeState = Integer.parseInt(request.getParameter("changeState"));
        switch(fileName)
        {
            case "Book":           
                boo.updateState(objectID, changeState);
                break;
            case "Translator":
                trans.remove(objectID);
                break;
            case "Account":
                tacc.updateState(objectID, changeState);
                break;
        }
        response.sendRedirect(finalPage);
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
