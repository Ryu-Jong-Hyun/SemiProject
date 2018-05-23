package semi.one.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import semi.one.dto.InquireDTO;

public class InquireDAO {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	/*DB연동*/
	public InquireDAO() {
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
	
	/*윤영 - 나의 문의 리스트 요청*/
	public ArrayList<InquireDTO> myInquireList(String loginId) {
		ArrayList<InquireDTO> myInquireList = new ArrayList<>();
		String sql = "SELECT * FROM inquire WHERE id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginId);
			rs = ps.executeQuery();
			while(rs.next()) {
				InquireDTO dto = new InquireDTO();
				dto.setInq_no(rs.getInt("inq_no"));
				dto.setInq_cat(rs.getString("inq_cat"));
				dto.setInq_title(rs.getString("inq_title"));
				dto.setId(rs.getString("id"));
				dto.setInq_state(rs.getString("inq_state"));
				dto.setInq_date(rs.getDate("inq_date"));
				
				myInquireList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		 return myInquireList;
	}

	/*윤영 - (관리자)문의 리스트 요청*/
	public ArrayList<InquireDTO> inquireList() {
		ArrayList<InquireDTO> inquireList = new ArrayList<>();
		String sql = "SELECT * FROM inquire WHERE inq_state='답변대기'ORDER BY inq_date ASC";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				InquireDTO dto = new InquireDTO();
				dto.setInq_no(rs.getInt("inq_no"));
				dto.setInq_cat(rs.getString("inq_cat"));
				dto.setInq_title(rs.getString("inq_title"));
				dto.setId(rs.getString("id"));
				dto.setInq_state(rs.getString("inq_state"));
				dto.setInq_date(rs.getDate("inq_date"));
				
				inquireList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}	
		return inquireList;
	}

	/*윤영 - (관리자)문의 상세보기 요청*/
	public InquireDTO inqDetail(int inq_no) {
		InquireDTO dto = new InquireDTO();
		String sql = "SELECT * FROM inquire WHERE inq_no = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, inq_no);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setInq_title(rs.getString("inq_title"));
				dto.setId(rs.getString("id"));
				dto.setInq_cat(rs.getString("inq_cat"));
				dto.setInq_content(rs.getString("inq_content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return dto;
	}

	/*윤영 - 문의하기 등록 요청*/
	public int inquire(InquireDTO dto, String loginId) {
		int success = 0;
		
		String sql = "INSERT INTO inquire(inq_no, id, inq_cat, inq_title, inq_content, inq_state, inq_date) VALUES(inq_seq.NEXTVAL,?,?,?,?,'답변대기',SYSDATE)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginId);//문의를 하는 현재로그인한 ID
			ps.setString(2, dto.getInq_cat());
			ps.setString(3, dto.getInq_title());
			ps.setString(4, dto.getInq_content());
			
			success = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return success;
	}
	
	/*윤영 - 답변 상태 업데이트*/
	public int staUpadte(int inq_no) {
		int success = 0;
		
		String sql = "update inquire set inq_state='답변완료' where inq_no=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, inq_no);//문의를 하는 현재로그인한 ID
			success = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return success;
		
	}
}
