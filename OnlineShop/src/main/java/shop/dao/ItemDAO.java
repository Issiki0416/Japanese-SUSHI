package shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shop.bean.ItemBean;

public class ItemDAO {
	private String url = "jdbc:postgresql:intern";
	private String user = "postgres";
	private String password = "postgres";
	
	public ItemDAO() throws DAOException{
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("ドライバの登録に失敗しました");
		}
	}
	
	public List<ItemBean> findAll() throws DAOException{
		String sql = "SELECT * FROM items";
		try(
				Connection con = DriverManager.getConnection(url,user,password);
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs  = st.executeQuery();){
				List<ItemBean> list = new ArrayList<ItemBean>();
				while(rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					int price = rs.getInt("price");
					ItemBean bean = new ItemBean(id,name, price);
					list.add(bean);
				}
				return list;
			}catch(SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました");
			}
	}
	public ItemBean findById(int id) throws DAOException{
		String sql = "SELECT * FROM items WHERE id = ?";
		try(
				Connection con = DriverManager.getConnection(url,user,password);
				PreparedStatement st = con.prepareStatement(sql);){
			    st.setInt(1, id);
				ResultSet rs = st.executeQuery();
				if(rs.next()) {
					String name = rs.getString("name");
					int price = rs.getInt("price");
					ItemBean bean = new ItemBean(id,name,price);
					
					return bean;
				} else {
					return null;
				}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました");
		}
	}
}
