<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Badminton</title>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- chart -->
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>

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
    <div class="container-fluid"  style="padding-left: 0px !important;padding-right: 0px !important;">
        <jsp:include page='headAdmin.jsp'></jsp:include>
       
        <div class="row">
            <div class="col-2" style="margin-top: 15%;">
                <jsp:include page='menu.jsp'></jsp:include>
            </div>
            <div class="col-10" style="margin-top: 5%;">
                <div class="bg bg-white bg-radius" >               
                    <div  style="text-align: center;">
                       <h3> Quản lý danh mục</h3>
                    </div>
                    <div class="row">
                        <div class="col" >
                            <h6 style="text-align: center;">Danh mục hiện có</h6>
                            <div class="list-group" id="categories" style="text-align: center;margin: 1rem;">
                                <c:forEach items="${categories}" var="cate">
                                    <a class="list-group-item list-group-item-action categories" data-id="${cate.idType}">${cate.nameType}</a>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="col">
                            <h6>Thêm danh mục</h6>
                            <div class="row">
                                <div class="col">
                                    <input type="text" class="form-control" placeholder="Tên danh mục" id="nameCategories">
                                </div>
                                <div class="col">
                                    <button type="button" class="btn btn-primary" disabled>Sửa</button>
                                    <button type="button" class="btn btn-success" onclick="addCategories()">Thêm</button>
                                    <button type="button" class="btn btn-danger" id="deleteCategories"  onclick="deleteCategories()" disabled>Xóa</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col" >
                            <h6 style="text-align: center;">Thương hiệu hiện có</h6>
                            <div class="list-group" style="text-align: center;margin: 1rem;">
                                <div id="brand">
                                
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <h6>Thêm thương hiệu</h6>
                            <div class="row">
                                <div class="col">
                                    <input type="text" class="form-control" placeholder="Tên danh mục" id="nameBrand">
                                </div>
                                <div class="col">
                                    <button type="button" class="btn btn-primary" disabled>Sửa</button>
                                    <button type="button" class="btn btn-success" onclick="addBrand()">Thêm</button>
                                    <button type="button" class="btn btn-danger" id="deleteBrand"  disabled>Xóa</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        var id;
      $(document).ready(function categories(e){
        categoriesClick();
      }
     );

     function brandClick(){
        $('.brand1').click(function(e){
            document.getElementById('nameBrand').value = $(this).html();
            document.getElementById('deleteBrand').removeAttribute('disabled');
        });
     }

     function categoriesClick(){
        $('.categories').click(function(e){
            document.getElementById('nameCategories').value = $(this).html();
            document.getElementById('deleteCategories').removeAttribute('disabled');
             id = parseInt($(this).data('id'));
            $.ajax(
                'selectPortfolio', 
                {
                type: 'GET',  // http method
                data: {
                    idType : id				    	
                        },  // data to submit
                success: function (data, status, xhr) {
                    document.getElementById('brand').innerHTML ='';
                    $.each(data, function( index, row ) { 		
                        $( "#brand" ).append( "<a class='list-group-item list-group-item-action brand1' data-idBrand='"+row.idBrand +"'>" + row.nameBrand + "</a>" );
                    });
                    brandClick();
                },
                error: function (jqXhr, textStatus, errorMessage) {
                        $('p').append('Error' + errorMessage);
                }
            });
        });
     }

      

      function addCategories(){
          var name=$('#nameCategories').val();
         if(name != ""){
            $.ajax(
              'addNewCategories',
              {
                  type:'POST',
                  data:{
                      name : name
                  },
                    success: function (data, status, xhr) {
                        document.getElementById('categories').innerHTML ='';
                        $.each(data, function( index, row ) { 		
                            $( "#categories" ).append( "<a class='list-group-item list-group-item-action categories' data-id='"+row.idType +"'>" + row.nameType + "</a>" );
                        });
                        categoriesClick();
                    },
                    error: function (jqXhr, textStatus, errorMessage) {
                        $('p').append('Error' + errorMessage);
                    }
              }
          );
         }else{
            alert("Vui lòng không để thông tin trống");
         }
      }

      function deleteCategories(){
        $.ajax(
              'deleteCategories',
              {
                  type:'POST',
                  data:{
                    id : id
                  },
                    success: function (data, status, xhr) {
                        document.getElementById('categories').innerHTML ='';
                        document.getElementById('nameBrand').value="";
                        $.each(data, function( index, row ) { 		
                            $( "#categories" ).append( "<a class='list-group-item list-group-item-action categories' data-id='"+row.idType +"'>" + row.nameType + "</a>" );                          
                        });
                        categoriesClick();
                    },
                    error: function (jqXhr, textStatus, errorMessage) {
                        $('p').append('Error' + errorMessage);
                    }
              }
          );
      }

      function addBrand(){
          var name=$('#nameBrand').val();
         if(name != ""){
            $.ajax(
              'addBrand',
              {
                  type:'POST',
                  data:{
                      name : name,
                      id : id
                  },
                    success: function (data, status, xhr) {
                        document.getElementById('nameBrand').innerHTML ='';
                        $.each(data, function( index, row ) { 		
                            $( "#brand" ).append( "<a class='list-group-item list-group-item-action brand1' data-idBrand='"+row.idBrand +"'>" + row.nameBrand + "</a>" );
                        });
                        brandClick();
                    },
                    error: function (jqXhr, textStatus, errorMessage) {
                        $('p').append('Error' + errorMessage);
                    }
              }
          );
         }else{
            alert("Vui lòng không để thông tin trống");
         }
      }
 </script>
 
</body>
</html>
