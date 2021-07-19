<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="asset/css/bootstrap.css"/>
    <link rel="stylesheet" href="asset/css/style.css"/>
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />

    <title>BookDetails</title>
</head>
<style>
    .book-report .dropdown-item:hover {
        cursor: pointer;
    }
</style>
<body>
<div class="container-fluid">
    <%@include file="navbar.jsp" %>
    <div class="bookDetail-wrapper">
        <div class="bookDetail-section">
            <div class="bookDetail">
                <div class="bookDetail-img">
                    <img
                            src="asset/img/book/${requestScope.book.imgName}"
                            alt="${requestScope.book.id}"
                    />
                </div>
                <div class="bookDetail-information">
                    <div class="book-name">
                        <h1>${requestScope.book.name}</h1>
                    </div>
                    <div class="infor">
                        <div>
                                    <span class="book-tag"
                                    ><i class="fa fa-rss"></i> Status:</span
                                    >
                            <span class="tag-detail book-status"
                            >${requestScope.book.status}</span
                            >
                        </div>
                        <div>
                                    <span class="book-tag">
                                        <i class="fa fa-tag"></i> Category:</span
                                    >
                            <span class="tag-detail">
                                        <c:forEach items="${requestScope.categories}" var="cate">
                                            <a
                                                    href="BookWithCategoryServlet?categoryId=${cate.categoryId}"
                                            >${cate.categoryName}</a
                                            >
                                        </c:forEach>
                                    </span>
                        </div>
                        <div>
                            <span class="book-tag"><i class="fa fa-eye"></i> View:</span>
                            <span class="tag-detail">${requestScope.book.totalView}</span>
                        </div>
                        <div class="book-tag wrap-star">
                            <div class="star-wrapper">
                                <a
                                        href="#"
                                        onclick="rate(${sessionScope.accountId},${requestScope.book.id}, 5)"
                                        class="fa fa-star s1"
                                ></a>
                                <a
                                        href="#"
                                        onclick="rate(${sessionScope.accountId},${requestScope.book.id}, 4)"
                                        class="fa fa-star s2"
                                ></a>
                                <a
                                        href="#"
                                        onclick="rate(${sessionScope.accountId},${requestScope.book.id}, 3)"
                                        class="fa fa-star s3"
                                ></a>
                                <a
                                        href="#"
                                        onclick="rate(${sessionScope.accountId},${requestScope.book.id}, 2)"
                                        class="fa fa-star s4"
                                ></a>
                                <a
                                        href="#"
                                        onclick="rate(${sessionScope.accountId},${requestScope.book.id}, 1)"
                                        class="fa fa-star s5"
                                ></a>
                                <span id="rate-mark">${requestScope.rateMark}</span>
                            </div>
                            <script src="https://kit.fontawesome.com/5ea815c1d0.js"></script>
                        </div>
                        <!--Rating-->
                    </div>
                    <div class="action">
                        <div class="action-1">
                            <button
                                    onclick="follow2(${book.id},${sessionScope.accountId}, 'book-bookmark', 'red', 'black')"
                                    class="action-follow"
                            >
                                        <span id="book-bookmark${requestScope.book.id}"
                                                <%if ((boolean) request.getAttribute("isFollow")) {%>
                                              style="color:red"<%}%>>Follow
                                        </span>
                            </button>

                            <button type="button" class="action-follow" style="margin-left: 15px"
                                    onclick="setClipboard()" data-toggle="popover" data-placement="top"
                                    data-content="Copied Comic's Link">
                                <i class="fas fa-share"></i>
                            </button>
                            <script>
                                function setClipboard() {
                                    var tempInput = document.createElement("input");
                                    tempInput.style = "position: absolute; left: -1000px; top: -1000px";
                                    tempInput.value = window.location.href;
                                    document.body.appendChild(tempInput);
                                    tempInput.select();
                                    document.execCommand("copy");
                                    document.body.removeChild(tempInput);
                                }
                            </script>
                            <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="false">
                                Report this book!
                            </button>
                            <div class="dropdown-menu book-report">
                                <div class="book-report-content" style="display:none"></div>
                                <div class="dropdown-item">
                                    <a href="ReportServlet?id=${requestScope.book.id}&accountId=${sessionScope.accountId}&content=AbusiveContent&status=1&type=book">
                                        Abusive content
                                    </a>
                                </div>
                                <div class="dropdown-item">
                                    <a href="ReportServlet?id=${requestScope.book.id}&accountId=${sessionScope.accountId}&content=Plagiarism&status=1&type=book">
                                        Plagiarism
                                    </a>
                                </div>
                                <div class="dropdown-item">
                                    <a href="ReportServlet?id=${requestScope.book.id}&accountId=${sessionScope.accountId}&content=EroticContent&status=1&type=book">
                                        Erotic content
                                    </a>
                                </div>
                                <div class="dropdown-item">
                                    <a href="ReportServlet?id=${requestScope.book.id}&accountId=${sessionScope.accountId}&content=HateSpeech&status=1&type=book">
                                        Hate speech
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="action-2">
                        <div class="read-first">
                            <a href="ReadFirstServlet?bookId=${requestScope.book.id}">
                                <button class="btn-hover first" onclick="">
                                    Read First
                                </button>
                            </a>
                        </div>
                        <div class="read-last">
                            <button class="btn-hover last">Continue</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <details>
                    <summary>Summary</summary>
                    <p>${requestScope.book.description}</p>
                </details>
                <div class="chap-list-wrapper">
                    <div class="function">Filter for chapter here</div>
                    <div class="chap-list-detail">
                        <table class="tablee">
                            <thead>
                            <tr>
                                <th>Chapter</th>
                                <th>Rating</th>
                                <th>View</th>
                                <th>Last_Updated</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.lsChapter}" var="chapter">
                                <tr>
                                    <td>
                                        <a
                                                href="ChapterDetailServlet?bookId=${chapter.bookId}&chapterId=${chapter.chapterId}&chapterName=${chapter.chapterName}"
                                        >
                                                ${chapter.chapterName}</a
                                        >
                                    </td>
                                    <td>5 star</td>
                                    <td>${chapter.view}</td>
                                    <td>${chapter.getDate()}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <%@include file="comment.jsp" %>
                </div>
            </div>
        </div>

    </div>
</div>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="asset/js/bootstrap.js"></script>
<script src="asset/js/bookInfo.js"></script>

</body>
</html>
