<%@page import="in.shri.DB.DBCon"%>
<%@page import="in.shri.model.*"%>
<%@page import="java.util.*" %>
<%@page import="in.shri.Dao.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cartList");
List<Cart> cartProduct = null;
if (cartList != null) {
	ProductDao pDao = new ProductDao(DBCon.getConnection());
	cartProduct = pDao.getCartProducts(cartList);
	double total=pDao.getSum(cartList);
	request.setAttribute("cartList", cartList);
	request.setAttribute("total", total);
}
%>
<!DOCTYPE html>
<html>
<head>
<%@include file="includes/header.jsp" %>
<meta charset="UTF-8">
<title>Your Cart😃</title>
</head>
<body>
<%@include file="includes/navbar.jsp" %>
<div class="container my-3">
		<div class="d-flex py-3"><h3>Total Price: Rs.<%= request.getAttribute("total") %> </h3> <a class="mx-3 btn btn-primary" href="checkout">Check Out</a></div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Buy Now</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (cartList != null) {
					for (Cart c : cartProduct) {
				%>
				<tr>
					<td><%=c.getName()%></td>
					<td><%=c.getCategory()%></td>
					<td><%=c.getPrice()* c.getQuantity()%></td>
					<td>
						<form action="order-now" method="post" class="form-inline">
						<input type="hidden" name="id" value="<%= c.getId()%>" class="form-input">
							<div class="form-group d-flex justify-content-between">
								<a class="btn bnt-sm btn-incre" href="update?action=inc&id=<%= c.getId()%>"><i class="fas fa-plus-square"></i></a> 
								<input type="text" name="quantity" class="form-control"  value="<%=c.getQuantity()%>" readonly> 
								<a class="btn btn-sm btn-decre" href="update?action=dec&id=<%= c.getId()%>"><i class="fas fa-minus-square"></i></a>
								<button type="submit" class="btn btn-primary btn-sm">Buy</button>
							</div>
						</form>	
					</td>
					<td><a href="remove?id=<%=c.getId() %>" class="btn btn-sm btn-danger">Remove</a></td>
				</tr>

				<%
				}}%>
			</tbody>
		</table>
	</div>
<%@include file="includes/footer.jsp" %>
</body>
</html>