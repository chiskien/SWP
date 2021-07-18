<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="asset/css/bootstrap.css" />
    <link rel="stylesheet" href="asset/css/style.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
    <title>Profile</title>
  </head>
  <body>
    <div class="container-fluid">
      <%@include file = "navbar.jsp"%>
      <main class="main-background">
        <div class="user-profile-wrapper">
          <%@include file="component/userInfoSidebar.jsp" %>
          <div class="user-data-wrapper">
            <div class="user-data-content">
              <div class="user-data-content-header">
                <span>Following</span>
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
                      placeholder="Search following"
                      style="background-color: #ecdccb; color: #966905"
                    />
                    <button class="input-group-addon" type="submit">
                      <i class="fa fa-search" style="font-size: 27px"></i>
                    </button>
                  </div>
                </form>
              </div>
              <div class="container">
                <div class="row" style="overflow-y: auto">
                  <c:forEach items="${requestScope.data}" var="book">
                    <div class="card2">
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
                              <h5>${book.name}</h5>
                            </a>
                          </div>
                          <a href="BookDetailServlet?id=${book.id}"
                            ><button class="book-action b1">
                              View Detail
                            </button></a
                          >
                          <a
                            href="UserDeleteFollowServlet?bookId=${book.id}&accountId=${sessionScope.accountId}"
                            ><button class="book-action b2">Unfollow</button></a
                          >
                        </div>
                      </div>
                    </div>
                  </c:forEach>
                </div>
              </div>
            </div>
          </div>
        </div>
      </main>
    </div>
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script src="asset/js/bootstrap.js"></script>
  </body>
</html>
