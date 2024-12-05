<%-- 
    Document   : adminMenu
    Created on : 14 Jan 2024, 1:35:50 pm
    Author     : edrag
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin Login</title>
        <link rel="stylesheet" href="CSS/style.css">
        <style>
            #messageBox {
                background-color: #d4edda;
                color: #155724;
                padding: 20px;
                margin-bottom: 20px;
                border: 1px solid #c3e6cb;
                border-radius: 8px;
                display: none;
            }
        </style>
    </head>
    <body>
        <%
            // Check if the "sessionDestroyed" attribute is set and is true
            Boolean sessionDestroyed = (Boolean) request.getAttribute("sessionDestroyed");
            if (sessionDestroyed != null) {
        %>
        <!-- Show the message box if the session has been destroyed (logout) -->
        <div id="messageBox">
            Thank you for logging out. Have a great day!
        </div>
        <%
            } else if (sessionDestroyed == null) {
        %>
        <div id="messageBox">
            Please insert the right information. Have a great day!
        </div>
        <%
            }
        %>
        <h1>Security Guard Management System(SeGuMaS)</h1>
        <div class="container" id="container">
            <div class="form-container sign-in-container">
                <form action="loginServlet" method="GET">
                    <h1>Login</h1>
                    <input type="Username" placeholder="Username" name="username"/>
                    <input type="password" placeholder="Password" name="pass"/>
                    <button>LOGIN</button>
                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-right">
                        <h1 style="margin-top: 20%;">Hello, Welcome Back to SeGuMaS!</h1>
                        <a style="margin-bottom: 20%;" class="segu" href="#SEGUMAS"><img src="img/logo.png" alt="Trulli" width="100%" height="90%" ></a>

                    </div>
                </div>
            </div>
        </div>

        <footer>
            <p>
                Created with <i class="fa fa-heart"> Love </i> by <i class="fa fa-heart">Muhammad Irman Syakir Bin Ismail</i>
            </p>
        </footer>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            // Show the message box using jQuery
            $(document).ready(function () {
                var messageBox = $('#messageBox');
                messageBox.show('<%= sessionDestroyed != null && sessionDestroyed ? "block" : "none" %>');

                // Automatically hide the message box after 5 seconds
                setTimeout(function () {
                    messageBox.hide();
                }, 5000);
            });
        </script>
    </body>
</html>