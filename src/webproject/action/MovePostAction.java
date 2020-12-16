package webproject.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webproject.dao.WebDAO;
import webproject.dto.CommentDTO;

public class MovePostAction implements WebActionImp{

	@Override
	public void execute(HttpServletRequest req) {
		 
		req.setAttribute("post_Num", req.getParameter("post_Num"));
		//System.out.println(req.getParameter("post_Num")+"포스트 넘버");
		req.setAttribute("account_Name", req.getParameter("account_Name"));
		req.setAttribute("account_Img", req.getParameter("account_Img"));
		req.setAttribute("post_Like", req.getParameter("post_Like"));
		req.setAttribute("post_Img", req.getParameter("post_Img"));
		req.setAttribute("post_Content", req.getParameter("post_Content"));
		req.setAttribute("post_Sysdate", req.getParameter("post_Sysdate"));
		
		WebDAO dao = WebDAO.getInstance();
		List<CommentDTO> aList = dao.getComment(Integer.parseInt(req.getParameter("post_Num")));
		req.setAttribute("aList", aList);
		
	} // end execute()

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
	}

} // end class
