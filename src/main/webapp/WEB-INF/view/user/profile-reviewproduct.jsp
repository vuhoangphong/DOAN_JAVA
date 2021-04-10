<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

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
                <jsp:param name="review" value="active" />
            </jsp:include> <!-- menu left-->
         </div> <!-- end column -->
         
    <div class="col-8" style="margin-top: 9rem;">
        <section class="text-center mb-4">
            <div class="row wow fadeIn">
                <c:forEach items="${listProduct}" var="product">                
                    <div class="col-lg-4 col-md-6 mb-4" onclick="window.location='/productdetail?idProduct=${product.idProduct}';">              
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
                </c:forEach>
            </div>
        </section>
    </div> <!--  end row -->
</div>
</div>
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
        // Animations initialization
        new WOW().init();
    </script>

</body>


</html>