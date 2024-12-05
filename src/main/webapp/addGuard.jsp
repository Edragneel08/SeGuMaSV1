<%-- 
    Document   : addGuard
    Created on : 15 Jan 2024, 6:31:39 pm
    Author     : edrag
--%>

<%-- 
    Document   : addNewHrStaff
    Created on : 14 Jan 2024, 6:24:46 pm
    Author     : edrag
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add new HR Staff</title>
        <link rel="stylesheet" href="CSS/adminMenu.css">
        <link rel="stylesheet" href="CSS/addHR.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="js/navBar.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <div class="sidebar">
            <a class="segu" href="#SEGUMAS"><img src="img/logo.png" alt="Trulli" width="100%" height="100%" ></a>
                <%
                    // Get the value of the "username" attribute from the session
                    String username = (String) session.getAttribute("username");
                %>
            <h2 style="color: wheat">Hi,<%= username %></h2>
            <a  href="adminMenu.jsp">Home</a>
            <ul class="mainmenu">
                <li class="dropdown">
                    <a href="" class="dropdown-link active">Manage HR Staff</a>
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
            <a href="${pageContext.request.contextPath}/logout">LOGOUT</a>
        </div>


        //form
        <div class="content">
            <!-- content -->
            <div class="mycontainer3">

                <div class="container col-md-8">
                    <div class="card">
                        <div class="card-body">
                            <c:if test="${guard != null}">
                                <form action="updateGuard" method="GET">
                                </c:if>
                                <c:if test="${guard == null}">
                                    <form action="insertGuard" method="GET">
                                        <!-- <input type='hidden' name="action" value="insert">-->
                                    </c:if>

                                    <caption>
                                        <h2>
                                            <c:if test="${guard != null}">
                                                Edit Security Guard
                                            </c:if>
                                            <c:if test="${guard == null}">
                                                Add New Security Guard
                                            </c:if>
                                        </h2>
                                    </caption>

                                    <fieldset class="form-group">
                                        <label>Security Guard ID : </label> 
                                        <input type="text" value="<c:out value='${guard.guardID}' />"
                                               class="form-control" name="guardID" required="required"/>
                                    </fieldset>

                                    <fieldset class="form-group">
                                        <label>Security Guard Full Name : </label> 
                                        <input type="text" value="<c:out value='${guard.guardName}' />"
                                               class="form-control" name="guardName" required="required"/>
                                    </fieldset>

                                    <fieldset class="form-group">
                                        <label>Security Guard Age : </label> 
                                        <input type="text" value="<c:out value='${guard.guardAge}' />" 
                                               class="form-control" name="guardAge"/>
                                    </fieldset>

                                    <fieldset class="form-group">
                                        <label for="guardGender">Gender:</label>
                                        <select class="form-control" name="guardGender" id="guardGender">
                                            <option value="" <c:if test="${guard.guardGender eq 'Male'}">selected</c:if>>Choose</option>
                                            <option value="Male" <c:if test="${guard.guardGender eq 'Male'}">selected</c:if>>Male</option>
                                            <option value="Female" <c:if test="${guard.guardGender eq 'Female'}">selected</c:if>>Female</option>
                                            <option value="Other" <c:if test="${guard.guardGender eq 'Other'}">selected</c:if>>Other</option>
                                                <!-- Add more options as needed -->
                                            </select>
                                        </fieldset>


                                        <fieldset class="form-group">
                                            <label>Phone Number : </label>
                                            <input type="text" value="<c:out value='${guard.guardPhoneNo}' />" 
                                               class="form-control" name="guardPhoneNo"/>
                                    </fieldset>

                                    <fieldset class="form-group">
                                        <label>Email : </label>
                                        <input type="text" value="<c:out value='${guard.guardEmail}' />" 
                                               class="form-control" name="guardEmail"/>
                                    </fieldset>

                                    <fieldset class="form-group">
                                        <label for="nationality">Nationality:</label>
                                        <select class="form-control" name="nationality" id="nationality">
                                            <option value="" <c:if test="${guard.nationality eq 'Choose'}">selected</c:if>>Choose Nationality</option>
                                            <option value="Citizen" <c:if test="${guard.nationality eq 'Citizen'}">selected</c:if>>Citizen</option>
                                            <option value="Non-Citizen" <c:if test="${guard.nationality eq 'Non-Citizen'}">selected</c:if>>Non-Citizen</option>
                                                <!-- Add more options as needed -->
                                            </select>
                                        </fieldset>


                                        <fieldset class="form-group">
                                            <label for="guardRace">Race:</label>
                                            <select class="form-control" name="guardRace" id="guardRace">
                                                <option value="" <c:if test="${guard.guardRace eq 'Choose'}">selected</c:if>>Choose Race</option>
                                            <option value="Malays" <c:if test="${guard.guardRace eq 'Malays'}">selected</c:if>>Malays</option>
                                            <option value="Chinese" <c:if test="${guard.guardRace eq 'Chinese'}">selected</c:if>>Chinese</option>
                                            <option value="India" <c:if test="${guard.guardRace eq 'India'}">selected</c:if>>India</option>
                                            <option value="Others" <c:if test="${guard.guardRace eq 'Others'}">selected</c:if>>Others</option>
                                                <!-- Add more options as needed -->
                                            </select>
                                        </fieldset>


                                        <fieldset class="form-group">
                                            <label>Salary : </label>
                                            <input type="text" value="<c:out value='${guard.guardSalary}' />" 
                                               class="form-control" name="guardSalary"/>
                                    </fieldset>

                                    <fieldset class="form-group">
                                        <label>Date Of Birth : </label>
                                        <input type="text" value="<c:out value='${guard.guardDOB}' />" 
                                               class="form-control" name="guardDOB"/>
                                    </fieldset>

                                    <fieldset class="form-group">
                                        <label>Branch ID : </label>
                                        <input type="text" value="<c:out value='${guard.branchID}' />" 
                                               class="form-control" name="branchID"/>
                                    </fieldset>

                                    <fieldset class="form-group">
                                        <label>Site ID : </label>
                                        <input type="text" value="<c:out value='${guard.siteID}' />" 
                                               class="form-control" name="siteID"/>
                                    </fieldset>

                                    <c:if test="${guard != null}">
                                        <button type="submit" class="btn btn-success">UPDATE</button>
                                        <input type='hidden' name="action" value="update    ">
                                    </c:if>
                                    <c:if test="${guard == null}">
                                        <button type="submit" class="btn btn-success">SUBMIT</button>
                                    </c:if>
                                </form>
                        </div>
                    </div>
                </div>
            </div>
        </div> 
    </body>
</html>