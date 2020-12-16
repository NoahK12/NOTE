package webproject.dto;

public class CommentDTO {
	private int comment_Seq, post_Num, comment_Ref, comment_Step, comment_Level ;
	private String comment_Name, comment_Content;

	public CommentDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getComment_Seq() {
		return comment_Seq;
	}

	public void setComment_Seq(int comment_Seq) {
		this.comment_Seq = comment_Seq;
	}

	public int getPost_Num() {
		return post_Num;
	}

	public void setPost_Num(int post_Num) {
		this.post_Num = post_Num;
	}

	public int getComment_Ref() {
		return comment_Ref;
	}

	public void setComment_Ref(int comment_Ref) {
		this.comment_Ref = comment_Ref;
	}

	public int getComment_Step() {
		return comment_Step;
	}

	public void setComment_Step(int comment_Step) {
		this.comment_Step = comment_Step;
	}

	public int getComment_Level() {
		return comment_Level;
	}

	public void setComment_Level(int comment_Level) {
		this.comment_Level = comment_Level;
	}

	public String getComment_Name() {
		return comment_Name;
	}

	public void setComment_Name(String comment_Name) {
		this.comment_Name = comment_Name;
	}

	public String getComment_Content() {
		return comment_Content;
	}

	public void setComment_Content(String comment_Content) {
		this.comment_Content = comment_Content;
	}


	

}
