<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
    var test = ${requestScope.testChannel}
    	alert(test);
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawVisualization);

      function drawVisualization() {
        // Some raw data (not necessarily accurate)
    /*     var data = google.visualization.arrayToDataTable([
          ['Month', 'Bolivia', 'Ecuador', 'Madagascar', 'Papua New Guinea', 'Rwanda', 'Average'],
          ['2004/05',  165,      938,         522,             998,           450,      614.6],
          ['2005/06',  135,      1120,        599,             1268,          288,      682],
          ['2006/07',  157,      1167,        587,             807,           397,      623],
          ['2007/08',  139,      1110,        615,             968,           215,      609.4],
          ['2008/09',  136,      691,         629,             1026,          366,      569.6]
        ]); */
        var chartData = ${requestScope.testChannel};
        chartData.unshift(['업종','점포수']);
        
        var data = google.visualization.arrayToDataTable(chartData);
        var options = {
          title : 'TEST',
          vAxis: {title: '점포수'},
          hAxis: {title: '업종'},
          seriesType: 'bars',
          series: {5: {type: 'line'}}
        };

        var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
      
      /*functi on ajaxJSON() {
		  var xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (this.readyState == 4 && this.status == 200) {
		    	var chartData = this.responseText;
		    	alert(chartData[0][0]);
		    	chartData = eval(chartData);
		    	alert(chartData[0][0]);
		    	drawVisualization(chartData);
		    }
		  };
		  xhttp.send();
		} */
    </script>
  </head>
  <body>
  	<button onclick="drawVisualization()">클릭</button>
    <div id="chart_div" style="width: 900px; height: 500px;"></div>
  </body>
</html>