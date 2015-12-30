<%--
  Created by IntelliJ IDEA.
  User: yadong.zhang
  Date: 2015/12/29
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#status").val('${user.status}');
            var tag = '${tag}';
            $("#asd").click(function () {
                var url = $("#form").attr("action");
                var loginName = $("input[name='loginName']").val();
                var passWord = $("input[name='passWord']").val();
                var rePassWord = $("input[name='rePassWord']").val();
                var status = $("#status").val();
                var code = $("input[name='securityCode']").val();
                var params = "";
                if (tag == 1) {//status
                    params = "{\"user\":{\"telephone\":\"" + loginName + "\",\"status\":\"" + status + "\"},\"securityCode\":\"" + code + "\",\"rePassword\":\"\"}";
                } else {
                    params = "{\"user\":{\"telephone\":\"" + loginName + "\",\"password\":\"" + passWord + "\"},\"rePassword\":\"" + rePassWord + "\",\"securityCode\":\"" + code + "\"}";
                }


                $.post(url, {"param": params}, function (data) {
                    $("#span").html(data.status + data.message + "" + data.data);
                })
            })
        })
    </script>
</head>
<body>
<form action="${tag eq 2 ? '/users/updatePassword' : '/users/updateStatus'}" method="post" id="form">
    <p><input type="text" name="loginName" value="${user.telephone}"></p>
    <p><input type="password" name="passWord" value="${user.password}"></p>
    <p><input type="password" name="rePassWord" value="1234567."></p>
    <p><select name="status" id="status">
        <option value="1">正常</option>
        <option value="2">锁定</option>
        <option value="3">禁用</option>
    </select></p>
    <p><input type="text" name="securityCode" value="123456"></p>
    <a id="asd" href="javascript:void(0)">提交</a>
</form>

<p><a href="/users/goUpdatePassword/12341">修改密码</a>&nbsp;&nbsp;<a href="/users/goUpdateStatus/12341">修改状态</a></p>
<span style="background-color: chartreuse;width: 500px;height: 500px" id="span"></span>
</body>
</html>
