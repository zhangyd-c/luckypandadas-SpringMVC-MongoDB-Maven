<%--
  Created by IntelliJ IDEA.
  User: yadong.zhang
  Date: 2015/12/24
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#asd").click(function () {
                var url = $("#form").attr("action");
                var loginName = $("input[name='loginName']").val();
                var passWord = $("input[name='passWord']").val();
                var code = $("input[name='securityCode']").val();
                var params = "{\"user\":{\"telephone\":\"" + loginName + "\",\"password\":\"" + passWord + "\"},\"rePassword\":\"\",\"securityCode\":\"" + code + "\"}";

                $.post(url, {"param": params}, function (data) {
                    $("#span").html(data.status + data.message + "" + data.data);
                })
            })
        })
    </script>
</head>
<body>
<form action="${tag eq 1 ? '/users/login' : '/users/signUp'}" method="post" id="form">
    <p><input type="text" name="loginName" value="12341"></p>
    <p><input type="text" name="passWord" value="1234567."></p>
    <p><input type="text" name="securityCode" value="123456"></p>
    <a id="asd" href="javascript:void(0)">提交</a>
</form>
<p><a href="/users/goLogin">登录</a>&nbsp;&nbsp;<a href="/users/goSignup">注册</a></p>
<p><a href="/users/goUpdatePassword/12341">修改密码</a>&nbsp;&nbsp;<a href="/users/goUpdateStatus/12341">修改状态</a></p>
<span style="background-color: chartreuse;width: 500px;height: 500px" id="span"></span>
</body>
</html>
