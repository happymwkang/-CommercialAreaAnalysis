<%@ page import="model.dto.PopCompDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load('current', {'packages' : [ 'corechart' ]});
	function drawChart() {
		var data = google.visualization.arrayToDataTable(${requestScope.popComp});
		var options = {
			title : 'My Daily Activities'
		};
		var chart = new google.visualization.PieChart(document
				.getElementById('piechart'));
		chart.draw(data, options);
	}
	
	
	
	function ajaxJSON() {
		var a = [1,2,3];
		console.log(typeof(a));
		console.log(${requestScope.popComp}[0]);
		console.log(typeof(${requestScope.popComp}));
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var chartData = ${requestScope.popComp};
				alert(chartData);
				drawChart(chartData);
			}
		};
	}
	function print(){
		 
		var a = ${requestScope.popComp};
		var b = a[0];
		console.log(a);
	}
	//ajaxJSON();
</script>
</head>
<body>
	<button onclick="drawChart()">클릭</button>
	<div id="piechart" style="width: 900px; height: 500px;"></div>

</body>
</html>