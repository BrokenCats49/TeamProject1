package Service.Board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Board.boardDAO;
import Model.Board.boardVO;
import Service.CommandHandler;

public class Board_modifyProHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		int page = Integer.parseInt(request.getParameter("page"));
		int idx = Integer.parseInt(request.getParameter("idx"));
		boardVO vo = new boardVO();
		vo.setIdx(idx);
		vo.setSubject(request.getParameter("subject"));
		vo.setContents(request.getParameter("contents"));
		vo.setPasswd(request.getParameter("pass"));
		
		boardDAO dao = boardDAO.getInstance();
		int row = dao.boardUpdate(vo);
		
		request.setAttribute("page", page);
		request.setAttribute("row", row);
		request.setAttribute("idx", idx);
		
		return "/board/boardmodify.jsp";
	}

}
