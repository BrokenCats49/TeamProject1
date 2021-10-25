package Service.Qa;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Member.memberDAO;
import Model.Member.memberVO;
import Model.qa.qaDAO;
import Model.qa.qaVO;
import Service.CommandHandler;

public class Qa_viewHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String num = request.getParameter("idx");
		int idx = Integer.parseInt(num);
		
		int page = Integer.parseInt(request.getParameter("page"));
		HttpSession session = request.getSession();
		memberDAO dao2 = memberDAO.getInstance();
		memberVO vo2 = (memberVO)session.getAttribute("uvo");
		if(vo2!=null) {
			memberVO vo3 = dao2.memberSelect(vo2.getUserid());
			request.setAttribute("mvo", vo3);
		}
		qaDAO dao = qaDAO.getInstance();
			boolean result = false;
		//쿠키
			boolean bool = false;
			Cookie info = null;
			Cookie[] cookies = request.getCookies();
			for(int x=0; x<cookies.length; x++) {
				info = cookies[x];
				if(info.getName().equals("qa"+idx)) {
					bool=true;
					break;
				}
			}
			if(!bool) {
				String value=""+System.currentTimeMillis();
				info = new Cookie("qa"+idx,value);
				info.setMaxAge(120);
				response.addCookie(info);
				result = dao.updateCount(idx);
			}
		qaVO vo = dao.getDetail(idx);
		vo.setContents(vo.getContents().replace("\n", "<br>"));
		request.setAttribute("qvo", vo);
		request.setAttribute("idx", num);
		request.setAttribute("page", page);
		
		if(result){
			return "/qa/qaview.jsp";
		}else {
			return "/qa/qaview.jsp";
		}
	}

}
