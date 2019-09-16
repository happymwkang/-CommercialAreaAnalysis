<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  <style type="text/css">
  .f{
  margin: 50px 50px 50px 1200px;
  }
  .g{
  margin: 450px 450px 50px 200px;
  }
  div.left{
  width:50%;
  float:left;
  }
  div.right{
  width:50%;
  float:right;
  }
  </style>
   <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
    <title>Dorne - Directory &amp; Listing Template</title>

    <!-- Favicon -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Stylesheet -->
    <link href="style.css" rel="stylesheet">

    <!-- Responsive CSS -->
    <link href="css/responsive/responsive.css" rel="stylesheet">
	
	<script type="text/javascript"
		src="https://www.gstatic.com/charts/loader.js"></script>
  
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.load('current', { 'packages': ['corechart', 'gauge'] });
      google.charts.setOnLoadCallback(drawVisualization);
      google.charts.setOnLoadCallback(drawChart);

      function drawVisualization() {
    	  //엄종별 점포수
         var shopCnt = ${requestScope.getChannelCnt[0]};
        shopCnt.unshift(['업종','점포수']);
        
        var shopCntData = google.visualization.arrayToDataTable(shopCnt);
        var shopCntOptions = {
          width: 550,
          height: 500,
          title : '${requestScope.areaNm} 업종별 점포수' ,
          vAxis: {title: '점포수'},
          hAxis: {title: '업종'},
          seriesType: 'bars',
          series: {5: {type: 'line'}}
        };
        
        var shopCntChart = new google.visualization.ComboChart(document.getElementById("Chart1"));
        shopCntChart.draw(shopCntData, shopCntOptions);
        
     	 //엄종별 창업 점포수
        var openCnt = ${requestScope.getChannelOpenCnt[0]};
        openCnt.unshift(['업종','점포수']);
        
        var openCntData = google.visualization.arrayToDataTable(openCnt);
        var openCntOptions = {
          width: 550,
          height: 500,
          title : '${requestScope.areaNm} 업종별 창업점포수' ,
          vAxis: {title: '창업점포수'},
          hAxis: {title: '업종'},
          seriesType: 'bars',
          series: {5: {type: 'line'}}
        };
        
        var openCntChart = new google.visualization.ComboChart(document.getElementById("Chart2"));
        openCntChart.draw(openCntData, openCntOptions); 
        
      //엄종별 폐업 점포수
        var closeCnt = ${requestScope.getChannelCloseCnt[0]};
        closeCnt.unshift(['업종','점포수']);
        
        var closeCntData = google.visualization.arrayToDataTable(closeCnt);
        var closeCntOptions = {
          width: 550,
          height: 500,
          title : '${requestScope.areaNm} 업종별 폐업점포수' ,
          vAxis: {title: '폐업점포수'},
          hAxis: {title: '업종'},
          seriesType: 'bars',
          series: {5: {type: 'line'}}
        };
        
        var closeCntChart = new google.visualization.ComboChart(document.getElementById("Chart3"));
        closeCntChart.draw(closeCntData, closeCntOptions);
        
      //엄종별 프렌차이즈 점포수
        var frcCnt = ${requestScope.getChannelFrcCnt[0]};
        frcCnt.unshift(['업종','점포수']);
        
        var frcCntData = google.visualization.arrayToDataTable(frcCnt);
        var frcCntOptions = {
          width: 550,
          height: 500,
          title : '${requestScope.areaNm} 업종별 프렌차이즈 점포수' ,
          vAxis: {title: '창업점포수'},
          hAxis: {title: '업종'},
          seriesType: 'bars',
          series: {5: {type: 'line'}}
        };
        
        var frcCntChart = new google.visualization.ComboChart(document.getElementById("Chart4"));
        frcCntChart.draw(frcCntData, frcCntOptions);  
        

        
    /*     var areaIx = ${requestScope.getArealIx[0]};
        areaIx.unshift(['상권명','변화지표']);
        console.log(areaIx);
        var areaIxData = google.visualization.arrayToDataTable(areaIx);

        var areaIxOptions = {
          width: 400, height: 120,
          redFrom: 90, redTo: 100,
          yellowFrom:75, yellowTo: 90,
          minorTicks: 5
        };

        var gaugeIx = google.visualization.Gauge(document.getElementById('Chart5'));
        gaugeIx.draw(areaIxData, areaIxOptions);
         */
        
      }
      
      
     	/* 게이지 차트  */
      
       function drawChart() {

    	   var areaIx = ${requestScope.getArealIx[0]};
           areaIx.unshift(['상권명','변화지표']);
          var data = google.visualization.arrayToDataTable(areaIx);

          var options = {
            width: 400, height: 320,
            greenFrom:0, greenTo:25,
            redFrom: 75, redTo: 100,
            yellowFrom:25, yellowTo: 75,
            minorTicks: 1
          };

          var chart = new google.visualization.Gauge(document.getElementById('Chart5'));

          chart.draw(data, options);
        }
      
     
      
    </script>
  </head>
  <body>
    <!-- <div id="chart_div" style="width: 900px; height: 500px;"></div> -->
    
    <!-- ***** Editor Pick Area Start ***** -->
    <section class="dorne-editors-pick-area bg-img bg-overlay-9 section-padding-100" style="background-image: url(img/bg-img/hero-2.jpg);">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="section-heading text-center">
                        <span></span>
                        <h4>분기별 상권 업종별 정보</h4>
                        <p></p>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-12 col-lg-6" id = "Chart1">
                    <div class="single-editors-pick-area wow fadeInUp" data-wow-delay="0.2s">
                        <div class="editors-pick-info">
                            <div class="places-total-destinations d-flex">
                            </div>
                            <div>
                            </div>
                            <div id="columnchart_material"></div>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-lg-6" id = "Chart2">
                    <div class="single-editors-pick-area wow fadeInUp" data-wow-delay="0.4s">
                        <div class="editors-pick-info">
                            <div class="places-total-destinations d-flex">
                            </div>
                            <div class="add-more">
                            </div>
                        </div>
                    </div>
                    <div class="single-editors-pick-area wow fadeInUp" data-wow-delay="0.6s">
                        <div class="editors-pick-info">
                            <div class="places-total-destinations d-flex">
                            </div>
                            <div class="add-more">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ***** Editor Pick Area End ***** -->
    
     <!-- ***** Editor Pick Area Start ***** -->
    <section class="dorne-editors-pick-area bg-img bg-overlay-9 section-padding-100" style="background-image: url(img/bg-img/hero-2.jpg);">
         <div class="container">
           <!--  <div class="row">
                <div class="col-12">
                    <div class="section-heading text-center">
                        <span></span>
                        <h4>분기별 상권 업종별 정보</h4>
                        <p></p>
                    </div>
                </div>
            </div> -->

            <div class="row">
                <div class="col-12 col-lg-6" id = "Chart3">
                    <div class="single-editors-pick-area wow fadeInUp" data-wow-delay="0.2s">
                        <div class="editors-pick-info">
                            <div class="places-total-destinations d-flex">
                            </div>
                            <div>
                            </div>
                            <div id="columnchart_material"></div>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-lg-6" id = "Chart4">
                    <div class="single-editors-pick-area wow fadeInUp" data-wow-delay="0.4s">
                        <div class="editors-pick-info">
                            <div class="places-total-destinations d-flex">
                            </div>
                            <div class="add-more">
                            </div>
                        </div>
                    </div>
                    <div class="single-editors-pick-area wow fadeInUp" data-wow-delay="0.6s">
                        <div class="editors-pick-info">
                            <div class="places-total-destinations d-flex">
                            </div>
                            <div class="add-more">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> 
    </section>
    <!-- ***** Editor Pick Area End ***** -->
   				 <div>
                        <h4 align="center">분기별 상권 업종별 정보 </h4>
                    </div>
                    <div class="left">
                    	<img class= "left" src="img/ChangeIndex.png" width="500" height="1000" style="margin : center">
                    </div>
                  	<div class= "right" id = "Chart5" ></div> 
                    
    
    
    
    
  </body>
</html>