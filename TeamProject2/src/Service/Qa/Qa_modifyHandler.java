package Service.Qa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.qa.qaDAO;
import Model.qa.qaVO;
import Service.CommandHandler;

public class Qa_modifyHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 페이지 번호와 글 번호를 가져온다.
		String pageNum = request.getParameter("page");
		String num = request.getParameter("idx");
		int boardNum = Integer.parseInt(num);
		qaDAO dao = qaDAO.getInstance();
		qaVO board = dao.getDetail(boardNum);
		
		request.setAttribute("list", board);
		request.setAttribute("idx", num);
		request.setAttribute("page", pageNum);
		return "/qa/qamodify.jsp";
	}

}
