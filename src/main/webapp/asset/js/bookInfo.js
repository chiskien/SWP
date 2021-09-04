function follow(bookId, accountId) {

    var url = "/myConnectDB/AddFollowServlet";
    var xhr = new XMLHttpRequest();
    var add = 1;
    if (document.getElementById("book-follow" + bookId).style.color === "red") {
        add = -1;
    }
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            var jsons = JSON.parse(xhr.responseText);
            document.getElementById("total-follow" + bookId).innerHTML = jsons.follows;
            console.log(jsons);
            if (add > 0) {
                document.getElementById("book-follow" + bookId).style.color = "red";
            } else {
                document.getElementById("book-follow" + bookId).style.color = "white";
            }
        }
    }

    xhr.open('get', url + "?bookId=" + bookId + "&accountId=" + accountId + "&add=" + add);
    xhr.send();
}

function bookmark(bookId, accountId) {
    var url = "/myConnectDB/AddBookMarkServlet";
    var xhr = new XMLHttpRequest();
    var add = 1;
    if (document.getElementById("book-bookmark" + bookId).style.color == "red") {
        add = -1;
    }
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            var jsons = xhr.responseText;
            if (add > 0) {
                document.getElementById("book-bookmark" + bookId).style.color = "red";
            } else {
                document.getElementById("book-bookmark" + bookId).style.color = "white";
            }
            console.log(jsons);
        }
    }

    xhr.open('get', url + "?bookId=" + bookId + "&accountId=" + accountId + "&add=" + add);
    xhr.send();
}

function viewBookInfo(bookId, accountId) {
    console.log(bookId + " " + accountId);
    var url = "/myConnectDB/ViewBookInfoServlet";
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            var jsons = JSON.parse(xhr.responseText);
            if (jsons.isFollow) {
                document.getElementById("book-follow" + bookId).style.color = "red";
            }
            if (jsons.isBookmarked) {
                document.getElementById("book-bookmark" + bookId).style.color = "red";
            }
            console.log(jsons.isFollow);
        }
    }
    xhr.open('get', url + "?bookId=" + bookId + "&accountId=" + accountId);
    xhr.send();
}

function bookmark3(bookId, accountId, chapterId, id, isColor, isNotColor) {
    var url = "/myConnectDB/AddBookMarkServlet";
    var xhr = new XMLHttpRequest();
    var add = 1;
    var button = document.getElementById(id + bookId);
    console.log(button.style.color);
    if (button.style.color == isColor) {
        add = -1;
    }
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            var jsons = xhr.responseText;
            if (add > 0) {
                document.getElementById(id + bookId).style.color = isColor;
            } else {
                document.getElementById(id + bookId).style.color = isNotColor;
            }
            console.log(jsons);
        }
    }

    xhr.open('get', url + "?bookId=" + bookId + "&accountId=" + accountId + "&add=" + add + "&chapterId=" + chapterId);
    xhr.send();
}

function follow2(bookId, accountId, id, isColor, isNotColor) {

    var url = "/myConnectDB/AddFollowServlet";
    var xhr = new XMLHttpRequest();
    var add = 1;
    if (document.getElementById(id + bookId).style.color == "red") {
        add = -1;
    }
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            var jsons = JSON.parse(xhr.responseText);

            console.log(jsons);
            if (add > 0) {
                document.getElementById(id + bookId).style.color = isColor;
            } else {
                document.getElementById(id + bookId).style.color = isNotColor;
            }
        }
    }

    xhr.open('get', url + "?bookId=" + bookId + "&accountId=" + accountId + "&add=" + add);
    xhr.send();
}

function rate(accountId, bookId, mark) {
    var url = "/myConnectDB/UpdateRateServlet?bookId=" + bookId + "&accountId=" + accountId + "&mark=" + mark;
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            var a = xhr.responseText;
            console.log(a);
            document.getElementById("rate-mark").innerHTML = a;
        }
    }
    xhr.open('get', url);
    xhr.send();
}



