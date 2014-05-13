<%--通知列表里获得AJAX通知--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="life.lose.MyLoseEntity" %>
<%@ page import="life.lose.ManageLose" %>
<%@ page import="tool.Tool" %>
<%
	// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	try {
		List<MyLoseEntity> loseEntities = ManageLose.get(Tool.getXHMMfromCookie(request)[0]);
		if (loseEntities.size() > 0) {
%>
<div class="alert alert-info">以下是你所有的失物招领信息,如果你已经成功完成失物招领请点黄色状态按钮确认完成</div>
<%
	for (MyLoseEntity one : loseEntities) {
%>
<div class="thumbnail alert-warning" id="<%=one.getId()%>" onclick="addComment(this)">
	<%--描述--%>
	<h4><%=one.getMyDes()%>
	</h4>
	<hr>
	<%--发布时间--%>
	<div class="btn-group btn-group-xs">
		<button class="btn btn-info active"><i class="glyphicon glyphicon-time"></i></button>
		<button class="btn active"><%=one.getMyDate()%>
		</button>
	</div>
	<%--失物地点--%>
	<div class="btn-group btn-group-xs">
		<button class="btn btn-info active"><i class="glyphicon glyphicon-map-marker"></i></button>
		<button class="btn active"><%=one.getMyLocation()%>
		</button>
	</div>
	<%--联系方式--%>
	<div class="btn-group btn-group-xs">
		<button class="btn btn-info active"><i class="glyphicon glyphicon-phone"></i></button>
		<button class="btn active"><%=one.getMyPhone()%>
		</button>
	</div>
	<%--目前状态--%>
	<div class="btn-group btn-group-xs">
		<button class="btn active"><i class="glyphicon glyphicon-stats"></i></button>
		<button onclick="completeOne(this);" class="btn <%=one.getBootstapStateString()%>"><%=one.getStateString()%>
		</button>
	</div>
	<%--失物还是招领--%>
	<div class="btn-group btn-group-xs">
		<button class="btn active btn-info"><i class="glyphicon <%=one.getLoseOrUpdateGl()%>"></i></button>
		<button class="btn active"><%=one.getLoseOrUpdateChinese()%>
		</button>
	</div>
	<%--是否是今天的--%>
	<%
		if (one.isToday()) {
	%>
	<button class="btn btn-success btn-xs active">Today</button>
	<%
		}
	%>
	<%--评论框--%>
	<iframe src="" class="form-control comment" style="padding: 0;border: none;height: 400px;display: none"></iframe>

</div>
<%
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
		return;
	}
%>