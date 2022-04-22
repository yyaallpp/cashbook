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
import vo.Member;

public class CashbookDao {
	// cashbook 수정 및 hashtag 수정(memo 수정 가능성 있으니)
	public int updateCashbook(Cashbook cashbook, List<String> hashtag, String sessionMemberId) {
		// cashbook을 update후 memo안의 hashtag도 수정해야함
		// hashtag를 update를 하려했으나 그 전의 hashtag가 남아서 hashtag 순위에 오류를 줌 -> hashtag를 삭제 후
		// 다시 생성으로 하는 형식
		int row = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false); // 자동 커밋 해제
			// cashbook update
			String sql = "UPDATE cashbook SET cash_date = ?, kind = ?, cash = ?, memo = ?, update_date = NOW() where cashbook_no = ? and member_id= ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cashbook.getCashDate());
			stmt.setString(2, cashbook.getKind());
			stmt.setInt(3, cashbook.getCash());
			stmt.setString(4, cashbook.getMemo());
			stmt.setInt(5, cashbook.getCashbookNo());
			stmt.setString(6, sessionMemberId);
			System.out.println(cashbook.getCashbookNo() + " <-- cashbookNo CashbookDao UpdateCashbook delete hashtag");
			stmt.executeUpdate();

			// delete hashtag
			String sql2 = "DELETE FROM hashtag WHERE cashbook_no = ?";
			PreparedStatement stmt2 = conn.prepareStatement(sql2);
			System.out.println(cashbook.getCashbookNo() + " <-- cashbookNo CashbookDao UpdateCashbook delete hashtag");
			stmt2.setInt(1, cashbook.getCashbookNo());
			row = stmt2.executeUpdate();
			if (row == 1) {
				System.out.println("updateHashcode 삭제 성공 !");
			} else {
				System.out.println("updateHashcode 삭제 실패...");
			}

			// insert hashcode
			String sql3 = "INSERT INTO hashtag(cashbook_no, tag) VALUES(?,?)";
			PreparedStatement stmt3 = null;
			for (String h : hashtag) {
				stmt3 = conn.prepareStatement(sql3);
				stmt3.setInt(1, cashbook.getCashbookNo());
				stmt3.setString(2, h);
				stmt3.executeUpdate();
			}
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
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
		return row;
	}

	// cashbook 삭제 및 해시태그 삭제
	public int deleteCashbook(int cashbookNo,String sessionMemberId) {
		int row = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// cashbook삭제
			/*
			 * String sql = " DELETE c,h FROM cashbook c" + " INNER JOIN hashtag h " +
			 * " WHERE c.cashbook_no = ? AND c.cashbook_no = h.cashbook_no"; --> inner
			 * join으로 해서 한번에 삭제 가능
			 */
			// hashtag 삭제
			conn = DBUtil.getConnection(); // DB접속
			conn.setAutoCommit(false); // 자동커밋 해제
			String sql2 = "DELETE FROM hashtag WHERE cashbook_no = ? ";
			PreparedStatement stmt2 = null;
			stmt2 = conn.prepareStatement(sql2);
			stmt2.setInt(1, cashbookNo);
			stmt2.executeUpdate();
			row = stmt2.executeUpdate();
			System.out.println(cashbookNo + row + " <-- cashbookNo, row deleteCashbook");
			if (row == 0) {
				System.out.println("hashtag 삭제실패");
			} else if (row == 1) {
				System.out.println("hashtag 삭제성공");
			}

			String sql = "DELETE FROM cashbook WHERE cashbook_no = ?  AND member_id = ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cashbookNo);
			stmt.setString(2, sessionMemberId);
			row = stmt.executeUpdate();
			System.out.println(cashbookNo + row + " <-- cashbookNo, row deleteCashbook");
			if (row == 0) {
				System.out.println("cashbook 삭제실패");
			} else if (row == 1) {
				System.out.println("cashbook 삭제성공");
			}

			conn.commit(); // 수동 커밋
		} catch (Exception e) {
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
	public Cashbook selectCashbook(int cashbookNo, String sessionMemberId) {
		Cashbook cashbook = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT cash_date cashDate, kind, cash, memo, create_date createDate FROM cashbook WHERE cashbook_no = ? AND member_id = ? ";

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cashbookNo);
			stmt.setString(2, sessionMemberId);
			rs = stmt.executeQuery();

			if (rs.next()) {
				cashbook = new Cashbook();
				cashbook.setCashbookNo(cashbookNo);
				cashbook.setCashDate(rs.getString("cashDate"));
				cashbook.setKind(rs.getString("kind"));
				cashbook.setCash(rs.getInt("cash"));
				cashbook.setMemo(rs.getString("memo"));
				cashbook.setCreateDate(rs.getString("createDate"));
			}

		} catch (Exception e) {
			try {
				conn.rollback();
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
		return cashbook;
	}

	// cashbook삽입 메서드
	public void insertCashbook(Cashbook cashbook, List<String> hashtag, String sessionMemberId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false); // 자동커밋을 해제
			String sql = "INSERT INTO cashbook(cash_date, kind, cash, memo, member_id,update_date, create_date) VALUES(?,?,?,?,?,NOW(),NOW())";

			// insert와 select를 실행 -> 입력하자마자 select함 -> 방금 생성된 row의 키값 ex) select 방금 입력한
			// cashbook_no from cashbook 을 실행함
			stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, cashbook.getCashDate());
			stmt.setString(2, cashbook.getKind());
			stmt.setInt(3, cashbook.getCash());
			stmt.setString(4, cashbook.getMemo());
			stmt.setString(5, sessionMemberId);
			stmt.executeUpdate(); // insert를 실행
			rs = stmt.getGeneratedKeys(); // select를 실행 -> select 방금 입력한 cashbook_no from cashbook
			int cashbookNo = 0;
			if (rs.next()) {
				cashbookNo = rs.getInt(1);
			}
			// hashtag를 저장하는 코드
			PreparedStatement stmt2 = null;
			for (String h : hashtag) {
				String sql2 = "INSERT INTO hashtag(cashbook_no, tag) VALUES(?,?)";
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

	// member_id추가해야됨
	// cashbook리스트 보여주기
	public List<Map<String, Object>> selectCashbookListByMonth(int year, int month,String sessionMemberId) {
		List<Map<String, Object>> list = new ArrayList<>();
		// SELECT cashbook_no cashbookNo, DAY(cash_date) day, kind, cash, LEFT(memo,5)
		// memo FROM cashbook WHERE YEAR(cash_date) =? AND MONTH(cash_date) = ? ORDER BY
		// cash_date ASC
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT cashbook_no cashbookNo, DAY(cash_date) day, kind, cash, LEFT(memo,5) memo FROM cashbook WHERE YEAR(cash_date) =? AND MONTH(cash_date) = ? AND member_id = ? ORDER BY DAY(cash_date) ASC, kind ASC";

		try {
			// DB접속
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			// ? 값 채우기
			stmt.setInt(1, year);
			stmt.setInt(2, month);
			stmt.setString(3, sessionMemberId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Map<String, Object> m = new HashMap<String, Object>();
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
