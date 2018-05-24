package semi.one.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import semi.one.dao.AnswerDAO;
import semi.one.dao.InquireDAO;
import semi.one.dao.QuestionDAO;
import semi.one.dao.ReplyDAO;
import semi.one.dao.ReviewDAO;
import semi.one.dto.AnswerDTO;
import semi.one.dto.InquireDTO;
import semi.one.dto.QuestionDTO;
import semi.one.dto.ReplyDTO;
import semi.one.dto.ReviewDTO;


public class BoardService {

	/*윤영 - 후기등록 요청*/
	public void review(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8"); //한글깨짐방지
		
		String loginId = (String) request.getSession().getAttribute("loginId"); //로그인한 세션ID값
		
		//DAO 생성
		ReviewDAO dao = new ReviewDAO();
		//파라메터 형식
		ReviewDTO dto = new ReviewDTO();
	
		dto.setPrj_no(Integer.parseInt(request.getParameter("prj_no")));
		dto.setPd_id(request.getParameter("pd_id"));
		dto.setRev_title(request.getParameter("revTitle"));
		dto.setRev_content(request.getParameter("revContent"));
		int success = dao.review(dto, loginId);
		
		Gson json = new Gson();//Gson 객체 생성      
	    HashMap<String, Integer> map = new HashMap<>();//map 생성      
	    map.put("success", success);//map 에 값 추가      
	    String obj = json.toJson(map);//json 으로 변경
	    response.getWriter().println(obj);
	}
	
	
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
	
	
	/*윤영 - 후기 상세보기 요청*/
	public void reviewDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rev_no = request.getParameter("rev_no");
		
		ReviewDAO dao = new ReviewDAO();
		ReviewDTO dto = dao.reviewDetail(Integer.parseInt(rev_no));
		
		request.setAttribute("review", dto);
		
		RequestDispatcher dis = request.getRequestDispatcher("reviewDetail.jsp");
		dis.forward(request, response);
		
	}
		
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
			
		dto.setInq_cat(request.getParameter("choice"));
		dto.setInq_title(request.getParameter("inqTitle"));
		dto.setInq_content(request.getParameter("inqContent"));
		
		String msg = "문의 등록 실패";
		String page = "inquireForm.jsp";
		
		if(dao.inquire(dto, loginId)>0) {
			msg="문의 등록 성공";
			page="myInquireList";
			
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
		String msg="답변 실패";//실패시 메시지
		
		if(dao.reply(dto)>0) {//성공시
			InquireDAO dao1 = new InquireDAO();
			dao1.staUpadte(inq_no);
			msg="답변 성공";
			page ="inquireList";
		}
		request.setAttribute("msg", msg);
		RequestDispatcher dis = request.getRequestDispatcher(page);
		dis.forward(request, response);
		
	}
	
	/*윤영 - (상세페이지)문의 등록 요청*/
	public void question(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8"); //한글깨짐방지
		
		String loginId = (String) request.getSession().getAttribute("loginId"); //로그인한 세션ID값
		String prj_no = (String) request.getSession().getAttribute("prj_no"); //해당프로젝트 번호
		
		QuestionDAO dao = new QuestionDAO();
		QuestionDTO dto = new QuestionDTO();
		
		dto.setQus_cat(request.getParameter("choice"));
		dto.setQus_title(request.getParameter("qusTitle"));
		dto.setQus_content(request.getParameter("qusContent"));
		
		String msg = "문의 등록 실패";
		
		if(dao.question(dto, loginId, prj_no)>0) {
			msg="문의 등록 성공";
		}
		request.setAttribute("msg", msg);
		RequestDispatcher dis= request.getRequestDispatcher("detail?prj_no="+prj_no);
		dis.forward(request, response);
	}
	
	/*윤영 - (상세페이지)문의 상세보기 요청*/
	public void qnaDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //한글깨짐방지
		
		int qus_no =  Integer.parseInt(request.getParameter("qus_no"));
		QuestionDAO dao = new QuestionDAO();//상세보기 - 제목, 아이디,내용
		QuestionDTO dto = dao.qnaDetail(qus_no);
		AnswerDAO dao1 = new AnswerDAO();
		AnswerDTO dto1 = dao1.answerDetail(qus_no);
		
		request.setAttribute("qnaDetail", dto);
		request.setAttribute("answerDetail", dto1);
		RequestDispatcher dis = request.getRequestDispatcher("qnaDetail.jsp");
		dis.forward(request, response);
		
	}

	/*윤영 - (상세페이지)문의 수정 요청*/
	public void qnaUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8"); //한글깨짐방지
			
	  String qTitle = request.getParameter("qTitle");
      int qno = Integer.parseInt(request.getParameter("qno"));
      String qContent = request.getParameter("qContent");
      
      System.out.println(qTitle + qno + qContent);
      
      QuestionDAO dao = new QuestionDAO();
      
      Gson json = new Gson();
      HashMap<String, Integer> map = new HashMap<>();
      map.put("success", dao.qnaUpdate(qTitle, qno, qContent));

      String obj = json.toJson(map);

      response.getWriter().println(obj);
	}


	/*윤영 - (상세페이지)문의 삭제 요청*/
	public void qnaDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int qno = Integer.parseInt(request.getParameter("qus_no"));
		
		QuestionDAO dao = new QuestionDAO();
		
		String msg = "삭제 실패";
		if(dao.delete(qno)>0) {
			 msg="삭제 완료";
	         request.setAttribute("msg", msg);
		}
		 RequestDispatcher dis = request.getRequestDispatcher("projectDetail.jsp");
	     dis.forward(request, response);
	}


	/*윤영 - (상세페이지)문의 답변 요청*/
	public void qnaAnswer(HttpServletRequest request, HttpServletResponse response) throws IOException {
	  request.setCharacterEncoding("UTF-8");
		
	  String qTitle = request.getParameter("qTitle");
      int qno = Integer.parseInt(request.getParameter("qno"));
      String ansContent = request.getParameter("ansContent");
      
      AnswerDAO dao = new AnswerDAO();
      int success = dao.qnaAnswer(qTitle, qno, ansContent);
      Gson json = new Gson();
      HashMap<String, Integer> map = new HashMap<>();
      if(success>0) {
    	  map.put("success",success);
    	  QuestionDAO dao1 = new QuestionDAO();
		  dao1.staUpadte(qno);
      }
      String obj = json.toJson(map);
      response.getWriter().println(obj);
	}	
}
