<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>BookFilter</title>
        <link rel="stylesheet" href="asset/css/bootstrap.css" />
        <link rel="stylesheet" href="asset/css/card.css" />
        <link rel="stylesheet" href="asset/css/style.css" />
        <link
            rel="icon"
            href="asset/img/body/one_piece_3-wallpaper-1600x900.jpg"
            type="image/icon type"
            />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
            />
    </head>
    <body>
        <div class="container-fluid">
            <header>
                <div class="header-wrapper">
                    <div class="left">
                        <a href="BookServlet">
                            <h1>BROMICS</h1>
                        </a>
                        <div class="menu">
                            <ul>
                                <li><a href="BookServlet">Home</a></li>
                                <li class="dropdown">
                                    <a href="" class="dropdown-toggle" data-toggle="dropdown"
                                       >Categories</a
                                    >
                                    <ul class="dropdown-menu container">
                                        <div class="row">
                                            <c:forEach items="${requestScope.lsCategory}" var="category">
                                                <div class="col-md-2">
                                                    <li>
                                                        <a
                                                            class="dropdown-item"
                                                            href="BookWithCategoryServlet?categoryId=${category.categoryId}"
                                                            >${category.categoryName}</a
                                                        >
                                                    </li>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </ul>
                                </li>
                                <li><a href="BookFilterServlet">Explore</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="right">
                        <a href=""
                           ><div class="btn-notification">
                                <i class="fa fa-bell" style="font-size: 30px"></i></div
                            ></a>
                        <form
                            action="BookFilterServlet"
                            method="GET"
                            class="navbar-form form-inline"
                            style="display: inline-block"
                            >
                            <div class="input-group search-box">
                                <input
                                    type="text"
                                    id="search"
                                    class="form-control"
                                    placeholder="Search for book/author"
                                    />
                                <button class="input-group-addon" type="submit">
                                    <i class="fa fa-search" style="font-size: 27px"></i>
                                </button>
                            </div>
                        </form>
                        <c:choose>
                            <c:when test="${sessionScope.user != null}">
                                <div class="dropdown" style="display: inline-block">
                                    <a
                                        href=""
                                        class="login dropdown-toggle user-action"
                                        data-toggle="dropdown"
                                        >
                                        ${sessionScope.user} <b class="carpet"></b>
                                    </a>
                                    <ul class="dropdown-menu" style="width: 100px">
                                        <li>
                                            <a href="userProfile.jsp"> <i class="fa fa-user"></i> Profile</a>
                                        </li>
                                        <li>
                                            <a href="following.jsp"> <i class="fa fa-heart"></i> Following</a>
                                        </li>
                                        <li>
                                            <a href="history.jsp"> <i class="fa fa-history"></i> Recently</a>
                                        </li>
                                        <li>
                                            <a href="bookmark.jsp"> <i class="fa fa-bookmark"></i> Book Mark</a>
                                        </li>
                                        <li class="divider"></li>
                                        <li><a href="LogoutServlet">Log Out</a></li>
                                    </ul>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <a href="login2.jsp"><p class="login">Login</p></a>
                            </c:otherwise>
                        </c:choose>

                        <!-- <div class="section_search"></div> -->
                    </div>
                </div>
                <script src="asset/js/popper.js"></script>
            </header>      
            <form action="BookFilterServlet" method="get" class="filter-wrapper">
                <div class="book-collection-wrapper">
                    <div class="book-collection">
                        <div class="row" style="justify-content: space-between; margin-bottom: 20px;">
                            <div class="action-sort">
                                <select name="sort">
                                    <c:choose>
                                        <c:when test="${requestScope.sort=='A-Z'}">
                                            <option selected>A-Z</option>
                                            <option>Z-A</option>
                                            <option>Newest</option>
                                            <option>Oldest</option>
                                            <option>Most View</option>
                                        </c:when>
                                        <c:when test="${requestScope.sort=='Z-A'}">
                                            <option>A-Z</option>
                                            <option selected>Z-A</option>
                                            <option>Newest</option>
                                            <option>Oldest</option>
                                            <option>Most View</option>
                                        </c:when>
                                        <c:when test="${requestScope.sort=='Newest'}">
                                            <option>A-Z</option>
                                            <option>Z-A</option>
                                            <option selected>Newest</option>
                                            <option>Oldest</option>
                                            <option>Most View</option>
                                        </c:when>
                                        <c:when test="${requestScope.sort=='Oldest'}">
                                            <option>A-Z</option>
                                            <option>Z-A</option>
                                            <option>Newest</option>
                                            <option selected>Oldest</option>
                                            <option>Most View</option>
                                        </c:when>
                                        <c:when test="${requestScope.sort=='Most View'}">
                                            <option>A-Z</option>
                                            <option>Z-A</option>
                                            <option>Newest</option>
                                            <option>Oldest</option>
                                            <option selected>Most View</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option>A-Z</option>
                                            <option>Z-A</option>
                                            <option>Newest</option>
                                            <option>Oldest</option>
                                            <option>Most View</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </div>
                            <div class="action-submit">
                                <input type="submit" value="Submit">
                            </div>
                        </div>
                        <div class="row">
                            <c:choose>
                                <c:when test="${requestScope.data.size()!=0}">
                                    <c:forEach items="${requestScope.data}" var="book">
                                        <c:if test="${book.appear==0}">
                                            <div class="card3">
                                                <!--Card demo =))) đừng xóa cái này-->
                                                <div class="profile">
                                                    <img
                                                        src="asset/img/book/${book.imgName}"
                                                        class="top"
                                                        alt="${book.name}"
                                                        />
                                                    <div class="bottom">
                                                        <div class="book-name">
                                                            <a href="BookDetailServlet?id=${book.id}">
                                                                <h5 style="color: #292926 !important">${book.name}</h5>
                                                            </a>
                                                        </div>
                                                        <a href="BookDetailServlet?id=${book.id}"
                                                           ><button class="book-action3 b1">
                                                                View Detail
                                                            </button></a
                                                        >
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise> 
                                    <%@include file = "error.jsp"%>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
                <div class="filter-content-wrapper">
                    <div class="filter-status">
                        <div class="radio-wrapper">
                            <ul>
                                <li>
                                    <input
                                        type="radio"
                                        id="ongoing"
                                        name="status"
                                        value="on going"
                                        />
                                    <label for="ongoing">OnGoing</label>
                                </li>
                                <li>
                                    <input
                                        type="radio"
                                        id="finished"
                                        name="status"
                                        value="finished"
                                        />
                                    <label for="finished">Finished</label>
                                </li>
                                <li>
                                    <input type="radio" id="break" name="status" value="break" />
                                    <label for="break">Break</label>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="filter-categories">
                        <div class="categories-filter-wrapper">
                            <ul style="padding:0">
                                <div class="row">
                                    <c:forEach items="${requestScope.lsCategory}" var = "category">
                                        <div class="col-md-6">
                                            <li style="font-size:0.85rem; margin: 5px 0;">
                                                <input type="checkbox" name="categories" id="${category.categoryId}"  value="${category.categoryId}">
                                                <label for="${category.categoryId}">${category.categoryName}</label>
                                            </li>
                                        </div>
                                    </c:forEach>
                                </div>
                            </ul>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
        <script src="asset/js/bootstrap.js"></script>
    </body>
</html>
