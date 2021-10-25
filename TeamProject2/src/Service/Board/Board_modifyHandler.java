package Service.Board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Board.boardDAO;
import Model.Board.boardVO;
import Service.CommandHandler;

public class Board_modifyHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int idx = Integer.parseInt(request.getParameter("idx"));
		int page = Integer.parseInt(request.getParameter("page"));
		boardDAO dao = boardDAO.getInstance();
		boardVO board = dao.boardSelect(idx);
		
		request.setAttribute("page", page);
		request.setAttribute("board", board);
		
		
		return "/board/boardmodify.jsp";
	}

}
