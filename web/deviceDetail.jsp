<%@page import="duonght.dto.Account"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Device Detail Page</title>
        <link rel="stylesheet" href="css/style.css" />
        <style>
            @import url("https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap");
        </style>
        <link rel="stylesheet" href="fonts/themify-icons/themify-icons.css" />
        <link rel="icon" href="./img/word-image-1047.jpg" type="image/x-icon" />
        <link
            rel="stylesheet"
            href="node_modules/bootstrap/dist/css/bootstrap.min.css"
            />
        <link
            rel="stylesheet"
            href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
            integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
            crossorigin="anonymous"
            />
        <script src="node_modules/jquery/dist/jquery.slim.min.js"></script>
        <script src="node_modules/popper.js/dist/umd/popper.min.js"></script>
        <script src="node_modules/bootstrap/dist/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="/fonts/fontawesome-free-6.1.1-web/css/all.min.css">

    </head>

    <body>
        <%
            Account acc = (Account) session.getAttribute("UserDB");
            boolean login = false;
            if (acc != null) {
                login = true;
            }
            if (login) {
        %>
        <c:set var="descriptionList" value="${requestScope.DESCRIPTION_LIST}"/>
        <c:set var="device" value="${requestScope.DEVICE}"/>
        <c:set var="brandList" value="${sessionScope.LIST_BRAND}"/>
        <c:set var="categoryList" value="${sessionScope.LIST_CATEGORY}"/>
        <c:set var="descriptionList" value="${sessionScope.LIST_DESCRIPTION}"/>
        <c:set var="description" value="${requestScope.DESCRIPTION_LIST}"/>

        <!-- nabar -->
        <div class="row navbar">
            <!-- logo -->
            <div class="col-sm-4 navbar-user-left d-flex align-items-center">
                <div class="col-sm-5 logo">
                    <a href="MainController?filter=&action=HomeSearchDevice&value=${category.value}"><img src="./img/logo.png" height="80" alt="" /></a>
                </div>
                <!-- product-list -->
                <div class="">
                    <div class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle d-flex align-items-center justify-content-center user-info" href="#"
                           id="navbarDropdownMenuLink" role="button" data-toggle="dropdown">
                            <p class="product-list">Product</p>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <c:forEach var="category" items="${categoryList}">
                                <li>
                                    <a class="dropdown-item" value="${category.key}" href="MainController?filter=${category.key}&action=HomeSearchDevice&value=${category.value}">${category.value}</a>
                                </li>
                            </c:forEach>
                            <li>
                                <a class="dropdown-item" href="MainController?filter=&action=HomeSearchDevice&value=${category.value}">All Product</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-sm-4 text-center navbar-user-fill"></div>
            <!-- cart-icon -->
            <div class="col-sm-4 text-center navbar-user-right d-flex">
                <div class="col-sm-6 cart-shopping">
                    <a href="Cart.jsp" class="" role="button">
                        <i class="fas fa-shopping-cart text-dark ml-5 "></i>
                    </a>
                </div>
                <!-- welcome -->
                <div class="col-sm-6">
                    <div class="nav-item dropdown align-items-center">
                        <a class="nav-link dropdown-toggle d-flex align-items-center justify-content-end user-info"
                           href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown">
                            <img src="${sessionScope.User.picture}"
                                 class="rounded-circle" height="25">
                            <p class="user-name">${sessionScope.UserDB.userName}</p>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <li>
                                <a class="dropdown-item" href="myprofile.jsp">My profile</a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="MainController?action=Logout">Logout</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="detail-devices-wapper d-flex row">
    <div class="col-sm-7 order-first">
        <img class="img-devices" src="${device.url}" id="main-img">
    </div>
    <div class="row detail-devices col-sm-5 mt-5">
        <div class="divices-name col-sm-12 mt-5">
            <h2>${device.deviceName}</h2>
            <br/>
        </div>
        <div class="col-sm-12">
            <form action="MainController" method="POST">
                
                <p>Warehouse : ${device.warehouseName}</p>
                <p>Brand Name : ${device.brandName}</p>
                <p>Quantity : ${device.quantity}</p>
                <p>Deposit : ${device.deposit} VND</p>
                <div class="buttons_added">
                    <p>Amount: </p>
                    <input aria-label="quantity" max="${device.quantity}" min="1" name="quantityToCart" type="number" value="1">
                    <input type="hidden" name="deviceID" value="${device.deviceID}"/>
                </div>
                </span>
                <div class="col-sm-12 mt-5">

                    <button type="submit" name="action" value="AddToCart" class="rent-button"><i class="fas fa-shopping-cart"> Add to Card</i></button>
                    <button type="submit" name="action" value="RentNow" class="rent-button-1">Borrow Now</button>
                </div> 
            </form>
        </div>
    </div>
    <div class="col-sm-12">
        <h2 class="ml-5 mt-5">PRODUCT DESCRIPTION</h2>
        <div class="ml-5">
            <c:forEach var="detail" items="${requestScope.DETAIL_LIST}" varStatus="counter">
                <p>${description[counter.count-1]}: ${detail.value}</p>
            </c:forEach>
        </div>
    </div>
    <div class="col-sm-12">

    </div>
</div>

<footer class="footer-distributed">
            <div class="footer-left">
                <p class="footer-company-name">
                    <img width="70% " src="img/logo.png" alt=""/>
                </p>
            </div>
            <div class="footer-center">
                <div>
                    <i class="fa fa-map-marker"></i>
                    <p><span>??i h?c FPT</span> KCN - TP.Th? ??c - TP.HCM</p>
                </div>
                <div>
                    <i class="fa fa-phone"></i>
                    <p>3463452343</p>
                </div>
                <div>
                    <i class="fa fa-envelope"></i>
                    <p><a href="mailto:admin@gmail.com">admin@gmail.com</a></p>
                </div>
            </div>
            <div class="footer-right">
                <p class="footer-company-about">
                    <span>About the company</span>
                <p class="text-justify">The software specializes in providing and lending IT equipment to FPT University students who need to borrow. 
                    The purpose of creating this software is to create opportunities for FPT University students to borrow IT
                    equipment to support their work and study at the school.</p>
                </p>
                <div class="footer-icons">
                    <a href="https://www.facebook.com/"><i class="ti-facebook"></i></a>
                    <a href="https://www.twitter.com/"><i class="ti-twitter"></i></a>
                    <a href="https://www.instagram.com/"><i class="ti-instagram"></i></a>
                    <a href="https://www.github.com/"><i class="ti-github"></i></a>
                </div>
            </div>
        </footer>
<%
} else {
%>
<h1>You Must Login To View This</h1>   
<div class="row mb-4">
    <div class="col-sm-12 col-md-6 d-flex justify-content-center">
        <a class="btn btn-lg btn-google btn-block text-uppercase btn-outline" href="https://accounts.google.com/o/oauth2/auth?scope=email profile&redirect_uri=http://localhost:8084/DeviceManagement/LoginHandler&response_type=code
           &client_id=33568893407-i7p94f2ca7var420dpis79903h4o46ut.apps.googleusercontent.com&approval_prompt=force"> <img src="https://img.icons8.com/color/16/000000/google-logo.png">Login With Google</a>   
    </div>
</div>
<%
    }
%>
</body>
</html>