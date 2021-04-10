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
  <link href="css/productShow.css" rel="stylesheet">
  
 <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
   
</head>
<style>  
</style>
<body>
    <jsp:include page='headShop.jsp'></jsp:include>
    <div class="container-fluid" style="padding: 0px !important; margin-top: 1rem;">
    <div class= "row">
        <jsp:include page='menuleft.jsp'></jsp:include>

        <div class="col-lg-10 col-md-12 col-sm-12 " style="margin-top: 7rem;">
            <div class="tab-pane fade active show right" id="allProduct" role="tabpanel" aria-labelledby="allProduct">          
                <div class = "panel panel-default" >
                    <div class="card" >
                        <div class="card-body">
                            <form action="saleschannel-allproduct-show-search" method="get"  id="saleschannel-allproduct-show-search">                                     
                                <div class="row">
                                    <div class="col">
                                        <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                                            <li class="nav-item">
                                                <a class="nav-link active" id="pills-conHang-tab" >Đang bán</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link " id="pills-hetHang-tab"  href="/saleschannel-allproduct-soldout">Hết hàng</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" id="pills-daAn-tab"  href="/saleschannel-allproduct-hide">Đã ẩn</a>
                                            </li>                           
                                        </ul>
                                    </div>
                                </div> <!--end row status product-->
                                <div class="row">
                                    <div class="col-lg-3 col-md-3">                                        
                                        <select class="browser-default custom-select" id="idPortfolio" name="portfolio">
                                            <option value="none" selected disabled hidden>Chọn ngành</option>
                                            <c:forEach items="${listProductPortfolio}" var="product">
                                                <option value="${product.idType}">${product.nameType}</option>
                                            </c:forEach>
                                        </select>                                       
                                    </div>
                                    <div class="col-lg-3 col-md-3">                                        
                                        <select class="browser-default custom-select" id="brand" name="brand">
                                            <option value="none" selected disabled hidden>Chọn loại hàng</option>
                                        </select>                                   
                                    </div>
                                    <div class="col-lg-4 col-md-3">
                                        <div class="md-form mt-0" >
                                            <input class="form-control" type="text" placeholder="Search" aria-label="Search"  id="idSearch" name="idSearch">
                                        </div>
                                    </div>
                                    <div class="col-lg-2 col-md-3">
                                        <button type="submit" class="btn btn-light">Tìm kiếm</button>
                                    </div>                  
                                </div>                                
                            </form>
                            <hr>
                            <div class="tab-content pt-2 pl-1" id="pills-tabContent">                                   
                                <div class="tab-pane fade show active" id="pills-conHang" role="tabpanel" aria-labelledby="pills-conHang-tab"> 
                                    <div class="row" >                           
                                        <c:forEach items="${listImageProduct}" var="product">
                                            <div class="col-lg-md-3" style="padding: 15px 15px">                                            
                                                <div class="card" style="width: 250px">                                                                        
                                                    <div class="view overlay" style=" height: 295px">
                                                        <c:if test="${product.priceDiscount !=0}">
                                                            <span class="badge badge-danger" style="float: right;">Giảm ${product.priceDiscount}%</span>
                                                        </c:if>
                                                        <img src="getImage?idProduct=${product.idProduct}" class="card-img-top" id="imageProduct" value="${product.idProduct}" alt="" height="100%">
                                                        <a> <div class="mask rgba-white-slight"></div></a>
                                                    </div>
                                                    <!--Card image-->
                                                    <!--Card content-->
                                                    <div class="card-body text-center" style=" height:190px">
                                                    <div class="group row" style="padding-left: 40%;" >             
                                                        <li style="list-style-type: none;"><a><i class="fa-star yellow-text far<c:if test="${product.ranking>=1}"> fas</c:if>" data-index="1"></i></a></li>   
                                                        <li style="list-style-type: none;"><a><i class="fa-star yellow-text far<c:if test="${product.ranking>=2}"> fas</c:if>" data-index="2"></i></a></li>
                                                        <li style="list-style-type: none;"><a><i class="fa-star yellow-text far<c:if test="${product.ranking>=3}"> fas</c:if>" data-index="3"></i></a></li>
                                                        <li style="list-style-type: none;"><a><i class="fa-star yellow-text far<c:if test="${product.ranking>=4}"> fas</c:if>" data-index="4"></i></a></li>
                                                        <li style="list-style-type: none;"><a><i class="fa-star yellow-text far<c:if test="${product.ranking>=5}"> fas</c:if>" data-index="5"></i></a></li>
                                                    </div>
                                                    <input type="hidden" id="mdb_review_rating" name="mdb_review_rating" value="4">
                                                        <div >
                                                            <strong> <a href="" class="dark-grey-text">${product.nameProduct}</a></strong>
                                                        </div>       
                                                        <c:if test="${product.priceDiscount !=0}">       
                                                            <span class="badge badge-pill danger-color" style="text-decoration: line-through">
                                                                <fmt:formatNumber value = "${product.priceProduct}" />đ
                                                            </span> 
                                                        </c:if>    
                                                        <br>                            
                                                        <h4 class="font-weight-bold blue-text">
                                                            <strong >                                       
                                                                    <fmt:formatNumber value = "${product.price}" />đ
                                                                <br>                                                                                                                                                               
                                                            </strong>
                                                        </h4>                           
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-6">
                                                           <button type="button" class="btn btn-tw hide" name="hide">Ẩn</button> 
                                                        </div>
                                                        <div class="col-6">
                                                           <button type="button" class="btn btn-tw fix" name="fix">Sửa</button> 
                                                        </div>                                                               
                                                    </div>                                                                                                                                                      
                                                </div>                                                
                                            </div>
                                        </c:forEach><!--for each all product-->
                                    </div>
                                </div>
                            </div>
                        </div>   
                    </div>
                </div> 
            </div>  
        </div><!--end column product -->
     </div>         
</div>                  
         
                        



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
                $( "#brand" ).append( "<option value='none' selected disabled hidden>Chọn loại hàng</option>");
                $.each(data, function( index, row ) {       
                    $( "#brand" ).append( "<option value='"+row.idBrand +"'>" + row.nameBrand +"</option>" );
                });             
            },
            error: function (jqXhr, textStatus, errorMessage) {
                    $('p').append('Error' + errorMessage);
            }
        });
    });
});
$(".hide").click(function(e){
    var idProduct = $(this).parent('div').parent('div').parent('div').find("#imageProduct").attr("value");
   $(this).parent('div').parent('div').parent('div').parent('div').remove();
    $.ajax(
        'hideProduct',
        {
            type: 'POST',
            data:{
                idProduct: idProduct
            },
            success: function (data,status,xhr){
                if(data == "success"){
                   
                }
            },
            error: function (jqXhr,textStatus,errorMessage){
                $('p').append('Error'+errorMessage);
            }
        })
});

</script>
    
</body>


</html>