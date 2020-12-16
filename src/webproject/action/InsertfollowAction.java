package webproject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webproject.dao.WebDAO;

public class InsertfollowAction implements WebActionImp{

	//by.동기 해당 유저 follow 추가
	@Override
	public void execute(HttpServletRequest req) {
		 
		WebDAO dao=new WebDAO();
		
		String name=req.getParameter("name");
		
		HttpSession session=req.getSession();
		session.getAttribute("account_Num");
		//note_follow table에 follow하는 유저의 account_num과
		//follow 받는 유저의 account_name 추가
		dao.insertFollow(name, (Integer) session.getAttribute("account_Num"));
		
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
	}

	
}
