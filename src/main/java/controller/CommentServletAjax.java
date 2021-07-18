/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;



import com.google.gson.Gson;
import dao.AccountDao;
import dao.CommentDao;
import entity.Comment;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
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
@WebServlet(name = "CommentServletAjax", urlPatterns = {"/CommentServletAjax"})
public class CommentServletAjax extends HttpServlet {

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
        try  {
            String comment = request.getParameter("comment");
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            int chapterId = -1;
            
            if(request.getParameter("chapterId")!=""){
                chapterId = Integer.parseInt(request.getParameter("chapterId"));
            }
            
            HttpSession session = request.getSession(false);
            int accountId = (int)session.getAttribute("accountId");
            new CommentDao().post(comment, accountId, bookId, chapterId);
            Comment com = Comment.builder()
                    .account(new AccountDao().getOne(accountId))
                    .bookId(bookId)
                    .chapterId(chapterId)
                    .content(comment)
                    .date(new java.sql.Date(Calendar.getInstance().getTimeInMillis()))
                    .build();
            PrintWriter pr = response.getWriter();
            pr.print("{\"a\":"+new Gson().toJson(com)+",");
            pr.print("\"b\":{\"postTime\":\""+com.getPostTime()+"\"}}");
            
            
                    
        }catch(Exception ex){
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
