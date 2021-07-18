<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Home Page</title>
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
      <%@include file = "navbar.jsp"%>
      <div class="content">
        <div class="banner-wrap">
          <div class="container">
            <div class="carousel slide" id="main-carousel" data-ride="carousel">
              <ol class="carousel-indicators">
                <li
                  data-target="#main-carousel"
                  data-slide-to="0"
                  class="active"
                ></li>
                <li data-target="#main-carousel" data-slide-to="1"></li>
                <li data-target="#main-carousel" data-slide-to="2"></li>
                <li data-target="#main-carousel" data-slide-to="3"></li>
              </ol>
              <!-- /.carousel-indicators -->
              <div class="carousel-inner">
                <div class="carousel-item active">
                  <img
                    class="d-block imgg"
                    src="asset/img/body/attackontitan-wallpaper-1600x900.jpg"
                    alt="attack on titan"
                  />
                  <div class="carousel-caption d-none d-md-block">
                    <h3>Attact on Titan</h3>
                  </div>
                </div>
                <div class="carousel-item">
                  <img
                    class="d-block imgg"
                    src="asset/img/body/one_piece_3-wallpaper-1600x900.jpg"
                    alt=""
                  />
                  <div class="carousel-caption d-none d-md-block">
                    <h3>One Piece</h3>
                  </div>
                </div>
                <div class="carousel-item">
                  <img
                    class="d-block imgg"
                    src="asset/img/body/naruto___road_to_ninja-wallpaper-1600x900.jpg"
                    alt=""
                  />
                  <div class="carousel-caption d-none d-md-block">
                    <h3>Naruto</h3>
                  </div>
                </div>
                <div class="carousel-item">
                  <img
                    src="asset/img/body/code_geass___lelouch-wallpaper-1600x900.jpg"
                    alt=""
                    class="d-block imgg"
                  />
                  <div class="carousel-caption d-none d-md-block">
                    <h3>Code Geass</h3>
                  </div>
                </div>
              </div>
              <a
                href="#main-carousel"
                class="carousel-control-prev"
                data-slide="prev"
              >
                <span class="carousel-control-prev-icon"></span>
                <span class="sr-only" aria-hidden="true">Prev</span>
              </a>
              <a
                href="#main-carousel"
                class="carousel-control-next"
                data-slide="next"
              >
                <span class="carousel-control-next-icon"></span>
                <span class="sr-only" aria-hidden="true">Next</span>
              </a>
            </div>
          </div>
        </div>
        <div class="container">
          <div class="section-today">
            <h3>
              <i class="fa fa-bomb" style="font-size: 36px; color: red"></i>
              Most Favorite
            </h3>
            <div class="row">
              <c:forEach
                items="${requestScope.lsTopView}"
                var="book"
                varStatus="count"
              >
                <c:if test="${book.appear==0}">
                  <div class="example-1 card">
                    <div class="wrapper">
                      <img
                        src="asset/img/book/${book.imgName}"
                        width="100%"
                        height="100%"
                        alt="${book.name}"
                      />
                      <div class="date">
                        <span class="day">${book.latestUpdate}</span>
                      </div>
                      <div class="data">
                        <div class="content">
                          <a href="BookDetailServlet?id=${book.id}"
                            ><span class="author">View Detail</span></a
                          >
                          <h5 class="title">
                            <a href="BookDetailServlet?id=${book.id}"
                              >${book.name}</a
                            >
                          </h5>
                          <p class="text">${book.description}</p>
                          <label
                            for="show-menu${count.index}"
                            onclick="viewBookInfo(${book.id},${sessionScope.accountId})"
                            class="menu-button"
                            ><span></span
                          ></label>
                        </div>
                        <input type="checkbox" id="show-menu${count.index}" />
                        <ul class="menu-content">
                          <li>
                            <a
                              onclick="bookmark(${book.id},${sessionScope.accountId})"
                              id="book-bookmark${book.id}"
                              class="fa fa-bookmark-o"
                            ></a>
                          </li>
                          <li>
                            <a
                              onclick="follow(${book.id},${sessionScope.accountId})"
                              id="book-follow${book.id}"
                              class="fa fa-heart-o"
                              ><span id="total-follow${book.id}"
                                >${book.totalFollow}</span
                              ></a
                            >
                          </li>
                          <li>
                            <a href="#" class="fa fa-comment-o"
                              ><span>${book.totalComment}</span></a
                            >
                          </li>
                        </ul>
                      </div>
                    </div>
                  </div>
                </c:if>
              </c:forEach>
            </div>
          </div>
          <div class="section-email"></div>
          <div class="section-today">
            <i class="fa fa-calendar-check" style="font-size: 36px"></i>
            <h3>
              <i
                class="fa fa-calendar-check-o"
                style="font-size: 36px; color: red"
              ></i>
              Today's Comic
            </h3>
            <div class="row">
              <c:forEach
                items="${requestScope.data}"
                var="book"
                varStatus="count"
              >
                <c:if test="${book.appear==0}">
                  <div class="example-1 card">
                    <div class="wrapper">
                      <img
                        src="asset/img/book/${book.imgName}"
                        width="100%"
                        height="100%"
                        alt="${book.name}"
                      />
                      <div class="date">
                        <span class="day">${book.latestUpdate}</span>
                      </div>
                      <div class="data">
                        <div class="content">
                          <a href="BookDetailServlet?id=${book.id}"
                            ><span class="author">View Detail</span></a
                          >
                          <h5 class="title">
                            <a href="BookDetailServlet?id=${book.id}"
                              >${book.name}</a
                            >
                          </h5>
                          <p class="text">${book.description}</p>
                          <label
                            for="show-menu${count.index}"
                            onclick="viewBookInfo(${book.id},${sessionScope.accountId})"
                            class="menu-button"
                            ><span></span
                          ></label>
                        </div>
                        <input type="checkbox" id="show-menu${count.index}" />
                        <ul class="menu-content">
                          <li>
                            <a
                              onclick="bookmark(${book.id},${sessionScope.accountId})"
                              id="book-bookmark${book.id}"
                              class="fa fa-bookmark-o"
                            ></a>
                          </li>
                          <li>
                            <a
                              onclick="follow(${book.id},${sessionScope.accountId})"
                              id="book-follow${book.id}"
                              class="fa fa-heart-o"
                              ><span id="total-follow${book.id}"
                                >${book.totalFollow}</span
                              ></a
                            >
                          </li>
                          <li>
                            <a href="#" class="fa fa-comment-o"
                              ><span>${book.totalComment}</span></a
                            >
                          </li>
                        </ul>
                      </div>
                    </div>
                  </div>
                </c:if>
              </c:forEach>
            </div>
          </div>
        </div>
      </div>
      <div class="recomend"></div>
    </div>
    <script src="asset/js/bookInfo.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script src="asset/js/bootstrap.js"></script>
  </body>
</html>
