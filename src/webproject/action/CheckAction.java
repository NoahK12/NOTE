package webproject.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webproject.dao.WebDAO;
import webproject.dto.AccountDTO;

public class CheckAction implements WebActionImp{

	public int checkSignup = 0;
	
	@Override
	public void execute(HttpServletRequest req) {
		
		WebDAO dao = WebDAO.getInstance();
		
		String fid = req.getParameter("fid");
		String fname = req.getParameter("fname");
		String email = req.getParameter("email");
		
		List<AccountDTO> aList = dao.checkSignup();
		
		for (int i = 0; i < aList.size(); i++) {
			if(aList.get(i).getAccount_Id().equals(fid)) {
				checkSignup = 1;
			} else if(aList.get(i).getAccount_Name().equals(fname)) {
				checkSignup = 2;
			} else if(aList.get(i).getAccount_Email().equals(email)) {
				checkSignup = 3;
			}
		}
		
	} // end execute()

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
	}
	
} // end class
