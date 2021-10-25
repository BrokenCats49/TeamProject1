package Service.Qa;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Model.qa.qaDAO;
import Model.qa.qaVO;
import Service.CommandHandler;

public class Qa_modifyProHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 답글 작성 후 원래 페이지로 돌아가기 위해 페이지 번호가 필요하다.
		

		// 업로드 파일 사이즈
		int fileSize = 5 * 1024 * 1024;
		// 업로드될 폴더 절대경로
		String uploadPath = request.getServletContext().getRealPath("/qa/upload");

		try {
			MultipartRequest multi = new MultipartRequest(request, uploadPath, fileSize, "utf-8",
					new DefaultFileRenamePolicy());
			String pageNum = multi.getParameter("page");
			// 파리미터 값을 가져온다.
			int num = Integer.parseInt(multi.getParameter("idx")); // 글 번호
			String subject = multi.getParameter("subject"); // 글 제목
			String content = multi.getParameter("contents"); // 글 내용
			String existFile = multi.getParameter("oldfilename"); // 기존 첨부 파일
			String passwd = multi.getParameter("pass");
			// 파라미터 값을 자바빈에 세팅한다.
			qaVO border = new qaVO();
			border.setIdx(num);
			border.setSubject(subject);
			border.setContents(content);
			border.setPass(passwd);
			// 글 수정 시 업로드된 파일 가져오기
			Enumeration<String> fileNames = multi.getFileNames();
			if (fileNames.hasMoreElements()) {
				String fileName = fileNames.nextElement();
				String updateFile = multi.getFilesystemName(fileName);

				if (updateFile == null) // 수정시 새로운 파일을 첨부 안했다면 기존 파일명을 세팅
					border.setFilename(existFile);
				else // 새로운 파일을 첨부했을 경우
					border.setFilename(updateFile);
			}

			qaDAO dao = qaDAO.getInstance();
			boolean result = dao.updateBoard(border);
			int row = 99;
			if (result) {
				row = 1;
				request.setAttribute("row", row);
				request.setAttribute("idx", num);
				request.setAttribute("page", pageNum);
			} else {
				row = 0;
				request.setAttribute("row", row);
				request.setAttribute("idx", num);
				request.setAttribute("page", pageNum);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("글 수정 오류 : " + e.getMessage());
		}
		return "/qa/qamodify.jsp";
	}

}
