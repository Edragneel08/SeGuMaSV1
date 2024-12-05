<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Side Navigation Bar</title>
        <style>
            body {
                font-family: Arial, sans-serif;
            }

            .sidenav {
                position: fixed;
                top: 0;
                left: 0;
                height: 100%;
                width: 100%;
                max-width: 100%;
                transform: translateX(-100%);
                transition: transform 0.3s ease-in-out;
                background-color: #333;
                color: white;
                padding: 20px;
            }

            .sidenav.open {
                transform: translateX(0);
            }

            .subnav {
                display: none;
            }

            .subnav-btn:hover + .subnav {
                display: block;
            }
        </style>
    </head>
    <body>
        <button onclick="openNav()">Open Navigation Bar</button>
        <div id="mySidenav" class="sidenav">
            <a href="#">Link 1</a>
            <a href="#">Link 2</a>
            <a href="#">Link 3</a>
            <button class="subnav-btn">Sub Navigation</button>
            <div class="subnav">
                <a href="#">Sub Link 1</a>
                <a href="#">Sub Link 2</a>
                <a href="#">Sub Link 3</a>
            </div>
        </div>
        <script src="js/navBar.js"></script>
    </body>
</html>