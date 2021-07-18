package controller;

import dao.*;
import dao.hoang_dao.FollowDao;
import entity.*;
import org.junit.jupiter.api.*;
import org.mockito.Mock;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookFilterServletTest {

    BookDetailServlet bookDetailServlet;
    private static final String path = "bookFilter.jsp";
    @Mock
    HttpServletResponse response;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpSession session;
    @Mock
    RequestDispatcher dispatcher;


    @BeforeEach
    void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        session = mock(HttpSession.class);
        bookDetailServlet = new BookDetailServlet();
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
//Test case 1
    void processRequestTest1() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn("1");
        when(session.getAttribute("accountId")).thenReturn(21);

        bookDetailServlet.processRequest(request, response);

        verify(request, times(1)).getRequestDispatcher(path);
        verify(dispatcher).forward(request, response);
    }

    @Test
//Test case 1
    void processRequestTest2() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn("1");
        when(session.getAttribute("accountId")).thenReturn(0);

        bookDetailServlet.processRequest(request, response);

        verify(request, times(1)).getRequestDispatcher(path);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void processRequestTest4() {
        when(request.getParameter("id")).thenReturn("1");
        when(session.getAttribute("accountId")).thenReturn(0);

        int id = Integer.parseInt(request.getParameter("id"));
        int accountId = (int) session.getAttribute("accountId");

        Book a = new BookDao().getOne(id);
        List<Category> listCategories = new CategoryDao().getAllWithBookId(id);
        List<Chapter> lsChapter = new ChapterDao().getAllWithBookId(id);
        List<Comment> lsComment = new CommentDao().getAllWithBookId(id);
        boolean isFollowed = FollowDao.getFollowOrNot(id, accountId);
        Translator translator = new TranslatorDao().getOneWithBookId(id);
        List<Book> lsBookWithTranslator = new BookDao().getAllWithTranslatorId(translator.getTranslatorId());


        assertNotNull(listCategories);
        assertNotNull(lsChapter);
        assertNotNull(lsComment);
        assertFalse(isFollowed);
        assertNotNull(lsBookWithTranslator);
    }

    @Test
    void processRequestTest5() {
        when(request.getParameter("id")).thenReturn("1");
        when(session.getAttribute("accountId")).thenReturn(21);

        int id = Integer.parseInt(request.getParameter("id"));
        int accountId = (int) session.getAttribute("accountId");

        Book a = new BookDao().getOne(id);
        List<Category> listCategories = new CategoryDao().getAllWithBookId(id);
        List<Chapter> lsChapter = new ChapterDao().getAllWithBookId(id);
        List<Comment> lsComment = new CommentDao().getAllWithBookId(id);
        boolean isFollowed = FollowDao.getFollowOrNot(id, accountId);
        Translator translator = new TranslatorDao().getOneWithBookId(id);
        List<Book> lsBookWithTranslator = new BookDao().getAllWithTranslatorId(translator.getTranslatorId());

        assertNotNull(listCategories);
        assertNotNull(lsChapter);
        assertNotNull(lsComment);
        assertTrue(isFollowed);
        assertNotNull(lsBookWithTranslator);
    }

    @Test
    void processRequestTest6() {
        when(request.getParameter("id")).thenReturn("13435");
        when(session.getAttribute("accountId")).thenReturn(0);

        int id = Integer.parseInt(request.getParameter("id"));
        int accountId = (int) session.getAttribute("accountId");

        Book a = new BookDao().getOne(id);
        List<Category> listCategories = new CategoryDao().getAllWithBookId(id);
        List<Chapter> lsChapter = new ChapterDao().getAllWithBookId(id);
        List<Comment> lsComment = new CommentDao().getAllWithBookId(id);
        boolean isFollowed = FollowDao.getFollowOrNot(id, accountId);
        Translator translator = new TranslatorDao().getOneWithBookId(id);
        List<Book> lsBookWithTranslator = new BookDao().getAllWithTranslatorId(translator.getTranslatorId());

        assertNull(listCategories);
        assertNull(lsChapter);
        assertNull(lsComment);
        assertFalse(isFollowed);
        assertNull(lsBookWithTranslator);
    }

}