<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BADMINTON</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/rSlider.min.css">
    <!-- Material Design Bootstrap -->
    <link href="css/mdb.min.css" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="css/style.min.css" rel="stylesheet">
<script src="js/rSlider.min.js"></script>
</head>
<body>
    <jsp:include page="head.jsp"></jsp:include>
    <main>
        <c:if test="${check == true}">
            <h4 style="margin: 9rem;">Tài khoản của bạn đã được kích hoạt thành công </h4>
        </c:if>
        <c:if test="${check == false}">
            <h4 style="margin: 9rem;">Mã kích hoạt của bạn không hợp lệ </h4>
        </c:if>
    </main>
   
</body>
</html>