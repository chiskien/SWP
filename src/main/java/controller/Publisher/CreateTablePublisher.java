/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Publisher;

import dao.BookDao;
import dao.ChapterDao;
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
 * @author nguye
 */
@WebServlet(name = "CreateTablePublisher", urlPatterns = {"/CreateTablePublisher/*"})
public class CreateTablePublisher extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    BookDao boo = new BookDao();
    ChapterDao chap = new ChapterDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String url = request.getRequestURI();
            String fileName = url.substring(url.lastIndexOf('/') + 1);
            String finalPage = "/myConnectDB/PublisherPage.jsp";
            List list = null;
            String type = "";
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(2400);

            int test = 1;
            switch (fileName) {
                case "Book":
//                    int accountId = (int)session.getAttribute("accountId");
                    int accountId = 3;
                    list = boo.getAllWithTranslator(accountId);
                    type = "Book";
                    break;
                case "Chapter":
                    list = chap.getAllWithBookId(test);
                    type = "Chapter";
                    break;
                default:
                    finalPage = "Error.html";
                    break;
            }
//            Testing Object
            for (int i = 0; i < list.size(); i++) {
                out.print(list.get(i).toString() + "\n");
            }

//            session.setAttribute("RequestList", list);
//            session.setAttribute("type", type);
//            response.sendRedirect(finalPage);
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
