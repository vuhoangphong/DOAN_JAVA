<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

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
<link href="css/welcome.css" rel="stylesheet">

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>



<style type="text/css">
.yellow-text{
    color: #f2a707!important;
}
</style>




</head>
<body>
    <jsp:include page='head.jsp'></jsp:include>
    <div id="carousel-example-1z" class="carousel slide carousel-fade pt-1" data-ride="carousel">

        <ol class="carousel-indicators">
            <li data-target="#carousel-example-1z" data-slide-to="0"class="active"></li>
            <li data-target="#carousel-example-1z" data-slide-to="1"></li>
            <li data-target="#carousel-example-1z" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner" style="margin-top: 99px;" role="listbox">

            
            <div class="carousel-item active">
                <div class="view banner-default1">
                    <div class="mask rgba-black-strong d-flex justify-content-center align-items-center"></div>
                </div>
            </div>
            
            <div class="carousel-item">
                <div class="view banner-default2">               
                    <div class="mask rgba-black-strong d-flex justify-content-center align-items-center"></div>                      
                 </div>                  
            </div>
            
            
            <div class="carousel-item">
                <div class="view banner-default3">          
                    <div class="mask rgba-black-strong d-flex justify-content-center align-items-center"></div>
                </div>
            </div>
            

        </div>
      
        <a class="carousel-control-prev" href="#carousel-example-1z" role="button" data-slide="prev"> 
            <span class="carousel-control-prev-icon" aria-hidden="true"></span> 
            <span class="sr-only">Previous</span>
        </a> 
        <a class="carousel-control-next" href="#carousel-example-1z" role="button" data-slide="next"> 
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
      

    </div>
    
    </div>
    <!--Main layout-->
    <main>
    <div class="container">
<div class="row" style="margin-top: 8rem;"> 
<div class="col-lg-3">
    <div class="">
                  
                    <div class="row">
                         <div class="col-12" style="margin-top: -16px;">
                            <section class="section">

                                <ul class="list-group z-depth-1">
                                    
                                    <li class="list-group-item d-flex justify-content-between align-items-center" onclick="window.location='/getproduct?idType=0';">
                                        <a class="dark-grey-text font-small"><i class="fas fa-indent mr-2" aria-hidden="true"></i> Tất cả</a>
                                        <a href=""></a><span class="badge badge-danger badge-pill">${countProductTpye.get(0)}</span>
                                    </li>
                                    <c:forEach items="${listPortfolios}" var="portfolios" varStatus="loop">
                                        <li class="list-group-item d-flex justify-content-between align-items-center" onclick="window.location='/getproduct?idType=${portfolios.idType}';">
                                            <a class="dark-grey-text font-small">${portfolios.nameType}</a>
                                           
                                            
                                        </li>
                                   </c:forEach>
                                    
                                </ul>
                            </section>
                         </div>   
                    </div>                              
                </div>
    </div>
    
    <div class="col">
        <!--Section: Products v.3-->
        <section class="text-center mb-4">
            <!--Grid row-->
            <div class="row wow fadeIn">
                <c:forEach items="${listProduct}" var="product">     
                    <div class="col-lg-4 col-md-6 mb-4" onclick="window.location='/productdetail?idProduct=${product.idProduct}';">
                        <!--Card-->
                        <div class="card">
                            <!--Card image-->
                            <div class="view overlay" style=" height: 295px">
                                <c:if test="${product.priceDiscount !=0}">
                                    <span class="badge badge-danger" style="float: right;">Giảm ${product.priceDiscount}%</span>
                                </c:if>
                                <img src="getImage?idProduct=${product.idProduct}" class="card-img-top" alt="" height="100%">
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
                            <!--Card content-->
                        </div>
                        <!--Card-->
                    </div>
                    <!--Grid column-->            
                </c:forEach>

            </div>
            <!--Grid row-->

            <!--Grid row-->
            
        </section>
        <!--Section: Products v.3-->
    </div>
    
</div>


        <!--Pagination-->
        <nav class="d-flex justify-content-center wow fadeIn">
            <ul class="pagination pg-blue">

                <!--Arrow left-->
                <li class="page-item disabled"><a class="page-link" href="#"
                    aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
                        <span class="sr-only">Previous</span>
                </a></li>

                <li class="page-item active"><a class="page-link" href="#">1
                        <span class="sr-only">(current)</span>
                </a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item"><a class="page-link" href="#">4</a></li>
                <li class="page-item"><a class="page-link" href="#">5</a></li>

                <li class="page-item"><a class="page-link" href="#"
                    aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
                        class="sr-only">Next</span>
                </a></li>
            </ul>
        </nav>
        <!--Pagination-->



    </div>


    </main>
    
   
    <!-- footer -->
    <jsp:include page='footer.jsp'></jsp:include>
    <!-- form create user-->


   



    <input type="hidden" id="success" value="${success}">
    <input type="hidden" id="value" value="${value}">

    <script type="text/javascript">

  $( document ).ready(function() {
        var success = $("#success").val();
        if(success == "true")
            $('#registerSuccess').modal('show');
        if(success == "false")
            $('#registerFail').modal('show');
        if(success == "exist"){    
            $('#centralModalSuccess').modal('show');          
            }   
    });
    
  $( document ).ready(function() {
        var loginSuccess = "${loginSuccess}";
        if(loginSuccess != ""){
            $('#nameAvatarLogin').css("visibility","visible");
            $('#btnLogin').css("visibility","hidden");
        }
      });
    


       
    
      function displayForm() {
          $('#abc').css('display', 'block');
      }


      $(document).ready(function(){
          var mySlider = new rSlider({
                target: '#slider',             
                range: true, // range slider
                min: 0,
                max: 10000,
                values: [0, 10000],
                step: 100,
            });
          });
          

      $(document).ready(

        function filter(){
            $('input:radio[name="groupRadio"]').each(function(){
                    if($(this).val() == $('#checked').val()){
                        $(this).prop('checked', true);
                    }                   
                });
          }
      );

      function filter(number){      
          var value = $("#value").val();        
            if(number==0  ){
                if(!value){
                    window.location='/getproduct?idType=0&value=0';
                 }else{
                     window.location='/getproduct?idType='+value+'&value='+value+'';
                }               
            }                       
            if(number==3){
                if(!value){
                    window.location='/getproductlowtohight?idType=0&value=0';
                 }else{
                     window.location='/getproductlowtohight?idType='+value+'&value='+value+'';
                }           
                
            }
            if(number==4 && value){
                if(!value){
                    window.location='/getproducthighttolow?idType=0&value=0';
                 }else{
                     window.location='/getproducthighttolow?idType='+value+'&value='+value+'';
                }   
                
            }   
                    
        }                   

        $('#multi').mdbRange({
  single: {
    active: true,
    multi: {
      active: true,
      rangeLength: 1
    },
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
    <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
    

    
    
    <!-- Initializations -->
    <script type="text/javascript">
    // Animations initialization
    new WOW().init();

  </script>

</body>

</html>
