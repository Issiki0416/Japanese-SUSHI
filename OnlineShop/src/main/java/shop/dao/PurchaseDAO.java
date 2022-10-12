package shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import shop.bean.CartBean;
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
		String sql = "INSERT INTO purchase (product_id,product_name,product_price,customer_name,customer_address) VALUES(?,?,?,?,?)";
			
		try(
				Connection con = DriverManager.getConnection(url,user,password);
				PreparedStatement st = con.prepareStatement(sql);){
				List<ItemBean> items = cart.getItems();
				for(ItemBean  item : items) {
					st.setInt(1, item.getId());
					st.setString(2,item.getName());
					st.setInt(3, item.getPrice());
					st.setString(4, name);
					st.setString(5, address);
					st.executeUpdate();
				}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
	
	}

}
}
