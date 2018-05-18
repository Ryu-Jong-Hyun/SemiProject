package semi.one.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import semi.one.dto.CoinDTO;
import semi.one.dto.MemberDTO;
import semi.one.paging.ListObject;

public class CoinDAO {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public CoinDAO() {
		
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		
		
	}

	//코인 내역
	public ArrayList<CoinDTO> CoinDetail(String id, int idx, int x) {
		
		ArrayList<CoinDTO> list = new ArrayList<CoinDTO>();
		CoinDTO dto = null;
		
		//쿼리 및 ps 준비
		String sql ="SELECT * "+
		"FROM (SELECT ROW_NUMBER() OVER(ORDER BY coin_date DESC) AS rnum, id, coin_list, coin_don, coin_date FROM coin where id = ? ) "+
    	"WHERE rnum between ? and ?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setInt(2, idx);
			ps.setInt(3, idx+x-1);
			rs = ps.executeQuery();

			while(rs.next()){
				dto = new CoinDTO(); //값이 있을 때만 객체화
				//dto에 담기
				dto.setId(id);
				dto.setCoin_list(rs.getString("coin_list"));
				dto.setCoin_don(rs.getInt("coin_don"));
				dto.setCoin_date(rs.getDate("coin_date"));
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;//실패시 값 안받기
		}finally {
			resClose();
		}
		
		return list;
	}
	
	//리스트 총갯수 구하기
	public int DataCnt(String id) {
		int tCnt = 0;
		
		//쿼리 및 ps 준비
		String sql ="SELECT count(*) FROM coin WHERE id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();

			if(rs.next()){
				tCnt = rs.getInt("count(*)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;//실패시 값 안받기
		}finally {
			resClose();
		}
		return tCnt;
	}
	
	//코인 잔액
	public int coinBalance(String id) {
		//쿼리 및 ps 준비
		int balancel=0;
		String sql = "SELECT coin_don, coin_list FROM coin WHERE id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();

			while(rs.next()){
				if(rs.getString("coin_list").equals("투자")) {
					balancel -= rs.getInt("coin_don");
				}else{
					balancel += rs.getInt("coin_don");
				}
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return balancel;
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
