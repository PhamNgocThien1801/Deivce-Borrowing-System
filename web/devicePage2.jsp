<%@page import="duonght.dto.Account"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Devices Page</title>
        <link rel="stylesheet" href="css/style.css" />
        <style>
            @import url("https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap");
        </style>
        <link rel="stylesheet" href="fonts/themify-icons/themify-icons.css" />
        <link rel="icon" href="./img/word-image-1047.jpg" type="image/x-icon" />
        <link rel="stylesheet" href="node_modules/bootstrap/dist/css/bootstrap.min.css" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
              integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous" />
        <script src="node_modules/jquery/dist/jquery.slim.min.js"></script>
        <script src="node_modules/popper.js/dist/umd/popper.min.js"></script>
        <script src="node_modules/bootstrap/dist/js/bootstrap.min.js"></script>
        <style>
            .inputFilter select {
                width: 100%;
                height: 50px;
                margin-bottom: 10px;
            }
        </style>
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
        <c:set var="search" value="${requestScope.FILTER}"/>
        <c:set var="deviceListFilter" value="${requestScope.LIST_DEVICE_FILTER}"/>
        <c:set var="brandList" value="${sessionScope.LIST_BRAND}"/>
        <c:set var="categoryList" value="${sessionScope.LIST_CATEGORY}"/>
        <c:set var="warehouseList" value="${sessionScope.LIST_WAREHOUSE}"/>
        <c:set var="descriptionList" value="${sessionScope.LIST_DESCRIPTION}"/>
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
        <!-- search-button -->
        <div class="row manager-function d-flex align-items-center">
            <div class="col-sm-8 left-function text-center">
                <a href="#" id="chosefilter">
                    <button class="btn insertnew btn-color" type="button">
                        <i class="filer-icon fa fa-filter"></i>
                        <label class="button-insert">Filter</label>
                    </button>
                </a>
                <div id="FilterModal" class="modal fade" role="dialog">
                    <div class="modal-dialog modal-lg" role="content">
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title">Choose Filter</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
                                <form action="MainController" method="POST">
                                    <div class="container">
                                        <div class="row">
                                            <div class="col-4 inputFilter">
                                                <select name="filterBrand"  class="text-center">
                                                    <option selected value="">Choose Brand</option>
                                                    <c:forEach var="brand" items="${brandList}">
                                                        <option value="${brand.key}" >${brand.value}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-4 inputFilter">
                                                <select name="filterWarehouse"  class="text-center">
                                                    <option selected  value="">Choose Warehouse</option>
                                                    <c:forEach var="warehouse" items="${warehouseList}">
                                                        <option value="${warehouse.key}" >${warehouse.value}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-4 inputFilter">
                                                <select name="filterCategory" class="text-center">
                                                    <option selected  value="">Choose Category</option>
                                                    <c:forEach var="category" items="${categoryList}">
                                                        <option value="${category.key}">${category.value}</option>
                                                    </c:forEach>
                                                </select> 
                                            </div>
                                            <c:set var="detailName" value="detailName"/>
                                            <c:set var="desName" value="desName"/>
                                            <c:forEach var="description" items="${descriptionList}" varStatus="counter" >
                                                <div class="col-4 inputFilter">
                                                    <c:set var="desName" value='${desName}${counter.count}'/>
                                                    <c:set var="detailName" value='${detailName}${counter.count}'/>
                                                    <input type="hidden" name="${desName}" value="${description}"/>
                                                    <select name="${detailName}" class="text-center">   
                                                        <option selected  value="">Choose ${description}</option>
                                                        <c:forEach var="detail" items="${sessionScope[description]}">
                                                            <option value="${detail}">${detail}</option>
                                                        </c:forEach>
                                                    </select> </br>
                                                    <c:set var="detailName" value='detailName'/>
                                                    <c:set var="desName" value="desName"/>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>

                                    <div class="col-12 d-flex">
                                        <div class="col-6 mt-3">
                                            <button class="btn-filter btn btn-danger" data-dismiss="modal">Cancel</button>
                                        </div>
                                        <div class="col-6 mt-3">
                                            <button class="btn-filter btn btn-color" type="submit" value="FilterDevice" name="action">Search</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <button type="button" class="btn">
                    <div class="nav-item dropdown d-flex align-items-center">
                        <a class="nav-link dropdown-toggle d-flex align-items-center" href="#" id="navbarDropdownMenuLink"
                           role="button" data-toggle="dropdown">
                            <label class="button-insert">Brand</label>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <c:forEach var="brand" items="${brandList}">
                                <li>
                                    <a  class="dropdown-item" value="${brand.key}" href="MainController?filter=${brand.key}&action=HomeSearchDevice&value=${brand.value}">${brand.value}</a>
                                </li>
                            </c:forEach>
                            <li>
                                <a class="dropdown-item" href="MainController?filter=&action=HomeSearchDevice">All</a>
                            </li>
                        </ul>
                    </div>
                </button>
                <button type="button" class="btn">
                    <div class="nav-item dropdown d-flex align-items-center">
                        <a class="nav-link dropdown-toggle d-flex align-items-center" href="#" id="navbarDropdownMenuLink"
                           role="button" data-toggle="dropdown">
                            <label class="button-insert">Warehouse</label>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <c:forEach var="warehouse" items="${warehouseList}">
                                <li>
                                    <a  class="dropdown-item" value="${warehouse.key}" href="MainController?filter=${warehouse.key}&action=HomeSearchDevice&value=${warehouse.value}">${warehouse.value}</a>
                                </li>
                            </c:forEach>
                            <li>
                                <a class="dropdown-item" href="MainController?filter=&action=HomeSearchDevice">All</a>
                            </li>
                        </ul>
                    </div>
                </button>
                <button type="button" class="btn">
                    <div class="nav-item dropdown d-flex align-items-center">
                        <a class="nav-link dropdown-toggle d-flex align-items-center" href="#" id="navbarDropdownMenuLink"
                           role="button" data-toggle="dropdown">
                            <label class="button-insert">Category</label>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <c:forEach var="category" items="${categoryList}">
                                <li>
                                    <a  class="dropdown-item" value="${category.key}" href="MainController?filter=${category.key}&action=HomeSearchDevice&value=${category.value}">${category.value}</a>
                                </li>
                            </c:forEach>
                            <li>
                                <a class="dropdown-item" href="MainController?filter=&action=HomeSearchDevice">All</a>
                            </li>
                        </ul>
                    </div>
                </button>

            </div>
            <div class="col-sm-4 right-function">
                <div class="search">
                    <form action="" id="search-box">
                        <div class="row search-box-wrapper">
                            <form action="MainController" method="POST">
                                <input type="text" id="search-text" class="col-8" placeholder="Search by device name" name="search" value="${search}">
                                <button type="submit" name="action" value="HomeSearchDevice" id="search-btn" class="col-2"><i class="fas fa-search "></i></button>
                            </form>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-sm-12">
            <div class="table table-device">
                <table class="col-sm-12">
                    <thead>
                    <th class="text-center">#</th>
                    <th class="text-center">Device ID</th>
                    <th class="text-center">Image</th>
                    <th class="text-center">Device Name</th>
                    <th class="text-center">Warehouse</th>
                    <th class="text-center">Brand</th>
                    <th class="text-center">Quantity</th>
                    <th class="text-center">Deposit(VND)</th>
                    <th class="text-center">Detail</th>
                    <th class="text-center">Action</th>
                    </thead>
                    <c:if test="${empty requestScope.ERROR}">
                        <tbody>
                            <c:forEach var="device" items="${deviceListFilter}" varStatus="counter">
                                <tr>
                                    <td class="text-center">${counter.count}</td>
                                    <td class="text-center">${device.deviceID}</td>
                                    <td class="text-center"><img class="img-product" src="${device.url}" alt="no import image"/></td>
                                    <td class="text-center">${device.deviceName}</td>
                                    <td class="text-center">${device.warehouseName}</td>
                                    <td class="text-center">${device.brandName}</td>
                                    <td class="text-center">${device.quantity}</td>
                                    <td class="text-center">${device.deposit}</td>
                                    <td class="text-center"><a href="MainController?action=Detail&deviceID=${device.deviceID}&deviceName=${device.deviceName}&cateID=${device.cateID}&cateName=${device.cateName}&url=${device.url}&warehouseID=${device.warehouseID}&warehouseName=${device.warehouseName}&brandID=${device.brandID}&brandName=${device.brandName}&quantity=${device.quantity}&deposit=${device.deposit}" id="fa-info-circle"><i class="fas fa-info-circle"></i></a></td>
                                    <td class="text-center"><button type="submit">Add to cart</button></td>
                                </tr>
                            </c:forEach>    
                        </tbody>
                    </c:if>
                </table>
                <c:if test="${not empty requestScope.ERROR}">
                    <h2 class="nope">${requestScope.ERROR}</h2>
                </c:if>
            </div>

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
    <script>
        $(document).ready(function () {
            $("#chosefilter").click(function () {
                $("#FilterModal").modal("show");
            });
        });
    </script>
</body>
</html>