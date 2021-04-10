<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  

<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Babminton</title>
<!-- Font Awesome -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
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
.carousel {
     height: 100%; 
}

.progress {
    display: -ms-flexbox;
    display: flex;
    height: 1rem;
    overflow: hidden;
    font-size: .75rem;
    background-color: #d4dae0;
    border-radius: .25rem;
}
.border{
box-shadow: 0 2px 5px 0 rgba(0,0,0,.16), 0 2px 10px 0 rgba(0,0,0,.12);
}

.mt-3, .my-3 {
    margin-top: 4rem!important;
}

.nav-link-size {
    display: block;
    padding: .5rem 1rem;
}
.size-active{
background-color: #246ee8;
color: white;
}
.size-unactive {
    background-color: #ffffff;
}

.size-soldout {
    background-color: #d7d7d8;
}

.yellow-size{
  color: #f2a707!important;
   font-size: 20px;
}
.yellow-text{
    color: #f2a707!important;
}

</style>




</head>

<body>

<jsp:include page="head.jsp"></jsp:include>

<div class="container mt-5 pt-3">
    <!--Section: Product detail -->
    <section id="productDetails" class="pb-5">

      <!--News card-->
      <div class="card mt-5 hoverable">
        <div class="row mt-5">
          <div class="col-lg-6">

            <!--Carousel Wrapper-->
            <div id="carousel-thumb" class="carousel slide carousel-fade carousel-thumbnails" data-ride="carousel">

              <!--image product-->
              <div class="carousel-inner text-center text-md-left" role="listbox" style="height: 587px">
                <div class="carousel-item active" >
                  <img src="/getImage?idProduct=${product.idProduct}" alt="First slide" class="img-fluid" style="height: 100% ; padding-left: 50px">
                </div>
                <c:forEach items="${listImage}" var="image">
                <div class="carousel-item">
                  <img src="/getPhotoDescriptionProduct?idProduct=${product.idProduct}&idImage=${image.idImage}"  class="img-fluid" style="height: 100%">
                </div>
                </c:forEach>
              </div>
              <!--/.image product-->

              <!--Thumbnails-->
              <a class="carousel-control-prev" href="#carousel-thumb" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
              </a>
              <a class="carousel-control-next" href="#carousel-thumb" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
              </a>
              <!--/.Thumbnails-->

            </div>
            <!--/.Carousel Wrapper-->
          </div>
          
          <!-- information product-->
          <div class="col-lg-5 mr-3 text-center text-md-left">
            <h2 class="h2-responsive text-center text-md-left product-name font-weight-bold dark-grey-text mb-1 ml-xl-0 ml-4">
              <strong>${product.nameProduct}</strong>
            </h2>
         <c:choose>
            <c:when test="${product.priceDiscount >0}">
                <span class="badge badge-danger product mb-4 ml-xl-0 ml-4">bestseller</span>                
            </c:when>
            <c:otherwise>                       
            </c:otherwise>
        </c:choose>      
            <div>
                <div class="row">
                    <div class="col">
                        <h3>
                        <span class="red-text font-weight-bold">
                         <strong>
                          <fmt:formatNumber value = "${product.price}" />đ
                         </strong>
                        </span>
                        </h3>
                    </div>
                    <div class="col" style="text-decoration-line:line-through;text-decoration-color:#9e9e9e">
                    <h3>                      
                      <c:if test="${product.priceDiscount !=0}">       
                        <span class="badge badge-pill danger-color" style="text-decoration: line-through">
                            <fmt:formatNumber value = "${product.priceProduct}" />đ
                        </span> 
                    </c:if> 
                       </h3>        
                    </div>                  
                </div>          
             </div>
           
        
        
        
            <div class="accordion md-accordion" id="accordionEx" role="tablist" aria-multiselectable="true">
              <div class="card">

                
                <div class="card-header" role="tab" id="headingOne1">
                  <a data-toggle="collapse" data-parent="#accordionEx" href="#collapseOne1" aria-expanded="true" aria-controls="collapseOne1">
                    <h5 class="mb-0">
                     Mô tả sản phẩm
                      <i class="fas fa-angle-down rotate-icon"></i>
                    </h5>
                  </a>
                </div>

                <!-- Card body -->
                <div id="collapseOne1" class="collapse" role="tabpanel" aria-labelledby="headingOne1" data-parent="#accordionEx">
                  <div class="card-body">
                    <p>${product.productDescription}</p>   
                </div>
                </div>
              </div>
             
              <div class="card">

                <!-- Card header -->
                <div class="card-header" role="tab" id="headingTwo2">
                  <a class="collapsed" data-toggle="collapse" data-parent="#accordionEx" href="#collapseTwo2" aria-expanded="false" aria-controls="collapseTwo2">
                    <h5 class="mb-0">
                      Xuất xứ
                      <i class="fas fa-angle-down rotate-icon"></i>
                    </h5>
                  </a>
                </div>

               
                <div id="collapseTwo2" class="collapse" role="tabpanel" aria-labelledby="headingTwo2" data-parent="#accordionEx">
                  <div class="card-body">
                     <p>${product.origin}</p> 
                  </div>
                </div>
              </div>
            
            
              <div class="card">

                <!-- Card header -->
                <div class="card-header" role="tab" id="headingThree3">
                  <a class="collapsed" data-toggle="collapse" data-parent="#accordionEx" href="#collapseThree3" aria-expanded="false" aria-controls="collapseThree3">
                    <h5 class="mb-0">
                     Shop
                      <i class="fas fa-angle-down rotate-icon"></i>
                    </h5>
                  </a>
                </div>

               
                <div id="collapseThree3" class="collapse" role="tabpanel" aria-labelledby="headingThree3" data-parent="#accordionEx">
                  <div class="card-body" >
                   <img src="/getAvatarShop?idShop=${product.idShop}" class="rounded-circle center " height="200px" width="200px" alt="avatar" id="avatarShop" style="border: solid;border-color: deepskyblue;">
                  </div>
                  
                </div>
              </div>
               <!-- /.information product-->
               
              <!-- add list shoes-->
              <c:if test="${ type == 2}"> 
               <ul class="nav nav-pills">       
               <c:forEach items="${listSize}" var="size"> 
                <li class="nav-item m-2 border">
                  <a <c:if test="${size.numberSize != 0}"> class="nav-link-size size-unactive"  href="/productdetail-size?idProduct=${size.idProduct}&size=${size.idTypeShoesSize}" </c:if>  class="nav-link-size size-soldout"  >${size.shoesSize}</a>
                </li>
                </c:forEach>                    
             </ul>
            </c:if>
            <!-- /.add list shoes-->
            
            <!-- add list clothes-->
             <c:if test="${type == 4 || type ==3 }">
             <ul class="nav nav-pills">     
             <c:forEach items="${listSize}" var="size">       
                <li class="nav-item m-2 border"  >
                  <a  <c:if test="${size.numberSize != 0}">  class="nav-link-size size-unactive" style="width: 100px;"  href="/productdetail-size?idProduct=${size.idProduct}&size=${size.idTypeClothesSize}" </c:if>  class="nav-link-size size-soldout"  style="width: 100px;"  >${size.clothesSize}</a>
                </li>
               </c:forEach>     
             </ul>
             </c:if>
             <!-- /.add list clothes-->
            
    
             <!-- size select -->
            
             <!-- /.size select -->
             
             <!-- Add product to the bill  -->
        <div class="row mt-3">
            <c:if test="${sizeSelect !=0}"><p class="grey-text col-lg-6 col-md-6 " > Hiện chỉ còn ${numberSize} sản phẩm</p></c:if>
            <div class="col-lg-6 col-md-6">                 
                    <button onclick="this.parentNode.querySelector('input[type=number]').stepDown()"  style="height: 45px">-</button>
                    <input class="quantity" min="0" id="quantity" name="quantity" value="1" type="number" style="width: 50px;height: 44px">
                    <button  onclick="this.parentNode.querySelector('input[type=number]').stepUp()"  style="height: 45px">+</button>
            </div>
        </div>
            <div class="row mt-3 mb-4">             
               <c:if test="${sizeSelect == 0}">
                    <div class="col-lg-3 col-md-3">
                        <button class="btn btn-primary btn-rounded" data-toggle="modal" data-target="#selectSizeFail">Mua</button>  
                    </div>
                    <div class="col-lg-6 col-md-6">
                        <button class="btn btn-primary btn-rounded waves-effect waves-light" data-toggle="modal" data-target="#selectSizeFail">
                        <i class="fas fa-cart-plus mr-2" aria-hidden="true" ></i>Thêm vào giỏ</button>
                    </div>                       
               </c:if>
               
               <c:if test="${sizeSelect != 0 && user == 0}">
                    <div class="col-lg-3 -col-md-3">
                        <button class="btn btn-primary btn-rounded" data-toggle="modal" data-target="#formLogin">Mua</button>  
                    </div>
                    <div class="col-lg-6 col-md-6">
                        <button class="btn btn-primary btn-rounded waves-effect waves-light" data-toggle="modal" data-target="#formLogin">
                        <i class="fas fa-cart-plus mr-2" aria-hidden="true" ></i>Thêm vào giỏ</button>
                    </div>
                    
               </c:if>
               
                <c:if test="${sizeSelect != 0 && user != 0 }">
                    <div class="col-lg-3 col-md-3">
                        <form action="buy" method="POST" id="addcart">
                          <sec:csrfInput /> 
                            <input type="hidden" name="idProduct" value="${product.idProduct}">
                            <input type="hidden" id="numberSize" name="numberSize" >
                            <input type="hidden" id="size" name="size" value="${sizeSelect}">                                       
                            <button class="btn btn-primary btn-rounded">Mua</button>                           
                        </form>
                    </div>
                    <div class="col-lg-6 -col-md-6">
                        <button class="btn btn-primary btn-rounded waves-effect waves-light" onclick="addCart()">
                        <i class="fas fa-cart-plus mr-2" aria-hidden="true" ></i>Thêm vào giỏ</button>
                    </div>
                </c:if>                                       
            </div>              
        </div>
         <!-- /.Add product to the bill  -->
         
          </div>
        </div>
      </div>
    </section>
    <!-- /.Section: Product detail -->


    <div class="divider-new">
      <h3 class="h3-responsive font-weight-bold blue-text mx-3">Đánh giá sản phẩm</h3>
    </div>
    <!-- product information rating -->
  
    <div class="row">
        <div class="col" style="background: #f7f7f7; box-shadow: 0 2px 5px 0 rgba(0,0,0,.16), 0 2px 10px 0 rgba(0,0,0,.12);">           
                <div class="row" >
                    <div class="col-xs-12 col-md-6 text-center">
                        <h1 class="rating-num">${averageRanking}</h1>
                        <div class="group row" style="padding-left: 40%;" >             
                          <li style="list-style-type: none;"><a><i class="fa-star yellow-size far<c:if test="${averageRanking>=1}"> fas</c:if>" data-index="1"></i></a></li>   
                          <li style="list-style-type: none;"><a><i class="fa-star yellow-size  far<c:if test="${averageRanking>=2}"> fas</c:if>" data-index="2"></i></a></li>
                          <li style="list-style-type: none;"><a><i class="fa-star yellow-size  far<c:if test="${averageRanking>=3}"> fas</c:if>" data-index="3"></i></a></li>
                          <li style="list-style-type: none;"><a><i class="fa-star yellow-size  far<c:if test="${averageRanking>=4}"> fas</c:if>" data-index="4"></i></a></li>
                          <li style="list-style-type: none;"><a><i class="fa-star yellow-size  far<c:if test="${averageRanking>=5}"> fas</c:if>" data-index="5"></i></a></li>
                        </div>
                        <div style="margin-left: -1rem;">
                           <i class="fas fa-user-alt" style="color: #5b9cd6;font-size: larger; margin: 5px;" ></i>${numberOfReviews} Đánh giá
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6">
                        <div class="row rating-desc">    
                          <div class="input-group">
                            <div class="col-xs-3 col-md-3 text-right">
                                <li style="list-style-type: none;"><a>5<i class="fa-star yellow-size fas"></i></a></li>
                            </div> 
                            <div class="col-xs-8 col-md-9">              
                                <div class="progress progress-striped" style="box-shadow: inset 0 2px 5px 0 rgba(0,0,0,.16), 0 2px 10px 0 rgba(0,0,0,.12);">
                                    <div  role="progressbar" aria-valuenow="20" id="rank_5"                             
                                        aria-valuemin="0" aria-valuemax="100" style="width: 0%;box-shadow: 0 2px 5px 0 rgba(0,0,0,.16), 0 2px 10px 0 rgba(0,0,0,.12);background-color: #d3e892;">
                                    </div>
                                </div>
                            </div>
                          </div>
                            <!-- end 5 -->
                            <div class="col-xs-3 col-md-3 text-right">
                                 <li style="list-style-type: none;"><a>4<i class="fa-star yellow-size fas"></i></a></li>
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <div class="progress"  style="box-shadow: inset 0 2px 5px 0 rgba(0,0,0,.16), 0 2px 10px 0 rgba(0,0,0,.12);">
                                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20" id="rank_4" 
                                        aria-valuemin="0" aria-valuemax="100" style="width: 0% ;box-shadow: 0 2px 5px 0 rgba(0,0,0,.16), 0 2px 10px 0 rgba(0,0,0,.12);background-color: #5cb85c;">
                                       
                                    </div>
                                </div>
                            </div>
                            <!-- end 4 -->
                            <div class="col-xs-3 col-md-3 text-right" >
                                 <li style="list-style-type: none;"><a>3<i class="fa-star yellow-size fas"></i></a></li>
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <div class="progress" style="box-shadow: inset 0 2px 5px 0 rgba(0,0,0,.16), 0 2px 10px 0 rgba(0,0,0,.12);">
                                    <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" id="rank_3" 
                                        aria-valuemin="0" aria-valuemax="100" style="width: 0%;box-shadow: 0 2px 5px 0 rgba(0,0,0,.16), 0 2px 10px 0 rgba(0,0,0,.12);background-color: #5bc0de;">
                                        
                                    </div>
                                </div>
                            </div>
                            <!-- end 3 -->
                            <div class="col-xs-3 col-md-3 text-right"  >
                                 <li style="list-style-type: none;"><a>2<i class="fa-star yellow-size fas"></i></a></li>
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <div class="progress" style="box-shadow: inset 0 2px 5px 0 rgba(0,0,0,.16), 0 2px 10px 0 rgba(0,0,0,.12);">
                                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="20" id="rank_2" 
                                        aria-valuemin="0" aria-valuemax="100" style="width: 0%;box-shadow: 0 2px 5px 0 rgba(0,0,0,.16), 0 2px 10px 0 rgba(0,0,0,.12);background-color: #f0ad4e;">
                                        
                                    </div>
                                </div>
                            </div>
                            <!-- end 2 -->
                            <div class="col-xs-3 col-md-3 text-right"  >
                                 <li style="list-style-type: none;"><a>1<i class="fa-star yellow-size fas"></i></a></li>
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <div class="progress"  style="box-shadow: inset 0 2px 5px 0 rgba(0,0,0,.16), 0 2px 10px 0 rgba(0,0,0,.12);">
                                    <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80" id="rank_1" 
                                        aria-valuemin="0" aria-valuemax="100" style="width: 0%;box-shadow: 0 2px 5px 0 rgba(0,0,0,.16), 0 2px 10px 0 rgba(0,0,0,.12);background-color: #d9534f;">
                                        
                                    </div>
                                </div>
                            </div>
                            
                        </div>
                       
                    </div>
                </div>
            
        </div>
    </div>

  <!-- /.product information rating -->
    
    <!-- review product -->
    <form action="addComment" method="POST">
    <c:if test="${not empty pageContext.request.userPrincipal.name && statusComment == 1}">
    <div class="row">
      <div class="col-12">
        <ul class="rating mb-0" style="padding-top: 2%;">
          <div class="group row">
              <span class="black-text mr-2">Bạn đánh giá sản phẩm này như thế nào?</span>
              <li style="list-style-type: none;"><a><i class="fa-star yellow-text review-rating fas" data-index="1"></i></a></li>
              <li style="list-style-type: none;"><a><i class="fa-star yellow-text review-rating fas" data-index="2"></i></a></li>
              <li style="list-style-type: none;"><a><i class="fa-star yellow-text review-rating fas" data-index="3"></i></a></li>
              <li style="list-style-type: none;"><a><i class="fa-star yellow-text review-rating far" data-index="4"></i></a></li>
              <li style="list-style-type: none;"><a><i class="fa-star yellow-text review-rating far" data-index="5"></i></a></li>
          </div>
        </ul>      
        <input type="hidden" id="rank" name="rank" value="3">
      </div>
      <div class="col-12"> 
        <div class="md-form md-outline">
          <input type="text" id="nameUser" name="nameUser" class="form-control" required="" value="${pageContext.request.userPrincipal.name}"  style="pointer-events: none;">
          <label for="mdb_review_author">Tên</label>
        </div>
      </div>
      <div class="col-12"> 
        <div class="md-form md-outline">
          <textarea id="review" name="review" class="md-textarea form-control" rows="4" required=""></textarea>
          <label for="review">Đánh giá của bạn</label>
        </div>
      </div>
      <div class="col-12"> 
        <div style="padding-bottom: 10px">
          <button type="submit" class="btn btn-primary btn-sm ml-0 waves-effect waves-light" style="float: right;">Gửi<i class="far fa-paper-plane ml-2"></i></button>
        </div>
      </div>
  </div>
  <input type="hidden" id="idProduct" name="idProduct" value="${product.idProduct}">
</c:if>
</form>
   <!-- /.review product -->
<c:forEach items="${listReview}" var="review">
    <div style="border: 3px solid; border-radius: 20px; margin-top: 1rem;">
      <div style="margin-left: 1rem;">
        <div class="row mt-4" >
          <div class="col-1">
            <img  style="height: 80px; width: 80px; border-radius: 150%;" src="/getAvatarUser?idUser=${review.idUser}">
          </div>
          <div class="col mt-1" >
            <div class="input-group">
              <h5>${review.fullName} </h5>
              <i class="fas fa-check-circle" style="margin:1px 5px 1px 10px; color: #5cb85c; font-size:23px;"></i>
              <p>Chứng nhận đã mua hàng</p>
            </div>
              
              <p>Nhận xét vào ${review.reviewDate}</p>
          </div>
        </div>
  
        <div class="row">
          <div class="col">
            <ul class="rating mb-0" style="margin-top: 4px;margin-left: -25px;">
              <div class="group row">
                <li style="list-style-type: none;"><a><i class="fa-star yellow-text far<c:if test="${review.ranking>=1}"> fas</c:if>" data-index="1"></i></a></li>   
                <li style="list-style-type: none;"><a><i class="fa-star yellow-text far<c:if test="${review.ranking>=2}"> fas</c:if>" data-index="2"></i></a></li>
                <li style="list-style-type: none;"><a><i class="fa-star yellow-text far<c:if test="${review.ranking>=3}"> fas</c:if>" data-index="3"></i></a></li>
                <li style="list-style-type: none;"><a><i class="fa-star yellow-text far<c:if test="${review.ranking>=4}"> fas</c:if>" data-index="4"></i></a></li>
                <li style="list-style-type: none;"><a><i class="fa-star yellow-text far<c:if test="${review.ranking>=5}"> fas</c:if>" data-index="5"></i></a></li>
              </div>
            </ul>    
          </div>
        </div>
  
        <div class="row">
          <div class="col">
            <p>${review.review} </p>
          </div>
        </div>
      </div>
   </div>
</c:forEach>
   

   
</div>
 
 <!-- footer -->
 <jsp:include page='footer.jsp'></jsp:include>
  
    
    <!-- notification select size  -->
    <div class="modal fade" id="selectSizeFail" tabindex="-1"
        role="dialog" aria-labelledby="exampleModalCenterTitle"
        aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalCenterTitle">Thông báo</h5>
                </div>
                <div class="modal-body">Bạn cần phải chọn một size</div>

            </div>
        </div>
    </div>

    

    <!-- Notice of successful registration  -->
    <div class="modal fade" id="registerSuccess" tabindex="-1"
        role="dialog" aria-labelledby="exampleModalCenterTitle"
        aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalCenterTitle">Thông báo</h5>
                </div>
                <div class="modal-body">Bạn đã đăng ký thành công</div>

            </div>
        </div>
    </div>

    <!-- Registration notice failed  -->
    <div class="modal fade" id="registerFail" tabindex="-1" role="dialog"
        aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalCenterTitle">Thông báo</h5>
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
    
    <!-- Initializations -->
    <script type="text/javascript">
      $("#addcart").submit(function(){
             $("#numberSize").val($("#quantity").val());
            });

    $( document ).ready(function() {
        var loginSuccess = "${loginSuccess}";
        if(loginSuccess != ""){
            $('#nameAvatarLogin').css("visibility","visible");
            $('#btnLogin').css("visibility","hidden");
        }
      });

    $( document ).ready( function(){
            var s = $("#size").val() -1;
            $size = $('.nav-link-size');
            for(i = 0 ; i<=${listSize.size()} ; i++)
            {
                if(i == s ){
                    $($size.get(i)).removeClass('size-unactive');
                    $($size.get(i)).addClass('size-active');
                }
            }   
       
    });

    jQuery(document).ready(function($) {
        $('#mdb_review_rating').val(5);
        $stars = $('.review-rating');
        $stars.on('mouseover', function() {
          var currentIndex = parseInt($(this).data('index'));
          markStarsAsActive(currentIndex - 1);
          $('#mdb_review_rating').val(currentIndex);
        });
      }     
    );

      function markStarsAsActive(index) {
        unmarkActive();
        for (var i = 0; i <= index; i++) {
          $($stars.get(i)).removeClass('far');
          $($stars.get(i)).addClass('fas');
        }
        $("#rank").val(index+1);
      }

      function unmarkActive() {
        $stars.removeClass('fas');
        $stars.addClass('far');
      }


      function addCart(){
          $.ajax(
                    'addCart', 
                    {
                    type: 'POST',  // http method
                    data: {
                        idProduct: ${product.idProduct},
                        numberSize:$("#quantity").val(),
                        size: ${sizeSelect}                             
                         },  // data to submit
                    success: function (data, status, xhr){ 
                            if(data != "fail"){
                                $("#addProductSuccess").modal('show');  
                            }
                    },
                    error: function (jqXhr, textStatus, errorMessage) {
                            $('p').append('Error' + errorMessage);
                    }
                });
      }

      $(document).ready(function(){
       $("#rank_1").css("width","${percentage.get(0)}%");
       $("#rank_2").css("width","${percentage.get(1)}%");
       $("#rank_3").css("width","${percentage.get(2)}%");
       $("#rank_4").css("width","${percentage.get(3)}%");
       $("#rank_5").css("width","${percentage.get(4)}%");
      });

  </script>
  
  <!-- /.notification select size  -->
    <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="js/popper.min.js"></script>
    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="js/mdb.min.js"></script>

    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    
    <script type="text/javascript">
     // Animations initialization
     new WOW().init();
    
    </script>
</body>

</html>
    