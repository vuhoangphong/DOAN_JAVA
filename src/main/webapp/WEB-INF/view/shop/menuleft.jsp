<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="css/menuleft.css" rel="stylesheet">
<style>
 .bounceInDown{
  animation: a2 1s 1;
 }
 .bounceInDown.delay-02s{
  animation: a2 1s 1;
  animation-delay: 0.2s;
 }
 .bounceInDown.delay-03s{
  animation: a2 1s 1;
  animation-delay: 0.3s;
 }
 .bounceInDown.delay-04s{
  animation: a2 1s 1;
  animation-delay: 0.4s;
 }
 @keyframes a2{
   0%{transform: translateY(-20rem);}
   100%{transform: translateY(0rem);}
 }
</style>
</head>
     <body> 
            <div class="col-lg-2 col-md-12 col-sm-12 panel" style="margin-top: 8rem;">
              <div class="panel-menu">
                  <div class="row mr-1" >
                    <img src="/getAvatarShop?idShop=${shop.idShop}"class="rounded-circle mr-3 avatar"  alt="avatar">
                  </div>
                  <hr>
                  <div class="row mr-1 bounceInDown">        
                      <div class="col col-menu-left hover " data-toggle="collapse" data-target="#number1" aria-expanded="false">  
                        <a>
                            <p class="custom">Quản lý đơn hàng</p>
                            <i class="fa fa-angle-down rotate-icon " ></i>
                        </a>
                     </div>          
                  </div>

                  <div class="collapse" id="number1">
                      <!-- Links -->
                    <ul class="navbar-nav custom-in">
                      <li class="nav-item1">
                        <a class="nav-link" href="/waitconfirmation">Chờ xác nhận</a>
                      </li>
                      <li class="nav-item1">
                        <a class="nav-link" href="/closepackage">Đóng gói</a>
                      </li>  
                        <li class="nav-item1 active">
                          <a class="nav-link" href="/beingtransported">Đang giao <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item1">
                          <a class="nav-link" href="/successfuldelivery">Đã giao</a>
                        </li>
                        <li class="nav-item1">
                          <a class="nav-link" href="/cancelorder">Đã hủy</a>
                        </li>                                             
                      </ul>
                      <!-- Links -->
                    </div>
                    <!-- Collapsible content -->
                  <div class="row mr-1 bounceInDown delay-02s">
                      <div class="col col-menu-left " data-toggle="collapse" data-target="#number2" aria-expanded="false">
                          <a >
                            <p class="custom">Quản lý sản phẩm</p>
                            <i class="fa fa-angle-down rotate-icon "></i>
                          </a>
                      </div>
                  </div>
                  <div class="collapse " id="number2">
                      <!-- Links -->
                      <ul class="navbar-nav custom-in">
                        <li class="nav-item1 active">
                          <a class="nav-link" href="/saleschannel-allproduct-show">Tất cả sản phẩm <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item1">
                          <a class="nav-link" href="/saleschannel-product-banned">Sản phẩm vi phạm</a>
                        </li>
                        <li class="nav-item1">
                          <a class="nav-link" href="/addnew">Thêm sản phẩm</a>
                        </li>
                      </ul>
                      <!-- Links -->
                    </div>
                    <!-- Collapsible content -->
                  <div class="row mr-1 bounceInDown  delay-03s">
                      <div class="col col-menu-left" data-toggle="collapse" data-target="#number3" aria-expanded="false">
                            <a>
                              <p class="custom">Quản lý shop</p>
                              <i class="fa fa-angle-down rotate-icon "></i>
                          </a>
                      </div>
                  </div>
                  <div class="collapse" id="number3">
                      <!-- Links -->
                      <ul class="navbar-nav custom-in">
                        <li class="nav-item1 active">
                          <a class="nav-link" href="/saleschannel-settingshop">Thiết lập shop <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item1">
                          <a class="nav-link" href="/statistical">Thông kê</a>
                        </li>
                      </ul>
                      <!-- Links -->
                    </div>
                </div>
             </div>   
     </body>
</html>
<script>
  $(".col-menu-left, .nav-item1").hover(
    function(){
      $(this).addClass("hover");
    },
    function(){
      $(this).removeClass("hover");
    }
  );
  $(".col").click(
    function() {
      $(this).find(".custom").addClass("test1"); 
      $(this).find(".rotate-icon").addClass("turn-icon");
      setTimeout(function(){
        $(".custom").removeClass("test1")
      },1000);
    },
  );

</script>