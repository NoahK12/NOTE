package webproject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webproject.dao.WebDAO;
import webproject.dto.AccountDTO;

public class GetUserInfoAction implements WebActionImp{

	@Override
	public void execute(HttpServletRequest req) {
		
		WebDAO dao = WebDAO.getInstance();
		HttpSession session = req.getSession();
		AccountDTO pdto = dao.viewMethod((String)session.getAttribute("account_Id"));
		req.setAttribute("account_Img", pdto.getAccount_Img());
		req.setAttribute("account_About", pdto.getAccount_About());
		req.setAttribute("account_Email", pdto.getAccount_Email());
		req.setAttribute("account_Phone_Num", pdto.getAccount_Phone_Num());
		
	} // end execute()

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
	}

} // end class
