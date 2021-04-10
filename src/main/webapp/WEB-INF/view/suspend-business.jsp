<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  
<html lang="en">
<head>
    
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/rSlider.min.css">
    <script src="js/rSlider.min.js"></script>
    <!-- Material Design Bootstrap -->
    <link href="css/mdb.min.css" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="css/style.min.css" rel="stylesheet">
    <link href="css/head.css" rel="stylesheet">
<style >
.btn-login{
    margin: 1.5rem 0 1rem 0 !important;
    background: none!important;
    border: none!important;
    box-shadow: none!important;
    font-weight: 400;
}
</style>
</head>
<body>
  
    <div class="container-fluid" style="    position: relative; overflow-x: hidden;padding-left: 0 !important;height: 100%;">
        <div class="input-group" style=" background-color: white;border-bottom: 2px solid;">
            <div class="col-lg-4 col-md-4 col-sm-12">              
                <a class="logo" href="http://localhost:8080">
                    <p class="logo name">BADMINTON</p>
                    <div  class="icon"> </div>
                </a>              
            </div>
        
            <div class="col-lg-6 col-md-6 col-sm-12">     
                <form action="search" method="GET">         
                <div class="md-form md-outline input-group md-input-group w-100">
                        <c:if test="${empty param.title}">
                            <input type="text" class="form-control mb-0" name="keyWord"placeholder="Tìm kiếm">
                        </c:if>
                        <c:if test="${not empty param.title}">
                            <input type="text" class="form-control mb-0" name="keyWord"placeholder="Tìm kiếm" value="${param.title}">
                        </c:if>
                       
                        <div class="input-group-append">
                            <button class="btn btn-red btn-md px-3" type="submit">
                                <i class="fa fa-search"></i>Tìm kiếm
                            </button>
                        </div>                   
                    <div style="margin:8px 5px;">
                        <a class="waves-effect">
                            <span class="badge badge-pill red">0</span>
                            <i class="fas fa-shopping-cart pl-0 " style="color: burlywood;"></i>     
                        </a>                       
                    </div>                   
                </div>
            </form>
            </div>
        
            <div class="col-lg-2 col-md-2 col-sm-12">
                <c:if test="${not empty pageContext.request.userPrincipal.name}">
                    <div class="nav-item dropdown from group row btn-login" id="nameAvatarLogin" >
                        <!-- Avatar -->
                        <a href="/profile"> <img src="/getAvatarUser?idUser=${user}" class="rounded-circle mr-3" height="30px" width="30px" alt="avatar"></a>
                        <a class="nav-link dropdown-toggle font-weight-bold mb-2" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${pageContext.request.userPrincipal.name}</a>
                        <div class="dropdown-menu dropdown-primary" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="profile">Hồ sơ</a>
                            <a class="dropdown-item" href="saleschannel">Kênh bán hàng</a>
                            <a class="dropdown-item" href="cart">Giỏ hàng</a>
                            <form action="logout" method="GET">          
                                <button type="submit" class="dropdown-item">Thoát</button>
                            </form>                           
                        </div>
                    </div>                   
                </c:if>
                <c:if test="${empty pageContext.request.userPrincipal.name}">       
                    <div class="input-group-append" style="padding-top: 2rem;">
                        <a style="margin-right:2rem;" href="http://localhost:8080/login">Đăng nhập</a>
                        <a href="http://localhost:8080/createUser">Đăng ký</a>
                    </div>
                </c:if>
            </div>  
        </div>     
        <div class="row">
            <div> 
                <h1 style="    margin-left: 25px; margin-top: 25px;">Mặt hàng tạm dừng kinh doanh</h1>
            </div>
        </div>
        <div style="position: absolute; bottom: 0;width: 100%;">
            <jsp:include page="footer.jsp"></jsp:include>
        </div>
    </div>
    
</body>
</html>