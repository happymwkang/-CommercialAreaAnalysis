<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<!-- Title -->
<title>Dorne - Directory &amp; Listing Template</title>

<!-- Favicon -->
<link rel="icon" href="img/core-img/favicon.ico">

<!-- Core Stylesheet -->
<link href="style.css?after" rel="stylesheet">

<!-- Responsive CSS -->
<link href="css/responsive/responsive.css" rel="stylesheet">

<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript"> 
	/* google.charts.setOnLoadCallback(drawChart(1));
	google.charts.setOnLoadCallback(salesBusiness(1)); */

	 var selectedIndex1="";
	 var selectedIndex2="";
	 var selectedIndex3="";
	 var select_text1="";
	 var select_text2="";
	 var select_text3="";
	 var select_value1="";
	 var select_value2="";
	 var select_value3="";
	 
	 function select_form1(){
		  selectedIndex1 = document.custom.customSelect1.options.selectedIndex;
		  select_text1=document.custom.customSelect1.options[selectedIndex1].text;
		  select_value1=document.custom.customSelect1.options[selectedIndex1].value;
		 
		 
		if(select_value1 != ""){
			document.custom.customSelect2.style.display="inline";
			selectDivision(select_value1);
			
		}else if(select_value1 == ""){
			document.custom.customSelect2.style.display="none";
		}
	}
	 
	function select_form2(){
		 selectedIndex2 = document.custom.customSelect2.options.selectedIndex;
		 select_text2=document.custom.customSelect2.options[selectedIndex2].text;
		 select_value2=document.custom.customSelect2.options[selectedIndex2].value;
		
		 if(select_text2 != ""){
			 document.custom.customSelect3.style.display="inline";
			 console.log(select_value2);
			 selectArea(select_value1,select_value2);
		 }else if(select_text2 == ""){
			 document.custom.customSelect3.style.display="none";
		 }

		}
		
	function selectDivision(guNm) {
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var div_list = eval(this.responseText);
				var div_list2 = '<option selected value=\"\">상권형태</option>';
				for(var i in div_list){
					div_list2 += '<option value=\"'+div_list[i]+'\">'+div_list[i]+'</option>';
				}
			document.getElementById("customSelect2").innerHTML = div_list2;
		             
			}
		};
		xhttp.open("POST", "commercial.do?command=getSelectArea&guNm="+guNm, true);
		xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		xhttp.send();
	}
	
	function selectArea(guNm, divNm) {
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var div_list = eval(this.responseText);
				console.log(div_list);
				var div_list2 = '<option selected value=\"\">상권명</option>';
				for(var i in div_list){
					div_list2 += '<option value=\"'+div_list[i][0]+'\">'+div_list[i][1]+'</option>';
				}
			document.getElementById("customSelect3").innerHTML = div_list2;
		             
			}
		};
		xhttp.open("POST", "commercial.do?command=getSelectArea&guNm="+guNm+"&divNm="+divNm, true);
		xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		xhttp.send();
	}
	
	
	
	<!-- Draw chart function -->
		var index = -1;
		var indexForSales = -1;
		var indexForSalesByAge = -1;
	
		google.charts.load('current', {'packages' : [ 'bar' ]});
		function drawChart(c) {
			if(c==1){
				index+=1;
			}else if(c==-1){
				index-=1;
			}
			var chartData = ${requestScope.popComp}[index];
			var data = google.visualization.arrayToDataTable(chartData[1]);
			
			var options = {
				title : chartData[0][0] + "년 " + chartData[0][1] + "분기 " + chartData[0][2] +" 지역의 상주인구",
				backgroundColor: 'transparent',
				legend: {textStyle: {color: 'white'}},
				titleTextStyle: { color: 'white'},
				hAxis: { textStyle: {color: 'white'} },
				vAxis: { textStyle: {color: 'white'} },
				colors : ['white'],
				'width':400,
				'height':300
			}
			var chart = new google.charts.Bar(document.getElementById('columnchart_material'));
			chart.draw(data, google.charts.Bar.convertOptions(options));
		}
		<!-- 매출  그래프 -->
		function salesBusiness(c) {
			if(c==1){
				indexForSales+=1;
			}else if(c==-1){
				indexForSales-=1;
			}
			var raw = ${requestScope.salesAmounts}[indexForSales];
			var chartData=[];
			chartData.push(['업종','매출']);
			console.log(${requestScope.salesAmounts});
			console.log(raw);
			
			for(var r in raw){
				chartData.push([raw[r][0][3],raw[r][1][1]]);
				
			}
			console.log(chartData);
			
			var data = google.visualization.arrayToDataTable(chartData);
			var options = {
				title : raw[indexForSales][0][1] + "년 " + raw[indexForSales][0][2] + "분기 " + raw[indexForSales][0][0] +" 지역의 상주인구",
				backgroundColor: 'transparent',
				legend: {textStyle: {color: 'blue'}},
				titleTextStyle: { color: 'blue'},
				hAxis: { textStyle: {color: 'blue'} },
				vAxis: { textStyle: {color: 'blue'} },
				colors : ['blue'],
				'width':400,
				'height':300
			}
			var chart = new google.charts.Bar(document.getElementById('salesDay_material'));
			chart.draw(data, google.charts.Bar.convertOptions(options));
		}
		
		function salesAge(c) {
			if(c==1){
				indexForSalesByAge+=1;
			}else if(c==-1){
				indexForSalesByAge-=1;
			}
			var raw = ${requestScope.salesAmounts}[indexForSalesByAge];
			var chartData=[];
			chartData.push(['나이','매출']);
			console.log(${requestScope.salesAmounts});
			console.log('raw');
			console.log(raw);
			
			for(var r in raw){
				for(var r2=0 ; r2<raw[r][5].length/2 ; r2++){
					chartData.push([raw[r][5][r2*2],raw[r][5][(r2*2)+1]]);
				}
			}
			
			console.log(chartData);
			
			var chartData2 = [];
			chartData2.push(["나이","매출"]);
			for(var i = 1; i<7; i++){
				if(i!=6){
					chartData2.push([i+'0대',0]);
				}else{
					chartData2.push([i+'0대 이상',0]);
				}
			}
			console.log(chartData2);
			for(var i = 1; i<chartData.length; i++){
				for(var j = 1; j<chartData2.length;j++){
					if(chartData2[j][0]==chartData[i][0]){
						console.log(chartData[i][0], chartData[i][1]);
						chartData2[j][1] += chartData[i][1];
						break;
					}
				}
			}
			console.log(chartData2);
			var data = google.visualization.arrayToDataTable(chartData2);
			var options = {
				title : raw[indexForSalesByAge][0][1] + "년 " + raw[indexForSalesByAge][0][2] + "분기 " + raw[indexForSalesByAge][0][0] +" 지역의 상주인구",
				backgroundColor: 'transparent',
				legend: {textStyle: {color: 'blue'}},
				titleTextStyle: { color: 'blue'},
				hAxis: { textStyle: {color: 'blue'} },
				vAxis: { textStyle: {color: 'blue'} },
				colors : ['blue'],
				'width':400,
				'height':300
			}
			var chart = new google.charts.Bar(document.getElementById('salesAge_material'));
			chart.draw(data, google.charts.Bar.convertOptions(options));
		}
		
	</script>
</head>

<body>
	<!-- Preloader -->
	<div id="preloader">
		<div class="dorne-load"></div>
	</div>

	<!-- ***** Search Form Area ***** -->
	<div class="dorne-search-form d-flex align-items-center">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="search-close-btn" id="closeBtn">
						<i class="pe-7s-close-circle" aria-hidden="true"></i>
					</div>
					<form action="#" method="get">
						<input type="search" name="caviarSearch" id="search"
							placeholder="Search Your Desire Destinations or Events">
						<input type="submit" class="d-none" value="submit">
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- ***** Header Area Start ***** -->
	<header class="header_area" id="header">
		<div class="container-fluid h-100">
			<div class="row h-100">
				<div class="col-12 h-100">
					<nav class="h-100 navbar navbar-expand-lg">
						<a class="navbar-brand" href="index.html"><img
							src="img/core-img/logo.png" alt=""></a>
						<button class="navbar-toggler" type="button"
							data-toggle="collapse" data-target="#dorneNav"
							aria-controls="dorneNav" aria-expanded="false"
							aria-label="Toggle navigation">
							<span class="fa fa-bars"></span>
						</button>
						<!-- Nav -->
						<div class="collapse navbar-collapse" id="dorneNav">
							<ul class="navbar-nav mr-auto" id="dorneMenu">
								<li class="nav-item active"><a class="nav-link"
									href="index.html">Home <span class="sr-only">(current)</span></a>
								</li>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
									role="button" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">Explore <i class="fa fa-angle-down"
										aria-hidden="true"></i></a>
									<div class="dropdown-menu" aria-labelledby="navbarDropdown">
										<a class="dropdown-item" href="index.html">Home</a> <a
											class="dropdown-item" href="explore.html">Explore</a> <a
											class="dropdown-item" href="listing.html">Listing</a> <a
											class="dropdown-item" href="single-listing.html">Single
											Listing</a> <a class="dropdown-item" href="contact.html">Contact</a>
									</div></li>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#" id="navbarDropdown2"
									role="button" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">Listings <i class="fa fa-angle-down"
										aria-hidden="true"></i></a>
									<div class="dropdown-menu" aria-labelledby="navbarDropdown2">
										<a class="dropdown-item" href="index.html">Home</a> <a
											class="dropdown-item" href="explore.html">Explore</a> <a
											class="dropdown-item" href="listing.html">Listing</a> <a
											class="dropdown-item" href="single-listing.html">Single
											Listing</a> <a class="dropdown-item" href="contact.html">Contact</a>
									</div></li>
								<li class="nav-item"><a class="nav-link"
									href="contact.html">Contact</a></li>
							</ul>
							<!-- Search btn -->
							<div class="dorne-search-btn">
								<a id="search-btn" href="#"><i class="fa fa-search"
									aria-hidden="true"></i> Search</a>
							</div>
							<!-- Signin btn -->
							<div class="dorne-signin-btn">
								<a href="#">Sign in or Register</a>
							</div>
							<!-- Add listings btn -->
							<div class="dorne-add-listings-btn">
								<a href="#" class="btn dorne-btn">+ Add Listings</a>
							</div>
						</div>
					</nav>
				</div>
			</div>
		</div>
	</header>
	<!-- ***** Header Area End ***** -->

	<!-- ***** Welcome Area Start ***** -->
	<section class="dorne-welcome-area bg-img bg-overlay"
		style="background-image: url(wall.jpg);">
		<div class="container h-100">
			<div class="row h-100 align-items-center justify-content-center">
				<div class="col-12 col-md-10">
					<div class="hero-content">
						<h2>Discover places want to you</h2>
						<h4>This is the best guide of your business</h4>
					</div>
					<!-- Hero Search Form -->
					<div class="hero-search-form">

						<!-- Tabs Content -->
						<div class="tab-content" id="nav-tabContent">
							<div class="tab-pane fade show active" id="nav-places"
								role="tabpanel" aria-labelledby="nav-places-tab">
								<h6>What are you looking for?</h6>
								<form name="custom" action="commercial.do?command=getAll" method="post">
									<select class="custom-select" name="customSelect1" id="customSelect1" onChange="select_form1();">
										<option selected value="">시군구</option>
										<option value="강남구">강남구</option>
										<option value="강동구">강동구</option>
										<option value="강북구">강북구</option>
										<option value="강서구">강서구</option>
										<option value="관악구">관악구</option>
										<option value="광진구">광진구</option>
										<option value="구로구">구로구</option>
										<option value="금천구">금천구</option>
										<option value="노원구">노원구</option>
										<option value="도봉구">도봉구</option>
										<option value="동대문구">동대문구</option>
										<option value="동작구">동작구</option>
										<option value="마포구">마포구</option>
										<option value="서대문구">서대문구</option>
										<option value="서초구">서초구</option>
										<option value="성동구">성동구</option>
										<option value="성북구">성북구</option>
										<option value="송파구">송파구</option>
										<option value="양천구">양천구</option>
										<option value="영등포구">영등포구</option>
										<option value="용산구">용산구</option>
										<option value="은평구">은평구</option>
										<option value="종로구">종로구</option>
										<option value="중구">중구</option>
										<option value="중랑구">중랑구</option>
									</select> 
									<select class="custom-select" name="customSelect2" id="customSelect2" style="display: none" onChange="select_form2();">
										<option selected value="">상권형태</option>
									</select> 
									<select class="custom-select" name="customSelect3" id="customSelect3" style="display: none">
										<option selected value="">상권명</option>
									</select>
									<button type="submit" class="btn dorne-btn">
										<i class="fa fa-search pr-2" aria-hidden="false"></i> Search
									</button>
								</form>
							</div>
							<div class="tab-pane fade" id="nav-events" role="tabpanel"
								aria-labelledby="nav-events-tab">
								<h6>What are you looking for?</h6>
								<form action="#" method="get">
									<select class="custom-select">
										<option selected>Your Destinations</option>
										<option value="1">New York</option>
										<option value="2">Latvia</option>
										<option value="3">Dhaka</option>
										<option value="4">Melbourne</option>
										<option value="5">London</option>
									</select> <select class="custom-select">
										<option selected>All Catagories</option>
										<option value="1">Catagories 1</option>
										<option value="2">Catagories 2</option>
										<option value="3">Catagories 3</option>
									</select> <select class="custom-select">
										<option selected>Price Range</option>
										<option value="1">$100 - $499</option>
										<option value="2">$500 - $999</option>
										<option value="3">$1000 - $4999</option>
									</select>
									<button type="submit" class="btn dorne-btn">
										<i class="fa fa-search pr-2" aria-hidden="true"></i> Search
									</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Hero Social Btn -->
		

	</section>
	<!-- ***** Welcome Area End ***** -->

	<!-- ***** About Area Start ***** -->
	<section class="dorne-about-area section-padding-0-100">
		<div>
			<div class="row">
				<div class="col-12">
					<div class="about-content text-center">
						<h2>
							Discover your city with <br> <span>Dorne</span>
						</h2>
						<p>Class aptent taciti sociosqu ad litora torquent per conubia
							nostra, per inceptos himenaeos. Fusce quis tempus elit. Sed
							efficitur tortor neque, vitae aliquet urna varius sit amet. Ut
							rhoncus, nunc nec tincidunt volutpat, ex libero.</p>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- ***** About Area End ***** -->

	<!-- ***** Population data ***** -->
	<section
		class="dorne-editors-pick-area bg-img bg-overlay-9 section-padding-100"
		style="background-image: url(img/bg-img/hero-2.jpg);">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="section-heading text-center">
						<span></span>
						<h4>Who should be your main target ?</h4>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-12 col-lg-6">
					<div class="single-editors-pick-area wow fadeInUp"
						data-wow-delay="0.2s">
						<div class="editors-pick-info">
							<div class="places-total-destinations d-flex">
								<a href="#">Resident</a>
							</div>
							<div>
								<button  onclick="drawChart(-1);"><img src="left.png" width="25px" height="25px"></button>
								<button onclick="drawChart(1);"><img src="right.png"  width="25px" height="25px"></button>
							</div>
							<div id="columnchart_material"></div>
						</div>
					</div>
				</div>
				<div class="col-12 col-lg-6">
					<div class="single-editors-pick-area wow fadeInUp"
						data-wow-delay="0.4s">
						<img src="img/bg-img/editor-2.jpg" alt="">
						<div class="editors-pick-info">
							<div class="places-total-destinations d-flex">
								<a href="#">Barcelona</a>
							</div>
							<div class="add-more">
								<a href="#">+</a>
							</div>
						</div>
					</div>
					
				</div>
			</div>
		</div>
	</section>
	<!-- ***** Editor Pick Area End ***** -->

	<!-- ***** Features Destinations Area Start ***** -->
	<section class="graph">
		<div class="container-fluid">
			<div class="row">
				<div class="col-12">
					<div class="section-heading dark text-center">
						<span></span>
						<h4>매출</h4>
						<p>Editorâs pick</p>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-12 col-lg-6">
					<div class="single-editors-pick-area wow fadeInUp"
						data-wow-delay="0.2s">
						<div class="editors-pick-info">
							<div class="places-total-destinations d-flex">
								<a href="#">Sales amounts by business</a>
							</div>
							<div>
								<button onclick="salesBusiness(-1);"><img src="left.png" width="25px" height="25px"></button>
								<button onclick="salesBusiness(1);"><img src="right.png" width="25px" height="25px"></button>
							</div>
							<div id="salesDay_material"></div>
						</div>
					</div>
				</div>
				<div class="single-editors-pick-area wow fadeInUp"
						data-wow-delay="0.2s">
						<div class="editors-pick-info">
							<div class="places-total-destinations d-flex">
								<a href="#">Sales amounts by age</a>
							</div>
							<div>
								<button onclick="salesAge(-1);"><img src="left.png" width="25px" height="25px"></button>
								<button onclick="salesAge(1);"><img src="right.png" width="25px" height="25px"></button>
							</div>
							<div id="salesAge_material"></div>
						</div>
					</div>
			</div>
		</div>
	</section>
	<!-- ***** Features Destinations Area End ***** -->

	<!-- ***** Features Restaurant Area Start ***** -->
	
	<!-- ***** Features Restaurant Area End ***** -->

	<!-- ***** Features Events Area Start ***** -->
	<section class="graph" style="background-image: url(img/bg-img/hero-3.jpg)">
		<div class="container-fluid">
			<div class="row">
				<div class="col-12 col-lg-6">
					<div class="single-editors-pick-area wow fadeInUp"
						data-wow-delay="0.2s">
						<div class="editors-pick-info">
							<div class="places-total-destinations d-flex">
								<a href="#">Sales amounts by business</a>
							</div>
							<div>
								<button onclick="salesAge(-1);"><img src="left.png" width="25px" height="25px"></button>
								<button onclick="salesAge(1);"><img src="right.png" width="25px" height="25px"></button>
							</div>
							<div id="salesAge_material2"></div>
						</div>
					</div>
				</div>
				<div class="col-12 col-lg-6">
					<div class="single-editors-pick-area wow fadeInUp"
						data-wow-delay="0.4s">
						<img src="img/bg-img/editor-2.jpg" alt="">
						<div class="editors-pick-info">
							<div class="places-total-destinations d-flex">
								<a href="#">Barcelona</a>
							</div>
							<div class="add-more">
								<a href="#">+</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- ***** Features Events Area End ***** -->

	<!-- ***** Clients Area Start ***** -->
	<div class="dorne-clients-area section-padding-100">
		<div class="container-fluid">
			<div class="row">
				<div class="col-12">
					<div
						class="clients-logo d-md-flex align-items-center justify-content-around">
						<img src="img/clients-img/1.png" alt=""> <img
							src="img/clients-img/2.png" alt=""> <img
							src="img/clients-img/3.png" alt=""> <img
							src="img/clients-img/4.png" alt=""> <img
							src="img/clients-img/5.png" alt="">
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ***** Clients Area End ***** -->

	<!-- ****** Footer Area Start ****** -->
	<footer class="dorne-footer-area">
		<div class="container-fluid">
			<div class="row">
				<div
					class="col-12 d-md-flex align-items-center justify-content-between">
					<div class="footer-text">
						<p>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							Copyright &copy;
							<script>document.write(new Date().getFullYear());</script>
							All rights reserved | This template is made with <i
								class="fa fa-heart-o" aria-hidden="true"></i> by <a
								href="https://colorlib.com" target="_blank">Colorlib</a>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						</p>
					</div>
					<div class="footer-social-btns">
						<a href="#"><i class="fa fa-linkedin" aria-haspopup="true"></i></a>
						<a href="#"><i class="fa fa-behance" aria-hidden="true"></i></a> <a
							href="#"><i class="fa fa-dribbble" aria-hidden="true"></i></a> <a
							href="#"><i class="fa fa-twitter" aria-haspopup="true"></i></a> <a
							href="#"><i class="fa fa-facebook" aria-haspopup="true"></i></a>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<!-- ****** Footer Area End ****** -->

	<!-- jQuery-2.2.4 js -->
	<script src="js/jquery/jquery-2.2.4.min.js"></script>
	<!-- Popper js -->
	<script src="js/bootstrap/popper.min.js"></script>
	<!-- Bootstrap-4 js -->
	<script src="js/bootstrap/bootstrap.min.js"></script>
	<!-- All Plugins js -->
	<script src="js/others/plugins.js"></script>
	<!-- Active JS -->
	<script src="js/active.js"></script>
</body>

</html>