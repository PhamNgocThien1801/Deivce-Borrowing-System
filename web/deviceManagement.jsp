<%@page import="duonght.dto.Account"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Device Management</title>
        <link rel="stylesheet" href="css/style.css">
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap');
        </style>
        <link rel="stylesheet" href="fonts/themify-icons/themify-icons.css">
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
        <c:set var="brandListBasedOnCategory" value="${requestScope.LIST_BRAND_BASED_ON_CATEGORY}"/>
        <c:set var="search" value="${requestScope.SEARCH}"/>
        <c:set var="detailError" value="${requestScope.DETAIL_ERROR}"/>
        <c:set var="success" value="${requestScope.SUCCESS}"/>
        <c:set var="errorQuantity" value="${requestScope.ERROR_QUANTITY}"/>
        <c:set var="deviceList" value="${requestScope.LIST_DEVICE}"/>
        <c:forEach var="i" items="${deviceList}" varStatus="counter">
            <c:set var="descriptionList" value="${descriptionList}${counter.count}"/>
            <c:set var="descriptionList" value="${sessionScope.LIST_DESCRIPTION}"/>
            <c:set var="descriptionList" value="descriptionList"/>
        </c:forEach>
        <c:set var="brandList" value="${sessionScope.LIST_BRAND}"/>
        <c:set var="warehouseList" value="${sessionScope.LIST_WAREHOUSE}"/>
        <c:set var="warehouseListFilter" value="${sessionScope.LIST_WAREHOUSE}"/>

        <c:set var="categoryList" value="${sessionScope.LIST_CATEGORY}"/>
        <c:choose>
            <c:when test="${detailError.getDetailNameError() != null}" >
                <div id="detailError" class="modal fade" role="dialog">
                    <div class="modal-dialog modal-lg" role="content">
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title"><strong>${detailError.getDetailNameError()}</strong></h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:when test="${success != null}">
                <div id="success" class="modal fade" role="dialog">
                    <div class="modal-dialog modal-lg" role="content">
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title"><strong>${success}</strong></h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:when test="${errorQuantity != null}">
                <div id="success" class="modal fade" role="dialog">
                    <div class="modal-dialog modal-lg" role="content">
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title"><strong>${errorQuantity}</strong></h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:when>
        </c:choose>

        <div class="navbar-top">
            <div class="navbar-header">
                <!-- logo -->
                <div class="col-sm-3 logo">
                    <a href="#"><img src="./img/logo.png" height="80" alt=""></a>
                </div>
                <!-- name web -->
                <div class="col-sm-6 d-flex align-items-center justify-content-center text-center name-website">
                    <a href="#">DBS - FPT University HCM</a>
                </div>
                <!-- welcome -->
                <div class="col-sm-3 welcome d-flex align-items-center justify-content-end">
                    <div class="nav-item dropdown ">
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
        <div class="manage-navbar">
            <a href="MainController?search=&action=SearchDevice">
                <button class="btn btn-color btnoption" name="action" type="submit" value="SearchDevice">
                    Manage Devices
                </button>
            </a>
            <a href="MainController?action=GetListWarehouse">
                <button class="btn btn-secondary btnoption">
                    Manage Warehouse
                </button>
            </a>
            <a href="MainController?action=GetListCategory">
                <button class="btn btn-secondary btnoption">
                    Manage Category
                </button>
            </a>
        </div>
        <div class="row manager-function d-flex align-items-center">
            <div class="col-sm-6 left-function text-center">
                <a href="MainController?action=GetList" class="insert">
                    <button class="btn insertnew" type="button">
                        <i class="fas fa-plus-circle"></i>
                        <label class="button-insert">Insert New Devices</label>
                    </button>
                </a>
                <button type="button" class="btn">
                    <!-- <i class="fas fa-plus-circle"></i>
                    <label for="">insert new Device</label> -->
                    <div class="nav-item dropdown d-flex align-items-center">
                        <a class="nav-link dropdown-toggle d-flex align-items-center" href="#" id="navbarDropdownMenuLink"
                           role="button" data-toggle="dropdown">
                            <label class="button-insert">Filter</label>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <li>
                                <a class="dropdown-item" href="#">Brand</a>
                                <ul class="drop-submenu-1">
                                    <c:forEach var="brand" items="${brandList}">
                                        <li>
                                            <a value="${brand.key}" href="MainController?filter=${brand.key}&action=SearchDevice&value=${brand.value}">${brand.value}</a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </li>
                            <li>
                                <a class="dropdown-item" href="#">Category</a>
                                <ul class="drop-submenu-2">
                                    <c:forEach var="category" items="${categoryList}">
                                        <li>
                                            <a value="${category.key}" href="MainController?filter=${category.key}&action=SearchDevice&value=${category.value}">${category.value}</a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </li>
                            <li>
                                <a class="dropdown-item" href="#">Warehouse</a>
                                <ul class="drop-submenu-3">
                                    <c:forEach var="warehouse" items="${warehouseList}">
                                        <li>
                                            <a value="${warehouse.key}" href="MainController?filter=${warehouse.key}&action=SearchDevice&value=${warehouse.value}" >${warehouse.value}</a>
                                        </li>
                                    </c:forEach>
                                </ul>   
                            </li>
                            <li>
                                <a class="dropdown-item" href="MainController?search=&action=SearchDevice">All</a>
                            </li>
                        </ul>
                    </div>
                </button>
            </div>
            <div class="col-sm-6 right-function">
                <div class="search">
                    <form action="" id="search-box">
                        <div class="row search-box-wrapper">
                            <form action="MainController" method="POST">
                                <input type="text" id="search-text" class="col-8" placeholder="Search by device name" name="search" value="${search}">
                                <button type="submit" name="action" value="SearchDevice" id="search-btn" class="col-2"><i class="fas fa-search "></i></button>
                            </form>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="table-wapper col-sm-12">
            <table class="table text-center">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>DeviceID</th>
                        <th>Images</th>
                        <th id="device-nametable">Device Name</th>
                        <th>Brand</th>
                        <th>Quantity</th>
                        <th>Description</th>
                        <th>Category</th>
                        <th>Warehouse</th>
                        <th>Deposit (VND)</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>   
                ${sessionScope.ERROR}
                <c:if test="${not empty deviceList}">          
                    <tbody>
                        <c:set var="dl" value="${sessionScope.LIST_DETAIL}"/>
                        <c:forEach var="device" items="${deviceList}" varStatus="counter1">
                            <tr>
                        <form action="MainController"  method="POST">
                            <c:set var="modal" value="detailModal${counter1.count}"/>
                            <c:set var="descriptionList" value="${requestScope[device.cateID]}"/>
                            <td > <h5 class="center1">${counter1.count}</h5></td>
                            <td ><input type="text" name="deviceID" class="text-center inputmanager minimum" value="${device.deviceID}"  readonly></td>
                            <td >
                                <a href="MainController?action=OpenUpdateImgPage&deviceID=${device.deviceID}&url=${device.url}"><img class="img-product-manager" src="${device.url}" ></a>
                            </td>               
                            </td>
                            <td><input type="text" name="deviceName" class="text-center inputmanager" value="${device.deviceName}" id="input-devicename"></td>
                            <td>
                                <select name="brandID" id="fix_column" class="text-center inputmanager pt-1 pb-1 pl-2 pr-4">
                                    <c:forEach var="brand" items="${brandListBasedOnCategory[counter1.count-1]}">
                                        <c:choose>
                                            <c:when test="${brand.key.equals(device.brandID)}">
                                                <option value="${brand.key}" selected>${brand.value}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${brand.key}" >${brand.value}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select> </br>    
                            </td>
                            <td><input type="number" name="quantity" min="0" class="text-center inputmanager minimum" value="${device.quantity}"></td>
                            <td><a  id="fa-info-circle">
                                    <button class="btn center2" type="button" data-toggle="modal" data-target="#${modal}"><i class="fas fa-info-circle"></i></button></a>
                                <div id="${modal}" class="modal fade" role="dialog">
                                    <div class="modal-dialog modal-lg" role="content">
                                        <!-- Modal content-->
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h4 class="modal-title">Details modal</h4>
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            </div>

                                            <div class="modal-body">
                                                <div class="form-row">
                                                    <c:forEach var="description" items="${descriptionList}" varStatus="counter2" >
                                                        <div class="form-group col-sm-12 d-flex">    
                                                            <label for="" class="col-sm-6 text-center">
                                                                <h5>${description.descriptionName}</h5>
                                                            </label>
                                                            <c:set var="detailID" value='detailID${counter2.count}'/>                                 
                                                            <select name="${detailID}" class="col-sm-4 pt-1 pb-1"  id="list-chose">   
                                                                <c:forEach var="detail" items="${requestScope[description.descriptionName]}">
                                                                    <c:choose>
                                                                        <c:when test="${dl[counter1.count - 1][counter2.count-1].value.equals(detail.getDetailName())}">
                                                                            <option value="${detail.getDetailID()}" selected>${detail.getDetailName()}</option>
                                                                            <c:set var="currentDetailID" value="${detail.getDetailID()}"/>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <option value="${detail.getDetailID()}">${detail.getDetailName()}</option>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </c:forEach>
                                                            </select> </br>
                                                            <input type="hidden" name="currentDetailID${counter2.count}" value="${currentDetailID}">
                                                            <c:set var="detailID" value='detailID'/>
                                                        </div>
                                                    </c:forEach> 
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <input type="button" type="text" id="fix_column"class="text-center inputmanager categoryButton" onclick="location.href = 'MainController?action=UpdateDeviceCategory&cateID=${device.cateID}&deviceID=${device.deviceID}'"
                                       <c:forEach var="category" items="${categoryList}">
                                           <c:if test="${category.key.equals(device.cateID)}">                                 
                                               value="${category.value}" 
                                           </c:if>
                                       </c:forEach>
                                       />
                            </td>
                            <input name="cateName" type="hidden" 
                                   <c:forEach var="category" items="${categoryList}">
                                       <c:if test="${category.key.equals(device.cateID)}">                                 
                                           value="${category.value}"
                                       </c:if>
                                   </c:forEach>
                                   />
                            <td>
                                <select name="warehouseID" id="fix_column" class="text-center inputmanager">
                                    <c:forEach var="warehouse" items="${warehouseList}">
                                        <c:choose>
                                            <c:when test="${warehouse.key.equals(device.warehouseID)}">
                                                <option value="${warehouse.key}" selected>${warehouse.value}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${warehouse.key}">${warehouse.value}</option>
                                            </c:otherwise>
                                        </c:choose>       
                                    </c:forEach>
                                </select>
                            </td>
                            <input type="hidden" name="currentQuantity" value="${device.quantity}"/>
                            <td ><input type="number" name="deposit" step="1000" min="1000" class="text-center inputmanager medium" value="${device.deposit}" ></td>
                            <td><button type="submit"  name="action" value="DeleteDevice" onclick="return deleteConfirm()"  class="btn btn-danger center2"><i class="fas fa-trash-alt"></i></button></td>
                            <td><button type="submit" name="action" value="UpdateDevice"  class="btn btn-success center2"><i class="fas fa-recycle"></i></button></td>
                        </form>
                        </tr>
                        <c:set var="modal" value="detailModal"/>
                    </c:forEach>
                    </tbody>
                </c:if>
            </table>
            <c:if test="${empty deviceList}">
                <h2 class="nope">No result</h2>
            </c:if>
        </div>

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
    <script>
        $(document).ready(function () {
            $("#detailError").modal("show");
            $("#success").modal("show");
            $("#errorQuantity").modal("show");

        });
        function chooseFile(fileInput, c) {
            if (fileInput.files && fileInput.files[0]) {
                var reader = new FileReader();
                console.log(c);
                reader.onload = function (e) {
                    $('#' + c).attr('src', e.target.result);
                };
                reader.readAsDataURL(fileInput.files[0]);
            }
        }
        function deleteConfirm() {
            if (confirm("Are You Sure To Delete?"))
                return true;
            return false;
        }
    </script>

</html>