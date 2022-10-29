package shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import shop.bean.CartBean;
import shop.bean.CustomerBean;
import shop.bean.ItemBean;

public class PurchaseDAO {
	private String url = "jdbc:postgresql:intern";
	private String user = "postgres";
	private String password = "postgres";
	
	public PurchaseDAO() throws DAOException{
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("ドライバの登録に失敗しました");
		}
	}
	public void saveInfo(CartBean cart, String name, String address) throws DAOException{
		String sql = "INSERT INTO purchases (items_id,customers_id,name,address,created_at) VALUES(?,?,?,?,CURRENT_TIMESTAMP)";
		String getcustomerIdsql = "SELECT * FROM customers WHERE name = ?";
		CustomerBean customerbean = new CustomerBean();
		try(
				Connection con = DriverManager.getConnection(url,user,password);
				PreparedStatement st = con.prepareStatement(getcustomerIdsql);){
				st.setString(1, name);
				ResultSet rs  = st.executeQuery();
				if(rs.next()) {//複数人でないのでif
					customerbean.setId(rs.getInt("id"));
				}
				st.close();
				con.close();
				}catch(SQLException e) {
					e.printStackTrace();
					throw new DAOException("レコードの操作に失敗しました。");
			}
		try(
				Connection con2 = DriverManager.getConnection(url,user,password);
				PreparedStatement st2 = con2.prepareStatement(sql);){
				List<ItemBean> items = cart.getItems();
				
				for(ItemBean  item : items) {
					st2.setInt(1, item.getId());
					st2.setInt(2, customerbean.getId());
					st2.setString(3, name);
					st2.setString(4, address);
					st2.executeUpdate();
				}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
	
	}

}
}
