package Service.Pds;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Member.memberDAO;
import Model.Member.memberVO;
import Model.Pds.pdsDAO;
import Model.Pds.pdsVO;
import Service.CommandHandler;

public class Pds_modifyHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int idx = Integer.parseInt(request.getParameter("idx"));
		int page = Integer.parseInt(request.getParameter("page"));
		HttpSession session = request.getSession();
		memberDAO dao2 = memberDAO.getInstance();
		memberVO vo2 = (memberVO)session.getAttribute("uvo");
		String url="/login.do";
		if(vo2!=null) {
			memberVO vo3 = dao2.memberSelect(vo2.getUserid());
			request.setAttribute("mvo", vo3);
			url="/portfolio/portfoliomodify.jsp";
		}
		pdsDAO dao = pdsDAO.getInstance();
		pdsVO vo = dao.pdsview(idx);
		request.setAttribute("vo", vo);
		request.setAttribute("page", page);
		request.setAttribute("idx", idx);
		
		return url;
	}

}
