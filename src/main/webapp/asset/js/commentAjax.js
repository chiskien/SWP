function loadComment() {
    var url = "/myConnectDB/CommentServletAjax";
    var xhr = new XMLHttpRequest();
    var temp = document.getElementById("temp");
    var comment = document.getElementById("comment");
    var newcomment = document.getElementById("new-comment");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            var jsons = JSON.parse(xhr.responseText);
//            newcomment.innerHTML += "<li>\n\
//<div class='comment'>\n\
//<div class='comment-account'>"+
//                    jsons.a.account.name+
//"</div>\n\
//<div class='comment-content mt-2'>"+
//                    jsons.a.content+
//"</div><div class='comment-info mt-2' >"+
//                    jsons.b.postTime+
//                    
//"</div>\n\
//</div>\n\
//</li>";
            newcomment.innerHTML ="<div class='col-md-12'>\n"+
           " <div class='delete2'>Report this m*therf*kcr</div>\n"+
           " <div class='media g-mb-30 media-comment'>\n"+
            "    <img\n"+
             "       class='d-flex g-width-50 g-height-50 rounded-circle g-mt-3 g-mr-15'\n"+
              "      src='asset/img/body/undraw_profile_pic_ic5t.svg'\n"+
               "     alt='Image Description'\n"+
               "     />\n"+
               " <div class='media-body u-shadow-v18 g-bg-secondary g-pa-30'>\n"+
                "    <div class='g-mb-15'>\n"+
                "        <h5 class='h5 g-color-gray-dark-v1 mb-0'>\n"+
                 jsons.a.account.name+
                  "      </h5>\n"+
                   "     <span class='g-color-gray-dark-v4 g-font-size-12'\n"+
                   "           >"+jsons.b.postTime+"</span\n"+
                   "     >\n"+
                   " </div>\n"+

                   " <p>"+jsons.a.content+"</p>\n"+

                   " <ul class='list-inline d-sm-flex my-0'>\n"+
                   "     <li class='list-inline-item g-mr-20'>\n"+
                    "        <a\n"+
                 " class='u-link-v5 g-color-gray-dark-v4 g-color-primary--hover'\n"+
                "  href='#!'\n"+
               " >\n"+
                "  <i class='fa fa-thumbs-up g-pos-rel g-top-1 g-mr-3'></i>\n"+
                "  178\n"+
               " </a>\n"+
            "  </li>\n"+
             " <li class='list-inline-item g-mr-20'>\n"+
               " <a\n"+
               "   class='u-link-v5 g-color-gray-dark-v4 g-color-primary--hover'\n"+
                "  href='#!'\n"+
             "   >\n"+
             "     <i class='fa fa-thumbs-down g-pos-rel g-top-1 g-mr-3'></i>\n"+
              "    34\n"+
             "   </a>\n"+
            "  </li>\n"+
            "  <li class='list-inline-item ml-auto'>\n"+
              "  <a\n"+
               "   class='u-link-v5 g-color-gray-dark-v4 g-color-primary--hover'\n"+
               "   href='#!'\n"+
            "    >\n"+
             "     <i class='fa fa-reply g-pos-rel g-top-1 g-mr-3'></i>\n"+
             "     Reply\n"+
              "  </a>\n"+
             " </li>\n"+
          "  </ul>\n"+
        "  </div>\n"+
        "</div>\n"+
      "</div>"+newcomment.innerHTML;
            console.log(jsons);
            comment.value = 'Type your comment here';
        }
    }

    xhr.open('get', url + temp.value + "&comment=" + comment.value);
    xhr.send();
}
