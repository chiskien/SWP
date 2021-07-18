<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="asset/css/style.css" />
    <link rel="stylesheet" href="asset/css/bootstrap.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script src="asset/js/bootstrap.js"></script>
    <title>Login user</title>
  </head>
  <body>
    <div class="container-fluid">
      <%@include file = "navbar.jsp"%>
      <div class="login-wrapper">
        <img src="asset/img/body/luffy.png" sizes="70%" />
        <div class="login-section">
          <div class="login-image"></div>
          <div class="login-content">
            <h2>BROMICS'S TERRITORY</h2>
            <form action="LoginServlet" method="get">
              <div class="input-div one focus">
                <div class="i">
                  <i class="fa fa-user" style="font-size: 24px"></i>
                </div>
                <div>
                  <h5>Email</h5>
                  <input
                    type="text"
                    class="input"
                    name="email"
                    placeholder="Email"
                    value="${requestScope.email}"
                  />
                </div>
              </div>
              <div class="input-div two focus">
                <div class="i">
                  <i class="fa fa-lock" style="font-size: 24px"></i>
                </div>
                <div>
                  <h5>Password</h5>
                  <input
                    type="password"
                    class="input"
                    name="password"
                    placeholder="Password"
                    value="${requestScope.password}"
                  />
                </div>
              </div>
              <a href="#" class="forgot">Forgot Password</a>
              <input type="checkbox" name="remember" value="remember" />
              <span class="custom-checkbox"> Remember me </span>
              <span class="create" style="color: red"
                >${requestScope.message}</span
              >
              <input type="submit" class="btnn" value="Login" />
              <span class="create"
                >Don't have Account?<a href="Register.jsp">Register</a>
              </span>
            </form>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
