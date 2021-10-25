package Service.Gallery;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.descriptor.web.MultipartDef;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Model.Gallery.galleryDAO;
import Model.Gallery.galleryVO;
import Service.CommandHandler;



public class Gallery_modifyProHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		ServletContext context = request.getServletContext(); //현재 경로파악을 위해 객체생성
		String path = context.getRealPath("gallery/upload"); //파일 저장경로 설정 (그전에 미리 폴더를 만들어놔야함)
		String encType = "UTF-8";
		int sizeLimit = 5*1024*1024; //파일 최대 용량설정(5M) 파일한개당 용량
		MultipartRequest multi = 
				new MultipartRequest(request, path, sizeLimit, encType, new DefaultFileRenamePolicy());
		galleryVO vo = new galleryVO();
		int idx = Integer.parseInt(multi.getParameter("idx"));
		int page = Integer.parseInt(multi.getParameter("page"));
		vo.setIdx(Integer.parseInt(multi.getParameter("idx")));
		vo.setName(multi.getParameter("name"));
		vo.setPasswd(multi.getParameter("passwd"));
		vo.setSubject(multi.getParameter("subject"));
		vo.setContents(multi.getParameter("contents"));
		vo.setFilename(multi.getFilesystemName("filename")); //서버에다 파일을 저장해주고 이름을 불러와라
		String oldfilename = multi.getParameter("oldfilename");	// hidden으로 받아오는거임 
		galleryDAO dao = galleryDAO.getInstance();
		if(vo.getFilename() == null) {
			vo.setFilename(oldfilename);
		}else {
			File file = new File(path, oldfilename);
			if(file.exists())
				file.delete();
		}
		//수정 처리 메소드 호출
		int row = dao.galleryModify(vo);
		request.setAttribute("row", row);
		request.setAttribute("page", page);
		request.setAttribute("idx", idx);
		return "/gallery/gallery-view.jsp";
	}

}
