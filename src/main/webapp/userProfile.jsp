<%@page import="entity.Category"%>
<%@page import="java.util.List"%>
<%@page import="dao.CategoryDao"%>
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
      <% List<Category> list = new CategoryDao().getAll();
         request.setAttribute("lsCategory", list);
       %>
    <div class="container-fluid">
      <%@include file = "navbar.jsp"%>
      <main class="main-background">
        <div class="user-profile-wrapper">
            <%@include file="component/userInfoSidebar.jsp" %>
          <div class="user-data-wrapper">
            <div class="user-data-content">
              <div class="user-data-content-header">
              <span>Profile</span>
              </div>
              <div class="container" style="margin-top: 20px">
                <form class="row" action="UpdateProfileServlet">
                  <div class="col-md-6">
                    <div class="form-group">
                      <label for="username">UserName</label>
                      <input
                        class="form-control"
                        type="text"
                        id="username"
                        name="name"
                        value="${requestScope.account.name}"
                        required=""
                      />
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="form-group">
                      <label for="email">E-mail Address</label>
                      <input
                        class="form-control"
                        type="email"
                        id="email"
                        name="email"
                        value="${requestScope.account.email}"
                      />
                    </div>
                  </div>

                  <div class="col-md-6">
                    <div class="form-group">
                      <label for="account-pass">New Password</label>
                      <input
                        class="form-control"
                        name="password"
                        type="password"
                        id="account-pass"
                        value="${requestScope.account.password}"
                      />
                    </div>
                  </div>
                  
                  
                  <div class="container" style="height: 100px">
                    <textarea
                      name=""
                      id=""
                      style="width: 100%; height: 100%"
                    ></textarea>
                  </div>
                  <div class="col-12">
                    <button class="btn btn-style-1 btn-primary" type="submit">
                      Update Profile
                    </button>
                      <span>${requestScope.accountMessage}</span>
                  </div>
                </form>
                <div class="row statistic">
                  <div class="col-md-6 left-block"></div>
                  <div class="col-md-6 right-block"></div>
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
