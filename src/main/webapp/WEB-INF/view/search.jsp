<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Badminton</title>
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

</head>
<body>
    
    <jsp:include page="head.jsp">
        <jsp:param name="title" value="${keyWord}"/>
    </jsp:include>
    <div class="container">
        <div class="row">
            <div class="col-3" style="margin-top: 8rem;">
                <div class="row">
                    <!--catalog-->
                    <div class="col-12" style="margin-top: -16px;">
                        <section class="section">

                            <ul class="list-group z-depth-1">
                                <li class="list-group-item d-flex justify-content-between align-items-center" onclick="window.location='/getproduct?idType=0';">
                                    <a class="dark-grey-text font-small"><i class="fas fa-indent mr-2" aria-hidden="true"></i> Tất cả</a>
                                    <a href=""></a><span class="badge badge-danger badge-pill">${countProductTpye.get(0)}</span>
                                </li>
                                <c:forEach items="${listPortfolios}" var="portfolios">
                                    <li class="list-group-item d-flex justify-content-between align-items-center" onclick="window.location='/getproduct?idType=${portfolios.idType}';">
                                        <a class="dark-grey-text font-small">${portfolios.nameType}</a>
                                       
                                    </li>
                               </c:forEach>
                            </ul>
                        </section>
                     </div>
                     <!-- price range-->
                     <form action="search" method="GET">
                        <div class="col-12"> 
                            <h5 class="font-weight-bold dark-grey-text mt-3 "> Chọn khoảng giá</h5>
                            <div class="mt-3" >
                                <div class="col-12">
                                    <input type="text" class="form-control" name="min" placeholder="giá">
                                </div>
                                <div class="col-12">
                                    <p class="mt-2" style="margin-left: 45%;">đến</p>
                                </div>
                                <div class="col-12">
                                    <input type="text" class="form-control" name="max" placeholder="giá">
                                </div>                      
                            </div>
                        </div>
                        <div class="col-12">
                            <div style="margin-left: 30%;">
                                <button type="submit" class="btn" style="background-color: #1cafe0;border-radius: 5rem;">Ok</button>
                                <input type="hidden" name="keyWord" value="${keyWord}">
                            </div>
                        </div>
                    </form>
                    <!--sort-->
                    <div class="col-12">
                       <div class="mt-2">
                        </div>                  
                    </div>
                    <!--Brand-->
                    <div class="col-12">
                        <h5 class="font-weight-bold dark-grey-text mt-3 ">Thương hiệu</h5>
                        <c:forEach items="${brands}" var="brand">
                            <p><a href="getproductbrands?idBrand=${brand.idBrand}">${brand.nameBrand}</a></p>
                        </c:forEach>
                        <p></p>
                    </div>
                    <!--rank-->
                    <div class="col-12">
                        <h5 class="font-weight-bold dark-grey-text mt-3 "><strong>Rating</strong></h5>
                        <div class="divider"></div>
                        <div class="row ml-1">
                            <!-- Rating -->
                            <ul class="rating mb-0 group row">                                  
                                <li style="list-style-type: none;"><i class="fas fa-star yellow-text"></i></li>
                                <li style="list-style-type: none;"><i class="fas fa-star yellow-text"></i></li>
                                <li style="list-style-type: none;"><i class="fas fa-star yellow-text"></i></li>
                                <li style="list-style-type: none;"><i class="fas fa-star yellow-text"></i></li>
                                <li style="list-style-type: none;"><i class="fas fa-star yellow-text"></i></li>                                                                            
                            </ul>

                        </div>

                        <div class="row ml-1">
                            <!-- Rating -->
                             <ul class="rating mb-0 group row">                                     
                                <li style="list-style-type: none;"><i class="fas fa-star yellow-text"></i></li>
                                <li style="list-style-type: none;"><i class="fas fa-star yellow-text"></i></li>
                                <li style="list-style-type: none;"><i class="fas fa-star yellow-text"></i></li>
                                <li style="list-style-type: none;"><i class="fas fa-star yellow-text"></i></li>
                                <li style="list-style-type: none;"><i class="far fa-star yellow-text"></i></li>                                                                              
                            </ul>

                        </div>

                        <div class="row ml-1">
                            <!-- Rating -->
                             <ul class="rating mb-0 group row">                                     
                                <li style="list-style-type: none;"><i class="fas fa-star yellow-text"></i></li>
                                <li style="list-style-type: none;"><i class="fas fa-star yellow-text"></i></li>
                                <li style="list-style-type: none;"><i class="fas fa-star yellow-text"></i></li>
                                <li style="list-style-type: none;"><i class="far fa-star yellow-text"></i></li>
                                <li style="list-style-type: none;"><i class="far fa-star yellow-text"></i></li>                                                                        
                            </ul>
                        </div>
                        
                        <div class="row ml-1">
                            <!-- Rating -->
                             <ul class="rating mb-0 group row">                                     
                                <li style="list-style-type: none;"><i class="fas fa-star yellow-text"></i></li>
                                <li style="list-style-type: none;"><i class="fas fa-star yellow-text"></i></li>
                                <li style="list-style-type: none;"><i class="far fa-star yellow-text"></i></li>
                                <li style="list-style-type: none;"><i class="far fa-star yellow-text"></i></li>
                                <li style="list-style-type: none;"><i class="far fa-star yellow-text"></i></li>                                                                           
                            </ul>
                        </div>
                        
                        <div class="row ml-1">
                            <!-- Rating -->
                             <ul class="rating mb-0 group row">                                     
                                <li style="list-style-type: none;"><i class="fas fa-star yellow-text"></i></li>
                                <li style="list-style-type: none;"><i class="far fa-star yellow-text"></i></li>
                                <li style="list-style-type: none;"><i class="far fa-star yellow-text"></i></li>
                                <li style="list-style-type: none;"><i class="far fa-star yellow-text"></i></li>
                                <li style="list-style-type: none;"><i class="far fa-star yellow-text"></i></li>                                                                         
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-9" style="margin-top: 8rem;">
                <section class="text-center mb-4">
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
                                </div>
                            </div>   
                        </c:forEach>
                    </div>
                </section>
            </div>
        </div>
        <div class="row">
           
        </div>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
    <script>
    </script>
</body>
</html>