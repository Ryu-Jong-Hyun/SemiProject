package semi.one.dto;

import java.sql.Date;

public class ReplyDTO {
	
	private int rep_no;
	private int inq_no;
	private String rep_title;
	private String rep_content;
	private Date rep_date;
	
	public int getRep_no() {
		return rep_no;
	}
	public void setRep_no(int rep_no) {
		this.rep_no = rep_no;
	}
	public int getInq_no() {
		return inq_no;
	}
	public void setInq_no(int inq_no) {
		this.inq_no = inq_no;
	}
	public String getRep_title() {
		return rep_title;
	}
	public void setRep_title(String rep_title) {
		this.rep_title = rep_title;
	}
	public String getRep_content() {
		return rep_content;
	}
	public void setRep_content(String rep_content) {
		this.rep_content = rep_content;
	}
	public Date getRep_date() {
		return rep_date;
	}
	public void setRep_date(Date rep_date) {
		this.rep_date = rep_date;
	}
	
	
}
