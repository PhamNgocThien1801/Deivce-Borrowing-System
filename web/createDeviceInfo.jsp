<%@page import="duonght.dto.Account"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Insert Device Info Page</title>
        <link rel="stylesheet" href="css/style.css">
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap');
        </style>
        <link rel="stylesheet" href="node_modules/bootstrap/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
              integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
        <script src="node_modules/jquery/dist/jquery.slim.min.js"></script>
        <script src="node_modules/popper.js/dist/umd/popper.min.js"></script>
        <script src="node_modules/bootstrap/dist/js/bootstrap.min.js"></script>
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
        <c:set var="deviceName" value="${sessionScope.DEVICE_NAME}"/>
        <c:set var="cateName" value="${sessionScope.CATE_NAME}"/>
        <c:set var="warehouseName" value="${sessionScope.WAREHOUSE_NAME}"/>
        <c:set var="descriptionList" value="${sessionScope.DESCRIPTION_LIST}"/>
        <c:set var="brandList" value="${sessionScope.BRAND_LIST}"/>
        <c:set var="deviceError" value="${requestScope.DEVICE_ERROR}"/>
        <c:set var="detailError" value="${requestScope.DETAIL_ERROR}"/>
        <c:set var="categoryList" value="${sessionScope.LIST_CATEGORY}"/>
        <c:set var="warehouseList" value="${sessionScope.LIST_WAREHOUSE}"/>
        <c:set var="deviceError" value="${requestScope.DEVICE_ERROR}"/>
        <c:set var="brandListFilter" value="${sessionScope.LIST_BRAND}"/>


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
                    Manage Catagory
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
                                    <c:forEach var="brand" items="${brandListFilter}">
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
        <div class="row">
            <div class="tittle-add-devices col-sm-12 text-center mt-2 mb-2">
                <h2>Insert Device Information</h2>
            </div>
            <div class="col-sm-12">
                <form action="CreateDeviceController" method="POST" enctype="multipart/form-data">
                    <div class="choose-manager-tittle col-sm-12 text-center mt-3 ml-5 mb-3">
                        <label for="#" class="col-sm-2">
                            <h4>Device name</h4>
                        </label>
                        <input type="text" name="deviceName" maxLength="50" value="${deviceName}" class="col-sm-4"/>
                    </div>
                    <div class="choose-manager-tittle col-sm-12 text-center ml-5 mb-3">
                        <label for="#" class="col-sm-2">
                            <h4>Category name</h4>
                        </label>
                        <input type="text" name="cateName"  value="${cateName}" class="col-sm-4" readonly>
                    </div>
                    <div class="choose-manager-tittle col-sm-12 text-center mt-3 ml-5 mb-3">
                        <label for="#" class="col-sm-2">
                            <h4>Warehouse name</h4>
                        </label>
                        <input type="text" name="warehouseName" value="${warehouseName}" class="col-sm-4" readonly>
                    </div>
                    <div class="choose-manager-tittle col-sm-12 text-center mt-3 ml-5 mb-3">
                        <label for="#" class="col-sm-2">
                            <h4>Brand Name</h4>
                        </label>
                        <c:set var="detailID" value="detailID"/>
                        <select name="brandID" class="col-sm-4 pt-1 pb-1"  id="list-chose" required>
                            <option selected disabled value="">Choose Brand</option>
                            <c:forEach var="brand" items="${brandList}">
                                <option value="${brand.key}">${brand.value}</option>
                            </c:forEach>
                        </select> </br>               
                    </div>
                    <div class="choose-manager-tittle col-sm-12 text-center mt-3 ml-5 mb-3">
                        <label for="#" class="col-sm-2">
                            <h4>Quantity</h4>
                        </label>
                        <input type="number" name="quantity" required="" min="0" placeholder="Input Quantity" id="input-device" class="col-sm-4"/>
                    </div>
                    <div class="choose-manager-tittle col-sm-12 text-center mt-3 ml-5 mb-3">
                        <label for="#" class="col-sm-2">
                            <h4>Deposit (VND)</h4>
                        </label>
                        <input type="number" name="deposit" required="" min="1000" step="1000" placeholder="Input Deposit" id="input-device" class="col-sm-4"/>
                    </div>
                    <c:forEach var="description" items="${descriptionList}" varStatus="counter" >
                        <div class="choose-manager-tittle col-sm-12 text-center mt-3 ml-5 mb-3">
                            <label for="#" class="col-sm-2">
                                <h4>${description.descriptionName}</h4>
                            </label>
                            <c:set var="detailID" value='${detailID}${counter.count}'/>
                            <select name="${detailID}" class="col-sm-4 pt-1 pb-1"  id="list-chose" required>   
                                <option selected disabled value="">Choose ${description.descriptionName}</option>
                                <c:forEach var="detail" items="${sessionScope[description.descriptionName]}">
                                    <option value="${detail.getDetailID()}">${detail.getDetailName()}</option>
                                </c:forEach>
                            </select> </br>
                            <c:set var="detailID" value='detailID'/>
                        </div>
                    </c:forEach>
                    <div class="choose-manager-tittle col-sm-12 text-center mt-3 ml-5 mb-5">
                        <label for="#" class="col-sm-2">
                            <h4>Choose File</h4>
                        </label>
                        <img class="product-img-thumb" id="img-thumb" height="100px" width="100px" src="" alt="" >
                        <input type="file"  name="image" required="" onchange="readURL(this);">
                    </div>  
                    <div class="col-sm-12 d-flex justify-content-center">
                        <a href="" class="col-sm-5">
                            <button type="submit" name="action" value="CreateDevice" class="btn btn-color col-sm-4">Insert</button>
                        </a>
                    </div>
                </form>
                <c:set var="error" value="${requestScope.ERROR}"/>
                <c:if test="${error != null}">
                    <div id="success" class="modal fade" role="dialog">
                        <div class="modal-dialog modal-lg" role="content">
                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4 class="modal-title"><strong>${error}</strong></h4>
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
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
<footer></footer>
<script>
    $(document).ready(function () {
        $("#success").modal("show");
    });
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                document.getElementById('img-thumb').src = e.target.result;
            };
            reader.readAsDataURL(input.files[0]);
        }
    }
</script>

</html>