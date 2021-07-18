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
<%@include file = "component/navbar.jsp"%>
        <div class="container" style="margin-top:100px;">
            <div class="row">
                <div class="col-md-3">
                    <div class="list-group">

                        <div onclick="action(1)" href="#" class="list-group-item list-group-item-action" id="a-admin1">A second link item</div>
                        <div onclick="action(2)" href="#" class="list-group-item list-group-item-action" id="a-admin2">A third link item</div>
                        <div onclick="action(3)" href="#" class="list-group-item list-group-item-action" id="a-admin3">A fourth link item</div>
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
                            hoverBackgroundColor:'rgba(255, 128, 0, 0.5)'
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
                                     fontColor:'blue'
                                }
                            }],
                        yAxes:[{
                                ticks:{
                                    fontColor:'blue '
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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

    </body>
</html>
