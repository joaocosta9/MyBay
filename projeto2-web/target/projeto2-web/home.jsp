<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==" crossorigin="anonymous">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">


    <style>
        body{
            margin-top:20px;
            background:#eee;
        }

        .prod-info-main {
            border: 1px solid #CEEFFF;
            margin-left: 160px;
            margin-bottom: 20px;
            margin-top: 10px;
            background: #fff;
            padding: 6px;
            -webkit-box-shadow: 0 1px 4px 0 rgba(21,180,255,0.5);
            box-shadow: 0 1px 1px 0 rgba(21,180,255,0.5);
        }

        .prod-info-main .product-image {
            background-color: #EBF8FE;
            display: block;
            min-height: 238px;
            overflow: hidden;
            position: relative;
            border: 1px solid #CEEFFF;
            padding-top: 40px;
        }

        .prod-info-main .product-deatil {
            border-bottom: 1px solid #dfe5e9;
            padding-bottom: 17px;
            padding-left: 16px;
            padding-top: 16px;
            position: relative;
            background: #fff
        }

        .product-content .product-deatil h5 a {
            color: #2f383d;
            font-size: 15px;
            line-height: 19px;
            text-decoration: none;
            padding-left: 0;
            margin-left: 0
        }

        .prod-info-main .product-deatil h5 a span {
            color: #9aa7af;
            display: block;
            font-size: 13px
        }

        .prod-info-main .product-deatil span.tag1 {
            border-radius: 50%;
            color: #fff;
            font-size: 15px;
            height: 50px;
            padding: 13px 0;
            position: absolute;
            right: 10px;
            text-align: center;
            top: 10px;
            width: 50px
        }

        .prod-info-main .product-deatil span.sale {
            background-color: #21c2f8
        }

        .prod-info-main .product-deatil span.discount {
            background-color: #71e134
        }

        .prod-info-main .product-deatil span.hot {
            background-color: #fa9442
        }

        .prod-info-main .description {
            font-size: 12.5px;
            line-height: 20px;
            padding: 10px 14px 16px 19px;
            background: #fff
        }

        .prod-info-main .product-info {
            padding: 11px 19px 10px 20px
        }

        .prod-info-main .product-info a.add-to-cart {
            color: #2f383d;
            font-size: 13px;
            padding-left: 16px
        }

        .prod-info-main name.a {
            padding: 5px 10px;
            margin-left: 16px
        }

        .product-info.smart-form .btn {
            padding: 6px 12px;
            margin-left: 12px;
            margin-top: -10px
        }

        .load-more-btn {
            background-color: #21c2f8;
            border-bottom: 2px solid #037ca5;
            border-radius: 2px;
            border-top: 2px solid #0cf;
            margin-top: 20px;
            padding: 9px 0;
            width: 100%
        }

        .product-block .product-deatil p.price-container span,
        .prod-info-main .product-deatil p.price-container span,
        .shipping table tbody tr td p.price-container span,
        .shopping-items table tbody tr td p.price-container span {
            color: #21c2f8;
            font-family: Lato, sans-serif;
            font-size: 24px;
            line-height: 20px
        }

        .product-info.smart-form .rating label {
            margin-top:15px;

        }

        .prod-wrap .product-image span.tag2 {
            position: absolute;
            top: 10px;
            right: 10px;
            width: 36px;
            height: 36px;
            border-radius: 50%;
            padding: 10px 0;
            color: #fff;
            font-size: 11px;
            text-align: center
        }

        .prod-wrap .product-image span.tag3 {
            position: absolute;
            top: 10px;
            right: 20px;
            width: 60px;
            height: 36px;
            border-radius: 50%;
            padding: 10px 0;
            color: #fff;
            font-size: 11px;
            text-align: center
        }

        .prod-wrap .product-image span.sale {
            background-color: #57889c;
        }

        .prod-wrap .product-image span.hot {
            background-color: #a90329;
        }

        .prod-wrap .product-image span.special {
            background-color: #3B6764;
        }
        .shop-btn {
            position: relative
        }

        .shop-btn>span {
            background: #a90329;
            display: inline-block;
            font-size: 10px;
            box-shadow: inset 1px 1px 0 rgba(0, 0, 0, .1), inset 0 -1px 0 rgba(0, 0, 0, .07);
            font-weight: 700;
            border-radius: 50%;
            padding: 2px 4px 3px!important;
            text-align: center;
            line-height: normal;
            width: 19px;
            top: -7px;
            left: -7px
        }

        .product-deatil hr {
            padding: 0 0 5px!important
        }

        .product-deatil .glyphicon {
            color: #3276b1
        }

        .product-deatil .product-image {
            border-right: 0px solid #fff !important
        }

        .product-deatil .name {
            margin-top: 0;
            margin-bottom: 0
        }

        .product-deatil .name small {
            display: block
        }

        .product-deatil .name a {
            margin-left: 0
        }

        .product-deatil .price-container {
            font-size: 24px;
            margin: 0;
            font-weight: 300;

        }

        .product-deatil .price-container small {
            font-size: 12px;

        }

        .product-deatil .fa-2x {
            font-size: 16px!important
        }

        .product-deatil .fa-2x>h5 {
            font-size: 12px;
            margin: 0
        }

        .product-deatil .fa-2x+a,
        .product-deatil .fa-2x+a+a {
            font-size: 13px
        }

        .product-deatil .certified {
            margin-top: 10px
        }

        .product-deatil .certified ul {
            padding-left: 0
        }

        .product-deatil .certified ul li:not(first-child) {
            margin-left: -3px
        }

        .product-deatil .certified ul li {
            display: inline-block;
            background-color: #f9f9f9;
            padding: 13px 19px
        }

        .product-deatil .certified ul li:first-child {
            border-right: none
        }

        .product-deatil .certified ul li a {
            text-align: left;
            font-size: 12px;
            color: #6d7a83;
            line-height: 16px;
            text-decoration: none
        }

        .product-deatil .certified ul li a span {
            display: block;
            color: #21c2f8;
            font-size: 13px;
            font-weight: 700;
            text-align: center
        }

        .product-deatil .message-text {
            width: calc(100% - 70px)
        }

        @media only screen and (min-width:1024px) {
            .prod-info-main div[class*=col-md-4] {
                padding-right: 0
            }
            .prod-info-main div[class*=col-md-8] {
                padding: 0 13px 0 0
            }
            .prod-wrap div[class*=col-md-5] {
                padding-right: 0
            }
            .prod-wrap div[class*=col-md-7] {
                padding: 0 13px 0 0
            }
            .prod-info-main .product-image {
                border-right: 1px solid #dfe5e9
            }
            .prod-info-main .product-info {
                position: relative
            }
        }

        .sidenav {
            height: 100%; /* Full-height: remove this if you want "auto" height */
            width: 160px; /* Set the width of the sidebar */
            margin-top: 80px;
            position: fixed; /* Fixed Sidebar (stay in place on scroll) */
            z-index: 1; /* Stay on top */
            top: 0; /* Stay at the top */
            left: 0;
            background-color: #6d7a83; /* Black */
            overflow-x: hidden; /* Disable horizontal scroll */
            padding-top: 20px;
        }

        .sidenav a {
            padding: 6px 8px 6px 16px;
            text-decoration: none;
            font-size: 22px;
            color: #f1f1f1;
            display: block;
        }

        /* When you mouse over the navigation links, change their color */
        .sidenav a:hover {
            color: #f1f1f1;
        }
    </style>

</head>

<body>
<div class="container">
    <script>    function selectOnlyThis(id) {
        for (var i = 1;i <= 4; i++)
        {
            document.getElementById("Check" + i).checked = false;
        }
        document.getElementById(id).checked = true;
    }</script>

    <script>function updateTextInput(val) {
        document.getElementById('minprice').value=val;
    }</script>

    <script>function updateTextInput2(val) {
        document.getElementById('maxprice').value=val;
    }</script>
    <div style="align-self: center; margin-left: 580px">
        <div  style="margin-bottom: 40px" class="dropdown">
            <button style="margin-top: 20px" class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort by
                <span class="caret"></span></button>
            <ul class="dropdown-menu">
                <li><a href="#">Name</a></li>
                <li><a href="#">Price ascending</a></li>
                <li><a href="#">Price descending</a></li>
            </ul>
        </div>
    </div>
    <div class="sidenav">
        <a> Category <form style="font-size: 15px" action="/action_page.php">
            <input type="checkbox" id="Check1" onclick="selectOnlyThis(this.id)" value="Sport"> Sport<br>
            <input type="checkbox" id="Check2" onclick="selectOnlyThis(this.id)" value="Lazer"> Lazer<br>
            <input type="checkbox" id="Check3" onclick="selectOnlyThis(this.id)" value="Technology" > Technology <br><br>

        </form></a>
        <a href="#">Price range <form class="range-field row">
            <div style="font-size: 15px" class="col mr-0 pr-0">
                Min price
                <input type="range" min="0" max="10000" onchange="updateTextInput(this.value)" />
                <input  type="text" id="minprice" value="">
            </div>
            <div style="font-size: 15px" class="col ml-0 pl-0">
                Max price
                <input type="range" min="0" max="10000" onchange="updateTextInput2(this.value)" />
                <input id="maxprice" type="text" id="maxprice" value="">
            </div>
        </form> </a>
        <a href="#"> <button  style="background-color: #3276b1; width:100px;height: 50px" type="submit" value="Submit">  Submit </button> </a>
    </div>

    <% for(int i = 0; i < 5; i+=1) { %>
    <div class="col-xs-12 col-md-6">
        <!-- First product box start here-->
        <div class="prod-info-main prod-wrap clearfix">
            <div class="row">
                <div class="col-md-5 col-sm-12 col-xs-12">
                    <div class="product-image">
                        <img src="images/products/p4.png" class="img-responsive">
                    </div>
                </div>
                <div class="col-md-7 col-sm-12 col-xs-12">
                    <div class="product-deatil">
                        <h5 class="name">
                            <a href="#">
                                Product Code/Name here
                            </a>
                            <a href="#">
                                <span>Product Category</span>
                            </a>

                        </h5>
                        <p class="price-container">
                            <span>$199</span>
                        </p>
                        <span class="tag1"></span>
                    </div>
                    <div class="description">
                        <p>A Short product description here </p>
                    </div>
                </div>
            </div>
        </div>
        <!-- end product -->
    </div>
    <% } %>

</div>
</body>
</html>

