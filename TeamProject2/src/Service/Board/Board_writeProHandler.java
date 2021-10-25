package Service.Board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Board.boardDAO;
import Model.Board.boardVO;
import Model.Member.memberDAO;
import Model.Member.memberVO;
import Service.CommandHandler;

public class Board_writeProHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		int page = Integer.parseInt(request.getParameter("page"));
		HttpSession session = request.getSession();
		memberDAO dao2 = memberDAO.getInstance();
		memberVO vo2 = (memberVO)session.getAttribute("uvo");
		boardVO vo = new boardVO();
		if(vo2!=null) {
			String userid = vo2.getUserid();
			vo.setUserid(userid);
		}else {
			vo.setUserid(null);
		}
		vo.setName(request.getParameter("name"));
		vo.setPasswd(request.getParameter("pass"));
		vo.setSubject(request.getParameter("subject"));
		vo.setContents(request.getParameter("contents"));
		boardDAO dao = boardDAO.getInstance();
		int row = dao.boardWrite(vo);
		request.setAttribute("page", page);
		request.setAttribute("row", row);

		return "/board/board.jsp";
	}

}
