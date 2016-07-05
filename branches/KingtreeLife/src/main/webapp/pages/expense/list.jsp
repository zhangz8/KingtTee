<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="${ctx}/common.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="${ctx}/assets/Highcharts-4.2.5/js/highcharts.js"></script>
<script src="${ctx}/assets/Highcharts-4.2.5/js/exporting.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>消费管理</title>
</head>
<body>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">添加消费记录</div>
				<div class="panel-body">
					<form action="/expense/list">
						<div class="form-group row">
							<div class="col-md-3">
								<div class="input-group form_datetime date">
									<input value="${start}" placeholder="开始时间" name="startStr" class="form-control"><span class="input-group-addon"><span
										class="glyphicon glyphicon-calendar"></span></span>
								</div>
							</div>
							<div class="col-md-3">
								<div class="input-group form_datetime date">
									<input value="${end}" placeholder="结束时间" name="endStr" class="form-control"><span class="input-group-addon"><span
										class="glyphicon glyphicon-calendar"></span></span>
								</div>
							</div>
							<div class="col-md-2">
								<select class="form-control" name="type"><option value="" selected>--消费类型--</option>
									<c:forEach items="${dictionaryList}" var="item">
										<option value="${item.id}" <c:if test="${item.id==type}">selected='selected'</c:if>>${item.name}</option>
									</c:forEach></select>
							</div>
							<div class="col-md-2">
								<input class="form-control" value="${expenseName}" name="expenseName" placeholder="消费名称">
							</div>
							<div class="col-md-2">
								<select class="form-control" name="analysisType">
									<option value="" selected>--分析类型--</option>
									<c:forEach items="${analysisTypes}" var="item">
										<option value="${item.value}" <c:if test="${item.value==analysisType}">selected</c:if>>${item.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group pull-right row">
							<a class="btn btn-info" href="/expense/toAdd">添加</a>
							<button class="btn btn-info" type="reset">清空</button>
							<button class="btn btn-info" type="submit">查询</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">消费走势图</div>
				<div class="panel-body">
					<div id="container" style="width: 100%; height: 400px;"></div>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">消费记录表</div>
				<div class="panel-body">
					<table class="table table-hover" id="dataTables">
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
									<td><c:forEach items="${dictionaryList}" var="item">
											<c:if test="${item.id == expense.dictionaryId}">${item.name}</c:if>
										</c:forEach></td>
									<td><a class="btn btn-danger btn-sm" href="/expnese/remove?id=${expense.id}">删除</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var dictionaryList = $.parseJSON('${dictionaryJsons}');
	var legend = new Array();
	if (dictionaryList != null && dictionaryList != "" && dictionaryList != "null") {
		$.each(dictionaryList, function(i, e) {
			legend[i] = e.name;
		});
	}
	$('#dataTables').DataTable({
		serverSide : false,
		processing : true,
		pageLength : 20,
		searching : false,
		ordering : false,
		destroy : true,
		language : {
			lengthMenu : '',
			processing : "载入中",
			paginate : {
				previous : "上一页",
				next : "下一页",
				first : "第一页",
				last : "最后一页"
			},
			zeroRecords : "没有内容",
			info : "总共_PAGES_ 页，显示第_START_ 到第 _END_ ，筛选之后得到 _TOTAL_ 条，初始_MAX_ 条 ",
			infoEmpty : "0条记录",
			infoFiltered : ""
		}
	});
	$('.form_datetime').datetimepicker({
		language : 'zh-CN',
		weekStart : 1,
		todayBtn : 1,
		format : 'yyyy-mm-dd hh:mm:ss',
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 2,
		forceParse : 0
	});

	$('#container').highcharts(
			{
				chart : {
					type : 'area'
				},
				title : {
					text : 'US and USSR nuclear stockpiles'
				},
				subtitle : {
					text : 'Source: <a href="http://thebulletin.metapress.com/content/c4120650912x74k7/fulltext.pdf">'
							+ 'thebulletin.metapress.com</a>'
				},
				xAxis : {
					allowDecimals : false,
					labels : {
						formatter : function() {
							return this.value; // clean, unformatted number for year
						}
					}
				},
				yAxis : {
					title : {
						text : 'Nuclear weapon states'
					},
					labels : {
						formatter : function() {
							return this.value / 1000 + 'k';
						}
					}
				},
				tooltip : {
					pointFormat : '{series.name} produced <b>{point.y:,.0f}</b><br/>warheads in {point.x}'
				},
				plotOptions : {
					area : {
						pointStart : 1940,
						marker : {
							enabled : false,
							symbol : 'circle',
							radius : 2,
							states : {
								hover : {
									enabled : true
								}
							}
						}
					}
				},
				series : [
						{
							name : 'USA',
							data : [ null, null, null, null, null, 6, 11, 32, 110, 235, 369, 640, 1005, 1436, 2063, 3057, 4618, 6444, 9822, 15468,
									20434, 24126, 27387, 29459, 31056, 31982, 32040, 31233, 29224, 27342, 26662, 26956, 27912, 28999, 28965, 27826,
									25579, 25722, 24826, 24605, 24304, 23464, 23708, 24099, 24357, 24237, 24401, 24344, 23586, 22380, 21004, 17287,
									14747, 13076, 12555, 12144, 11009, 10950, 10871, 10824, 10577, 10527, 10475, 10421, 10358, 10295, 10104 ]
						},
						{
							name : 'USSR/Russia',
							data : [ null, null, null, null, null, null, null, null, null, null, 5, 25, 50, 120, 150, 200, 426, 660, 869, 1060, 1605,
									2471, 3322, 4238, 5221, 6129, 7089, 8339, 9399, 10538, 11643, 13092, 14478, 15915, 17385, 19055, 21205, 23044,
									25393, 27935, 30062, 32049, 33952, 35804, 37431, 39197, 45000, 43000, 41000, 39000, 37000, 35000, 33000, 31000,
									29000, 27000, 25000, 24000, 23000, 22000, 21000, 20000, 19000, 18000, 18000, 17000, 16000 ]
						} ]
			});
</script>
</html>
