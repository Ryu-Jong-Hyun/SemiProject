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
	public ArrayList<QuestionDTO> qnaList(String prj_no) {
		ArrayList<QuestionDTO> qnaList = new ArrayList<>();
		String sql="SELECT * FROM question WHERE prj_no=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(prj_no));
			rs = ps.executeQuery();
			while(rs.next()) {
				QuestionDTO dto = new QuestionDTO();
				dto.setQus_no(rs.getInt("qus_no"));
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
	
	/*윤영 - (상세페이지)문의하기 등록 요청*/
	public int question(QuestionDTO dto, String loginId, String prj_no) {
		int success =0;
		
		String sql = "INSERT INTO question(qus_no, prj_no, id, qus_cat, qus_title, qus_content, qus_state, qus_date) VALUES(qus_seq.NEXTVAL,?,?,?,?,?,'답변대기',SYSDATE)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(prj_no));
			ps.setString(2, loginId);
			ps.setString(3, dto.getQus_cat());
			ps.setString(4, dto.getQus_title());
			ps.setString(5, dto.getQus_content());
			
			success=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	/*윤영 - (상세페이지)문의 상세보기 요청*/
	public QuestionDTO qnaDetail(int qus_no) {
		QuestionDTO dto = new QuestionDTO();
		String sql = "SELECT * FROM question WHERE qus_no=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qus_no);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setQus_no(qus_no);
				dto.setQus_title(rs.getString("qus_title"));
				dto.setId(rs.getString("id"));
				dto.setQus_content(rs.getString("qus_content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return dto;
	}

	/*윤영 - (상세페이지)문의 수정 요청*/
	public Integer qnaUpdate(String qTitle, int qno, String qContent) {
		int success=0;
		
		String sql="UPDATE question SET qus_title=?, qus_content=? WHERE qus_no=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, qTitle);
			ps.setString(2, qContent);
			ps.setInt(3, qno);
			success = ps.executeUpdate();
			System.out.println(success);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return success;
	}
	
	/*윤영 - (상세페이지)문의 삭제 요청*/
	public int delete(int qno) {
		int success = 0;
		String sql = "DELETE FROM question WHERE qus_no=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qno);
			success = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		
		return success;
	}
	
	/*윤영 - 답변 성공시 상태 update*/
	public int staUpadte(int qno) {
		int success = 0;
		String sql = "UPDATE question SET qus_state='답변 완료' WHERE qus_no=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qno);//문의를 하는 현재로그인한 ID
			success = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		
		return success;
	}
}
