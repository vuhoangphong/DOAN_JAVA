<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Badminton</title>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    
    <style type="text/css">
        .bg{
            border-radius: 85px;
            background: linear-gradient(315deg, #9ab9b8, #b7dcda);
            box-shadow:  -6px -6px 12px #95b3b1, 6px 6px 12px #c1e9e7;
            margin-bottom: 10px;
            border: none !important;
        }
        .bg-white{
            background: #ffffff !important;
        }
        .bg-radius{
            border-radius: 30px;
        }
        html, body {
    margin: 0;
    height: 100%;
    background: #afd2d0;
}
.row {
    margin-right: 0px !important; 
    margin-left: 0px !important;
    }
    </style>
</head>
<body>
    <div class="container-fluid" style="padding-left: 0px !important;padding-right: 0px !important;">
        <jsp:include page='headAdmin.jsp'></jsp:include>
        <div class="row" >
            <div class="col-2" style="margin-top: 15%;">
                <jsp:include page="menu.jsp"></jsp:include>
            </div>
            <div class="col-10" style="margin-top: 5%;">
                <div class="bg bg-white bg-radius" >  
                    <h2 style="text-align: center;"> Quản lý sản phẩm</h2>  
                    <form action="admin-search" method="GET">
                    <div class="row" style="margin: 1rem;">
                        <div class="col">
                            <div class="input-group">    
                                <h5>Ngành hàng:</h5>                                     
                                <select class="browser-default custom-select" id="idPortfolio" name="portfolio">
                                    <option value="none" selected disabled hidden>Chọn ngành</option>
                                    <c:forEach items="${listProductPortfolio}" var="product">
                                        <option value="${product.idType}">${product.nameType}</option>
                                    </c:forEach>
                                </select>                                       
                            </div>
                            <div class="input-group">  
                                <h5>Loại hàng:</h5>                                      
                                <select class="browser-default custom-select" id="brand" name="brand">
                                    <option value="none" selected disabled hidden>Chọn loại hàng</option>
                                </select>                                   
                            </div>
                          
                        </div>
                        <div class="col">
                            <div class="input-group">
                                <div class="md-form mt-0" >
                                    <input class="form-control" type="text" placeholder="Search" aria-label="Search"  id="idSearch" name="idSearch">
                                </div>                   
                                <button type="button" class="btn btn-light" id="btnSearch" onclick="search()" >Tìm kiếm</button>
                            </div>       
                            <div class="input-group">
                                <h5 style="margin-right: 5px;">Sắp xếp theo:</h5>
                                    <Select class="custom-select" name="status" id="status">                
                                    <option  value="1">Đang bán</option>
                                    <option  value="0">Đang ẩn</option>
                                    <option  value="2">Hết hàng</option>
                                    <option  value="3">Bị cấm</option>
                                </Select>
                            </div>
                        </div>
                    </div>    
                </form>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">ID sản phẩm</th>
                                <th scope="col">Ngày đăng</th>
                                <th scope="col" >Tên</th>
                                <th scope="col">Giá</th>
                                <th scope="col">ID shop</th>
                                <th scope="col">Tên shop</th>
                                <th scope="col"></th>
     
                            </tr>
                        </thead>
                        <tbody id="body">
                            <c:forEach var="product" items="${listProduct}"> 
                                <tr id="${product.idProduct}">
                                    <td>${product.idProduct}</td>
                                    <td>${product.dateInput}</td>
                                    <td>${product.nameProduct}</td>
                                    <td>${product.price}</td>
                                    <td>${product.idShop}</td>
                                    <td>${product.nameShop}</td>
                                    <td>
                                        <button name="ban" class="btn btn-danger" data-toggle="modal" data-target="#detail">cấm</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>       
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade"  id="detail" tabindex="-1" role="dialog" aria-labelledby="detail" aria-hidden="true" data-backdrop="static">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title"> Lý do cấm sản phẩm</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close" >
                        <span aria-hidden="true">&times;</span>
                    </button>                
                </div>
                <div class="modal-body">
                    <textarea class="textarea form-control" style="height: 200px;"></textarea>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal" id="close">Đóng</button>
                    <button type="button" class="btn btn-primary" id="save"  data-dismiss="modal" >Lưu</button>
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
var id = 0;
$(document).ready(function(){
    $("#idPortfolio").change(function (){
        var selectPortfolio = $(this).children("option:selected").val();
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

    $("#status").change(function(){
        search();
       
    });

  
    $('button[name="ban"]').click( function(e){     
        id = $(this).parent('td').parent('tr').attr("id");
        $('#save').click( function(e){     
            $('#'+id).remove();
        });
    });
    

},
);

function click(){
    $('button[name="ban"]').click( function(e){     
        id = $(this).parent('td').parent('tr').attr("id");
        $('#save').click( function(e){     
            $('#'+id).remove();
        });
    });
}
function search(){
        var status = $("#status").val();
        if(status ==0 )
        adminSearchShow()
        if(status ==1 )
        adminSearchShow()
        if(status ==2 )
            adminSearchShow()
        if(status ==3 )
        adminSearchBan()
       
    }
function adminSearchShow(){
    var portfolio = $('#idPortfolio').val();
    var brand = $('#brand').val();
    var status = $('#status').val();
    var search = $('#idSearch').val();
    $.ajax(
            'admin-Search', 
            {
            type: 'POST',  // http method
            data: {
                portfolio : portfolio,
                brand:brand,
                status:status,
                search:search
                 },  // data to submit
            success: function (data, status, xhr) {
                $( "#body" ).empty();
                $.each(data, function( index, row ) {       
                    $( "#body" ).append(
                                "<tr>"+
                                    "<td>"+row.idProduct+"</td>"+
                                    "<td>"+row.dateInput+"</td>"+
                                    "<td>"+row.nameProduct+"</td>"+
                                    "<td>"+row.price+"</td>"+
                                    "<td>"+row.idShop+"</td>"+
                                   " <td>"+row.nameShop+"</td>"+
                                   "<td>"+
                                       "<button class='btn btn-danger'>Cấm</button>"+
                                  "</td>"+
                                "</tr>"
                                );
                });             
            },
            error: function (jqXhr, textStatus, errorMessage) {
                    $('p').append('Error' + errorMessage);
        }
     });
};


function adminSearchBan(){
    var portfolio = $('#idPortfolio').val();
    var brand = $('#brand').val();
    var status = $('#status').val();
    var search = $('#idSearch').val();
    $.ajax(
            'admin-Search', 
            {
            type: 'POST',  // http method
            data: {
                portfolio : portfolio,
                brand:brand,
                status:status,
                search:search
                 },  // data to submit
            success: function (data, status, xhr) {
                $( "#body" ).empty();
                $.each(data, function( index, row ) {       
                    $( "#body" ).append(
                                "<tr>"+
                                    "<td>"+row.idProduct+"</td>"+
                                    "<td>"+row.dateInput+"</td>"+
                                    "<td>"+row.nameProduct+"</td>"+
                                    "<td>"+row.price+"</td>"+
                                    "<td>"+row.idShop+"</td>"+
                                   " <td>"+row.nameShop+"</td>"+
                                   "<td>"+
                                       "<button class='btn btn-danger'>Bỏ cấm</button>"+
                                  "</td>"+
                                "</tr>"
                                );
                });             
            },
            error: function (jqXhr, textStatus, errorMessage) {
                    $('p').append('Error' + errorMessage);
        }
     });
};

</script>



</body>
</html>