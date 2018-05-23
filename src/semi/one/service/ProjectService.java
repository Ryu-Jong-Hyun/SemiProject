package semi.one.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import semi.one.dao.ProjectDAO;
import semi.one.dto.ProjectDTO;
import semi.one.dto.RewardDTO;
import semi.one.dto.SponsorDTO;
import semi.one.paging.ListObject;
import semi.one.paging.PagingService;

public class ProjectService {
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	String savePath = null;
	HttpSession session;
	RewardDTO rwDTO = new RewardDTO();

	
	public ProjectService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	//보네 - 프로젝트 기획하기(서비스)
	   public void prj_write() throws IOException, ServletException {
	      
	      ProjectDAO prjDAO = new ProjectDAO();
	      ProjectDTO prjDTO = new ProjectDTO();
	      
	      //사진 등록
	      String root = request.getSession().getServletContext().getRealPath("/");
	      savePath = root+"upload";//저장경로
	      System.out.println("프로젝트서비스 savePath : "+savePath);
	      
	      File dir = new File(savePath);//저장경로가 savePath
	      if(!dir.exists()) {//폴더 없으면
	         dir.mkdir();//만들어준다.
	      }
	      
	      MultipartRequest multi = new MultipartRequest(request, savePath, 1024*1024*10, "UTF-8", new DefaultFileRenamePolicy());
	      String photopath = "";//사진 이름
	      
	      photopath = photo_write(multi);

	      
	      //prj_due Date로 변환하려고!
	      String strDue = multi.getParameter("prj_due");
	      System.out.println(strDue);
		
	      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	      Date parsed = null;
	      java.sql.Date date = null;
	      try {
			parsed = format.parse(strDue);
			date = new java.sql.Date(parsed.getTime());
	      } catch (ParseException e) {
	    	  e.printStackTrace();
	      }
        


		System.out.println(date);


		//prjDTO에 파라미터 값 넣기
	      prjDTO.setPd_id(multi.getParameter("pd_id"));
	      prjDTO.setPrj_cat(multi.getParameter("prj_cat"));
	      prjDTO.setPrj_title(multi.getParameter("prj_title"));
	      prjDTO.setPrj_content(multi.getParameter("prj_content"));
	      prjDTO.setPrj_goal(Integer.parseInt(multi.getParameter("prj_goal")));
	      prjDTO.setPrj_due(date);
	      prjDTO.setPrj_bank(multi.getParameter("prj_bank"));
	      prjDTO.setPrj_account(multi.getParameter("prj_account"));
	      prjDTO.setPrj_comment(multi.getParameter("prj_comment"));//null값 들어갈 예정
	      prjDTO.setPrj_photo(photopath);

	      System.out.println("setDTO테스트");
	      System.out.println("pd_id : "+prjDTO.getPd_id());
	      System.out.println("prj_cat : "+prjDTO.getPrj_cat());
	      System.out.println("prj_title : "+prjDTO.getPrj_title());
	      System.out.println("prj_photo : "+prjDTO.getPrj_photo());
	      System.out.println("prj_content : "+prjDTO.getPrj_content());
	      System.out.println("prj_goal : "+prjDTO.getPrj_goal());
	      System.out.println("prj_curr : "+prjDTO.getPrj_curr());
	      System.out.println("prj_bank : "+prjDTO.getPrj_bank());
	      System.out.println("prj_due : "+prjDTO.getPrj_due());
	      System.out.println("prj_account : "+prjDTO.getPrj_account());
	      System.out.println("prj_picks : "+prjDTO.getPrj_picks());
	      System.out.println("prj_comment : "+prjDTO.getPrj_comment());

	      
	      String msg = "프로젝트 기획 등록에 실패하였습니다.";
	      String page = "prj_write.jsp";
	      if(prjDAO.prj_write(prjDTO)>0) {//성공시
	         msg = "프로젝트 기획 등록에 성공하였습니다. 관리자의 승인 대기중입니다.";
	         
	         session = request.getSession();
	         String loginId = (String) session.getAttribute("loginId");
	         System.out.println("세션값(로그인아이디) : "+loginId);
	         String prj_no = prjDAO.prj_no(loginId);
	         System.out.println("prj_no : "+prj_no);
	         
	         if(!"".equals(prj_no)||prj_no!=null) {
	            //리워드테이블에 prj_no 설정
	            rwDTO.setPrj_no(Integer.parseInt(prj_no));
	            rw_write(multi);
	            page = "main.jsp";
	         }
	         System.out.println("msg : "+msg);
	         System.out.println("page : "+page);
	         
	         
	      }
	      request.setAttribute("msg", msg);
	      RequestDispatcher dis = request.getRequestDispatcher(page);
	      dis.forward(request, response);

	      
	   }
	   
	   //보네 - 사진 업로드하기
	   public String photo_write(MultipartRequest multi) throws IOException {
	      request.setCharacterEncoding("UTF-8");
	      String newFileName ="";
	      if(!"".equals(multi.getParameter("prj_photo")) || multi.getParameter("prj_photo") != null) {
	         System.out.println("사진업로드 시작");
	                           
	            //파일명 변경(업로드한 파일이 있을 경우만 실행)
	            String oriFileName = multi.getFilesystemName("prj_photo");
	            if(oriFileName!=null) {
	               //확장자 추출
	               String ext = oriFileName.substring(oriFileName.indexOf("."));
	               //새 파일명 만들기(새 파일명+확장자)
	               newFileName = System.currentTimeMillis()+ext;
	               //파일명 변경
	               File oldFile = new File(savePath+"/"+oriFileName);
	               File newFile = new File(savePath+"/"+newFileName);
	               oldFile.renameTo(newFile);
	               //변경된 파일명 DTO에 추가
	            }else {//파일 업로드 안됐을때 테스트 이미지로...
	               newFileName = "TESETIMAGE.jpg";
	            }
	         System.out.println("사진업로드 끝");
	      }
	      return newFileName;
	      
	   }
	   //보네 - 리워드 등록
	   public void rw_write(MultipartRequest multi) throws IOException {
	      request.setCharacterEncoding("UTF-8");
	      ProjectDAO prjDAO = new ProjectDAO();

	      
	      System.out.println("리워드테스트--------------------");
	      int i = 0;
	        String strRw = multi.getParameter("rwVal");
	        String[] arr = strRw.split(",");
	           for(i=0; i<arr.length;i++) {
	              int num = i%3;//3개씩 뽑아오려고 나머지 변수 만들었음
	              switch(num) {
	              case 0:
	                 rwDTO.setRw_item(arr[i]);
	                 System.out.println(rwDTO.getRw_item());
	                 break;
	                 
	              case 1:
	                 rwDTO.setRw_min(Integer.parseInt((arr[i])));
	                 System.out.println(rwDTO.getRw_min());
	                 break;
	                 
	              case 2:
	                 rwDTO.setRw_max(Integer.parseInt((arr[i])));
	                 System.out.println(rwDTO.getRw_max());
	                 prjDAO.rw_write(rwDTO);//INSERT 한번씩!!
	                 break;
	                 }
	           }
	           prjDAO.resClose();//INSERT 여러번 돌고 나서 반납해주려고...
	           
	   }
	
	
	
	
	
	
	
	/**김응주 - 프로젝트 상세보기 mvc(사진),리워드*/
	public void photoDetail() throws ServletException, IOException{
		String prj_no = request.getParameter("prj_no");
		ProjectDAO dao = new ProjectDAO();
		ProjectDTO dto = dao.photoDetail(prj_no);
		ArrayList<RewardDTO> rdto = dao.rewardDetail(prj_no);
		request.setAttribute("info", dto);
		System.out.println(rdto);
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
		String page = "";
		if(dao.sponCheck(prj_no, loginId)) {
			 page = "sponsorList";
		}else {
		     request.setAttribute("msg", "본인의 프로젝트만 확인 가능");
			 page = "detail";
		}
		RequestDispatcher dis = request.getRequestDispatcher(page);
		 dis.forward(request, response);
	}

	/**김응주 - 찜하기*/
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
		ArrayList<ProjectDTO> dto = dao.myProject(loginId,1,9);
		request.setAttribute("dto", dto);
		request.setAttribute("start", 1);
		request.setAttribute("end", 9);
		
		RequestDispatcher dis = request.getRequestDispatcher("mypagePd.jsp");
		dis.forward(request, response);
	}

	/**김응주 - 페이징(다음)*/
	public void listNext() throws ServletException, IOException {
		String loginId = (String) request.getSession().getAttribute("loginId");
		int start = Integer.parseInt(request.getParameter("start"));
		int end = Integer.parseInt(request.getParameter("end"));
		
		start=start+9;
		end=end+9;
		ProjectDAO dao = new ProjectDAO();
		ArrayList<ProjectDTO> dto = dao.myProject(loginId,start,end);

		if(dto.size()!=0){
			request.setAttribute("dto", dto);
			request.setAttribute("start", start);
			request.setAttribute("end", end);
			RequestDispatcher dis = request.getRequestDispatcher("mypagePd.jsp");
			dis.forward(request, response);
		}else {
			response.sendRedirect("myProject");
			}
	}
	/**김응주 - 페이징(이전)*/
	public void listBack() throws ServletException, IOException {
		String loginId = (String) request.getSession().getAttribute("loginId");
		int start = Integer.parseInt(request.getParameter("start"));
		int end = Integer.parseInt(request.getParameter("end"));
	
		start=start-9;
		end=end-9;
		ProjectDAO dao = new ProjectDAO();
		ArrayList<ProjectDTO> dto = dao.myProject(loginId,start,end);
		
		if(end!=0){
			request.setAttribute("dto", dto);
			request.setAttribute("start", start);
			request.setAttribute("end", end);
			RequestDispatcher dis = request.getRequestDispatcher("mypagePd.jsp");
			dis.forward(request, response);
		}else {
			response.sendRedirect("myProject");
		}
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
			System.out.println("승인거절완료!!");
			RequestDispatcher dis = request.getRequestDispatcher("myAdmin");
			dis.forward(request, response);
		}
	}

	/**김응주 - 메인화면 -> 프로젝트*/
	public void projectList() throws ServletException, IOException {
		String id = (String) request.getSession().getAttribute("loginId");
		int no = Integer.parseInt(request.getParameter("no"));
		
		ProjectDAO dao = new ProjectDAO();
		ProjectDAO dao2 = new ProjectDAO();
		PagingService ls = new PagingService();
		ListObject oldLo = new ListObject();
		ListObject newLo = new ListObject();
		
		int dataCnt = dao2.DataCnt();//데이터 전체 수
		int x = 5;
		int y = 5;
		
		oldLo.setNo(no);
		oldLo.setDataCnt(dataCnt); //데이터 전체수
		oldLo.setX(x);	//한페이지에 몇개
		oldLo.setY(y); //이전과 다음 사이에 몇개의 페이지가 들어있는지
		
		newLo = ls.listPaging(oldLo);//페이징 파라미터
		
		ArrayList<ProjectDTO> dto = dao.projectList2(newLo.getIdx(), x);
		request.setAttribute("select", "좋아요순");
		request.setAttribute("dto", dto);
		request.setAttribute("newLo",newLo);
		RequestDispatcher dis = request.getRequestDispatcher("projectList.jsp");
		dis.forward(request, response);
	}

	/**김응주 - 메인화면 -> 프로젝트 -> 검색필터*/
	public void projectArr() throws ServletException, IOException {
		String id = (String) request.getSession().getAttribute("loginId");
		int no = Integer.parseInt(request.getParameter("no"));
		String choice = request.getParameter("choice");
		ArrayList<ProjectDTO> dto = new ArrayList<ProjectDTO>();
		System.out.println(choice+"초이스값은?");
		
		ProjectDAO dao = new ProjectDAO();
		ProjectDAO dao2 = new ProjectDAO();
		PagingService ls = new PagingService();
		ListObject oldLo = new ListObject();
		ListObject newLo = new ListObject();
		
		int dataCnt = dao2.DataCnt();//데이터 전체 수
		int x = 5;
		int y = 5;
		
		oldLo.setNo(no);
		oldLo.setX(x);	//한페이지에 몇개
		oldLo.setY(y); //이전과 다음 사이에 몇개의 페이지가 들어있는지
		oldLo.setDataCnt(dataCnt); //데이터 전체수
		newLo = ls.listPaging(oldLo);//페이징 파라미터
		if(choice.equals("pick")) {
			dto = dao.projectArrChoice(choice,newLo.getIdx(), x);
			request.setAttribute("choice", "pick");
			request.setAttribute("select", "좋아요순");
		}else if(choice.equals("date")) {
			dto = dao.projectArrChoice(choice,newLo.getIdx(), x);
			request.setAttribute("choice", "date");
			request.setAttribute("select", "최근순");
		}else if(choice.equals("goal")) {
			dto = dao.projectArrChoice(choice,newLo.getIdx(), x);
			request.setAttribute("choice", "goal");
			request.setAttribute("select", "목표금액 달성률순");
		}else if(choice.equals("due")) {
			dto = dao.projectArrChoice(choice,newLo.getIdx(), x);
			request.setAttribute("choice", "due");
			request.setAttribute("select", "마감일순");
		}
		request.setAttribute("addr", "./projectArr");
		request.setAttribute("dto", dto);
		request.setAttribute("newLo",newLo);
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
	public void pickList() throws ServletException, IOException {
		ProjectDAO dao = new ProjectDAO();
		
		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("loginId");
		
		ArrayList<ProjectDTO> picklist = dao.picklist(loginId);
		
		request.setAttribute("picklist", picklist);
		
		RequestDispatcher dis = request.getRequestDispatcher("searchPick.jsp");
		dis.forward(request, response);
	}
	
	public void investList() throws ServletException, IOException {
		ProjectDAO dao = new ProjectDAO();
		
		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("loginId");
		
		ArrayList<ProjectDTO> investlist = dao.investlist(loginId);
		
		request.setAttribute("investlist", investlist);
		
		RequestDispatcher dis = request.getRequestDispatcher("searchInvest.jsp");
		dis.forward(request, response);
		
	}
	
	public void searchList() throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String cg = request.getParameter("category");
		String search = request.getParameter("search");
		
		boolean searchSuccess = false;
		
		ProjectDAO dao = new ProjectDAO();
		
		ArrayList<String> success = dao.searchName();
		for(int i=0; i<success.size(); i++) {
			
			if(success.get(i).contains(search) && !search.isEmpty() && !success.get(i).equals(null)) {
				searchSuccess = true;
				System.out.println(search);
			}
		}

		if(searchSuccess) {
			ArrayList<ProjectDTO> categorylist = dao.searchlist(cg, search);
			
			request.setAttribute("categorylist", categorylist);
			
			RequestDispatcher dis = request.getRequestDispatcher("searchList.jsp");
			dis.forward(request, response);
		} else {
			ArrayList<ProjectDTO> dto = dao.projectList();
			request.setAttribute("dto", dto);
			RequestDispatcher dis = request.getRequestDispatcher("projectList.jsp");
			dis.forward(request, response);
		}
	}

	public void updatePrjState() throws ServletException, IOException {
		ProjectDAO dao = new ProjectDAO();
		dao.updatePrjState_s();
		dao = new ProjectDAO();
		dao.updatePrjState_f();
	}
	
	/**김응주-메인페이지 사진뽑아오기*/
	public void mainbefore(HttpServletRequest request2, HttpServletResponse response2) throws ServletException, IOException {
		ProjectDAO dao = new ProjectDAO();
		ProjectDAO dao1 = new ProjectDAO();
		ProjectDAO dao2 = new ProjectDAO();
		ArrayList<ProjectDTO> dto = dao.mainpick();  
		ArrayList<ProjectDTO> dto1 = dao1.maindate(); 
		ArrayList<ProjectDTO> dto2 = dao2.maindue();   
		request.setAttribute("dto", dto);
		request.setAttribute("size", dto.size());
		request.setAttribute("dto1", dto1);
		request.setAttribute("size1", dto1.size());
		request.setAttribute("dto2", dto2);
		request.setAttribute("size2", dto2.size());
		RequestDispatcher dis = request.getRequestDispatcher("main.jsp");
		dis.forward(request, response);
	}
	
	
/*		*//**김응주 - 페이징(다음)*//*
		public void mainNext() throws ServletException, IOException {
			int start = Integer.parseInt(request.getParameter("start"));
			int end = Integer.parseInt(request.getParameter("end"));
			
			start=start+1;
			end=end+1;
			ProjectDAO dao = new ProjectDAO();
			ArrayList<ProjectDTO> dto = dao.mainbefore(start,end);

			if(dto.size()!=0){
				request.setAttribute("dto", dto);
				request.setAttribute("start", start);
				request.setAttribute("end", end);
				RequestDispatcher dis = request.getRequestDispatcher("main.jsp");
				dis.forward(request, response);
			}else {
				response.sendRedirect("main");
				}
		}
		
		*//**김응주 - 페이징(이전)*//*
		public void mainBack() throws ServletException, IOException {
			int start = Integer.parseInt(request.getParameter("start"));
			int end = Integer.parseInt(request.getParameter("end"));
		
			start=start-1;
			end=end-1;
			ProjectDAO dao = new ProjectDAO();
			ArrayList<ProjectDTO> dto = dao.mainbefore(start,end);
			
			if(end!=0){
				request.setAttribute("dto", dto);
				request.setAttribute("start", start);
				request.setAttribute("end", end);
				RequestDispatcher dis = request.getRequestDispatcher("main.jsp");
				dis.forward(request, response);
			}else {
				response.sendRedirect("main");
			}
		}*/
	}
	

