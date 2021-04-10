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
  
</head>
<body>			
  <jsp:include page='headShop.jsp'></jsp:include>
  <div class="container-fluid" style="padding: 0px !important; margin-top: 1rem;">
    <div class="row">
        <jsp:include page='menuleft.jsp'></jsp:include>  
        <div class="col-lg-10 col-md-12 col-sm-12" style="margin-top: 8rem;">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">Mã</th>
                        <th scope="col">Tên sản phẩm</th>
                        <th scope="col">Số lượng</th>
                        <th scope="col">Thời gian</th>
                        <!-- <th scope="col"></th> -->
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listOrder}" var="order">
                        <tr>
                            <td id="idOrder">${order.idOrder}</td>
                            <td>${order.nameProduct}</td>
                            <td>${order.numberProductPurchased}</td>
                            <td class="dateBuy">${order.dateBuy}</td>
                            <!-- <td>
                                <button type="button" class="btn btn-blue confirmation" value="0">xác nhận</button>
                                <button type="button" class="btn btn-danger confirmation" value="1">Hủy</button>
                            </td> -->
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
   
 </div>			

			


  	<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
	  <!-- Bootstrap tooltips -->
	  <script type="text/javascript" src="js/popper.min.js"></script>
	  <!-- Bootstrap core JavaScript -->
	  <script type="text/javascript" src="js/bootstrap.min.js"></script>
	  <!-- MDB core JavaScript -->
	  <script type="text/javascript" src="js/mdb.min.js"></script>
	  <!-- Initializations -->

	<script>
        // $('.confirmation').click(function(e){
        //     var idOrder = $(this).parent('td').parent('tr').find('#idOrder').html();
        //     var status = $(this).val();
        //     $(this).parent('td').parent('tr').remove()
        //     $ajax(
        //         'beingtransported',
        //         {
        //             type: 'POST',
        //             data:{
        //                 status: status,
        //                 idOrder : idOrder
        //             },
        //             success:function(data,statusm,xhr){

        //             },
        //             error:function(jqXhr,textStatus,errorMessage){
        //                 $('p').append('Error'+errorMessage);
        //             }
        //         }
        //     )
        // })
    </script>
</body>


</html>