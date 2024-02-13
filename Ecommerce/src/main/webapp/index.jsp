<%@page import="in.shri.Dao.ProductDao"%>
<%@page import="in.shri.DB.DBCon"%>
<%@page import="in.shri.model.*"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    User auth=(User)request.getSession().getAttribute("auth");
    if(auth!=null){
        request.setAttribute("auth", auth);
    }
    
    ProductDao pd=new ProductDao(DBCon.getConnection());
    List<Products> prod=pd.getProducts();
%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="includes/header.jsp" %>
        
    <meta charset="UTF-8">
    <title>Home</title>
    <style>
        .product-box {
            border: 1px solid #ddd;
            padding: 15px;
            margin: 10px;
            text-align: center;
        }

        .product-image {
            max-width: 100%;
            height: auto;
        }

        .btn-buy {
            background-color: #28a745;
            color: #fff;
        }
    </style>
</head>
<body>
    <%@include file="includes/navbar.jsp" %>

    <div class="container">
        <div class="row">
            <%
                if(!prod.isEmpty()){
                    int count = 0; // To keep track of the number of products in the current row
                    for(Products p : prod){
                        %>
                        <div class="col-md-3"> <!-- Adjust the grid size according to your layout -->
                            <div class="product-box">
                                <img src="images/<%=p.getImage()%>" alt="Product Image" class="product-image">
                                <h4><%= p.getName() %></h4>
                                <p>Category: <%= p.getCategory() %></p>
                                <p>Price: <%= p.getPrice()%></p>
                                <button class="btn"><a href="cart?id=<%= p.getId()%>" class="btn btn-primary">Add to Cart</a></button>
                                <a href="order-now?quantity=1&id=<%= p.getId()%>" class="btn btn-primary">Buy Now</a>
                            </div>
                        </div>
                        <%
                        count++;
                        // Check if we have reached the maximum number of products per row
                        if (count == 4) {
                            %>
                            </div> <!-- Close the current row -->
                            <div class="row"> <!-- Start a new row -->
                            <%
                            count = 0; // Reset the count for the new row
                        }
                    }
                }
            %>
        </div>
    </div>

    <%@include file="includes/footer.jsp" %>
</body>
</html>
