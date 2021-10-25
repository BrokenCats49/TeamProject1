package Service.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Member.memberDAO;
import Model.Member.memberVO;
import Service.CommandHandler;
import Utility.UserSHA256;

public class Member_modifyPro implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("id");
		memberDAO dao = memberDAO.getInstance();
		memberVO vo = new memberVO();
		String repass = UserSHA256.getSHA256(request.getParameter("pass1"));
		vo.setPasswd(repass);
		vo.setEmail(request.getParameter("email"));
		vo.setCardnum(request.getParameter("cardnum"));
		String url = "/member/modify.jsp";
		int row = dao.memberUpdate(userid,vo);
		request.setAttribute("mrow", row);
		if(row==1) {
			return url;
		}
		return url;
	}

}
