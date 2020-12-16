package webproject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webproject.dao.WebDAO;
import webproject.dto.AccountDTO;

public class SignUpAction implements WebActionImp{

	@Override
	public void execute(HttpServletRequest req) {
		 
		WebDAO dao = WebDAO.getInstance();
		
		String fid = req.getParameter("fid");
		String fpass = req.getParameter("fpass");
		String fname = req.getParameter("fname");
		String email = req.getParameter("email");
		
		AccountDTO dto = new AccountDTO();
		dto.setAccount_Id(fid);
		dto.setAccount_Password(fpass);
		dto.setAccount_Name(fname);
		dto.setAccount_Email(email);
		
		dao.signUpMethod(dto);
		
		AccountDTO pdto = dao.viewMethod(fid);
		
		dao.followAdmin(pdto.getAccount_Num());
		
		HttpSession session = req.getSession();
		
		session.setMaxInactiveInterval(30 * 60);
		session.setAttribute("account_Id", fid);
		session.setAttribute("account_Password", fpass);
		session.setAttribute("account_Num", pdto.getAccount_Num());
		session.setAttribute("account_Name", fname);
		session.setAttribute("account_Email", email);
		
		dao.addaflagcategorytable(pdto.getAccount_Num());
		
	} // end execute()

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
	} // end execute()

} // end class
