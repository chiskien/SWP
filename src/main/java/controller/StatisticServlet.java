package controller;

import dao.BookDao;
import dao.TranslatorDao;
import entity.Book;
import entity.Translator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StatisticServlet", value = "/StatisticServlet")
public class StatisticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int role = Integer.parseInt(request.getParameter("role"));
        if (role == 3) {
            List<Book> lsBookTopView = new BookDao().getTopView();
            List<Book> lsBookFavorite = new BookDao().getMostFollowingBook();
            List<Translator> lsTranslator = new TranslatorDao().getAll();
            request.setAttribute("bookView",lsBookTopView);
            request.setAttribute("bookFav",lsBookFavorite);
            request.setAttribute("translator",lsTranslator);
            request.getRequestDispatcher("adminStatisticPage.jsp").forward(request,response);

        } else {
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
