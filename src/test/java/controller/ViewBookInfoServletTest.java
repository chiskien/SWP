package controller;

import dao.hoang_dao.FollowDao;
import dao.hoang_dao.HBookMarkDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ViewBookInfoServletTest {
    ViewBookInfoServlet viewBookInfoServlet = new ViewBookInfoServlet();
    StringWriter writer = new StringWriter();
    @Mock
    HttpServletResponse response;

    @Mock
    HttpServletRequest request;

    @BeforeEach
    void setup() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    //Test Case 1
    @Test
    void processRequestTest1() throws IOException, ServletException {
        when(request.getParameter("bookId")).thenReturn("16");
        when(request.getParameter("accountId")).thenReturn("21");
        when(response.getWriter()).thenReturn(new PrintWriter(writer));
        viewBookInfoServlet.processRequest(request, response);
        assertEquals("{\"isFollow\":false,\"isBookmarked\":true}",
                writer.toString());

    }

    //Test Case 2
    @Test
    void processRequestTest2() throws IOException, ServletException {
        when(request.getParameter("bookId")).thenReturn("1");
        when(request.getParameter("accountId")).thenReturn("21");
        when(response.getWriter()).thenReturn(new PrintWriter(writer));
        viewBookInfoServlet.processRequest(request, response);
        assertEquals("{\"isFollow\":true,\"isBookmarked\":true}",
                writer.toString());

    }

    //Test Case 3
    @Test
    void processRequestTest3() throws IOException, ServletException {
        when(request.getParameter("bookId")).thenReturn("3");
        when(request.getParameter("accountId")).thenReturn("21");
        when(response.getWriter()).thenReturn(new PrintWriter(writer));
        viewBookInfoServlet.processRequest(request, response);
        assertEquals("{\"isFollow\":true,\"isBookmarked\":true}",
                writer.toString());

    }

    //Test Case 4
    @Test
    void processRequestTest4() throws IOException, ServletException {
        when(request.getParameter("bookId")).thenReturn("4");
        when(request.getParameter("accountId")).thenReturn("21");
        when(response.getWriter()).thenReturn(new PrintWriter(writer));
        viewBookInfoServlet.processRequest(request, response);
        assertEquals("{\"isFollow\":true,\"isBookmarked\":false}",
                writer.toString());

    }//Test Case 5

    @Test
    void processRequestTest5() throws IOException, ServletException {
        when(request.getParameter("bookId")).thenReturn("28");
        when(request.getParameter("accountId")).thenReturn("21");
        when(response.getWriter()).thenReturn(new PrintWriter(writer));
        viewBookInfoServlet.processRequest(request, response);
        assertEquals("{\"isFollow\":true,\"isBookmarked\":false}",
                writer.toString());

    }//Test Case 7

    @Test
    void processRequestTest7() throws IOException, ServletException {
        when(request.getParameter("bookId")).thenReturn("24");
        when(request.getParameter("accountId")).thenReturn("21");
        when(response.getWriter()).thenReturn(new PrintWriter(writer));
        viewBookInfoServlet.processRequest(request, response);
        assertEquals("{\"isFollow\":false,\"isBookmarked\":true}",
                writer.toString());

    }//Test Case 8

    @Test
    void processRequestTest8() throws IOException, ServletException {
        when(request.getParameter("bookId")).thenReturn("32");
        when(request.getParameter("accountId")).thenReturn("21");
        when(response.getWriter()).thenReturn(new PrintWriter(writer));
        viewBookInfoServlet.processRequest(request, response);
        assertEquals("{\"isFollow\":false,\"isBookmarked\":true}",
                writer.toString());

    }//Test Case 9

    @Test
    void processRequestTest9() throws IOException, ServletException {
        when(request.getParameter("bookId")).thenReturn("13213");
        when(request.getParameter("accountId")).thenReturn("21");
        when(response.getWriter()).thenReturn(new PrintWriter(writer));
        viewBookInfoServlet.processRequest(request, response);
        assertEquals("{\"isFollow\":false,\"isBookmarked\":false}",
                writer.toString());

    }//Test Case 10

    @Test
    void processRequestTest10() throws IOException, ServletException {
        when(request.getParameter("bookId")).thenReturn("0");
        when(request.getParameter("accountId")).thenReturn("21");
        when(response.getWriter()).thenReturn(new PrintWriter(writer));
        viewBookInfoServlet.processRequest(request, response);
        assertEquals("{\"isFollow\":false,\"isBookmarked\":false}",
                writer.toString());
    }

    @Test
    void processRequestTest11() throws IOException, ServletException {
        when(request.getParameter("bookId")).thenReturn("16");
        when(request.getParameter("accountId")).thenReturn("2");
        when(response.getWriter()).thenReturn(new PrintWriter(writer));
        viewBookInfoServlet.processRequest(request, response);
        assertEquals("{\"isFollow\":false,\"isBookmarked\":false}",
                writer.toString());

    }

}
