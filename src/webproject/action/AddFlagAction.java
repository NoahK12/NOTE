package webproject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webproject.dao.WebDAO;

public class AddFlagAction implements WebActionImp{

	//by.동기 게시글 신고
	@Override
	public void execute(HttpServletRequest req) {
		WebDAO dao = WebDAO.getInstance();
		
		int post_num = Integer.parseInt(req.getParameter("post_Num"));
		int account_num = Integer.parseInt(req.getParameter("account_Num"));
		String kind = (String)req.getParameter("selected_Flag");
		//해당 게시글 신고 갯수 +1
		dao.addflag(post_num, kind);
		//note_flag_table에 신고당한 게시글의 post_num, 신고한 유저의 account_num 추가
	    dao.addflagtable(post_num, account_num);
		
		dao.addflag(post_num);
	}
	
	//by.동기  계정 신고
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
			WebDAO dao = WebDAO.getInstance();
		
		int flag_account_num = Integer.parseInt(req.getParameter("flag_account_Num"));
		System.out.println(req.getParameter("flag_account_Num"));
		int account_num = Integer.parseInt(req.getParameter("account_Num"));
		System.out.println(req.getParameter("account_Num"));
		String kind = (String)req.getParameter("selected_Flag");
		//해당 유저 신고 갯수 +1
		dao.addaflag(flag_account_num, kind);
		//해당 유저를 유저 신고 table에 추가
		//note_aflag_table에 신고당한 유저의 account_num, 신고한 유저의 account_num 추가
	    dao.addaflagtable(flag_account_num, account_num);
		
	}
	
}
