<%-- 
    Document   : user
    Created on : Jul 11, 2022, 5:24:06 PM
    Author     : Admin
--%>

<%@page import="duonght.dto.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>USER</title>
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
        <c:set var="categoryList" value="${sessionScope.LIST_CATEGORY}"/>
        <!-- nabar -->
        <!-- <div class="container"> -->
        <div class="row navbar">
            <!-- logo -->
            <div class="col-sm-4 navbar-user-left d-flex align-items-center">
                <div class="col-sm-5 logo">
                    <a href="MainController?action=LoadProcessRequest"><img src="./img/logo.png" height="80" alt="" /></a>
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
        <div class="row navbar-option col-sm-12 d-flex">
            <div class="col-sm-11">
                <div class="table table-user">
                    <table class="col-sm-12" id="myTable">
                        <thead>
                        <th class="text-center">Request ID</th>
                        <th class="text-center">Substance</th>
                        <th class="text-center">Request Date</th>
                        <th class="text-center">Action</th>
                        </thead>
                        <tbody>

                            <c:forEach var="request" varStatus="counter" items="${requestScope.LIST_PROCESSING_REQUEST_USER}">
                                <c:set var="detail" value="${request.requestDetail}"/>

                            <form action="MainController" method="POST">
                                <tr>
                                    <td class="text-center">${request.id}</td>
                                    <td class="text-center">${request.requestSubstance}</td>
                                    <td class="text-center">${request.requestDate}</td>
                                    <td class="text-center">
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
                                                                    <c:if test = "${detail.borrowDate != null}">
                                                                        <c:out value = "${detail.expiredDate}"/>
                                                                    </c:if>
                                                                    <c:if test = "${detail.borrowDate == null}">
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
                                                            </div></br>
                                                            <c:if test="${request.requestSubstance eq 'Extend Request'}">
                                                                <div class="form-group col-sm-12 d-flex">
                                                                    <label for="" class="col-sm-6 text-center">
                                                                        <h5>Reason</h5>
                                                                    </label>
                                                                    <label class="col-sm-4 pt-1 pb-1">
                                                                        <p>${request.extend.message}</p>
                                                                    </label>
                                                                </div>
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="navbar-col col-sm-1 order-first">
                <ul class="float-left">
                    <li>
                    </li>

                    <li>
                        <a href="MainController?action=LoadProcessRequest"><span class="indicator"></span><i class="fas-option active fas fa-sync-alt"><span
                                    class="navbaroption-tittle">Processing</span></i></a>
                    </li>
                    <li>
                        <a href="MainController?action=LoadApproveRequest"><i class="fas-option fas fa-check-circle"><span
                                    class="navbaroption-tittle">Approved</span></i></a>
                    </li>
                    <li>
                        <a href="MainController?action=LoadSuccessfulRequest"><i class="fas-option fas fa-undo-alt"><span
                                    class="navbaroption-tittle">Successful</span></i></a>
                    </li>
                    <li>
                        <a href="MainController?action=LoadReturnRequest"><i class="fas-option fas fa-sync-alt"><span
                                    class="navbaroption-tittle">Returned</span></i></a>
                    </li>
                    <li class="option-user-3">
                        <a href="MainController?action=LoadCancelUserRequest"><i class="fas-option fas fa-ban"><span
                                    class="navbaroption-tittle">Cancel</span></i></a>
                    </li>
                </ul>
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

    <script>
        function Delete() {
            $("#info").remove();
        }
    </script>

</html>