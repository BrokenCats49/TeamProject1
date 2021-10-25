package Service.Admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Admin.Acount.AccountDAO;
import Model.Admin.Acount.AccountVO;
import Service.CommandHandler;

public class Month_graphHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		AccountDAO dao = AccountDAO.getInstance();
		String url = "day_month.do"; 
		String date = null;
		AccountVO vo = new AccountVO();
		List<AccountVO> list, list2 = new ArrayList<AccountVO>();
		list = dao.MonthList();
		if(request.getParameter("month") != null) {
			date = request.getParameter("month");
			list2 = dao.totalmsearch(date);
		}else {
			list2 = dao.totalmsearch();
		}
		request.setAttribute("month", date);
		request.setAttribute("list", list2);
	return "/Admin/acount/month_graph.jsp";
	
	}

}
