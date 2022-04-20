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

import javax.servlet.RequestDispatcher;

import util.DBUtil;
import vo.Cashbook;

public class CashbookDao {
	// cashbook 삭제 및 해시태그 삭제
	public int deleteCashbook(int cashbookNo) {
		int row = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// cashbook삭제
			/* String sql = " DELETE c,h FROM cashbook c"
			+ " INNER JOIN hashtag h "
			+ " WHERE c.cashbook_no = ? AND c.cashbook_no = h.cashbook_no"; --> inner join으로 해서 한번에 삭제 가능
			*/
			String sql = "DELETE FROM cashbook WHERE cashbook_no = ?"; 
			conn = DBUtil.getConnection(); // DB접속
			conn.setAutoCommit(false); // 자동커밋 해제
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cashbookNo);
			row = stmt.executeUpdate();
			
			System.out.println(row + " " + cashbookNo);
			if(row == 0) {
				System.out.println("cashbook 삭제실패");
			} else if (row == 1) {
				System.out.println("cashbook 삭제성공");
			}
			// hashcode 삭제
			String sql2 = "DELETE FROM hashtag WHERE cashbook_no = ?";
			PreparedStatement stmt2 = null;
			stmt2 = conn.prepareStatement(sql2);
			stmt2.setInt(1, cashbookNo);
			row = stmt2.executeUpdate();
			if(row == 0) {
				System.out.println("hashtag 삭제실패");
			} else if (row == 1) {
				System.out.println("hashtag 삭제성공");
			}			
			conn.commit(); // 수동 커밋
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	} 

	// cashbook 상세열람
	public Cashbook selectCashbook(int cashbookNo) {
		Cashbook cashbook = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT cash_date cashDate, kind, cash, memo, create_date createDate FROM cashbook WHERE cashbook_no = ?";
		
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cashbookNo);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				cashbook = new Cashbook();
				cashbook.setCashbookNo(cashbookNo);
				cashbook.setCashDate(rs.getString("cashDate"));
				cashbook.setKind(rs.getString("kind"));
				cashbook.setCash(rs.getInt("cash"));
				cashbook.setMemo(rs.getString("memo"));
				cashbook.setCreateDate(rs.getString("createDate"));
			}
			
		} catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cashbook;
	}
	
	// cashbook삽입 메서드
	public void insertCashbook(Cashbook cashbook, List<String> hashtag) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false); // 자동커밋을 해제
			String sql="INSERT INTO cashbook(cash_date, kind, cash, memo, update_date, create_date) VALUES(?,?,?,?,NOW(),NOW())";
			
			// insert와 select를 실행 -> 입력하자마자 select함 -> 방금 생성된 row의 키값  ex) select 방금 입력한 cashbook_no from cashbook 을 실행함
			stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); 
			stmt.setString(1, cashbook.getCashDate());
			stmt.setString(2, cashbook.getKind());
			stmt.setInt(3, cashbook.getCash());
			stmt.setString(4, cashbook.getMemo());
			stmt.executeUpdate(); // insert를 실행
			rs = stmt.getGeneratedKeys(); // select를 실행 -> select 방금 입력한 cashbook_no from cashbook
			int cashbookNo = 0;
			if(rs.next()) {
				cashbookNo = rs.getInt(1);
			}			
			// hashtag를 저장하는 코드
			PreparedStatement stmt2 = null;
			for(String h : hashtag) {
				String sql2 = "INSERT INTO hashtag(cashbook_no, tag, create_date) VALUES(?,?,NOW())";		
				stmt2 = conn.prepareStatement(sql2);
				stmt2.setInt(1, cashbookNo);
				stmt2.setString(2, h);
				stmt2.executeUpdate();
			}
			
			conn.commit(); // setAutoCommit이 false라 직접 닫아야한다.
		} catch (Exception e) {
			try {
				conn.rollback(); // 예외가 발생하면 rollback
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
		
	// cashbook리스트 보여주기
	public List<Map<String,Object>> selectCashbookListByMonth(int year, int month){
		List<Map<String,Object>> list = new ArrayList<>();
		// SELECT cashbook_no cashbookNo, DAY(cash_date) day, kind, cash, LEFT(memo,5) memo FROM cashbook WHERE YEAR(cash_date) =? AND MONTH(cash_date) = ? ORDER BY cash_date ASC
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT cashbook_no cashbookNo, DAY(cash_date) day, kind, cash, LEFT(memo,5) memo FROM cashbook WHERE YEAR(cash_date) =? AND MONTH(cash_date) = ? ORDER BY DAY(cash_date) ASC, kind ASC";
		
		try {
			// DB접속
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			// ? 값 채우기
			stmt.setInt(1, year);
			stmt.setInt(2, month);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String,Object> m = new HashMap<String,Object>();
				m.put("cashbookNo", rs.getInt("cashbookNo"));
				m.put("day", rs.getInt("day"));
				m.put("kind", rs.getString("kind"));
				m.put("cash", rs.getInt("cash"));
				m.put("memo", rs.getString("memo"));
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
