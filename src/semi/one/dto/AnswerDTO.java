package semi.one.dto;

import java.sql.Date;

public class AnswerDTO {
	
	private int ans_no;
	private int qus_no;
	private String ans_title;
	private String ans_content;
	private Date ans_date;
	
	public int getAns_no() {
		return ans_no;
	}
	public void setAns_no(int ans_no) {
		this.ans_no = ans_no;
	}
	public int getQus_no() {
		return qus_no;
	}
	public void setQus_no(int qus_no) {
		this.qus_no = qus_no;
	}
	public String getAns_title() {
		return ans_title;
	}
	public void setAns_title(String ans_title) {
		this.ans_title = ans_title;
	}
	public String getAns_content() {
		return ans_content;
	}
	public void setAns_content(String ans_content) {
		this.ans_content = ans_content;
	}
	public Date getAns_date() {
		return ans_date;
	}
	public void setAns_date(Date ans_date) {
		this.ans_date = ans_date;
	}
	
	
}
