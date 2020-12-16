package webproject.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webproject.dao.WebDAO;
import webproject.dto.AccountDTO;
import webproject.dto.SearchDTO;

public class ListAction implements WebActionImp {
	
	@Override
	public void execute(HttpServletRequest req) {
		 
		WebDAO dao = WebDAO.getInstance();
		
		String searchWord=req.getParameter("searchWord"); //입력한 텍스트
		
		List<SearchDTO> aList3=dao.listMethod(searchWord);
		
		List<Integer> fList = new ArrayList<Integer>();
		for (int i = 0; i < aList3.size(); i++) {
			int follower = dao.countFollower(aList3.get(i).getAccount_Name());
			fList.add(follower);
		}
		req.setAttribute("fList", fList);
		
		HttpSession session = req.getSession();
		
		List<AccountDTO> aList2 = dao.follow((Integer)session.getAttribute("account_Num"));
		int[] aa = new int[aList3.size()];
		for (int i = 0; i < aList3.size(); i++) {
			for (int j = 0; j < aList2.size(); j++) {
				if(aList3.get(i).getAccount_Name().equals(aList2.get(j).getAccount_Name())) {
					aa[i] = 1;
					break;
				}else{
					aa[i] = 0;
				} 
			}
		}
		
		req.setAttribute("followlist", aa);
		req.setAttribute("aList3", aList3);
		req.setAttribute("search", searchWord);
		
	}
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
	}
	
}
