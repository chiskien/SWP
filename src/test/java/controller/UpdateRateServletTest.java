package controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;
import org.mockito.Mock;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

class UpdateRateServletTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    UpdateRateServlet updateRateServlet = new UpdateRateServlet();
    StringWriter writer;
    PrintWriter out;
    @BeforeEach
    void setUp() {
        writer = new StringWriter();
        out = new PrintWriter(writer);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    //test case 1
    @Test
    void testProcessRequest() throws ServletException, IOException {
        when(request.getParameter("accountId")).thenReturn("21");
        when(request.getParameter("bookId")).thenReturn("1");
        when(request.getParameter("mark")).thenReturn("5");
        when(response.getWriter()).thenReturn(out);
        updateRateServlet.processRequest(request,response);
        String result = writer.getBuffer().toString().trim();
        assertEquals(5, Double.parseDouble(result));
    }
    //test case 2
    //second time
    @Test
    void testProcessRequest2() throws ServletException, IOException {
        when(request.getParameter("accountId")).thenReturn("21");
        when(request.getParameter("bookId")).thenReturn("1");
        when(request.getParameter("mark")).thenReturn("4");
        when(response.getWriter()).thenReturn(out);
        updateRateServlet.processRequest(request,response);
        String result = writer.getBuffer().toString().trim();
        assertEquals(4.5, Double.parseDouble(result));
    }

    @Test
    void testProcessRequest3() throws ServletException, IOException {
        when(request.getParameter("accountId")).thenReturn("21");
        when(request.getParameter("bookId")).thenReturn("1");
        when(request.getParameter("mark")).thenReturn("3");
        when(response.getWriter()).thenReturn(out);
        updateRateServlet.processRequest(request,response);
        String result = writer.getBuffer().toString().trim();
        assertEquals(4, Double.parseDouble(result));
    }
    @Test
    void testProcessRequest4() throws ServletException, IOException {
        when(request.getParameter("accountId")).thenReturn("21");
        when(request.getParameter("bookId")).thenReturn("1");
        when(request.getParameter("mark")).thenReturn("2");
        when(response.getWriter()).thenReturn(out);
        updateRateServlet.processRequest(request,response);
        String result = writer.getBuffer().toString().trim();
        assertEquals(3.5, Double.parseDouble(result));
    }
    @Test
    void testProcessRequest5() throws ServletException, IOException {
        when(request.getParameter("accountId")).thenReturn("21");
        when(request.getParameter("bookId")).thenReturn("1");
        when(request.getParameter("mark")).thenReturn("1");
        when(response.getWriter()).thenReturn(out);
        updateRateServlet.processRequest(request,response);
        String result = writer.getBuffer().toString().trim();
        assertEquals(3, Double.parseDouble(result));
    }

    @AfterEach
    void tearDown() {
    }
}