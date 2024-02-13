package in.shri.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import in.shri.model.User;

public class UserDao {
	private Connection con;
	private String query;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public UserDao(Connection con) {
		this.con=con;
	}
	
	public User userLogin(String email,String password) { // this returns the type user
		User user=null;
		try {
			query="select * from User where email=? and password=?";
			ps=this.con.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet res=ps.executeQuery();
			
			if(res.next()) {
				user=new User();
				user.setEmail(rs.getString("email"));
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return user;
		
	}
}
