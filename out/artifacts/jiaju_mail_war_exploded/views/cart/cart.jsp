<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title>韩顺平教育-家居网购</title>
    <base href="<%=request.getContextPath()+"/"%>">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <link rel="stylesheet" href="assets/css/vendor/vendor.min.css"/>
    <link rel="stylesheet" href="assets/css/plugins/plugins.min.css"/>
    <link rel="stylesheet" href="assets/css/style.min.css"/>
<%--    自己增加对加减的处理--%>
    <script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>
    <script>
        $(function (){

            //清空购物车绑定点击实践
            $("a.clearCart").click(function (){
                return confirm("你确认要删除购物车?");
            })

            //给删除购物车的按钮做一个绑定一个事件
            $("a.delItem").click(function (){
                //要获取到你要删除的家具的名字
                //jquery找到上一级
                //分析当前的结构
                var furnName = $(this).parent().parent().find("td:eq(1)").text();
                // alert("furnName"+furnName);
                //使用确认弹窗
                //返回一个false或者是true
                return confirm("你确认要删除【"+furnName+"】?");
            })
            /*----------------------------
        Cart Plus Minus Button
    ------------------------------ */
            var CartPlusMinus = $(".cart-plus-minus");
            CartPlusMinus.prepend('<div class="dec qtybutton">-</div>');
            CartPlusMinus.append('<div class="inc qtybutton">+</div>');
            $(".qtybutton").on("click", function() {

                var $button = $(this);
                var oldValue = $button.parent().find("input").val();
                if ($button.text() === "+") {
                    var newVal = parseFloat(oldValue) + 1;
                } else {
                    // Don't allow decrementing below zero
                    if (oldValue > 1) {
                        var newVal = parseFloat(oldValue) - 1;
                    } else {
                        newVal = 1;
                    }
                }
                $button.parent().find("input").val(newVal);
                // alert("newVal="+newVal);
                var furnId = $button.parent().find("input").attr("furnId");
                // alert("furnId="+furnId);
                location.href="cartServlet?action=updateCount&count="+newVal+"&id="+furnId;
            });
        })
    </script>
</head>

<body>
<!-- Header Area start  -->
<div class="header section">
    <!-- Header Top Message Start -->
    <!-- Header Top  End -->
    <!-- Header Bottom  Start -->
    <div class="header-bottom d-none d-lg-block">
        <div class="container position-relative">
            <div class="row align-self-center">
                <!-- Header Logo Start -->
                <div class="col-auto align-self-center">
                    <div class="header-logo">
                        <a href="index.html"><img src="assets/images/logo/logo.png" alt="Site Logo"/></a>
                    </div>
                </div>
                <!-- Header Logo End -->
                <!-- Header Action Start -->
                <div class="col align-self-center">
                    <div class="header-actions">
                        <div class="header-bottom-set dropdown">
                            <a>欢迎: hello</a>
                        </div>
                        <div class="header-bottom-set dropdown">
                            <a href="#">订单管理</a>
                        </div>
                        <div class="header-bottom-set dropdown">
                            <a href="#">安全退出</a>
                        </div>
                    </div>
                </div>
                <!-- Header Action End -->
            </div>
        </div>
    </div>
    <!-- Header Bottom  Start 手机端的header -->
    <div class="header-bottom d-lg-none sticky-nav bg-white">
        <div class="container position-relative">
            <div class="row align-self-center">
                <!-- Header Logo Start -->
                <div class="col-auto align-self-center">
                    <div class="header-logo">
                        <a href="index.html"><img width="280px" src="assets/images/logo/logo.png"
                                                  alt="Site Logo"/></a>
                    </div>
                </div>
                <!-- Header Logo End -->
            </div>
        </div>
    </div>
    <!-- Main Menu Start -->
    <div style="width: 100%;height: 50px;background-color: black"></div>
    <!-- Main Menu End -->
</div>
<!-- Header Area End  -->

<!-- OffCanvas Cart Start -->

<!-- OffCanvas Cart End -->

<!-- OffCanvas Menu Start -->

<!-- OffCanvas Menu End -->


<!-- breadcrumb-area start -->


<!-- breadcrumb-area end -->

<!-- Cart Area Start -->
<div class="cart-main-area pt-100px pb-100px">
    <div class="container">
        <h3 class="cart-page-title">Your cart items</h3>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                <form action="#">
                    <div class="table-content table-responsive cart-table-content">
                        <table>
                            <thead>
                            <tr>
                                <th>图片</th>
                                <th>家居名</th>
                                <th>单价</th>
                                <th>数量</th>
                                <th>金额</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%--                            找到在这里显示购物车项，进行循环的items--%>
                            <%--
                                sessionScope.cart.items=>所取出来的实际上是一个hashMap
                                HashMap<Integer, CartItem> getItems()
                                取出的hashmap其中的一项
                                所以通过foreach取出的是每个对象HashMap<Integer, CartItem> getItems()中的k-v
                                var实际上就是一个entry
                                所以要取出cart对象，entry.value.name
                            --%>
                            <c:if test="${not empty sessionScope.cart.items}">
                                <c:forEach items="${sessionScope.cart.items}" var="entry">
                                    <tr>
                                        <td class="product-thumbnail">
                                            <a href="#"><img class="img-responsive ml-3"
                                                             src="assets/images/product-image/1.jpg"
                                                             alt=""/></a>
                                        </td>
                                        <td class="product-name"><a href="#">${entry.value.name}</a></td>
                                        <td class="product-price-cart"><span class="amount">$${entry.value.price}</span></td>
                                        <td class="product-quantity">
<%--                                            某个js文件对这个cart-plus-minus做了处理--%>
                                            <div class="cart-plus-minus">
                                                <input furnId="${entry.value.id}" class="cart-plus-minus-box" type="text" name="qtybutton"
                                                       value="${entry.value.count}"/>
                                            </div>
                                        </td>
                                        <td class="product-subtotal">$${entry.value.totalPrice}</td>
                                        <td class="product-remove">
                                            <a class="delItem" href="cartServlet?action=delItem&id=${entry.value.id}"><i class="icon-close"></i></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="cart-shiping-update-wrapper">
                                <h4>共${sessionScope.cart.totalCount}件商品 总价 ${sessionScope.cart.cartTotalPrice}元</h4>
                                <div class="cart-shiping-update">
                                    <a href="orderServlet?action=saveOrder">购 物 车 - 生 成 订 单</a>
                                </div>
                                <div class="cart-clear">
                                    <button>继 续 购 物</button>
                                    <a class="clearCart" href="cartServlet?action=clear">清 空 购 物 车</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>
<!-- Cart Area End -->

<!-- Footer Area Start -->
<div class="footer-area">
    <div class="footer-container">
        <div class="footer-top">
            <div class="container">
                <div class="row">
                    <!-- Start single blog -->
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-sm-6 col-lg-3 mb-md-30px mb-lm-30px" data-aos="fade-up"
                         data-aos-delay="400">
                        <div class="single-wedge">
                            <h4 class="footer-herading">信息</h4>
                            <div class="footer-links">
                                <div class="footer-row">
                                    <ul class="align-items-center">
                                        <li class="li"><a class="single-link" href="about.html">关于我们</a></li>
                                        <li class="li"><a class="single-link" href="#">交货信息</a></li>
                                        <li class="li"><a class="single-link" href="privacy-policy.html">隐私与政策</a></li>
                                        <li class="li"><a class="single-link" href="#">条款和条件</a></li>
                                        <li class="li"><a class="single-link" href="#">制造</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-lg-2 col-sm-6 mb-lm-30px" data-aos="fade-up" data-aos-delay="600">
                        <div class="single-wedge">
                            <h4 class="footer-herading">我的账号</h4>
                            <div class="footer-links">
                                <div class="footer-row">
                                    <ul class="align-items-center">
                                        <li class="li"><a class="single-link" href="my-account.html">我的账号</a>
                                        </li>
                                        <li class="li"><a class="single-link" href="cart.html">我的购物车</a></li>
                                        <li class="li"><a class="single-link" href="login.html">登录</a></li>
                                        <li class="li"><a class="single-link" href="wishlist.html">感兴趣的</a></li>
                                        <li class="li"><a class="single-link" href="checkout.html">结账</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-lg-3" data-aos="fade-up" data-aos-delay="800">

                    </div>
                    <!-- End single blog -->
                </div>
            </div>
        </div>
        <div class="footer-bottom">
            <div class="container">
                <div class="row flex-sm-row-reverse">
                    <div class="col-md-6 text-right">
                        <div class="payment-link">
                            <img src="#" alt="">
                        </div>
                    </div>
                    <div class="col-md-6 text-left">
                        <p class="copy-text">Copyright &copy; 2021 韩顺平教育~</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Footer Area End -->
<script src="assets/js/vendor/vendor.min.js"></script>
<script src="assets/js/plugins/plugins.min.js"></script>
<!-- Main Js -->
<script src="assets/js/main.js"></script>
</body>
</html>