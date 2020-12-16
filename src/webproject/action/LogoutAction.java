package webproject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements WebActionImp{

	@Override
	public void execute(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String fid = (String) session.getAttribute("account_Id");
		if (fid != null) {
			session.invalidate(); //세션연결 종료
		}
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}

}
