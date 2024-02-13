package in.shri.backend;

import java.io.IOException;
import java.util.ArrayList;

import in.shri.model.Cart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/remove")
public class Remove extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Remove() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try {
			int id=Integer.parseInt(request.getParameter("id"));
			if(id!=0) {
				ArrayList<Cart>cart_list=(ArrayList<Cart>) request.getSession().getAttribute("cartList");
				for(Cart c:cart_list) {
					if(id==c.getId()) {
						cart_list.remove(cart_list.indexOf(c));
						break;
					}
				}
				response.sendRedirect("cart.jsp");
			}
			else {
				response.sendRedirect("cart.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


}
