<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	var index = -1;
	
	google.charts.load('current', {'packages' : [ 'bar' ]});
	function drawChart() {
		var b= [2,3];
		b.unshift(1);
		alert(b);
		index +=1;
		var chartData = ${requestScope.popComp}[index];
		alert(chartData);
		var data = google.visualization.arrayToDataTable(chartData[1]);
		var options = {
			title : chartData[0][0] + "년 " + chartData[0][1] + "분기 " + chartData[0][2] +"의 상주인구"
		};
		var chart = new google.charts.Bar(document.getElementById('columnchart_material'));
		chart.draw(data, google.charts.Bar.convertOptions(options));
	}
	
	function drawChartBefore() {
		index -=1;
		var chartData = ${requestScope.popComp}[index];
		var data = google.visualization.arrayToDataTable(chartData[1]);
		var options = {
			title : chartData[0][0] + "년 " + chartData[0][1] + "분기 " + chartData[0][2] +"의 상주인구"
		};
		var chart = new google.charts.Bar(document.getElementById('columnchart_material'));
		chart.draw(data, google.charts.Bar.convertOptions(options));
	}
	
	
	
	function ajaxJSON() {
		var a = ${requestScope.popComp}
		console.log(typeof(a));
		console.log(a[0]);
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var chartData = ${requestScope.popComp};
				alert(chartData);
				drawChart(chartData);
			}
		};
	}
	//ajaxJSON();
</script>
</head>
<body>
	<button onclick="drawChartBefore()">이전 분기</button>
	<button onclick="drawChart()">다음 분기</button>
	<div id="columnchart_material" style="width: 900px; height: 500px;"></div>

</body>
</html>