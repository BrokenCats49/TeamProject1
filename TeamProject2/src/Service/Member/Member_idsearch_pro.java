package Service.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Member.memberDAO;
import Service.CommandHandler;

public class Member_idsearch_pro implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String names = request.getParameter("name");
		String emailes = request.getParameter("email");
		
		memberDAO dao = memberDAO.getInstance();
		String userid = dao.id_search2(names, emailes);
		int row=99;
		if(userid != null) {
			row= 1;
			request.setAttribute("row", row);
			request.setAttribute("userid", userid);
		}else {
			row=0;
			request.setAttribute("row", row);
			request.setAttribute("userid", userid);
		}
		return "/member/id_search.jsp";
	}

}
