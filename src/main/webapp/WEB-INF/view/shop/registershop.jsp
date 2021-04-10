<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Badminton</title>
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Material Design Bootstrap -->
<link href="css/mdb.min.css" rel="stylesheet">
<!-- Your custom styles (optional) -->
<link href="css/style.min.css" rel="stylesheet">

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>


</head>

<body style="text-align: center;">
	
	<!-- Default form login -->
	
	<div class="text-center border border-light p-5"  style="display: inline-block; margin-top: 10rem ;width: 470px; ">
	    <p class="h4 mb-4">Tạo thông tin shop</p>
	
	    <!-- Email -->
	    <input type="text" id="nameShop" class="form-control mb-4" placeholder="Tên shop">
	
	    <!-- Password -->
	    <input type="text" id="addressShop" class="form-control mb-4" placeholder="Địa chỉ shop">
	
		<input type="text" id="numberPhoneShop" class="form-control mb-4" placeholder="Số điện thoại shop">
		
	    <!-- Sign in button -->
	    <button class="btn btn-info btn-block my-4" type="button" onclick="register()" >Tạo shop</button>
	</div>
	
	
	
	<div class="modal fade" id="nameShopExist" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="z-index: 1051; padding-top: 220px;">
		<div class="modal-dialog modal-notify modal-success" role="document" style="right: 9px">
			<!--Content-->
			<div class="modal-content ">
				<!--Body-->
				<div class="modal-body">
					<div class="text-center">
						<i class="fas fa-times fa-4x mb-3 animated rotateIn"
							style="color: red;"></i>
						<p>Tên shop đã có người sử dụng </p>
					</div>
				</div>
			</div>
		</div>
	</div>
	
<div class="modal fade bottom" id="registerShopFail" tabindex="-1" 
  >
  <!-- Add class .modal-frame and then add class .modal-bottom (or other classes from list above) to set a position to the modal -->
  <div class="modal-dialog modal-frame modal-top" role="document">

    <div class="modal-content">
      <div class="modal-body">
        <div class="row d-flex justify-content-center align-items-center">

          <p class="pt-3 pr-2">Đăng ký shop thất bại</p>
        </div>
      </div>
    </div>
  </div>
</div>
	

	<!-- Bootstrap core JavaScript -->
	<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>

	

<script type="text/javascript">

	function register(){
		$.ajax(
			'registerShop', 
			{
		    type: 'POST',  // http method
		    data: {
		    	nameShop: $("#nameShop").val(),
		    	addressShop: $("#addressShop").val(),
			    numberPhoneShop : $("#numberPhoneShop").val()
			       
			     },  // data to submit
		    success: function (data, status, xhr) {
		        if(data == "exist"){
		        	$('#nameShopExist').modal('show');		   
		        } 
		        if(data == "registerShopSuccess"){
		        	window.location.href = "http://localhost:8080/saleschannel?success=true";			        		
				 }
				 if(data == "registerShopFail"){
					 $('#registerShopFail').modal('show');
				 }
				 
		    },
		    error: function (jqXhr, textStatus, errorMessage) {
		            $('p').append('Error' + errorMessage);
		    }
		});
		
		} 
	</script>
	
		 


</body>
	

</html>