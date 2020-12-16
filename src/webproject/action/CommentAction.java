package webproject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webproject.dao.WebDAO;
import webproject.dto.CommentDTO;

public class CommentAction implements WebActionImp{

	//by.동기 댓글 작성
	@Override
	public void execute(HttpServletRequest req) {

		WebDAO dao = WebDAO.getInstance();
		
		HttpSession session = req.getSession();
		
		CommentDTO dto = new CommentDTO();
		dto.setComment_Name((String)session.getAttribute("account_Name"));
		dto.setComment_Content(req.getParameter("text"));
		dto.setPost_Num(Integer.parseInt(req.getParameter("post_Num")));
		//note_webcomment table에 작성한 유저의 account_name, 댓글 내용, 해당 게시글의 post_num 추가
		dao.insertComment(dto);
	}
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
	}
}
