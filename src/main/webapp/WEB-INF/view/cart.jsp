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
.price-number{
font-size: 1.5rem;
font-weight: 600;
}

.img-fluid, .modal-dialog.cascading-modal.modal-avatar .modal-header, .video-fluid {
    max-width: 35%;
    height: auto;
}


.modal-dialog {
    max-width: 700px;
    margin: 1.75rem auto;
}

</style>




</head>
<body>
<jsp:include page="head.jsp"></jsp:include>	
	
<div class="container">
	<div class="row" style="margin-top: 6rem;">
	<div class="col">
		<table class="table table-image" id="allProduct">
			  <thead>
			    <tr>
			      <th scope="col">STT</th>
			      <th scope="col">Hình ảnh</th>
			      <th scope="col">Tên sản phẩm</th>
			      <th scope="col">size</th>	
			      <th scope="col" style="width: 13%;">Số Lượng</th>
			      <th scope="col">Giá</th>			     
			      <th scope="col"></th>
			    </tr>
			  </thead>
			  <tbody>
			  <c:forEach items="${listProduct}" var="product">
			<tr>
			
			  <th scope="row">1</th>
			  <td class="w-25">
			   <input type="hidden" id="size" value="${product.size}">
               <input type="hidden" id="idProduct" value="${product.idProduct}">
               <input type="hidden" id="priceProduct" value="${product.price}">
			  <img src="getImage?idProduct=${product.idProduct}" class="img-fluid img-thumbnail" >
			</td>
				<td >${product.nameProduct}</td>
				<td>${product.nameSize}</td>
				<td>
				   <button onclick="plus(this,0)"  style="width: 2rem;">-</button>
                   <input class="quantity" min="0" id="quantity" name="quantity" value="${product.numberOfSize}" type="number" style="width: 3rem;" onkeypress="return (event.charCode == 8 || event.charCode == 0 || event.charCode == 13) ? null : event.charCode >= 48 && event.charCode <= 57" name="itemConsumption" >
                   <button  onclick="plus(this,1)"  style="width: 2rem;">+</button>
                   </td>
                   <td class="price-number" id="price"><fmt:formatNumber value="${product.numberOfSize * product.price}" /></td>
				 
				<td>
            	  <span class="table-remove"><button type="button" id="remove" class="btn btn-danger btn-rounded btn-sm my-0">Xóa</button></span>
            	</td>
            	
			</tr>
				</c:forEach>
				<tr>
                <td colspan="3"></td>
                <td>
                  <h4 class="mt-2">
                    <strong>Tạm tính:</strong>
                  </h4>
                </td>
                <td class="text-right">
                  <h4 class="mt-2">
                    <strong class="price-number" id="totalPrice"></strong>
                  </h4>
                </td>
                <td colspan="3" class="text-right">
                
                  <button type="button" class="btn btn-primary btn-rounded waves-effect waves-light" onclick="checkCart()">Thanh toán
                    <i class="fas fa-angle-right right"></i>
                  </button>
                  
                </td>
              </tr>
		  </tbody>
		</table>
		</div> 
	</div>
</div>		

	<footer class="page-footer text-center font-small mt-4 wow fadeIn" style="width: 100%; position: fixed;">
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



<!-- Notice of successful registration  -->
	<div class="modal fade" id="errorMessage" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalCenterTitle">Thông báo</h5>
				</div>
				<div class="modal-body" id="paymentError">
					
					
				</div>

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

<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
	<script type="text/javascript">
    // Animations initialization
    new WOW().init();

	  function plus(elm,upDown){
		  if(upDown ==0){
			  elm.parentNode.querySelector('input[type=number]').stepDown()
				  }else{
			  elm.parentNode.querySelector('input[type=number]').stepUp();	
			}
			var idProduct = $(elm).parents('tr').find('#idProduct').val();
			var numberOfSize = $(elm).parents('tr').find('#quantity').val();
			var size = $(elm).parents('tr').find('#size').val();
			

			$.ajax(
					'plus', 
					{
				    type: 'POST',  // http method
				    data: {
				    	idProduct : idProduct,
				    	numberOfSize : numberOfSize,
				    	size :	size,    	
					     },  // data to submit
				    success: function (data, status, xhr) {
				    	if(data == 1){
				    		$(elm).parents('tr').find('#price').html(			    
				    				formatNumber($(elm).parents('tr').find('#quantity').val() * $(elm).parents('tr').find('#priceProduct').val())
						    		);
				    		totalPrice();
				    	}
				    },
				    error: function (jqXhr, textStatus, errorMessage) {
				            $('p').append('Error' + errorMessage);
				    }
				});
		  }

	  function formatNumber(nStr)
	  {
		    nStr += '';
		    x = nStr.split('.');
		    x1 = x[0];
		    x2 = x.length > 1 ? '.' + x[1] : '';
		    var rgx = /(\d+)(\d{3})/;
		    while (rgx.test(x1)) {
		        x1 = x1.replace(rgx, '$1' + '.' + '$2');
		    }
		    return x1 + x2;
	  }

	  function totalPrice() {
		  var total = 0;
		  $('table > tbody  > tr').each(function(index, tr) { 
			  if($(tr).find("#price").text().replace(/\./g,"") != ""){
				   total += parseInt($(tr).find("#price").text().replace(/\./g,""));
				  }
			});
		  $('#totalPrice').html(formatNumber(total));
		}

	  $( document ).ready(totalPrice());

	  $("#allProduct").on("click", "#remove", function() { 
		  
		    var idProduct = $(this).parents('tr').find('#idProduct').val();
			var size = $(this).parents('tr').find('#size').val();
			$(this).closest("tr").remove();

			$.ajax(
					'deleteRowCart', 
					{
				    type: 'POST',  // http method
				    data: {
				    	idProduct : idProduct,
				    	size :	size,    	
					     },  // data to submit
				    success: function (data, status, xhr) {
				    	if(data == 1){
				    		totalPrice();
				    	}
				    },
				    error: function (jqXhr, textStatus, errorMessage) {
				            $('p').append('Error' + errorMessage);
				    }
				});
		});


	  $( document ).ready(function() {
			var loginSuccess = "${loginSuccess}";
			if(loginSuccess != ""){
				$('#nameAvatarLogin').css("visibility","visible");
				$('#btnLogin').css("visibility","hidden");
			}
		  });

	
	  function checkCart()  { 
			$.ajax(
					'checkCart', 
					{
				    type: 'POST',  // http method
				    data: {   	
					     },  // data to submit
				    success: function (data, status, xhr) {
					    if(data.length == 0){
					    	window.location='/pay';
						 }else{
							 $( "#paymentError" ).html("");
							 $.each(data, function( index, row ) { 	
						    		$( "#paymentError" ).append( 
								    "<div class='row'><div class='col'>"+row.nameProduct+"</div><div class='col'>"+row.messageError+"</div></div><hr> ");
						    	});
							  $("#errorMessage").modal('show');	
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
	