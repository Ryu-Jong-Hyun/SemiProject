package semi.one.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import semi.one.dao.CoinDAO;
import semi.one.dao.MemberDAO;
import semi.one.dto.CoinDTO;
import semi.one.dto.MemberDTO;
import semi.one.paging.ListObject;
import semi.one.paging.PagingService;

public class CoinService {
	
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	
	public CoinService(HttpServletRequest request, HttpServletResponse response) {
		
		//MemberController에서 요청들어오면 이페이지에서 요청을 받는다.
		this.request = request;
		this.response = response;
	}

	//충전
	public void charge() throws IOException {
		String id = request.getParameter("id");
		String money = request.getParameter("money");
		
		MemberDAO dao = new MemberDAO();
		int success = dao.charge(id, money);
		
		Gson json = new Gson();//Gson 객체 생성		
		HashMap<String, Integer> map = new HashMap<>();//map 생성		
		map.put("success", success);//map 에 값 추가		
		String obj = json.toJson(map);//json 으로 변경
		//response 로 반환(옵션1:한글깨짐, 옵션2:크로스 도메인)
		response.getWriter().println(obj);
	}

	
	
	public void coinListForm() throws ServletException, IOException {
		
		String id = (String) request.getSession().getAttribute("loginId");
		int no = Integer.parseInt(request.getParameter("no"));//현재 페이지
	
		CoinDAO dao1 = new CoinDAO();
		CoinDAO dao2 = new CoinDAO();
		CoinDAO dao3 = new CoinDAO();
		PagingService ls = new PagingService();
		ListObject oldLo = new ListObject();
		ListObject newLo = new ListObject();
		
		int balance = dao1.coinBalance(id);//잔액
		int dataCnt = dao2.DataCnt(id);//데이터 전체 수
		int x = 10;
		int y = 5;
		
		oldLo.setNo(no);
		oldLo.setDataCnt(dataCnt); //데이터 전체수
		oldLo.setX(x);	//한페이지에 몇개
		oldLo.setY(y); //이전과 다음 사이에 몇개의 페이지가 들어있는지
		
		newLo = ls.listPaging(oldLo);//페이징 파라미터
		
		ArrayList<CoinDTO> list = dao3.CoinDetail(id, newLo.getIdx(), x);//페이징
		
		if(list != null) {
			//request에 담기
			request.setAttribute("list", list);
			request.setAttribute("balance",balance);
			request.setAttribute("newLo",newLo);
		}else {
			request.setAttribute("msg", "코인 내역 불러오는 중에 오류발생.");
		}
		
		//페이지 이동
		RequestDispatcher dis = request.getRequestDispatcher("coinList.jsp");
		dis.forward(request, response);
	}

}
