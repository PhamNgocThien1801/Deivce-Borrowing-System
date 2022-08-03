<%@page import="duonght.dto.Account"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Warehouse Management</title>
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
                <button class="btn btn-color btnoption">
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
                <a href="#" id="insert-new-warehouse">
                    <button class="btn " type="button">
                        <i class="fas fa-plus-circle"></i>
                        <label class="button-insert">Insert New WareHouse</label>
                    </button>
                </a>
                <div id="detailModal" class="modal fade" role="dialog">
                    <div class="modal-dialog modal-lg" role="content">
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title">Insert New WareHouse</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
                                <form method="POST" action="MainController">
                                    <div class="form-row">
                                        <div class="form-group col-sm-12 d-flex">
                                            <h5 class="col-sm-4">WareHouse Name</h5>
                                            <input class="col-sm-6" type="text" name="warehouseName" value="" placeholder="Input new WareHouse Name">
                                        </div>
                                        <div class="form-group col-sm-12 d-flex">
                                            <h5 class="col-sm-4">Location</h5>
                                            <input class="col-sm-6" type="text" name="location" value="" placeholder="Input new Location">
                                        </div>
                                        <div class="form-group col-sm-12 d-flex">
                                            <h5 class="col-sm-4">Limit Amount</h5>
                                            <input class="col-sm-6" type="text" name="limitAmout" value="" placeholder="Input Limit Amount">
                                        </div>
                                        <div class="col-sm-12">
                                            <button class="btn btn-success col-sm-4" type="submit" name="action" value="createWarehouse"> 
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
                            <input type="text" value="" name="searchWarehouse" id="search-text" class="col-8" placeholder="Search">
                            <button type="submit" name="action" value="SearchWarehouse" id="search-btn" class="col-2"><i class="fas fa-search "></i></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <c:set var="warehouses" value="${requestScope.warehouses}" />
        <c:if test="${empty warehouses}">
            <h1>Have No Warehouse</h1>
        </c:if>
        <c:if test="${not empty warehouses}">
            <div class="table-wapper col-sm-12">
                <table class="table text-center">
                    <thead>
                        <tr>
                            <th>WareHouseID</th>
                            <th>WareHouse Name</th>
                            <th>Location</th>
                            <th>Limit Amount</th>
                            <th>Status</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="warehouse" items="${warehouses}">
                            <tr>
                        <form method="POST" action="MainController">
                            <td><input type="text" readonly="" name="warehouseID" class="text-center" value="${warehouse.warehouseID}"></td>
                            <td><input type="text" class="text-center" name="warehouseName" value="${warehouse.warehouseName}"></td>
                            <td><input type="text" class="text-center" name="location" value="${warehouse.location}"></td>
                            <td><input type="text" class="text-center" name="limitAmount" value="${warehouse.limitAmount}"></td>
                            <td><input type="text" readonly="" class="text-center" name="status" value="${warehouse.status ? "Active" : "InActive"}"></td>
                            <td><button type="submit" name="action" value="UpdateWarehouse" class="btn btn-success" ><i class="fas fa-recycle"></i></button></td> 
                            <td><button type="submit" name="action" value="DeleteWarehouse" onclick="return confirmDelete()" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button></td>
                        </form>
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
        <input type="hidden" value="${requestScope.MESSAGE}" id="MESSAGE"/>
        <script>
            var MESSAGE = document.getElementById("MESSAGE").value;
            if (MESSAGE !== "") {
                alert(MESSAGE);
            }
        </script>
        <script>
            function confirmDelete() {
                if (confirm("Are you sure to Delete?")) {
                    return true;
                }
                return false;
            }
        </script>
        <script>
            $(document).ready(function () {
                $("#insert-new-warehouse").click(function () {
                    $("#detailModal").modal("show");
                });
            })
        </script>
    </body>

</html>