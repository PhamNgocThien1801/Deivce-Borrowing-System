<%-- 
    Document   : categoryManagement
    Created on : Jun 19, 2022, 9:17:41 PM
    Author     : Trung Duong
--%>

<%@page import="duonght.dto.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Category Management</title>
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
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                <button class="btn btn-secondary btnoption" name="action" type="submit" value="SearchDevice">
                    Manage Devices
                </button>
            </a>
            <a href="MainController?action=GetListWarehouse">
                <button class="btn btn-secondary btnoption">
                    Manage Warehouse
                </button>
            </a>
            <a href="MainController?action=GetListCategory">
                <button class="btn btn-color btnoption">
                    Manage Category
                </button>
            </a>
        </div>
        <div class="row manager-function d-flex align-items-center">
            <div class="col-sm-6 left-function text-center">
                <a href="#" id="insert-new-catagory" class="insert">
                    <button class="btn insertnew" type="button">
                        <i class="fas fa-plus-circle"></i>
                        <label class="button-insert">Insert New Category</label>
                    </button>
                </a>
                <div id="detailModal" class="modal fade" role="dialog">
                    <div class="modal-dialog modal-lg" role="content">
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title">Insert New Category</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
                                <form action="MainController" method="POST">
                                    <div class="form-row">
                                        <div class="form-group col-sm-12 d-flex">
                                            <h5 class="col-sm-4">Cate ID</h5>
                                            <input class="col-sm-6" type="text" required="" name="textCateID" placeholder="Input new Cate ID">
                                        </div>
                                        <div class="form-group col-sm-12 d-flex">
                                            <h5 class="col-sm-4">Cate Name</h5>
                                            <input class="col-sm-6" type="text" required="" name="textCateName" placeholder="Input new Cate Name">
                                        </div>
                                        <div class="form-group col-sm-12 d-flex">
                                            <h5 class="col-sm-4">Description</h5>
                                            <input class="col-sm-6" type="text" required="" name="textDescription" placeholder="Input One Description Of Cate">
                                        </div>
                                        <div class="form-group col-sm-12 d-flex">
                                            <h5 class="col-sm-4">Brand Name</h5>
                                            <input class="col-sm-6" type="text" required="" name="textBrandName" placeholder="Input One Brand Of Cate">
                                        </div>
                                        <div class="col-sm-12">
                                            <button class="btn btn-success col-sm-4" type="submit" name="action" value="CreateCategory"> 
                                                Insert                      
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 right-function">
                <div class="search">
                    <form action="MainController" method="POST" id="search-box">
                        <div class="row search-box-wrapper">
                            <input type="text" name="textSearch" value="${requestScope.textSearch}" id="search-text" class="col-8" placeholder="Search">
                            <button type="submit" name="action" value="SearchCategory" id="search-btn" class="col-2"><i class="fas fa-search "></i></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <c:set var="categories" value="${requestScope.categories}"/>
        <c:if test="${empty categories}">
            <h1>No Result!</h1>
        </c:if>
        <c:if test="${not empty categories}">
            <div class="table-wapper col-sm-12">
                <table class="table table-all text-center">
                    <thead>
                        <tr>
                            <th>STT</th>
                            <th>Cate ID</th>
                            <th>Cate Name</th>
                            <th>Update</th>
                            <th>Delete</th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="cate" items="${categories}" varStatus="counter">
                            <c:set var="newDescription" value="newdescription${counter.count}" />
                            <c:set var="newBrand" value="newBrand${counter.count}" />
                            <tr>
                        <form action="MainController" method="POST">
                            <td><input type="text" readonly="" class="text-center" value="${counter.count}"></td>
                            <td><input type="text" readonly="" class="text-center" name="textCateID" value="${cate.cateID}"></td>
                            <td><input type="text" class="text-center" name="textCateName" value="${cate.cateName}"></td>
                            <td><button type="submit" name="action" value="UpdateCategory" class="btn btn-success"><i class="fas fa-recycle"></i></button></td>
                            <td><button type="submit" name="action" value="DeleteCategory" onclick="return deleteConfirm()" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button></td>
                        </form>
                        <td>
                            <a id="insert-new-description">
                                <button class="btn " type="button" data-toggle="modal" data-target="#${newDescription}"><i class="fas fa-plus-circle"> Insert New Description</i></button>
                            </a>
                            <div id="${newDescription}" class="modal fade" role="dialog">
                                <div class="modal-dialog modal-lg" role="content">
                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h4 class="modal-title">Insert New Description</h4>
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        </div>
                                        <div class="modal-body">
                                            <form action="MainController" method="POST">
                                                <div class="form-row">
                                                    <div class="form-group col-sm-12 d-flex">
                                                        <h5 class="col-sm-4">Description Name</h5>
                                                        <input class="col-sm-6" type="text" name="newDescriptionName" required="" placeholder="Input new Description Name">
                                                        <input type="hidden" name="textCateID" value="${cate.cateID}">
                                                    </div>
                                                    <div class="col-sm-12">
                                                        <button class="btn btn-success col-sm-4" type="submit" name="action" value="CreateDescription"> 
                                                            Insert                      
                                                        </button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                        <td>
                            <a id="insert-new-description">
                                <button class="btn " type="button" data-toggle="modal" data-target="#${newBrand}"><i class="fas fa-plus-circle"> Insert New Brand</i></button>
                            </a>
                            <div id="${newBrand}" class="modal fade" role="dialog">
                                <div class="modal-dialog modal-lg" role="content">
                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h4 class="modal-title">Insert New Brand</h4>
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        </div>
                                        <div class="modal-body">
                                            <form action="MainController" method="POST">
                                                <div class="form-row">
                                                    <div class="form-group col-sm-12 d-flex">
                                                        <h5 class="col-sm-4">Brand Name</h5>
                                                        <input class="col-sm-6" type="text" name="textBrand" required="" placeholder="Input new Brand Name">
                                                        <input type="hidden" name="textCateID" value="${cate.cateID}">
                                                    </div>
                                                    <div class="col-sm-12">
                                                        <button class="btn btn-success col-sm-4" type="submit" name="action" value="CreateBrand"> 
                                                            Insert                      
                                                        </button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                        <td>
                            <c:set var="desDetail" value="desDetail${counter.count}" />
                            <a id="insert-new-detail"><button class="btn btn-light" id="dropdown-firsttable" data-toggle="modal" data-target="#${desDetail}">
                                    <i class="fas fa-info-circle"></i>
                                </button></a>
                            <div id="${desDetail}" class="modal fade" role="dialog">
                                <div class="modal-dialog modal-lg" role="content">
                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        </div>
                                        <div class="modal-body">
                                            <form action="MainController" method="POST">
                                                <div class="form-row">
                                                    <c:set var="descrip" value="des${cate.cateID}"/>
                                                    <c:set var="descriptions" value="${requestScope[descrip]}" />
                                                    <c:if test="${empty descriptions}">
                                                        <h4>This Category Have No Description.</h4>
                                                        <h4>Please, Input Some Description For It!</h4>
                                                    </c:if>
                                                    <c:if test="${not empty descriptions}">
                                                        <table class="table">
                                                            <thead>
                                                                <tr>
                                                                    <th>Description</th>
                                                                    <th>Detail</th>
                                                                    <th>New Detail</th>
                                                                    <th>Update</th>
                                                                    <th>Delete</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <c:forEach var="description" items="${descriptions}">
                                                                    <tr>
                                                                <form action="MainController" method="POST">
                                                                    <td><p class="mt-2">${description.descriptionName}</p></td>
                                                                    <td>
                                                                        <c:set var="pattern" value="${description.descriptionID}${description.descriptionName}" />
                                                                        <c:set var="details" value="${requestScope[pattern]}"/>
                                                                        <c:if test="${empty details}">
                                                                            <h6 class="text-danger ">Empty Description Detail. 
                                                                                <br/>Please, Insert Some One!</h6>
                                                                            </c:if>
                                                                            <c:if test="${not empty details}">
                                                                            <select class="col-sm-12 mt-2 pt-1 pb-1" name="detailName">
                                                                                <option selected disabled>Choose Details</option>
                                                                                <c:forEach var="detail" items="${details}">
                                                                                    <option>${detail.detailName}</option>
                                                                                </c:forEach>
                                                                            </select>
                                                                        </c:if>
                                                                    </td>
                                                                    <td>
                                                                        <input class="col-sm-8 mt-2" id="input-insertdetail" type="text" name="textDetail" value="" placeholder="Insert/Update">
                                                                        <input type="hidden" name="descriptionID" value="${description.descriptionID}">
                                                                        <button type="submit" name="action" value="CreateDetail" class="btn btn-success" id="btn-insertdetail">Insert</button>
                                                                    </td>
                                                                    <td>
                                                                        <a href="#">
                                                                            <button class="btn btn-success" id="btn-check" type="submit" name="action" value="UpdateDetail">
                                                                                <i class="fas fa-recycle"></i>
                                                                            </button>
                                                                        </a>
                                                                    </td>
                                                                    <td>
                                                                        <input type="hidden" name="descriptionName" value="${description.descriptionID}">
                                                                        <input type="hidden" name="textCateID" value="${cate.cateID}">
                                                                        <a href="#" >
                                                                            <button class="btn btn-danger" id="btn-check" onclick="return deleteConfirm()" type="submit" name="action" value="${empty details ? "DeleteDescription" : "DeleteDetail"}">
                                                                                <i class="fas fa-trash-alt"></i>
                                                                            </button>
                                                                        </a>
                                                                    </td>
                                                                </form>
                                                                </tr>
                                                            </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </c:if>
                                                </div>
                                                <div class="form-row">
                                                    <c:set var="brand" value="brand${cate.cateID}"/>
                                                    <c:set var="brands" value="${requestScope[brand]}" />
                                                    <c:if test="${empty brands}">
                                                        <h4>This Category Have No Brand.</h4>
                                                        <h4>Please, Input Some Brands For It!</h4>
                                                    </c:if>
                                                    <c:if test="${not empty brands}">
                                                        <table class="table">
                                                            <thead>
                                                                <tr>
                                                                    <th>Description</th>
                                                                    <th>Brand Name</th>
                                                                    <th>New Brand</th>
                                                                    <th>Update</th>
                                                                    <th>Delete</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                            <form action="MainController" method="POST">
                                                                <td><p class="mt-2">Brand</p></td>
                                                                <td>
                                                                    <select class="col-sm-12 mt-2 pt-1 pb-1" name="brandName">
                                                                        <option selected disabled>Choose Brand</option>
                                                                        <c:forEach var="brand" items="${brands}">
                                                                            <option>${brand.brandName}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </td>
                                                                <td>
                                                                    <input class="col-sm-8 mt-2" id="input-insertdetail" type="text" name="textBrand" value="" placeholder="Insert/Update">
                                                                    <input type="hidden" name="textCateID" value="${cate.cateID}">
                                                                    <button type="submit" name="action" value="CreateBrand" class="btn btn-success" id="btn-insertdetail">Insert</button>
                                                                </td>
                                                                <td>
                                                                    <a href="#">
                                                                        <button class="btn btn-success" id="btn-check" type="submit" name="action" value="UpdateBrand">
                                                                            <i class="fas fa-recycle"></i>
                                                                        </button>
                                                                    </a>
                                                                </td>
                                                                <td>
                                                                    <a href="#" >
                                                                        <button class="btn btn-danger" id="btn-check" onclick="return deleteConfirm()" type="submit" name="action" value="DeleteBrand">
                                                                            <i class="fas fa-trash-alt"></i>
                                                                        </button>
                                                                    </a>
                                                                </td>
                                                            </form>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </c:if>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
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
            $("#insert-new-catagory").click(function () {
                $("#detailModal").modal("show");
            });

            $("#insert-new-description").click(function () {
                $("#detailModal-description").modal("show");
            });

            $("#insert-new-detail").click(function () {
                $("#detailModal-detail").modal("show");
            });
        });
        function deleteConfirm() {
            if (confirm("Are you sure to Delete?")) {
                return true;
            }
            return false;
        }
    </script>
    <c:set var="CateError" value="${requestScope.CateError}"/>
    <c:if test="${CateError.cateIDError != null}">
        <input type="hidden" value="${CateError.cateIDError}" id="cateIDError"/>
    </c:if>
    <c:if test="${CateError.cateNameError != null}">
        <input type="hidden" value="${CateError.cateNameError}" id="cateNameError"/>
    </c:if>
    <script>
        var cateIDError = document.getElementById("cateIDError").value
        var cateNameError = document.getElementById("cateNameError").value
        alert(cateIDError + "\n" + cateNameError);
    </script>
    <input type="hidden" value="${requestScope.MESSAGE}" id="MESSAGE" />
    <script>
        var MESSAGE = document.getElementById("MESSAGE").value;
        if (MESSAGE !== "") {
            alert(MESSAGE);
        }
    </script>
    <script>
        function deleteConfirm() {
            if (confirm("Are You Sure To Delete?"))
                return true;
            return false;
        }
    </script>
</html>
