<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  	<base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">
    <title>星脸联盟</title>
    <!-- Bootstrap core CSS -->
    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/buttons.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="resources/css/dashboard.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <jsp:include page="top.jsp"></jsp:include>

    <div class="container-fluid">
      <div class="row">
        <jsp:include page="left.jsp"></jsp:include>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <div class="row">
        	用户管理
        	<form action="user/manager/list" method="POST" id="ticketManagerForm">
        	<div class="col-md-2">
	        	<input name="userName" value="${user.userName }"/>
        	</div>
        	<div class="col-md-3">
	        	<input name="nickName" value="${user.userName }"/>
        	</div>
        	<div class="col-md-2">
	        	<input name="codeName" value="${user.codeName }"/>
        	</div>
        	<div class="col-md-2">
	        	<button type="button" class="btn btn-primary" onclick="submit_form()">搜索</button>
        	</div>
        	</form>
        </div>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>账号</th>
                  <th>昵称</th>
                  <th>代号</th>
                  <th>手机号码</th>
                  <th>性别</th>
                  <th>年龄</th>
                  <th>注册时间</th>
                  <th>用户状态</th>
                  <th>发言状态</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
              	<c:forEach items="${list }" var="i">
                <tr>
                  <td>${i.id }</td>
                  <td>${i.userName }</td>
                  <td >${i.nickName }</td>
                  <td >${i.codeName }</td>
                  <td >${i.mobile }</td>
                  <td>
                  	<c:if test="${i.gender == 0 }">未设置</c:if>
                  	<c:if test="${i.gender == 1 }">男</c:if>
                  	<c:if test="${i.gender == 2 }">女</c:if>
                  </td>
                  <td>
                  	${i.age }
                  </td>
                  <td>
                  ${i.createTimeView }
                  </td>
                  <td>
                  	<c:if test="${i.isLock == 0 }">正常</c:if>
                  	<c:if test="${i.isLock == 1 }"><span style="color:red;">已禁用</span></c:if>
                  </td>
                  <td>
                  	<c:if test="${i.isSaymsg == 0 }">正常</c:if>
                  	<c:if test="${i.isSaymsg == 1 }"><span style="color:red;">已禁用</span></c:if>
                  </td>
                  <td>
                  <span class="button-dropdown" data-buttons="dropdown">
				    <button class="button button-rounded">
				      <span style="font-size;14px;word-break:keep-all;white-space:nowrap;">操作</span><i class="fa fa-caret-down"></i>
				    </button>
				    <ul class="button-dropdown-list">
				    <c:if test="${i.isLock == 0 }">
				      <li><a href="javascript:void(0);" onclick="lockUser(${i.id})">禁用</a></li>
				    </c:if>
				    <c:if test="${i.isLock == 1 }">
				      <li><a href="javascript:void(0);" onclick="unlockUser(${i.id})">解禁</a></li>
                  	</c:if>
                  	<c:if test="${i.isSaymsg == 0 }">
				      <li><a href="javascript:void(0);" onclick="saymsgUser(${i.id})">禁止发言</a></li>
				    </c:if>
                  	<c:if test="${i.isSaymsg == 1 }">
				      <li><a href="javascript:void(0);" onclick="unsaymsgUser(${i.id})">解禁发言</a></li>
                  	</c:if>
				    </ul>
				  </span>
                  </td>
                </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
          <nav>
			  <ul class="pagination">
			    <li>
			      <a href="user/manager/list?start=${page.currentPage-1 <= 0 ? 0: page.currentPage-1}" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
			    <c:forEach items="${page.pageList }" var="i">
			    <li <c:if test="${i==page.currentPage }">class="active"</c:if>><a  href="user/manager/list?start=${i }">${i+1 }</a></li>
			    </c:forEach>
			    <li>
			      <a href="user/manager/list?start=${page.currentPage+1 >= page.totalPage ? page.totalPage-1: page.currentPage+1 }" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
			  </ul>
			</nav>
        </div>
      </div>
      
    </div>
	<!-- Modal -->  
	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="resources/js/ie10-viewport-bug-workaround.js"></script>
    <script src="http://files.cnblogs.com/rubylouvre/bootstrap-modal.js"></script>
    <script src="http://www.bootcss.com/p/buttons/js/buttons.js"></script>
    <script type="text/javascript">
    function submit_form(){
    	$("#ticketManagerForm").submit();
    }
    function pass(id){
    	var content = $("#tx_"+id).val();
    	$.post("user/feedback/reply",{id:id,feedback:content},function(result){
    		if(result.status == 0){
    			alert("保存成功");
    			submit_form();
    		}
    	});
    }
    function openWin(url){
    	window.open(url,'newwindow','height=auto,width=auto,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no') ;
    }
    function lockUser(id){
    	if(confirm("确定要禁用该用户吗?禁用后就不可再登录")){
    		$.post("user/lock_user",{id:id},function(result){
       			window.location.reload();
        	});
    	}
    	
    }
    function unlockUser(id){
    	if(confirm("确定要解禁该用户吗?")){
    		$.post("user/unlock_user",{id:id},function(result){
       			window.location.reload();
        	});
    	}
    	
    }
    
    function saymsgUser(id){
    	if(confirm("确定要禁止该用户发言吗?")){
    		$.post("user/saymsg_user",{id:id},function(result){
       			window.location.reload();
        	});
    	}
    	
    }
    function unsaymsgUser(id){
    	if(confirm("确定要解禁该用户发言吗?")){
    		$.post("user/unsaymsg_user",{id:id},function(result){
       			window.location.reload();
        	});
    	}
    	
    }
    </script>
  </body>
</html>
