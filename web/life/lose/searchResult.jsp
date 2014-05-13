<%@ page import="life.lose.ManageLose" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 3/3/14
  Time: 8:09 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String want = request.getParameter("want");
	byte type = Byte.parseByte(request.getParameter("type"));
%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link href="/lib/css/bootstrap.min.css" rel="stylesheet">
	<script src="/lib/js/jquery-2.0.2.min.js"></script>
	<script src="/lib/js/bootstrap.min.js"></script>
	<link href="/lib/css/main.css" rel='stylesheet'>
	<script src="/lib/js/main.js"></script>
	<title>招领平台搜索结果</title>
	<script>
		var changeCount = Number(<%=ManageLose.ChangeCount%>);
		<%--ajax加载更多--%>
		function ajaxMore(btn) {
			$(btn).addClass('active');
			$(btn).text("正在努力加载中...");
			var begin = Number($(btn).attr('begin'));
			begin += changeCount;
			$.ajax({
				url: "GetSearchForAJAX.jsp",
				data: { begin: begin, type: <%=type%>, want: '<%=want%>'},
				contentType: "application/x-www-form-urlencoded; charset=utf-8"
			}).done(function (data) {
				if (data.length < 20) {
					$(btn).text("没有更多了!");
				} else {
					$(btn).before(data);
					$(btn).removeClass('active');
					$(btn).text("更多");
					$(btn).attr('begin', begin);
				}
			});
		}

		//加载评论框
		function addComment(the) {
			var id = $(the).attr('id');
			var url = 'comment.jsp?id=' + id;
			var Commnet = $(the).children('.comment').first();
			$(Commnet).attr('src', url);
			$(Commnet).toggle();
		}

		//设置该失误记录为完成
		function completeOne(btn) {
			var id = $(btn).parent().parent().attr('id');
			$.ajax({
				url: "CompleteOneServlet.jsp",
				data: {id: id},
				contentType: "application/x-www-form-urlencoded; charset=utf-8"
			}).done(function (data) {
				$(btn).text(data);
			});
		}
	</script>
</head>
<body>
<div class="container">
	<br>
	<%--默认拿出前changeCount通知--%>
	<jsp:include page="GetSearchForAJAX.jsp">
		<jsp:param name="begin" value="0"/>
		<jsp:param name="type" value="<%=type%>"/>
		<jsp:param name="want" value="<%=want%>"/>
	</jsp:include>
	<%--ajax 加载更多--%>
	<button class="form-control btn-info input-lg" onclick="ajaxMore(this)" begin="0">更多</button>
	<br>
</div>

<%--链接--%>
<%@ include file="link.jsp" %>

</body>
</html>