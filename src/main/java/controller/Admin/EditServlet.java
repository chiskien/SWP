/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Admin;

import dao.AccountDao;
import dao.BookDao;
import dao.Thai.TAccountDao;

import dao.TranslatorDao;
import entity.Account;
import entity.Book;
import entity.Translator;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thaip
 */
@WebServlet(name = "EditServlet", urlPatterns = {"/EditServlet/*"})
public class EditServlet extends HttpServlet {

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
    AccountDao acc = new AccountDao();
    BookDao boo = new BookDao();
    TranslatorDao trans = new TranslatorDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURI();
        String fileName = url.substring(url.lastIndexOf('/') + 1);
        String finalPage = "/myConnectDB/TableServlet/" + fileName;
        Object ob = request.getParameter("EditedObject");
        switch (fileName) {
            case "Book":
                int bid = Integer.parseInt(request.getParameter("id"));
                Book b = boo.getOne(bid);
                String bname = request.getParameter("name");
                int aid = Integer.parseInt(request.getParameter("aid"));
                int tid = Integer.parseInt(request.getParameter("tid"));
                int total = Integer.parseInt(request.getParameter("total"));
                int appear = Integer.parseInt(request.getParameter("appear"));
                int view = Integer.parseInt(request.getParameter("view"));
                String bstatus = request.getParameter("status");
                String description = request.getParameter("description");
                String img = request.getParameter("img");
                b.setName(bname);
                b.setAuthorId(aid);
                b.setTranslatorId(tid);
                b.setTotalChap(total);
                b.setAppear(appear);
                b.setTotalView(view);
                b.setStatus(bstatus);
                b.setDescription(description);
                b.setImgName(img);
                boo.update(b.getId(), b);
                break;
            case "Translator":
                int trid = Integer.parseInt(request.getParameter("id"));
                Translator t = trans.getOne(trid);
                String tname = request.getParameter("name");
                String link = request.getParameter("link");
                String donate = request.getParameter("donate");
                String timg = request.getParameter("img");
                Boolean tstatus = Boolean.valueOf(request.getParameter("donate"));
                t.setName(tname);
                t.setLinkFanpage(link);
                t.setDonationAccount(donate);
                t.setImgName(timg);
                t.setStatus(tstatus);
                trans.update(t.getTranslatorId(), t);
                break;
            case "Account":
                int accid = Integer.parseInt(request.getParameter("id"));
                Account a = acc.getOne(accid);
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                int role = Integer.parseInt(request.getParameter("role"));
                int status = Integer.parseInt(request.getParameter("status"));
                a.setName(name);
                a.setEmail(email);
                a.setRole(role);
                a.setStatus(status);
                tacc.updateAccount(a);
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
