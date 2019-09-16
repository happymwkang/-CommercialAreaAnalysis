<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <script type="text/javascript">
 
 
 var gubun = document.getElementById("custom-select1").options[document.getElementById("custom-select1").selectedIndex].value;
 console.log(gubun);
 
 </script>
</head>
<body>
에러낫어요
${responseScope.errorMsg }
</body>
</html>