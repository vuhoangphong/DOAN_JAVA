
  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Badminton</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="css/rSlider.min.css">
    <script src="js/rSlider.min.js"></script>
    <!-- Material Design Bootstrap -->
    <link href="css/mdb.min.css" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="css/style.min.css" rel="stylesheet">
</head>
    <body>  
        <jsp:include page='head.jsp'></jsp:include>
        <form action="login" method="POST">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">   
                    <div class="modal-header">
                        <h5 class="modal-title " id="createUser">Tại tài khoản</h5>                      
                    </div>
    
                    <div class="modal-body">
                        
                        <div class="md-form">
                            <input type="text" id="accountLogin" name="accountLogin" class="form-control" > 
                            <label for="account">Tài khoản</label>
                        </div>
                        <div class="md-form">
                            <input type="text" id="passwordLogin" name="passwordLogin" class="form-control " > 
                            <label for="password">Mật khẩu</label>
                        </div>              
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary" style="margin-right: 8rem;">Đăng nhập</button>
                        <a href="" >Quên mật khẩu</a>
                        <a href="http://localhost:8080/createUser" >Đăng ký</a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>


