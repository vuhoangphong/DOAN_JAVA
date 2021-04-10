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
  <link href="css/settingShop.css" rel="stylesheet">
 <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
   
<style type="text/css">

</style> 
</head>
<body>
<jsp:include page='headShop.jsp'></jsp:include>
<div class="container-fluid" style="padding-left: 0;">
<div class= "row">
	<jsp:include page='menuleft.jsp'></jsp:include>
	<div class="col-lg-10 col-md-12 col-sm-12"style="padding: 0px !important; margin-top: 8rem;">
	<form action="updateShop" method="post" enctype="multipart/form-data">
		<div class="card bg-white right mb-3" style="max-width: 100%;margin-right: 1rem;">	
			<div class="col">			 
    			 <div class="card-body d-flex flex-row">
					<img src="/getAvatarShop?idShop=${shop.idShop}" class="rounded-circle center " height="200px" width="200px" alt="avatar" id="avatarShop">
				</div>
				<input id="imageUpload" type="file" name="imageUpload" placeholder="Photo" required="" capture >
   			 </div>	
   		<button type="button" class="btn btn-cyan" style="margin: auto;" id="changeAvatar">Đổi avatar</button>
		<div class="form-group row">
			<div class="col-3">			 
    			<label  class="sm-2 col-form-label" style="padding-left: 20px">Tên shop</label>
    		</div>
    		<div class="col-7 sm-10">
   			   <div class="md-form mt-0">
     			   <input type="text" class="form-control" id="nameShop" name="nameShop" placeholder="Tên shop" value="${shop.nameShop}">
    		  </div>
   			 </div>	
		</div>
		<div class="form-group row">
			<div class="col-3">			 
    			<label  class="sm-2 col-form-label" style="padding-left: 20px">Địa chỉ</label>
    		</div>
    		<div class="col-7 sm-10">
   			   <div class="md-form mt-0">
     			   <input type="text" class="form-control" id="addressShop" name="addressShop" placeholder="Địa chỉ" value="${shop.addressShop}">
    		  </div>
   			 </div>	
		</div>
		<div class="form-group row">
			<div class="col-3">			 
    			<label  class="sm-2 col-form-label" style="padding-left: 20px">Số điện thoại</label>
    		</div>
    		<div class="col-7 sm-10">
   			   <div class="md-form mt-0">
     			   <input type="text" class="form-control" id="numberPhoneShop" name="numberPhoneShop" placeholder="Số điện thoại" value="${shop.numberPhoneShop}"  onkeypress="return (event.charCode == 8 || event.charCode == 0 || event.charCode == 13) ? null : event.charCode >= 48 && event.charCode <= 57" name="itemConsumption"   >
    		  </div>
   			 </div>	
		</div>		
		<button type="submit" class="btn btn-cyan" style="margin: auto; bottom: 10px">Lưu </button>
		</div>
		<input type="hidden" name="idShop" value="${shop.idShop}" >
		</form>
	</div>
	
</div>
</div>	

<div class="modal fade bottom" id="updateShopSuccess" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-frame modal-top" role="document">
    <div class="modal-content">
      <div class="modal-body">
        <div class="row d-flex justify-content-center align-items-center">
          <p class="pt-3 pr-2">update shop thành công </p>        
        </div>
      </div>	
    </div>
  </div>
</div>


<div class="modal fade bottom" id="updateShopFail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-frame modal-top" role="document">
    <div class="modal-content">
      <div class="modal-body">
        <div class="row d-flex justify-content-center align-items-center">
          <p class="pt-3 pr-2">update shop thất bại </p>        
        </div>
      </div>	
    </div>
  </div>
</div>

<div class="modal fade bottom" id="updateAvatarShopFail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-frame modal-top" role="document">
    <div class="modal-content">
      <div class="modal-body">
        <div class="row d-flex justify-content-center align-items-center">
          <p class="pt-3 pr-2">update avatar shop thất bại </p>        
        </div>
      </div>	
    </div>
  </div>
</div>
	 
<input type="hidden" id="updateShop" value="${updateShop}">	    
 <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
	  <!-- Bootstrap tooltips -->
	  <script type="text/javascript" src="js/popper.min.js"></script>
	  <!-- Bootstrap core JavaScript -->
	  <script type="text/javascript" src="js/bootstrap.min.js"></script>
	  <!-- MDB core JavaScript -->
	  <script type="text/javascript" src="js/mdb.min.js"></script>
	  <!-- Initializations -->
	  
<script type="text/javascript">
$(document).ready(function(){
	var statusAdd = $("#updateShop").val();
	if(statusAdd == "success"){
		$("#updateShopSuccess").modal('show');
		}
	if( statusAdd== "informationFail"){
		$("#updateShopFail").modal('show');
		}
	if( statusAdd== "imageFail"){
		$("#updateAvatarShopFail").modal('show');
		}
	});

$("#avatarShop").click(function(e){
	$("#imageUpload").click();
});

$("#changeAvatar").click(function(e){
	$("#imageUpload").click();
});

function avatarShopPreview(uploader){
	if(uploader.files && uploader.files[0]){
		$('#avatarShop').attr('src',window.URL.createObjectURL(uploader.files[0]));
		}
}
$("#imageUpload").change(function(){
	avatarShopPreview(this);
});
</script>
</body>


</html> 