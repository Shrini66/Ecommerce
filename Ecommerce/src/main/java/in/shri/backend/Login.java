package in.shri.backend;

import java.io.IOException;
import java.io.PrintWriter;

import in.shri.DB.DBCon;
import in.shri.Dao.UserDao;
import in.shri.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		String email=request.getParameter("email");
//		String pass=request.getParameter("password");
//			
//		PrintWriter out=response.getWriter();
//		out.println(email);
//		out.println(pass);
		
		response.sendRedirect("login.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try(PrintWriter out =response.getWriter()){
			String email=request.getParameter("email");
			String pass=request.getParameter("password");
			
			try {
				UserDao dao=new UserDao(DBCon.getConnection()); // setting up the connection as it has two methods one is constructor for checking up the connection and userlogin method having parameters email and password
				User user=dao.userLogin(email, pass);	// because of userlogin function returns a type of user it is stored in User user
				if(user!=null) {
					request.getSession().setAttribute("auth", user);
					RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
					rd.include(request, response);
				}else {
					RequestDispatcher rd=request.getRequestDispatcher("/login.jsp");
					rd.include(request, response);
				}
				
			}catch(Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
//			out.println(email + pass);
		}
		
	}

}
