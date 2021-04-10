
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  
<link href="css/head.css" rel="stylesheet">
<style >
.btn-login{
    margin: 1.5rem 0 1rem 0 !important;
    background: none!important;
    border: none!important;
    box-shadow: none!important;
    font-weight: 400;
}


</style >
            <div class="input-group" style="background-color: white;border-bottom: 2px solid;">
                <div class="col-lg-4 col-md-4 col-sm-12">              
                    <a class="logo" href="http://localhost:8080">
                        <p class="logo name">BADMINTON</p>
                        <div  class="icon"> </div>
                    </a>              
                </div>
            
                <div class="col-lg-6 col-md-6 col-sm-12">     
                  <h1 style="margin-top: 20px;"> Quản lý website</h1>
                </div>
            
                <div class="col-lg-2 col-md-2 col-sm-12">
                    <c:if test="${not empty pageContext.request.userPrincipal.name}">
                        <div class="nav-item dropdown from group row btn-login" id="nameAvatarLogin" >
                            <a class="nav-link dropdown-toggle font-weight-bold mb-2" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${pageContext.request.userPrincipal.name}</a>
                            <div class="dropdown-menu dropdown-primary" aria-labelledby="navbarDropdownMenuLink">
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
            <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
            <!-- Bootstrap tooltips -->
            <script type="text/javascript" src="js/popper.min.js"></script>
            <!-- Bootstrap core JavaScript -->
            <script type="text/javascript" src="js/bootstrap.min.js"></script>
            <!-- MDB core JavaScript -->
            <script type="text/javascript" src="js/mdb.min.js"></script>
            <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>