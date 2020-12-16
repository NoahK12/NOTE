package webproject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webproject.dao.WebDAO;

public class FlagDeleteAction implements WebActionImp{

	//관리자가 유저 삭제
	@Override
	public void execute(HttpServletRequest req) {
	WebDAO dao = WebDAO.getInstance();
	dao.followDelMethod(req.getParameter("account_Name"));
	dao.accountDelMethod(Integer.parseInt(req.getParameter("account_Num")));
		
	}

	//관리자가 게시글 삭제
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		WebDAO dao = WebDAO.getInstance();
		dao.copostDelMethod(Integer.parseInt(req.getParameter("post_Num")));
		dao.postDelMethod(Integer.parseInt(req.getParameter("post_Num")));
	}

}
