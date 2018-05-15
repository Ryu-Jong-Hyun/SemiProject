package semi.one.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.bind.v2.runtime.Location;

import semi.one.service.CoinService;
import semi.one.service.MemberService;

@WebServlet("/")
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
		
		MemberService member = null;
		CoinService coin = null;
		
		switch(subAddr) {
			case "/main":
				System.out.println("메인페이지 요청");
				
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
	
				
		}
	}
}
