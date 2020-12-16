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

public class UserinfoAction implements WebMultiImp{

	@Override
	public MultipartRequest executeMulti(HttpServletRequest req) {
		 
		MultipartRequest multi = null;
		String saveDirectory = "C:/study/workspace/webproject2020/WebContent/images";
		File file = new File(saveDirectory);
		if (!file.isDirectory()) {
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
		Enumeration params = multi.getParameterNames();
		while (params.hasMoreElements()) {
			String paramname = (String) params.nextElement();
			if(paramname.equalsIgnoreCase("introduce")){
		        String introduce = multi.getParameter("introduce");
		        dto.setAccount_About(introduce);
			}
			if(paramname.equalsIgnoreCase("email")) {
		        String email = multi.getParameter("email");
		        dto.setAccount_Email(email);
			}
			if(paramname.equalsIgnoreCase("tel")) {
		        String tel = multi.getParameter("tel");
		        dto.setAccount_Phone_Num(tel);
			}
		}
		
		dto.setAccount_Img(multi.getFilesystemName("filepath"));
		dto.setAccount_Num((Integer) session.getAttribute("account_Num"));
		dao.updateProfile(dto);
		AccountDTO pdto = dao.viewMethod((String)session.getAttribute("account_Id"));
		req.setAttribute("account_Img", pdto.getAccount_Img());
		req.setAttribute("account_Password", pdto.getAccount_Password());
		session.setAttribute("account_Img", pdto.getAccount_Img());
		session.setAttribute("account_About", pdto.getAccount_About());
		
		return multi;
	}

}
