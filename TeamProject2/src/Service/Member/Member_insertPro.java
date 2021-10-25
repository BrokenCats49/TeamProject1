package Service.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Member.memberDAO;
import Model.Member.memberVO;
import Service.CommandHandler;
import Utility.UserSHA256;

public class Member_insertPro implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		memberVO vo = new memberVO();
		String passwd = UserSHA256.getSHA256(request.getParameter("pass1"));
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String email = email1+"@"+email2;
		String cardnum = request.getParameter("cardnum");
		vo.setName(request.getParameter("name"));
		vo.setUserid(request.getParameter("id"));
		vo.setEmail(email);
		vo.setPasswd(passwd);
		if(cardnum.equals("")) {
			vo.setCardnum("");
		}else {
			vo.setCardnum(cardnum);
		}
		memberDAO dao = memberDAO.getInstance();
		int row= dao.memberWR(vo);
		String url = "/member/member.jsp";
		request.setAttribute("row", row);
		
		return url;
	}

}
