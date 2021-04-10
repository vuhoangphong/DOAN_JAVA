<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  <title>Badminton</title>
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
  <!-- Bootstrap core CSS -->
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <!-- Material Design Bootstrap -->
  <link href="css/mdb.min.css" rel="stylesheet">
  <!-- Your custom styles (optional) -->
  <link href="css/style.min.css" rel="stylesheet">

  <link href="css/menuleft.css" rel="stylesheet">
 <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<!-- chart -->
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
  
</head>
<body>			
  <jsp:include page='headShop.jsp'></jsp:include>
  <div class="container-fluid" style="padding-left: 0px !important; margin-top: 1rem;">
    <div class="row">
        <jsp:include page='menuleft.jsp'></jsp:include>  
        <div class="col-lg-10 col-md-12 col-sm-12" style="margin-top: 8rem;">
           <div class="row">
                <div class="col-9">
                    <h5 style="margin-top: 5px;float: right;">Chọn năm </h5>
                </div>
                <div class="col">
                    <div class="input-group">
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
           <div class="row">
               <div class="col">
                   <canvas id="myChart1"></canvas>
               </div>
               <div class="col">
               </div>
           </div>
           <canvas id="myChart"></canvas>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-10 col-md-12 col-sm-12" style="margin-top: 8rem;">
           
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
                text: 'THÔNG KÊ HÓA ĐƠN TRONG NĂM'
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
        data: data,
        backgroundColor:['rgb(6, 147, 227)',  'rgb(0, 208, 132)','rgb(255, 99, 132)'] ,
        }]
    },
    options: {}
});
})


    $('#year').change(function(e){
        var year = $( "#year option:selected" ).text();
        window.location.href = "http://localhost:8080/statisticalyear?Year="+year;
    });



 </script>
  	<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
	  <!-- Bootstrap tooltips -->
	  <script type="text/javascript" src="js/popper.min.js"></script>
	  <!-- Bootstrap core JavaScript -->
	  <script type="text/javascript" src="js/bootstrap.min.js"></script>
	  <!-- MDB core JavaScript -->
	  <script type="text/javascript" src="js/mdb.min.js"></script>
	  <!-- Initializations -->

	
</body>


</html>