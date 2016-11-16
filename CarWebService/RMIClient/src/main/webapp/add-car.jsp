<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Home</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"/>

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous"/>

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="styles/home.css">
    <link rel="stylesheet" href="styles/add-form.css">
    <script src="scripts/script.js"></script>
</head>
<body>
<s:set var="alreadyLoggedIn" value="#session.loggedInUser"/>
<c:if test="${alreadyLoggedIn == null}"> <c:redirect url="index.jsp"/> </c:if>
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
            <a class="navbar-brand" href="./home.jsp">MLVCarsService</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <div class="nav navbar-nav">
                <li ><a href="./home.jsp">Home <span class="sr-only">(current)</span></a></li>
                <li class="active"><a href="./add-car.jsp">Add car<span class="sr-only">(current)</span></a></li>
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
    <div class="row">
        <div class="panel panel-primary">
            <div class="panel-body">
                <s:form action="add">
                    <div class="form-group">
                        <h2>New car</h2>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="carBrand">Car brand</label>
                        <input name="carBrand" id="carBrand" type="text" maxlength="50" class="form-control"
                               placeholder="Example : Mercedes, BMW ..." required/>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="carModel">Car model</label>
                        <input name="carModel" id="carModel" type="text" maxlength="50" class="form-control"
                               placeholder="Q5, A4 ..." required>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="registrationNumber">Car registration number</label>
                        <input autocomplete="false" id="registrationNumber" type="text" maxlength="50" class="form-control" required>
                    </div>

                    <div class="form-group">
                        <label class="control-label" for="carPrice">Car price<br>
                            <small>(Your initial car price will be in: </small> <s:property value="#session.currency"/> <small>)</small>
                        </label>
                        <input autocomplete="false" id="carPrice" name="carPrice"
                               type="number" min="500" step="50"
                               maxlength="25" class="form-control" required/>
                    </div>

                    <div class="form-group popup">

                        <label class="control-label" for="userPassword">Please reenter your password </label>
                        <input id="userPassword" name="userPassword" type="password" maxlength="25" class="form-control" required
                               onkeyup="validatePassword('<s:property value="#session.loggedInUser.getPassword"/>')"
                               onblur="validatePassword('<s:property value="#session.loggedInUser.getPassword"/>')"
                        />
                        <span class="popuptext" id="myPopup">Check your password.</span>
                    </div>

                    <div class="form-group">
                        <button id="signupSubmit" type="submit" class="btn btn-info btn-block" data-toggle="confirmation"
                                data-btn-ok-label="Continue" data-btn-ok-icon="glyphicon glyphicon-share-alt"
                                data-btn-ok-class="btn-success"
                                data-btn-cancel-label="Stoooop!" data-btn-cancel-icon="glyphicon glyphicon-ban-circle"
                                data-btn-cancel-class="btn-danger"
                                data-title="Is it ok?" data-content="This might be dangerous">Add your car</button>
                    </div>
                    <p class="form-group">By adding this car, you agree to our <a href="#">Terms of Use.</a></p>
                    <hr>
                </s:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
