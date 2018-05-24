package semi.one.service;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import semi.one.dao.MemberDAO;
import semi.one.dto.MemberDTO;

public class MemberService {
	
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	
	public MemberService(HttpServletRequest request, HttpServletResponse response) {
		
		//MemberController에서 요청들어오면 이페이지에서 요청을 받는다.
		this.request = request;
		this.response = response;
	}

	//로그인 서비스
	public void login() throws IOException {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberDAO dao = new MemberDAO();
		boolean success = dao.login(id, pw);
		
		if(success) {
			request.getSession().setAttribute("loginId", id);
		}
		//성공 실패 여부를 response 로 반환
		Gson json = new Gson();
		HashMap<String, Boolean> map = new HashMap<>();
		map.put("success", success);
		String obj = json.toJson(map);
		response.getWriter().println(obj);		
	}

	//회원가입 서비스
	public void join() throws IOException {

		request.setCharacterEncoding("UTF-8");
		MemberDTO dto = new MemberDTO();
		dto.setId(request.getParameter("id"));
		dto.setPw(request.getParameter("pw"));
		dto.setName(request.getParameter("name"));
		dto.setEmail(request.getParameter("email"));
		dto.setPhone(request.getParameter("phone"));
		dto.setAddr(request.getParameter("addr"));
		MemberDAO dao = new MemberDAO();
		int success = dao.join(dto);
				
		Gson json = new Gson();//Gson 객체
		HashMap<String, Integer> map = new HashMap<String, Integer>();//map 만들어서 값 추가
		map.put("success", success);
		//json 형태로 변환
		String obj = json.toJson(map);
		//response 반환(한글깨짐 방지,크로스 도메인)
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println(obj);
		
	}

	//아이디 중복확인 서비스
	public void overlay() throws IOException {

		String id = request.getParameter("id");
		boolean able = false;
		boolean overlay = false;
		MemberDAO dao = new MemberDAO();
		System.out.println(id.length());
		if(id.length()>=4 && id.length()<=10) {
			able = true;
			overlay = dao.overlay(id);
		}
		
				
		Gson json = new Gson();//Gson 객체 생성		
		HashMap<String, Boolean> map = new HashMap<>();//map 생성		
		map.put("overlay", overlay);//map 에 값 추가	
		map.put("able", able);
		String obj = json.toJson(map);//json 으로 변경
		//response 로 반환(옵션1:한글깨짐, 옵션2:크로스 도메인)
		response.getWriter().println(obj);
		
	}
	
	//폰번호 중복확인 서비스
		public void overlayResist() throws IOException {

			String phone = request.getParameter("phone");
			boolean overlay = false;
			MemberDAO dao = new MemberDAO();
			overlay = dao.overlayResist(phone);
					
			Gson json = new Gson();//Gson 객체 생성		
			HashMap<String, Boolean> map = new HashMap<>();//map 생성		
			map.put("overlay", overlay);//map 에 값 추가	
			String obj = json.toJson(map);//json 으로 변경
			//response 로 반환(옵션1:한글깨짐, 옵션2:크로스 도메인)
			response.getWriter().println(obj);
			
		}

	//로그아웃 서비스
	public void logout() throws IOException {

		//세션추출
		HttpSession session = request.getSession();
		//loginId 속성값 삭제
		session.removeAttribute("loginId");
		//main페이지 요청
		response.sendRedirect("main");
		
	}

	//아이디 찾기
	public void searchId() throws IOException {
		
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		System.out.println("param : "+name+"/"+phone);
		MemberDAO dao = new MemberDAO();
		String id = dao.searchId(name, phone);
				
		Gson json = new Gson();//Gson 객체 생성		
		HashMap<String, String> map = new HashMap<>();//map 생성		
		map.put("searchId", id);//map 에 값 추가		
		String obj = json.toJson(map);//json 으로 변경
		//response 로 반환(옵션1:한글깨짐, 옵션2:크로스 도메인)
		response.getWriter().println(obj);
		
	}

	//비번 찾기 요청
	public void searchPw() throws IOException {
		
		String id = request.getParameter("id");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		System.out.println("param : "+id+"/"+phone+"/"+email);
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPhone(phone);
		dto.setEmail(email);
		
		MemberDAO dao = new MemberDAO();
		String pId = dao.searchPw(dto);
				
		Gson json = new Gson();//Gson 객체 생성		
		HashMap<String, String> map = new HashMap<>();//map 생성		
		map.put("id", pId);//map 에 값 추가		
		String obj = json.toJson(map);//json 으로 변경
		//response 로 반환(옵션1:한글깨짐, 옵션2:크로스 도메인)
		response.getWriter().println(obj);
		
	}

	//비번 변경 요청
	public void pwChange() throws IOException {

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		MemberDAO dao = new MemberDAO();
		int success = dao.pwChange(id, pw);
		
		Gson json = new Gson();//Gson 객체 생성		
		HashMap<String, Integer> map = new HashMap<>();//map 생성		
		map.put("success", success);//map 에 값 추가		
		String obj = json.toJson(map);//json 으로 변경
		//response 로 반환(옵션1:한글깨짐, 옵션2:크로스 도메인)
		response.getWriter().println(obj);
	}

	//개인정보 수정 화면 
	public void memberUpdateForm() throws ServletException, IOException {
		String id = (String) request.getSession().getAttribute("loginId");
		//dao호출 -> detail(id)
		MemberDAO dao = new MemberDAO();
		//MemberDTO반환
		MemberDTO dto = dao.memberDetail(id);
		if(dto != null) {
			//request에 담기
			request.setAttribute("info", dto);
		}else {
			request.setAttribute("msg", "회원 정보 불러오는 도중에 오류발생.");
		}
		
		//페이지 이동
		RequestDispatcher dis = request.getRequestDispatcher("updateForm.jsp");
		dis.forward(request, response);
		
	}

	//개인정보 변경
	public void memberUpdate() throws IOException {

		request.setCharacterEncoding("UTF-8"); //한글깨짐방지
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String addr = request.getParameter("addr");
		
		MemberDTO dto =new MemberDTO();
		dto.setId(id);
		dto.setName(name);
		dto.setEmail(email);
		dto.setPhone(phone);
		dto.setAddr(addr);
		
		MemberDAO dao = new MemberDAO();
		int success = dao.memberUpdate(dto);
		
		Gson json = new Gson();//Gson 객체 생성		
		HashMap<String, Integer> map = new HashMap<>();//map 생성		
		map.put("success", success);//map 에 값 추가		
		String obj = json.toJson(map);//json 으로 변경
		//response 로 반환(옵션1:한글깨짐, 옵션2:크로스 도메인)
		response.getWriter().println(obj);
	}

	//비밀번호 수정
	public void updatePw() throws IOException {
		String id = request.getParameter("id");
		String currPw = request.getParameter("currPw");
		String newPw = request.getParameter("newPw");
		
		MemberDAO dao = new MemberDAO();
		int chk=0;
		if(dao.login(id,currPw)) {
			chk=1;
		}
		dao = new MemberDAO();
		int success = dao.pwChange(id,newPw);
		
		Gson json = new Gson();//Gson 객체 생성		
		HashMap<String, Integer> map = new HashMap<>();//map 생성		
		map.put("chk", chk);//map 에 값 추가		
		map.put("success", success);//map 에 값 추가		
		String obj = json.toJson(map);//json 으로 변경
		//response 로 반환(옵션1:한글깨짐, 옵션2:크로스 도메인)
		response.getWriter().println(obj);
		
	}

}
