<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>幻风阅读会员注册页面</title>
	<%@ include file="/pages/common/head.jsp"%>
    <style type="text/css">
        .login_form{
            height:440px;
            margin-top: 30px;
        }
        #sub_btn{
            margin-top: 10px;
        }
    </style>
    <script type="text/javascript">
        $(function () { //加载后
        	$("#username").blur(function () {
				//获取用户名
				var user = this.value;
				var pattUser = /^\w{5,12}$/;
				if( !pattUser.test(user) ) {    //用户名不合法
					$("span.errorMsg").html("用户名须是5-12位的字母数字下划线！");
				} else {
					//判断用户名是否已经存在
					$.getJSON("${basePath}userServlet","action=ajaxCheckName&username="+user,function (data) {
						if(data.existsUsername) {
							$("span.errorMsg").html("用户名已存在");
						} else {
							$("span.errorMsg").html("用户名可用");
						}
					});
				}
			});
        	//点击验证码
			$("#code_pic").click(function () {
				this.src = "${basePath}kaptcha.jpg?d=" + new Date();
			});
            //注册点击
            $("#sub_btn").click(function () {
                //验证密码，6~18位，必须同时包含数字和字母的字符串
                var pswd = $("#password").val();
                var pattPswd = /^(?=.*[0-9])(?=.*[a-zA-Z])(.{6,18})$/;
                if(!pattPswd.test(pswd)) {  //密码不合法
                    $("span.errorMsg").html("密码须是6-18位且包含字母和数字！");
                    return false;
                }
                //验证确认密码
                var repPswd = $("#repwd").val();
                if(pswd != repPswd) {  //确认密码不合法
                    $("span.errorMsg").html("两次密码不一致！");
                    return false;
                }
                //验证电子邮箱
                var email = $("#email").val();
                var pattEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
                if(!pattEmail.test(email)) {  //邮箱不合法
                    $("span.errorMsg").html("邮箱不合法！");
                    return false;
                }
                //验证码不为空
                var code = $("#code").val();
                code = code.trim(); //修剪前后空格
                if(code == null || code == "") {    //验证码为空
                    $("span.errorMsg").html("验证码不能为空！");
                    return false;
                }
                $("span.errorMsg").html("");    //都合法，则错误框不显示
            });
        })

    </script>
    <script src="static/script/jquery-1.7.2.js"></script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.png">
            <span style="font-size: 50px; margin-left: 20px">幻风书城</span>
		</div>
			<div class="login_banner">
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册</h1>
								<span class="errorMsg">
									${requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username"
										value="${requestScope.username}"/><br /><br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password"/><br /><br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd"/><br /><br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email"
										value="${requestScope.email}"/><br /><br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 150px" placeholder="请输入验证码" autocomplete="off" tabindex="1" name="vercode" id="vercode"/>
									<img id="code_pic" alt="" src="kaptcha.jpg" style="width: 80px; height: 30px; float: right; margin-right: 40px;">
									<br /><br />
									<input type="submit" value="注册" id="sub_btn" />
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		<%@ include file="/pages/common/tail.jsp"%>
</body>
</html>
