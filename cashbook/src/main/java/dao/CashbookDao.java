package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CashbookDao {
	public List<Map<String,Object>> selectCashbookListByMonth(int year, int month){
		List<Map<String,Object>> list = new ArrayList<>();
		// SELECT cashbook_no cashbookNo, DAY(cash_date) day, kind, cash FROM cashbook WHERE YEAR(cash_date) =? AND MONTH(cash_date) = ? ORDER BY cash_date ASC
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT cashbook_no cashbookNo, DAY(cash_date) day, kind, cash FROM cashbook WHERE YEAR(cash_date) =? AND MONTH(cash_date) = ? ORDER BY cash_date ASC";
		
		try {
			// DB접속
			Class.forName("org.mariadb.jdbc.Driver");
			String dburl = "jdbc:mariadb://localhost:3306/cashbook";
			String dbpw = "java1234";
			String dbuser = "root";
			conn = DriverManager.getConnection(dburl,dbuser,dbpw);
			stmt = conn.prepareStatement(sql);
			// ? 값 채우기
			stmt.setInt(1, year);
			stmt.setInt(2, month);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String,Object> m = new HashMap<String,Object>();
				m.put("cashbookNo", rs.getInt("cashbookNo"));
				m.put("day", rs.getString("day"));
				m.put("kind", rs.getString("kind"));
				m.put("cash", rs.getInt("cash"));
				list.add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
}
