<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Babminton</title>
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<link rel="stylesheet" href="css/rSlider.min.css">
<script src="js/rSlider.min.js"></script>



<!-- Material Design Bootstrap -->
<link href="css/mdb.min.css" rel="stylesheet">
<!-- Your custom styles (optional) -->
<link href="css/style.min.css" rel="stylesheet">

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>




<style type="text/css">


</style>



</head>

<body>
	<jsp:include page="head.jsp"></jsp:include>

    <div class="container wow fadeIn" style="margin-top: 8rem;">

      <!-- Heading -->
      <h2 class="my-5 h2 text-center">Thanh toán</h2>
	
      <!--Grid row-->
      <div class="row">

        <!--Grid column-->
        <div class="col-md-7 mb-4">

          <!--Card-->
          <div class="card">

            <!--Card content-->
          
<div class="card-body">
            <!--full name-->
              <div class="row">
                <div class="col mb-2">                 
                  <div class="md-form ">
                    <input type="text"  id ="fullName" name="fullName" class="form-control" value="${user.getFullName()}">
                    <label for="firstName" class="">Họ và Tên</label>
                  </div>
                </div>             
              </div>
          
              <!--email-->
              <div class="md-form mb-5">
                <input type="text"  id="email" name="email" class="form-control" placeholder="name@gmail.com" value="${user.getEmail()}">
                <label for="email" class="">Email </label>
              </div>

              <!--address-->
              <div class="md-form mb-5">
                <input type="text" id="address"  name="address" class="form-control" placeholder="" required value="${user.getAddress()}">
                <label for="address" class="">Địa chỉ</label>
              </div>

              <!--address-2-->
              <div class="md-form mb-5">
                <input type="text"  id="phoneNumber" name="phoneNumber" class="form-control" placeholder="0327xxx436"  value="${user.getPhoneNumber()}">
                <label for="address-2" class="">Số điện thoại</label>
              </div>

              <!--Grid row-->
              <div class="row">

                <!--Grid column-->
                <div class="col-lg col-md-12 mb-4">

                  <label for="country">Chọn công ty vận chuyển</label>
                  <select class="custom-select d-block w-100" id="shippees" required>
                    <option  value="none" selected disabled hidden="">Chọn</option>
                    <c:forEach items="${listShippers}" var="shippers">
                    	<option value="${shippers.freight}" data-index="${shippers.idCompanyShipper}">${shippers.nameCompanyShipper}</option>
                    </c:forEach>                  
                  </select>	                
                </div>
            
                <div class="col-lg col-md-6 mb-4">

                  <label for="zip">Giá vận chuyến</label>
                  <input type="text" class="form-control" id="freight" placeholder="" required disabled="disabled">


                </div>
                <!--Grid column-->

              </div>
              <!--Grid row-->

            

              <div class="d-block my-3">
                <div class="custom-control custom-radio">
                  <input id="paymentOnDelivery" name="payment" type="radio" class="custom-control-input" checked required value="1">
                  <label class="custom-control-label" for="paymentOnDelivery">Thanh toán khi nhận hàng.</label>
                </div>
                
                <div class="custom-control custom-radio">
                  <input id="paymentWithMOMO" name="payment" type="radio" class="custom-control-input" required value="2">
                  <label class="custom-control-label" for="paymentWithMOMO">Thanh toán qua MOMO.</label>
                </div>         
              </div>
              <hr class="mb-4">
              <button class="btn btn-primary btn-lg btn-block" type="button" onclick="payment()">Thanh toán</button>
              
 			<input type="hidden" name="idCompanyShipper" id="idCompanyShipper"> 
 			<input type="hidden" name="totalMoney" id="totalMoney"> 
 			
            </div>

          </div>
          <!--/.Card-->

        </div>
        <!--Grid column-->

        <!--Grid column-->
        <div class="col-md-5 mb-4">

          <!-- Heading -->
          <h4 class="d-flex justify-content-between align-items-center mb-3">
            <span class="text-muted">Giỏ hàng</span>
            <span class="badge badge-secondary badge-pill">${listProduct.size()}</span>
          </h4>

          <!-- Cart -->
          <ul class="list-group mb-3 z-depth-1">
          	<c:forEach items="${listProduct}" var="product">
            <li class="list-group-item d-flex justify-content-between lh-condensed cart" >
              <div>
                <h6 class="my-0">${product.nameProduct}</h6>
                <small style="color: #37a8f0">${product.nameSize}</small>
              </div>
              <span class="text-muted" ><fmt:formatNumber value="${product.numberOfSize *product.price}" /> đ</span>
            </li>
            </c:forEach>
          </ul>
          <!-- Cart -->

          <!-- Promo code -->
          <div class="card p-2">
            <div class="input-group">
            	<div class="col-md-5">
            		 <span style="font-size: xx-large; font-weight: 500;">Tổng tiền:</span>
            	</div>
            	<div class="col">
            		 <span style="font-size: xx-large; font-weight: 500;" id="total"></span>
            	</div>                        
            </div>
          </div>
          <!-- Promo code -->

        </div>
        <!--Grid column-->

      </div>
      <!--Grid row-->

    </div>

  <!--Main layout-->

 	<footer class="page-footer text-center font-small mt-4 wow fadeIn">
		<hr class="my-4">
		<div class="pb-4">
			<a href="https://www.facebook.com/profile.php?id=100009263859905" target="_blank"> <i
				class="fab fa-facebook-f mr-3"></i>
			</a> <a href="https://www.facebook.com/profile.php?id=100009263859905"
				target="_blank"> <i class="fab fa-google-plus-g mr-3"></i>
			</a> <a href="https://www.facebook.com/profile.php?id=100009263859905"
				target="_blank"> <i class="fab fa-github mr-3"></i>
			</a> 
		</div>
		
		<div class="footer-copyright py-3">
			© 2020 : Phát triển bởi Hoàng Đại  
		</div>		
	</footer>
	
	
	<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
	<!-- Bootstrap tooltips -->
	<script type="text/javascript" src="js/popper.min.js"></script>
	<!-- Bootstrap core JavaScript -->
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<!-- MDB core JavaScript -->
	<script type="text/javascript" src="js/mdb.min.js"></script>
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

	<!-- Initializations -->
	<script type="text/javascript">
    // Animations initialization
    new WOW().init();

    $( document ).ready(function() {
		var loginSuccess = "${loginSuccess}";
		if(loginSuccess != ""){
			$('#nameAvatarLogin').css("visibility","visible");
			$('#btnLogin').css("visibility","hidden");
		}
	  }
    	
	  );
	  
    $("#shippees").change(function (){
		var selectPortfolio = $(this).children("option:selected").val();
		$("#freight").val(formatNumber(selectPortfolio));
		selectPortfolio =parseInt(selectPortfolio) + parseInt(sumTotal());
		$("#total").html(formatNumber(selectPortfolio));
		$("#totalMoney").val(selectPortfolio);
    })
    
     function formatNumber(nStr)
	  {
		    nStr += '';
		    x = nStr.split('.');
		    x1 = x[0];
		    
		    var rgx = /(\d+)(\d{3})/;
		    while (rgx.test(x1)) {
		        x1 = x1.replace(rgx, '$1' + ',' + '$2');
		    }
		    return x1+"đ" ;
	  }
	  function sumTotal(){
		var sum = 0;
 		$( ".cart" ).each(function( index ) {
			sum+= parseInt($( this ).find('span').text().replace(/\.|đ| /g,""));
    	});
		return sum;
	  }
    $( document ).ready(function total(){
		$("#total").html(formatNumber(sumTotal()));
		$("#totalMoney").val(sumTotal());
    });

    $("#addOrder").submit(function(){
   	 $("#idCompanyShipper").val($("#shippees option:selected").data("index"));
   	});


    function payment(){
    	 $("#idCompanyShipper").val($("#shippees option:selected").data("index"));
    	var payment = 0;
    	
    	$('input:radio[name="payment"]').each(function(){
			if($(this).prop('checked')){
				payment = $(this).val();
			}					
		})
		$.ajax(
			'payment', 
			{
		    type: 'POST',  // http method
		    data: {
			     fullName: $("#fullName").val(),
			     email: $("#email").val(),
			     address: $("#address").val(),
			     email: $("#email").val(),
			     phoneNumber: $("#phoneNumber").val(),	
			     payment: payment,
			     totalMoney: $("#totalMoney").val(),
			     idCompanyShipper: $("#idCompanyShipper").val()				     
			     },  // data to submit
		    success: function (data, status, xhr){ 
			        if(data != null){
			        	window.location.href = data;
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
	
	