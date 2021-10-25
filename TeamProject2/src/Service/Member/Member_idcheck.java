package Service.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Member.memberDAO;
import Service.CommandHandler;

public class Member_idcheck implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		memberDAO dao = memberDAO.getInstance();
		String userid = "";
		if(request.getParameter("userid")!=null) {
			userid=request.getParameter("userid");
		}
		int row = dao.UseridCheck(userid);
		request.setAttribute("userid", userid);
		request.setAttribute("row", row);
		return "/member/id_check.jsp";
	}

}
