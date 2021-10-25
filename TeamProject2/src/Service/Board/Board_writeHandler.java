package Service.Board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Member.memberDAO;
import Model.Member.memberVO;
import Service.CommandHandler;

public class Board_writeHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		memberDAO dao2 = memberDAO.getInstance();
		memberVO vo = (memberVO)session.getAttribute("uvo");
		if(vo!=null) {
			memberVO vo2 = dao2.memberSelect(vo.getUserid());
			request.setAttribute("mvo", vo2);
		}
		int page = 1;
		if(request.getParameter("page")!=null && 
				!request.getParameter("page").equals("")){
			page=Integer.parseInt(request.getParameter("page"));
		}
		request.setAttribute("page", page);
		
		return "/board/boardwrite.jsp";
	}

}
