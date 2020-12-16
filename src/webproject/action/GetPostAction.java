package webproject.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webproject.dao.WebDAO;
import webproject.dto.PostDTO;

public class GetPostAction implements WebActionImp{

	@Override
	public void execute(HttpServletRequest req) {
		 
		WebDAO dao = WebDAO.getInstance();
		
		HttpSession session = req.getSession();
		
		List<PostDTO> aList = dao.getPostMethod((String)session.getAttribute("account_Name"));
		int countPost = dao.countPost((String)session.getAttribute("account_Name"));
		int countFollow = dao.countFollow((Integer)session.getAttribute("account_Num"));
		int countFollower = dao.countFollower((String)session.getAttribute("account_Name"));
		
		req.setAttribute("aList", aList);
		req.setAttribute("countPost", countPost);
		req.setAttribute("countFollow", countFollow);
		req.setAttribute("countFollower", countFollower);
		
	} // end execute()

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
	}
	
} // end class
