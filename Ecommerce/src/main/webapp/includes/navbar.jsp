<%@page import="java.util.*"%>
<%@page import="in.shri.model.*"%>
<head>
<style>
body {
	padding-top: 56px;
}

.navbar {
	background-color: #343a40;
}

.navbar-brand, .navbar-nav .nav-link {
	color: #ffffff;
}

.navbar-brand:hover, .navbar-nav .nav-link:hover {
	color: #f8f9fa;
}

.navbar-toggler-icon {
	background-color: #ffffff;
}
</style>
</head>

<%
// Get the current page URL
String currentPage = request.getRequestURI();
%>
<%
    ArrayList<Cart> cl = (ArrayList<Cart>) session.getAttribute("cartList");
if (cl != null) {
	request.setAttribute("cartList", cl);
}
%>

<nav class="navbar navbar-expand-lg navbar fixed-top">
	<a class="navbar-brand" href="#">Modern Clothing</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarNav" aria-controls="navbarNav"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarNav">
		<ul class="navbar-nav ml-auto">
			<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="cart.jsp">Cart
					<span><%=(cl != null) ? cl.size() : 0%></span>
			</a></li>

			<%
			if (session.getAttribute("auth") != null) {
			%>
			<li class="nav-item"><a class="nav-link" href="orders.jsp">Orders</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="logout">Logout</a>
			</li>

			<%
			} else {
			%>

			<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a>
			</li>
			<%
			}
			%>

		</ul>
	</div>
</nav>
