<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en"> 
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
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

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>

    <style>
        .input-error{
            border-bottom: 2px solid red !important;
            box-shadow: none !important;
        
        }

        .input-success{
            border-bottom: 2px solid greenyellow !important;
            box-shadow: none !important;
           
        }
    </style>
</head>
<body>
    <jsp:include page="head.jsp"></jsp:include>
    <form action="registerUser" id="registerForm">
            <div class="modal-dialog modal-dialog-centered" role="document" id="formRegister">
                <div class="modal-content">   
                    <div class="modal-header">
                        <h5 class="modal-title " id="createUser">Tại tài khoản</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
    
                    <div class="modal-body">
                        <div class="md-form">
                            <input type="text" id="fullName" name="fullName" class="form-control " required="required" > 
                            <label for="fullName">Họ Tên</label>
                        </div>
                        <div class="md-form">
                            <input type="text" id="account" name="account" class="form-control" > 
                            <label for="account">Tài khoản</label>
                        </div>
                        <div class="md-form">
                            <input type="password" id="password" name="password" class="form-control " > 
                            <label for="password">Mật khẩu</label>
                        </div>
                        <div class="md-form">
                            <input type="email" id="email" name="email" class="form-control " > 
                            <label  for="email" >Email</label>
                        </div>
                        <div class="md-form" style="height: 40px">
                            <input type="number" id="phoneNumber" name="phoneNumber" class="form-control"onkeypress="return (event.charCode == 8 || event.charCode == 0 || event.charCode == 13) ? null : event.charCode >= 48 && event.charCode <= 57" name="itemConsumption" >
                            <label  for="phoneNumber">Số điện thoại</label>
                        </div>
                        <div class="md-form" style="padding-left: 100px;">
                            <div class="custom-control custom-radio custom-control-inline" style="padding-right: 150px;">
                                <input type="radio" checked class="custom-control-input" id="sex"name="sex">
                                 <label class="custom-control-label" for="sex" style="cursor: pointer;">Nam</label>
                            </div>
                            <div class="custom-control custom-radio custom-control-inline">
                                <input type="radio" class="custom-control-input" id="sex1" name="sex"> 
                                <label class="custom-control-label" for="sex1" style="cursor: pointer;">Nữ</label>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                        <button type="button" class="btn btn-primary" onclick="register()">Tạo tại khoản</button>
                    </div>
                </div>
    
            </div>
        </div>
    </form>


     <!-- Notice of successful registration  -->
     <div class="modal fade" id="registerSuccess" tabindex="-1"
     role="dialog" aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
     <div class="modal-dialog modal-dialog-centered" role="document">
         <div class="modal-content">
             <div class="modal-header">
                <i class="fas fa-check" style="color: rgb(155, 243, 12);font-size: 32px;margin: auto;"></i>
             </div>
             <div class="modal-body">Bạn đã đăng ký thành công</div>
         </div>
     </div>
 </div>

 <!-- Registration notice failed  -->
 <div class="modal fade" id="registerFail" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalCenterTitle" aria-hidden="true" style="z-index: 1051">
     <div class="modal-dialog modal-dialog-centered" role="document">
         <div class="modal-content">
             <div class="modal-header">
                <i class="fas fa-exclamation-triangle" style="color: #ab2929;font-size: 32px;margin: auto;"></i>
             </div>
             <div class="modal-body">Bạn đã đăng ký không thành công</div>

         </div>
     </div>
 </div>

 <div class="modal fade" id="accountExist" tabindex="-1"
     role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
     style="z-index: 1051; padding-top: 220px;">
     <div class="modal-dialog modal-notify modal-success" role="document"
         style="right: 9px">
         <!--Content-->
         <div class="modal-content ">
             <!--Body-->
             <div class="modal-body">
                 <div class="text-center">
                     <i class="fas fa-times fa-4x mb-3 animated rotateIn"
                         style="color: red;"></i>
                     <p>Tài khoản đã tồn tại</p>
                 </div>
             </div>
         </div>
     </div>
 </div>

    <jsp:include page="footer.jsp"></jsp:include>
    

    <script>
      
        //check change input email
    function checkEmail(){
        var checkMail  = $("#email").val();
        var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
        if(!emailReg.test(checkMail) ||checkMail ==""){
            $("#email").removeAttr('class');
            $("#email").addClass("form-control input-error");
            return false;
        }else{
            $("#email").removeAttr('class');
            $("#email").addClass("form-control input-success");
            return true;
        }
    }
  $("#email").keyup(function(){checkEmail()});

   // check change input full name
   function checkFullName(){
      if($("#fullName").val() == "" || $("#fullName").val().length < 6 ){
          $("#fullName").removeAttr('class');
          $("#fullName").addClass("form-control input-error");
          return false;
      }else{
          $("#fullName").removeAttr('class');
          $("#fullName").addClass("form-control input-success");
         return true;
      }
    }
  $("#fullName").keyup(function(){checkFullName()});

  //check change input account 
  function checkAccount(){
      if( $("#account").val()=="" ||  $("#account").val().length <6 ){
          $("#account").removeAttr('class');
          $("#account").addClass("form-control input-error");
           return false;
      }else{
          $("#account").removeAttr('class');
          $("#account").addClass("form-control input-success");
          return true;
      }
  }
  $("#account").keyup(function(){checkAccount()});

  //check change input password
  function checkPassword(){
      if($("#password").val()==""|| $("#password").val().length <6){
          $("#password").removeAttr('class');
          $("#password").addClass("form-control input-error");
          return false;
      }else{
          $("#password").removeAttr('class');
          $("#password").addClass("form-control input-success");
          return true;
       }
  }
  $("#password").keyup(function(){checkPassword()});

  //check change input phone number
        function checkPhoneNumber(){
            if($("#phoneNumber").val() ==""||$("#phoneNumber").val().length != 10){
            	$("#phoneNumber").removeAttr('class');
            	$("#phoneNumber").addClass("form-control input-error");
                return false;
            }else{
            	$("#phoneNumber").removeAttr('class');
            	$("#phoneNumber").addClass("form-control input-success");
                return true;
            }
        }
        $("#phoneNumber").keyup(function(){checkPhoneNumber()});
          
    

         function register(){
            if(checkEmail() && checkFullName() &&  checkPassword() && checkAccount() && checkPhoneNumber()){
                $.ajax(
                'registerUser', 
                {
                type: 'POST',  // http method
                data: {
                     fullName: $("#fullName").val(),
                     account: $("#account").val(),
                     password: $("#password").val(),
                     email: $("#email").val(),
                     phoneNumber: $("#phoneNumber").val()                
                     },  // data to submit
                success: function (data, status, xhr) {
                    if(data == "exist"){
                        $('#accountExist').modal('show');    
                    }
                    if(data == "true"){
                        $('#createUser').modal('hide');
                        $('#registerSuccess').modal('show');
                        $('#formRegister').html("");    
                        $('#registerForm').prepend("<div style="+"margin:9rem;font-size:33px;"+"> Vui lòng truy cập vào gmail để kích hoạt tài khoản <a href="+"https://mail.google.com"+">https://mail.google.com</a></div>");
                    }
                    if(data == "false"){
                        $('#createUser').modal('hide');
                        $('#registerFail').modal('show');
                    }
                        
                },
                error: function (jqXhr, textStatus, errorMessage) {
                        $('p').append('Error' + errorMessage);
                    }
            });
         }else{
        	 $('#registerFail').modal('show');
         } 
        }
  
    </script>

<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="js/popper.min.js"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="js/mdb.min.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
 <!-- Initializations -->
<script type="text/javascript">
// Animations initialization
new WOW().init();
</script>
    
</body>
</html>


