package Service.Pds;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Member.memberDAO;
import Model.Member.memberVO;
import Model.Pds.pdsDAO;
import Model.Pds.pdsVO;
import Service.CommandHandler;
import Utility.PageIndex;

public class Pds_listHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		memberDAO dao2 = memberDAO.getInstance();
		memberVO vo = (memberVO)session.getAttribute("uvo");
		if(vo!=null) {
			memberVO vo2 = dao2.memberSelect(vo.getUserid());
			request.setAttribute("mvo", vo2);
		}
		pdsDAO dao = pdsDAO.getInstance();
		String s_query ="", search="", key="";
		String url="/pdslist.do";
		int totcount=0;
		if(request.getParameter("key")!=null) {
			key=request.getParameter("key");
			search=request.getParameter("search");
			s_query=search+" like '%" + key + "%'";
			totcount = dao.pdsCount(s_query);
		}else {
			totcount = dao.pdsCount();
		}
		int nowpage = 1;
		int maxlist = 10;
		int totpage = 1;
		if(totcount% maxlist ==0) {
			totpage=totcount/maxlist;
		}else {
			totpage=totcount/maxlist+1;
		}
		if(request.getParameter("page") != null) {
			nowpage=Integer.parseInt(request.getParameter("page"));
		}
		int startpage = (nowpage-1) * maxlist + 1;
		int endpage = nowpage * maxlist;
		int listcount = totcount - ((nowpage-1)*maxlist); //list에서 번호출력용
		List<pdsVO> list = null;
		if(key.equals("")) {
			list = dao.pdsList(startpage, endpage);
		}else {
			list = dao.pdsList(s_query, startpage, endpage);
		}
		String pageskip="";
		if(key.equals("")) {
			pageskip = PageIndex.pageList(nowpage, totpage, url, "");
		}else {
			pageskip = PageIndex.pageListHan(nowpage, totpage, url, search, key);
		}
		request.setAttribute("page", nowpage);
		request.setAttribute("totcount", totcount);
		request.setAttribute("totpage", totpage);
		request.setAttribute("list2", list);
		request.setAttribute("listcount", listcount);
		request.setAttribute("pageskip", pageskip);
		request.setAttribute("search", search);
		request.setAttribute("key", key);
		
		return "/portfolio/portfolio.jsp";
	}

}
