package Service.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Member.memberDAO;
import Model.Member.memberVO;
import Service.CommandHandler;
import Utility.UserSHA256;

public class Member_loginPro implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String userid = request.getParameter("id");
		String passwd = request.getParameter("pw");
		passwd = UserSHA256.getSHA256(passwd);
		memberDAO dao = memberDAO.getInstance();
		int row = dao.Userlogin(userid, passwd);
		request.setAttribute("row", row);
		if (row == 1) {
			HttpSession session = request.getSession();
			memberVO vo = dao.memberSession(userid);
			session.setAttribute("uvo", vo);
			session.setMaxInactiveInterval(1800);
			return "/member/login.jsp";
		}else{
			return "/member/login.jsp";
		}
	}

}
