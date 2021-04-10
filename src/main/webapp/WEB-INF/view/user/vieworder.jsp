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
    <link rel="stylesheet"
    href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="css/mdb.min.css" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="css/style.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="../head.jsp"></jsp:include> <!-- head page -->
    <div class="container" style="margin-top: 1rem;">
        <div class="row">
            <div class="col-4" style="margin-top: 9rem;">
                <jsp:include page="menuLeft.jsp">
                    <jsp:param name="orderManagement" value="active" />
                </jsp:include> <!-- menu left-->
             </div>
            <div class="col-8" style="margin-top: 9rem;">
               <div class="row"> 
                    <div class="col">
                        <h3>Chi tiết đơn hàng #123 - Trạng thái giao hàng</h3>                       
                    </div>
               </div>
               <div class="row"> 
                    <div class="col">
                        <h7 style="float: right;">Ngày đặt: 12/2/2020</h7>                   
                    </div>
               </div>

               <div class="row" style="margin-top: 3px;"> 
                    <div class="col">
                        <p>ĐỊA CHỈ NGƯỜI NHẬN</p>  
                        <div style="background-color:rgb(239, 239, 239);height: 140px;margin: 5px;">
                            <p>ĐỊA CHỈ NGƯỜI NHẬN</p> 
                            <p>ĐỊA CHỈ NGƯỜI NHẬN</p> 
                            <p>ĐỊA CHỈ NGƯỜI NHẬN</p> 
                        </div>                 
                    </div>
                    <div class="col">
                        <p>HÌNH THỨC GIAO HÀNG</p>      
                        <div style="background-color:rgb(239, 239, 239);height: 140px;margin: 5px;">
                            <p>ĐỊA CHỈ NGƯỜI NHẬN</p> 
                            <p>ĐỊA CHỈ NGƯỜI NHẬN</p> 
                            <p>ĐỊA CHỈ NGƯỜI NHẬN</p> 
                        </div>            
                    </div>
                    <div class="col">
                        <p>HÌNH THỨC THANH TOÁN</p>  
                        <div style="background-color:rgb(239, 239, 239);height: 140px;margin: 5px;">
                            <p>ĐỊA CHỈ NGƯỜI NHẬN</p> 
                            <p>ĐỊA CHỈ NGƯỜI NHẬN</p> 
                            <p>ĐỊA CHỈ NGƯỜI NHẬN</p> 
                        </div>             
                    </div>
                </div>
               
                <!--table product-->
                    <div class="panel panel-default">
                        <div class="card" >
                            <div class="card-body">
                                <div class='table-responsive'>
                                    <!--Table-->
                                    <table id="tablePreview" class="table table-striped">
                                    <!--Table head-->
                                        <thead>
                                            <tr>                                            
                                                <th>Mã hóa đơn</th>                                                                                   
                                                <th>Ngày dự kiến giao</th>                                           
                                                <th>Tổng tiền</th>                                           
                                                <th>Trạng thái</th> 
                                                <th></th>                    
                                            </tr>
                                        </thead>
                                        <!--Table head-->
                                        <!--Table body-->
                                        <tbody>
                                        <c:forEach items="${listOrder}" var="order">
                                            <tr>                                           
                                                <td>${order.idOrder}</td>                                                                                       
                                                <td>${order.deliveryDate}</td>                                            
                                                <td>${order.totalMoney}</td>                                                                                    
                                                <td>${order.shippingStatus}</td>
                                                <td>
                                                    <button type="button" class="btn" data-toggle="modal" data-target="#detail" data-keyboard="false" data-backdrop="static">xem</button>
                                                </td>
                                            </tr>
                                        </c:forEach>                                       
                                       </tbody>
                                       <!--Table body-->
                                    </table>
                                    <!--Table-->
                                </div>
                            </div>
                        </div>
                    </div>
                <!--end table product-->
                
            </div>  
        </div>
    </div>
    <!-- footer -->
    <jsp:include page='../footer.jsp'></jsp:include>
</body>
</html>