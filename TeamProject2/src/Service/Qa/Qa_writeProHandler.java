package Service.Qa;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Model.qa.qaDAO;
import Model.qa.qaVO;
import Service.CommandHandler;

public class Qa_writeProHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 업로드 파일 사이즈
		int fileSize = 5 * 1024 * 1024;
		ServletContext context = request.getServletContext();
		// 업로드될 폴더 절대경로
		String uploadPath = context.getRealPath("qa/upload");
		System.out.println("BoardWriteAction 파일경로 : " + uploadPath);
		String encType = "UTF-8";
		try {
			// 파일업로드
			MultipartRequest multi = new MultipartRequest(request, uploadPath, fileSize, encType,
					new DefaultFileRenamePolicy());
			// 파일이름 가져오기
			String fileName = "";
			Enumeration<String> names = multi.getFileNames();
			if (names.hasMoreElements()) {
				String name = names.nextElement();
				fileName = multi.getFilesystemName(name);
			}
			qaDAO dao = qaDAO.getInstance();
			qaVO borderData = new qaVO();

			borderData.setIdx(dao.getSeq()); // 시퀀스값 가져와 세팅
			borderData.setUserid(multi.getParameter("userid")); // 히든값
			borderData.setSubject(multi.getParameter("subject"));
			borderData.setKinds(multi.getParameter("kinds"));
			borderData.setPass(multi.getParameter("pass"));
			borderData.setContents(multi.getParameter("contents"));
			borderData.setFilename(fileName);

			boolean result = dao.qaInsert(borderData);
			int row = 99;
			if (result) {
				row = 1;
				request.setAttribute("row", row);
			} else {
				row = 0;
				request.setAttribute("row", row);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("글 작성 오류 : " + e.getMessage());
		}
		return "/qa/qa.jsp";
	}

}
