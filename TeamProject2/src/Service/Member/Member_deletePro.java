package Service.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Member.memberDAO;
import Model.Member.memberVO;
import Service.CommandHandler;
import Utility.UserSHA256;

public class Member_deletePro implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String userid = request.getParameter("userid");
		String passw = UserSHA256.getSHA256(request.getParameter("pass"));
		memberVO vo = new memberVO();
		vo.setUserid(userid);
		vo.setPasswd(passw);
		memberDAO dao = memberDAO.getInstance();
		int row = dao.Delete(vo);
		if(row==1) {
			HttpSession session = request.getSession();
			session.invalidate();
		}
		request.setAttribute("row", row);
		return "/member/delete.jsp";
	}

}
