package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.JDBCUtil;

public class MemberDAO {
	private static MemberDAO dao=new MemberDAO();
	private MemberDAO() {}
	public static MemberDAO getInstance() {
		return dao;
	}	
//--------------------------------로그인 메소드 -반환 id,pwd,파라메타  MemberVO()	
	public MemberVO login(String id,String pwd) {
		MemberVO vo=null;
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement("select name,grade from membertbl where id=? and pwd=?");
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs=pstmt.executeQuery();
			if(rs.next()) {// 로그인 성공
				vo=new MemberVO(id,rs.getString("name"),rs.getString("grade"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return vo;
	}
}

