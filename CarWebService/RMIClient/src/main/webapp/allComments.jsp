<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:set var="alreadyLoggedIn" value="#session.loggedInUser" />
<c:if test="${alreadyLoggedIn == null}">
    <c:redirect url="index.jsp" /> </c:if>
<!DOCTYPE hmtl>
<html>

<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous" />

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
          crossorigin="anonymous" />

    <script src="scripts/jquery-3.1.1.min.js"></script>

    <!-- Latest compiled and minified JavaScript -->
    <script src="scripts/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>


    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/css/bootstrap-select.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/bootstrap-select.min.js"></script>

    <link rel="stylesheet" href="styles/home.css">
    <link rel="stylesheet" href="styles/all-comments.css">
    <script src="scripts/script.js"></script>
    <link rel="stylesheet" href="styles/account-overview.css"/>
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
        <h1>All comments about this car</h1>
    </div>

    <div class="alert alert-warning" role="alert" id="rating"><s:property value="#session.selectedCar.getAverageMark"/> <small>/10</small></div>
    <div class="alert alert-info" role="alert" id="car-info">

        <table class="table">
            <tr class="car-info-detail">
                <td class="center-cell">
                    <small>Brand :</small>
                </td>
                <td><s:property value="#session.selectedCar.getBrand.toUpperCase"/></td>
            </tr>
            <tr class="car-info-detail">
                <td class="center-cell">
                    <small>Model:</small>
                </td>
                <td><s:property value="#session.selectedCar.getModel"/></td>
            </tr>
            <tr class="car-info-detail">
                <td class="center-cell">
                    <small>Available for rent :</small>
                </td>
                <td><b>
                    <s:set var="selectedAvailable" value="#session.selectedCar.isAvailable"/>
                    <c:choose>
                        <c:when test="${selectedAvailable}">YES</c:when>
                        <c:otherwise>NO</c:otherwise>
                    </c:choose>
                   </b>
                </td>
            </tr>
        </table>
    </div>
    <div class="pre-scrollable">

        <table id="all-comments">
            <s:iterator value="#session.selectedCar.getComments" status="statusVar">
                <tr class="one-comment">
                    <td>
                        <div class="alert alert-success" role="alert"><s:property/> </div>
                    </td>
                </tr>
            </s:iterator>
        </table>



    </div>

    <div class="bottom">
        <a href="home.jsp" class="btn btn-success">Home</a>
    </div>
</div>
</body>

</html>