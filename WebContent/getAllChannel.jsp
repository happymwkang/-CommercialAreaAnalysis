<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%--${requestScope.getAllChannel.year}  --%>
[['year'<%out.print(", '점포수'],"); %>
<c:forEach items="${requestScope.testChannel}" var="dataAll" varStatus="index"> 
	<c:if test ="${index.last }">
	
		<%out.print(""); %>${dataAll.shopCnt}<%out.print("]"); %>
	</c:if>
	<c:if test ="${!index.last }">
		<%out.print("['"); %>${dataAll.year}<%out.print("',"); %>
		<%out.print(""); %>${dataAll.shopCnt}<%out.print("],"); %>
	</c:if> 
 	</c:forEach> 
 	]
</body>
</html>