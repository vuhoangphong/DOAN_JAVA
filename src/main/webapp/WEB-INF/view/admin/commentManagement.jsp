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
    <div class="container-fluid" style="padding-left: 0px !important;padding-right: 0px !important;">
        <jsp:include page='headAdmin.jsp'></jsp:include>
       
        <div class="row">
            <div class="col-2" style="margin-top: 15%;">
                <jsp:include page='menu.jsp'></jsp:include>
            </div>
            <div class="col-10" style="margin-top: 5%;">
                <div class="bg bg-white bg-radius" >  
                    <h2 style="text-align: center;"> Quản lý bình luận</h2>  
                    <div class="row" style="margin: 1rem;">
                        <div class="col">
                            <h5 style="float: right;">Sắp xếp theo:</h5>
                        </div>
                        <div class="col-2">
                            <Select class="custom-select">
                                <option  value="0">Tất cả</option>
                                <option  value="1">1 sao</option>
                                <option  value="2">2 sao</option>
                                <option  value="3">3 sao</option>
                                <option  value="4">4 sao</option>
                                <option  value="5">5 sao</option>
                            </Select>
                        </div>
                    </div>    
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col" style="width: 150px;">Thời gian</th>
                                <th scope="col">Đáng giá</th>
                                <th scope="col">Rank</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody id="body">
                            <c:forEach var="rank" items="${listRank}">
                            <tr>
                                <td>${rank.reviewDate}</td>
                                <td>${rank.review}</td>
                                <td>${rank.ranking}</td>
                                <td>
                                    <a href="http://localhost:8080/productdetail?idProduct=${rank.idProduct}">xem</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>       
                </div>
        </div>
    </div>
    <script>
    $(document).ready(function(e){
        $('select').change(function(e){
            var id = $(this).val();
            if(id == 0 )
               getRank(id);
            if(id == 1 )
                getRank(id);
            if(id == 2 )
                getRank(id);
            if(id == 3 )
                getRank(id);
            if(id == 4 )
                getRank(id);
            if(id == 5 )
                getRank(id);
        });
    });
    function getRank(id){
        $.ajax(
            'getRank',
            {
                type:'POST',
                data:{
                    rank : id
                },
                success:function(data,status,xhr){
                    $('#body').empty();
                    $.each(data,function(index,row){
                        $('#body').append(
                            "<tr>"+
                                "<td>"+row.reviewDate+"</td>"+
                                "<td>"+row.review+"</td>"+
                                "<td>"+row.ranking+"</td>"+
                                "<td>"+
                                    "<a href="+"http://localhost:8080/productdetail?idProduct="+row.idProduct+">xem</a>"+
                               "</td>"+
                            "</tr>"
                        );
                    });
                    
                },
                error:function(jqXhr,textStatus,errorMessage){
                    alert('error');
                }
            }
        )
    }
    </script>
</body>
</html>
