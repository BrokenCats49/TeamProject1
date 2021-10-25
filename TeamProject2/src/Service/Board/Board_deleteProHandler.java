package Service.Board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Board.boardDAO;
import Service.CommandHandler;

public class Board_deleteProHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int idx = Integer.parseInt(request.getParameter("idx"));
		int page = Integer.parseInt(request.getParameter("page"));
		String pass = request.getParameter("pass");
		
		boardDAO dao = boardDAO.getInstance();
		int row = dao.boardDelete(idx,pass);
		
		request.setAttribute("row", row);
		request.setAttribute("page", page);

	
		return "/board/boarddelete.jsp";
	}

}
