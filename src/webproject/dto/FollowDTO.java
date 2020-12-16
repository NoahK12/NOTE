package webproject.dto;

public class FollowDTO {

	private int follow_Seq;
	private String account_Num, follow_Name;
	
	public FollowDTO() {
	}

	public int getFollow_Seq() {
		return follow_Seq;
	}

	public void setFollow_Seq(int follow_Seq) {
		this.follow_Seq = follow_Seq;
	}

	public String getAccount_Num() {
		return account_Num;
	}

	public void setAccount_Num(String account_Num) {
		this.account_Num = account_Num;
	}

	public String getFollow_Id() {
		return follow_Name;
	}

	public void setFollow_Id(String follow_Name) {
		this.follow_Name = follow_Name;
	}

	
} // end class
