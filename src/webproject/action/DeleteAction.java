package webproject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webproject.dao.WebDAO;

public class DeleteAction implements WebActionImp{

	//by.동기 해당 유저 follow 삭제
	@Override
	public void execute(HttpServletRequest req) {

		WebDAO dao = WebDAO.getInstance();
		
		String name=req.getParameter("name");
		HttpSession session = req.getSession();
		//note_follow table에서 follow 삭제한 유저의 account_num과
		//삭제당한 유저의 follow_name을 검색하여 삭제
		dao.deleteMethod(name,(Integer)session.getAttribute("account_Num"));
		
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}

}
