package webproject.action;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import webproject.dao.WebDAO;
import webproject.dto.PostDTO;

public class UploadAction implements WebMultiImp{

	//by.동기 게시글 작성
	@Override
	public MultipartRequest executeMulti(HttpServletRequest req) {
		 
		MultipartRequest multi = null;
		//image file이 저장되는 장소 설정
		String saveDirectory = "C:/study/workspace/NOTE/WebContent/images";
		File file = new File(saveDirectory);
		if(!file.isDirectory()) {
			//해당 폴더가 없으면 폴더 생성
			file.mkdir();
		}
		
		int maxPostSize = 1024*1024*100; //최대 100m
		String encoding = "UTF-8";
		
		try {
			multi = new MultipartRequest(req, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		HttpSession session = req.getSession();
		
		WebDAO dao = WebDAO.getInstance();
		
		PostDTO dto = new PostDTO();
		Enumeration params = multi.getParameterNames();
		while (params.hasMoreElements()) {
			String paramname = (String) params.nextElement();
			if(paramname.equalsIgnoreCase("noticetext")) {
				String noticetext = multi.getParameter("noticetext");
				dto.setPost_Content(noticetext);
			}
		}
		dto.setAccount_Name((String)session.getAttribute("account_Name"));
		dto.setPost_Img(multi.getFilesystemName("filepath"));
		//note_post table에 추가
		dao.uploadPost(dto);
		//작성한 게시글의 post_num
		int postnum = dao.updatepost();
		//note_flag_category table에 게시글 추가
		dao.addflagcategorytable(postnum);
	
		return multi;
		
	} // end executeMulti()

} // end class
