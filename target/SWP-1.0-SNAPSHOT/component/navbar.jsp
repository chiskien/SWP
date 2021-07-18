<%-- 
    Document   : navbar
    Created on : Jan 14, 2021, 9:02:27 PM
    Author     : hoang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<nav class="navbar navbar-expand-lg navbar-light "  style="background-color: rgb(255,128,0)">
    <div class="container">
        <a class="navbar-brand" href="BookServlet">TruyentranhHOANG</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
                <!--                <a class="nav-link active" aria-current="page" href="#">Category</a>-->
                <div class="dropdown">
                    <button class="btn" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                        Category
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton" style="width:70vw; top:45px">
                        <div class="row">
                            <c:forEach items="${requestScope.lsCategory}" var = "category">
                                <div class="col-md-2">
                                    <li><a class="dropdown-item" href="BookWithCategoryServlet?categoryId=${category.categoryId}">${category.categoryName}</a></li>
                                </div>
                            </c:forEach>
                        </div>
                    </ul>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="HistoryServlet">History</a>
            </li>
            <c:if test="${requestScope.admin}">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    Web management
                </a>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <li><a class="dropdown-item" href="AdminBookManagement">Book management</a></li>
                    <li><a class="dropdown-item" href="ChartController">Charts</a></li>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item" href="#">Something else here</a></li>
                </ul>
            </li>
            </c:if>
            <li class="nav-item">
                <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
            </li>
        </ul>
        <form class="d-flex" action="BookSearchServlet">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="search">
            <button class="btn btn-outline-success" type="submit">Search</button>
        </form>

        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <div class="dropdown">
                    <button class="btn" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                        ${sessionScope.user}
                    </button>
                    <div class="accountDropdown dropdown-menu" aria-labelledby="dropdownMenuButton" style="width:200%;right:0!important;left:auto">
                        <a href="#" class="dropdown-item">Personal page</a>
                        <div class="dropdown-divider"></div>
                        <a href="#" class="dropdown-item">Bookcase</a>
                        <div class="dropdown-divider"></div>
                        <a href="LogoutServlet" class="dropdown-item" style="text-align: center">Log out</a>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <form action="login.jsp" method="get">
                    <button class="btn btn-outline-success" type="submit">Login</button>
                </form>
                <form action="register.jsp" method="get">
                    <button class="btn btn-outline-success" type="submit">Register</button>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
</nav>