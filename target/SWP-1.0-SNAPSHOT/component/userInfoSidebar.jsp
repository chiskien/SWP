<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="side-bar">
  <div class="avatar-profile">
    <img src="asset/img/body/undraw_profile_pic_ic5t.svg" alt="" />
    <h3>${sessionScope.user}</h3>
  </div>
  <div class="management">
    <div class="user-action-menu">
      <ul>
        <li>
          <a href="UserProfileServlet?accountId=${sessionScope.accountId}"
            ><i class="fa fa-home"></i> Home</a
          >
        </li>
        <li>
          <a href="UserBookMarkServlet?accountId=${sessionScope.accountId}">
            <i class="fa fa-bookmark"></i> Book Mark</a
          >
        </li>
        <li>
          <a href="UserHistoryServlet?accountId=${sessionScope.accountId}"
            ><i class="fa fa-history"></i> History</a
          >
        </li>
        <li>
          <a href="UserFollowServlet?accountId=${sessionScope.accountId}"
            ><i class="fa fa-heart"></i> Following</a
          >
        </li>
        <li>
          <a href=""><i class="fa fa-exclamation-triangle"></i> Report</a>
        </li>
        <li><a href="">Request</a></li>
      </ul>
    </div>
  </div>
</div>
