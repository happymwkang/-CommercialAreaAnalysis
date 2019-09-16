<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
    <title>Commertial Area </title>

    <!-- Favicon -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Stylesheet -->
    <link href="style.css" rel="stylesheet">

    <!-- Responsive CSS -->
    <link href="css/responsive/responsive.css" rel="stylesheet">
    
    
 <script type="text/javascript">
 var selectedIndex1="";
 var selectedIndex2="";
 var selectedIndex3="";
 var select_text1="";
 var select_text2="";
 var select_text3="";
 var select_value1="";
 var select_value2="";
 var select_value3=""; 
 var arrSelect = [];
 var selectHtml
 
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
		 selectArea(select_value1, select_value2)
	 }else if(select_text2 == ""){
		 document.custom.customSelect3.style.display="none";
	 }

	}
	
function selectDivision(guNm) {
	 var xhttp = new XMLHttpRequest();
     xhttp.onreadystatechange = function() {
       if (this.readyState == 4 && this.status == 200) {
    	   alert(this.responseText);
    	   selectHtml = '<option selected value=\"\">상권형태</option>';
    	   arrSelect = eval(this.responseText);
    	   for(var i = 0 ; i < arrSelect.length ; i++){
    			  selectHtml += '<option value=\"'+arrSelect[i]+'\">'+arrSelect[i]+'</option>';
    		 }
    			document.getElementById("customSelect2").innerHTML = selectHtml;
    			
       }
     };
     xhttp.open("POST", "CommertialController?command=getSelectDivision&guNm="+guNm, true);
     xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
     xhttp.send();
   }
   
function selectArea(guNm, divisionNm) {
	 var xhttp = new XMLHttpRequest();
     xhttp.onreadystatechange = function() {
       if (this.readyState == 4 && this.status == 200) {
    	   selectHtml = '<option selected value=\"\">상권명</option>';
    	   arrSelect = eval(this.responseText);
    	   for(var i = 0 ; i < arrSelect.length ; i++){
    			  selectHtml += '<option value=\"'+arrSelect[i]+'\">'+arrSelect[i]+'</option>';
    		 }
    			document.getElementById("customSelect3").innerHTML = selectHtml;
    			
       }
     };
     xhttp.open("POST", "CommertialController?command=getSelectArea&guNm="+guNm+"&divisionNm="+divisionNm, true);
     xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
     xhttp.send();
   }
 
 </script>
</head>
<body>
 <!-- ***** Welcome Area Start ***** -->
    <section class="dorne-welcome-area bg-img bg-overlay" style="background-image: url(img/bg-img/hero-1.jpg);">
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
                            <div class="tab-pane fade show active" id="nav-places" role="tabpanel" aria-labelledby="nav-places-tab">
                                <h6>What are you looking for?</h6>
                                <form name ="custom" action="CommertialController?command=commertialAnalysis" method="post" >
                                    <select class="custom-select" name ="customSelect1"  id = "customSelect1" onChange="select_form1();">
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
                                    
                                    
                                     <select class="custom-select" name ="customSelect2" id = "customSelect2" style="display:none" onChange="select_form2();">
                                        <option selected value="">상권형태</option>
                                    </select> 
                                    
                                    <select class="custom-select" name ="customSelect3" id = "customSelect3" style="display:none">
                                        <option selected value="">상권명</option>
                                    </select>
                                    <button type="submit" class="btn dorne-btn"><i class="fa fa-search pr-2" aria-hidden="false"></i> Search</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Hero Social Btn -->
        <div class="hero-social-btn">
            <div class="social-title d-flex align-items-center">
                <h6>Follow us on Social Media</h6>
                <span></span>
            </div>
            <div class="social-btns">
                <a href="#"><i class="fa fa-linkedin" aria-haspopup="true"></i></a>
                <a href="#"><i class="fa fa-behance" aria-hidden="true"></i></a>
                <a href="#"><i class="fa fa-dribbble" aria-hidden="true"></i></a>
                <a href="#"><i class="fa fa-twitter" aria-haspopup="true"></i></a>
                <a href="#"><i class="fa fa-facebook" aria-haspopup="true"></i></a>
            </div>
        </div>
    </section>
    <!-- ***** Welcome Area End ***** -->
</body>
</html>