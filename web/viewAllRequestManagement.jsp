<%-- 
    Document   : viewAllRequestManagement
    Created on : Jun 28, 2022, 4:44:36 AM
    Author     : Admin
--%>
<%@page import="duonght.dto.Account"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
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
        <div class="col-sm-12 name-page text-center">
            <h2>
                Request Management
            </h2>
        </div>
        <div class="col-sm-12 staff-navbar justify-content-center text-center">
            <form action="MainController">
                <button class="btn btn-primary" type="submit" name="action" value="LoadAllRequestManager">Load All request</button></a>
                <button class="btn btn-secondary" type="submit" name="action" value="LoadProcessingRequest">Processing Request</button></a>
                <button class="btn btn-secondary" type="submit" name="action" value="LoadApprovedRequest">Approved Request</button></a>
                <button class="btn btn-secondary" type="submit" name="action" value="LoadSuccessRequest">Successful Request</button></a>
                <button class="btn btn-secondary" type="submit" name="action" value="LoadReturnedRequest">Returned Request</button></a>
                <button class="btn btn-secondary" type="submit" name="action" value="LoadCanceledRequest">Cancel Request</button></a>
            </form>
        </div>
        <div class="d-flex justify-content-center">
            <div class="nav-item dropdown">
                <a class="nav-link dropdown-toggle d-flex align-items-center  user-info" href="#"
                   id="navbarDropdownMenuLink" role="button" data-toggle="dropdown">
                    <p class="product-list">Search by UserID</p>
                </a>
                <form action="MainController" method="POST">
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li>
                            <input type="text" name="SearchByUserName" placeholder="input userID"/>
                            <button class="btn" type="submit" name="action" value="SearchRequestByUserInAllRequest"/>Search
                        </li>
                    </ul>
                </form>

            </div>
            <div class="nav-item dropdown">
                <a class="nav-link dropdown-toggle d-flex align-items-center  user-info" href="#"
                   id="navbarDropdownMenuLink" role="button" data-toggle="dropdown">
                    <p class="product-list">Search Between</p>
                </a>
                <form action="MainController" method="POST">
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li>
                            <input type="date" name="SearchByDate1" placeholder="input userID"/>
                            <input type="date" name="SearchByDate2" placeholder="input userID"/>
                            <button class="btn" type="submit" name="action" value="SearchRequestByDateInAllRequest"/>Search
                        </li>
                    </ul>
                </form>

            </div>
        </div>           
        <div class="table-wapper col-sm-12">
            <table class="table text-center">

                <thead>
                    <tr>
                        <th>Request ID</th>
                        <th>User ID </th>
                        <th>Username</th>
                        <th>Detail</th>
                        <th>Request Date</th>
                        <th>Substance</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="request" varStatus="counter" items="${requestScope.LIST_REQUEST}">
                        <tr>
                            <td>${request.id}
                            </td>
                            <td>${request.user.userID}</td>
                            <td>${request.user.userName}</td>
                            <td>
                                <c:set var="detail" value="${request.requestDetail}"/>
                                <a  id="fa-info-circle">
                                    <button class="btn" type="button" data-toggle="modal" data-target="#${detail}"><i class="fas fa-info-circle"></i></button></a>
                                <div id="${detail}" class="modal fade" role="dialog">
                                    <div class="modal-dialog modal-lg" role="content">
                                        <!-- Modal content-->
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h4 class="modal-title">Details</h4>
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            </div>

                                            <div class="modal-body">
                                                <div class="form-row">
                                                    <div class="form-group col-sm-12 d-flex">    
                                                        <label for="" class="col-sm-6 text-center">
                                                            <h5>Device name</h5>
                                                        </label>
                                                        <label class="col-sm-4 pt-1 pb-1"id="list-chose">
                                                            ${detail.device.deviceName}
                                                        </label>
                                                    </div>
                                                    </br>
                                                    <div class="form-group col-sm-12 d-flex">   
                                                        <label for="" class="col-sm-6 text-center">
                                                            <h5>Quantity</h5>
                                                        </label>
                                                        <label class="col-sm-4 pt-1 pb-1"id="list-chose">
                                                            ${detail.quantity}
                                                        </label>
                                                    </div>
                                                    </br>
                                                    <div class="form-group col-sm-12 d-flex">   
                                                        <label for="" class="col-sm-6 text-center">
                                                            <h5>Borrowed date</h5>
                                                        </label>
                                                        <label class="col-sm-4 pt-1 pb-1"id="list-chose">

                                                            <c:if test = "${detail.borrowDate != null}">
                                                                <c:out value = "${detail.borrowDate}"/>
                                                            </c:if>
                                                            <c:if test = "${detail.borrowDate == null}">
                                                                <c:out value = "Not approved"/>
                                                            </c:if>
                                                        </label></div></br>
                                                    <div class="form-group col-sm-12 d-flex">   
                                                        <label for="" class="col-sm-6 text-center">
                                                            <h5>Expired date</h5>
                                                        </label>
                                                        <label class="col-sm-4 pt-1 pb-1">

                                                            <c:if test = "${detail.expiredDate != null}">
                                                                <c:out value = "${detail.expiredDate}"/>
                                                            </c:if>
                                                            <c:if test = "${detail.expiredDate == null}">
                                                                <c:out value = "Not approved"/>
                                                            </c:if>
                                                        </label></div></br>
                                                    <div class="form-group col-sm-12 d-flex">   
                                                        <label for="" class="col-sm-6 text-center">
                                                            <h5>Detail status</h5>
                                                        </label>
                                                        <label class="col-sm-4 pt-1 pb-1">
                                                            ${detail.detailStatus}
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td>${request.requestDate}</td>
                            <td>${request.requestSubstance}</td>
                            <td>${request.requestStatus}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>


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
            $("#fa-info-circle").click(function () {
                $("#detailModal").modal("show");
            });
        });
        $(document).ready(function () {
            $("#extend").click(function () {
                $("#extendModal").modal("show");
            });
        });

        function reset() {
            document.getElementsByTagName('form')[0].reset();
        }
    </script>
</html>