<%@page import="dao.TranslatorDao"%>
<%@page import="dao.Thai.TAccountDao"%>
<%@page import="entity.Translator"%>
<%@page import="entity.Account"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="dao.AccountDao"%>
<%@page import="java.sql.Date"%>
<%@page import="entity.Day_View"%>
<%@page import="entity.Category_BookNum"%>
<%@page import="entity.Book"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0-rc.1/Chart.bundle.js"></script>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
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
    <script>
        function action(a) {
            var c = 'a-admin' + a;
            console.log(c);
            var button = document.getElementById('a-admin' + a);
            var chart = document.getElementById('admin' + a);
            button.style.backgroundColor = 'rgb(255,128,0,0.7)';
            chart.style.display = 'block';
            var i = 0;
            for (i = 1; i < 4; i++) {
                if (i != a) {
                    document.getElementById('a-admin' + i).style.backgroundColor = 'white';
                    console.log('admin' + i);
                    document.getElementById('admin' + i).style.display = 'none';
                }
            }
        }
    </script>

    <body onload="action(1)">
        <div class="wrapper">
            <!-- Sidebar  -->
            <nav id="sidebar">
                <div class="sidebar-header">
                    <a href="BookServlet"><h3>BROMICS</h3></a>
                </div>

                <ul class="list-unstyled components">
                    <li>
                        <a href="ChartController">Dashboard</a>
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
                            <button class="btn btn-outline-light" type="submit">Search</button>
                        </form>
                        <button
                            class="btn btn-light d-inline-block d-lg-none ml-auto"
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


            <div class="container" style="margin-top:100px;">
                <div class="row">
                    <div class="col-md-3">
                        <div class="list-group">

                            <div onclick="action(1)" href="#" class="list-group-item list-group-item-action" id="a-admin1">Pie Chart</div>
                            <div onclick="action(2)" href="#" class="list-group-item list-group-item-action" id="a-admin2">Graph</div>
                            <div onclick="action(3)" href="#" class="list-group-item list-group-item-action" id="a-admin3">Area Chart</div>
                        </div>
                    </div>
                    <div class="col-md-9">
                        <div class="row" id="admin1" style="display: none">
                            <div class="col-md-9">
                                <canvas id="bookChart"></canvas>
                            </div>
                        </div>
                        <div id="admin2" class="row" >
                            <div class="col-md-9">
                                <canvas id="category_BookNumChart"></canvas>
                            </div>
                        </div>
                        <div id="admin3" class="row" >
                            <div class="col-md-9">
                                <canvas id="view_DateChart"></canvas>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
            <%
                Book[] lsBook = (Book[]) request.getAttribute("lsBook");
                String[] bookName = new String[lsBook.length];
                int[] bookView = new int[lsBook.length];
                for (int i = 0; i < lsBook.length; i++) {
                    bookName[i] = lsBook[i].getName();
                    bookView[i] = lsBook[i].getTotalView();
                }

                Category_BookNum[] lsCateBook = (Category_BookNum[]) request.getAttribute("lsCategory_BookNum");
                String[] categoryName = new String[lsCateBook.length];
                int[] bookNum = new int[lsCateBook.length];
                for (int i = 0; i < lsCateBook.length; i++) {
                    categoryName[i] = lsCateBook[i].getCategory().getCategoryName();
                    bookNum[i] = lsCateBook[i].getBookNum();
                }

                Day_View[] lsDayView = (Day_View[]) request.getAttribute("lsDay_View");
                Date[] date = new Date[lsDayView.length];
                int[] views = new int[lsDayView.length];

                for (int i = 0; i < lsDayView.length; i++) {
                    date[i] = lsDayView[i].getDate();
                    views[i] = lsDayView[i].getViews();
                }
            %>

            <script>
                var arrName = new Array();
                <%
                    for (int i = 0; i < bookName.length; i++) {
                %>
                arrName[<%= i%>] = '<%=bookName[i]%>'; //Here is the latest update check it sravan .Put single quotes.
                <%}%>
                var arrView = new Array();
                <%
                    for (int i = 0; i < bookView.length; i++) {
                %>
                arrView[<%= i%>] = <%=bookView[i]%>; //Here is the latest update check it sravan .Put single quotes.
                <%}%>
                var color = '1237890abcdef';
                var arrColor = new Array();
                var i;
                for (i = 0; i < arrName.length; i++) {
                    arrColor.push("#" + Math.floor(Math.random() * color.length) + "" + Math.floor(Math.random() * color.length) + "" + Math.floor(Math.random() * color.length));
                }

                var myChart2 = document.getElementById('bookChart').getContext('2d');
                //global option
                Chart.defaults.global.defaultFontFamily = 'Lato';
                Chart.defaults.global.defaultFontSize = 18;
                Chart.defaults.global.defaultFontColor = 'green';
                var chart2 = new Chart(myChart2, {
                    type: 'pie', //bar,horizongtalBar,pie,line,doughnut,radar,polarArea
                    data: {
                        labels: arrName,
                        datasets: [{
                                label: 'Population',
                                data: arrView,
                                //                            backgroundColor:'green'
                                backgroundColor: arrColor
                                ,
                                borderWidth: 1,
                                borderColor: '#777',
                                hoverBorderWidth: 1,
                                hoverBackgroundColor: 'rgba(255, 128, 0, 0.5)'
                            }]
                    },
                    options: {
                        title: {
                            display: true,
                            text: 'Most viewed books'
                        },
                        legend: {
                            display: true,
                            position: 'right',
                            labels: {

                                backGroundColor: 'red'
                            }
                        },
                        layout: {
                            padding: {
                                left: 50,
                                right: 0,
                                botton: 0,
                                top: 0
                            }
                        },
                        tooltips: {
                            enabled: true
                        }
                    }
                });
            </script>
            <script>
                var arrCategoryName = new Array();
                <%
                    for (int i = 0; i < categoryName.length; i++) {
                %>
                arrCategoryName[<%= i%>] = '<%= categoryName[i]%>'; //Here is the latest update check it sravan .Put single quotes.
                <%}%>
                var arrBookNum = new Array();
                <%
                    for (int i = 0; i < categoryName.length; i++) {
                %>
                arrBookNum[<%= i%>] = <%= bookNum[i]%>; //Here is the latest update check it sravan .Put single quotes.
                <%}%>
                var color = '1234567890abcdef';
                var arrColor = new Array();
                var i;
                for (i = 0; i < arrBookNum.length; i++) {
                    arrColor.push("#" + Math.floor(Math.random() * color.length) + "" + Math.floor(Math.random() * color.length) + "" + Math.floor(Math.random() * color.length));
                }

                var myChart3 = document.getElementById('category_BookNumChart').getContext('2d');
                //global option
                Chart.defaults.global.defaultFontFamily = 'Lato';
                Chart.defaults.global.defaultFontSize = 18;
                Chart.defaults.global.defaultFontColor = 'green';
                var chart3 = new Chart(myChart3, {
                    type: 'horizontalBar', //bar,horizongtalBar,pie,line,doughnut,radar,polarArea
                    data: {
                        labels: arrCategoryName,
                        datasets: [{
                                data: arrBookNum,
                                //                            backgroundColor:'green'
                                backgroundColor: arrColor
                                ,
                                borderWidth: 1,
                                borderColor: '#777',
                                hoverBorderWidth: 1,
                                defaultFontSize: 20
                            }]
                    },
                    options: {
                        title: {
                            display: true,
                            text: 'Categories\'s number of books'
                        },
                        legend: {
                            display: false,
                            position: 'right',
                            labels: {

                                backGroundColor: 'red'
                            }
                        },
                        layout: {
                            padding: {
                                left: 50,
                                right: 0,
                                botton: 0,
                                top: 0
                            }
                        },
                        tooltips: {
                            enabled: true
                        }
                    }
                });
            </script>
            <script>
                var arrDate = new Array();
                <%
                    for (int i = 0; i < lsDayView.length; i++) {
                %>
                arrDate[<%= i%>] = '<%=date[i]%>'; //Here is the latest update check it sravan .Put single quotes.
                <%}%>
                var arrViewInDate = new Array();
                <%
                    for (int i = 0; i < lsDayView.length; i++) {
                %>
                arrViewInDate[<%= i%>] = <%=views[i]%>; //Here is the latest update check it sravan .Put single quotes.
                <%}%>

                var ctx = document.getElementById("view_DateChart").getContext("2d");

                var myChart = new Chart(ctx, {
                    type: 'line',
                    options: {
                        scales: {
                            xAxes: [{
                                    type: 'time',
                                    time: {
                                        unit: 'day'
                                    },
                                    ticks: {
    //                                    beginAtZero: true
                                        fontColor: 'blue'
                                    }
                                }],
                            yAxes: [{
                                    ticks: {
                                        fontColor: 'blue '
                                    }
                                }]
                        }
                    },
                    data: {
    //                    labels: ["2015-03-15T13:03:00Z", "2015-03-25T13:02:00Z", "2015-04-25T14:12:00Z"],
                        datasets: [{
                                label: 'Number of Views per Day',
                                data:
                                        [

                <% for (int i = 0; i < lsDayView.length; i++) {%>
                                            {
                                                t: arrDate[<%=i%>],
                                                y: arrViewInDate[<%=i%>]
                                            },
                <%}%>

                                        ],
                                backgroundColor: [
                                    'rgb(255,128,0,0.5)'
                                ],
                                borderColor: [
                                    'rgba(255,99,132,1)',
                                    'rgba(54, 162, 235, 1)',
                                    'rgba(255, 206, 86, 1)',
                                    'rgba(75, 192, 192, 1)',
                                    'rgba(153, 102, 255, 1)',
                                    'rgba(255, 159, 64, 1)'
                                ],
                                borderWidth: 1
                            }]
                    }
                });
            </script>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

    </body>
</html>
