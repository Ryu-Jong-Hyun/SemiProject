package semi.one.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import semi.one.dto.QuestionDTO;

public class QuestionDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	/*DB연동*/
	public QuestionDAO() {
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
	
	/*윤영 - (프로젝트 상세페이지)QnA 리스트 요청*/
	public ArrayList<QuestionDTO> qnaList() {
		ArrayList<QuestionDTO> qnaList = new ArrayList<>();
		String sql="SELECT * FROM question";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				QuestionDTO dto = new QuestionDTO();
				dto.setQus_cat(rs.getString("qus_cat"));
				dto.setQus_title(rs.getString("qus_title"));
				dto.setQus_state(rs.getString("qus_state"));
				dto.setId(rs.getString("id"));
				dto.setQus_date(rs.getDate("qus_date"));
				
				qnaList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return qnaList;
	}
}
