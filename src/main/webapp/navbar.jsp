<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <div class="header-wrapper">
        <div class="left">
            <a href="BookServlet">
                <h1>BROMICS</h1>
            </a>
            <div class="menu">
                <ul>
                    <li><a href="BookServlet" class="off">Home</a></li>
                    <li class="dropdown">
                        <a href="" class="dropdown-toggle off" data-toggle="dropdown"
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
                    <li class="off">
                        <a href="BookFilterServlet" class="off">Explore</a>
                    </li>
                    <li>
                        <a onclick="toggleNotification()"
                           ><div class="btn-notification" style="position: absolute; margin-left: 17%">
                                <i class="fa fa-bell" style="font-size: 30px; color: white;"></i></div
                            ></a>
                        <div class="notifi-box" id="box">
                            <h2>Notification</h2>
                            <c:forEach items="${requestScope.lsNotification}" var = "noti">
                                <div class="notifi-item">
                                <img src="https://p.kindpng.com/picc/s/78-785827_user-profile-avatar-login-account-male-user-icon.png" alt="img">
                                <div class="text">
                                    <h4>Test Name</h4>
                                    <p>${noti.content}</p>
                                </div>
                            </div>
                            </c:forEach>
                        </div>
                    </li>
                </ul>
            </div>

        </div>
        <div class="right">


            <script>
                var box = document.getElementById('box');
                var down = false;

                function toggleNotification() {
                    if (down) {
                        box.style.height = '0px';
                        box.style.opacity = 0;
                        down = false;
                    } else {
                        box.style.height = '510px';
                        box.style.opacity = 1;
                        down = true;
                    }
                }
            </script>

            <form
                action="BookSearchServlet"
                method="GET"
                class="navbar-form form-inline"
                style="display: inline-block"
                >
                <div class="input-group search-box">
                    <input
                        type="text"
                        id="search"
                        class="form-control"
                        placeholder="Search for book"
                        name="search"
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
                                <a
                                    href="UserProfileServlet?accountId=${sessionScope.accountId}"
                                    >
                                    <i class="fa fa-user"></i> Profile</a
                                >
                            </li>
                            <li>
                                <a
                                    href="UserBookMarkServlet?accountId=${sessionScope.accountId}"
                                    >
                                    <i class="fa fa-bookmark"></i> Book Mark</a
                                >
                            </li>
                            <li>
                                <a
                                    href="UserHistoryServlet?accountId=${sessionScope.accountId}"
                                    >
                                    <i class="fa fa-history"></i> Recently</a
                                >
                            </li>
                            <li>
                                <a href="UserFollowServlet?accountId=${sessionScope.accountId}">
                                    <i class="fa fa-heart"></i> Following</a
                                >
                            </li>
                            <c:if test="${sessionScope.role==3}">
                            <li>
                                <a href="ChartController">
                                    Switch to admin</a
                                >
                            </li>
                            </c:if>
                            <c:if test="${sessionScope.role==2}">
                            <li>
                                <a href="ChartController">
                                    Switch to admin</a
                                >
                            </li>
                            </c:if>
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
    <script type="text/javascript">
                $(".off").click(function () {
                    $(this).toggleClass("on");
                });
    </script>
</header>
