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
  </div>
  

    <div class="col-lg-9">
    </div>
 </div>			
</div>
			
<div class="modal fade bottom" id="modalRegisterShopSuccess" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-frame modal-top" role="document">
    <div class="modal-content">
      <div class="modal-body">
        <div class="row d-flex justify-content-center align-items-center">
          <p class="pt-3 pr-2">Chúc mừng bạn đã đăng ký shop thành công </p>        
        </div>
      </div>	
    </div>
  </div>
</div>

<input type="hidden" id="registerShopSuccess" value="${registerShopSuccess}" >
<input type="hidden" name="succes" value="false" >

<script type="text/javascript">
	$(document).ready(function(){
		var registerShopSuccess = $("#registerShopSuccess").val();
		if(registerShopSuccess == "true"){
			$('#modalRegisterShopSuccess').modal('show');
			}
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