<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>


    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/css/bootstrap-select.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/bootstrap-select.min.js"></script>

    <link rel="stylesheet" href="styles/home.css">
    <link rel="stylesheet" href="styles/buy.css">
    <script src="scripts/script.js"></script>
    <link rel="stylesheet" href="styles/currency-selector.css" type="text/css" />
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
    <div class="row">
        <div class="col-xs-8">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <div class="panel-title">
                        <div class="row">
                            <div class="col-xs-6">
                                <h5><span class="glyphicon glyphicon-shopping-cart"></span> Shopping Cart</h5>
                            </div>
                            <div class="col-xs-6">
                                <a href="home.jsp" type="button" class="btn btn-danger btn-sm btn-block">
                                    <span class="glyphicon glyphicon-share-alt"></span> Cancel shopping
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-xs-4">
                            <h4 class="product-name"><strong><s:property value="#session.selectedCar.getBrand.toUpperCase"/> </strong></h4>
                            <h4>
                                <span><s:property value="#session.selectedCar.getModel"/></span></h4>

                            Rented times : <b><s:property value="#session.selectedCar.rentedTimes"/></b> <small>time(s)</small>
                            <br> Average grade : <b><s:property value="#session.selectedCar.averageMark"/></b><small>/1O</small>
                            <br> Rental price : <small><s:property value="#session.selectedCar.rentalPrice"/></small>
                        </div>
                        <div class="col-xs-6 car-price">
                            <div class="col-xs-6 text-right">
                                <h5><strong><s:property value="#session.selectedCar.sellingPrice"/> <span class="text-muted">x</span></strong></h5>
                            </div>
                            <div class="col-xs-4">
                                <input type="text" class="form-control input-sm" value="1" disabled>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="balance-overview">
                        <table class="table">
                            <caption class="center-cell">This is your bank account overview</caption>
                            <tr class="account-info">
                                <td class="center-cell">
                                    <span>Account <b>id</b> :</span>
                                </td>
                                <td>
                                    <s:property value="#session.loggedInUser.getAccountId"/>
                                </td>
                            </tr>
                            <tr class="account-info">
                                <td class="center-cell">
                                    <span>Account <b>balance</b> :</span>
                                </td>
                                <td>
                                    <s:set var="balance" value="#session.loggedInUser.getAccountBalance" />
                                    <fmt:formatNumber value="${balance}" type="currency" currencySymbol=""/>
                                </td>
                            </tr>

                            <tr class="account-info">
                                <td class="center-cell">
                                    <span> <b>Deduction</b> :</span>
                                </td>
                                <td class="minus">
                                    <s:set var="s_price" value="#session.selectedCar.sellingPrice" />
                                    <b>-</b> <fmt:formatNumber value="${s_price}" type="currency" currencySymbol=""/>
                                </td>
                            </tr>

                            <tr class="car-info">
                                <td class="center-cell">
                                    <span>After purchase  :</span>
                                </td>
                                <td>

                                    <c:set var="afterPurchase" value="${balance - s_price}"/>
                                    <fmt:formatNumber value="${afterPurchase}" type="currency" currencySymbol=""/> <s:property value="#session.currency"/>
                                </td>
                            </tr>
                        </table>

                    </div>
                    <hr>
                    <div class="row currency-row">
                        <div class="text-center">
                            <div class="col-xs-9">
                                <h6 class="text-right">Change currency ?</h6>
                            </div>
                            <div class="col-xs-3">

                                <b><s:property value="#session.currency"/></b> to

                                <s:form action="change-buy-currency">
                                    <div class="currency-selector">
                                        <select name="newCurrency" class="selectpicker" data-style="btn-info btn-sm">
                                            <option value="EUR" data-icon="glyphicon-eur" data-tokens="eur">EUR ,
                                                <small>(Euro)</small>
                                            </option>
                                            <option value="USD" data-icon="glyphicon-usd" data-tokens="dollard usd">USD,
                                                <small>(US Dollar)</small>
                                            </option>
                                            <option value="GBP" data-icon="glyphicon-gbp" data-tokens="england great britain pound british">GBP,
                                                (British Pound)
                                                <small></small>
                                            </option>
                                            <option value="CNY" data-icon="glyphicon-yen" data-tokens="china yen yuan">CNY,
                                                <small>(Chinese Yuan)</small>
                                            </option>
                                            <option value="JPY" data-icon="glyphicon-yen" data-tokens="japan yen">JPY,
                                                <small>(Janapese Yen)</small>
                                            </option>
                                            <option value="CHF" data-icon="glyphicon-copyright-mark" data-tokens="swiss suisse franc">CHF,
                                                <small>(Swiss Franc)</small>
                                            </option>
                                        </select>
                                    </div>

                                    <button type="submit" class="btn btn-warning btn-xs"><span class="glyphicon glyphicon-refresh"></span>
                                        Change
                                    </button>
                                </s:form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="balance-overview">



                </div>
                <div class="panel-footer">
                    <div class="row text-center">
                        <div class="col-xs-9">
                            <h4 class="text-right">Total : <strong> <span class="currency"><s:property value="#session.currency"/></span> <fmt:formatNumber value="${s_price}" type="currency" currencySymbol=""/></strong></h4>
                        </div>
                        <div class="col-xs-3">
                            <button type="button" href="buySelectedCar" class="btn btn-success btn-block" <c:if test="${afterPurchase < 0}"> disabled </c:if> >
                                Purchase it !
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        $(document).ready(function () {
            $(".btn-select").each(function (e) {
                var value = $(this).find("ul li.selected").html();
                if (value != undefined) {
                    $(this).find(".btn-select-input").val(value);
                    $(this).find(".btn-select-value").html(value);
                }
            });
        });

        $(document).on('click', '.btn-select', function (e) {
            e.preventDefault();
            var ul = $(this).find("ul");
            if ($(this).hasClass("active")) {
                if (ul.find("li").is(e.target)) {
                    var target = $(e.target);
                    target.addClass("selected").siblings().removeClass("selected");
                    var value = target.html();
                    $(this).find(".btn-select-input").val(value);
                    $(this).find(".btn-select-value").html(value);
                }
                ul.hide();
                $(this).removeClass("active");
            }
            else {
                $('.btn-select').not(this).each(function () {
                    $(this).removeClass("active").find("ul").hide();
                });
                ul.slideDown(300);
                $(this).addClass("active");
            }
        });

        $(document).on('click', function (e) {
            var target = $(e.target).closest(".btn-select");
            if (!target.length) {
                $(".btn-select").removeClass("active").find("ul").hide();
            }
        });

    </script>
</div>
</body>

</html>