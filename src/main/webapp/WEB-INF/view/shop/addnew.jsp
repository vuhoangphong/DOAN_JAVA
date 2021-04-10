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
<title></title>
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

<style type="text/css">
.md-form {
margin-top: 0px;
}

.md-form .form-control {
padding: 0.4rem }

.md-form label {
left: 50px
}


</style>
</head>

<body>

<form action="addProduct" method="post" enctype="multipart/form-data"  id="addProduct">

	<div
		style="padding-left: 230px; padding-right: 40px; padding-top: 20px;"
		id="basicInformation">
		<div class="card" style="height: 600px">
			<div class="card-body" style="padding-left: 50px; padding-top: 50px;">
				<h4 class="card-title">Thông tin cơ bản</h4>
				<div class="from-group row" style="flex-wrap: inherit;">
					<label for="sanpham"
						style="padding-left: 10px; padding-top: 8px; width: 130px;">Tên sản phẩm:</label>
					<div class="container">
						<input class="form-control" id="nameProduct" name="nameProduct" type="text" placeholder="Nhập tên sản phẩm" required="required" maxlength="60">
					</div>
				</div>

				<div class="from-group row"
					style="flex-wrap: inherit; padding-top: 30px;">
					<label for="sanpham"
						style="padding-left: 10px; padding-top: 8px; width: 130px;">Mô tả sản phẩm:</label>
					<div class="container">
						<textarea class="form-control" id="productDescription" name="productDescription" rows="7"></textarea>
					</div>
				</div>

				<div class="from-group row" style="flex-wrap: inherit; padding-top: 30px;">
					<label for="sanpham" style="padding-left: 10px; padding-top: 8px; width: 130px;">Danh mục:</label>
					<div style="width: 250px">
						<select class="protfolio custom-select" id="idPortfolio" name="idPortfolio" required="required">
							<option value=""></option>
							<c:forEach items="${listProductPortfolio}" var="product">
								<option value="${product.idType}">${product.nameType}</option>
							</c:forEach>

						</select>
					</div>

				</div>
				<div class="from-group row"
					style="flex-wrap: inherit; padding-top: 30px;">
					<label for="sanpham" style="padding-left: 10px; padding-top: 8px; width: 130px;">Thương hiệu:</label>
					<div style="width: 250px">
						<select class="browser-default custom-select" id="brand" name="idBrand" required="required">
							<option value=""></option>
						</select>
					</div>
				</div>

				<div class="from-group row"
					style="flex-wrap: inherit; padding-top: 30px;">
					<label for="sanpham" style="padding-left: 10px; padding-top: 15px; width: 130px;">Xuất xứ:</label>
					<div class="md-form">
						 <input type="text" id="origin" name="origin" class="form-control" required="required">
  						 <label for="origin">Nhập xuất xứ</label>
					</div>
				</div>

			</div>
		</div>
	</div>


		<div style="padding-left: 230px; padding-right: 40px; padding-top: 20px; padding-bottom: 20px">
			<nav class="navbar navbar-light navbar-1 white">
				<div class="card-body" style="padding-left: 50px; padding-top: 50px;">
					<h4 class="card-title">Thông tin bán hàng</h4>
					<div class="from-group row " style="text-align: center; padding-top: 10px">
						<div class="col-5">
							<label>Giá gốc:</label>
						</div>
						<div class="col-5">
							<input class="form-control" id="priceProduct" name="priceProduct" type="text" placeholder="Nhập giá" required="required">
						</div>
					</div>
					<div class="from-group row" style="text-align: center; padding-top: 10px">
						<div class="col-5">
							<label>Giá giảm</label>
						</div>
						<div class="col-5">
							<input class="form-control" type="text" id="priceDiscount" name="priceDiscount" placeholder="Nhập giá" required="required">
						</div>
					</div>					
					<div class="from-group row"
						style="text-align: center; padding-top: 10px">
						<div class="col-5">
							<label for="numberProduct">Kho hàng:</label>
						</div>
						<div class="col-5">
							<input class="form-control" id="numberProduct" name="numberProduct" type="text" placeholder="0" required="required">
						</div>
					</div>
				</div>



				<!-- Collapsible content -->
				<div class="collapse navbar-collapse" id="size" >
					<label  id="labelNumberSize" style="visibility: hidden;">Số lượng từng size</label>
					<div>							
						<div class ="row" style="padding-top: 15px ; visibility: hidden;" id="sizeShoes">
							<div class="md-form col" >
								<input type="number" id="size36" name="shoesSize" class="form-control" min="0" required value="0">
	 							 <label for="size36">size 36</label>
							</div>
							<div class="md-form col" >
								<input type="number" id="size37" name="shoesSize" class="form-control" min="0" required value="0" >
	 							 <label for="size37">size 37</label>
							</div><div class="md-form col" >
								<input type="number" id="size38"  name="shoesSize" class="form-control" min="0" required value="0">
	 							 <label for="size38">size 38</label>
							</div><div class="md-form col" >
								<input type="number" id="size39" name="shoesSize" class="form-control" min="0" required value="0">
	 							 <label for="size39">size 39</label>
							</div><div class="md-form col" >
								<input type="number" id="size40" name="shoesSize" class="form-control" min="0" required value="0">
	 							 <label for="size40">size 40</label>
							</div>					
						
						
							<div class="md-form col" >
								<input type="number" id="size41" name="shoesSize" class="form-control" min="0" required value="0">
	 							 <label for="size41">size 41</label>
							</div>
							<div class="md-form col" >
								<input type="number" id="size42" name="shoesSize" class="form-control" min="0" required value="0">
	 							 <label for="size42">size 42</label>
							</div><div class="md-form col" >
								<input type="number" id="size43" name="shoesSize" class="form-control" min="0" required value="0">
	 							 <label for="size43">size 43</label>
							</div><div class="md-form col" >
								<input type="number" id="size44" name="shoesSize" class="form-control" min="0" required value="0">
	 							 <label for="size44">size 44</label>
							</div><div class="md-form col" >
								<input type="number" id="size45" name="shoesSize" class="form-control" min="0" required value="0">
	 							 <label for="size45">size 45</label>
							</div>					
						
						</div>
						
					</div>
					
					<div class ="row" style="visibility: hidden;" id="sizeClothes">
						<div class="md-form col" >
							<input type="number" id="sizeS" name="clotheSize" class="form-control" min="0" required value="0">
								 <label for="sizeS">size S</label>
						</div>
						<div class="md-form col" >
							<input type="number" id="sizeM" name="clotheSize" class="form-control" min="0" required value="0">
								 <label for="sizeM">size M</label>
						</div><div class="md-form col" >
							<input type="number" id="sizeL" name="clotheSize" class="form-control" min="0" required value="0">
								 <label for="sizeL">size L</label>
						</div><div class="md-form col" >
							<input type="number" id="sizeXL" name="clotheSize" class="form-control" min="0" required value="0">
								 <label for="sizeXL">size XL</label>
						</div><div class="md-form col" >
							<input type="number" id="sizeXXL" name="clotheSize" class="form-control" min="0" required value="0">
								 <label for="sizeXXL">size XXL</label>
						</div>					
					</div>
					
				</div>
				
					
			
				<!-- Collapsible content -->

			</nav>
		</div>


		<div style="padding-left: 230px; padding-right: 40px; padding-top: 20px; padding-bottom: 20px" id="salesInformation">
		<div class="card" style="height: 300px">
			<div class="card-body" style="padding-left: 50px; padding-top: 50px;">
				<h4 class="card-title">Hình ảnh</h4>
				<div class="row">
					<div class="col-3">
						<img src="img/addImage.png" class="rounded" height="200px" width="200px" alt="avatar" id="avatarProduct" style="border: dotted; border-color: deepskyblue;">
						<input type="file" class="form-control-file" id="imageProduct1" name="avatarProduct" required="required" style="display: none;"  placeholder="Photo">					
					</div>
					<div class="col-3">
						<img src="img/addImage.png" class="rounded imageProduct" height="200px" width="200px" id="imageProduct2" style="border: dotted; border-color: deepskyblue;">
						<input type="file" class="form-control-file imageChange" id="imageProduct2" name="imageProduct" style="display: none;" >
					</div>
					<div class="col-3">
						<img src="img/addImage.png" class="rounded imageProduct" height="200px" width="200px"  id="imageProduct3" style="border: dotted; border-color: deepskyblue;">
						<input type="file" class="form-control-file imageChange " id="imageProduct3" name="imageProduct" style="display: none;" >
					</div>
					<div class="col-3">
						<img src="img/addImage.png" class="rounded imageProduct" height="200px" width="200px"  id="imageProduct4" style="border: dotted; border-color: deepskyblue;">
						<input type="file" class="form-control-file imageChange" id="imageProduct4" name="imageProduct" style="display: none;" >
					</div>
				</div>	
						
			</div>	
				<div style="text-align: center;">
					<button class="btn aqua-gradient btn-rounded waves-effect waves-light " type="submit" style="bottom: 1px;">Thêm</button>
			</div>			
		</div>
	
	</div>

</form>

	<div class="col-md-2">
		<div class="sticky" style="position: fixed; top: 90px; z-index: 2; width: 162.516px; height: 407px;">
			<div id="scrollspy">
				<ul class="nav nav-pills default-pills smooth-scroll"
					data-allow-hashes="">
					<li class="nav-item"><a class="nav-link" href="#basicInformation">Thông tin cơ bản</a></li>
					<li class="nav-item"><a class="nav-link" href="#salesInformation">Thông tin bán hàng</a></li>
					<li class="nav-item"><a class="nav-link" href="saleschannel">Hình ảnh</a></li>					
				</ul>
				<form action="saleschannel" method="post">
					<input type="hidden" name="success" value="false">
					<button type="submit" style="padding: 0;border: none; background: none; padding-left: 15px; outline:none;">Trở về</button>
				</form>
			</div>
		</div>
	</div>

	

	<div class="modal fade bottom" id="addProductSuccess" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-frame modal-top" role="document">
    <div class="modal-content">
      <div class="modal-body">
        <div class="row d-flex justify-content-center align-items-center">
          <p class="pt-3 pr-2">Thêm sản phẩm thành công </p>        
        </div>
      </div>	
    </div>
  </div>
</div>


<div class="modal fade bottom" id="addProductFail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-frame modal-top" role="document">
    <div class="modal-content">
      <div class="modal-body">
        <div class="row d-flex justify-content-center align-items-center">
          <p class="pt-3 pr-2">Thêm sản phẩm không thành công </p>        
        </div>
      </div>	
    </div>
  </div>
</div>

<input type="hidden" id="statusAdd" value="${statusAdd}">

	<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
	<!-- Bootstrap tooltips -->
	<script type="text/javascript" src="js/popper.min.js"></script>
	<!-- Bootstrap core JavaScript -->
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<!-- MDB core JavaScript -->
	<script type="text/javascript" src="js/mdb.min.js"></script>
    <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		var statusAdd = $("#statusAdd").val();
		if(statusAdd == "true"){
			$("#addProductSuccess").modal('show');
			}
		if( statusAdd== "false"){
			$("#addProductFail").modal('show');
			}
		});

	$(document).ready(function(){
			$("#idPortfolio").change(function (){
				var selectPortfolio = $(this).children("option:selected").val();
				
				$.ajax(
						'selectPortfolio', 
						{
					    type: 'GET',  // http method
					    data: {
					    	idType : selectPortfolio				    	
						     },  // data to submit
					    success: function (data, status, xhr) {
					    	$( "#brand" ).html("");
					    	$.each(data, function( index, row ) { 		
					    		$( "#brand" ).append( "<option value='"+row.idBrand +"'>" + row.nameBrand +"</option>" );
					    	});
							if(selectPortfolio == 3 || selectPortfolio == 4){
								$('#size').css("display","inline");	
								$('#labelNumberSize').css("visibility","visible");
								$('#sizeClothes').css("visibility","visible");
								$('#sizeShoes').css("visibility","hidden");							
							}else if(selectPortfolio == 2){
								$('#size').css("display","inline");	
								$('#labelNumberSize').css("visibility","visible");
								$('#sizeShoes').css("visibility","visible");
								$('#sizeClothes').css("visibility","hidden");		
							}else{
								$('#size').css("display","none");	
								$('#labelNumberSize').css("visibility","hidden");
								$('#sizeShoes').css("visibility","hidden");
								$('#sizeClothes').css("visibility","hidden");	
							}
						        
					    },
					    error: function (jqXhr, textStatus, errorMessage) {
					            $('p').append('Error' + errorMessage);
					    }
					});
			
		});
	});

	$("#avatarProduct").click(function(e){
		$("#imageProduct1").click();
		});

	$("#imageProduct1").change(function(){
		addProductImageReview(this);
		});

	function addProductImageReview(upload){
			if(upload.files && upload.files[0]){
					$("#avatarProduct").attr('src',window.URL.createObjectURL(upload.files[0]));
				}
		}

	$(".imageProduct").click(function(e){
		$(this).parent('div').find('input')[0].click();
		});

	$(".imageChange").change(function(){
		var image = $(this).parent('div').find('img')[0]; 
		if(this.files && this.files[0]){	
			$(image).attr('src',window.URL.createObjectURL(this.files[0]));						
		}
	});

	
		
	
	</script>
</body>


</html>