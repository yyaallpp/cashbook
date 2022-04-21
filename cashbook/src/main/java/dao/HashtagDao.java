package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DBUtil;
import vo.Cashbook;

public class HashtagDao {	
	// kind별 순위
	public List<Map<String, Object>> hashtagKindRankList(String kind) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT t.tag tag, t.cnt cnt, RANK() over(ORDER BY t.cnt DESC) rank "
					+ " FROM (SELECT t.tag tag, COUNT(*) cnt "
					+ "	FROM hashtag t INNER JOIN cashbook c "
					+ "	ON t.cashbook_no = c.cashbook_no "
					+ " WHERE c.kind = ? "
					+ " GROUP BY t.tag) t ";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, kind);
			rs = stmt.executeQuery();
	         while(rs.next()) {
	             Map<String, Object> map = new HashMap<>();
	             map.put("rank", rs.getInt("rank"));
	             map.put("tag", rs.getString("tag"));
	             map.put("cnt", rs.getInt("cnt"));
	             list.add(map);
	         }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	// date별 순위
	public List<Map<String, Object>> hashtagDateRankList(String cashDate) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT h.tag tag, COUNT(*) cnt, RANK() over(ORDER BY cnt DESC) rank "
					+ " FROM hashtag h INNER JOIN cashbook c"
					+ "	ON h.cashbook_no = c.cashbook_no"
					+ " WHERE c.cash_date=?"
					+ " GROUP BY tag";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cashDate);
			rs = stmt.executeQuery();
	         while(rs.next()) {
	             Map<String, Object> map = new HashMap<>();
	             map.put("tag", rs.getString("tag"));
	             map.put("cnt", rs.getInt("cnt"));
	             map.put("ranking", rs.getInt("rank"));
	             list.add(map);
	         }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}	
	// hashtag 상세보기
	public List<Map<String, Object>> selectHashtagOne(String tag) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT h.tag tag, c.cash_date cashDate, c.kind kind, c.cash cash, c.memo memo "
					+ " FROM cashbook c INNER JOIN hashtag h "
					+ "	ON c.cashbook_no=h.cashbook_no "
					+ " WHERE h.tag=? "
					+ " ORDER BY c.cash_date ";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tag);
			rs = stmt.executeQuery();
	         while(rs.next()) {
	             Map<String, Object> map = new HashMap<>();
	             map.put("tag", rs.getString("tag"));
	             map.put("cashDate", rs.getString("cashDate"));
	             map.put("kind", rs.getString("kind"));
	             map.put("memo", rs.getString("memo"));
	             list.add(map);
	         }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	// 리스트보기
	public List<Map<String,Object>> selectTagRankList() {
		List<Map<String,Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT t.tag, t.cnt, RANK() over(ORDER BY t.cnt DESC) rank "
				+ "	FROM "
				+ "	(SELECT tag, COUNT(*) cnt "
				+ "	FROM hashtag "
				+ "	GROUP BY tag) t" ;		
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("tag", rs.getString("tag"));
				map.put("cnt", rs.getInt("t.cnt"));
				map.put("rank", rs.getInt("rank"));
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
