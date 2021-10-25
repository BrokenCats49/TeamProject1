package Service.Admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Admin.Order.OrderDAO;
import Model.Admin.Order.OrderVO;
import Service.CommandHandler;
import Utility.PageIndex;

public class Order_hunwang_Handler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		OrderDAO dao = OrderDAO.getInstance();
		String s_query = "", addtag = "", query = "", key = "";

		int totcount = 0; // 게시글 총수
		// post 방식(검색일 경우)
		if (request.getParameter("key") != null) {
			// key = URLDecoder.decode(request.getParameter("key"));
			key = request.getParameter("key");
			query = request.getParameter("search");
			s_query = query + " like '%" + key + "%'";
			addtag = "&search=" + query + "&key=" + key;
			totcount = dao.order_listCount(s_query);
		} else {
			totcount = dao.order_listCount();
		}

		int nowpage = 1; // 현재 페이지
		int maxlist = 10; // 페이지당 글 수
		int totpage = 1; // 총 페이지수

		if (totcount % maxlist == 0) {
			totpage = totcount / maxlist;
		} else {
			totpage = totcount / maxlist + 1;
		}

		if (request.getParameter("page") != null) {
			nowpage = Integer.parseInt(request.getParameter("page"));
		}

		int pagestart = (nowpage - 1) * maxlist + 1;
		int endpage = nowpage * maxlist;
		int listcount = totcount - ((nowpage - 1) * maxlist);

		List<OrderVO> list = null;
		if (key.equals("")) {
			list = dao.order_List(pagestart, endpage);
		} else {
			list = dao.order_List(s_query, pagestart, endpage);
		}
		String pageSkip = "";
		if (key.equals("")) {
			pageSkip = PageIndex.pageList(nowpage, totpage, "order_hunwang.do", addtag);
		} else {
			pageSkip = PageIndex.pageListHan(nowpage, totpage, "order_hunwang.do", query, key);
		}
		request.setAttribute("totcount", totcount);
		request.setAttribute("list", list);
		request.setAttribute("listcount", listcount);
		request.setAttribute("page", nowpage);
		request.setAttribute("totpage", totpage);
		request.setAttribute("pageSkip", pageSkip);
		request.setAttribute("search", query);
		request.setAttribute("key", key);

		return "/Admin/order/order_hunwang.jsp";
	}

}
