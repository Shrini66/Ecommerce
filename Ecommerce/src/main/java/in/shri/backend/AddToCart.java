package in.shri.backend;

import in.shri.model.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class AddToCart extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        try (PrintWriter out = response.getWriter()) {
            ArrayList<Cart> list = new ArrayList<>();

            int id = Integer.parseInt(request.getParameter("id"));

            Cart c = new Cart();
            c.setId(id);
            c.setQuantity(1);

            HttpSession session = request.getSession();
            ArrayList<Cart> list1 = (ArrayList<Cart>) session.getAttribute("cartList");

            if (list1 == null) {
                list.add(c);
                session.setAttribute("cartList", list);
//                out.println("Session Created");
                response.sendRedirect("index.jsp");
            } else {
                list = list1;
                boolean exists = false;

                for (Cart i : list1) {
                    if (i.getId() == id) {
                        exists = true;
                        out.println("Product Already Exists.. Go to Cart Page <a href='cart.jsp'>Cart</a>");
                        
                        break;
                    }
                }

                if (!exists) {
                    list.add(c);
                    session.setAttribute("cartList", list);
                    response.sendRedirect("index.jsp");
                }

//                for (Cart i : list) {
//                    out.println(i.getId());
//                } // to check which product is added
            }
        }
    }
}

// Logical Error

//@WebServlet("/cart")
//public class AddToCart extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html");
//
//        try (PrintWriter out = response.getWriter()) {
//            ArrayList<Cart> list = new ArrayList<>();
//
//            int id = Integer.parseInt(request.getParameter("id"));
//
//            Cart c = new Cart();
//            c.setId(id);
//            c.setQuantity(1);
//
//            HttpSession session = (HttpSession) request.getSession();
//            ArrayList<Cart> list1 = (ArrayList<Cart>) session.getAttribute("cartList");
//
//            if (list1 == null) {
//                list.add(c);
//                session.setAttribute("cartList", list);
//                out.println("Session Created");
//            }else {
//            	list=list1;
//            	boolean exists=false;
//            	
//            	for(Cart i:list1) {
//            		if(i.getId()==id) {
//            			out.println("Product Already Exists");
//            		}
//            		
//            	}
//            	if(!exists) {
//        			list1.add(c);
//        			out.println("Product Added");
//        		}
//            	for(Cart i:list1) {
//            		out.println(i.getId());
//            	}
//            	
//            }
//
//        }
//    }
//}
