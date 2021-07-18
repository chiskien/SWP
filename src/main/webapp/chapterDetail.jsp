<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="asset/css/normalize.css" />
        <link rel="stylesheet" href="asset/css/bootstrap.css" />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
            />
        <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
        <script src="asset/js/bootstrap.js"></script>
        <script src="asset/js/bookInfo.js"></script>
        <link rel="stylesheet" href="asset/css/chapDetail.css" />
        <title>Chapter Detail</title>
    </head>
    <body id="#">
        <div class="container-fluid">
            <header>
                <div class="header-wrapper">
                    <div class="header-nav">
                        <a href="BookServlet" class="home-link"><h1>BROMICS</h1></a>
                        <div class="name">
                            <a href="BookDetailServlet?id=${book.id}">${requestScope.book.name}</a>|
                            <a href="BookDetailServlet?id=${book.id}" id="chapter-index"> ${requestScope.chapter.chapterName}</a>
                            <button style="color:white" onclick = "bookmark3(${requestScope.book.id},${sessionScope.accountId},${requestScope.chapter.chapterId}, 'book-bookmark', 'red', 'white')" id = "book-bookmark${requestScope.book.id}">
                                <i class="fa fa-bookmark" style="color:inherit; font-size:30px; "></i>
                            </button>
                        </div>
                    </div>
                </div>
            </header>
            <main>
                <div class="container" style="text-align: center">
                    <c:forEach items="${requestScope.lsFrame}" var="frame">
                        <img src="asset/img/book/${book.name}/${frame.imgName}" style="margin: 10px 0; width: 80%;">
                    </c:forEach>
                </div>
            </main>
            <footer>
                <div class="footerr">
                    <div class="inner">
                        <a id="backward" href="ChangeChapterServlet?bookId=${requestScope.book.id}&chapterId=${requestScope.chapter.chapterId}&status=1">
                            <i class="	fa fa-chevron-left" ></i>
                        </a>
                        <a href="BookDetailServlet?id=${book.id}" style="margin:0 20px">
                            <i class="fa fa-navicon" ></i>
                        </a>
                        <a id="forward" href="ChangeChapterServlet?bookId=${requestScope.book.id}&chapterId=${requestScope.chapter.chapterId}&status=0">
                            <i class="fa fa-chevron-right"></i>
                        </a>
                    </div>
                </div>
            </footer>
        </div>
        <script></script>
    </body>
</html>
