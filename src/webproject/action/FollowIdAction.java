package webproject.action;

import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webproject.dao.WebDAO;
import webproject.dto.AccountDTO;


public class FollowIdAction implements WebActionImp{
	
	//by.동기 해당 유저가 follow한 유저들의 정보를 join table에서 가져옴
	@Override
	public void execute(HttpServletRequest req) {
		
		WebDAO dao = WebDAO.getInstance();
		HttpSession session = req.getSession();
		
		int num = (Integer)session.getAttribute("account_Num");
		//note_account_follow_join table에서 
		//해당 유저의 account_num을 검색하여 follower의 정보 list를 가져옴
		List<AccountDTO> aList = dao.follow(num);
		req.setAttribute("aList10", aList);
	}
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
	}
}
