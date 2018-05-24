package semi.one.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import semi.one.dto.MemberDTO;

public class MemberDAO {
   
   Connection conn = null;
   PreparedStatement ps = null;
   ResultSet rs = null;

   public MemberDAO() {
      
      try {
         Context ctx = new InitialContext();
         DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle");
         conn = ds.getConnection();
      } catch (Exception e) {
         e.printStackTrace();
      }   
      
   }

   //회원가입
   public int join(MemberDTO dto) {
      
      int success = 0;
      String sql="INSERT INTO member (id, pw, name, email, phone, addr, balance, power)VALUES(?,?,?,?,?,?,0,0)";
      try {
         ps = conn.prepareStatement(sql);
         ps.setString(1, dto.getId());
         ps.setString(2, dto.getPw());
         ps.setString(3, dto.getName());
         ps.setString(4, dto.getEmail());
         ps.setString(5, dto.getPhone());
         ps.setString(6, dto.getAddr());
         
         success = ps.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         resClose();
      }      
      return success;
      
   }

   //중복 체크
   public boolean overlay(String id) {
      
      boolean over = false;
      String sql="SELECT id FROM member WHERE id=?";
      try {
         ps = conn.prepareStatement(sql);
         ps.setString(1, id);
         rs = ps.executeQuery();
         over = rs.next();
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         resClose();
      }
      return over;
      
   }
   
   public boolean overlayResist(String phone) {
	   
	   boolean over = false;
	      String sql="SELECT id FROM member WHERE phone=?";
	      try {
	         ps = conn.prepareStatement(sql);
	         ps.setString(1, phone);
	         rs = ps.executeQuery();
	         over = rs.next();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }finally {
	         resClose();
	      }
	      return over;
	}

   //로그인
   public boolean login(String id, String pw) {

      boolean success = false;
      String sql = "SELECT id FROM member WHERE id=? AND pw=?";
      try {
         ps = conn.prepareStatement(sql);
         ps.setString(1, id);
         ps.setString(2, pw);
         rs = ps.executeQuery();
         success = rs.next();
         
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         resClose();
      }      
      return success;
      
   }
   
   //아이디 찾기
   public String searchId(String name, String phone) {
      String id="";
      String sql = "SELECT id FROM member WHERE name=? AND phone=?";
      try {
         ps = conn.prepareStatement(sql);
         ps.setString(1, name);
         ps.setString(2, phone);
         rs = ps.executeQuery();
         
         if(rs.next()) {
            id = rs.getString("id");
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         resClose();
      }      
      return id;
   }
   
   //비밀번호 찾
   public String searchPw(MemberDTO dto) {
      String id="";
      String sql = "SELECT id FROM member WHERE id=? AND phone=? AND email=?";
      try {
         ps = conn.prepareStatement(sql);
         ps.setString(1, dto.getId());
         ps.setString(2, dto.getPhone());
         ps.setString(3, dto.getEmail());
         rs = ps.executeQuery();
         if(rs.next()) {
            id = rs.getString("id");
         }
      }catch (SQLException e) {
         e.printStackTrace();
      }finally {
         resClose();
      }      
      return id;
   }
   
   //비번 변경
   public int pwChange(String id, String pw) {
      
      int success=0;
      String sql = "UPDATE member SET pw = ? WHERE id = ?";
      try {
         ps = conn.prepareStatement(sql);
         ps.setString(1, pw);
         ps.setString(2, id);
         success = ps.executeUpdate();
      }catch (SQLException e) {
         e.printStackTrace();
      }finally {
         resClose();
      }      
      return success;
   }

   //회원 상세 정보 거져오기
   public MemberDTO memberDetail(String id){
      
      MemberDTO dto = null;
      //쿼리 및 ps 준비
      String sql = "SELECT * FROM member WHERE id=?";
      try {
         ps=conn.prepareStatement(sql);
         //? 대응
         ps.setString(1, id);
         //쿼리 실행
         rs = ps.executeQuery();
         //rs에서 값 추출
         if(rs.next()){
            dto = new MemberDTO(); //값이 있을 때만 객체화
            //dto에 담기
            dto.setId(id);
            dto.setPw(rs.getString("pw"));
            dto.setName(rs.getString("name"));
            dto.setEmail(rs.getString("email"));
            dto.setPhone(rs.getString("phone"));
            dto.setAddr(rs.getString("addr"));
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
         return null;//실패시 값 안받기
      }finally {
         resClose();
      }
      
      return dto;
   }
   
   //회원 정보 수정
   public int memberUpdate(MemberDTO dto) {
      
      int success = 0; //결과 받아올 변수
      //1.sql 생성
      String sql ="UPDATE member SET name=?, email=? , phone=?, addr=? WHERE id=?";
      
      try {//2.PreparedStatement 추출
         ps = conn.prepareStatement(sql);
         //3.?대응
         //회원가입시 입력한 값을 한번에 저장한 클래스(DTO)에서 입력받은 값을 가져와서 대응
         ps.setString(1, dto.getName());
         ps.setString(2, dto.getEmail());
         ps.setString(3, dto.getPhone());
         ps.setString(4, dto.getAddr());
         ps.setString(5, dto.getId()); 
         //4.sql실행
         success = ps.executeUpdate();
         //5.자원반납
      } catch (SQLException e) {
         e.printStackTrace();
         return 0; //만약 Exception걸릴 경우에 실행되지않고 멈추기 때문에 return값줌.
      }finally {
         //자원반납
         resClose();
      }
      return success;
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


   //자원반납
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

	
}