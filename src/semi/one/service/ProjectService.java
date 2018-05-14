package semi.one.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.one.dao.ProjectDAO;
import semi.one.dto.ProjectDTO;

public class ProjectService {
	
	/*김응주 - 마이페이지(기획자,관리자,일반회원) */
	public void mypage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean mypagePdchk = false;
		boolean mypageAdminchk = false;
		String loginId = (String) request.getSession().getAttribute("loginId");
		ProjectDAO dao = new ProjectDAO();
		mypagePdchk = dao.mypage(loginId);		
		mypageAdminchk = dao.mypageAdmin(loginId);
		if(mypagePdchk) {
			response.sendRedirect("mypage_prod.jsp");	//기획자 마이페이지
		}else if(mypageAdminchk) {
			response.sendRedirect("mypage_admin.jsp");		//관리자 마이페이지
		}else {
			response.sendRedirect("mypage_spon.jsp");		//일반회원 마이페이지
		}
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
