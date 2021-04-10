<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Badminton</title>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- chart -->
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>

    <style type="text/css">
        .bg{
            border-radius: 85px;
            background: linear-gradient(315deg, #9ab9b8, #b7dcda);
            box-shadow:  -6px -6px 12px #95b3b1, 6px 6px 12px #c1e9e7;
            margin-bottom: 10px;
            border: none !important;
        }
        .bg-white{
            background: linear-gradient(315deg, #f7f7f7, #ecf1f1) !important;
        }
        .bg-radius{
            border-radius: 30px;
        }
        html, body {
    margin: 0;
    height: 100%;
    background: #afd2d0
}

.row {
    margin-right: 0px !important; 
    margin-left: 0px !important;
    }
    </style>
</head>

<body>
    <div class="container-fluid" style="padding-left: 0px !important;padding-right: 0px !important;">
        <jsp:include page='headAdmin.jsp'></jsp:include>
       
        <div class="row">
            <div class="col-2" style="margin-top: 15%;">
                <jsp:include page='menu.jsp'></jsp:include>
            </div>
            <div class="col-10" style="margin-top: 5%;">
                <div class="bg bg-white bg-radius" >
                    <h3 style="margin: 10px; text-align: center;">Thống kê hóa đơn</h3>
                   <div class="row">
                        <div class="col">
                            <canvas id="myChart1"></canvas>
                        </div>
                        <div class="col">
                            <div class="input-group" >
                                <h5 style="margin-top: 5px;">Chọn năm </h5>
                                <div class="input-group" style="width: 30%;">
                                    <select class="form-control" name="" id="year" style="margin-right: 1rem;">
                                        <option>2021</option>
                                        <option>2020</option>
                                        <option>2019</option>
                                        <option>2018</option>
                                        <option>2017</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                   </div>
                   <canvas id="myChart"></canvas>
                </div>
            </div>
        </div>
    </div>
    <script>
    $(document).ready(function(e){
        var ctx = document.getElementById('myChart').getContext('2d');
        var dataCancel = "${dataCancel}".split(/\s+/);
        var dataSuccess = "${dataSuccess}".split(/\s+/);
        var dataOrder = "${dataOrder}".split(/\s+/);
       
        var chart = new Chart(ctx, {
         // The type of chart we want to create
         type: 'bar',

          // The data for our dataset
        data: {
            labels: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7','Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'],
            datasets: [{
                label: 'Đơn hủy',
                backgroundColor: 'rgb(255, 99, 132)',
                borderColor: 'rgb(255, 99, 132)',
                data: dataCancel
                
             },{
                label: 'Đơn thành công',
                backgroundColor: 'rgb(0, 208, 132)',
                borderColor: 'rgb(0, 208, 132)',
                data: dataSuccess
             },{
                label: 'Tổng đơn đặt hàng',
                backgroundColor: 'rgb(6, 147, 227)',
                borderColor: 'rgb(6, 147, 227)',
                data: dataOrder
             }
             ]
         },
         // Configuration options go here
         options: {
            title: {
                display: true,
                text: 'THÔNG KÊ HÓA ĐƠN TRONG NĂM THEO THÁNG'
            }, 
            layout: {
                padding: {
                    left: 50,
                    right: 0,
                    top: 0,
                    bottom: 0
                 },
             }
         }
        }
    ); 
})
    
$(document).ready(function(e){
    var ctx = document.getElementById('myChart1').getContext('2d');
    var data = ["${sumOrder}","${sumSuccess}","${sumCancel}"];
    var myDoughnutChart = new Chart(ctx, {
    type: 'pie',
    data: { 
        labels: [ 'Tổng đơn đặt hàng','Đơn thành công','Đơn hủy'],
        datasets: [{
        data:data,
        backgroundColor:['rgb(6, 147, 227)',  'rgb(0, 208, 132)','rgb(255, 99, 132)'] ,
        }]
    },
    options: {
        title: {
                display: true,
                text: 'TỔNG SỐ HÓA ĐƠN'
            }
    }
});
})

$('#year').change(function(e){  
        var year = $( "#year option:selected" ).text();
        window.location.href = "http://localhost:8080/adminyear?Year="+year;
    });
 </script>
 
</body>
</html>
