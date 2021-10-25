package Service.Gallery;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Gallery.galleryDAO;
import Model.Gallery.galleryVO;
import Model.Member.memberDAO;
import Model.Member.memberVO;
import Service.CommandHandler;

public class Gallery_modifyHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int idx = Integer.parseInt(request.getParameter("idx"));
		int page = Integer.parseInt(request.getParameter("page"));
		HttpSession session = request.getSession();
		memberDAO dao2 = memberDAO.getInstance();
		memberVO vo2 = (memberVO)session.getAttribute("uvo");
		String url="/index.jsp";
		if(vo2!=null) {
			memberVO vo3 = dao2.memberSelect(vo2.getUserid());
			request.setAttribute("mvo", vo3);
			url="/gallery/gallerymodify.jsp";
		}
		galleryDAO dao = galleryDAO.getInstance();
		galleryVO vo = dao.galleryView(idx);
		request.setAttribute("vo", vo);
		request.setAttribute("page", page);
		request.setAttribute("idx", idx);
		
		return url;
	}

}
