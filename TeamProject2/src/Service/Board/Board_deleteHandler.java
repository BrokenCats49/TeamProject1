package Service.Board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.CommandHandler;

public class Board_deleteHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int idx = Integer.parseInt(request.getParameter("idx"));
		int page = Integer.parseInt(request.getParameter("page"));				
		request.setAttribute("idx", idx);
		request.setAttribute("page", page);
		return "/board/boarddelete.jsp";
	}

}
