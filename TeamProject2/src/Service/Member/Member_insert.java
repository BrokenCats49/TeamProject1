package Service.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Member.memberVO;
import Service.CommandHandler;

public class Member_insert implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		memberVO vo = (memberVO)session.getAttribute("uvo");
		String url = "/member/member.jsp";
		if(vo!=null) {
			url="index.jsp";
		}
		return url;
	}

}
