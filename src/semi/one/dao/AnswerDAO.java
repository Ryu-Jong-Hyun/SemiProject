package semi.one.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import semi.one.dto.AnswerDTO;
import semi.one.dto.InquireDTO;
import semi.one.dto.QuestionDTO;

public class AnswerDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	/*DB연동*/
	public AnswerDAO() {
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

	/*윤영 - 문의답변 요청*/
	public Integer qnaAnswer(String qTitle, int qno, String ansContent) {
		int success = 0;
		
		String sql = "INSERT INTO answer(ans_no, qus_no, ans_title, ans_content, ans_date) VALUES(ans_seq.NEXTVAL,?,?,?,SYSDATE)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qno);
			ps.setString(2, qTitle);
			ps.setString(3, ansContent);
			
			success = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return success;
	}

	/*윤영 - 답변 내용 요청*/
	public AnswerDTO answerDetail(int qus_no) {
		AnswerDTO dto = new AnswerDTO();
		String sql = "SELECT * FROM answer WHERE qus_no=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qus_no);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setAns_content(rs.getString("ans_content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return dto;
	}

}
