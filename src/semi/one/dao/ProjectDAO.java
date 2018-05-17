package semi.one.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import semi.one.dto.ProjectDTO;
import semi.one.dto.RewardDTO;
import semi.one.dto.SponsorDTO;

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
	
	
	
	//보네 - 프로젝트 작성

	   public int prj_write(ProjectDTO prjDTO) {
	      //1. INSERT 첫번째
	      int success = 0;
	      String sql = "INSERT INTO project (prj_no, pd_id, prj_cat, prj_title, prj_photo, prj_content, prj_goal, prj_curr, prj_bank,"
	            + "prj_account, prj_due, prj_date, prj_picks, prj_state, prj_comment)"
	            + "VALUES (prj_no_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, 0, ?, ?, ?, SYSDATE, 0, '승인대기', null)";
	      
	      
	      try {
	         //1.
	         System.out.println("쿼리문 대입중");
	         ps=conn.prepareStatement(sql);
	         ps.setString(1, prjDTO.getPd_id());
	         ps.setString(2, prjDTO.getPrj_cat());
	         ps.setString(3, prjDTO.getPrj_title());
	         ps.setString(4, prjDTO.getPrj_photo());
	         ps.setString(5, prjDTO.getPrj_content());
	         ps.setLong(6, prjDTO.getPrj_goal());
	         ps.setString(7, prjDTO.getPrj_bank());
	         ps.setString(8, prjDTO.getPrj_account());
	         ps.setDate(9, prjDTO.getPrj_due()); 
	         success = ps.executeUpdate();
	         

	      } catch (SQLException e) {
	         e.printStackTrace();
	         return 0;
	      }//자원반납 나중에
	      return success;
	   }
	   //2.
	   public String prj_no(String id) {
	      String rsStr = "";
	      //쿼리 및 ps 준비
	      String sql = "SELECT A.* FROM (SELECT prj_no FROM project WHERE pd_id=? ORDER BY prj_date DESC)A WHERE ROWNUM =1";
	      try {
	         
	         ps=conn.prepareStatement(sql);
	/*         RewardDTO rwDTO = new RewardDTO();*/
	         //System.out.println("아이디 받아올수있는지 확인 : "+(String)request.getSession().getAttribute(id));
	         ps.setString(1, id);
	         

	         rs = ps.executeQuery();
	         //rs에서 값 추출
	         if(rs.next()) {
	            //dto에 담기
	            rsStr = rs.getString("prj_no");
	            
	            
	         }
	         
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	         return rsStr;
	      }//자원반납 나중에
	      return rsStr;
	   }
	   
	   
	   
	   //3.
	   public void rw_write(RewardDTO rwDTO) {//rwsuccess int로 반환하던거 지웠음
	      //INSERT
	      String sql = "INSERT INTO reward (rw_no, prj_no, rw_item, rw_min, rw_max) "
	            + "VALUES (rw_no_seq.NEXTVAL, ?, ?, ?, ?)";
	      
	      
	      try {
	         
	         System.out.println("리워드 쿼리문 만드는중");
	         ps=conn.prepareStatement(sql);
	         ps.setInt(1, rwDTO.getPrj_no());
	         ps.setString(2, rwDTO.getRw_item());//리워드이름
	         ps.setInt(3, rwDTO.getRw_min());//최소금액
	         ps.setInt(4, rwDTO.getRw_max());//최대금액
	         ps.executeUpdate();
	         

	         
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }//여러번 돌거라서 자원반납은 나중에.
	   }

	

	
	/**김응주 - 테스트용 리스트메서드 */
	public ArrayList<ProjectDTO> list() {
		
		ArrayList<ProjectDTO> list = new ArrayList<ProjectDTO>();
		try {
			String sql = "SELECT * FROM project ORDER BY prj_no DESC";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				ProjectDTO dto = new ProjectDTO();
				dto.setPrj_no(rs.getInt("prj_no"));
				dto.setPd_id(rs.getString("pd_id"));
				dto.setPrj_cat(rs.getString("prj_cat"));
				dto.setPrj_title(rs.getString("prj_title"));
				dto.setPrj_content(rs.getString("prj_content"));
				dto.setPrj_goal(rs.getInt("prj_goal"));
				dto.setPrj_curr(rs.getInt("prj_curr"));
				dto.setPrj_account(rs.getString("prj_account"));
				dto.setPrj_due(rs.getDate("prj_due"));
				dto.setPrj_date(rs.getDate("prj_date"));
				dto.setPrj_picks(rs.getInt("prj_picks"));
				dto.setPrj_state(rs.getString("prj_state"));
				dto.setPrj_url(rs.getString("prj_url"));
				dto.setPrj_bank(rs.getString("prj_bank"));
				dto.setPrj_comment(rs.getString("prj_comment"));
				dto.setPrj_photo(rs.getString("prj_photo"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return list;
	}
	
	
	/**김응주 - 프로젝트 상세보기 mvc(사진)*/
	public ProjectDTO photoDetail(String prj_no) {
		ProjectDTO dto = null;
		String sql ="SELECT * FROM project WHERE prj_no = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(prj_no));
			rs = ps.executeQuery();
			if(rs.next()) {
				dto = new ProjectDTO();
				dto.setPrj_no(rs.getInt("prj_no"));
				dto.setPrj_photo(rs.getString("prj_photo"));
			}
			String newFileName = fileNameCall(dto.getPrj_no()); 
			if(newFileName != null) {	
				dto.setPrj_photo(newFileName);		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	/**김응주 - 프로젝트 상세보기 mvc(사진) 추출*/
	private String fileNameCall(int prj_no) {
		String sql = "SELECT newFileName FROM photo WHERE prj_no = ?";
		String fileName = null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, prj_no);
			rs=ps.executeQuery();
			fileName = rs.next() ? rs.getString("newFileName") : null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	/**김응주 - 프로젝트 상세보기 mvc(리워드)*/
	public RewardDTO rewardDetail(String prj_no) {
		RewardDTO dto = null;
		String sql = "SELECT * FROM reward WHERE prj_no =?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(prj_no));
			rs = ps.executeQuery();
			if(rs.next()) {
				dto = new RewardDTO();
				dto.setRw_no(rs.getInt("rw_no"));
				dto.setPrj_no(rs.getInt("prj_no"));
				dto.setRw_item(rs.getString("rw_item"));
				dto.setRw_min(rs.getInt("rw_min"));
				dto.setRw_max(rs.getInt("rw_max"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return dto;
	}
	
		


	/**김응주 - 투자자 목록확인*/
	public ArrayList<SponsorDTO> sponList(String prj_no) {
		ArrayList<SponsorDTO> list = new ArrayList<SponsorDTO>();
		try {
			String sql = "SELECT * FROM sponsor WHERE prj_no = ? AND spon_don > 1";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(prj_no));
			rs = ps.executeQuery();
			while(rs.next()) {
				SponsorDTO dto = new SponsorDTO();
				dto.setId(rs.getString("id"));
				dto.setSpon_don(rs.getInt("spon_don"));
				dto.setSpon_date(rs.getDate("spon_date"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**김응주 - 프로젝트 상세보기 ajax(사진 외 값)*/
	public ProjectDTO detailView(String prj_no) {
		ProjectDTO dto = null;
		String sql="SELECT * FROM project WHERE prj_no=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(prj_no));
			rs = ps.executeQuery();
			if(rs.next()) {
				dto = new ProjectDTO();
				dto.setPrj_no(rs.getInt("prj_no"));
				dto.setPd_id(rs.getString("pd_id"));
				dto.setPrj_cat(rs.getString("prj_cat"));
				dto.setPrj_title(rs.getString("prj_title"));
				dto.setPrj_content(rs.getString("prj_content"));
				dto.setPrj_goal(rs.getLong("prj_goal"));
				dto.setPrj_curr(rs.getLong("prj_curr"));
				dto.setPrj_account(rs.getString("prj_account"));
				dto.setPrj_due(rs.getDate("prj_due"));
				dto.setPrj_date(rs.getDate("prj_date"));
				dto.setPrj_picks(rs.getInt("prj_picks"));
				dto.setPrj_state(rs.getString("prj_state"));
				dto.setPrj_url(rs.getString("prj_url"));
				dto.setPrj_bank(rs.getString("prj_bank"));
				dto.setPrj_comment(rs.getString("prj_comment"));
				/*목표금액까지 진행률*/
				float a = dto.getPrj_curr();
				float b = dto.getPrj_goal();
				float c = (a/b)*100;
				dto.setPrj_gc(c);
				/*D-day*/
				SimpleDateFormat StringDate = new SimpleDateFormat("yyyy-MM-dd");
				String dday = StringDate.format(dto.getPrj_due()).toString(); 
				System.out.println(dday);
				String date[] = dday.split("-");
				int y = Integer.parseInt(date[0]);
				int m = Integer.parseInt(date[1]);
				int d = Integer.parseInt(date[2]);
				dto.setPrj_dday(countdday(y, m, d));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return dto;
	}
	
	/**김응주 - 투자자 목록확인전 권한확인*/
	public boolean sponCheck(String prj_no, String loginId) {
		String sql = "SELECT * FROM project WHERE pd_id=? AND prj_no=?";
		boolean success = false;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginId);
			ps.setString(2, prj_no);
			rs = ps.executeQuery();
			success = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			resClose();
		}
		
		return success;
	}

	/**김응주 - 찜하기*/
	public int pick(String prj_no, String loginId) {		
		String sql = "INSERT INTO pick(id, prj_no) VALUES(?, ?)";
		int success = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginId);
			ps.setString(2, prj_no);
			success = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return success;
	}
	/**김응주 - 찜하기*/
	public int pickUp(String prj_no) {
		String sql = "UPDATE project SET prj_picks = prj_picks+1 WHERE prj_no = ?";
		int success = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, prj_no);
			success = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			resClose();
		}
		return success;
		
	}
	/**김응주 - 찜하기*/
	public int pickCancel(String prj_no, String loginId) {
		String sql = "DELETE FROM pick WHERE id=? AND prj_no=?";
		int success = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginId);
			ps.setString(2, prj_no);
			success = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return success;
	}
	/**김응주 - 찜하기*/
	public int pickDown(String prj_no) {
		String sql = "UPDATE project SET prj_picks = prj_picks-1 WHERE prj_no = ?";
		int success = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, prj_no);
			success = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			resClose();
		}
		return success;
	}
	
	public int pickShow(String prj_no) {
		String sql = "SELECT prj_picks FROM project WHERE prj_no=?";
		int showP = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, prj_no);
			rs = ps.executeQuery();
			if(rs.next()) {
			showP = rs.getInt("prj_picks");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			resClose();
		}
		return showP;
		
	}


	
	/**김응주 - 마이페이지(관리자)*/
	public boolean mypageAdmin(String loginId) {
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

	/**김응주 - 마이페이지(기획자-내project)*/
	public ArrayList<ProjectDTO> myProject(String loginId) {
		ArrayList<ProjectDTO> list = new ArrayList<ProjectDTO>();
		String sql = "SELECT * FROM project WHERE pd_id=? AND prj_state='진행'";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginId);
			rs = ps.executeQuery();
			while(rs.next()) {
				ProjectDTO dto = new ProjectDTO();
				dto.setPrj_title(rs.getString("prj_title"));
				dto.setPrj_no(rs.getInt("prj_no"));
				dto.setPrj_photo(rs.getString("prj_photo"));
				list.add(dto);
				System.out.println(list+"진행");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
		}
		return list;
	}
	/**김응주 - 마이페이지(기획자-내project)*/
	public ArrayList<ProjectDTO> myProject2(String loginId) {
		ArrayList<ProjectDTO> list = new ArrayList<ProjectDTO>();
		String sql = "SELECT * FROM project WHERE pd_id=? AND prj_state='대기'";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginId);
			rs = ps.executeQuery();
			while(rs.next()) {
				ProjectDTO dto = new ProjectDTO();
				dto.setPrj_title(rs.getString("prj_title"));
				dto.setPrj_no(rs.getInt("prj_no"));
				dto.setPrj_photo(rs.getString("prj_photo"));
				list.add(dto);
				System.out.println(list+"대기");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
		}
		return list;
	}
	/**김응주 - 마이페이지(기획자-내project)*/
	public ArrayList<ProjectDTO> myProject3(String loginId) {
		ArrayList<ProjectDTO> list = new ArrayList<ProjectDTO>();
		String sql = "SELECT * FROM project WHERE pd_id=? AND prj_state='완료'";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginId);
			rs = ps.executeQuery();
			while(rs.next()) {
				ProjectDTO dto = new ProjectDTO();
				dto.setPrj_title(rs.getString("prj_title"));
				dto.setPrj_no(rs.getInt("prj_no"));
				dto.setPrj_photo(rs.getString("prj_photo"));
				list.add(dto);
				System.out.println(list+"완료");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return list;
	}

	
	/**김응주 - 마이페이지(관리자-프로젝트승인)*/
	public ArrayList<ProjectDTO> AdminPd() {
		ArrayList<ProjectDTO> list = new ArrayList<ProjectDTO>();
		String sql = "SELECT * FROM project WHERE prj_state='대기'";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				ProjectDTO dto = new ProjectDTO();
				dto.setPrj_photo(rs.getString("prj_photo"));
				dto.setPrj_content(rs.getString("prj_content"));
				dto.setPrj_no(rs.getInt("prj_no"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		
		
		return list;
	}

	/**김응주 - 프로젝트 승인*/
	public int projectOk(String prj_no) {
		int ok = 0;
		String sql = "UPDATE project SET prj_state='진행' WHERE prj_no=? AND prj_state='대기'";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, prj_no);
			ok = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		
		return ok;
	}
	
	/**김응주 - 프로젝트 거절*/
	public int projectNo(ProjectDTO dto) {
		int success = 0;
		String sql = "UPDATE project SET prj_comment=?, prj_state='승인거절' WHERE prj_no= ?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, dto.getPrj_comment());
			ps.setInt(2, dto.getPrj_no());
			success = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return success;
	}
	
	
	/**김응주 - 프로젝트 리스트 처음화면(좋아요 순)*/
	public ArrayList<ProjectDTO> projectList() {
		ArrayList<ProjectDTO> list = new ArrayList<ProjectDTO>();
		String sql = "SELECT * FROM project ORDER BY prj_picks DESC";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				ProjectDTO dto = new ProjectDTO();
				dto.setPrj_cat(rs.getString("prj_cat"));
				dto.setPrj_title(rs.getString("prj_title"));
				dto.setPrj_photo(rs.getString("prj_photo"));
				dto.setPrj_picks(rs.getInt("prj_picks"));
				dto.setPrj_date(rs.getDate("prj_date"));
				dto.setPrj_due(rs.getDate("prj_due"));
				dto.setPrj_goal(rs.getLong("prj_goal"));
				dto.setPrj_curr(rs.getLong("prj_curr"));
				dto.setPrj_no(rs.getInt("prj_no"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return list;
	}


	/**김응주 - 프로젝트 검색필터*/
	public ArrayList<ProjectDTO> projectArrChoice(String choice) {
		ArrayList<ProjectDTO> list = new ArrayList<ProjectDTO>();
		String sql = "";
		if(choice.equals("goal")) {
			sql = "SELECT * FROM project ORDER BY (prj_curr/prj_goal*100) DESC";
		}else if(choice.equals("date")){
			sql = "SELECT * FROM project ORDER BY prj_date ASC";
		}else if(choice.equals("pick")){
			sql = "SELECT * FROM project ORDER BY prj_picks DESC";
		}else if(choice.equals("due")){
			sql = "SELECT * FROM project ORDER BY (prj_due-sysdate) ASC";
		}
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				ProjectDTO dto = new ProjectDTO();
				dto.setPrj_cat(rs.getString("prj_cat"));
				dto.setPrj_title(rs.getString("prj_title"));
				dto.setPrj_photo(rs.getString("prj_photo"));
				dto.setPrj_picks(rs.getInt("prj_picks"));
				dto.setPrj_date(rs.getDate("prj_date"));
				dto.setPrj_due(rs.getDate("prj_due"));
				dto.setPrj_goal(rs.getLong("prj_goal"));
				dto.setPrj_curr(rs.getLong("prj_curr"));
				dto.setPrj_no(rs.getInt("prj_no"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return list;
	}




	/**김응주 - d-day계산*/
	public int countdday(int myear, int mmonth, int mday) {
	    try {
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        
	        Calendar todaCal = Calendar.getInstance(); 
	        Calendar ddayCal = Calendar.getInstance(); 
	        
	        mmonth -= 1;
	        ddayCal.set(myear,mmonth,mday);
	        long today = todaCal.getTimeInMillis()/86400000;
	        long dday = ddayCal.getTimeInMillis()/86400000;
	        long count = dday - today; 
	        return (int) count;
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	        return -1;
	    }
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
	
	/*자원반납*/
	public void resClose() {
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

	/*류종현 찜한 프로젝트 목록 출력*/
	public ArrayList<ProjectDTO> picklist(String loginId) {
		ArrayList<ProjectDTO> list = new ArrayList<ProjectDTO>();		
		String sql="SELECT * FROM project WHERE prj_no IN(SELECT prj_no FROM pick WHERE id = ?)";

		try {
			ps = conn.prepareStatement(sql);	
			ps.setString(1, loginId);
			rs = ps.executeQuery();			
			while(rs.next()) {
				ProjectDTO dto = new ProjectDTO();
				dto.setPrj_no(rs.getInt("prj_no"));
				dto.setPrj_title(rs.getString("prj_title"));
				dto.setPrj_photo(rs.getString("prj_photo"));
				list.add(dto);
			}			
		} catch (SQLException e) {
			System.out.println(e.toString());
			return null;
		}finally {
			resClose();
		}		
		return list;
	}



	public ArrayList<ProjectDTO> investlist(String loginId) {
		ArrayList<ProjectDTO> list = new ArrayList<ProjectDTO>();		
		String sql="SELECT * FROM project WHERE prj_no IN(SELECT prj_no FROM sponsor WHERE id = ?)";

		try {
			ps = conn.prepareStatement(sql);	
			ps.setString(1, loginId);
			rs = ps.executeQuery();					
			while(rs.next()) {
				ProjectDTO dto = new ProjectDTO();
				dto.setPrj_no(rs.getInt("prj_no"));
				dto.setPrj_title(rs.getString("prj_title"));
				dto.setPrj_photo(rs.getString("prj_photo"));
				list.add(dto);
			}			
		} catch (SQLException e) {
			System.out.println(e.toString());
			return null;
		}finally {
			resClose();
		}		
		return list;
	}

	public ArrayList<String> searchName() {
		ArrayList<String> slist = new ArrayList<String>();
		
		String sql = "SELECT * FROM project";
		boolean success = false;
		try {			
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				slist.add(rs.getString("prj_title"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return slist;
	}


	public ArrayList<ProjectDTO> searchlist(String cg, String search) {
		ArrayList<ProjectDTO> list = new ArrayList<ProjectDTO>();		
		String sql="SELECT * FROM project WHERE prj_cat = ? AND prj_title LIKE '%'||?||'%'";

		try {
			ps = conn.prepareStatement(sql);	
			ps.setString(1, cg);
			ps.setString(2, search);
			rs = ps.executeQuery();					
			while(rs.next()) {
				ProjectDTO dto = new ProjectDTO();
				dto.setPrj_cat(rs.getString("prj_cat"));
				dto.setPrj_title(rs.getString("prj_title"));
				dto.setPrj_photo(rs.getString("prj_photo"));
				dto.setPrj_picks(rs.getInt("prj_picks"));
				dto.setPrj_date(rs.getDate("prj_date"));
				dto.setPrj_due(rs.getDate("prj_due"));
				dto.setPrj_goal(rs.getLong("prj_goal"));
				dto.setPrj_curr(rs.getLong("prj_curr"));
				dto.setPrj_no(rs.getInt("prj_no"));
				list.add(dto);
			}			
		} catch (SQLException e) {
			System.out.println(e.toString());
			return null;
		}finally {
			resClose();
		}		
		return list;
	}



	public void updatePrjState_s() {
		String sql="UPDATE project SET prj_state='성공' WHERE prj_no IN(SELECT prj_no FROM project WHERE prj_state='진행' AND prj_due<SYSDATE AND prj_goal<=prj_curr)";
		int cnt=0;
		try {			
			ps = conn.prepareStatement(sql);
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
	}
	
	public void updatePrjState_f() {
		String sql="UPDATE project SET prj_state='실패' WHERE prj_no IN(SELECT prj_no FROM project WHERE prj_state='진행' AND prj_due<SYSDATE AND prj_goal>prj_curr)";
		int cnt=0;
		try {			
			ps = conn.prepareStatement(sql);
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
	}
	
}
