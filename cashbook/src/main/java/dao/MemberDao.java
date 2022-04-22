package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBUtil;
import vo.Member;

public class MemberDao {
	// 회원가입
	// 회원수정
	// 회원탈퇴
	// 회원정보
	
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
