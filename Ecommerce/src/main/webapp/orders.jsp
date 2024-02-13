<%@page import="in.shri.Dao.OrderDao"%>
<%@page import="in.shri.DB.DBCon"%>
<%@page import="in.shri.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<Order>orders=null;
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
	orders=new OrderDao(DBCon.getConnection()).orders(auth.getId());
}else{
	response.sendRedirect("login.jsp");
}

ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cartList");
if (cart_list != null) {
	request.getSession().setAttribute("cartList", cart_list);
}
%>
<!DOCTYPE html>
<html>
<head>
<%@include file="includes/header.jsp"%>
<meta charset="UTF-8">
<title>Orders</title>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>
	<div class="container">
		<div class="card-header my-3">All Orders</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Quantity</th>
					<th scope="col">Date</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
			<%
				if(orders!=null){
					for(Order i:orders){ %>
						<tr>
							<td><%= i.getName()%></td>
							<td><%= i.getCategory()%></td>
							<td><%= i.getPrice()%></td>
							<td><%= i.getQuantity()%></td>
							<td><%= i.getDate()%></td>
							<td><a href="cancel?id=<%= i.getOrder_id()%>" class="btn btn-sm btn-danger">Cancel</a></td>
						</tr>
					<%	
					}
				}
			%>
			</tbody>
		</table>

	</div>

	<%@include file="includes/footer.jsp"%>
</body>
</html>