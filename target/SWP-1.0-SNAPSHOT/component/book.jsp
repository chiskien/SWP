
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a href="BookDetailServlet?id=${book.id}" style="text-decoration: none;color:white;">
    <div style="position:absolute;left:5px">
        <span class="badge bg-primary">${book.latestUpdate}</span>
    </div>
    <img src="asset/img/book/${book.imgName}" style="height:291px;width:194px;" class="card-img-top"  alt="?????">


    <div class="card-body">
        <h5  class="card-title" style = "text-align: center;-webkit-line-clamp: 2;
             -webkit-box-orient: vertical;
             overflow: hidden;
             display: -webkit-box;">${book.name}</h5>
        <!--<div height = "150px" class=" overflow: scroll">-->
        <!--<p class="card-text" style="overflow: scroll">${book.description}</p>-->
        <!--</div>-->
        <!--<a href="#" class="btn btn-primary">Add to cart</a>-->

    </div>
</a>
<span class="info" style="color:white;width:40vw; ">
    <h5 style = "white-space:normal;-webkit-line-clamp: 2;
        -webkit-box-orient: vertical;
        overflow: hidden;
        display: -webkit-box;color:#2E8BE8">${book.name}</h5>
    <p>${book.status}</p>
    <p>Views: ${book.totalView}</p>
    <c:forEach items="${book.allCategory}" var="cate">
        <span class="badge bg-primary">${cate.categoryName}</span>
    </c:forEach>
    <br>
    <p style="
       white-space:normal;
       overflow: hidden;
       text-overflow: ellipsis;
       display: -webkit-box;
       -webkit-box-orient: vertical;
       -webkit-line-clamp: 10;
       ">${book.description}</p>
</span>