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
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<style type="text/css">
.center{
    display: block;
    margin-left: auto;
    margin-right: auto;
}
.modal-dialog {
    max-width: 100% !important;
    margin: 1.75rem auto;
}
</style>
</head>
<body>
    <jsp:include page="../head.jsp"></jsp:include> <!-- head page -->
<div class="container" style="margin-top: 1rem;height: 100%;">
    <div class="row">
        <div class="col-4" style="margin-top: 9rem;">
            <jsp:include page="menuLeft.jsp">
                <jsp:param name="orderManagement" value="active" />
            </jsp:include> <!-- menu left-->
         </div>
        <div class="col-8" style="margin-top: 9rem;">
            <!-- right pannel of quản lý đơn hàng -->
            <div class="tab-pane fade active show " id="v-pills-quanLyDonHang"role="tabpanel" aria-labelledby="v-pills-quanLyDonHang-tab">
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
                                            <td id="idOrder">${order.idOrder}</td>                                                                                       
                                            <td>${order.deliveryDate}</td>                                            
                                            <td>${order.totalMoney}</td>                                                                                    
                                            <td>${order.shippingStatus}</td>
                                            <td>
                                                <button type="button" class="btn">xem</button>
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
            </div>
        </div>  
    </div>
</div>
<!-- footer -->
<jsp:include page='../footer.jsp'></jsp:include>


<div class="modal fade bottom"  id="detail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="margin: 100px 100px;" role="document">
        <div class="modal-content" >
            <div class="modal-header" id = "titleDetail">       
            </div>
            <div class="modal-body">
                <table id="tablePreview" class="table table-striped">
                    <!--Table head-->
                        <thead>
                            <tr>                                            
                                <th>Tên sản phẩm</th>                                                                                   
                                <th>Số lượng</th>                                           
                                <th>Giá </th>                                           
                                <th>Shop</th> 
                                <th>Trạng thái</th>   
                                <th>Vận Chuyển</th>                  
                            </tr>
                        </thead>
                        <!--Table head-->
                        <!--Table body-->
                        <tbody id="bodyDetail">                                       
                       </tbody>
                       <!--Table body-->
                    </table>
                    <!--Table-->
            </div>
            <div class="modal-footer">
                <button type="button" id="btn-close" class="btn btn-secondary" data-dismiss="modal">đóng</button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade bottom" id="addProductFail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-frame modal-top" role="document">
    <div class="modal-content">
      <div class="modal-body">
        <div class="row d-flex justify-content-center align-items-center">
          <p class="pt-3 pr-2"> không thành công </p>        
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
          <p class="pt-3 pr-2">thành công </p>        
        </div>
      </div>    
    </div>
  </div>
</div>
<input type="hidden" id="status" value="${transactionStatus}">
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

    $(document).ready(function(){
        var status = $("#status");
        if(status == 1){
            $("#addProductSuccess").modal('show');
            }
        else if(status != 1 && status >= 0){
            $("#addProductFail").modal('show');
            }
        else
        {} }
     );
   
     $(document).ready(function(){
        $('button').click( function(e){
           var id = $(this).parent('td').parent('tr').find('#idOrder').html();
            $('#bodyDetail').empty();
            $('#titleDetail').empty();

            $('#titleDetail').append(
                "<h5 class=modal-title'>"+"Chi tiết hóa đơn:"+id+"</h5>"   
            );
            $.ajax(
            'order-detail', 
            {
            type: 'POST',  // http method
            data: {
                id:id
                 },  // data to submit
            success: function (data, status, xhr) {
                $.each(data,function(index,row){
                    $( "#bodyDetail").append(
                        "<tr>"+
                            "<td>"+row.nameProduct+"</td>"+
                            "<td>"+row.numberProduct+"</td>"+
                            "<td>"+row.price+"</td>"+
                            "<td>"+row.nameShop+"</td>"+
                            "<td>"+row.status+"</td>"+
                            "<td>"+row.nameCompanyShipper+"</td>"+
                        "</tr>" 
                  );     
                });
                     
            },
            error: function (jqXhr, textStatus, errorMessage) {
                    
        }
        });                
        $("#detail").modal('show');
            });
     });
  
     
           
        

</script>

</body>


</html>