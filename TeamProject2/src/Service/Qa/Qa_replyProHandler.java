package Service.Qa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.qa.qaDAO;
import Model.qa.qaVO;
import Service.CommandHandler;

public class Qa_replyProHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		qaDAO dao = qaDAO.getInstance();
		qaVO borderData = new qaVO();
		
		// 답글 작성 후 원래 페이지로 돌아가기 위해 페이지 번호가 필요하다.
		String pageNum = request.getParameter("page");
		
		// 파리미터 값을 가져온다.
		int num = Integer.parseInt(request.getParameter("idx"));
		String id = request.getParameter("userid");
		String subject = request.getParameter("subject");
		String kinds = request.getParameter("kinds");
		String content = request.getParameter("contents");
		String pass = request.getParameter("pass");
		int ref = Integer.parseInt(request.getParameter("reseq"));

		// 답글 저장
		borderData.setIdx(dao.getSeq()); // 답글의 글번호는 시퀀스값 가져와 세팅
		borderData.setKinds(kinds); // 답글의 글번호는 시퀀스값 가져와 세팅
		borderData.setUserid(id);
		borderData.setSubject(subject);
		borderData.setContents(content);
		borderData.setRepnum(ref);
		borderData.setPass(pass);
		borderData.setQaparent(num);	// 부모글의 글번호를 저장
		
		boolean result = dao.qaInsert(borderData);
		int row = 99;
		if (result) {
			row = 1;
			request.setAttribute("row", row);
			return "/qa/qaview.jsp";
		} else {
			row = 0;
			request.setAttribute("row", row);
			return "/qa/qaview.jsp";
		}

	}

}
