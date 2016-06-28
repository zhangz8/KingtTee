<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>生活费表</title>
</head>
<body>
	<table class="table">
		<thead>
			<tr>
				<th>ID</th>
				<th>日期</th>
				<th>条目</th>
				<th>金额</th>
				<th>类别</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${expenses}" var="expense">
				<tr>
					<td>${expense.id}</td>
					<td>${expense.expenseTime.toString()}</td>
					<td>${expense.itemName}</td>
					<td>${expense.money}</td>
					<td>${expense.id}</td>
					<td>删除</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
