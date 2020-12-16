package webproject.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webproject.dao.WebDAO;
import webproject.dto.AccountPostDTO;
import webproject.dto.PostDTO;

public class PostAction implements WebActionImp{

	//by.동기 main page에 출력할 게시글 list
	@Override
	public void execute(HttpServletRequest req) {
		WebDAO dao = WebDAO.getInstance();
		HttpSession session = req.getSession();
		
		int num = (Integer)session.getAttribute("account_Num");
		String name = (String)session.getAttribute("account_Name");
		//본인의 게시글과 follow한 유저의 게시글 post_num list
		List<PostDTO> aList = dao.followerid(num, name);
		//게시글 detail list
		List<AccountPostDTO> aList2 = dao.post(aList);
		
		req.setAttribute("aList2", aList2);
	}
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
	}
}
