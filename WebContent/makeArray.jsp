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
        var chartData = [['PC방',5724.0], ['가구·가전',16439.0], ['가전제품수리',4627.0], ['건강보조식품',8588.0], ['네일숍',5900.0], ['노래방',21423.0], ['당구장',4172.0], ['두발미용업',44161.0], ['부동산중개업',69269.0], ['분식전문점',32786.0], ['서적·문구',11910.0], ['섬유제품',23968.0], ['세탁소(가정)',9583.0], ['숙박업',13358.0], ['슈퍼마켓',39105.0], ['스포츠클럽',7036.0], ['식료품',41313.0], ['양식음식점',18977.0], ['예체능학원',22915.0], ['오락·운동',12184.0], ['외국어학원',8549.0], ['의류점',116968.0], ['의약·의료용품',19671.0], ['일반교습학원',20924.0], ['일반의원',21736.0], ['일식음식점',15461.0], ['자동차수리·세차',10834.0], ['제과점',7727.0], ['주방·가정용품',31746.0], ['중식음식점',13634.0], ['치과의원',12172.0], ['치킨전문점',6072.0], ['커피·음료',34918.0], ['컴퓨터·주변기기',13560.0], ['통신판매업',33251.0], ['패션용품',40012.0], ['패스트푸드점',6217.0], ['편의점',3453.0], ['피부관리실',14825.0], ['한식음식점',141437.0], ['한의원',9888.0], ['핸드폰',15249.0], ['호프·간이주점',44801.0], ['화장품',37110.0], ['화초·애완',15481.0]];
        
        /* var chartData = ${requestScope.testChannel}; */
        var chartData2= [[],[]];
        chartData2[0].push('year');
        chartData2[1].push('year');
        
        for(var c in chartData){
        	chartData2[0].push(chartData[c][0]);
        	chartData2[1].push(chartData[c][1]);
        }
        console.log(chartData);
        console.log(chartData2);
        
        var data = google.visualization.arrayToDataTable(chartData2);
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