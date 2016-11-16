<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:set var="alreadyLoggedIn" value="#session.loggedInUser"/>
<c:if test="${alreadyLoggedIn == null}">
    <c:redirect url="index.jsp"/> </c:if>
<!DOCTYPE hmtl>
<html>

<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous"/>

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
          crossorigin="anonymous"/>

    <script src="scripts/jquery-3.1.1.min.js"></script>

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>


    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/css/bootstrap-select.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/bootstrap-select.min.js"></script>

    <script src="scripts/script.js"></script>
    <link rel="stylesheet" href="styles/currency-selector.css" type="text/css"/>
    <link rel="stylesheet" href="styles/comments.css"/>
    <script src="scripts/comments.js"></script>
    <link rel="stylesheet" href="styles/home.css">
</head>

<body>


<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1"
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
                <li class="active"><a href="./home.jsp">Home <span class="sr-only">(current)</span></a></li>
                <li><a href="./add-car.jsp">Add car<span class="sr-only">(current)</span></a></li>
                <li>
                    <div class="navbar navbar-right">
                        <table>
                            <tr>

                                <th id="user_name">
                                    <a href="account-overview.jsp"> <s:property
                                            value="#session.loggedInUser.getFirstName"/>
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
                    </div>
                </li>
            </div>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>

<section class="container">
    <ol class="breadcrumb">
        <li><a href="home.jsp">Home</a></li>
    </ol>


    <div class="page-header">
        <h1>Rent</h1>
        <a href="./add-car.jsp" class="btn btn-primary navbar-right">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Add Car
        </a>
    </div>

    <s:set var="currentCar" value="#session.loggedInUser.currentlyRenting"/>
    <div class="jumbotron">
        <c:choose>
            <c:when test="${currentCar != null}">
                <h1 style="font-size:30px">My Car</h1>
                <table class="table table-hover">
                    <tr>
                        <th>Brand</th>
                        <th>Model</th>
                        <th>Mark</th>
                    </tr>
                    <tr>
                        <td>
                            <s:property value="#session.loggedInUser.currentlyRenting.getBrand.toUpperCase"/>
                        </td>
                        <td>
                            <s:property value="#session.loggedInUser.currentlyRenting.getModel"/>
                        </td>
                        <td>
                            <s:form action="getAllComments">
                                <input type="hidden" name="selectedCar" id="selectedCar"
                                       value="<s:property value='#session.loggedInUser.currentlyRenting.getId'/>"
                                />
                                <button type="submit" class="btn btn-default"><s:property
                                        value="#session.loggedInUser.currentlyRenting.getAverageMark"/></button>
                            </s:form>
                        </td>
                    </tr>
                </table>
                <s:set var="mineIsBought" value="#session.loggedInUser.currentlyRenting.isBought"/>
                <s:set var="mineIsBuyable" value="#session.loggedInUser.currentlyRenting.isBuyable"/>
                <s:form action="buyCurrentProduct">
                    <button class="btn btn-success" <c:if test="${!mineIsBuyable}">disabled</c:if>>Buy</button>
                </s:form>
                <button type="button" data-toggle="modal" data-target="#myModal" class="btn btn-warning">Comment
                </button>
                <s:form action="restoreCurrentProduct">
                    <button class="btn btn-danger" <c:if test="${mineIsBought}">disabled</c:if>>Restore</button>
                </s:form>
            </c:when>
            <c:otherwise>
                ( no car currently rented )
            </c:otherwise>
        </c:choose>
    </div>

    <%--search--%>

    <div class="selector">
        <h4>Choose your currency</h4>
        <small class="current-currency">Current currency :</small>
        <b><s:property value="#session.currency"/></b>

        <s:form action="changeCurrency">
            <div class="currency-selector">

                <select name="newCurrency" class="selectpicker" data-live-search="true" data-style="btn-info">
                    <option selected disabled value="">--Change currency--</option>
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
            <small class="current-currency">(You need an active internet connection to use this feature)</small>
        </s:form>
    </div>

    <br><br>
    <table class="table table-hover">
        <tr>
            <th>Brand</th>
            <th>Model</th>
            <th><b>Selling</b> price</th>
            <th>Buyable</th>
            <th><b>Rental</b> price</th>
            <th>Grade</th>
            <th>Available</th>
            <th>Rented Times</th>
            <th>Rent</th>
            <th>Buy</th>
        </tr>


        <s:iterator value="#session.allProducts" var="product">
            <tr>
                <s:set var="buyability" value="#product.isBuyable"/>
                <td><b><s:property value="#product.getBrand.toUpperCase"/></b></td>
                <td>
                    <s:property value="#product.getModel"/>
                </td>
                <td class="center-cell">
                    <s:set var="s_price" value="#product.sellingPrice"/>
                    <fmt:formatNumber value="${s_price}" type="currency" currencySymbol=""/>
                </td>
                <td class="center-cell">
                    <c:choose>
                        <c:when test="${buyability}">
                            <span class="glyphicon glyphicon-ok" style="color:lawngreen" aria-hidden="true"></span>
                        </c:when>
                        <c:otherwise>
                            <span class="glyphicon glyphicon-remove" style="color:orange" aria-hidden="true"></span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="center-cell">
                    <s:set var="r_price" value="#product.rentalPrice"/>
                    <fmt:formatNumber value="${r_price}" type="currency" currencySymbol=""/>
                </td>
                <td class="center-cell">
                    <s:form action="getAllComments">
                        <input type="hidden" name="selectedCar" id="selectedCar"
                               value="<s:property value='#product.getId'/>"/>
                        <button type="submit" class="btn btn-default"><s:property
                                value="#product.getAverageMark"/></button>
                    </s:form>


                </td>
                <td class="center-cell">
                    <s:set var="availability" value="#product.isAvailable"/>
                    <s:set var="boughtAProduct" value="#session.loggedInUser.hasBoughtAProduct"/>
                    <c:choose>
                        <c:when test="${availability}">
                            <span class="glyphicon glyphicon-ok" style="color:green" aria-hidden="true"></span>
                        </c:when>
                        <c:otherwise>
                            <span class="glyphicon glyphicon-remove" style="color:red" aria-hidden="true"></span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="center-cell">
                    <s:property value="#product.rentedTimes"/>
                </td>
                <td class="center-cell">
                    <s:form class="buy-rent" action="rent">
                        <input type="hidden" name="selectedCar" id="selectedCar"
                               value="<s:property value='#product.getId'/>"/>

                        <div class="dropdown">
                            <button type="submit" class="glyphicon glyphicon-shopping-cart dropbtn" aria-hidden="true"
                                    <c:if test="${boughtAProduct}">disabled</c:if>>
                            </button>
                            <c:if test="${!availability}">
                                <div class="dropdown-content">
                                    <p>You will be added to the renter queue of this car.</p>
                                </div>
                            </c:if>
                        </div>
                    </s:form>
                </td>

                <td class="center-cell">
                    <s:form class="buy-rent" action="buy">
                        <input type="hidden" name="selectedCar" id="selectedCar"
                               value="<s:property value='#product.getId'/>"/>

                        <div class="dropdown">
                        <button type="submit" class="glyphicon glyphicon-euro dropbtn buy-btn" style="color:#E3A311" aria-hidden="true"
                                <c:if test="${!buyability}">disabled</c:if>>
                        </button>
                            <c:if test="${!buyability}">
                                <div class="dropdown-content cant-be-bought">
                                    <p>This car cannot be bought.</p>
                                </div>
                            </c:if>
                        </div>
                    </s:form>
                </td>
            </tr>
        </s:iterator>
    </table>


    <!-- Modal -->
    <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Give a comment to this
                        <s:property value="#session.loggedInUser.currentlyRenting.getBrand.toUpperCase"
                        /> !</h4>
                </div>
                <div class="modal-body">
                    <div class="container">
                        <div class="row">

                            <div class="col-md-6">
                                <div class="widget-area no-padding blank">
                                    <div class="status-upload">

                                        <s:form action="commentCurrentProduct">
                                            <textarea name="comment"
                                                      placeholder="What are your thoughts about this car ? "></textarea>

                                            <div class="form-group">

                                                <select name="rating" class="form-control" id="sel1" required>
                                                    <option selected disabled value="">--Car rating--</option>
                                                    <option value="0">0</option>
                                                    <option value="1">1</option>
                                                    <option value="2">2</option>
                                                    <option value="3">3</option>
                                                    <option value="4">4</option>
                                                    <option value="5">5</option>
                                                    <option value="6">6</option>
                                                    <option value="7">7</option>
                                                    <option value="8">8</option>
                                                    <option value="9">9</option>
                                                    <option value="10">10</option>
                                                </select>
                                            </div>
                                            <button type="submit" class="btn btn-success green share-button"><i
                                                    class="fa fa-share"></i> Share
                                            </button>
                                        </s:form>

                                    </div>
                                    <!-- Status Upload  -->


                                </div>
                                <!-- Widget Area -->


                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default " data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>
    </div>

</section>

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
</body>

</html>