package webproject.action;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;

public interface WebMultiImp {

	public MultipartRequest executeMulti(HttpServletRequest req);
	
} // end interface
