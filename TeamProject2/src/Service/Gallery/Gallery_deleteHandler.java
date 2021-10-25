package Service.Gallery;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Member.memberDAO;
import Model.Member.memberVO;
import Service.CommandHandler;

public class Gallery_deleteHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int idx = Integer.parseInt(request.getParameter("idx"));
		int page = Integer.parseInt(request.getParameter("page"));
		HttpSession session = request.getSession();
		memberDAO dao = memberDAO.getInstance();
		memberVO vo = (memberVO)session.getAttribute("uvo");
		String url="/index.jsp";
		if(vo!=null) {
			memberVO vo2 = dao.memberSelect(vo.getUserid());
			request.setAttribute("mvo", vo2);
			url="/gallery/gallerydelete.jsp";
		}
		request.setAttribute("idx", idx);
		request.setAttribute("page", page);
		return url;
	}

}
