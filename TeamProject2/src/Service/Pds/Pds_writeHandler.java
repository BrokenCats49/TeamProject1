package Service.Pds;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Member.memberDAO;
import Model.Member.memberVO;
import Service.CommandHandler;

public class Pds_writeHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int page = Integer.parseInt(request.getParameter("page"));
		request.setAttribute("page", page);
		HttpSession session = request.getSession();
		memberDAO dao = memberDAO.getInstance();
		memberVO vo = (memberVO)session.getAttribute("uvo");
		String url="/portfolio/portfolio.jsp";
		if(vo!=null) {
			memberVO vo2 = dao.memberSelect(vo.getUserid());
			request.setAttribute("mvo", vo2);
			url="/portfolio/portfoliowrite.jsp";
			
		}
		return url;
	}

}
