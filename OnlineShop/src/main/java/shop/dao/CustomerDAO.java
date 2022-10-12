package shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import shop.bean.CustomerBean;

public class CustomerDAO {
	private String url = "jdbc:postgresql:intern";
	private String user = "postgres";
	private String password = "postgres";
	CustomerBean customerbean=null;
	
	public CustomerDAO() throws DAOException{
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("ドライバの登録に失敗しました");
		}
	}
	public CustomerBean loginCheck(String name, String password) throws DAOException {
		String sql = "SELECT * FROM customer WHERE name = ? AND password = ?";
		try(
			Connection con = DriverManager.getConnection(url,user,password);
			PreparedStatement st = con.prepareStatement(sql);){
			st.setString(1,name);
			st.setString(2, password);
			ResultSet rs  = st.executeQuery();
			if(rs.next()) {//複数人でないのでif
				customerbean = new CustomerBean(rs.getString("name"),rs.getString("password"));
			}
			st.close();
			con.close();
			return customerbean;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました");
		}
	}

}
