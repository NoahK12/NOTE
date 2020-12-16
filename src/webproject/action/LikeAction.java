package webproject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webproject.dao.WebDAO;

public class LikeAction implements WebActionImp{
	
	//by.동기 게시글 좋아요 추가 
	@Override
	public void execute(HttpServletRequest req) {
		WebDAO dao = WebDAO.getInstance();
		HttpSession session = req.getSession();
		
		
		int post_num = Integer.parseInt(req.getParameter("post_Num"));
		int account_num =  (Integer)session.getAttribute("account_Num"); 
		//해당 게시글 좋아요 +1
		dao.likeplus(post_num);
		//note_like_table에 유저의 account_num, 게시글의 post_num 추가
		dao.insertlike(post_num, account_num);
		
	}
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
	}
	
	public int return_like(HttpServletRequest req) {
		WebDAO dao = WebDAO.getInstance();
		int post_num = Integer.parseInt(req.getParameter("post_Num"));
		//해당 게시글 좋아요 갯수
		return dao.return_like(post_num);
	}

}
