package webproject.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webproject.dao.WebDAO;
import webproject.dto.AccountPostDTO;
import webproject.dto.PostDTO;

public class LikeListAction implements WebActionImp {
	
	//by.동기 follow한 게시글 중 좋아요한 게시글 체크 list
	@Override
	public void execute(HttpServletRequest req) {
		WebDAO dao = WebDAO.getInstance();
		HttpSession session = req.getSession();

		int account_num = (Integer) session.getAttribute("account_Num");
		//유저가 좋아요 누른 게시글 list
		List<PostDTO> aList3 = dao.likelist(account_num);
		String name = (String) session.getAttribute("account_Name");
		//유저가 follow한 게시글 list
		List<PostDTO> aList = dao.followerid(account_num, name);
		List<AccountPostDTO> aList2 = dao.post(aList);
		int[] chk = new int[aList2.size()];
		for (int i = 0; i < aList2.size(); i++) {
			for (int j = 0; j < aList3.size(); j++) {
				if (aList2.get(i).getPost_Num() == aList3.get(j).getPost_Num()) {
					//좋아요를 누른 게시글
					chk[i] = 1;
					break;
				} else {
					//좋아요를 누르지 않은 게시글
					chk[i] = 0;
				}
			}
		}

		req.setAttribute("aList3", chk);

	}

	//by.동기  해당 유저가 좋아요 누른 게시글인지 확인
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		WebDAO dao = WebDAO.getInstance();
		HttpSession session = req.getSession();

		int account_num = (Integer) session.getAttribute("account_Num");
		int post_num = Integer.parseInt(req.getParameter("post_Num")); 
		//좋아요 누른 게시글 = 1, else = 0
		int num =dao.postlikechk(post_num, account_num);
		req.setAttribute("num", num);
		req.setAttribute("post_Num", req.getParameter("post_Num"));
	}
}
