package Service.Pds;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Member.memberDAO;
import Model.Member.memberVO;
import Model.Pds.pdsDAO;
import Model.Pds.pdsVO;
import Service.CommandHandler;

public class Pds_viewHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int idx = Integer.parseInt(request.getParameter("idx"));
		int page = Integer.parseInt(request.getParameter("page"));
		HttpSession session = request.getSession();
		memberDAO dao2 = memberDAO.getInstance();
		memberVO vo2 = (memberVO)session.getAttribute("uvo");
		if(vo2!=null) {
			memberVO vo3 = dao2.memberSelect(vo2.getUserid());
			request.setAttribute("mvo", vo3);

		}
		pdsDAO dao = pdsDAO.getInstance();
		//쿠키
		boolean bool = false;
		Cookie info = null;
		Cookie[] cookies = request.getCookies();
		for(int x=0; x<cookies.length; x++) {
			info = cookies[x];
			if(info.getName().equals("PDS"+idx)) {
				bool=true;
				break;
			}
		}
		if(!bool) {
			String value=""+System.currentTimeMillis();
			info = new Cookie("PDS"+idx,value);
			info.setMaxAge(120);
			response.addCookie(info);
			dao.pdsHits(idx);
		}
		pdsVO vo = dao.pdsview(idx);
		vo.setContents(vo.getContents().replace("\n", "<br>"));
		request.setAttribute("pvo", vo);
		request.setAttribute("page", page);
		request.setAttribute("idx", idx);
		
		return "/portfolio/portfolioview.jsp";
	}

}
