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
      <div class="register-wrapper">
        <img src="asset/img/body/naruto.png" id="naruto" />
        <div class="register-section">
          <div class="register-image"></div>
          <div class="register-content">
            <h2>BROMICS'S TERRITORY</h2>
            <form action="RegisterServlet" method="get">
              <div class="input-div one focus">
                <div class="i">
                  <i class="fa fa-user" style="font-size: 24px"></i>
                </div>
                <div>
                  <h5>Username</h5>
                  <input
                    type="text"
                    class="input"
                    name="name"
                    placeholder="Username"
                  />
                </div>
              </div>
              <div class="input-div two focus">
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
                  />
                </div>
              </div>
              <div class="input-div three focus">
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
                  />
                </div>
              </div>
              <div class="input-div four focus">
                <div class="i">
                  <i class="fa fa-lock" style="font-size: 24px"></i>
                </div>
                <div>
                  <h5>Password</h5>
                  <input
                    type="password"
                    class="input"
                    name="rePassword"
                    placeholder="Password Confirm"
                  />
                </div>
              </div>
              <input type="submit" class="btnn2" value="Register" />
              <span class="create"
                >Already have Account?<a href="login2.jsp">Login</a>
              </span>
            </form>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
