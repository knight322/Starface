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
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="resources/css/dashboard.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="resources/css/lanrenzhijia.css">
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
        	<form action="ticket/manager" method="POST" id="ticketManagerForm">
        	<div class="col-md-4">
	        	<select class="form-control" name="verifyResult">
	        		<option value="0" <c:if test="${ticketReq.verifyResult == 0}">selected</c:if> >未验证</option>
	        		<option value="1" <c:if test="${ticketReq.verifyResult == 1}">selected</c:if>>真</option>
	        		<option value="2" <c:if test="${ticketReq.verifyResult == 2}">selected</c:if>>假</option>
	        	</select>
        	</div>
        	<div class="col-md-4">
	        	<input type="phone" id="inputPhone" class="form-control" name="phone" placeholder="手机号码">
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
                  <th>上传时间</th>
                  <th>手机号码</th>
                  <th>正面</th>
                  <th>背面</th>
                  <th>状态</th>
                  <c:if test="${ticketReq.verifyResult == 2 }">
                  <th>原因</th>
                  </c:if>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
              	<c:forEach items="${list }" var="i">
                <tr>
                  <td>${i.id }</td>
                  <td>${i.createTime }</td>
                  <td>${i.phone }</td>
                  <td>
	                  <a href="javascript:void(0);" onclick="openWin('${fileBasePath }${i.frontImg }')">正面图片</a> 
	                  <!--<img src="${fileBasePath }${i.frontImg }" onclick="" style="max-width:100px;max-height:100px;"/>  -->
                  </td>
                  <td>
                  	<a href="javascript:void(0);" onclick="openWin('${fileBasePath }${i.backImg }')">反面图片</a> 
                  	<!-- <img src="${fileBasePath }${i.backImg }" style="max-width:100px;max-height:100px;"/> -->
                  </td>
                  <td>
                  	<c:if test="${i.verifyResult ==0}">未验证</c:if>
                  	<c:if test="${i.verifyResult ==1}">真</c:if>
                  	<c:if test="${i.verifyResult ==2}">假</c:if>
                  </td>
                  <c:if test="${ticketReq.verifyResult == 2 }">
                  <td>
                  ${i.reason }
                  </td>
                  </c:if>
                  <td>
                  	<button type="button" class="btn btn-primary" onclick="pass(${i.id})">通过</button>
                  	<button type="button" class="btn btn-primary" onclick="showSlide(${i.id})">不通过</button>
                  </td>
                </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

	<div class="theme-popover">
		<div class="theme-poptit">
			<a href="javascript:void(0);" onclick="close_win()" title="关闭" class="close">关闭</a>
			<h3>验证不通过</h3>
		</div>
		<div class="theme-popbod dform">
			<ol>
				<li><strong>原因:</strong>
					<textarea id="reason" rows="8" cols="60"></textarea>
				</li>
				<li><input id="theme_ticket_id" value="" type="hidden"/></li>
				<li><br /><input class="btn btn-primary" type="button" onclick="fail()" value=" 提 交 "/></li>
			</ol>
		</div>
	</div>
	<div class="theme-popover-mask">
	<!-- Modal -->  
	<!-- Bootstrap core JavaScript"
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="resources/js/jquery/1.11.0/jquery-1.11.0.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="resources/js/ie10-viewport-bug-workaround.js"></script>
    <script src="http://files.cnblogs.com/rubylouvre/bootstrap-modal.js"></script>
    <script type="text/javascript">
    function submit_form(){
    	$("#ticketManagerForm").submit();
    }
    function pass(id){
    	if(confirm("确认验证通过吗?")){
    		$.post("ticket/verify_ticket",{id:id,verifyResult:1},function(result){
        		if(result.status == 0){
        			alert("验证成功");
        			submit_form();
        		}
        		
        	});
    	}
    }
    function fail(){
    	var reason = $("#reason").val();
    	var id = $("#theme_ticket_id").val();
   		$.post("ticket/verify_ticket",{id:id,verifyResult:2,reason:reason},function(result){
       		if(result.status == 0){
       			alert("操作成功");
       			submit_form();
       		}
       	});
    }
    function showSlide(id){
    	var t_id = $("#theme_ticket_id").val();
    	if(id != t_id){
    		$("#reason").val("");
    	}
   		$('.theme-popover-mask').fadeIn(100);
   		$('.theme-popover').slideDown(200);
   		$("#theme_ticket_id").val(id);
    }
    function close_win(){
   		$('.theme-popover-mask').fadeOut(100);
   		$('.theme-popover').slideUp(200);
    }
    function openWin(url){
    	window.open(url,'newwindow','height=auto,width=auto,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no') ;
    }
    </script>
  </body>
</html>
