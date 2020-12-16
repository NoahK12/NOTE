package webproject.dto;

import java.sql.Date;

public class PostDTO {
private int post_Num, post_Like, post_Flag;
private String account_Name, post_Content, post_Img;
private Date post_Sysdate;
	
	public PostDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getPost_Num() {
		return post_Num;
	}

	public void setPost_Num(int post_Num) {
		this.post_Num = post_Num;
	}

	public int getPost_Like() {
		return post_Like;
	}

	public void setPost_Like(int post_Like) {
		this.post_Like = post_Like;
	}

	public int getPost_Flag() {
		return post_Flag;
	}

	public void setPost_Flag(int post_Flag) {
		this.post_Flag = post_Flag;
	}

	public String getAccount_Name() {
		return account_Name;
	}

	public void setAccount_Name(String account_Name) {
		this.account_Name = account_Name;
	}

	public String getPost_Content() {
		return post_Content;
	}

	public void setPost_Content(String post_Content) {
		this.post_Content = post_Content;
	}

	public String getPost_Img() {
		return post_Img;
	}

	public void setPost_Img(String post_Img) {
		this.post_Img = post_Img;
	}

	public Date getPost_Sysdate() {
		return post_Sysdate;
	}

	public void setPost_Sysdate(Date post_Sysdate) {
		this.post_Sysdate = post_Sysdate;
	}

}
