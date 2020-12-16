package webproject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webproject.dao.WebDAO;

public class UserSkipAction implements WebActionImp{

	@Override
	public void execute(HttpServletRequest req) {
		 
		WebDAO dao = WebDAO.getInstance();
		
		HttpSession session = req.getSession();
		
		dao.skipSetting((Integer) session.getAttribute("account_Num"));
		
		session.setAttribute("account_Img", "person.png");
		
	} // end execute()

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
	} // end execute()
	
} // end class
