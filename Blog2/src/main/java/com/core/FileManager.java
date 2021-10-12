package com.core;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;

//DiskFileUpload - Deprecated
//ServletFileUpload(FileItemFactory -- 파일이 저장될 수단)

public class FileManager {
	/**
	 * 파일 업로드 처리
	 * 
	 * @param request
	 * @return
	 * @throws FileUploadException 
	 * @throws UnsupportedEncodingException 
	 */
	public static String upload(HttpServletRequest request) throws FileUploadException, UnsupportedEncodingException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload();
		upload.setHeaderEncoding("UTF-8");
		
		List<FileItem> items = upload.parseRequest(request);
		Iterator<FileItem> params = items.iterator();
		while(params.hasNext()) {
			FileItem item = params.next();
			if (item.isFormField()) { // 일반 양식 데이터 //isFormField -> 있으면 추가, 없으면 추가 안함.
				String key = item.getFieldName();
				String value = item.getString("UTF-8");
				System.out.println(key + "=" + value);
			} else {// 파일 데이터
				
			}
		}
		
	return null;	
	}
	
	
	//boolean isFirstFile = true; // 첫번째 파일 여부 -> 첫번째 파일 앞엔 ||가 붙으면 안되니까
	
	//String ext = originalFileName.substring(originalFileName.lastIndexOf(".")); // 확장자 앞 . 에서 끊음. (~~.jpg)
}
