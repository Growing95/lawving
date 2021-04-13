package com.ict.lawving.filedown;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

@Component("filedown2")
public class FileDownView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> downFileModel = (Map)model.get("downFile");
		File renameFile = (File)downFileModel.get("renameFile");
		String originalFilename = (String)downFileModel.get("library_file_name");
				
		//한글 파일명 인코딩 처리를 위해 파일명만 추출함
		response.setContentType("text/plain; charset=utf-8");
		response.addHeader("Content-Disposition", "attachment; filename=\""
				+ new String(originalFilename.getBytes("utf-8"), "ISO-8859-1") + "\"");
		response.setContentLength((int)renameFile.length());
		
		//저장된 폴더에서 대상 파일을 읽어서, 요청한 클라이언트 브라우저로 보내기 위한
		//입출력 스트림 생성함
		OutputStream out = response.getOutputStream();
		
		try(FileInputStream fin = new FileInputStream(renameFile);){
			FileCopyUtils.copy(fin, out);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		out.flush();
		out.close();
		
	}

}
