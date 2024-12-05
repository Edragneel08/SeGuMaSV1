<%-- 
    Document   : createSchedule
    Created on : 20 Jan 2024, 5:26:31 pm
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
        <script src="js/navBar.js"></script>

        <!--search scripts-->
        <script>
            function performSearch() {
                var input, filter, table, tr, td, i, j, txtValue;
                input = document.getElementById("search");
                filter = input.value.toUpperCase();
                table = document.getElementById("security-guard-table");
                tr = table.getElementsByTagName("tr");

                for (i = 1; i < tr.length; i++) { // Start from index 1 to skip the header row
                    // Loop through all columns in the current row
                    var matchFound = false;
                    for (j = 0; j < tr[i].cells.length; j++) {
                        td = tr[i].cells[j];
                        if (td) {
                            txtValue = td.textContent || td.innerText;
                            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                                matchFound = true;
                                break; // Break if a match is found in any column
                            }
                        }
                    }

                    // Show/hide the row based on whether a match is found
                    if (matchFound) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        </script>


    </head>
    <body>
        <div class="sidebar">
            <a class="segu" href="#SEGUMAS"><img src="img/logo.png" alt="Trulli" width="100%" height="100%" ></a>
            <a  href="<%=request.getContextPath()%>/listGuardMenu" class="active">Home</a>
            <ul class="mainmenu">
                <li class="dropdown">
                    <a href="" class="dropdown-link">Manage HR Staff</a>
                    <ul class="submenu">
                        <li><a href="addNewHrStaff.jsp">Add New Staff</a></li>
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
                        <li><a href="createSchedule.jsp">Create Schedule</a></li>
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
            <a href="${pageContext.request.contextPath}/logout">LOGOUT</a>
        </div>

        <div class="content">


            <div class="mycontainer4">
                <div style="background-color:#A7C7E7;">
                    <h2>Add Guard to Schedule</h2>
                    <form action="addToSchedule" method="post">
                        <table>
                            <tr>
                                <td>
                                    <label for="shiftDuration">Duration :</label>
                                    <input type="text" id="shiftDuration" name="shiftDuration" required>

                                    <label for="shiftDate">Date :</label>
                                    <input type="date" id="shiftDate" name="shiftDate" required>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="shiftStartTime">Start Time :</label>
                                    <input type="text" id="shiftStartTime" name="shiftStartTime" required>

                                    <label for="shiftEndTime">End Time :</label>
                                    <input type="text" id="shiftEndTime" name="shiftEndTime" required>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="branchID">Branch ID:</label>
                                    <input type="text" id="branchID" name="branchID" required>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <input type="text" id="search" placeholder="Search..." oninput="performSearch()">
                                </td>
                            </tr>
                        </table>
                        <div class="subnav">
                            <input type="text" id="search" placeholder="Search..." oninput="performSearch()">
                            <table class="table table-bordered" id="security-guard-table">
                                <thead>
                                    <tr>
                                        <th>Guard ID</th>
                                        <th>Guard Name</th>
                                        <th>Gender</th>
                                        <th>Phone No</th>
                                        <th>Site</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="guard" items="${listGuard}">
                                        <tr>
                                            <td>
                                                FWEFWEFEW
                                                <c:out value="${guard.guardID}" />
                                            </td>
                                            <td>
                                                FWEFEWF
                                                <c:out value="${guard.guardName}" />
                                            </td>
                                         
                                            <td>
                                                WEFEWFEW
                                                <c:out value="${guard.guardGender}" />
                                            </td>
                                            <td>
                                                WEFFEWFEW
                                                <c:out value="${guard.guardPhoneNo}" />
                                            </td>
                                          
                                            <td>
                                                EWWFWEFEW
                                                <c:out value="${guard.siteID}" />
                                            </td>
                                            <td>
                                                <button>ADD</button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>


                        <button type="submit">Add to Schedule</button>

                    </form>
                    </table>
                </div>
            </div>

            <div class="mycontainer2">
                <div style="background-color:#A7C7E7">
                    <div class="subnav">
                        <input type="text" id="search" placeholder="Search..." oninput="performSearch()">
                        <table class="table table-bordered" id="security-guard-table">
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
            </div>
        </div>
    </body>
</html>

