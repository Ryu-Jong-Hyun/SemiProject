package semi.one.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import semi.one.dto.ReplyDTO;

public class ReplyDAO {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	/*DB연동*/
	public ReplyDAO() {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	/*자원반납*/
	private void resClose() {
		try {
			if(rs != null) {
				rs.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(conn != null) {
				conn.close();
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	
	/*윤영 - (관리자)문의 답변 등록 요청*/
	public int reply(ReplyDTO dto) {
		int success = 0;
		
		String sql = "INSERT INTO reply(rep_no, inq_no, rep_title, rep_content, rep_date) VALUES(rep_seq.NEXTVAL,?,?,?,SYSDATE)";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dto.getInq_no());		
			ps.setString(2, dto.getRep_title());
			ps.setString(3, dto.getRep_content());
			
			success = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return success;
	}
}
