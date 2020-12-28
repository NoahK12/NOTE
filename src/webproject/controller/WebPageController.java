package webproject.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import webproject.action.AddFlagAction;
import webproject.action.AdminAction;
import webproject.action.CheckAction;
import webproject.action.CommentAction;
import webproject.action.DeleteAction;
import webproject.action.FlagDeleteAction;
import webproject.action.FlagListAction;
import webproject.action.FollowIdAction;
import webproject.action.FollowProfileAction;
import webproject.action.GetPostAction;
import webproject.action.GetUserInfoAction;
import webproject.action.InsertfollowAction;
import webproject.action.LikeAction;
import webproject.action.LikeListAction;
import webproject.action.ListAction;
import webproject.action.LoginAction;
import webproject.action.LogoutAction;
import webproject.action.MovePostAction;
import webproject.action.PostAction;
import webproject.action.SignUpAction;
import webproject.action.UnLikeAction;
import webproject.action.UploadAction;
import webproject.action.UserSettingAction;
import webproject.action.UserSkipAction;
import webproject.action.UserinfoAction;

@WebServlet("/login/*")
public class WebPageController extends HttpServlet {

	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = req.getRequestURI();
		String next = "";
		
		path = path.substring(path.lastIndexOf("/") + 1);
		
		if (path.equals("*")) {
			path = "login.do";
		}
		
		if (path.equals("login.do")) {
			next = "/loginpageview/login.jsp";
		} else if (path.equals("main.do")) { // 로그인 버튼 누르면...
			LoginAction login = new LoginAction();
			login.execute(req);
			if (login.cnt == 0) {
				next = "/loginpageview/login.jsp";
			} else {
				FollowIdAction follow = new FollowIdAction();
				PostAction post = new PostAction();
				follow.execute(req);
				post.execute(req);
				LikeListAction like = new LikeListAction();
				like.execute(req);
				FlagListAction flag = new FlagListAction();
				flag.execute(req);
				resp.sendRedirect("remain.do");}
			
			
		} else if (path.equals("signup.do")) {
			CheckAction ca = new CheckAction();
			ca.execute(req);
			if(ca.checkSignup == 0) {
				SignUpAction sup = new SignUpAction();
				sup.execute(req);
				next = "/usersettingview/usersetting.jsp";
			} else if(ca.checkSignup == 1){
				String existId = "id";
				req.setAttribute("existId", existId);
				next = "/loginpageview/login.jsp";
			} else if(ca.checkSignup == 2) {
				String existName = "name";
				req.setAttribute("existName", existName);
				next = "/loginpageview/login.jsp";
			} else if(ca.checkSignup == 3) {
				String existEmail = "email";
				req.setAttribute("existEmail", existEmail);
				next = "/loginpageview/login.jsp";
			}
			
			
		}else if(path.equals("usersetting.do")) {
			FollowIdAction follow = new FollowIdAction();
			UserSettingAction usa = new UserSettingAction();
			follow.execute(req);
			PostAction post = new PostAction();
			post.execute(req);
			MultipartRequest multi = usa.executeMulti(req);
			resp.sendRedirect("remain.do");
			
			
		} else if (path.equals("usersettingskip.do")) {
			UserSkipAction uskip = new UserSkipAction();
			uskip.execute(req);
			FollowIdAction follow = new FollowIdAction();
			follow.execute(req);
			PostAction post = new PostAction();
			post.execute(req);
			resp.sendRedirect("remain.do");
			
			
		}else if(path.equals("profile.do")) {
			GetPostAction gpa = new GetPostAction();
			gpa.execute(req);
			next = "/profile/profilePage.jsp";
			
			
		}else if(path.equals("remain.do")) {
			FollowIdAction follow = new FollowIdAction();
			PostAction post = new PostAction();
			follow.execute(req);
			post.execute(req);
			LikeListAction like = new LikeListAction();
			like.execute(req);
			FlagListAction flag = new FlagListAction();
			flag.execute(req);
			next = "/mainpageview/mainpage.jsp";
			
			
		}else if(path.equals("write.do")) {
			FollowIdAction follow = new FollowIdAction();
			follow.execute(req);
			next = "/noticepageview/noticepage.jsp";
			
			
		}else if(path.equals("userinfo.do")) {
			GetUserInfoAction guia = new GetUserInfoAction();
			guia.execute(req);
			next = "/userinfoview/userinfo.jsp";
			
			
		}else if(path.equals("search.do")) {
			ListAction list = new ListAction();
			list.execute(req); //상속 시켜줌으로써  속성값을 list.jsp에 넘겨줌 
			FollowIdAction follow = new FollowIdAction();
			follow.execute(req);
			next = "/searchview/searchPage.jsp";
			
			
		}else if(path.equals("admin.do") || path.equals("flag_account.do")) {
			AdminAction admin = new AdminAction();
			admin.execute(req);
			next = "/adminpageview/flag_account.jsp";
			
			
		}else if(path.equals("flag_post.do")) {
			AdminAction admin = new AdminAction();
			admin.execute(req, resp);
			next = "/adminpageview/flag_post.jsp";
			
			
		}else if(path.equals("upload.do")) {
			UploadAction upload = new UploadAction();
			upload.executeMulti(req);
			FollowIdAction follow = new FollowIdAction();
			follow.execute(req);
			PostAction post = new PostAction();
			post.execute(req);
			resp.sendRedirect("remain.do");
			
			
		}else if(path.equals("accountDelete.do")) {
			FlagDeleteAction del = new FlagDeleteAction();
			del.execute(req);
			resp.sendRedirect("admin.do");
			
			
		}else if(path.equals("postDelete.do")) {
			FlagDeleteAction del = new FlagDeleteAction();
			del.execute(req, resp);
			resp.sendRedirect("flag_post.do");
			
			
		}else if (path.equals("logout.do")) {
			LogoutAction logout = new LogoutAction();
			logout.execute(req);
			resp.sendRedirect("login.do");
			
			
		} else if(path.equals("chat")) {
			CommentAction ca= new CommentAction();
			ca.execute(req);
			MovePostAction mpa = new MovePostAction();
			mpa.execute(req);
			String a = req.getParameter("post_Content");
			a = URLEncoder.encode(a);
			resp.sendRedirect("list.do?post_Num="+req.getParameter("post_Num")+"&account_Name="+req.getParameter("account_Name")+"&account_Img="+req.getParameter("account_Img")+"&post_Like="+req.getParameter("post_Like")+"&post_Img="+req.getParameter("post_Img")+"&post_Content="+a+"&post_Sysdate="+req.getParameter("post_Sysdate"));
			
			
		} else if(path.equals("list.do")) {
			LikeListAction like = new LikeListAction();
			like.execute(req);
			FlagListAction flag = new FlagListAction();
			flag.execute(req);
			MovePostAction mpa = new MovePostAction();
			mpa.execute(req);
			LikeListAction colike = new LikeListAction();
			colike.execute(req,resp);
			next="/commentview/comment.jsp";
			
			
		} else if(path.equals("userupdate.do")) {
			UserinfoAction uia = new UserinfoAction();
			uia.executeMulti(req);
			FollowIdAction follow = new FollowIdAction();
			follow.execute(req);
			PostAction post = new PostAction();
			post.execute(req);
			resp.sendRedirect("remain.do");
			
			
		} else if (path.equals("followT")) {
			InsertfollowAction insertfoll=new InsertfollowAction();
			insertfoll.execute(req);
			String a = req.getParameter("searchWord");
			a = URLEncoder.encode(a);
			resp.sendRedirect("search.do?searchWord="+a);
			
			
		} else if (path.equals("followF")) {
			String a = req.getParameter("searchWord");
			a = URLEncoder.encode(a);
			DeleteAction delete=new DeleteAction();
			delete.execute(req);
			resp.sendRedirect("search.do?searchWord="+a);
			
			
		} else if (path.equals("followProfile.do")) {
			FollowProfileAction fpa = new FollowProfileAction();
			fpa.execute(req);
			FlagListAction fla = new FlagListAction();
			fla.execute(req, resp);
			next = "/followprofileview/followProfilePage.jsp";
		
			
		} else if(path.equals("like.do")) {
			LikeAction like = new LikeAction();
			like.execute(req);
			FollowIdAction follow = new FollowIdAction();
			PostAction post = new PostAction();
			follow.execute(req);
			post.execute(req);
			LikeListAction listlike = new LikeListAction();
			listlike.execute(req);
			resp.sendRedirect("remain.do");
			
			
		}else if(path.equals("unlike.do")) {
			UnLikeAction unlike = new UnLikeAction();
			unlike.execute(req);
			FollowIdAction follow = new FollowIdAction();
			PostAction post = new PostAction();
			follow.execute(req);
			post.execute(req);
			LikeListAction like = new LikeListAction();
			like.execute(req);
			resp.sendRedirect("remain.do");
			
			
		}else if(path.equals("colike.do")) {
			LikeAction like = new LikeAction();
			like.execute(req);
			int num = like.return_like(req);
			FollowIdAction follow = new FollowIdAction();
			PostAction post = new PostAction();
			follow.execute(req);
			post.execute(req);
			LikeListAction listlike = new LikeListAction();
			listlike.execute(req, resp);
			String a = req.getParameter("post_Content");
			a = URLEncoder.encode(a);
			resp.sendRedirect("list.do?post_Num="+req.getParameter("post_Num")+"&account_Name="+req.getParameter("account_Name")+"&account_Img="+req.getParameter("account_Img")+"&post_Img="+req.getParameter("post_Img")+"&post_Content="+a+"&post_Sysdate="+req.getParameter("post_Sysdate")+"&post_Like="+num);
			
			
		}else if(path.equals("counlike.do")) {
			UnLikeAction unlike = new UnLikeAction();
			unlike.execute(req);
			int num = unlike.return_like(req);
			FollowIdAction follow = new FollowIdAction();
			PostAction post = new PostAction();
			follow.execute(req);
			post.execute(req);
			LikeListAction like = new LikeListAction();
			like.execute(req, resp);
			String a = req.getParameter("post_Content");
			a = URLEncoder.encode(a);
			resp.sendRedirect("list.do?post_Num="+req.getParameter("post_Num")+"&account_Name="+req.getParameter("account_Name")+"&account_Img="+req.getParameter("account_Img")+"&post_Img="+req.getParameter("post_Img")+"&post_Content="+a+"&post_Sysdate="+req.getParameter("post_Sysdate")+"&post_Like="+num);
		} 
		
		else if(path.equals("addflag.do")) {
			AddFlagAction addflag = new AddFlagAction();
			addflag.execute(req);
			resp.sendRedirect("remain.do");
		}
		

		else if(path.equals("addaccountflag.do")) {
			AddFlagAction addflag = new AddFlagAction();
			addflag.execute(req,resp);
			
			resp.sendRedirect("remain.do");
		}		
		
		if(!next.equals("")) {
			RequestDispatcher dis = req.getRequestDispatcher(next);
			dis.forward(req, resp);	
		}
		
	} // end doProcess()

} // end class
