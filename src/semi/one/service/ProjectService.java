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

import semi.one.dao.ProjectDAO;
import semi.one.dao.SponsorDTO;
import semi.one.dto.ProjectDTO;
import semi.one.dto.RewardDTO;

public class ProjectService {
	
	
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	
	public ProjectService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	/**김응주 - 프로젝트 상세보기 mvc(사진),리워드*/
	public void photoDetail() throws ServletException, IOException{
		String prj_no = request.getParameter("prj_no");
		ProjectDAO dao = new ProjectDAO();
		ProjectDTO dto = dao.photoDetail(prj_no);
		RewardDTO rdto = dao.rewardDetail(prj_no); //리워드
		request.setAttribute("info", dto);
		request.setAttribute("rwd", rdto);
		RequestDispatcher dis = request.getRequestDispatcher("projectDetail.jsp");
		dis.forward(request, response);
		
	}

	/**김응주 - 투자자목록 읽어오기*/
	public void sponList() throws ServletException, IOException {
		System.out.println("스폰서리스트 메서드진입");
		ProjectDAO dao = new ProjectDAO();
		ArrayList<SponsorDTO> list = dao.sponList(request.getParameter("prj_no"));
		String prj = request.getParameter("prj_no");
		request.setAttribute("list", list);
		 RequestDispatcher dis = request.getRequestDispatcher("sponsorList.jsp");
		 dis.forward(request, response);
		
	}

	/**김응주 - 프로젝트 상세보기 ajax(사진 외 값)*/
	public void detail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String prj_no = (String) request.getSession().getAttribute("prj_no");
		ProjectDAO dao = new ProjectDAO();
		ProjectDTO dto = dao.detailView(prj_no);
		Gson json = new Gson();
		HashMap<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		String obj = json.toJson(map);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println(obj);
	}

	/**김응주 - 투자자 리스트 확인전 권한 확인*/
	public void sponsorListCheck() throws ServletException, IOException {
		String prj_no = request.getParameter("prj_no");
		String loginId = (String) request.getSession().getAttribute("loginId");
		ProjectDAO dao = new ProjectDAO();
		if(dao.sponCheck(prj_no, loginId)) {
			 RequestDispatcher dis = request.getRequestDispatcher("sponsorList");
			 dis.forward(request, response);
		}else {
		     request.setAttribute("msg", "본인의 프로젝트만 확인 가능");
			 RequestDispatcher dis = request.getRequestDispatcher("detail");
			 dis.forward(request, response);
		}
	}

	/**김응주 - 찜하기
	 * @throws ServletException */
	public void pick() throws IOException, ServletException {
		int success = 0;
		int pick=0;
		int pickup=0;
		int pickCancel=0;
		int pickDown=0;
		int chk = 0; // 0 = 로그인 ON
		int showP=0;
		String prj_no = request.getParameter("prj_no");
		String loginId = (String) request.getSession().getAttribute("loginId");
		ProjectDAO dao = new ProjectDAO();
		success = dao.pick(prj_no, loginId);
		
		if(loginId != null) {	//로그인이 된 상태여야만 찜하기 가능
		if(success>0) {
			pickup = dao.pickUp(prj_no);
			if(pickup>0) {
				pick = 1;
			}else {
				pick = 0;
			}
		}else {
		pickCancel	= dao.pickCancel(prj_no, loginId);
			if(pickCancel>0) {
				pickDown = dao.pickDown(prj_no);
				if(pickDown>0) {
					pick=0;	
				}else {
					pick = 1;
				}
			}		
		}
	}else {
		chk = 1; 
	}
		ProjectDAO dao1 = new ProjectDAO();
		showP = dao1.pickShow(prj_no);
		
		Gson json = new Gson();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pick", pick);
		map.put("chk", chk);
		map.put("showP", showP);
		String obj = json.toJson(map);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println(obj);
		
	}
	
	/**김응주 - 마이페이지(기획자,투자자,관리자)*/
	public void mypage() throws ServletException, IOException {
		String loginId = (String) request.getSession().getAttribute("loginId");
		ProjectDAO dao = new ProjectDAO();
		if(dao.mypageAdmin(loginId)) {
			response.sendRedirect("myAdmin");	//관리자 마이페이지
		}else {
			response.sendRedirect("coinListForm?no=1"); //일반회원 마이페이지
		}
	}
		
	
	/**김응주 - 마이페이지(기획자-내 프로젝트)*/
	public void myProject() throws IOException, ServletException {
		String loginId = (String) request.getSession().getAttribute("loginId");
		ProjectDAO dao = new ProjectDAO();
		ArrayList<ProjectDTO> dto = dao.myProject(loginId);
		request.setAttribute("dto", dto);
		ArrayList<ProjectDTO> dto2 = dao.myProject2(loginId);
		request.setAttribute("dto2", dto2);
		ArrayList<ProjectDTO> dto3 = dao.myProject3(loginId);
		request.setAttribute("dto3", dto3);
		
		RequestDispatcher dis = request.getRequestDispatcher("mypagePd.jsp");
		dis.forward(request, response);
	}

	/**김응주 - 마이페이지(관리자-프로젝트-승인)*/
	public void myAdmin() throws ServletException, IOException {
		ProjectDAO dao = new ProjectDAO();
		ArrayList<ProjectDTO> dto = dao.AdminPd();
		request.setAttribute("dto", dto);
		RequestDispatcher dis = request.getRequestDispatcher("mypageAdmin.jsp");
		dis.forward(request, response);
	}

	
	/**김응주 - 마이페이지(관리자-프로젝트승인)*/
	public void projectOk() throws ServletException, IOException {
		int ok = 0;
		String prj_no = request.getParameter("prj_no");
		ProjectDAO dao = new ProjectDAO();
		ok = dao.projectOk(prj_no);
		RequestDispatcher dis = request.getRequestDispatcher("myAdmin");
		dis.forward(request, response);
	}

	/**김응주 - 마이페이지(관리자-프로젝트거절메세지) */
	public void projectNoMsg() throws ServletException, IOException {
		String prj_no = request.getParameter("prj_no");
		request.setAttribute("prj_no", prj_no);
		RequestDispatcher dis = request.getRequestDispatcher("projectNo.jsp");
		dis.forward(request, response);
	}
	
	/**김응주 - 마이페이지(관리자-프로젝트거절)  */
	public void projectMsg() throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int success = 1;
		ProjectDTO dto = new ProjectDTO();
		dto.setPrj_no(Integer.parseInt(request.getParameter("prj_no")));
		dto.setPrj_comment(request.getParameter("msg"));
		ProjectDAO dao = new ProjectDAO();
		if(success == dao.projectNo(dto)) {
			RequestDispatcher dis = request.getRequestDispatcher("myAdmin");
			dis.forward(request, response);
		}
	}

	/**김응주 - 메인화면 -> 프로젝트*/
	public void projectList() throws ServletException, IOException {
		ProjectDAO dao = new ProjectDAO();
		ArrayList<ProjectDTO> dto = dao.projectList();
		request.setAttribute("dto", dto);
		RequestDispatcher dis = request.getRequestDispatcher("projectList.jsp");
		dis.forward(request, response);
	}

	/**김응주 - 메인화면 -> 프로젝트 -> 검색필터*/
	public void projectArr() throws ServletException, IOException {
		ArrayList<ProjectDTO> dto = new ArrayList<ProjectDTO>();
		String choice = request.getParameter("choice");
		ProjectDAO dao = new ProjectDAO();
		if(choice.equals("date")) {
			dto = dao.projectArrChoice(choice);
		}else if(choice.equals("goal")) {
			dto = dao.projectArrChoice(choice);
		}else if(choice.equals("due")) {
			dto = dao.projectArrChoice(choice);
		}else if(choice.equals("pick")) {
			dto = dao.projectArrChoice(choice);
		}
		request.setAttribute("dto", dto);
		RequestDispatcher dis = request.getRequestDispatcher("projectList.jsp");
		dis.forward(request, response);
	}

	/*윤영 - 성공한 프로젝트 리스트 요청*/
	public void successList1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		/*투자자 페이지*/
		
		request.setCharacterEncoding("UTF-8"); //한글깨짐방지
		//프로젝트 테이블 사용
		ProjectDAO dao = new ProjectDAO();
		//프로젝트 데이터 담긴 ArrayList
		ArrayList<ProjectDTO> successList1 = dao.successList1(); 
		request.setAttribute("successList1", successList1);
		System.out.println(successList1);
		//특정한 페이지로 이동
		RequestDispatcher dis = request.getRequestDispatcher("successList_spon.jsp");
		dis.forward(request, response);
	}

	public void successList2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*기획자 페이지*/
		
		request.setCharacterEncoding("UTF-8"); //한글깨짐방지
		//프로젝트 테이블 사용
		ProjectDAO dao = new ProjectDAO();
		//프로젝트 데이터 담긴 ArrayList
		ArrayList<ProjectDTO> successList2 = dao.successList2(); 
		request.setAttribute("successList2", successList2);
		System.out.println(successList2);
		//특정한 페이지로 이동
		RequestDispatcher dis = request.getRequestDispatcher("successList_prod.jsp");
		dis.forward(request, response);
		
	}
}
