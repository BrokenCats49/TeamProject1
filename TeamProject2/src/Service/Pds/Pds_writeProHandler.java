package Service.Pds;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Model.Pds.pdsDAO;
import Model.Pds.pdsVO;
import Service.CommandHandler;

public class Pds_writeProHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		ServletContext context = request.getServletContext(); //현재 경로파악을 위해 객체생성
		String path = context.getRealPath("portfolio/upload"); //파일 저장경로 설정 (그전에 미리 폴더를 만들어놔야함)
		String encType = "UTF-8";
		int sizeLimit = 5*1024*1024; //파일 최대 용량설정(5M) 파일한개당 용량
		MultipartRequest multi = 
				new MultipartRequest(request, path, sizeLimit, encType, new DefaultFileRenamePolicy());
		pdsVO vo = new pdsVO();
		vo.setName(multi.getParameter("name"));
		vo.setUserid(multi.getParameter("userid"));
		vo.setPass(multi.getParameter("pass"));
		vo.setSubject(multi.getParameter("subject"));
		vo.setContents(multi.getParameter("contents"));
		vo.setFilename(multi.getFilesystemName("filename")); //실제 파일이름
		int page = Integer.parseInt(multi.getParameter("page"));
		pdsDAO dao = pdsDAO.getInstance();
		int row = dao.pdsWrite(vo);
		request.setAttribute("row", row);
		request.setAttribute("page", page);

		return "/portfolio/portfolio.jsp";
	}

}
