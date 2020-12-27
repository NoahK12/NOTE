package webproject.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webproject.dao.WebDAO;
import webproject.dto.AFlagCategoryDTO;
import webproject.dto.AccountDTO;
import webproject.dto.FlagCategoryDTO;
import webproject.dto.PostDTO;

public class AdminAction implements WebActionImp{

	@Override
	public void execute(HttpServletRequest req) {
		WebDAO dao = WebDAO.getInstance();
		List<AccountDTO> aList = dao.accountFlagMethod();
		List<AFlagCategoryDTO> aList2 = dao.accountFlagCateMethod(aList);
		req.setAttribute("aList", aList);
		req.setAttribute("aList2", aList2);
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		WebDAO dao = WebDAO.getInstance();
		List<PostDTO> aList = dao.postFlagMethod(); 
		
		List<FlagCategoryDTO> aList2 = dao.postFlagCateMethod(aList);
		
		req.setAttribute("aList", aList);
		req.setAttribute("aList2", aList2);
		
		
	}

}
