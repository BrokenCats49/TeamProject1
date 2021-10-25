package Service.Admin;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Admin.Card.CardDAO;
import Model.Admin.Card.CardVO;
import Service.CommandHandler;

public class Card_Record_Handler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		CardDAO dao = CardDAO.getInstance();
		String s_query = "", addtag = "", query = "", key = "";

		int totcount = 0;
		if (request.getParameter("key") != null) {
			key = request.getParameter("key");
			query = request.getParameter("search");
			s_query = query + " like '%" + key + "%'";
			totcount = dao.card_record_count(s_query);
		} else {
			totcount = dao.card_record_count();
		}
		
		List<CardVO> list = null;
		if (key.equals("")) {
			list = dao.card_record_list();
		} else {
			list = dao.card_record_list(s_query);
		}
		request.setAttribute("totcount", totcount);
		request.setAttribute("list", list);
		request.setAttribute("search", query);
		request.setAttribute("key", key);
		
		return "/Admin/machine/card_record_list.jsp";
	}

}
