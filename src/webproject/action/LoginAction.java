package webproject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webproject.dao.WebDAO;
import webproject.dto.AccountDTO;

public class LoginAction implements WebActionImp{

	public int cnt = 0;
	
	@Override
	public void execute(HttpServletRequest req) {
		
		WebDAO dao = WebDAO.getInstance();
		
		// 변수 지정/가져오기
		String fid = req.getParameter("fid");
		String fpass = req.getParameter("fpass");
		
		// 변수 dto에 저장
		AccountDTO dto = new AccountDTO();
		dto.setAccount_Id(fid);
		dto.setAccount_Password(fpass);
		
		// 로그인 체크 확인
		cnt = dao.loginCheck(dto);

		HttpSession session = null;
		
		if (cnt == 1) {
			
			// 아이디 확인 후 아이디 관련된 정보를 모두 가져옴.
			AccountDTO pdto = dao.viewMethod(fid);
			
			// sessionScope에 개인정보 (id, password, etc.) 넘겨준다.
			session = req.getSession();
			session.setMaxInactiveInterval(30 * 60);
			session.setAttribute("account_Id", fid);
			session.setAttribute("account_Password", fpass);
			session.setAttribute("account_Num", pdto.getAccount_Num());
			session.setAttribute("account_Name", pdto.getAccount_Name());
			session.setAttribute("account_About", pdto.getAccount_About());
			session.setAttribute("account_Email", pdto.getAccount_Email());
			session.setAttribute("account_Img", pdto.getAccount_Img());
			session.setAttribute("account_Phone_Num", pdto.getAccount_Phone_Num());
			session.setAttribute("account_Flag", pdto.getAccount_Flag());
			
		} else {
			req.setAttribute("id", fid);
		}
	} // end execute()

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
	} // end execute()

} // end class
