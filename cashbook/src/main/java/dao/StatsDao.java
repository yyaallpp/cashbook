package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBUtil;
import vo.Stats;

public class StatsDao {
	public void insertStats() { // <-- Listener
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		// INSERT INTO stats(day, cnt) VALUES(CURDATE(), 1)
		String sql = "INSERT INTO stats(day, cnt) VALUES(CURDATE(), 1)";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);		
			stmt.executeUpdate();
					
		} catch (SQLException e) {
			e.printStackTrace();
			
		} try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// 오늘 접속자 수를 보여주는 메서드
	public Stats selectStatsOneByNow() { // <-- Listner, Controller
		Stats stats = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		// SELECT day,cnt FROM stats WHERE DAY = CURDATE()
		String sql = "SELECT day, cnt FROM stats WHERE DAY = CURDATE()";
		
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);	
			rs = stmt.executeQuery();
			while(rs.next()) {
				stats = new Stats();
				stats.setDay(rs.getString("day"));
				stats.setCnt(rs.getInt("cnt"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return stats;
	}
	
	public void updateStatsByNow() { // <-- Listener	
		Stats stats = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		
		// UPDATE stats SET cnt = cnt+1 WHERE DAY = CURDATE()
		String sql = "UPDATE stats SET cnt = cnt+1 WHERE DAY = CURDATE()";
		
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);		
			stmt.executeUpdate();	
		} catch(Exception e){
			e.printStackTrace();
		} try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		}
	
	// 현재까지 총 몇명이 왔는지 
	public int selectStatsTotalCount() { // <-- Controller
		int cnt = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		// SELECT SUM(cnt) from stats 
		String sql = "SELECT SUM(cnt) cnt from stats ";
		
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);	
			rs = stmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}			
		} catch(Exception e){
			e.printStackTrace();
		} try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
}
