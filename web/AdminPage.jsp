<%-- 
    Document   : AdminPage
    Created on : Jun 5, 2022, 10:44:11 AM
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
        <title>Admin Page</title>
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
        <!-- navbar -->
        <div class="navbar-top">
            <div class="navbar-header">
                <!-- logo -->
                <div class="col-sm-3 logo">
                    <a href="getAllAccount"><img src="./img/logo.png" height="80" alt=""></a>
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

            <!-- search -->
            <div class="seach-wapper">
                <div class="search-left col-sm-8 order-md-first">

                </div>
                <div class="search col-sm-4 order-md-last">
                    <form action="MainController" method="POST" id="search-box">
                        <div class="row search-box-wrapper">
                            <input type="text" name="searchID" value="${requestScope.searchID}" id="search-text" class="col-10" placeholder="Search">
                            <button id="search-btn" type="submit" name="action" value="SearchByID" class="col-2"><i class="fas fa-search "></i></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <c:set var="accounts" value="${requestScope.accounts}"></c:set>
        <c:if test="${empty accounts}">
            <h1>No Result</h1>
        </c:if>
        <c:if test="${not empty accounts}">
            <!-- table -->
            <div class="table-all">
                <div class="table-wapper col-sm-12">
                    <table class="table text-center">
                        <thead>
                            <tr>
                                <th>STT</th>
                                <th>Họ và Tên</th>
                                <th>ID</th>
                                <th>Phone</th>
                                <th>Email</th>
                                <th>Role</th>
                                <th>Position</th>
                                <th>Deposit</th>
                                <th>Status</th>
                                <th>Update button</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="count" value="1"></c:set>
                            <c:forEach var="acc" items="${accounts}">
                                <tr>
                                    <td>${count}</td>
                                    <td>${acc.userName}</td>
                                    <td>${acc.userID}</td>
                                    <td>${acc.phone}</td>
                                    <td>${acc.email}</td>
                                    <td>${acc.roleID}</td>
                                    <td>${acc.position}</td>
                                    <td>${acc.deposit}</td>
                                    <td class="${acc.status == 1 ? "text-success" : "text-danger"}">${acc.status == 1 ? "Acctive" : "InActive"}</td>
                                    <td>
                                        <form action="MainController" method="POST">
                                            <input value="${acc.userID}" name="userID" type="hidden"/>
                                            <input type="hidden" value="${acc.status}" name="status"/>
                                            <input type="hidden" value="${acc.email}" name="email" />
                                            <input type="hidden" value="UpdateAccountStatus" name="action"/>
                                            <c:if test="${acc.roleID != 'AD'}">
                                                <input type="submit" value="${acc.status == 1 ? "Block" : "UnBlock"}" class="${acc.status == 1 ? "btn btn-danger" : "btn btn-success"}"></input>
                                            </c:if>
                                            <c:if test="${acc.roleID == 'AD'}">
                                                <input type="button" value="See Only" class="btn btn-warning"></input>
                                            </c:if>
                                        </form>
                                    </td>
                                </tr>
                                <c:set var="count" value="${count + 1}"></c:set>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:if>

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
        <input type="hidden" value="${requestScope.MESSAGE}" id="MESSAGE"/>
        <script>
            var MESSAGE = document.getElementById("MESSAGE").value;
            if (MESSAGE !== "") {
                alert(MESSAGE);
            }
        </script>
    </body>
</html>
