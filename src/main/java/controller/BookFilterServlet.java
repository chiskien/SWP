/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BookDao;
import dao.CategoryDao;
import entity.Book;
import entity.Category;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hoang
 */
@WebServlet(name = "BookFilterServlet", urlPatterns = {"/BookFilterServlet"})
public class BookFilterServlet extends HttpServlet {

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
            String filterStatus = request.getParameter("status");
            String arrCategoryId[] = request.getParameterValues("categories");

            List<Category> lsCategory = new CategoryDao().getAll();
            List<Category> filterCategory = new ArrayList();
            if (arrCategoryId != null) {
                int arr[] = new int[arrCategoryId.length];
                for (int i = 0; i < arrCategoryId.length; i++) {
                    arr[i] = Integer.parseInt(arrCategoryId[i]);
                }

                filterCategory = new CategoryDao().getAllWithIds(arr);
            } else {
                filterCategory = new ArrayList();
            }
            String filterSort = request.getParameter("sort");

            

            List<Book> lsBook = new BookDao().getAllWithFilter(filterCategory, filterSort,filterStatus);

            request.setAttribute("sort", filterSort);
            request.setAttribute("data", lsBook);
            request.setAttribute("filterCategory", filterCategory);
            request.setAttribute("lsCategory", lsCategory);

            request.getRequestDispatcher("bookFilter.jsp").forward(request, response);
        } catch (Exception ex) {

            response.sendRedirect("error.jsp");
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
