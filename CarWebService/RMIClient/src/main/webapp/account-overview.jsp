<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:set var="alreadyLoggedIn" value="#session.loggedInUser"/>
<c:if test="${alreadyLoggedIn == null}"> <c:redirect url="index.jsp"/> </c:if>
<html>
<head>
    <title>Registration confirmation</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <script src="scripts/bootstrap.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="styles/registration-confirm.css" type="text/css">
    <link rel="stylesheet" href="styles/home.css" type="text/css">
    <link rel="stylesheet" href="styles/account-overview.css" type="text/css">


</head>
<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
                    aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">MLVCarsService</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <div class="nav navbar-nav">
                <li class="active"><a href="./home.jsp">Home <span class="sr-only">(current)</span></a></li>
                <li><a href="./add-car.jsp">Add car<span class="sr-only">(current)</span></a></li>
                <li>
                    <div class="navbar navbar-right"><table>
                        <tr>

                            <th id="user_name">
                                <a href="account-overview.jsp"> <s:property value="#session.loggedInUser.getFirstName"/>
                                    <s:property
                                            value="#session.loggedInUser.getLastName"/><span
                                            class="sr-only">(current)</span></a>
                            </th>
                        </tr>
                        <tr>
                            <td class="center-cell disconnect-label">
                                <a href="disconnect">disconnect</a>
                            </td>
                        </tr>
                    </table>
                    </div></li>
            </div>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>

<div class="container">
    <div class="page-header">
        <h1><s:property value="#session.loggedInUser.getFirstName"/> <s:property value="#session.loggedInUser.getLastName"/></h1>
    </div>

    <div class="col-xs-12 col-md-3">
        <div class="panel panel-success">
            <div class="cnrflash">
                <div class="cnrflash-inner">
                        <span class="cnrflash-label">YOU ARE
                            <br>
                            AWESOME </span>
                </div>
            </div>
            <div class="panel-heading">
                <h3 class="panel-title">
                    Your account overview</h3>
            </div>
            <div class="panel-body">
                <div class="the-price">
                    <h1>
                        ID : <span class="subscript"><s:property value="#session.loggedInUser.getId"/> </span></h1>
                    <small>Use it to log in. And <b>keep it safe</b> !</small>
                </div>
                <table class="table">
                    <tr>
                        <td class="center-cell">
                            <small>First name :</small>
                        </td>
                        <td>
                            <s:property value="#session.loggedInUser.getFirstName"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="center-cell">
                            <small>Last name :</small>
                        </td>
                        <td>
                            <s:property value="#session.loggedInUser.getLastName"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="center-cell">
                            <small>Status :</small>
                        </td>
                        <td>
                            <s:property value="#session.loggedInUser.type.name.toUpperCase"/>
                        </td>
                    </tr>
                    <tr class="account-info">
                        <td class="center-cell">
                            <small>Account <b>id</b> :</small>
                        </td>
                        <td>
                            <s:property value="#session.loggedInUser.getAccountId"/>
                        </td>
                    </tr>
                    <tr class="account-info">
                        <td class="center-cell">
                            <small>Account <b>balance</b> :</small>
                        </td>
                        <td>
                            <s:set var="curBalance" value="#session.loggedInUser.getAccountBalance"/>
                            <fmt:formatNumber value="${curBalance}" type="currency" currencySymbol=""/> <b><s:property value="#session.currency"/></b>
                        </td>
                    </tr>
                    <s:set var="currentCar" value="#session.loggedInUser.currentlyRenting"/>
                    <tr class="car-info">
                        <td class="center-cell">
                            <small>Current car :</small>
                        </td>
                        <td>
                            <c:choose>
                            <c:when test="${currentCar != null}">
                    <tr class="car-info-detail">
                        <td class="center-cell">
                            <small>Brand :</small>
                        </td>
                        <td><s:property value="#session.loggedInUser.currentlyRenting.getBrand"/></td>
                    </tr>
                    <tr class="car-info-detail">
                        <td class="center-cell">
                            <small>Model:</small>
                        </td>
                        <td><s:property value="#session.loggedInUser.currentlyRenting.getModel"/></td>
                    </tr>
                    <tr class="car-info-detail">
                        <td class="center-cell">
                            <small>Average grade :</small>
                        </td>
                        <td>
                            <b><s:property value="#session.loggedInUser.currentlyRenting.averageMark"/></b><small>/10</small>
                        </td>
                    </tr>

                    </c:when>
                    <c:otherwise>
                        <small>( no current car )</small>
                    </c:otherwise>
                    </c:choose>
                    </td>
                    </tr>
                </table>
            </div>
            <div class="panel-footer">
                <a href="home.jsp" class="btn btn-success" role="button">Home</a>
            </div>
        </div>


    </div>
</div>
</body>
</html>
