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
                <span>History</span>
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
                      placeholder="Search history"
                      style="background-color: #ecdccb; color: #966905"
                    />
                    <button class="input-group-addon" type="submit">
                      <i class="fa fa-search" style="font-size: 27px"></i>
                    </button>
                  </div>
                </form>
              </div>
              <div class="container">
                <div class="row">
                  <c:forEach items="${requestScope.data}" var="book">
                    <div class="card2">
                      <!--Card demo =))) đừng xóa cái này-->
                      <div class="profile">
                        <img
                          src="asset/img/book/${book.imgName}"
                          class="top"
                          alt="${book.name}"
                        />
                        <div class="delete">
                          <a
                            href="UserDeleteHistoryServlet?bookId=${book.id}&accountId=${sessionScope.accountId}"
                            style="color: white"
                            ><i class="fa fa-close"></i
                          ></a>
                        </div>
                        <div class="bottom">
                          <div class="book-name">
                            <h5>
                              <a href="BookDetailServlet?id=${book.id}"
                                >${book.name}</a
                              >
                            </h5>
                          </div>
                          <a href="BookDetailServlet?id=${book.id}">
                            <button id="mybutton" class="book-action2 b3">
                              View Detail
                            </button>
                          </a>
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
