<%-- 
    Document   : viewListStaff
    Created on : 15 Jan 2024, 12:45:16 am
    Author     : edrag
--%>


<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="CSS/adminMenu.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="js/navBar.js"></script>
    </head>
    <body>
                <div class="sidebar">
            <a class="segu" href="#SEGUMAS"><img src="img/logo.png" alt="Trulli" width="100%" height="100%" ></a>
            <a  href="adminMenu.jsp">Home</a>
            <ul class="mainmenu">
                <li class="dropdown">
                    <a href="" class="dropdown-link">Manage HR Staff</a>
                    <ul class="submenu">
                        <li><a class="active" href="addNewHrStaff.jsp">Add New Staff</a></li>
                        <li><a href="<%=request.getContextPath()%>/listStaff">View List Staff</a></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-link">Manage Security Guard</a>
                    <ul class="submenu">
                        <li><a href="addGuard.jsp">Add New Guard</a></li>
                        <li><a href="<%=request.getContextPath()%>/listGuard">View List Guard</a></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-link">Manage Schedule</a>
                    <ul class="submenu">
                        <li><a href="">Create Schedule</a></li>
                        <li><a href="">View Schedule</a></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-link">Manage Leave</a>
                    <ul class="submenu">
                        <li><a href="">Create New Leave</a></li>
                        <li><a href="">View List</a></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-link">Manage Guard Salary</a>
                    <ul class="submenu">
                        <li><a href="">Calculate Salary</a></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-link">Manage Report</a>
                    <ul class="submenu">
                        <li><a href="">View Salary Report</a></li>
                        <li><a href="">View Schedule Report</a></li>
                        <li><a href="">View Leave Report</a></li>
                    </ul>
                </li>
            </ul>
        </div>

        <div class="content">
            <div class="container">

                <h3 class="text-center">List of Guard</h3>
                <hr>
                <div class="container text-left">

                    <a href="<%=request.getContextPath()%>/newGuard" class="btn btn-success">Add Guard</a>
                </div>
                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Guard ID</th>
                            <th>Guard Name</th>
                            <th>Age</th>
                            <th>Gender</th>
                            <th>Phone No</th>
                            <th>Salary</th>
                            <th>Site</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="guard" items="${listGuard}">
                            <tr>
                                <td>
                                    <c:out value="${guard.guardID}" />
                                </td>
                                <td>
                                    <c:out value="${guard.guardName}" />
                                </td>
                                <td>
                                    <c:out value="${guard.guardAge}" />
                                </td>
                                <td>
                                    <c:out value="${guard.guardGender}" />
                                </td>
                                <td>
                                    <c:out value="${guard.guardPhoneNo}" />
                                </td>
                                <td>
                                    <c:out value="${guard.guardSalary}" />
                                </td>
                                <td>
                                    <c:out value="${guard.siteID}" />
                                </td>
                                <td><a href="editGuard?guardID=<c:out value='${guard.guardID}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; 
                                    <a href="deleteGuard?guardID=<c:out value='${guard.guardID}' />">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>    
    </body>
</html>

