package Service.Pds;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.CommandHandler;

public class Download implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String filename = request.getParameter("filename");
		
		ServletContext context = request.getServletContext();
		String uploadFilePath = context.getRealPath("portfolio/upload");
		String filepath = uploadFilePath+File.separator+filename;
		
		byte[] b = new byte[4096];
		FileInputStream fileInputStream = new FileInputStream(filepath);
		
		String mimeType = request.getServletContext().getMimeType(filepath);
		if(mimeType == null) {
			mimeType = "application/octet-stream";
		}
		response.setContentType(mimeType);
		
        // 파일명 UTF-8로 인코딩(한글일 경우를 대비)
        String sEncoding = new String(filename.getBytes("UTF-8"),"ISO-8859-1");
        response.setHeader("Content-Disposition", "attachment; fileName= " + sEncoding);
        
        // 파일 쓰기 OutputStream
        ServletOutputStream servletOutStream = response.getOutputStream();
        
        int read;
        while((read = fileInputStream.read(b,0,b.length))!= -1){
            servletOutStream.write(b,0,read);            
        }
        
        servletOutStream.flush();
        servletOutStream.close();
        fileInputStream.close();
		
        return null;
	}

}
