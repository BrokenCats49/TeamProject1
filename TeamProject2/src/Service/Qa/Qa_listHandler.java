package Service.Qa;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Member.memberDAO;
import Model.Member.memberVO;
import Model.qa.qaDAO;
import Model.qa.qaVO;
import Service.CommandHandler;

public class Qa_listHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		memberDAO dao2 = memberDAO.getInstance();
		memberVO vo = (memberVO)session.getAttribute("uvo");
		if(vo!=null) {
			memberVO vo2 = dao2.memberSelect(vo.getUserid());
			request.setAttribute("mvo", vo2);
		}
		
		// 현재 페이지 번호 만들기
		int spage = 1;
		String page = request.getParameter("page");

		if(page != null)	spage = Integer.parseInt(page);
		
		// 검색조건과 검색내용을 가져온다.
		String opt = request.getParameter("opt");
		String condition = request.getParameter("condition");
		
		// 검색조건과 내용을 Map에 담는다.
		HashMap<String, Object> listOpt = new HashMap<String, Object>();
		listOpt.put("opt", opt);
		listOpt.put("condition", condition);
		listOpt.put("start", spage*10-9);
		
		qaDAO dao = qaDAO.getInstance();
		int listCount = dao.getQaListCount(listOpt);
		ArrayList<qaVO> list =  dao.getQaList(listOpt);
		// 한 화면에 10개의 게시글을 보여지게함
		// 페이지 번호는 총 5개, 이후로는 [다음]으로 표시
		// 전체 페이지 수
		int maxPage = (int)(listCount/10.0 + 0.9);
		//시작 페이지 번호
		int startPage = (int)(spage/5.0 + 0.8) * 5 - 4;
		//마지막 페이지 번호
		int endPage = startPage + 4;
		if(endPage > maxPage)	endPage = maxPage;
		// 4개 페이지번호 저장
		request.setAttribute("spage", spage);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		// 글의 총 수와 글목록 저장
		//request.setAttribute("listCount", listCount);
		request.setAttribute("list", list);
		// 단순 조회이므로 forward()사용 (= DB의 상태변화 없으므로) 
		return "/qa/qa.jsp";
	}

}
