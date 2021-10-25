package Service.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Member.memberDAO;
import Model.Member.memberVO;
import Service.CommandHandler;
import Utility.UserSHA256;

public class Member_cashPro implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		memberVO vo = new memberVO();
		String cardnum = request.getParameter("cardnum");
		String cash1 =request.getParameter("charge");
		int cash = Integer.parseInt(cash1);
		vo.setCardnum(request.getParameter("cardnum"));
		vo.setCharge(Integer.parseInt(request.getParameter("charge")));
		memberDAO dao = memberDAO.getInstance();
		int row= dao.cash(cardnum, cash);
		String url = "/member/card.jsp";
		request.setAttribute("row", row);
		
		return url;
	}

}
