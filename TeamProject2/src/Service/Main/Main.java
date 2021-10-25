package Service.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Gallery.galleryDAO;
import Model.Gallery.galleryVO;
import Model.Member.memberVO;
import Model.Notic.noticeDAO;
import Model.Notic.noticeVO;
import Model.qa.qaDAO;
import Model.qa.qaVO;
import Service.CommandHandler;

public class Main implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		memberVO vo = (memberVO)session.getAttribute("uvo");
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
		
		qaDAO dao2 = qaDAO.getInstance();
		int listCount = dao2.getQaListCount(listOpt);
		ArrayList<qaVO> list2 =  dao2.getQaList(listOpt);
		galleryDAO dao = galleryDAO.getInstance();
		noticeDAO dao3 = noticeDAO.getInstance();
		List<noticeVO> list3 = dao3.noticeList();
		List<galleryVO> list = dao.galleryList();
		request.setAttribute("list", list);
		request.setAttribute("page", spage);
		request.setAttribute("list2", list2);
		request.setAttribute("list3", list3);
		return "/index.jsp";
	}

}
