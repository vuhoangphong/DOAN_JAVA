<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
<style type="text/css">
.center{
    display: block;
    margin-left: auto;
    margin-right: auto;
}

</style>
</head>
<body>
<jsp:include page="../head.jsp"></jsp:include> <!-- head page -->
<div class="container" style="margin-top: 1rem;">
	<div class="row">
		<div class="col-4" style="margin-top: 9rem;">
			<jsp:include page="menuLeft.jsp">
				<jsp:param name="profile" value="active" />
			</jsp:include> <!-- menu left -->
			
		 </div> 
	<div class="col-8" style="margin-top: 9rem;">
			<div class="tab-pane " id="v-pills-tabContent">
				<!-- right pannel of thông tin tài khoản -->
				<div class="tab-pane fade  active show " id="v-pills-thongTinTaiKhoan" >
					<div class="panel panel-default">
							<div class="card" >
								<div class="card-body">
								<form action="uploadUser" method="post" enctype="multipart/form-data">
									<div class="row">								
										<div class="card-body d-flex flex-row">
												<!-- Avatar -->												
												<img src="/getAvatarUser?idUser=${userInfo.idUser}" class="rounded-circle center" height="200px" width="200px" alt="avatar" id="avatarUser">
												<input id="imageUpload" type="file" name="imageUpload" placeholder="Photo" style="display: none;" required="" capture>
											</div>
											<!-- input name  -->
											<div class="md-form input-group">
												<div class="input-group-prepend">
													<span class="input-group-text md-addon">Họ và Tên</span>
												</div>
												<input type="text" aria-label="hoTen" class="form-control"
													placeholder="Họ và Tên" value="${userInfo.fullName}" name="fullName">
											</div>
											<!-- input email -->
											<div class="md-form input-group mb-4">
											<div class="input-group-append">
													<span class="input-group-text md-addon"
														id="material-addon2">Email</span>
												</div>
												<input type="text" class="form-control"
													placeholder="Nhập Email" value="${userInfo.email}" name="email">												
											</div>
											<!--  input phone number -->
											<div class="md-form input-group mb-3">
												<div class="input-group-prepend">
													<span class="input-group-text md-addon" id="soDienThoai">Số
														điện thoại</span>
												</div>
												<input type="text" class="form-control"
													placeholder="Nhập số điện thoại" value="${userInfo.phoneNumber}" name="phoneNumber" onkeypress="return (event.charCode == 8 || event.charCode == 0 || event.charCode == 13) ? null : event.charCode >= 48 && event.charCode <= 57" name="itemConsumption">

											</div>
											<!--  input address -->
											<div class="md-form input-group mb-3">
												<div class="input-group-prepend" style="padding-right: 45px">
													<span class="input-group-text md-addon" id="diaChi">Địa
														chỉ</span>
												</div>
												<input type="text" class="form-control"
													placeholder="Nhập địa chỉ" value="${userInfo.address}" name="address">
											</div>
									
										<c:choose>
											<c:when test="${userInfo.sex >0}" >
												<div class="custom-control custom-radio custom-control-inline"
													style="padding-right: 150px;">
													<input type="radio"  class="custom-control-input" id="sex"
														name="sex"> <label class="custom-control-label"
														for="sex" style="cursor: pointer;" >Nam</label>
												</div>
												<div class="custom-control custom-radio custom-control-inline">
													<input type="radio" checked  class="custom-control-input" id="sex1"
														name="sex"> <label class="custom-control-label"
														for="sex1" style="cursor: pointer;">Nữ</label>
												</div>
											</c:when>											
											<c:otherwise>
												<div class="custom-control custom-radio custom-control-inline"
													style="padding-right: 150px;">
													<input type="radio" checked class="custom-control-input" id="sex"
														name="sex"> <label class="custom-control-label"
														for="sex" style="cursor: pointer;">Nam</label>
												</div>
												<div class="custom-control custom-radio custom-control-inline">
													<input type="radio"  class="custom-control-input" id="sex1"
														name="sex"> <label class="custom-control-label"
														for="sex1" style="cursor: pointer;">Nữ</label>
												</div>										
											</c:otherwise>
										</c:choose>
										<input type="hidden" name="idUser" value="${userInfo.idUser}">		
										<div style="padding-top: 3%; padding-left: 30%;">								
											<button type="submit" class="btn btn-info">Lưu</button>																													
										</div>
									</div>
									</form>	
								</div>
							</div>
					</div>
				</div>
			</div>
		</div>
	</div>		 
</div>

<!-- footer -->
<jsp:include page='../footer.jsp'></jsp:include>
		
	<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
	<!-- Bootstrap tooltips -->
	<script type="text/javascript" src="js/popper.min.js"></script>
	<!-- Bootstrap core JavaScript -->
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<!-- MDB core JavaScript -->
	<script type="text/javascript" src="js/mdb.min.js"></script>
	<!-- Initializations -->
	<script type="text/javascript">
	$("#avatarUser").click(function(e){
		$("#imageUpload").click();
		});

		
	function uploadAvatarUser(upload){
		if(upload.files && upload.files[0]){
			$("#avatarUser").attr('src',window.URL.createObjectURL(upload.files[0]));			
			}
		}

	$("#imageUpload").change(function(){
		uploadAvatarUser(this);
		});
	</script>

</body>


</html>