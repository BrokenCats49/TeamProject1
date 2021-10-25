package Service.Board;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Board.boardDAO;
import Model.Board.boardVO;
import Service.CommandHandler;

public class Board_viewHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int idx = Integer.parseInt(request.getParameter("idx"));
		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		boardDAO dao = boardDAO.getInstance();
		// 조회수 제한 쿠키
		boolean bool = false;
		Cookie info = null;
		Cookie[] cookies = request.getCookies();
		for (int x = 0; x < cookies.length; x++) {
			info = cookies[x];
			if (info.getName().equals("Board" + idx)) {
				bool = true;
				break;
			}
		}
		if (!bool) {
			String value = "" + System.currentTimeMillis();
			info = new Cookie("Board" + idx, value);
			info.setMaxAge(120);
			response.addCookie(info);
			dao.boardHits(idx);
		}
		boardVO board = dao.boardSelect(idx);
		String content = board.getContents();
		board.setContents(content.replace("\n", "<br>"));
		request.setAttribute("board", board);
		request.setAttribute("page", page);
		request.setAttribute("idx", idx);

		return "/board/boardview.jsp";
	}

}
