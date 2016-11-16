<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<s:set var="alreadyLoggedIn" value="#session.loggedInUser"/>
<c:if test="${alreadyLoggedIn != null}">
    <c:redirect url="home.jsp"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
          crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="styles/home.css" type="text/css">
    <link rel="stylesheet" href="styles/login.css" type="text/css">
    <link rel="stylesheet" href="styles/add-form.css" type="text/css"/>
    <script src="scripts/script.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.13.1/jquery.validate.min.js"></script>

    <script src="scripts/jquery-3.1.1.min.js"></script>
    <script src="scripts/bootstrap.js"></script>
    <script src="scripts/bootstrap-confirmation.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
</head>
<body>
<div class="text-center" style="padding:50px 0">
    <div class="logo">Register</div>
    <!-- Main Form -->
    <div class="login-form-1">
        <s:form action="registration" id="register-form" class="text-left">
            <div class="login-form-main-message"></div>
            <div class="main-login-form">
                <div class="login-group">
                        <%-- first name --%>
                    <div class="form-group">
                        <label for="reg_firstName" class="sr-only">Fist Name</label>
                        <input type="text" class="form-control" id="reg_firstName" name="firstName"
                               placeholder="First name"
                        required/>
                    </div>

                        <%-- last name--%>
                    <div class="form-group">
                        <label for="reg_lastName" class="sr-only">Last Name</label>
                        <input type="text" class="form-control" id="reg_lastName" name="lastName"
                               placeholder="Last name"
                        required/>
                    </div>
                        <%-- account id --%>
                    <div class="form-group">
                        <label for="reg_account_id" class="sr-only">Last Name</label>
                        <input type="number" min="1" class="form-control" id="reg_account_id" name="accountId"
                               placeholder="Account id" required/>
                    </div>
                        <%--password--%>
                    <div class="form-group">
                        <label for="reg_password" class="sr-only">Password</label>
                        <input type="password" class="form-control" id="reg_password" name="password"
                               minlength="8"
                               placeholder="password" required/>
                    </div>
                        <%--password confirmation--%>
                    <div class="form-group popup">
                        <label for="reg_password_confirm" class="sr-only">Password Confirm</label>
                        <input type="password" class="form-control" id="reg_password_confirm"
                               name="reg_password_confirm" placeholder="confirm password" required
                               onkeyup="validateConfirmPassword()"
                                onblur="validateConfirmPassword()"/>
                        <span class="popuptext" id="myPopup" onclick="hide()">Check your password.</span>
                    </div>
                        <%-- member type--%>
                    <div class="form-group login-group-checkbox">
                        <input type="radio" name="memberType" value="student" id="student" required/>
                        <label for="student">Student</label>

                        <input type="radio"  name="memberType" value="teacher" id="teacher" required/>
                        <label for="teacher">Teacher</label>
                    </div>

                    <div class="form-group login-group-checkbox">
                        <input type="checkbox" class="" id="reg_agree"required/>
                        <label for="reg_agree">i agree with <a href="#">terms</a></label>
                    </div>
                </div>
                <button type="submit" class="login-button" data-toggle="confirmation"><i class="fa fa-chevron-right"></i></button>
            </div>
            <div class="etc-login-form">
                <p>already have an account? <a href="index.jsp">login here</a></p>
            </div>
        </s:form>
    </div>
    <!-- end:Main Form -->
</div>
</body>
</html>
