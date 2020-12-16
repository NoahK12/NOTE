package webproject.dto;

import java.util.Date;

public class AccountPostDTO {
	private int post_Like, post_Flag, account_Num,post_Num; 
	private String  account_Name,account_Img, post_Img, post_Content , account_Id; 
	private Date post_Sysdate;
	
	public String getAccount_Id() {
		return account_Id;
	}

	public void setAccount_Id(String account_Id) {
		this.account_Id = account_Id;
	}

	public AccountPostDTO() {

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
	
	public int getPost_Num() {
		return post_Num;
	}
	
	public void setPost_Num(int post_num) {
		this.post_Num = post_num;
	}

	public int getAccount_Num() {
		return account_Num;
	}

	public void setAccount_Num(int account_Num) {
		this.account_Num = account_Num;
	}

	public String getAccount_Img() {
		return account_Img;
	}

	public void setAccount_Img(String account_Img) {
		this.account_Img = account_Img;
	}

	public String getPost_Img() {
		return post_Img;
	}

	public void setPost_Img(String post_Img) {
		this.post_Img = post_Img;
	}

	public String getPost_Content() {
		return post_Content;
	}
	public void setAccount_Name(String account_Name) {
		this.account_Name = account_Name;
	}

	public String getAccount_Name() {
		return account_Name;
	}
	

	public void setPost_Content(String post_Content) {
		this.post_Content = post_Content;
	}

	public Date getPost_Sysdate() {
		return post_Sysdate;
	}

	public void setPost_Sysdate(Date post_Sysdate) {
		this.post_Sysdate = post_Sysdate;
	}
	
	
}
