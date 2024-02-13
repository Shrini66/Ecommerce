package in.shri.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.shri.model.Order;
import in.shri.model.Products;

public class OrderDao {
    private Connection con;
    private String query;
    private PreparedStatement ps;
    private ResultSet rs;

    public OrderDao(Connection con) {
        this.con = con;
    }

    public boolean insert(Order model) {
        boolean res = false;
        try {
            query = "INSERT INTO orders (p_id, u_id, o_quantity, o_date) VALUES (?, ?, ?, ?)";
            ps = this.con.prepareStatement(query);
            ps.setInt(1, model.getId());
            ps.setInt(2, model.getUid());
            ps.setInt(3, model.getQuantity());
            ps.setString(4, model.getDate());

            int result = ps.executeUpdate();
            if (result > 0) {
                res = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources (PreparedStatement, ResultSet) here if necessary
        }
        return res;
    }

    public List<Order> orders(int id) {
        List<Order> ord = new ArrayList<>();
        try {
            query = "SELECT * FROM orders WHERE p_id=?";
            ps = this.con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                ProductDao dao = new ProductDao(this.con);
                int pid = rs.getInt("p_id");

                Products prod = dao.getSingleProduct(pid);
                order.setOrder_id(rs.getInt("o_id"));
                order.setId(pid);
                order.setName(prod.getName());
                order.setCategory(prod.getCategory());
                order.setPrice(prod.getPrice() * rs.getInt("o_quantity"));
                order.setQuantity(rs.getInt("o_quantity"));
                order.setDate(rs.getString("o_date"));
                ord.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources (PreparedStatement, ResultSet) here if necessary
        }
        return ord;
    }
}
