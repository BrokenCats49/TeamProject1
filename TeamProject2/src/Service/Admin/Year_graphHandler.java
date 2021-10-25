package Service.Admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Admin.Acount.AccountDAO;
import Model.Admin.Acount.AccountVO;
import Service.CommandHandler;

public class Year_graphHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		AccountDAO dao = AccountDAO.getInstance();
		String url = "year_month.do"; 
		String date = null;
		AccountVO vo = new AccountVO();
		List<AccountVO> list, list2 = new ArrayList<AccountVO>();
		list = dao.MonthList();
		if(request.getParameter("year") != null) {
			date = request.getParameter("year");
			list2 = dao.totalysearch(date);
		}else {
			list2 = dao.totalysearch();
		}
		request.setAttribute("year", date);
		request.setAttribute("list", list2);
	return "/Admin/acount/year_graph.jsp";
	
	}

}
