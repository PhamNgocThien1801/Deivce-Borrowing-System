<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>LOGIN</title>
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
        <!-- navbarlogin -->
        <!--        <div class="col-sm-12 navbarlogin d-flex">
                    <img width="10%" src="img/logo.png" alt="">
                    <h3><a class="ml-4" href="#">DBS - FPT University HCM</a></h3>
                </div>-->
        <!-- login-form -->
        <div class="col-sm-12 loggin-wapper p-0 d-flex">
            <div class="col-sm-8 p-0">
                <img width="100%" class="img-login" src="img/theme.webp" alt="alo">
            </div>
            <div class="col-sm-4 text-center justify-content-center align-items-center pt-5">
                <img width="100%" src="img/logo.png" alt="">
                <a class="col-sm-8 btn btn-lg btn-google btn-block text-uppercase btn-outline border rounded mr-auto ml-auto bg-primary text-light"
                   href="https://accounts.google.com/o/oauth2/auth?scope=email profile&redirect_uri=http://localhost:8084/DeviceManagement/LoginHandler&response_type=code
                   &client_id=33568893407-i7p94f2ca7var420dpis79903h4o46ut.apps.googleusercontent.com&approval_prompt=force"> 
                    <i class="btn btn-social-icon btn-google"></i> Login With Google</a>
            </div>
        </div>
    </body>

</html>