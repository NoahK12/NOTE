package webproject.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webproject.dao.WebDAO;
import webproject.dto.AccountDTO;
import webproject.dto.PostDTO;

public class FollowProfileAction implements WebActionImp{

	@Override
	public void execute(HttpServletRequest req) {
		 
		WebDAO dao = WebDAO.getInstance();
		
		String following_name = req.getParameter("following_Name");
		AccountDTO pdto = dao.viewMethodByName(following_name);
		
		List<PostDTO> aList = dao.getPostMethod(pdto.getAccount_Name());
		int countPost = dao.countPost(pdto.getAccount_Name());
		int countFollow = dao.countFollow(pdto.getAccount_Num());
		int countFollower = dao.countFollower(pdto.getAccount_Name());
		
		req.setAttribute("account_Name", pdto.getAccount_Name());
		req.setAttribute("account_Img", pdto.getAccount_Img());
		req.setAttribute("account_About", pdto.getAccount_About());
		req.setAttribute("account_Num", pdto.getAccount_Num());
		req.setAttribute("aList", aList);
		req.setAttribute("countPost", countPost);
		req.setAttribute("countFollow", countFollow);
		req.setAttribute("countFollower", countFollower);
		
	} // end execute()

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
	}

} // end class
