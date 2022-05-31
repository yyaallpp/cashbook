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
import vo.Member;

public class MemberDao {
	// 회원가입
	public void insertMemeber(Member member) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO member(member_id, member_pw, phone, email, create_date, update_date) values(?,PASSWORD(?),?,?,NOW(),NOW() )";
		
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			stmt.setString(3, member.getPhone());
			stmt.setString(4, member.getEmail());
			stmt.setString(5, member.getCreateDate());
			stmt.setString(6, member.getUpdateDate());
			int row = stmt.executeUpdate();
			if(row == 1) {
				System.out.println("member 입력");
			} else {
				System.out.println("입력실패");
			}	
		} catch(Exception e){
			e.printStackTrace();	
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 회원비밀번호 수정 -> 나중에 Member에 비밀번호 말고 추가로 올수 있어서 List로함
	public List<Member> updateMemberPw(String sessionMemberId,String memberPw) {
		List<Member> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "UPDATE member SET member_pw = PASSWORD(?) WHERE member_id = ? ";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberPw);
			stmt.setString(2, sessionMemberId);
			rs = stmt.executeQuery();
			System.out.println(sql+ " <-- selectMemberOne");
			Member m = null;
			if(rs.next()) {
				m = new Member();
				m.setMemberId(rs.getString("memberId"));
				m.setMemberPw(rs.getString("memberPw"));
				m.setCreateDate(rs.getString("createDate"));
				list.add(m);
			}
		} catch(Exception e) {
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
	
	// 회원의 현재 비밀번호가 일치한지 확인하는 메서드
	 public String compareMemberPw(String sessionMemberId, String memberPw) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String result = "";
		String sql = "SELECT member_pw memberPw FROM member WHERE member_id = ? AND member_pw = PASSWORD(?)";		
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sessionMemberId);
			stmt.setString(2, memberPw);
			rs = stmt.executeQuery();
			System.out.println(sql+ " <-- selectMemberOne");
			if(rs.next()) {
				result = rs.getString("memberPw");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(result + " <-- result compareMemberPw() MemberDao");
		
		return result;
	 }
	
	// 회원탈퇴 -> member,cashbook도 지워야함
	public void deleteMember(String sessioMemberId, String memberPw) {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
		String sql = "DELETE FROM cashbook WHERE member_id =? ";
		String sql2 = "DELETE FROM member WHERE member_id = ? AND member_pw = PASSWORD(?)";
		
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false); // 자동커밋 해제
			// cashbook삭제
			stmt = conn.prepareStatement(sql); 
			stmt.setString(1, sessioMemberId);
			stmt.executeUpdate();
			// member삭제
			stmt2 = conn.prepareStatement(sql2);
			stmt2.setString(1, sessioMemberId);
			stmt2.setString(2, memberPw);
			stmt2.executeUpdate();
			
			conn.commit();
			
		} catch(Exception e) {
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
	}
	
	// 회원정보
	public Member selectMemberOne(String sessionMemberId){
		Member m = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT member_id memberId, member_pw memberPw, phone, email, create_date createDate FROM member WHERE member_id = ? ";
		
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sessionMemberId);
			rs = stmt.executeQuery();
			System.out.println(sql+ " <-- selectMemberOne");
			if(rs.next()) {
				m = new Member();
				m.setMemberId(rs.getString("memberId"));
				m.setMemberPw(rs.getString("memberPw"));
				m.setPhone(rs.getString("phone"));
				m.setEmail(rs.getString("email"));
				m.setCreateDate(rs.getString("createDate"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return m;
	}
	
	// 회원정보 수정
	public void updateMember(Member member,String sessionMemberId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "UPDATE member SET phone = ?, email = ?, update_date = NOW() where member_id = ?";
		
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getPhone());
			stmt.setString(2, member.getEmail());
			stmt.setString(3,sessionMemberId);
			stmt.executeUpdate();	
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
	}
	
	// 로그인
	public String selectMemberByIdPw(Member member) {
		String memberId = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT member_id memberId FROM member WHERE member_id = ? AND member_pw=PASSWORD(	?) ";
		
		try {
			conn = DBUtil.getConnection();
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			rs = stmt.executeQuery();
			if(rs.next()) {
				memberId = rs.getString("memberId");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return memberId;
	}
}
