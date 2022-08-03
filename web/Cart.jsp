<%-- 
    Document   : Cart
    Created on : Jul 19, 2022, 11:08:16 AM
    Author     : Admin
--%>
<%@page import="duonght.dto.Account"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CART</title>
        <link rel="stylesheet" href="css/style.css">
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap');
        </style>
        <link rel="stylesheet" href="node_modules/bootstrap/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="fonts/themify-icons/themify-icons.css">
        <link rel="icon" href="./img/word-image-1047.jpg" type="image/x-icon">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
              integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
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
        <!-- navbar -->
        <c:set var="categoryList" value="${sessionScope.LIST_CATEGORY}"/>

        <div class="row navbar">
            <!-- logo -->
            <div class="col-sm-4 navbar-user-left d-flex align-items-center">
                <div class="col-sm-5 logo">
                    <a href="MainController?filter=&action=HomeSearchDevice&value=${category.value}"><img src="./img/logo.png" height="80" alt="" /></a>
                </div>
                <!-- product-list -->
                <div class="mr-auto">
                    <div class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle d-flex align-items-center justify-content-center user-info" href="#"
                           id="navbarDropdownMenuLink" role="button" data-toggle="dropdown">
                            <p class="product-list">Device</p>
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
                <a href="MainController?action=LoadProcessRequest">Request List</a>
            </div>

            <!-- cart-icon -->
            <div class="col-sm-4 text-center navbar-user-right d-flex">
                <div class="col-sm-6 cart-shopping">
                    <a href="#" class="" role="button">
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
        <div class="mt-1 ml-3">
<!--            <a href="user.html">
                <button type="button"  onclick="location.href = 'MainController?action=LoadProcessRequest'"  class="btn btn-danger">
                    Home
                </button>
            </a>-->
        </div>
        <div class="">
            <div class="col-sm-12">
                <form action="MainController" method="POST">

                    <div class="table table-all container table-user">
                        <table class="col-sm-12">
                            <thead>
                            <th class="text-center">DeviceID</th>
                            <th class="text-center">Name Product</th>
                            <th class="text-center">Brand</th>
                            <th class="text-center">WarehouseName</th>
                            <th class="text-center">Quantity</th>
                            <th class="text-center">BorrowDate</th>
                            <th class="text-center">Action</th>
                            </thead>
                            <c:forEach var="device" items="${sessionScope.CART.getCart()}" varStatus="counter">
                                <tbody>
                                    <tr id="info">
                                        <td class="text-center">${device.value.deviceID}</td>
                                        <td class="text-center">${device.value.deviceName}</td>
                                        <td class="text-center">${device.value.warehouseName}</td>
                                        <td class="text-center">${device.value.brandName}</td>
                                        <td class="text-center"><input type="number" min="1" value="${device.value.quantity}"name="deviceQuantity"/> </td>       
                                        <td class="text-center">
                                            <select class="borrowdate" name="borrowedDate${counter.count}">
                                                <option value="15">15 days</option>
                                                <option value="30">30 days</option>
                                                <option value="90">90 days</option>
                                            </select>
                                        </td>
                                        <td class="text-center" >
                                            <input type="hidden" name="deviceID" value="${device.value.deviceID}"/>
                                            <button type="submit"  name="action" value="UpdateCart"  class="btn btn-success center2"><i class="fas fa-recycle"></i></button>
                                            <button type="button"  onclick="location.href = 'MainController?action=DeleteItemInCart&deviceID=${device.value.deviceID}'"  class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
                                        </td>
                                    </tr>
                                </tbody>
                            </c:forEach>
                        </table>
                    </div>
                    <button class="btn-color button-rent" type="submit" name="action" value="CreateRequest">
                        Borrow
                    </button>

                </form>
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
                    <p><span>Đại học FPT</span> KCN - TP.Thủ Đức - TP.HCM</p>
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