package semi.one.dto;

import java.sql.Date;

public class InquireDTO {
	private int inq_no;
	private String id;
	private String inq_cat;
	private String inq_title;
	private String inq_content;
	private String inq_state;
	private Date inq_date;
	
	
	public int getInq_no() {
		return inq_no;
	}
	public void setInq_no(int inq_no) {
		this.inq_no = inq_no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInq_cat() {
		return inq_cat;
	}
	public void setInq_cat(String inq_cat) {
		this.inq_cat = inq_cat;
	}
	public String getInq_title() {
		return inq_title;
	}
	public void setInq_title(String inq_title) {
		this.inq_title = inq_title;
	}
	public String getInq_content() {
		return inq_content;
	}
	public void setInq_content(String inq_content) {
		this.inq_content = inq_content;
	}
	public String getInq_state() {
		return inq_state;
	}
	public void setInq_state(String inq_state) {
		this.inq_state = inq_state;
	}
	public Date getInq_date() {
		return inq_date;
	}
	public void setInq_date(Date inq_date) {
		this.inq_date = inq_date;
	}
	
	
}
