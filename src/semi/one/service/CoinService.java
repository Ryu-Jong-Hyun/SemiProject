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
		int money = Integer.parseInt(request.getParameter("money"));
		
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
		CoinDAO dao = new CoinDAO();
		ArrayList<CoinDTO> list = dao.CoinDetail(id);
		CoinDAO dao2 = new CoinDAO();
		int total = dao2.coinTotal(id);
		if(list != null) {
			//request에 담기
			request.setAttribute("list", list);
			request.setAttribute("total",total);
		}else {
			request.setAttribute("msg", "코인 내역 불러오는 중에 오류발생.");
		}
		
		//페이지 이동
		RequestDispatcher dis = request.getRequestDispatcher("coinList.jsp");
		dis.forward(request, response);
	}

}
