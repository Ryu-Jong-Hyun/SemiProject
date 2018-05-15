package semi.one.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.one.dao.InquireDAO;
import semi.one.dao.ProjectDAO;
import semi.one.dao.QuestionDAO;
import semi.one.dao.ReplyDAO;
import semi.one.dao.ReviewDAO;
import semi.one.dto.InquireDTO;
import semi.one.dto.ProjectDTO;
import semi.one.dto.QuestionDTO;
import semi.one.dto.ReplyDTO;
import semi.one.dto.ReviewDTO;


public class BoardService {

	/*****************************************************************************/
	/*윤영 - 후기등록 요청*/
	public void review(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8"); //한글깨짐방지
		
		String loginId = (String) request.getSession().getAttribute("loginId"); //로그인한 세션ID값
		
		//DAO 생성
		ReviewDAO dao = new ReviewDAO();
		//파라메터 형식
		ReviewDTO dto = new ReviewDTO();
		
		//prj_no, pd_id 가져오기
		dto.setPrj_no(Integer.parseInt(request.getParameter("prjNo")));	
		dto.setPd_id(request.getParameter("pdId"));
		dto.setRev_title(request.getParameter("revTitle"));
		dto.setRev_title(request.getParameter("revContent"));
		
		String msg = "후기 작성 실패";
		String page = "mypage_spon.jsp";
		
		if(dao.review(dto, loginId)>0) {
			msg = "후기 작성 성공";
			page = "/successList2";
		}
		request.setAttribute("msg", msg);
		RequestDispatcher dis = request.getRequestDispatcher(page);
		dis.forward(request, response);
	}
	/*****************************************************************************/
	
	
	
	/*윤영 - 후기리스트 요청*/
	public void reviewList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8"); //한글깨짐방지
		
		String prj_no = request.getParameter("prj_no");
		
		ReviewDAO dao = new ReviewDAO();
		ArrayList<ReviewDTO> reviewList = dao.reviewList(Integer.parseInt(prj_no)); 
		request.setAttribute("reviewList", reviewList);
		//특정한 페이지로 이동
		RequestDispatcher dis = request.getRequestDispatcher("reviewList.jsp");
		dis.forward(request, response);
		
	}
	
	
	/*****************************************************************************/
	/*윤영 - 후기 상세보기 요청*/
	public void reviewDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*String rev_no = request.getParameter("rev_no");
		
		ReviewDAO dao = new ReviewDAO();
		ReviewDTO dto = dao.reviewDetail(Integer.parseInt(rev_no));
		
		request.setAttribute("review", dto);
		
		RequestDispatcher dis = request.getRequestDispatcher("reviewDetail.jsp");
		dis.forward(request, response);*/
		
	}
	/*****************************************************************************/
	
	
	
	
	/*윤영 - 나의 문의 리스트 요청*/
	public void myInquireList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8"); //한글깨짐방지
		
		String loginId = (String) request.getSession().getAttribute("loginId"); //로그인한 세션ID값
		
		InquireDAO dao = new InquireDAO();
		ArrayList<InquireDTO> myInquireList = dao.myInquireList(loginId); 
		request.setAttribute("myInquireList", myInquireList);
		System.out.println(myInquireList);
		//특정한 페이지로 이동
		RequestDispatcher dis = request.getRequestDispatcher("myInquireList.jsp");
		dis.forward(request, response);
		
	}

	/*윤영 - 문의 등록 요청*/
	public void inquire(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8"); //한글깨짐방지
		
		String loginId = (String) request.getSession().getAttribute("loginId"); //로그인한 세션ID값
		
		InquireDAO dao = new InquireDAO();
		InquireDTO dto = new InquireDTO();
			
		dto.setInq_title(request.getParameter("inqTitle"));
		dto.setInq_content(request.getParameter("inqContent"));
		
		String msg = "문의 등록 실패";
		String page = "inquireForm.jsp";
		
		if(dao.inquire(dto, loginId)>0) {
			msg="문의 등록 성공";
			page="./myInquireList";
		}
		request.setAttribute("msg", msg);
		RequestDispatcher dis= request.getRequestDispatcher(page);
		dis.forward(request, response);
		
		
	}
	
	
	/*윤영 - (관리자)문의 리스트 요청*/
	public void inquireList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		/*관리자 페이지*/
		
		request.setCharacterEncoding("UTF-8"); //한글깨짐방지
		
		InquireDAO dao = new InquireDAO();
		
		ArrayList<InquireDTO> inquireList = dao.inquireList(); 
		request.setAttribute("inquireList", inquireList);
		//특정한 페이지로 이동
		RequestDispatcher dis = request.getRequestDispatcher("inquireList.jsp");
		dis.forward(request, response);		
	}

	
	/*윤영 - (관리자)문의 상세보기 요청*/
	public void inquireDetail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8"); //한글깨짐방지
		
		String inq_no = request.getParameter("inq_no");
		
		InquireDAO Inqdao = new InquireDAO();//상세보기 - 제목,아이디, 유형, 내용
		InquireDTO Inqdto = Inqdao.inqDetail(Integer.parseInt(inq_no));
		//ReplyDAO Repdao = new ReplyDAO(); //상세보기 - '답변'
		
		request.setAttribute("inqDetail", Inqdto);
		RequestDispatcher dis = request.getRequestDispatcher("inquireDetail.jsp");
		dis.forward(request, response);
		
		
	}

	/*윤영 - (관리자)문의 답변 등록 요청*/
	public void reply(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8"); //한글깨짐방지
		
		ReplyDAO dao = new ReplyDAO();
		ReplyDTO dto = new ReplyDTO();
		int inq_no = Integer.parseInt(request.getParameter("inqNo"));
		dto.setInq_no(inq_no);
		dto.setRep_title(request.getParameter("inqTitle"));
		dto.setRep_content(request.getParameter("replyContent"));
		
		String page="inquireDetail.jsp";//실패시 문의 상세페이지
		
		if(dao.reply(dto)>0) {
			InquireDAO dao1 = new InquireDAO();
			dao1.staUpadte(inq_no);
			page ="inquireList";
		}
		RequestDispatcher dis = request.getRequestDispatcher(page);
		dis.forward(request, response);
		
	}

	/*윤영 - (프로젝트 상세페이지)QnA 리스트 요청*/
	public void qnaList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8"); //한글깨짐방지
		
		QuestionDAO dao = new QuestionDAO();
		
		ArrayList<QuestionDTO> qnaList = dao.qnaList();
		request.setAttribute("qnaList", qnaList);
		//특정한 페이지로 이동
		/*RequestDispatcher dis = request.getRequestDispatcher("./projectDetail");
		dis.forward(request, response);*/
		
	}

}
