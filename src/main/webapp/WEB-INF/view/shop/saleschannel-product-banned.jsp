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
  <link href="css/menuleft.css" rel="stylesheet">
  <link href="css/productBanned.css" rel="stylesheet">
 <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
   
<style type="text/css">

</style> 
</head>
<body>
    <jsp:include page='headShop.jsp'></jsp:include>
    <div class="container-fluid" style="padding: 0px !important; margin-top: 1rem;">
    <div class= "row">
        <jsp:include page='menuleft.jsp'></jsp:include>
    
        <div class="col-lg-10 col-md-12 col-sm-12">
            <div class="tab-pane fade ative show right " id="v-pills-sanPhamViPham" role="tabpanel" aria-labelledby="v-pills-sanPhamViPham-tab">
                <div class = "panel panel-default" >
                    <div style="padding-left: 40px; padding-right: 40px; padding-top: 10px;">
                        <div class=" row">
                            <div class="col-lg-4 col-md-4 col-sm-12" > 
                                <p class="font-weight-bold">Sản phẩm vi phạm</p>
                            </div>  
                            <div class="col-lg-4 col-md-4 col-sm-12" >              
                                <select class="browser-default custom-select">
                                    <option selected>select ngành hàng</option>                   
                                </select>
                            </div>      
                            <div class="col-lg-4 col-md-4 col-sm-12" >             
                                <select class="browser-default custom-select">
                                    <option selected>select loại hàng</option>                    
                                </select>
                            </div>                                  
                        </div>
                        <hr>
                        <table class="table table-image">
                            <thead>
                                <tr>
                                  <th scope="col">STT</th>
                                  <th scope="col">Hình ảnh</th>
                                  <th scope="col">Tên sản phẩm</th>
                                  <th scope="col">Lý do vi phạm</th>
                                  <th scope="col">Ngày bị cấm</th>                                       
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listProduct}" var="product">
                                    <tr>
                                        <th scope="row">1</th>
                                            <td class="w-25">
                                            <img src="getImage?idProduct=${product.idProduct} " class="img-fluid img-thumbnail" >
                                            </td>
                                            <td>${product.nameProduct}</td>
                                            <td>${product.reasonBanned}</td>
                                            <td>${product.dayBanned}</td>                                       
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div> 
                </div>                      
            </div>
        </div>
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
</script>
</body>


</html> 