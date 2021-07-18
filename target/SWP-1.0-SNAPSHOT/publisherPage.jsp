<%-- 
    Document   : AdminPage
    Created on : Jun 19, 2021, 3:12:03 PM
    Author     : nguye
--%>

<%@page import="entity.Translator"%>
<%@page import="entity.Book"%>
<%@page import="Thai.entity.Report"%>
<%@page import="java.util.List"%>
<%@page import="entity.Account"%>
<%@page import="dao.TranslatorDao"%>
<%@page import="dao.Thai.TReportDao"%>
<%@page import="dao.BookDao"%>
<%@page import="dao.Thai.TAccountDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="asset/css/bootstrap.css" />
        <link rel="stylesheet" href="asset/css/publisher.css" />
        <title>Document</title>
    </head>
    <body>
        <div class="wrapper">
            <!-- Sidebar  -->
            <nav id="sidebar">
                <div class="sidebar-header"  style="background: #fbd593;">
                    <a href="BookServlet"><h3>BROMICS</h3></a>
                </div>

                <ul class="list-unstyled components" style="background: #FDE8C4;">
                    <li>
                        <a href="#">Dashboard</a>
                    </li>
                    <li>
                        <a href="TableServlet/Account">Acount Management</a>
                    </li>
                    <li>
                        <a href="TableServlet/Book">Book Management</a>
                    </li>
                    <li>
                        <a href="TableServlet/Translator">Translator Management</a>
                    </li>
                    <li>
                        <a href="TableServlet/Report">Report Management</a>
                    </li>
                </ul>
            </nav>

            <!-- Page Content  -->
            <div id="content">
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <div class="container-fluid">
                        <button type="button" id="sidebarCollapse" class="btn btn-info"  style="background: #FDCBC4; border-color: #FDC4F5">
                            <i class="fas fa-align-left"></i>
                            <span>Toggle Sidebar</span>
                        </button>
                        <form class="d-flex" action="AdminSearch">
                            <select class="form-select" aria-label="Default select example" name="searchType">
                                <option value="0" selected>Search Item</option>
                                <option value="Account">Account</option>
                                <option value="Book">Book</option>
                                <option value="Translator">Translator</option>
                                <option value="Report">Report</option>
                            </select>
                            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="searchValue">
                            <button class="btn btn-outline-dark" type="submit">Search</button>
                        </form>
                        <button
                            class="btn btn-dark d-inline-block d-lg-none ml-auto"
                            type="button"
                            data-toggle="collapse"
                            data-target="#navbarSupportedContent"
                            aria-controls="navbarSupportedContent"
                            aria-expanded="false"
                            aria-label="Toggle navigation"
                            >
                            <i class="fas fa-align-justify"></i>
                        </button>

                    </div>
                </nav>

                <button type="button" class="btn btn-outline-warning" onclick="myFunction2()">Add</button>

                <div id="myModal2" class="hmodal" style="text-align: left;">
                    <!-- Modal content -->
                    <div class="hmodal-content">
                        <div class="hmodal-header">
                            <span class="close2">&times;</span>
                            <h2>Modal Header</h2>
                        </div>
                        <form action="AddChapterServlet">
                            <div class="hmodal-body">
                                <p style="display: inline;">Comic: </p>
                                
                                <select name = "bookId" class="form-control" id="exampleFormControlSelect1" style="display: inline; width: 50%">
                                    <c:forEach items="${requestScope.lsBook}" var="book">
                                        <option value="${book.id}">${book.name}</option>
                                    </c:forEach>
                                </select>
                                <p style="display: block;"></p>
                                <p style="display: inline;">Chapter: </p><input id="b2" name="chapterName">
                                <p></p>
                            </div>
                            <div class="hmodal-footer">
                                <button class="btn btn-outline-dark" type="submit" id="savebtn">Add</button>
                            </div>
                        </form>
                    </div>
                </div>

                <script>
// Get the modal
                    var modal2 = document.getElementById("myModal2");

// Get the <span> element that closes the modal
                    var span2 = document.getElementsByClassName("close2")[0];

                    var btn = document.getElementById("savebtn");

// When the user clicks the button, open the modal 

                    function myFunction2(a, b, c, d, e) {
                        modal2.style.display = "block";

                    }

// When the user clicks on <span> (x), close the modal

                    span2.onclick = function () {
                        modal2.style.display = "none";
                    }

                    btn.onclick = function () {
                        modal2.style.display = "none";
                    }

// When the user clicks anywhere outside of the modal, close it
                    window.onclick = function (event) {
                        if (event.target == modal2) {
                            modal.style.display = "none";
                            modal2.style.display = "none";
                        }
                    }


                </script>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
        <script src="asset/js/bootstrap.js"></script>
        <script>
                    $(document).ready(function () {
                        $("#sidebarCollapse").on("click", function () {
                            $("#sidebar").toggleClass("active");
                        });
                    });


        </script>
    </body>
</html>