<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta name="keywords" content="Web前端视频教程,大数据视频教程,HTML5视频教程,UI视频教程,PHP视频教程,java视频教程,python基础教程">
    <meta name="description"
          content="好先生教育在线课程视频,为您提供java,python,HTML5,UI,PHP,大数据等学科经典视频教程在线浏览学习,精细化知识点解析,深入浅出,想学不会都难,好先生教育,学习成就梦想！">
    <meta name="author" content="">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/css.css">
    <title>在线公开课-好先生教育|java|大数据|HTML5|python|UI|PHP视频教程</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/gVerify.js"></script>
    <script type="text/javascript">

        $(function () {

            //div 两个哪个显示呢？
            if (null != "${sessionScope.userAccount}" && "${sessionScope.userAccount}" != "") {
                $("#regBlock").css("display", "none");
                $("#userBlock").css("display", "block");
            } else {
                $("#regBlock").css("display", "block");
                $("#userBlock").css("display", "none");
            }

        });

        function addFavorite2() {
            var url = window.location;
            var title = document.title;
            var ua = navigator.userAgent.toLowerCase();
            if (ua.indexOf("360se") > -1) {
                alert("由于360浏览器功能限制，请按 Ctrl+D 手动收藏！");
            } else if (ua.indexOf("msie 8") > -1) {
                window.external.AddToFavoritesBar(url, title); //IE8
            } else if (document.all) {
                try {
                    window.external.addFavorite(url, title);
                } catch (e) {
                    alert('您的浏览器不支持,请按 Ctrl+D 手动收藏!');
                }
            } else if (window.sidebar) {
                window.sidebar.addPanel(title, url, "");
            } else {
                alert('您的浏览器不支持,请按 Ctrl+D 手动收藏!');
            }
        }
    </script>
    <style>


        #pjax-container {
            padding: 20px;
            position: relative;
            min-height: 900px;
            background-color: #eeeeee;

        }

        .timeline {
            background-color: #ffffff;
            width: 800px;
            margin-left: 28%;
            padding: 30px;
        }
        .nav2 {
            position: fixed;
        }

        .search_div{
            float: left;
            margin-top: 30px;
            margin-right: 10px;
        }
        .input_search{
            height: 35px;
            line-height: 1.3;
            border-width: 1px;
            border-style: solid;
            background-color: #fff;
            border-radius: 2px;
            border-color: #D2D2D2;

        }

    </style>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script type="application/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>

<body class="w100">
<header>
    <div class="container">
        <span>欢迎来到好先生教育！</span>


        <div id="regBlock" style="display:none;float:right">
            <a href="javascript:;" id="reg_open"><img src="${pageContext.request.contextPath}/img/we.png">注册</a>
            <a href="javascript:;" id="login_open"><img src="${pageContext.request.contextPath}/img/we.png">登录</a>
        </div>

        <div id="userBlock" style="display:none;float:right">

            <a href="javascript:;" id="loginout">退出</a>
            <a href="${pageContext.request.contextPath}/user/showMyProfile" id="account">${sessionScope.userAccount}</a>
        </div>

        <a onclick="JavaScript:addFavorite2()"><img src="${pageContext.request.contextPath}/img/sc.png"
                                                    draggable="false">加入收藏</a>
        <a onclick="pyRegisterCvt()" target="_blank"
           href="http://wpa.qq.com/msgrd?v=3&uin=666666&site=qq&menu=yes"><img
                src="${pageContext.request.contextPath}/img/we.png" draggable="false">联系我们</a>
        <a class="color_e4"><img src="${pageContext.request.contextPath}/img/phone.png" draggable="false"> 4008-800-8800&#x3000;&#x3000;4008-800-8800</a>

    </div>
</header>
<nav class="w100">
    <div class="container">
        <img src="${pageContext.request.contextPath}/img/logo.png" alt="好先生教育的logo"
             onclick="location.href='${pageContext.request.contextPath}/index.jsp'" draggable="false">
        <ul class="text_13 f_right">

            <div class="search_div">
                <form onkeypress="return event.keyCode != 13;" action="${pageContext.request.contextPath}/video/searchVideo" id="search_Form">
                    <input class="input_search" id="search_Title" type="text" name="title" placeholder="输入要查找的课程名称">
                    <button id="search_Button" onclick="search_Click()" type="button" class="layui-btn layui-btn-sm" style="background-color: #b8b0fd;">
                        <i class="layui-icon">&#xe615;</i> 搜索</button>
                </form>
            </div>


            <li>
                <a href="${pageContext.request.contextPath}/index.jsp">首页</a>
            </li>
            <li class="nav_down">
                高端课程<img src="${pageContext.request.contextPath}/img/nav_down.png" alt="" draggable="false">
                <ul id="nav_down" class="t_center">
                    <c:forEach items="${subjectList}" var="subject">
                        <li>
                            <a href="${pageContext.request.contextPath}/course/course/${subject.id}">${subject.subjectName}</a>
                        </li>
                    </c:forEach>
                </ul>
            </li>
            <li>
                <a href="#">在线公开课</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/goodSir/showSpeakers">专家师资</a>
            </li>

            <li id="gkk" class="nav_choose"><a href="${pageContext.request.contextPath}/goodSir/news">好先生新闻</a></li>
            <li class="nav_last">
                <a href="${pageContext.request.contextPath}/goodSir/aboutUs">关于好先生</a>
            </li>
        </ul>
    </div>
</nav>

<div id="pjax-container">

    <div class="nav2">
        <ul class="layui-nav layui-nav-tree " style="background-color: #60686b;height: 450px;top: 20%"
            lay-filter="test">
            <!-- 侧边导航: <ul class="layui-nav layui-nav-tree layui-nav-side"> -->
            <li class="layui-nav-item"><a href="${pageContext.request.contextPath}">首页</a></li>
            <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/goodSir/showSpeakers">专家师资</a></li>
            <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/goodSir/news">好先生新闻</a></li>
            <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/goodSir/aboutUs">关于好先生</a></li>
            <li class="layui-nav-item"><a href=""> 赶快加入我们吧！</a><img
                    src="${pageContext.request.contextPath}/img/erweima.png" height="200px" width="200px"></li>
        </ul>
    </div>
    <div class="timeline">
        <ul class="layui-timeline">
            <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                <div class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">8月18日</h3>
                    <p>
                        layui 2.0 的一切准备工作似乎都已到位。发布之弦，一触即发。
                        <br>不枉近百个日日夜夜与之为伴。因小而大，因弱而强。
                        <br>无论它能走多远，抑或如何支撑？至少我曾倾注全心，无怨无悔 <i class="layui-icon"></i>
                    </p>
                </div>
            </li>
            <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                <div class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">8月16日</h3>
                    <p>杜甫的思想核心是儒家的仁政思想，他有“<em>致君尧舜上，再使风俗淳</em>”的宏伟抱负。个人最爱的名篇有：</p>
                    <ul>
                        <li>《登高》</li>
                        <li>《茅屋为秋风所破歌》</li>
                    </ul>
                </div>
            </li>
            <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                <div class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">8月15日</h3>
                    <p>
                        中国人民抗日战争胜利72周年
                        <br>常常在想，尽管对这个国家有这样那样的抱怨，但我们的确生在了最好的时代
                        <br>铭记、感恩
                        <br>所有为中华民族浴血奋战的英雄将士
                        <br>永垂不朽
                    </p>
                </div>
            </li>
            <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                <div class="layui-timeline-content layui-text">
                    <div class="layui-timeline-title">过去</div>
                </div>
            </li>
        </ul>
    </div>
</div>

<!--页脚-->
<footer>
    <ul>
        <li>
            <img style="width: 200px;height: 60px" src="${pageContext.request.contextPath}/img/footer_logo.png" alt="" draggable="false">
        </li>
        <li class="mt25">
            <h3>校区地址</h3>
            <ul>
                <li>地址<br>河南省郑州市前程大道ZTBU学院</li>

            </ul>
        </li>
        <li class="mt25">
            <h3>联系我们</h3>
            <ul id="foo_icon">
                <li>河南省郑州市前程大道ZTBU学院</li>
                <li>e-mail:zz@wanfang.com</li>
                <li>电话: </li>
                <li class="erwei">
                    <br>
                    <div>
                        <img class="weixin" src="${pageContext.request.contextPath}/img/微信公众号.png" alt=""
                             draggable="false">
                        <img class="weibo" src="${pageContext.request.contextPath}/img/微博公众号.png" alt=""
                             draggable="false">
                    </div>
                </li>
            </ul>
        </li>
    </ul>
    <div class="record">好先生教育 &copy; 豫ICP备170508020105号;ZTBU学院</div>
</footer>


<!--登录注册弹出框-->
<div class="mask hidden" id="login">
    <div class="mask_content">
        <div class="mask_content_header">
            <img src="${pageContext.request.contextPath}/img/logo.png" alt="" class="ma">
        </div>
        <div class="mask_content_body">
            <form id="loginForm" action="/user/loginUser">
                <h3>快速登录</h3>
                <input type="email" id="loginEmail" placeholder="请输入邮箱" name="email">
                <input type="password" id="loginPassword" placeholder="请输入密码" name="password">
                <div id="forget">
                    <a href="${pageContext.request.contextPath}/user/forgetPassword">忘记密码？</a>
                </div>
                <input type="submit" onclick="return commitLogin()" value="登&#x3000;录">
            </form>
        </div>
        <div class="mask_content_footer">
            <span id="login_close">关&#x3000;闭</span>
        </div>
    </div>
</div>
<div class="mask hidden" id="reg">
    <div class="mask_content">
        <div class="mask_content_header">
            <img src="${pageContext.request.contextPath}/img/logo.png" alt="" class="ma">
        </div>
        <div class="mask_content_body">
            <form id="regForm" action="user/insertUser.action">
                <h3>新用户注册</h3>
                <input type="email" id="regEmail" placeholder="请输入邮箱" name="email"><span id="emailMsg"></span>
                <input type="password" id="regPsw" placeholder="请输入密码" name="password">
                <input type="password" id="regPswAgain" placeholder="请再次输入密码" name="psw_again"><span
                    id="passMsg"></span>
                <div id="yzm" class="form-inline">
                    <input type="text" name="yzm" style="width: 45%; display: inline-block;">
                    <div id="v_container" style="width: 45%;height: 40px;float:right;"></div>
                </div>
                <input type="submit" onclick="return commitRegForm();" value="注&#x3000;册">
            </form>
        </div>
        <div class="mask_content_footer">
            <span id="reg_close">关&#x3000;闭</span>
        </div>
    </div>
</div>


</body>

<script src="${pageContext.request.contextPath}/js/index.js"></script>
<script type="text/javascript">

    function search_Click() {
        var title = document.getElementById("search_Title").value;
        if (title === "") {
            alert('关键字不可为空');
            return false;
        }else {
            $("#search_Form").submit();
        }

    }

</script>
</html>
