<%--
  Created by IntelliJ IDEA.
  User: 小王
  Date: 2024/7/5
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--直接请求CustomerFurnServlet，获取网站首页要显示的分页数据--%>
<%--类似于我们网站的程序入口--%>
<jsp:forward page="/customerFurnServlet?action=pageByName&pageNo=1"></jsp:forward>
<%--customerServlet--%>