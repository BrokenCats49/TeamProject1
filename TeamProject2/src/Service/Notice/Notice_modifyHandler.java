package Service.Notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Member.memberDAO;
import Model.Member.memberVO;
import Model.Notic.noticeDAO;
import Model.Notic.noticeVO;
import Service.CommandHandler;

public class Notice_modifyHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int idx = Integer.parseInt(request.getParameter("idx"));
		int page = Integer.parseInt(request.getParameter("page"));
		HttpSession session = request.getSession();
		memberDAO dao2 = memberDAO.getInstance();
		memberVO vo2 = (memberVO)session.getAttribute("uvo");
		String url="/login.do";
		if(vo2!=null) {
			if(vo2.getUserid().equals("sub3815")) {
				memberVO vo3 = dao2.memberSelect(vo2.getUserid());
				request.setAttribute("mvo", vo3);
				url="/notice/noticemodify.jsp";
			}
		}
		noticeDAO dao = noticeDAO.getInstance();
		noticeVO vo = dao.noticeview(idx);
		request.setAttribute("vo", vo);
		request.setAttribute("page", page);
		request.setAttribute("idx", idx);
		
		return url;
	}

}
