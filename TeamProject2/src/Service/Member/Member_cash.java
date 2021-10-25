package Service.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Member.memberDAO;
import Model.Member.memberVO;
import Service.CommandHandler;

public class Member_cash implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		memberDAO dao = memberDAO.getInstance();
		memberVO vo = (memberVO)session.getAttribute("uvo");
		String url="/index.jsp";
		if(vo!=null) {
			memberVO vo2 = dao.memberSelect(vo.getUserid());
			request.setAttribute("mvo", vo2);
			memberVO vo3 = dao.cashCheck(vo2.getCardnum());
			request.setAttribute("cvo", vo3);
			url="/member/card.jsp";
			
		}
		
		return url;
	}

}
