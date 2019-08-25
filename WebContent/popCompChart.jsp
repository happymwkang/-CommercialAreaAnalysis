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
	function drawChart(chartData) {
		var data = google.visualization.arrayToDataTable(chartData);
		var options = {
			title : 'My Daily Activities'
		};
		var chart = new google.visualization.PieChart(document
				.getElementById('piechart'));
		chart.draw(data, options);
	}
	
	
	
	function ajaxJSON() {
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var chartData = this.responseText;
				alert(chartData);
				drawChart(eval(chartData));
			}
		};
	}
	function print(){
		 

		console.log(eval(${requestScope.popComp}));
		console.log(typeof(${requestScope.popComp}));
		console.log(typeof(eval(${requestScope.popComp})));
	}
	//ajaxJSON();
</script>
</head>
<body>
	<button onclick="print()">클릭</button>
	<div id="piechart" style="width: 900px; height: 500px;"></div>

</body>
</html>