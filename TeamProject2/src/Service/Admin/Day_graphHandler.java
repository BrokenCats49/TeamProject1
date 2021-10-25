package Service.Admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Admin.Acount.AccountDAO;
import Model.Admin.Acount.AccountVO;
import Service.CommandHandler;

public class Day_graphHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		AccountDAO dao = AccountDAO.getInstance();
		String url = "day_graph.do"; 
		String date = null;
		AccountVO vo = new AccountVO();
		List<AccountVO> list = new ArrayList<AccountVO>();
		list = dao.DayList();
		if(request.getParameter("date") != null) {
			date = request.getParameter("date");
			vo = dao.totalsearch(date);
		}else {
			vo = dao.totalsearch();
		}
		request.setAttribute("vo", vo);
		request.setAttribute("date", date);
		request.setAttribute("list", list);
	return "/Admin/acount/day_graph.jsp";
	
	}

}
