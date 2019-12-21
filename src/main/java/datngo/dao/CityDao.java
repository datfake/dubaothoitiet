package datngo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import datngo.DB.ConnectDatabase;
import datngo.model.City;


public class CityDao {
	private Connection conn;
	private Statement st;
	private ResultSet rs;

	public ArrayList<City> getCitys() {
		ArrayList<City> lists = new ArrayList<>();
		conn = ConnectDatabase.getConnection();
		String sql = "SELECT * FROM city";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				City city = new City(rs.getInt("id"), rs.getString("name"),rs.getDouble("lon"),rs.getDouble("lat"));
				lists.add(city);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDatabase.close(st, rs, conn);
		}
		return lists;
	}
}
