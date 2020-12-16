package webproject.dto;

public class FlagCategoryDTO {

	private int fc_Num, post_Num, spam, abusive, obscene, illegaladv;
	
	public FlagCategoryDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getFc_Num() {
		return fc_Num;
	}

	public void setFc_Num(int fc_Num) {
		this.fc_Num = fc_Num;
	}

	public int getPost_Num() {
		return post_Num;
	}

	public void setPost_Num(int post_Num) {
		this.post_Num = post_Num;
	}

	public int getSpam() {
		return spam;
	}

	public void setSpam(int spam) {
		this.spam = spam;
	}

	public int getAbusive() {
		return abusive;
	}

	public void setAbusive(int abusive) {
		this.abusive = abusive;
	}

	public int getObscene() {
		return obscene;
	}

	public void setObscene(int obscene) {
		this.obscene = obscene;
	}

	public int getIllegaladv() {
		return illegaladv;
	}

	public void setIllegaladv(int illegaladv) {
		this.illegaladv = illegaladv;
	}


}
