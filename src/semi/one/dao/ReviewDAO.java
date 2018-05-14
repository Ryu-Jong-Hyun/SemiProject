package semi.one.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import semi.one.dto.ReviewDTO;

public class ReviewDAO {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	/*DB연동*/
	public ReviewDAO() {
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

	/*윤영 - 프로젝트 후기 리스트 요청*/
	public ArrayList<ReviewDTO> reviewList(int prj_no) {
		ArrayList<ReviewDTO> reviewList = new ArrayList<>();
		String sql = "SELECT * FROM review WHERE prj_no=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, prj_no);
			rs = ps.executeQuery();
			while(rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setRev_no(rs.getInt("rev_no"));
				dto.setId(rs.getString("id")); //후기작성한 id
				dto.setRev_title(rs.getString("rev_title"));
				dto.setRev_date(rs.getDate("rev_date"));
				
				reviewList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return reviewList;
	}

	/*윤영 - 후기등록 요청*/
	public int review(ReviewDTO dto, String loginId) {
		int success = 0;
		String sql = "INSERT INTO review (rev_no, prj_no, pd_id, id, rev_title, rev_content, rev_date) VALUES(rev_seq.NEXTVAL,?,?,?,?,?,SYSDATE)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dto.getPrj_no());
			ps.setString(2, dto.getPd_id());//프로젝트 기획자 아이디
			ps.setString(3, loginId);//현재로그인한 아이디
			ps.setString(4, dto.getRev_title());
			ps.setString(5, dto.getRev_content());
			System.out.println(dto.getPd_id());
			
			success = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return success;
	}

	/*윤영 - 후기 상세보기 요청
	public ReviewDTO reviewDetail(int rev_no) {
		ReviewDTO dto = new ReviewDTO();
		String sql = "SELECT * FROM review WHERE rev_no=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, rev_no);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}*/
}
