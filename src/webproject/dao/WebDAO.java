package webproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import templatedbcp.DbcpTemplate;
import webproject.dto.AFlagCategoryDTO;
import webproject.dto.AccountDTO;
import webproject.dto.AccountPostDTO;
import webproject.dto.CommentDTO;
import webproject.dto.FlagCategoryDTO;
import webproject.dto.FollowDTO;
import webproject.dto.PostDTO;
import webproject.dto.SearchDTO;

public class WebDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;

	public WebDAO() {
	}

	private static WebDAO dao = new WebDAO();

	public static WebDAO getInstance() {
		return dao;
	}
	
	//신고 당한 유저 list
	public List<AccountDTO> accountFlagMethod() {
		List<AccountDTO> aList = new ArrayList<AccountDTO>();
		try {
			conn = DbcpTemplate.getInit();
			stmt = conn.createStatement();
			String sql = "select account_id, account_num, account_name, account_img, account_flag from note_webaccount where account_flag >= 1 order by account_flag desc";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				AccountDTO dto = new AccountDTO();
				dto.setAccount_Num(rs.getInt("account_num"));
				dto.setAccount_Id(rs.getString("account_Id"));
				dto.setAccount_Name(rs.getString("account_Name"));
				dto.setAccount_Img(rs.getString("account_Img"));
				dto.setAccount_Flag(rs.getInt("account_Flag"));
				aList.add(dto);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(rs);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(conn);
		}
		return aList;
	}// end accountFlagMethod()

	//계정 삭제
	public void accountDelMethod(int num) {
		try {
			conn = DbcpTemplate.getInit();
			String sql = "DELETE FROM note_webaccount where account_num= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(rs);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(conn);
		}
	}

	//by.동기 게시글 삭제
	public void postDelMethod(int num) {
		try {
			conn = DbcpTemplate.getInit();
			String sql = "DELETE FROM note_post where post_num= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(rs);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(conn);
		}
	}
	
	//해당 게시글 댓글 모두 삭제
	public void copostDelMethod(int num) {
		try {
			conn = DbcpTemplate.getInit();
			String sql = "DELETE FROM note_webcomment where post_num= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(rs);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(conn);
		}
	}
	
	//신고당한 게시글 list
	public List<PostDTO> postFlagMethod() {
		List<PostDTO> aList = new ArrayList<PostDTO>();
		try {
			conn = DbcpTemplate.getInit();
			stmt = conn.createStatement();
			String sql = "select account_name, post_content, post_num, post_img,post_flag from note_post where post_flag >=1 order by post_flag desc";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				PostDTO dto = new PostDTO();
				dto.setAccount_Name(rs.getString("account_name"));
				dto.setPost_Content(rs.getString("post_Content"));
				dto.setPost_Flag(rs.getInt("post_Flag"));
				dto.setPost_Img(rs.getString("post_Img"));
				dto.setPost_Num(rs.getInt("post_Num"));
				aList.add(dto);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally { //////////////////////////////////////////////// added finally section/////
			DbcpTemplate.close(rs);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(conn);
		}

		return aList;
	}////////////// end postFlagMethod()
	
	//해당 게시글이 신고당한 유형 list
	public List<FlagCategoryDTO> postFlagCateMethod(List<PostDTO> List) {
		List<FlagCategoryDTO> aList = new ArrayList<FlagCategoryDTO>();
		try {
			conn = DbcpTemplate.getInit();
			String sql = "select * from note_flag_category where post_num = ?";
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < List.size(); i++) {
				pstmt.setInt(1, List.get(i).getPost_Num());
				
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FlagCategoryDTO dto = new FlagCategoryDTO();
				dto.setAbusive(rs.getInt("abusive"));
				dto.setIllegaladv(rs.getInt("illegaladv"));
				dto.setObscene(rs.getInt("obscene"));
				dto.setSpam(rs.getInt("spam"));
				aList.add(dto);
			}
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally { //////////////////////////////////////////////// added finally section/////
			DbcpTemplate.close(rs);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(conn);
		}

		return aList;
	}////////////// end postFlagCateMethod()
	
	//해당 유저가 신고당한 유형 list
	public List<AFlagCategoryDTO> accountFlagCateMethod(List<AccountDTO> List) {
		List<AFlagCategoryDTO> aList = new ArrayList<AFlagCategoryDTO>();
		try {
			conn = DbcpTemplate.getInit();
			String sql = "select * from note_aflag_category where account_num = ?";
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < List.size(); i++) {
				pstmt.setInt(1, List.get(i).getAccount_Num());
				
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AFlagCategoryDTO dto = new AFlagCategoryDTO();
				dto.setAbusive(rs.getInt("abusive"));
				dto.setIllegaladv(rs.getInt("illegaladv"));
				dto.setObscene(rs.getInt("obscene"));
				dto.setSpam(rs.getInt("spam"));
				aList.add(dto);
			}
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally { //////////////////////////////////////////////// added finally section/////
			DbcpTemplate.close(rs);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(conn);
		}

		return aList;
	}////////////// end postFlagCateMethod()

	//회원가입
	public void signUpMethod(AccountDTO dto) {
		try {
			conn = DbcpTemplate.getInit();
			String sql = "insert into note_webaccount(account_num,account_id,account_password,account_name,account_email,account_flag) values(webaccount_account_num_seq.nextval,?,?,?,?,0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getAccount_Id());
			pstmt.setString(2, dto.getAccount_Password());
			pstmt.setString(3, dto.getAccount_Name());
			pstmt.setString(4, dto.getAccount_Email());
			pstmt.executeQuery();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(rs);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(conn);
		}

	} // end signUpMethod()

	//로그인시 ID와 PASSWORD 확인
	public int loginCheck(AccountDTO dto) {

		int cnt = 0;

		try {
			conn = DbcpTemplate.getInit();
			String sql = "select count(*) from note_webaccount where account_id=? and account_password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getAccount_Id());
			pstmt.setString(2, dto.getAccount_Password());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(rs);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(conn);
		}

		return cnt;

	} // end loginCheck()

	//by.동기 해당 유저 정보 list
	public AccountDTO viewMethod(String fid) {

		AccountDTO pdto = new AccountDTO();

		try {
			conn = DbcpTemplate.getInit();
			String sql = "select * from note_webaccount where account_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pdto.setAccount_Num(rs.getInt("account_Num"));
				pdto.setAccount_Id(rs.getString("account_Id"));
				pdto.setAccount_Password(rs.getString("account_Password"));
				pdto.setAccount_Name(rs.getString("account_Name"));
				pdto.setAccount_About(rs.getString("account_About"));
				pdto.setAccount_Email(rs.getString("account_Email"));
				pdto.setAccount_Img(rs.getString("account_Img"));
				pdto.setAccount_Phone_Num(rs.getString("account_Phone_Num"));
				pdto.setAccount_Flag(rs.getInt("account_Flag"));
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(rs);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(conn);
		}

		return pdto;

	} // end viewMethod()

	//해당 유저 정보 list
	public AccountDTO viewMethodByName(String fid) {

		AccountDTO pdto = new AccountDTO();

		try {
			conn = DbcpTemplate.getInit();
			String sql = "select * from note_webaccount where account_name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pdto.setAccount_Num(rs.getInt("account_Num"));
				pdto.setAccount_Id(rs.getString("account_Id"));
				pdto.setAccount_Password(rs.getString("account_Password"));
				pdto.setAccount_Name(rs.getString("account_Name"));
				pdto.setAccount_About(rs.getString("account_About"));
				pdto.setAccount_Email(rs.getString("account_Email"));
				pdto.setAccount_Img(rs.getString("account_Img"));
				pdto.setAccount_Phone_Num(rs.getString("account_Phone_Num"));
				pdto.setAccount_Flag(rs.getInt("account_Flag"));
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(rs);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(conn);
		}

		return pdto;

	} // end viewMethod()
	
	//해당 계정 정보 수정
	public void updateProfile(AccountDTO dto) {

		try {
			conn = DbcpTemplate.getInit();
			String sql = "update note_webaccount set account_img=?,account_about=?,account_phone_num=?,account_email=? where account_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getAccount_Img());
			pstmt.setString(2, dto.getAccount_About());
			pstmt.setString(3, dto.getAccount_Phone_Num());
			pstmt.setString(4, dto.getAccount_Email());
			pstmt.setInt(5, dto.getAccount_Num());
			pstmt.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(rs);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(conn);
		}

	} // end updateProfile()

	//by.동기 해당 유저가 follow 하는 유저 list
	public List<FollowDTO> followid(int num) {
		List<FollowDTO> aList = new ArrayList<FollowDTO>();

		try {
			conn = DbcpTemplate.getInit();
			String sql = "select * from note_follow where account_num=?";
			pstmt = conn.prepareStatement(sql);

		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(rs);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(conn);
		}

		return aList;
	}

	//session에 account_img, account_about을 저장함
	public void initSaveSetting(AccountDTO dto) {

		try {
			conn = DbcpTemplate.getInit();
			String sql = "update note_webaccount set account_img=?,account_about=?,account_phone_num=? where account_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getAccount_Img());
			pstmt.setString(2, dto.getAccount_About());
			pstmt.setString(3, dto.getAccount_Phone_Num());
			pstmt.setInt(4, dto.getAccount_Num());
			pstmt.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(rs);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(conn);
		}

	} // end saveSetting()

	//유저가 이미지를 사용하지 않을 경우 임시 이미지로 지정
	public void skipSetting(int num) {

		try {
			conn = DbcpTemplate.getInit();
			String sql = "update note_webaccount set account_img='person.png' where account_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(rs);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(conn);
		}

	} // end skipSetting()

	//by.동기 해당 유저가 follow한 유저들의 정보를 join table에서 가져옴
	public List<AccountDTO> follow(int num) {

	      List<AccountDTO> aList = new ArrayList<AccountDTO>();

	      try {
	         conn = DbcpTemplate.getInit();
	         String sql = "SELECT account_name, account_img, follow_seq, account_num FROM note_account_follow_join WHERE account_num=? order by follow_seq desc";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, num);

	         rs = pstmt.executeQuery();

	         while (rs.next()) {
	            AccountDTO dto = new AccountDTO();
	            dto.setAccount_Num(rs.getInt("account_num"));
	            dto.setAccount_Name(rs.getString("account_name"));
	            dto.setAccount_Img(rs.getString("account_img"));
	            aList.add(dto);
	         }
	      } catch (NamingException | SQLException e) {
	         e.printStackTrace();
	      } finally {
	         DbcpTemplate.close(conn);
	         DbcpTemplate.close(stmt);
	         DbcpTemplate.close(pstmt);
	         DbcpTemplate.close(rs);
	      }

	      return aList;

	   }// end follow() ///////////////////

	//account_id, account_name, account_emial 중복 체크
	public List<AccountDTO> checkSignup() {

		List<AccountDTO> aList = new ArrayList<AccountDTO>();

		try {
			conn = DbcpTemplate.getInit();
			String sql = "select account_id,account_name,account_email from note_webaccount";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				AccountDTO dto = new AccountDTO();
				dto.setAccount_Id(rs.getString("account_id"));
				dto.setAccount_Name(rs.getString("account_name"));
				dto.setAccount_Email(rs.getString("account_email"));
				aList.add(dto);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(rs);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(conn);
		}

		return aList;

	} // end checkSignup()

	//by.동기 해당 유저가 follow한 게시글의 post_num list
	public List<PostDTO> followerid(int num, String name) {
		List<PostDTO> aList = new ArrayList<PostDTO>();
		try {
			conn = DbcpTemplate.getInit();
			String sql = "SELECT post_num FROM note_post WHERE account_name in "
					+ "(SELECT follow_name FROM note_follow WHERE account_num=?) or account_name=? ORDER BY post_num DESC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, name);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PostDTO dto = new PostDTO();
				dto.setPost_Num(rs.getInt("post_num"));
				aList.add(dto);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(conn);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(rs);
		}

		return aList;
	}// end follow() ///////////////////

	//by.동기 해당 유저가 follow한 게시글 및 게시글 작성자 정보 list
	public List<AccountPostDTO> post(List<PostDTO> aList) {
		List<AccountPostDTO> aList2 = new ArrayList<AccountPostDTO>();

		try {
			conn = DbcpTemplate.getInit();
			String sql = "SELECT * FROM note_webaccount_post_join WHERE post_num=? ORDER BY post_num DESC";
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < aList.size(); i++) {
				pstmt.setInt(1, aList.get(i).getPost_Num());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					AccountPostDTO dto = new AccountPostDTO();
					dto.setAccount_Id(rs.getString("account_id"));
					dto.setAccount_Name(rs.getString("account_name"));
					dto.setAccount_Num(rs.getInt("account_num"));
					dto.setAccount_Img(rs.getString("account_img"));
					dto.setPost_Num(rs.getInt("post_num"));
					dto.setPost_Img(rs.getString("post_img"));
					dto.setPost_Content(rs.getString("post_content"));
					dto.setPost_Like(rs.getInt("post_like"));
					dto.setPost_Sysdate(rs.getDate("post_sysdate"));
					dto.setPost_Flag(rs.getInt("post_flag"));
					aList2.add(dto);
				}
			}

		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(conn);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(rs);
		}

		return aList2;
	}// end post() ///////////////////

	//by.동기 해당 게시글의 댓글 list
	public List<CommentDTO> getComment(int postNum) {

		List<CommentDTO> aList = new ArrayList<CommentDTO>();

		try {
			conn = DbcpTemplate.getInit();
			String sql = "select * from note_webcomment where post_num=? order by comment_seq asc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CommentDTO dto = new CommentDTO();
				dto.setComment_Content(rs.getString("comment_content"));
				dto.setComment_Level(rs.getInt("comment_level"));
				dto.setComment_Name(rs.getString("comment_name"));
				dto.setComment_Ref(rs.getInt("comment_ref"));
				dto.setComment_Seq(rs.getInt("comment_seq"));
				dto.setComment_Step(rs.getInt("comment_step"));
				aList.add(dto);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(conn);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(rs);
		}

		return aList;

	} // end getComment()

	//by.동기 게시글 작성
	public void uploadPost(PostDTO dto) {

		try {
			conn = DbcpTemplate.getInit();
			String sql = "insert into note_post values(post_post_num_seq.nextval,?,?,?,0,sysdate,0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getAccount_Name());
			pstmt.setString(2, dto.getPost_Content());
			pstmt.setString(3, dto.getPost_Img());
			pstmt.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(conn);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(rs);
		}

	} // end uploadPost()

	//유저가 계정을 생성할때 관리자를 자동으로 follow 함
	public void followAdmin(int accountNum) {

		try {
			conn = DbcpTemplate.getInit();
			String sql = "insert into note_follow values(follow_follow_seq_seq.nextval,?,'admin')";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accountNum);
			pstmt.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(conn);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(rs);
		}

	} // end followAdmin()

	//by.동기 댓글 작성
	public void insertComment(CommentDTO dto) {
		try {
			conn = DbcpTemplate.getInit();
			String sql = "insert into note_webcomment values(webcomment_comment_seq_seq.nextval,?,0,0,0,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getPost_Num());
			pstmt.setString(2, dto.getComment_Name());
			pstmt.setString(3, dto.getComment_Content());
			pstmt.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(rs);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(conn);
		}

	}// end insertMethod()

	//account_name으로 검색한 list
	public List<SearchDTO> listMethod(String searchWord) {

		List<SearchDTO> aList = new ArrayList<SearchDTO>();

		try {
			conn = DbcpTemplate.getInit();

			String sql = "select account_num,account_id,account_name,account_img ";
			sql += " from note_webaccount where lower(account_name) ";
			if (searchWord != "") {
				sql += " like lower (?)";
			} else {
				sql += " like lower ('% %')";
			}
			pstmt = conn.prepareStatement(sql);

			if (searchWord != "") {
				pstmt.setString(1, "%" + searchWord.toLowerCase() + "%");
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				SearchDTO dto = new SearchDTO();
				dto.setAccount_Num(rs.getInt("account_num"));
				dto.setAccount_Id(rs.getString("account_id"));
				dto.setAccount_Name(rs.getString("account_name"));
				dto.setAccount_Img(rs.getString("account_img"));
				aList.add(dto);
			}

		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(conn);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(rs);
			DbcpTemplate.close(stmt);
		}

		return aList;

	}// end listMethod

	//by.동기 해당 유저 follow 추가
	//followingNum = follow 하는 유저의 account_num
	//followedName = follow 받는 유저의 account_name
	public void insertFollow(String followedName, int followingNum) {

		try {
			conn = DbcpTemplate.getInit();

			String sql = "insert into note_follow values(follow_follow_seq_seq.nextval,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, followingNum);
			pstmt.setString(2, followedName);
			pstmt.executeUpdate();

		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(conn);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(rs);
			DbcpTemplate.close(stmt);
		}

	} // end insertFollow()

	//by.동기 해당 유저 follow 삭제
	//followingNum = follow 한 유저의 account_num
	//followedName = follow 받은 유저의 account_name
	public void deleteMethod(String followedName, int followingNum) {
		try {
			conn = DbcpTemplate.getInit();

			String sql = "delete from note_follow where account_num=? and follow_name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, followingNum);
			pstmt.setString(2, followedName);

			pstmt.executeUpdate();

		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(conn);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(rs);
			DbcpTemplate.close(stmt);
		}
	} // end deleteMethod()

	//by.동기 해당 유저의 게시글 list
	public List<PostDTO> getPostMethod(String accountName) {

		List<PostDTO> aList = new ArrayList<PostDTO>();

		try {
			conn = DbcpTemplate.getInit();
			String sql = "select * from note_post where account_name=? order by post_num desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, accountName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PostDTO dto = new PostDTO();
				dto.setPost_Content(rs.getString("post_content"));
				dto.setPost_Flag(rs.getInt("post_flag"));
				dto.setAccount_Name(rs.getString("account_name"));
				dto.setPost_Img(rs.getString("post_img"));
				dto.setPost_Like(rs.getInt("post_like"));
				dto.setPost_Num(rs.getInt("post_num"));
				dto.setPost_Sysdate(rs.getDate("post_sysdate"));
				aList.add(dto);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(conn);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(rs);
			DbcpTemplate.close(stmt);
		}

		return aList;

	} // end getPostMethod()

	//해당 유저의 게시글 갯수
	public int countPost(String accountName) {

		int num = 0;

		try {
			conn = DbcpTemplate.getInit();
			String sql = "select * from note_post where account_name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, accountName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				num++;
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(conn);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(rs);
			DbcpTemplate.close(stmt);
		}

		return num;

	} // end countPost()

	//해당 유저가 follow한 갯수
	public int countFollow(int accountNum) {

		int num = 0;

		try {
			conn = DbcpTemplate.getInit();
			String sql = "select * from note_follow where account_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accountNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				num++;
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(conn);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(rs);
			DbcpTemplate.close(stmt);
		}

		return num;

	} // end countFollow()

	//해당 유저가 follow 받은 갯수
	public int countFollower(String followName) {

		int num = 0;

		try {
			conn = DbcpTemplate.getInit();
			String sql = "select * from note_follow where follow_name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, followName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				num++;
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(conn);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(rs);
			DbcpTemplate.close(stmt);
		}

		return num;

	} // end countFollower()

	//by.동기 해당 게시글 신고
	public void addflagtable(int post_num, int account_num) {

		try {
			conn = DbcpTemplate.getInit();
			String sql = "INSERT INTO note_flag_table (flag_num, post_num, account_num)"
					+ " VALUES (flag_table_flag_num_seq.nextval, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, post_num);
			pstmt.setInt(2, account_num);
			pstmt.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(conn);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(rs);
		}
	}// end addflagtable()
	
	//by.동기 해당 유저 신고
	public void addaflagtable(int flag_account_num, int account_num) {

		try {
			conn = DbcpTemplate.getInit();
			String sql = "INSERT INTO note_aflag_table (flag_num, flag_account_num, account_num)"
					+ " VALUES (AFLAG_TABLE_FLAG_NUM_SEQ.nextval, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, flag_account_num);
			pstmt.setInt(2, account_num);
			pstmt.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(conn);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(rs);
		}
	}// end addflagtable()

	//by.동기 게시글 신고시 해당 게시글 신고 갯수 +1
	public void addflag(int post_num) {

		try {
			conn = DbcpTemplate.getInit();
			String sql = "UPDATE note_post set post_flag=post_flag+1 where post_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, post_num);
			pstmt.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(conn);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(rs);
		}
	}// end addflag()

	//by.동기 게시글 신고시 note_flag_category 테이블에 해당 게시글이 신고 받은 이유 +1
	public void addflag(int post_num, String kind) {

		try {
			conn = DbcpTemplate.getInit();
			String sql = "UPDATE note_flag_category set " + kind + " = " + kind + " +1 where post_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, post_num);
			pstmt.executeUpdate();

		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(conn);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(rs);
		}
	}// end addflag()
	
	//by.동기 유저 신고시 해당 유저 신고 갯수 +1
	public void addaflag(int account_num) {

		try {
			conn = DbcpTemplate.getInit();
			String sql = "UPDATE note_webaccount set account_flag=account_flag+1 where account_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, account_num);
			pstmt.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(conn);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(rs);
		}
	}// end addflag()
	
	//by.동기 유저 신고시 note_aflag_category 테이블에 해당 유저가 신고 받은 이유 +1
	public void addaflag(int account_num, String kind) {

		try {
			conn = DbcpTemplate.getInit();
			String sql = "UPDATE note_aflag_category set " + kind + " = " + kind + " +1 where account_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, account_num);
			pstmt.executeUpdate();

		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(conn);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(rs);
		}
	}// end addflag()

	//by.동기 해당 유저가 신고한 게시글 list
	public List<PostDTO> flaglist(int account_num) {

		List<PostDTO> aList = new ArrayList<PostDTO>();

		try {
			conn = DbcpTemplate.getInit();
			String sql = "SELECT post_num FROM note_flag_table WHERE account_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, account_num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PostDTO dto = new PostDTO();
				dto.setPost_Num(rs.getInt("post_num"));
				aList.add(dto);

			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(conn);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(rs);
		}

		return aList;

	}// end flaglist()

	//by.동기 해당 게시글 좋아요 -1
	public void likeminus(int post_num) {

		try {
			conn = DbcpTemplate.getInit();
			String sql = "UPDATE note_post set post_like=post_like-1 where post_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, post_num);
			pstmt.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(conn);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(rs);
		}

	}// end likeminus()

	//by.동기 해당 게시글 좋아요 삭제
	public void dellike(int post_num, int account_num) {

		try {
			conn = DbcpTemplate.getInit();
			String sql = "DELETE FROM note_like_table where post_num = ? AND account_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, post_num);
			pstmt.setInt(2, account_num);
			pstmt.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(conn);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(rs);
		}

	}// end dellike()

	//by.동기 해당 게시글 좋아요 +1
	public void likeplus(int post_num) {

		try {
			conn = DbcpTemplate.getInit();
			String sql = "UPDATE note_post set post_like=post_like+1 where post_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, post_num);
			pstmt.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(conn);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(rs);
		}

	}// end likeplus()

	//by.동기 해당 게시글 좋아요 추가
	public void insertlike(int post_num, int account_num) {

		try {
			conn = DbcpTemplate.getInit();
			String sql = "INSERT INTO note_like_table VALUES(like_table_like_num_seq.nextval,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, post_num);
			pstmt.setInt(2, account_num);
			pstmt.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(conn);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(rs);
		}

	}// end insertlike()

	//by.동기 해당 유저의 좋아요 누른 게시글 list
	public List<PostDTO> likelist(int account_num) {

		List<PostDTO> aList = new ArrayList<PostDTO>();

		try {
			conn = DbcpTemplate.getInit();
			String sql = "SELECT post_num FROM note_like_table WHERE account_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, account_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PostDTO dto = new PostDTO();
				dto.setPost_Num(rs.getInt("post_num"));
				aList.add(dto);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(conn);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(rs);
		}
		return aList;
	}// end likelist()

	//게시글 작성시 note_flag_category table에 추가
	public void addflagcategorytable(int post_num) {

		try {
			conn = DbcpTemplate.getInit();
			String sql ="INSERT INTO note_FLAG_CATEGORY (FC_NUM, POST_NUM, SPAM, ABUSIVE, OBSCENE, ILLEGALADV) VALUES (flag_category_fc_num_seq.nextval, ?, '0', '0', '0', '0')";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, post_num);
			pstmt.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(conn);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(rs);
		}
	}// end addflagcategorytable()
	
	//유저 신고 종류 추가
	public void addaflagcategorytable(int account_num) {

		try {
			conn = DbcpTemplate.getInit();
			String sql ="INSERT INTO note_AFLAG_CATEGORY (AC_NUM, ACCOUNT_NUM, SPAM, ABUSIVE, OBSCENE, ILLEGALADV) VALUES (aflag_category_ac_num_seq.nextval, ?, '0', '0', '0', '0')";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, account_num);
			pstmt.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(conn);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(rs);
		}
	}// end addflagcategorytable()

	//by.동기 가장 최근에 작성된 게시글
	public int updatepost() {
		int num =0;
		try {
			conn = DbcpTemplate.getInit();
			stmt = conn.createStatement();
			String sql ="select  max (post_num) from note_post";
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				num = rs.getInt(1);
			}
			
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(conn);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(rs);
		}
		return num;
	}

	//by.동기 해당 유저가 좋아요 누른 게시글인지 확인
	public int postlikechk(int post_num, int account_num) {
		int num=0;
		try {
			conn = DbcpTemplate.getInit();
			String sql = "select* from note_like_table where account_num= ? and post_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, account_num);
			pstmt.setInt(2, post_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num =1;
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(conn);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(rs);
		}
		
		return num;
	}
	
	//by.동기 해당 게시글 좋아요 갯수
	public int return_like(int post_num) {
		int num = 0;
		try {
			conn = DbcpTemplate.getInit();
			String sql = "select post_like from note_post where post_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, post_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt("post_like");
			}
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbcpTemplate.close(conn);
			DbcpTemplate.close(stmt);
			DbcpTemplate.close(pstmt);
			DbcpTemplate.close(rs);
		}
		return num;
		
	}
	
	//유저 삭제시 해당 유저 follow 삭제
	public void followDelMethod(String name) {
	      try {
	         conn = DbcpTemplate.getInit();
	         String sql = "DELETE FROM note_follow WHERE follow_name=?";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, name);
	         pstmt.executeUpdate();
	      } catch (NamingException | SQLException e) {
	         e.printStackTrace();
	      } finally {
	         DbcpTemplate.close(rs);
	         DbcpTemplate.close(pstmt);
	         DbcpTemplate.close(stmt);
	         DbcpTemplate.close(conn);
	      }
	   }
	
	//by.동기 해당 유저 신고 여부 확인
	public int flagChek(int account_num, int flag_account_num) {
	    int num = 0;
	    System.out.println("account num: " + account_num);
	    System.out.println("flag_account num: " + flag_account_num);
		try {
	         conn = DbcpTemplate.getInit();
	         String sql = "select * from note_AFLAG_TABLE where account_num=? and flag_account_num=?";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, account_num);
	         pstmt.setInt(2, flag_account_num);
	         rs = pstmt.executeQuery();
	         if (rs.next()) {
				num = 1;
			}
	      } catch (NamingException | SQLException e) {
	         e.printStackTrace();
	      } finally {
	         DbcpTemplate.close(rs);
	         DbcpTemplate.close(pstmt);
	         DbcpTemplate.close(stmt);
	         DbcpTemplate.close(conn);
	      }
		return num;
	   }
	
	
} // end class
