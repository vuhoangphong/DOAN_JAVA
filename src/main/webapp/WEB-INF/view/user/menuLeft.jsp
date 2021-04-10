<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="card bg-light mb-3" style="max-width: 20rem">
    <div class="card-header" style="background: white;">
        <div class="card-body d-flex flex-row">
            <!-- Avatar -->
            <img src="/getAvatarUser?idUser=${user}"
                class="rounded-circle mr-3" height="50px" width="50px"
                alt="avatar">
            <!-- Content -->
            <div>
                <!-- Title -->
                <h4 class="card-title font-weight-bold mb-2">${userInfo.fullName}</h4>
            </div>
        </div>
    </div>

    <div class="card-body" style="background: white;">				
        <div class="row">
            <div class="col">
                <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist"aria-orientation="vertical">
                    <div class="row">                                       
                        <p class="text-dark" >
                              <a class="nav-link ${param.profile} " style="color: black;" href="/profile" >                                       
                                  <i class="fas fa-user" style=" margin-right : 1rem;color: black;"> </i>
                              Thông tin tài khoản</a> 
                        </p>
                    </div>
                    <div class="row">                                      
                        <p class="text-dark" >
                            <a class="nav-link ${param.orderManagement} " style="color: black;" href="/profile-ordermanagement" >                                       
                                <i class="fas fa-receipt" style=" margin-right : 1rem;color: black;"> </i>
                            Quản lý đơn hàng</a> 
                        </p>
                    </div>
                    <div class="row">                                       
                        <p class="text-dark" >
                            <a class="nav-link ${param.review}" style="color: black;" href="/profile-reviewproduct" >                                       
                                <i class="far fa-edit" style=" margin-right : 1rem;color: black;"> </i>
                            Nhận xét sản phẩm đã mua</a> 
                        </p>
                    </div>
                    <div class="row">                                       
                        <p class="text-dark" >
                            <a class="nav-link ${param.favorite}" style="color: black;" href="/profile-favoriteproduct" >                                       
                                <i class="fas fa-heart" style=" margin-right : 1rem;color: black;"> </i>
                            Sản phẩm yêu thích</a> 
                        </p>
                    </div>                                   
                    <div class="row">                                       
                        <p class="text-dark" >
                            <a class="nav-link ${param.viewed}" style="color: black;" href="/profile-viewedproduct" >                                       
                                <i class="fas fa-user" style=" margin-right : 1rem;color: black;"> </i>
                            Sản phẩm đã xem</a> 
                        </p>
                    </div>                                   
                </div>
            </div>
        </div>					
    </div>
</div>