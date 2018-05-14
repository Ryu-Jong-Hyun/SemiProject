package semi.one.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import semi.one.dto.ProjectDTO;

public class ProjectDAO {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	/*DB연동*/
	public ProjectDAO() {
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
	
	/*김응주 - 테스트용 로그인*/
	public boolean login(String id, String pw) {
		String sql = "SELECT id FROM member WHERE id=? AND pw=?";
		System.out.println("로그인 DAO진입");
		boolean success = false;
		try {			
			ps = conn.prepareStatement(sql);//prepareStatement 준비
			//? 대응
			ps.setString(1, id);
			ps.setString(2, pw);			
			rs = ps.executeQuery();//쿼리 실행			
			success = rs.next();//resultSet 으로 부터 결과 확인
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			resClose();
		}
		return success;
	}





	/*김응주 - 마이페이지(기획자)*/
	public boolean mypage(String loginId) {
		boolean success = false;
		String sql = "SELECT * FROM project WHERE pd_id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginId);
			rs = ps.executeQuery();
			success = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
		}
		return success;
		
	}
	
	/*김응주 - 마이페이지(관리자)*/
	public boolean mypageAdmin(String loginId) {
		System.out.println("관리자 마이페이지 쿼리");
		boolean success = false;
		String sql = "SELECT * FROM member WHERE id=? AND power='1'";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, loginId);
			rs=ps.executeQuery();
			success = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return success;
	}

	/*윤영 - 성공한 프로젝트 리스트 요청*/
	public ArrayList<ProjectDTO> successList1() {
		//투자자 페이지
		ArrayList<ProjectDTO> successList1 = new ArrayList<>();
		String sql = "SELECT * FROM project WHERE prj_state='성공'";
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				ProjectDTO dto = new ProjectDTO();
				dto.setPrj_no(rs.getInt("prj_no"));
				dto.setPrj_title(rs.getString("prj_title"));
				dto.setPrj_photo(rs.getString("prj_photo"));
				
				successList1.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return successList1;
	}

	public ArrayList<ProjectDTO> successList2() {
		//기획자 페이지
		ArrayList<ProjectDTO> successList2 = new ArrayList<>();
		String sql = "SELECT * FROM project WHERE prj_state='성공'";
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				ProjectDTO dto = new ProjectDTO();
				dto.setPrj_no(rs.getInt("prj_no"));
				dto.setPrj_title(rs.getString("prj_title"));
				dto.setPrj_photo(rs.getString("prj_photo"));
				
				successList2.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return successList2;
	}
}
