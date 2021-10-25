package Service.Qa;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.qa.qaDAO;
import Service.CommandHandler;

public class Qa_deleteProHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		 // 글번호를 가져온다.
        String num = request.getParameter("idx");
        int boardNum = Integer.parseInt(num);
        String passwd = request.getParameter("pass");
        String page = request.getParameter("page");
        qaDAO dao = qaDAO.getInstance();
        // 삭제할 글에 있는 파일 정보를 가져온다.
        String fileName = dao.getFileName(boardNum);
        // 글 삭제 - 답글이 있을경우 답글도 모두 삭제한다.
        boolean result = dao.deleteBoard(boardNum,passwd);
        
        // 파일삭제 
        if(fileName != null)
        {
            // 파일이 있는 폴더의 절대경로를 가져온다.
            String folder = request.getServletContext().getRealPath("upload");
            // 파일의 절대경로를 만든다.
            String filePath = folder + "/" + fileName;
 
            File file = new File(filePath);
            if(file.exists()) file.delete(); // 파일은 1개만 업로드 되므로 한번만 삭제하면 된다.
        }
        int row = 99;
        if(result){
        	row=1;
        	request.setAttribute("row", row);
        	request.setAttribute("page", page);
            return "/qa/qadelete.jsp";
        }
        else {
        	row=0;
        	request.setAttribute("row", row);
        	request.setAttribute("page", page);
        	return "/qa/qadelete.jsp";
        }
		
	}

}
