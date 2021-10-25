package Service.Pds;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Pds.pdsDAO;
import Model.Pds.pdsVO;
import Service.CommandHandler;

public class Pds_deleteProHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		ServletContext context = request.getServletContext(); //현재 경로파악을 위해 객체생성
		String path = context.getRealPath("portfolio/upload"); //파일 저장경로 설정 (그전에 미리 폴더를 만들어놔야함)
		int idx = Integer.parseInt(request.getParameter("idx"));
		int page = Integer.parseInt(request.getParameter("page"));
		String userid = request.getParameter("userid");
		String passw = request.getParameter("pass");
		pdsVO vo = new pdsVO();
		vo.setIdx(idx);
		vo.setPass(passw);
		vo.setUserid(userid);
		pdsDAO dao = pdsDAO.getInstance();
		pdsVO v2 =dao.pdsview(idx);
		int row = dao.pdsDelete(vo);
		if(row==1) {
		String oldfilename=v2.getFilename();
		System.out.println(v2.getFilename());
			if(oldfilename != null) {
				File file = new File(path, oldfilename);
				if(file.exists()) {
					file.delete();
				}
			}
		}
		request.setAttribute("page", page);
		request.setAttribute("row", row);
		
		return "/portfolio/pdsdelete.jsp";
	}

}
