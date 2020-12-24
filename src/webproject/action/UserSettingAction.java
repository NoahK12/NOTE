package webproject.action;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import webproject.dao.WebDAO;
import webproject.dto.AccountDTO;

public class UserSettingAction implements WebMultiImp{

	@Override
	public MultipartRequest executeMulti(HttpServletRequest req) {
		 
		MultipartRequest multi = null;
		String saveDirectory = "C:/study/workspace/NOTE/WebContent/images";
		File file = new File(saveDirectory);
		if(!file.isDirectory()) {
			file.mkdir();
		}
		
		int maxPostSize = 1*1000*1000*1000; // 1GB
		String encoding = "UTF-8";
		
		try {
			multi = new MultipartRequest(req, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		HttpSession session = req.getSession();
		
		WebDAO dao = WebDAO.getInstance();
		
		AccountDTO dto = new AccountDTO();
		dto.setAccount_Num((Integer) session.getAttribute("account_Num"));
		
		if (multi.getFilesystemName("filepath") == null) {
			String defaultImg = "person.png";
			dto.setAccount_Img(defaultImg);
			session.setAttribute("account_Img", defaultImg);
		} else {
			String newImg = multi.getFilesystemName("filepath");
			dto.setAccount_Img(newImg);
			session.setAttribute("account_Img", newImg);
		}
		
		Enumeration params = multi.getParameterNames();
		while (params.hasMoreElements()) {
			String paramname = (String) params.nextElement();
			if(paramname.equalsIgnoreCase("abouttext")) {
				String abouttext = multi.getParameter("abouttext");
				if(abouttext==null) {
					dto.setAccount_About(abouttext);
				}else {
					abouttext="";
					dto.setAccount_About(abouttext);
				}
				session.setAttribute("account_About", abouttext);
			}
			if(paramname.equalsIgnoreCase("phonetext")){
		        String phonetext = multi.getParameter("phonetext");
		        if(phonetext==null) {
					dto.setAccount_About(phonetext);
				}else {
					phonetext="";
					dto.setAccount_About(phonetext);
				}
		        dto.setAccount_Phone_Num(phonetext);
		        session.setAttribute("account_Phone_Num", phonetext);
			}    
		}
		dao.initSaveSetting(dto);
		
		return multi;
		
	} // end executeMulti()
	
} // end class
