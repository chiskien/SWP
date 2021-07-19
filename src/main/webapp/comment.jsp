<script src="asset/js/commentAjax.js"></script>
<input
  style="display: none"
  id="temp"
  value="?bookId=${requestScope.book.id}&chapterId=${requestScope.chapter.chapterId}"
/>
<div class="comment-section container">
  <div class="row">
    <div class="col-md-12">
      <c:choose>
        <c:when test="${sessionScope.user!=null}">
          <div class="post-comment mb-3">
            <div accept-charset="Windows-1256" method="post">
              <div class="post-comment-content">
                <textarea
                  id="comment"
                  style="
                    background-color: rgba(103, 104, 105, 0.3);
                    width: 100%;
                    color: black;
                    border: 1px solid gray;
                  "
                  rows="5"
                  name="comment"
                >
                                    Type your comment here</textarea
                >
              </div>
              <div
                class="post-comment-submit"
                style="background-color: #292926; height: 40px"
              >
                <button
                  style="
                    margin: 3px 10px 0;
                    color: white;
                    background-color: #36a189;
                    padding: 2px 15px;
                    border-radius: 5px;
                    font-weight: 700;
                    border-width: 0px;
                    border: none;
                  "
                  onclick="loadComment()"
                >
                  Post Comment
                </button>
              </div>
            </div>
          </div>
        </c:when>
        <c:otherwise>
          <div class="post-comment mb-3" style="color: white">
            You must login to post your comment
          </div>
        </c:otherwise>
      </c:choose>
    </div>
    <c:forEach items="${requestScope.lsComment}" var="comment">
      <div id="new-comment" class="col-md-12"></div>
      <div class="col-md-12">
        <button
          type="button"
          class="btn btn-danger dropdown-toggle delete2"
          data-toggle="dropdown"
          aria-haspopup="true"
          aria-expanded="false"
        >
          Report this comment!
        </button>
        <div class="dropdown-menu book-report">
          <div class="book-report-content" style="display: none"></div>
          <div class="dropdown-item">
            <a
              href="ReportServlet?id=${requestScope.book.id}&accountId=${sessionScope.accountId}&content=AbusiveContent&commentId=${comment.commentId}&status=1&type=comment"
            >
              Abusive content
            </a>
          </div>
          <div class="dropdown-item">
            <a
              href="ReportServlet?id=${requestScope.book.id}&accountId=${sessionScope.accountId}&content=Plagiarism&commentId=${comment.commentId}&status=1&type=comment"
            >
              Plagiarism
            </a>
          </div>
          <div class="dropdown-item">
            <a
              href="ReportServlet?id=${requestScope.book.id}&accountId=${sessionScope.accountId}&content=EroticContent&commentId=${comment.commentId}&status=1&type=comment"
            >
              Erotic content
            </a>
          </div>
          <div class="dropdown-item">
            <a
              href="ReportServlet?id=${requestScope.book.id}&accountId=${sessionScope.accountId}&content=HateSpeech&commentId=${comment.commentId}&status=1&type=comment"
            >
              Hate speech
            </a>
          </div>
        </div>
        <div class="media g-mb-30 media-comment">
          <img
            class="d-flex g-width-50 g-height-50 rounded-circle g-mt-3 g-mr-15"
            src="asset/img/body/undraw_profile_pic_ic5t.svg"
            alt="Image Description"
          />
          <div class="media-body u-shadow-v18 g-bg-secondary g-pa-30">
            <div class="g-mb-15">
              <h5 class="h5 g-color-gray-dark-v1 mb-0">
                ${comment.account.name}
              </h5>
              <span class="g-color-gray-dark-v4 g-font-size-12"
                >${comment.getPostTime()}</span
              >
            </div>

            <p>${comment.content}</p>

            <ul class="list-inline d-sm-flex my-0">
              <li class="list-inline-item g-mr-20">
                <a
                  class="u-link-v5 g-color-gray-dark-v4 g-color-primary--hover"
                  href="#!"
                >
                  <i class="fa fa-thumbs-up g-pos-rel g-top-1 g-mr-3"></i>
                  178
                </a>
              </li>
              <li class="list-inline-item g-mr-20">
                <a
                  class="u-link-v5 g-color-gray-dark-v4 g-color-primary--hover"
                  href="#!"
                >
                  <i class="fa fa-thumbs-down g-pos-rel g-top-1 g-mr-3"></i>
                  34
                </a>
              </li>
              <li class="list-inline-item ml-auto">
                <a
                  class="u-link-v5 g-color-gray-dark-v4 g-color-primary--hover"
                  href="#!"
                >
                  <i class="fa fa-reply g-pos-rel g-top-1 g-mr-3"></i>
                  Reply
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </c:forEach>
    <style type="text/css">
      @media (min-width: 0) {
        .g-mr-15 {
          margin-right: 1.07143rem !important;
        }
      }

      @media (min-width: 0) {
        .g-mt-3 {
          margin-top: 0.21429rem !important;
        }
      }

      .g-height-50 {
        height: 50px;
      }

      .g-width-50 {
        width: 50px !important;
      }

      @media (min-width: 0) {
        .g-pa-30 {
          padding: 2.14286rem !important;
        }
      }

      .g-bg-secondary {
        background-color: #fafafa !important;
      }

      .u-shadow-v18 {
        box-shadow: 0 5px 10px -6px rgba(0, 0, 0, 0.15);
        border: 0.5px solid gray;
      }

      .g-color-gray-dark-v4 {
        color: #777 !important;
      }

      .g-font-size-12 {
        font-size: 0.85714rem !important;
      }

      .media-comment {
        margin-top: 20px;
      }
    </style>
  </div>
</div>
