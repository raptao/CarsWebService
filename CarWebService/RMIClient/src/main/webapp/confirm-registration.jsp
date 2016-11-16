
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Registration confirmation</title>
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

    <link rel="stylesheet" href="styles/registration-confirm.css" type="text/css">
    <link rel="stylesheet" href="styles/home.css" type="text/css">


</head>
<body>

<div class="container">
    <div class="col-xs-12 col-md-3">
        <div class="panel panel-success">
            <div class="cnrflash">
                <div class="cnrflash-inner">
                        <span class="cnrflash-label">YOU ARE
                            <br>
                            REGISTERED ! </span>
                </div>
            </div>
            <div class="panel-heading">
                <h3 class="panel-title">
                    This is the overview of your account</h3>
            </div>
            <div class="panel-body">
                <div class="the-price">
                    <h1>
                        ID : <span class="subscript"><s:property  value="member.getId"/> </span></h1>
                    <small>Use it to log in. And <b>keep it safe</b> !</small>
                </div>
                <table class="table">
                    <tr>
                        <td class="center-cell">
                            <small>First name :</small>
                        </td>
                        <td >
                            <s:property  value="member.getFirstName"/>
                        </td>
                    </tr>
                    <tr >
                        <td class="center-cell">
                            <small>Last name :</small>
                        </td>
                        <td >
                            <s:property  value="member.getLastName"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="center-cell">
                            <small>Account id :</small>
                        </td>
                        <td>
                            <s:property  value="member.getAccountId"/>
                        </td>
                    </tr>

                    <tr>
                        <td class="center-cell">
                            <small>Your status:</small>
                        </td>
                        <td>
                            <s:property  value="member.type.name.toUpperCase"/>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="panel-footer">
                <a href="index.jsp" class="btn btn-success" role="button">Log In now !</a>
            </div>
        </div>


    </div>
</div>
</body>
</html>
