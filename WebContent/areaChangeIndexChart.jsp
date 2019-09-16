<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
   <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
   <script type="text/javascript">
   
   console.log("실험중여")
      console.log( ${requestScope.getAllAreaChanIx});
   ${requestScope.getAllAreaChanIx};
   /* 
   google.charts.load('current', {'packages':['gauge']});
      google.charts.setOnLoadCallback(drawChart);
	
      
      
      
      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Label', 'Value'],
          ['상권지표', 68]
        ]);

        var options = {
          width: 400, height: 120,
          redFrom: 90, redTo: 100,
          yellowFrom:75, yellowTo: 90,
          minorTicks: 5
        };

        var chart = new google.visualization.Gauge(document.getElementById('chart_div'));

        chart.draw(data, options);
        setInterval(function() {
          data.setValue(2, 1, 60 );
          chart.draw(data, options);
        }, 26000);
      } */
    </script>
  </head>
  <body>
    <div id="chart_div" style="width: 400px; height: 120px;"></div>
  </body>
</html>