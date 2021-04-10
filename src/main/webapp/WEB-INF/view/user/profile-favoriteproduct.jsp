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
<div class="container" style="margin-top: 1rem;height: 100%;">
    <div class="row">
        <div class="col-4" style="margin-top: 9rem;">
            <jsp:include page="menuLeft.jsp">
                <jsp:param name="favorite" value="active" />
            </jsp:include> <!-- menu left-->
        </div>
        <div class="col-8" style="margin-top: 9rem;">
            
        </div>  
    </div>
</div>
<!-- footer -->
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