<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="col-sm-3 col-md-2 sidebar">
  <ul class="nav nav-sidebar">
    <li name="menu_li" <c:if test="${url==1 }">class="active"</c:if>><a href="user/manager/list?start=0">用户管理 <span class="sr-only">(current)</span></a></li>
    <li name="menu_li" <c:if test="${url==2 }">class="active"</c:if>><a href="weblog/manager/list?start=0">日志管理 </a></li>
    <li name="menu_li" <c:if test="${url==2 }">class="active"</c:if>><a href="common/inform/list?start=0">举报列表 </a></li>
  </ul>
  <%--
  <ul class="nav nav-sidebar">
    <li name="menu_li" <c:if test="${url==5 }">class="active"</c:if>><a href="user/zxlvManager">最新利率</a></li>
    <li name="menu_li" <c:if test="${url==6 }">class="active"</c:if>><a href="user/gywmManager">关于我们</a></li>
  </ul>
  <ul class="nav nav-sidebar">
    <li name="menu_li" <c:if test="${url==7 }">class="active"</c:if>><a href="user/sendMsgManager">发送消息</a></li>
  </ul>
   --%>
</div>