package webproject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webproject.dao.WebDAO;

public class UnLikeAction implements WebActionImp{

	//by.동기 좋아요 삭제
	@Override
	public void execute(HttpServletRequest req) {
		WebDAO dao = WebDAO.getInstance();
		HttpSession session = req.getSession();
		
		int post_num = Integer.parseInt(req.getParameter("post_Num"));
		int account_num =  (Integer)session.getAttribute("account_Num");
		//note_like_table에서 유저 account_num과 게시글 post_num 검색하여 삭제
		dao.dellike(post_num, account_num);
		//note_post 테이블에서 해당 게시글 좋아요 -1
		dao.likeminus(post_num);
	}
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}
	
	//by.동기 해당 게시글 좋아요 갯수
	public int return_like(HttpServletRequest req) {
		WebDAO dao = WebDAO.getInstance();
		int post_num = Integer.parseInt(req.getParameter("post_Num"));
	
		return dao.return_like(post_num);
	}

	
}
