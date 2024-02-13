package in.shri.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import in.shri.model.*;
import java.util.*;

public class ProductDao {
	private Connection con;
	private PreparedStatement ps;
	private String query;
	private ResultSet rs;
	
	public ProductDao(Connection con) {
		this.con=con;
	}
	
	public List<Products> getProducts(){
		List<Products>prod=new ArrayList<>();
		
		try {
			query="select * from products";
			ps=this.con.prepareStatement(query);  // connection from productdao class only...
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Products p=new Products();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setCategory(rs.getString("category"));
				p.setPrice(rs.getDouble("price"));
				p.setImage(rs.getString("image"));
				
				prod.add(p);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return prod;
	}
	
	
	public List<Cart>getCartProducts(ArrayList<Cart>cartList){
		List<Cart>products=new ArrayList<>();
		try {
			if(cartList.size()>0)
			{
				for(Cart item:cartList) {
					query="select * from products where id=?";
					ps=this.con.prepareStatement(query);
					ps.setInt(1, item.getId());
					rs=ps.executeQuery();
					while(rs.next()) {
						Cart c=new Cart();
						c.setId(rs.getInt("id"));
						c.setName(rs.getString("Name"));
						c.setCategory(rs.getString("Category"));
						c.setPrice(rs.getDouble("price"));
						c.setQuantity(item.getQuantity());
						products.add(c);						
					}
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return products;
	}
	
	public double getSum(ArrayList<Cart>list) {
		double sum=0;
		try {
			if(list.size()>0) {
				for(Cart i:list) {
					query="select * from products where id=?";
					ps=this.con.prepareStatement(query);
					ps.setDouble(1, i.getId());
					rs=ps.executeQuery();
					while(rs.next()) {
						sum+=rs.getDouble("price")*i.getQuantity();
					}
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return sum;
		
	}

	public Products getSingleProduct(int id) {
		// TODO Auto-generated method stub
		Products row=null;
		try {
			query="select * from products where id=?";
			ps=this.con.prepareStatement(query);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				row=new Products();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("Name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
