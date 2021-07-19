package controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookFilterServletTest {

    @Mock
    HttpServletResponse response;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpSession session;
    @Mock
    RequestDispatcher dispatcher;

    BookFilterServlet filterServlet  = new BookFilterServlet();
    @BeforeEach
    void setUp() {
        response = mock(HttpServletResponse.class);
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        dispatcher = mock(RequestDispatcher.class);
        when(request.getSession()).thenReturn(session);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void processRequest() throws ServletException, IOException {
        String path = "error.jsp";
        when(request.getParameter("status")).thenReturn("Break");
        String[] cate = {"Anime", "Shonen","Isekai"};
        when(request.getParameterValues("categories")).thenReturn(cate);
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        when(request.getParameter("Sort")).thenReturn("A-Z");
        filterServlet.processRequest(request,response);
        verify(response).sendRedirect(path);
    }
    @Test
    void processRequest2() throws ServletException, IOException {
        String path = "bookFilter.jsp";
        when(request.getParameter("status")).thenReturn("break");
        String[] cate = {"Action", "Comedy","Adventures"};
        when(request.getParameterValues("categories")).thenReturn(cate);
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        when(request.getParameter("Sort")).thenReturn("A-Z");
        filterServlet.processRequest(request,response);

        verify(request).getRequestDispatcher(path);
        verify(dispatcher).forward(request,response);
    }
    @Test
    void processRequest3() throws ServletException, IOException {
        String path = "bookFilter.jsp";
        when(request.getParameter("status")).thenReturn("break");
        String[] cate = {"Action", "Comedy","Adventures"};
        when(request.getParameterValues("categories")).thenReturn(cate);
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        when(request.getParameter("Sort")).thenReturn("Most View");
        filterServlet.processRequest(request,response);

        verify(request,times(1)).getRequestDispatcher(path);
        verify(dispatcher).forward(request,response);
    }
    @Test
    void processRequest4() throws ServletException, IOException {
        String path = "error.jsp";
        when(request.getParameter("status")).thenReturn("break");
        String[] cate = {"Action", "Comedy","Adventures"};
        when(request.getParameterValues("categories")).thenReturn(cate);
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        when(request.getParameter("Sort")).thenReturn("A-Z");
        filterServlet.processRequest(request,response);

        verify(response).sendRedirect(path);
    }
}