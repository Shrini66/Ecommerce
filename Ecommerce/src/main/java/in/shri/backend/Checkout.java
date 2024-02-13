package in.shri.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import in.shri.Dao.OrderDao;
import in.shri.model.Cart;
import in.shri.model.Order;
import in.shri.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Checkout() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out=response.getWriter()){
			
			SimpleDateFormat formatter=new SimpleDateFormat();
			Date date=new Date();
			
			ArrayList<Cart>cart_list=(ArrayList<Cart>)request.getSession().getAttribute("cartList");
			
			User auth=(User)request.getSession().getAttribute("auth");
			
			if(cart_list!=null && auth!=null) {
				for(Cart c:cart_list) {
					Order order=new Order();
					order.setId(c.getId());
					order.setUid(auth.getId());
					order.setQuantity(c.getQuantity());
					order.setDate(formatter.format(date));
					
					OrderDao dao=new OrderDao(in.shri.DB.DBCon.getConnection());
					boolean res=dao.insert(order);
					if(!res) {
						break;
					}
				}
				cart_list.clear();
				response.sendRedirect("orders.jsp");
			}else {
				if(auth==null) {
					response.sendRedirect("login.jsp");
				}else {
					response.sendRedirect("cart.jsp");
				}
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
