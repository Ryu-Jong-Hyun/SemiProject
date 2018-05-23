package semi.one.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import semi.one.service.BoardService;
import semi.one.service.ProjectService;

import semi.one.service.CoinService;
import semi.one.service.MemberService;

@WebServlet({"/main","/login","/logout","/join","/overlay","/searchId","/searchPw","/pwChange","/memberUpdateForm","/memberUpdate","/updatePw", "/charge","/coinListForm","/successList1","/review","/successList2"
	,"/reviewList","/reviewDetail","/myInquireList","/inquire","/inquireList","/inquireDetail","/reply","/qnaList","/projectDetail","/detail","/sponsorList","/sponsorListCheck"
	,"/pick","/myProject","/mypage","/myAdmin","/projectOk","/projectNoMsg","/projectNo","/project","/projectArr","/pickList","/searchList","/listNext","/listBack","/prj_write","/mainbefore","/mainNext","/mainBack"})

public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dual(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dual(request, response);
	}

	private void dual(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String ctx = request.getContextPath();
		String subAddr = uri.substring(ctx.length());
		

		//Service 생성
		ProjectService project = null;
		BoardService board = null;

		MemberService member = null;
		CoinService coin = null;
		ProjectService service = null;
		
		switch(subAddr) {
			case "/main":
				System.out.println("메인페이지 요청");
				service =  new ProjectService(request, response);
				service.mainbefore(request, response);
				break;
				
			case "/login":
				System.out.println("로그인 요청");
				member = new MemberService(request, response);
				member.login();
				break;
				
			case "/logout":
				System.out.println("로그아웃 요청");
				member = new MemberService(request, response);
				member.logout();
				break;
				
			case "/join":
				System.out.println("회원가입 요청");
				member = new MemberService(request, response);
				member.join();
				break;
				
			case "/overlay":
				System.out.println("중복확인 요청");
				member = new MemberService(request, response);
				member.overlay();
				break;
				
			case "/searchId":
				System.out.println("아이디 찾기 요청");
				member = new MemberService(request, response);
				member.searchId();
				break;
				
			case "/searchPw":
				System.out.println("비번 찾기 요청");
				member = new MemberService(request, response);
				member.searchPw();
				break;
				
			case "/pwChange":
				System.out.println("비번 찾기(변경) 요청");
				member = new MemberService(request, response);
				member.pwChange();
				break;
				
			case "/memberUpdateForm":
				System.out.println("회원정보수정 화면 요청");
				member = new MemberService(request, response);
				member.memberUpdateForm();
				break;
			
			case "/memberUpdate":
				System.out.println("회원 정보 수정 요청");
				member = new MemberService(request, response);
				member.memberUpdate();
				break;
				
			case "/updatePw":
				System.out.println("비번 수정 요청 ");
				member = new MemberService(request, response);
				member.updatePw();
				break;
				
			case "/charge":
				System.out.println("충전 요청 ");
				coin = new CoinService(request, response);
				coin.charge();
				break;
				
				
			case "/coinListForm":
				System.out.println("코인 내역 화면 요청 ");
				coin = new CoinService(request, response);
				coin.coinListForm();
				break;
			

			 
				 
			/*윤영 - 후기작성&확인*/
			case "/successList1": //투자자 페이지
				System.out.println("투자자-성공한 프로젝트 리스트 요청");
				project = new ProjectService(request, response);
				project.successList1(request, response);
				break;
			case "/review":
				System.out.println("후기작성 등록 요청");
				board = new BoardService();
				board.review(request, response);
				break;	
			case "/successList2": //기획자 페이지
				System.out.println("기획자-성공한 프로젝트 리스트 요청");
				project = new ProjectService(request, response);
				project.successList2(request, response);
				break;
			case "/reviewList":
				System.out.println("프로젝트 후기 리스트 요청");
				board = new BoardService();
				board.reviewList(request, response);
				break;
			case "/reviewDetail":
				System.out.println("후기 상세보기 요청");
				board = new BoardService();
				board.reviewDetail(request, response);
				break;
				
			/*윤영 - 나의 문의 확인&작성*/	
			case "/myInquireList":
				System.out.println("나의 문의리스트 요청");
				board = new BoardService();
				board.myInquireList(request, response);
				break;
			case "/inquire":
				System.out.println("문의작성 등록 요청");
				board = new BoardService();
				board.inquire(request, response);
				break;
				
			/*윤영 - 문의 확인&답변*/	
			case "/inquireList":
				System.out.println("관리자-문의리스트 요청");
				board = new BoardService();
				board.inquireList(request, response);
				break;
			case "/inquireDetail":
				System.out.println("관리자-문의 상세보기 요청");
				board = new BoardService();
				board.inquireDetail(request, response);
				break;
			case "/reply":
				System.out.println("관리자-문의 답변등록 요청");
				board = new BoardService();
				board.reply(request, response);
				break;
			case "/qnaList":
				System.out.println("상세페이지 - QnA리스트 요청");
				board = new BoardService();
				board.qnaList(request, response);
				break;	
			
				/**응주 */
			
			case "/projectDetail": //함
				System.out.println("AJAX 상세보기값 받아오기");
				service =  new ProjectService(request, response);
				service.detail(request, response);
				break;
				
			 case "/detail": //함	// detail -> projectDetail.jsp -> projectDetail
				 System.out.println("prj_no 세션저장 + MVC 사진 받아오기");
				 request.getSession().setAttribute("prj_no", request.getParameter("prj_no"));
				 service = new ProjectService(request,response);
				 service.photoDetail();
				 break;
			
			 case "/sponsorList":
				 System.out.println("스폰서 리스트보기");
				 service = new ProjectService(request,response);
				 service.sponList();
				 break;
				 
				 
			 case "/sponsorListCheck":
				 System.out.println("투자자 목록 확인전 권한확인");
				 service = new ProjectService(request,response);
				 service.sponsorListCheck();
				 break;
				 
			 case "/pick":
				 System.out.println("찜하기");
				 service = new ProjectService(request,response);
				 service.pick();
				 break;
				
				 
			 case "/myProject":
				 System.out.println("마이페이지(기획자-내프로젝트)");
				 service = new ProjectService(request,response);
				 service.myProject();
				 break;
				 
			 case "/mypage"://함
				 System.out.println("마이페이지(권한체크)");
				 project = new ProjectService(request, response);
				 project.mypage();
				 break;
				 
			 case "/myAdmin"://함
				 System.out.println("마이페이지(관리자-프로젝트승인)");
				 service = new ProjectService(request,response);
				 service.myAdmin();
				 break;
				 
			 case "/projectOk":
				 System.out.println("프로젝트 승인");
				 service = new ProjectService(request,response);
				 service.projectOk();
				 break;
				 
			 case "/projectNoMsg":
				 System.out.println("프로젝트 거절메세지작성");
				 service = new ProjectService(request,response);
				 service.projectNoMsg();
				 break;
				 
			 case "/projectNo":
				 System.out.println("프로젝트 거절");
				 service = new ProjectService(request,response);
				 service.projectMsg();
				 break;
				 
			 case "/project":
				 System.out.println("프로젝트불러오기(초기값:픽순)");
				 service = new ProjectService(request,response);
				 service.projectList();
				 break;
				 
			 case "/projectArr":
				 System.out.println("프로젝트정렬불러오기");
				 service = new ProjectService(request,response);
				 service.projectArr();
				 break;
				 
			 case "/pickList":
				 System.out.println("찜한 목록 불러오기");
				 service = new ProjectService(request,response);
				 service.pickList();
				 break;
				 
			 case "/investList":
				 System.out.println("투자한 목록 불러오기");
				 service = new ProjectService(request,response);
				 service.investList();
				 break;
				 
			 case "/searchList":
				 System.out.println("검색 기능 불러오기");
				 service = new ProjectService(request,response);
				 service.searchList();
				 break;
		
			 case "/listNext":
					System.out.println("next");
					service = new ProjectService(request, response);
					service.listNext();
					break;
					
			case "/listBack":
					System.out.println("back");
					service = new ProjectService(request, response);
					service.listBack();
					break; 
  
				 /*보네*/
				 
			case "/prj_write":
				System.out.println("프로젝트 작성 완료");
				service = new ProjectService(request,response);
				service.prj_write();
				break;
				
			case "/adminSuccessList":
				System.out.println("관리자 성공기획");
				service = new ProjectService(request,response);
				service.adminSuccessList();
				break;
		}
	}
}
