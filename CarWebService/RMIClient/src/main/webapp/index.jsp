<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:set var="alreadyLoggedIn" value="#session.loggedInUser"/>
<c:if test="${alreadyLoggedIn != null}">
    <c:redirect url="home.jsp"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="styles/login.css" type="text/css">
    <script src="scripts/script.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.13.1/jquery.validate.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
</head>
<body>


<h3>Welcome to the University car loan web service !</h3>

<!-- LOGIN FORM -->
<div class="text-center" style="padding:50px 0">
    <div class="logo">Login</div>
    <!-- Main Form -->
    <div class="login-form-1">
        <s:form action="logIn" class="text-left">
            <div class="login-form-main-message"></div>
            <div class="main-login-form">
                <div class="login-group">
                        <%-- ID --%>
                    <div class="form-group">
                        <label for="lg_id" class="sr-only">ID</label>
                        <input type="number" min="1" class="form-control" id="lg_id" name="formId"
                               placeholder="Please enter your ID" required/>
                    </div>

                        <%-- password --%>
                    <div class="form-group">
                        <label for="lg_password" class="sr-only">Password</label>
                        <input type="password" class="form-control" id="lg_password" name="formPassword"
                               placeholder="Please enter your password" required/>
                    </div>
                </div>
                <button type="submit" class="login-button"><i class="fa fa-chevron-right"></i></button>
            </div>
            <div class="etc-login-form">
                <p>new user? <a href="register.jsp">create new account</a></p>
            </div>
        </s:form>
    </div>
    <!-- end:Main Form -->
</div>

</body>
</html>


