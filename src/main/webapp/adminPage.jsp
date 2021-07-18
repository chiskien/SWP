<%-- 
    Document   : AdminPage
    Created on : Jun 19, 2021, 3:12:03 PM
    Author     : nguye
--%>

<%@page import="dao.AccountDao"%>
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
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="asset/css/bootstrap.css" />
        <link rel="stylesheet" href="asset/css/admin.css" />
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

        <title>Document</title>
        <%
            String type = (String) session.getAttribute("type");
//            String type = (String) request.getAttribute("type");
            AccountDao acc = new AccountDao();
            List<Account> accs = new TAccountDao().getAll();
            List<Translator> trans = new TranslatorDao().getAll();
        %>
    </head>
    <body>
        <div class="wrapper">
            <!-- Sidebar  -->
            <nav id="sidebar">
                <div class="sidebar-header">
                    <h3>BROMICS</h3>
                </div>

                <ul class="list-unstyled components">
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
                        <button type="button" id="sidebarCollapse" class="btn btn-info">
                            <i class="fas fa-align-left"></i>
                            <span>Toggle Sidebar</span>
                        </button>
                        <button type="button" id="" class="btn btn-info" onclick="myFunction3()">
                            <span>New Notification</span>
                            <i class="far fa-comment-dots"></i>
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

                <div id="noti" class="hmodal" style="text-align: left;">
                    <!-- Modal content -->
                    <div class="hmodal-content">
                        <div class="hmodal-header" style="background-color:  white">
                            <span class="nclose" style="color: black">&times;</span>
                            <h2 style="color: black">Send notification</h2>
                        </div>
                        <form action="NotificationSender">
                            <div class="hmodal-body">
                                <p style="display: inline;">Receiver: </p>

                                <select name="account">
                                    <option value="100">All Account</option>
                                    <option value="0">Not select</option>
                                    <% for (Account a : accs) {%>
                                    <option value=<%= a.getAccountId()%>><%= a.getName()%></option>
                                    <%}%>
                                </select>

                                <select name="translator">
                                    <option value="100">All Translator</option>
                                    <option value="0">Not select</option>
                                    <% for (Translator t : trans) {%>
                                    <option value=<%= t.getTranslatorId()%>><%= t.getName()%></option>
                                    <%}%>
                                </select>

                                <p style="display: block;"></p>
                                <p style="display: inline;">Content: </p> 
                                <p style="display: block;"></p>
                                <textarea name="content" rows="10" style="width: 100%;"></textarea>
                                <input name="page" hidden="true" value="<%=type%>">
                                <p></p>
                            </div>
                            <div class="hmodal-footer" style="background-color: white; padding: 15px;">
                                <button class="btn btn-outline-dark" type="submit" id="savebtn">Send</button>
                            </div>
                        </form>
                    </div>
                </div>

                <script>
// Get the modal
                    var noti = document.getElementById("noti");

// Get the <span> element that closes the modal
                    var nspan = document.getElementsByClassName("nclose")[0];

// When the user clicks the button, open the modal 
                    function myFunction3() {
                        noti.style.display = "block";
                    }

// When the user clicks on <span> (x), close the modal
                    nspan.onclick = function () {
                        noti.style.display = "none";
                    }

// When the user clicks anywhere outside of the modal, close it
                    window.onclick = function (event) {
                        if (event.target == noti) {
                            noti.style.display = "none";
                        }
                    }


                </script>

                <%if (type.contains("Account")) {
                        List<Account> list = null;
                        if (type.equals("Account")) {
                            list = (List<Account>) session.getAttribute("RequestList");
//                        List<Account> list = (List<Account>) request.getAttribute("RequestList");
                %><h2>Account Managerment</h2><%
                } else {
                    list = (List<Account>) session.getAttribute("result");
                %><h2>Account Search</h2><%
                    }
                %>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th scope="col">Account ID</th>
                            <th scope="col">Name</th>
                            <th scope="col">Email</th>
                            <th scope="col">Role</th>
                            <th scope="col">Status</th>
                        </tr>
                    <tbody>
                        <% for (int i = 0; i < list.size(); i++) {
                        %>
                        <tr>
                            <th scope="row" onclick="myFunction('<%=list.get(i).getAccountId()%>', '<%=list.get(i).getName()%>', '<%=list.get(i).getEmail()%>', '<%=list.get(i).getRole()%>', '<%=list.get(i).getStatus()%>')">
                                <%=list.get(i).getAccountId()%>
                                </td> 
                            <td onclick="myFunction('<%=list.get(i).getAccountId()%>', '<%=list.get(i).getName()%>', '<%=list.get(i).getEmail()%>', '<%=list.get(i).getRole()%>', '<%=list.get(i).getStatus()%>')">
                                <%=list.get(i).getName()%>
                            </td>
                            <td onclick="myFunction('<%=list.get(i).getAccountId()%>', '<%=list.get(i).getName()%>', '<%=list.get(i).getEmail()%>', '<%=list.get(i).getRole()%>', '<%=list.get(i).getStatus()%>')">
                                <%=list.get(i).getEmail()%>
                            </td>                
                            <td onclick="myFunction('<%=list.get(i).getAccountId()%>', '<%=list.get(i).getName()%>', '<%=list.get(i).getEmail()%>', '<%=list.get(i).getRole()%>', '<%=list.get(i).getStatus()%>')">
                                <%=list.get(i).getRole()%>
                            </td>
                            <td onclick="myFunction('<%=list.get(i).getAccountId()%>', '<%=list.get(i).getName()%>', '<%=list.get(i).getEmail()%>', '<%=list.get(i).getRole()%>', '<%=list.get(i).getStatus()%>')">
                                <%=list.get(i).getStatus()%>
                            </td>
                            <td>
                                <a onclick="myFunction2('<%=list.get(i).getAccountId()%>', '<%=list.get(i).getName()%>', '<%=list.get(i).getEmail()%>', '<%=list.get(i).getRole()%>', '<%=list.get(i).getStatus()%>')">Edit</a>
                                <a href="DeleteServlet/Account?id=<%=list.get(i).getAccountId()%>" onclick="return confirm('Are you sure you want to delete this Account?');">Delete</a>
                            </td> 
                        </tr>
                    <div id="myModal" class="hmodal" style="text-align: left;">
                        <!-- Modal content -->
                        <div class="hmodal-content">
                            <div class="hmodal-header">
                                <span class="close">&times;</span>
                                <h2>Account</h2>
                            </div>
                            <div class="hmodal-body">
                                <p id="a"></p>
                                <p id="b"></p>
                                <p id="c"></p>
                                <p id="d"></p>
                                <p id="e"></p>
                            </div>
                            <div class="hmodal-footer">
                                <h3></h3>
                            </div>
                        </div>
                    </div>

                    <div id="myModal2" class="hmodal" style="text-align: left;">
                        <!-- Modal content -->
                        <div class="hmodal-content">
                            <div class="hmodal-header">
                                <span class="close2">&times;</span>
                                <h2>Account</h2>
                            </div>
                            <form action="EditServlet/Account">
                                <div class="hmodal-body">
                                    <p style="display: inline;">ID: </p><input id="a2" name="id" readonly="">
                                    <p style="display: block;"></p>
                                    <p style="display: inline;">Name: </p><input id="b2" name="name">
                                    <p style="display: block;"></p>
                                    <p style="display: inline;">Email: </p><input id="c2" name="email">
                                    <p style="display: block;"></p>
                                    <p style="display: inline;">Role: </p><input id="d2" name="role">
                                    <p style="display: block;"></p>
                                    <p style="display: inline;">Status: </p><input id="e2"  name="status">
                                    <p></p>
                                </div>
                                <div class="hmodal-footer">
                                    <button class="btn btn-outline-dark" type="submit" id="savebtn">Save</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <%}%>
                    </tbody>
                </table>


                <script>
// Get the modal
                    var modal = document.getElementById("myModal");
                    var modal2 = document.getElementById("myModal2");

// Get the <span> element that closes the modal
                    var span = document.getElementsByClassName("close")[0];
                    var span2 = document.getElementsByClassName("close2")[0];

                    var btn = document.getElementById("savebtn");

// When the user clicks the button, open the modal 
                    function myFunction(a, b, c, d, e) {
                        modal.style.display = "block";
                        document.getElementById("a").innerHTML = "Account ID: " + a;
                        document.getElementById("b").innerHTML = "Name: " + b;
                        document.getElementById("c").innerHTML = "Email: " + c;
                        document.getElementById("d").innerHTML = "Role: " + d;
                        document.getElementById("e").innerHTML = "Status: " + e;
                    }

                    function myFunction2(a, b, c, d, e) {
                        modal2.style.display = "block";
                        document.getElementById("a2").value = a;
                        document.getElementById("b2").value = b;
                        document.getElementById("c2").value = c;
                        document.getElementById("d2").value = d;
                        document.getElementById("e2").value = e;
                    }

// When the user clicks on <span> (x), close the modal
                    span.onclick = function () {
                        modal.style.display = "none";
                    }

                    span2.onclick = function () {
                        modal2.style.display = "none";
                    }

                    btn.onclick = function () {
                        modal2.style.display = "none";
                    }

// When the user clicks anywhere outside of the modal, close it
                    window.onclick = function (event) {
                        if (event.target == modal || event.target == modal2) {
                            modal.style.display = "none";
                            modal2.style.display = "none";
                        }
                    }


                </script>

                <%} else if (type.contains("Report")) {
                    List<Report> list = null;
                    if (type.equals("Report")) {
                        list = (List<Report>) session.getAttribute("RequestList");
                %><h2>Report Managerment</h2><%
                } else {
                    list = (List<Report>) session.getAttribute("result");
                %><h2>Report Search</h2><%
                    }
                %>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th scope="col">Report ID</th>
                            <th scope="col">Account ID</th>
                            <th scope="col">Book ID</th>
                            <th scope="col">Comment ID</th>
                            <th scope="col">Content</th>
                            <th scope="col">Status</th>
                            <th scope="col">Action</th>
                        </tr>
                    <tbody>
                        <% for (int i = 0; i < list.size(); i++) {
                        %>
                        <tr>
                            <th scope="row" onclick="myFunction('<%=list.get(i).getReportId()%>', '<%=list.get(i).getAccountId()%>', '<%=list.get(i).getBookId()%>', '<%=list.get(i).getCommentId()%>', '<%=list.get(i).getContent()%>', '<%=list.get(i).getStatus()%>')">
                                <%=list.get(i).getReportId()%>
                                </td> 
                            <td onclick="myFunction('<%=list.get(i).getReportId()%>', '<%=list.get(i).getAccountId()%>', '<%=list.get(i).getBookId()%>', '<%=list.get(i).getCommentId()%>', '<%=list.get(i).getContent()%>', '<%=list.get(i).getStatus()%>')">
                                <%=list.get(i).getAccountId()%>
                            </td>
                            <td onclick="myFunction('<%=list.get(i).getReportId()%>', '<%=list.get(i).getAccountId()%>', '<%=list.get(i).getBookId()%>', '<%=list.get(i).getCommentId()%>', '<%=list.get(i).getContent()%>', '<%=list.get(i).getStatus()%>')">
                                <%=list.get(i).getBookId()%>
                            </td>                
                            <td onclick="myFunction('<%=list.get(i).getReportId()%>', '<%=list.get(i).getAccountId()%>', '<%=list.get(i).getBookId()%>', '<%=list.get(i).getCommentId()%>', '<%=list.get(i).getContent()%>', '<%=list.get(i).getStatus()%>')">
                                <%=list.get(i).getCommentId()%>
                            </td>
                            <td onclick="myFunction('<%=list.get(i).getReportId()%>', '<%=list.get(i).getAccountId()%>', '<%=list.get(i).getBookId()%>', '<%=list.get(i).getCommentId()%>', '<%=list.get(i).getContent()%>', '<%=list.get(i).getStatus()%>')">
                                <%=list.get(i).getContent()%>
                            </td>
                            <td onclick="myFunction('<%=list.get(i).getReportId()%>', '<%=list.get(i).getAccountId()%>', '<%=list.get(i).getBookId()%>', '<%=list.get(i).getCommentId()%>', '<%=list.get(i).getContent()%>', '<%=list.get(i).getStatus()%>')">
                                <%=list.get(i).getStatus()%>
                            </td> 
                            <% if(list.get(i).getStatus() == 1) {%>
                            <td>
                                <a href="ServletReportDetail?aid=<%=list.get(i).getReportId()%>" >
                                <button type="button" id="" class="btn btn-info" data-toggle="tooltip" data-placement="top" title="Pass">
                                    <i class="fas fa-check"></i>
                                </button>
                                </a>
                                <a href="SystemPunisherServlet?aid=<%=list.get(i).getReportId()%>" >
                                <button type="button" id="" class="btn btn-danger" data-toggle="tooltip" data-placement="top" title="Ban">
                                    <i class="fas fa-ban"></i>
                                </button>
                                </a>
                            </td>
                            <%} else {%>
                            <td></td>
                            <%}%>
                        </tr>
                    <div id="myModal" class="hmodal" style="text-align: left;">
                        <!-- Modal content -->
                        <div class="hmodal-content">
                            <div class="hmodal-header">
                                <span class="close">&times;</span>
                                <h2>Report</h2>
                            </div>
                            <div class="hmodal-body">
                                <p id="a"></p>
                                <p id="b"></p>
                                <p id="c"></p>
                                <p id="d"></p>
                                <p id="e"></p>
                                <p id="f"></p>
                            </div>
                            <div class="hmodal-footer">
                                <h3></h3>
                            </div>
                        </div>
                    </div>
                    <%}%>
                    </tbody>
                </table>
                <script>
// Get the modal
                    var modal = document.getElementById("myModal");

// Get the <span> element that closes the modal
                    var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
                    function myFunction(a, b, c, d, e, f) {
                        modal.style.display = "block";
                        document.getElementById("a").innerHTML = "Report ID: " + a;
                        document.getElementById("b").innerHTML = "Account ID: " + b;
                        document.getElementById("c").innerHTML = "Book ID: " + c;
                        document.getElementById("d").innerHTML = "Comment ID: " + d;
                        document.getElementById("e").innerHTML = "Content: " + e;
                        document.getElementById("f").innerHTML = "Status: " + f;
                    }

// When the user clicks on <span> (x), close the modal
                    span.onclick = function () {
                        modal.style.display = "none";
                    }

// When the user clicks anywhere outside of the modal, close it
                    window.onclick = function (event) {
                        if (event.target == modal) {
                            modal.style.display = "none";
                        }
                    }
                </script>
                <%} else if (type.contains("Book")) {
                    List<Book> list = null;
                    if (type.equals("Book")) {
                        list = (List<Book>) session.getAttribute("RequestList");
                %><h2>Book Managerment</h2><%
                } else {
                    list = (List<Book>) session.getAttribute("result");
                %><h2>Book Search</h2><%
                    }
                %>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th scope="col">BookID</th>
                            <th scope="col">Name</th>
                            <th scope="col">AuthorID</th>
                            <th scope="col">TranslatorID</th>
                            <th scope="col">TotalChap</th>
                            <th scope="col">Appear</th>
                            <th scope="col">TotalView</th>
                            <th scope="col">Status</th>
                            <th scope="col">ImgName</th>
                        </tr>
                    <tbody>
                        <% for (int i = 0; i < list.size(); i++) {
                        %>
                        <tr>
                            <th scope="row" onclick="myFunction('<%=list.get(i).getId()%>', '<%=list.get(i).getName()%>', '<%=list.get(i).getAuthorId()%>', '<%=list.get(i).getTranslatorId()%>', '<%=list.get(i).getTotalChap()%>', '<%=list.get(i).getAppear()%>', '<%=list.get(i).getTotalView()%>', '<%=list.get(i).getStatus()%>', '<%=list.get(i).getDescription()%>', '<%=list.get(i).getImgName()%>')">
                                <%=list.get(i).getId()%>
                                </td> 
                            <td onclick="myFunction('<%=list.get(i).getId()%>', '<%=list.get(i).getName()%>', '<%=list.get(i).getAuthorId()%>', '<%=list.get(i).getTranslatorId()%>', '<%=list.get(i).getTotalChap()%>', '<%=list.get(i).getAppear()%>', '<%=list.get(i).getTotalView()%>', '<%=list.get(i).getStatus()%>', '<%=list.get(i).getDescription()%>', '<%=list.get(i).getImgName()%>')">
                                <%=list.get(i).getName()%>
                            </td>
                            <td onclick="myFunction('<%=list.get(i).getId()%>', '<%=list.get(i).getName()%>', '<%=list.get(i).getAuthorId()%>', '<%=list.get(i).getTranslatorId()%>', '<%=list.get(i).getTotalChap()%>', '<%=list.get(i).getAppear()%>', '<%=list.get(i).getTotalView()%>', '<%=list.get(i).getStatus()%>', '<%=list.get(i).getDescription()%>', '<%=list.get(i).getImgName()%>')">
                                <%=list.get(i).getAuthorId()%>
                            </td>                
                            <td onclick="myFunction('<%=list.get(i).getId()%>', '<%=list.get(i).getName()%>', '<%=list.get(i).getAuthorId()%>', '<%=list.get(i).getTranslatorId()%>', '<%=list.get(i).getTotalChap()%>', '<%=list.get(i).getAppear()%>', '<%=list.get(i).getTotalView()%>', '<%=list.get(i).getStatus()%>', '<%=list.get(i).getDescription()%>', '<%=list.get(i).getImgName()%>')">
                                <%=list.get(i).getTranslatorId()%>
                            </td>
                            <td onclick="myFunction('<%=list.get(i).getId()%>', '<%=list.get(i).getName()%>', '<%=list.get(i).getAuthorId()%>', '<%=list.get(i).getTranslatorId()%>', '<%=list.get(i).getTotalChap()%>', '<%=list.get(i).getAppear()%>', '<%=list.get(i).getTotalView()%>', '<%=list.get(i).getStatus()%>', '<%=list.get(i).getDescription()%>', '<%=list.get(i).getImgName()%>')">
                                <%=list.get(i).getTotalChap()%>
                            </td>
                            <td onclick="myFunction('<%=list.get(i).getId()%>', '<%=list.get(i).getName()%>', '<%=list.get(i).getAuthorId()%>', '<%=list.get(i).getTranslatorId()%>', '<%=list.get(i).getTotalChap()%>', '<%=list.get(i).getAppear()%>', '<%=list.get(i).getTotalView()%>', '<%=list.get(i).getStatus()%>', '<%=list.get(i).getDescription()%>', '<%=list.get(i).getImgName()%>')">
                                <%=list.get(i).getAppear()%>
                            </td>
                            <td onclick="myFunction('<%=list.get(i).getId()%>', '<%=list.get(i).getName()%>', '<%=list.get(i).getAuthorId()%>', '<%=list.get(i).getTranslatorId()%>', '<%=list.get(i).getTotalChap()%>', '<%=list.get(i).getAppear()%>', '<%=list.get(i).getTotalView()%>', '<%=list.get(i).getStatus()%>', '<%=list.get(i).getDescription()%>', '<%=list.get(i).getImgName()%>')">
                                <%=list.get(i).getTotalView()%>
                            </td>
                            <td onclick="myFunction('<%=list.get(i).getId()%>', '<%=list.get(i).getName()%>', '<%=list.get(i).getAuthorId()%>', '<%=list.get(i).getTranslatorId()%>', '<%=list.get(i).getTotalChap()%>', '<%=list.get(i).getAppear()%>', '<%=list.get(i).getTotalView()%>', '<%=list.get(i).getStatus()%>', '<%=list.get(i).getDescription()%>', '<%=list.get(i).getImgName()%>')">
                                <%=list.get(i).getStatus()%>
                            </td>
                            <td onclick="myFunction('<%=list.get(i).getId()%>', '<%=list.get(i).getName()%>', '<%=list.get(i).getAuthorId()%>', '<%=list.get(i).getTranslatorId()%>', '<%=list.get(i).getTotalChap()%>', '<%=list.get(i).getAppear()%>', '<%=list.get(i).getTotalView()%>', '<%=list.get(i).getStatus()%>', '<%=list.get(i).getDescription()%>', '<%=list.get(i).getImgName()%>')">
                                <img src="asset/img/book/\<%=list.get(i).getImgName()%>" alt="" width="180px" />
                            </td>
                            <td>
                                <a onclick="myFunction2('<%=list.get(i).getId()%>', '<%=list.get(i).getName()%>', '<%=list.get(i).getAuthorId()%>', '<%=list.get(i).getTranslatorId()%>', '<%=list.get(i).getTotalChap()%>', '<%=list.get(i).getAppear()%>', '<%=list.get(i).getTotalView()%>', '<%=list.get(i).getStatus()%>', '<%=list.get(i).getDescription()%>', '<%=list.get(i).getImgName()%>')">Edit</a>
                                <a href="DeleteServlet/Book?id=<%=list.get(i).getId()%>" onclick="return confirm('Are you sure you want to delete this Book?');">Delete</a>
                            </td> 
                        </tr>
                    <div id="myModal" class="hmodal" style="text-align: left;">
                        <!-- Modal content -->
                        <div class="hmodal-content">
                            <div class="hmodal-header">
                                <span class="close">&times;</span>
                                <h2>Book</h2>
                            </div>
                            <div class="hmodal-body">
                                <p id="a"></p>
                                <p id="b"></p>
                                <p id="c"></p>
                                <p id="d"></p>
                                <p id="e"></p>
                                <p id="f"></p>
                                <p id="g"></p>
                                <p id="h"></p>
                                <p id="i"></p>
                                <p id="k"></p>
                            </div>
                            <div class="hmodal-footer">
                                <h3></h3>
                            </div>
                        </div>
                    </div>

                    <div id="myModal2" class="hmodal" style="text-align: left;">
                        <!-- Modal content -->
                        <div class="hmodal-content">
                            <div class="hmodal-header">
                                <span class="close2">&times;</span>
                                <h2>Book</h2>
                            </div>
                            <form action="EditServlet/Book">
                                <div class="hmodal-body">
                                    <p style="display: inline;">ID: </p><input id="a2" name="id" readonly="">
                                    <p style="display: block;"></p>
                                    <p style="display: inline;">Name: </p><input id="b2" name="name">
                                    <p style="display: block;"></p>
                                    <p style="display: inline;">Author ID: </p><input id="c2" name="aid">
                                    <p style="display: block;"></p>
                                    <p style="display: inline;">Translator ID: </p><input id="d2" name="tid">
                                    <p style="display: block;"></p>
                                    <p style="display: inline;">Total Chapter: </p><input id="e2" name="total">
                                    <p style="display: block;"></p>
                                    <p style="display: inline;">Appear: </p><input id="f2" name="appear">
                                    <p style="display: block;"></p>
                                    <p style="display: inline;">Total View: </p><input id="g2" name="view">
                                    <p style="display: block;"></p>
                                    <p style="display: inline;">Status: </p><input id="h2" name="status">
                                    <p style="display: block;"></p>
                                    <p style="display: inline;">Description: </p><textarea id="i2" name="description" style="width: 50%; height: 100px;"> </textarea>
                                    <p style="display: block;"></p>
                                    <p style="display: inline;">Image Name: </p><input id="k2"  name="img">
                                    <p></p>
                                </div>
                                <div class="hmodal-footer">
                                    <button class="btn btn-outline-dark" type="submit" id="savebtn">Save</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <%}%>
                    </tbody>
                </table>
                <script>
// Get the modal
                    var modal = document.getElementById("myModal");
                    var modal2 = document.getElementById("myModal2");

// Get the <span> element that closes the modal
                    var span = document.getElementsByClassName("close")[0];
                    var span2 = document.getElementsByClassName("close2")[0];

                    var btn = document.getElementById("savebtn");

// When the user clicks the button, open the modal 
                    function myFunction(a, b, c, d, e, f, g, h, i, k) {
                        modal.style.display = "block";
                        document.getElementById("a").innerHTML = "BookID: " + a;
                        document.getElementById("b").innerHTML = "Name: " + b;
                        document.getElementById("c").innerHTML = "AuthorID: " + c;
                        document.getElementById("d").innerHTML = "TranslatorID: " + d;
                        document.getElementById("e").innerHTML = "TotalChap: " + e;
                        document.getElementById("f").innerHTML = "Appear: " + f;
                        document.getElementById("g").innerHTML = "TotalView: " + g;
                        document.getElementById("h").innerHTML = "Status: " + h;
                        document.getElementById("i").innerHTML = "Description: " + i;
                        document.getElementById("k").innerHTML = "ImgName: " + k;

                    }

                    function myFunction2(a, b, c, d, e, f, g, h, i, k) {
                        modal2.style.display = "block";
                        document.getElementById("a2").value = a;
                        document.getElementById("b2").value = b;
                        document.getElementById("c2").value = c;
                        document.getElementById("d2").value = d;
                        document.getElementById("e2").value = e;
                        document.getElementById("f2").value = f;
                        document.getElementById("g2").value = g;
                        document.getElementById("h2").value = h;
                        document.getElementById("i2").value = i;
                        document.getElementById("k2").value = k;
                    }

// When the user clicks on <span> (x), close the modal
                    span.onclick = function () {
                        modal.style.display = "none";
                    }

                    span2.onclick = function () {
                        modal2.style.display = "none";
                    }

                    btn.onclick = function () {
                        modal2.style.display = "none";
                    }

// When the user clicks anywhere outside of the modal, close it
                    window.onclick = function (event) {
                        if (event.target == modal || event.target == modal2) {
                            modal.style.display = "none";
                            modal2.style.display = "none";
                        }
                    }

                </script>
                <%} else if (type.contains("Translator")) {
                    List<Translator> list = null;
                    if (type.equals("Translator")) {
                        list = (List<Translator>) session.getAttribute("RequestList");
                %><h2>Translator Managerment</h2><%
                } else {
                    list = (List<Translator>) session.getAttribute("result");
                %><h2>Translator Search</h2><%
                    }
                %>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th scope="col">Translator ID</th>
                            <th scope="col">Name</th>
                            <th scope="col">Link Fanpage</th>
                            <th scope="col">Donation Account</th>
                            <th scope="col">Img Name</th>
                            <th scope="col">Status</th>
                        </tr>
                    <tbody>
                        <% for (int i = 0; i < list.size(); i++) {
                        %>
                        <tr>
                            <th scope="row" onclick="myFunction('<%=list.get(i).getTranslatorId()%>', '<%=list.get(i).getName()%>', '<%=list.get(i).getLinkFanpage()%>', '<%=list.get(i).getDonationAccount()%>', '<%=list.get(i).getImgName()%>', '<%=list.get(i).isStatus()%>')">
                                <%=list.get(i).getTranslatorId()%>
                                </td> 
                            <td onclick="myFunction('<%=list.get(i).getTranslatorId()%>', '<%=list.get(i).getName()%>', '<%=list.get(i).getLinkFanpage()%>', '<%=list.get(i).getDonationAccount()%>', '<%=list.get(i).getImgName()%>', '<%=list.get(i).isStatus()%>')">
                                <%=list.get(i).getName()%>
                            </td>
                            <td onclick="myFunction('<%=list.get(i).getTranslatorId()%>', '<%=list.get(i).getName()%>', '<%=list.get(i).getLinkFanpage()%>', '<%=list.get(i).getDonationAccount()%>', '<%=list.get(i).getImgName()%>', '<%=list.get(i).isStatus()%>')">
                                <%=list.get(i).getLinkFanpage()%>
                            </td>                
                            <td onclick="myFunction('<%=list.get(i).getTranslatorId()%>', '<%=list.get(i).getName()%>', '<%=list.get(i).getLinkFanpage()%>', '<%=list.get(i).getDonationAccount()%>', '<%=list.get(i).getImgName()%>', '<%=list.get(i).isStatus()%>')">
                                <%=list.get(i).getDonationAccount()%>
                            </td>
                            <td onclick="myFunction('<%=list.get(i).getTranslatorId()%>', '<%=list.get(i).getName()%>', '<%=list.get(i).getLinkFanpage()%>', '<%=list.get(i).getDonationAccount()%>', '<%=list.get(i).getImgName()%>', '<%=list.get(i).isStatus()%>')">
                                <%=list.get(i).getImgName()%>
                            </td>
                            <td onclick="myFunction('<%=list.get(i).getTranslatorId()%>', '<%=list.get(i).getName()%>', '<%=list.get(i).getLinkFanpage()%>', '<%=list.get(i).getDonationAccount()%>', '<%=list.get(i).getImgName()%>', '<%=list.get(i).isStatus()%>')">
                                <%=list.get(i).isStatus()%>
                            </td>
                            <td>
                                <a onclick="myFunction2('<%=list.get(i).getTranslatorId()%>', '<%=list.get(i).getName()%>', '<%=list.get(i).getLinkFanpage()%>', '<%=list.get(i).getDonationAccount()%>', '<%=list.get(i).getImgName()%>', '<%=list.get(i).isStatus()%>')">Edit</a>
                                <a href="DeleteServlet/Translator?id=<%=list.get(i).getTranslatorId()%>" onclick="return confirm('Are you sure you want to delete this Translator?');">Delete</a>
                            </td> 
                        </tr>
                    <div id="myModal" class="hmodal" style="text-align: left;">
                        <!-- Modal content -->
                        <div class="hmodal-content">
                            <div class="hmodal-header">
                                <span class="close">&times;</span>
                                <h2>Translator</h2>
                            </div>
                            <div class="hmodal-body">
                                <p style="display: inline;">ID: </p><p id="a"></p>
                                <p style="display: block;"></p>
                                <p style="display: inline;">Name: </p><p id="b"></p>
                                <p style="display: block;"></p>
                                <p style="display: inline;">Link Fanpage: </p><p id="c"></p>
                                <p style="display: block;"></p>
                                <p style="display: inline;">Donation Account: </p><p id="d"></p>
                                <p style="display: block;"></p>
                                <p style="display: inline;">Image Name: </p><p id="e"></p>
                                <p style="display: block;"></p>
                                <p style="display: inline;">Status: </p><p id="f"></p>
                                <p></p>
                            </div>
                            <div class="hmodal-footer">
                                <h3></h3>
                            </div>
                        </div>
                    </div>

                    <div id="myModal2" class="hmodal" style="text-align: left;">
                        <!-- Modal content -->
                        <div class="hmodal-content">
                            <div class="hmodal-header">
                                <span class="close2">&times;</span>
                                <h2>Translator</h2>
                            </div>
                            <form action="EditServlet/Translator">
                                <div class="hmodal-body">
                                    <p style="display: inline;">ID: </p><input id="a2" name="id" readonly="">
                                    <p style="display: block;"></p>
                                    <p style="display: inline;">Name: </p><input id="b2" name="name">
                                    <p style="display: block;"></p>
                                    <p style="display: inline;">Link Fanpage: </p><input id="c2" name="link">
                                    <p style="display: block;"></p>
                                    <p style="display: inline;">Donation Account: </p><input id="d2" name="donate">
                                    <p style="display: block;"></p>
                                    <p style="display: inline;">Image Name: </p><input id="e2" name="img">
                                    <p style="display: block;"></p>
                                    <p style="display: inline;">Status: </p><input id="f2"  name="status">
                                    <p></p>
                                </div>
                                <div class="hmodal-footer">
                                    <button class="btn btn-outline-dark" type="submit" id="savebtn">Save</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <%}%>
                    </tbody>
                </table>
                <script>
// Get the modal
                    var modal = document.getElementById("myModal");
                    var modal2 = document.getElementById("myModal2");

// Get the <span> element that closes the modal
                    var span = document.getElementsByClassName("close")[0];
                    var span2 = document.getElementsByClassName("close2")[0];

                    var btn = document.getElementById("savebtn");

// When the user clicks the button, open the modal 
                    function myFunction(a, b, c, d, e, f) {
                        modal.style.display = "block";
                        document.getElementById("a").innerHTML = "ID: " + a;
                        document.getElementById("b").innerHTML = "Name: " + b;
                        document.getElementById("c").innerHTML = "Link Fanpage: " + c;
                        document.getElementById("d").innerHTML = "Donation Account: " + d;
                        document.getElementById("e").innerHTML = "Image Name: " + e;
                        document.getElementById("f").innerHTML = "Status: " + f;
                    }

                    function myFunction2(a, b, c, d, e, f) {
                        modal2.style.display = "block";
                        document.getElementById("a2").value = a;
                        document.getElementById("b2").value = b;
                        document.getElementById("c2").value = c;
                        document.getElementById("d2").value = d;
                        document.getElementById("e2").value = e;
                        document.getElementById("f2").value = f;
                    }


// When the user clicks on <span> (x), close the modal
                    span.onclick = function () {
                        modal.style.display = "none";
                    }

                    span2.onclick = function () {
                        modal2.style.display = "none";
                    }

                    btn.onclick = function () {
                        modal2.style.display = "none";
                    }

// When the user clicks anywhere outside of the modal, close it
                    window.onclick = function (event) {
                        if (event.target == modal || event.target == modal2) {
                            modal.style.display = "none";
                            modal2.style.display = "none";
                        }
                    }
                </script>
                <%}%>
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