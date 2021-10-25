package Service.Gallery;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Gallery.galleryDAO;
import Model.Gallery.galleryVO;
import Model.Member.memberDAO;
import Model.Member.memberVO;
import Service.CommandHandler;

public class Gallery_viewHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		memberDAO dao2 = memberDAO.getInstance();
		memberVO vo2 = (memberVO)session.getAttribute("uvo");
		if(vo2!=null) {
			memberVO vo3 = dao2.memberSelect(vo2.getUserid());
			request.setAttribute("mvo", vo3);
		}
		int idx = Integer.parseInt(request.getParameter("idx"));
		int page = Integer.parseInt(request.getParameter("page"));
		galleryDAO dao = galleryDAO.getInstance();
		//조회수 제한 쿠키
		boolean bool = false;
		Cookie info = null;
		Cookie[] cookies = request.getCookies();
		for (int x = 0; x < cookies.length; x++) {
			info = cookies[x];
			if (info.getName().equals("Gallery" + idx)) {
				bool = true;
				break;
			}
		}
		if (!bool) {
			String value = "" + System.currentTimeMillis();
			info = new Cookie("Gallery" + idx, value);
			info.setMaxAge(120);
			response.addCookie(info);
			dao.galleryHits(idx);
		}
		galleryVO vo = dao.galleryView(idx);
		vo.setContents(vo.getContents().replace("\n", "<br>"));
		request.setAttribute("gvo", vo);
		request.setAttribute("page", page);
		request.setAttribute("idx", idx);
		return "/gallery/gallery-view.jsp";
	}

}
