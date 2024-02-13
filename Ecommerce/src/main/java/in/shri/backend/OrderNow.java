package in.shri.backend;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import in.shri.DB.DBCon;
import in.shri.Dao.OrderDao;
import in.shri.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import java.io.*;
@WebServlet("/order-now")
public class OrderNow extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try {
			SimpleDateFormat format=new SimpleDateFormat("yyyy-mm-dd");
			Date date=new Date();
			
			User auth=(User)request.getSession().getAttribute("auth");
			if(auth !=null) {
				String Product_id=request.getParameter("id");
				int Product_Quantity=Integer.parseInt(request.getParameter("quantity"));
				if(Product_Quantity <=0) {
					Product_Quantity=1;
				}
				Order order=new Order();
				order.setId(Integer.parseInt(Product_id));
				order.setUid(auth.getId());
				order.setQuantity(Product_Quantity);
				order.setDate(format.format(date));
				
				OrderDao dao=new OrderDao(DBCon.getConnection());
				boolean res=dao.insert(order);
				if(res) {
					ArrayList<Cart>cart_list=(ArrayList<Cart>) request.getSession().getAttribute("cartList");
					for(Cart c:cart_list) {
						if(c.getId()==Integer.parseInt(Product_id)) {
							cart_list.remove(cart_list.indexOf(c));
							break;
						}
					}
					response.sendRedirect("orders.jsp");
				}
				else {
					out.println("something Went wrong");
				}
				
			}
			else {
				response.sendRedirect("login.jsp");
			}
			
		}catch(Exception e) {
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
