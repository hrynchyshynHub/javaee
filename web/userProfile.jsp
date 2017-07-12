<%--
  Created by IntelliJ IDEA.
  User: ivan.hrynchyshyn
  Date: 03.07.2017
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Bootstrap 101 Template</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        *{
            padding: 0;
            margin:0;
        }
        .wrapper{
            background: #eee;
            border: 1px solid #999;
            width: 1150px;
            height: 70px;
        }
        .wrapper textarea{
            background: whitesmoke;
            border:none;
            width:100%;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            resize: none;
        }
        .wrapper textarea:focus{
            outline: none;
        }
        .controls{
            background: whitesmoke;
            text-align: right;
            margin-top: -6px;

        }
        .mybtn{
            height: 50px;
            font-weight: bold;
            color: whitesmoke;
            border-width: 1px 0 0 1px;
            margin-top: 0;
        }
        .post{
            height: auto;
            border: 1px solid #b2dba1;
            margin-top: 5px;
        }
    </style>
</head>
<body>
<div class="container">

    <h1 class="center-block">Welcome user ${user.username}</h1>
    <div class="wrapper">
        <form action="addPostToUser" method="post">
        <textarea  name = "postDescription" cols="20" rows="3"></textarea>
            <input type="hidden" value="${user.username}" name="username">
        <div class="controls">
            <button class="btn btn-success mybtn">Post</button>
        </div>
        </form>
    </div>
    <br>
     <h1>Posts</h1>
    <% if(request.getAttribute("error") != null) {
        out.print("<div class=\"alert alert-danger fade in\" > <a href = \"#\" class=\"close\" data - dismiss = \"alert\">&times;</a >" +
                "<strong > Error ! </strong >" + request.getAttribute("error") + "</div>");
    }
    %>
    <c:forEach var = "post" items="${user.posts}">
        <div class="post">
        <h4>Post #${post.id}</h4>
        <h2>${post.description}</h2>
        </div>
    </c:forEach>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $(".close").click(function(){
            $(".alert").hide(500);
        });
    });
</script>
</body>
</html>
