<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Badminton</title>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <style type="text/css">
        .bg{
            border-radius: 85px;
            background: linear-gradient(315deg, #9ab9b8, #b7dcda);
            box-shadow:  -6px -6px 12px #95b3b1, 6px 6px 12px #c1e9e7;
            margin-bottom: 10px;
            border: none !important;
        }
        .bg-white{
            background: #ffffff !important;
        }
        .bg-radius{
            border-radius: 30px;
        }
        html, body {
    margin: 0;
    height: 100%;
    background: #afd2d0;

}
.row {
    margin-right: 0px !important; 
    margin-left: 0px !important;
    }
    </style>
</head>

<body>
    <div class="container-fluid" style="padding-left: 0px !important;padding-right: 0px !important;">
        <jsp:include page="headAdmin.jsp"></jsp:include>
        <div class="row">
            <div class="col-2"  style="margin-top: 15%;">
                <jsp:include page="menu.jsp"></jsp:include>
            </div>
            <div class="col-10"  style="margin-top: 5%;">
                <div class="bg bg-white bg-radius" >  
                    <h2 style="text-align: center;"> Quản lý cửa hàng</h2>  
                <div>
            </div>
        </div>
    </div>
</body>
</html>