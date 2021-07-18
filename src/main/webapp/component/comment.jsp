<%-- 
    Document   : comment
    Created on : Mar 15, 2021, 10:01:51 PM
    Author     : hoang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="asset/js/commentAjax.js"></script>
<style>
    .list-chapter li{
        padding: 6px 40px 6px 10px; background-color: rgba(103,104,105,.3);margin-bottom:25px;border-radius: 5px;
    }
    .list-chapter li .comment-info{
        font-size:0.85em;opacity: 0.5;
    }
</style>
<input style="display:none" id="temp" value="?bookId=${requestScope.book.id}&chapterId=${requestScope.chapter.chapterId}">
<div class="container">
    <div class="card mt-2" style="background: #212529;color:white; ">
        <div class="card-header">
            Comments
        </div>
        <div class="card-body">
            
            <c:choose>
                <c:when test="${sessionScope.user!=null}">
                    <div class="post-comment mb-3">
                        <div accept-charset="Windows-1256" method="post">
                            <div class="post-comment-content" >
                                <textarea id="comment" style="background-color: rgba(103,104,105,.3);width:100%; color:white;border:none;"  rows="5" name="comment">Type your comment here</textarea>
                            </div>
                            <div class="post-comment-submit" style="background-color: #353535; height:40px">
                                <button style="margin:3px 10px 0; color:white;background-color: #36a189;padding:2px 15px; border-radius: 5px;font-weight:700;border-width: 0px;border:none;" onclick="loadComment()">Post Comment</button>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="post-comment mb-3" style="color:white">
                        You must login to post your comment
                    </div>
                </c:otherwise>
            </c:choose>
            <ul class="list-chapter"style="list-style-type: none;padding-inline-start:0;">
                <div id ="new-comment"></div>
                <c:forEach items="${requestScope.lsComment}" var="comment">
                    <li>
                        <div class="comment">
                            <div class="comment-account">
                                ${comment.account.name}
                            </div>
                            <div class="comment-content mt-2">
                                ${comment.content}
                            </div>
                            <div class="comment-info mt-2" >
                                ${comment.getPostTime()}
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
