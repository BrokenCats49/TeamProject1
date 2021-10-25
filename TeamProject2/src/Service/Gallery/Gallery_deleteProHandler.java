package Service.Gallery;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Gallery.galleryDAO;
import Model.Gallery.galleryVO;
import Service.CommandHandler;

public class Gallery_deleteProHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		ServletContext context = request.getServletContext(); //현재 경로파악을 위해 객체생성
		String path = context.getRealPath("gallery/upload"); //파일 저장경로 설정 (그전에 미리 폴더를 만들어놔야함)
		int idx = Integer.parseInt(request.getParameter("idx"));
		int page = Integer.parseInt(request.getParameter("page"));
		String passw = request.getParameter("pass");
		galleryVO vo = new galleryVO();
		vo.setIdx(idx);
		vo.setPasswd(passw);
		galleryDAO dao = galleryDAO.getInstance();
		galleryVO v2 =dao.galleryView(idx);
		int row = dao.galleryDelete(vo);
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
		return "/gallery/gallerydelete.jsp";
	}

}
