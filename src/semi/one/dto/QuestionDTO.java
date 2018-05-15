package semi.one.dto;

import java.sql.Date;

public class QuestionDTO {
	private int qus_no;
	private int prj_no;
	private String id;
	private String qus_cat;
	private String qus_title;
	private String qus_content;
	private String qus_state;
	private Date qus_date;
	
	
	public int getQus_no() {
		return qus_no;
	}
	public void setQus_no(int qus_no) {
		this.qus_no = qus_no;
	}
	public int getPrj_no() {
		return prj_no;
	}
	public void setPrj_no(int prj_no) {
		this.prj_no = prj_no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQus_cat() {
		return qus_cat;
	}
	public void setQus_cat(String qus_cat) {
		this.qus_cat = qus_cat;
	}
	public String getQus_title() {
		return qus_title;
	}
	public void setQus_title(String qus_title) {
		this.qus_title = qus_title;
	}
	public String getQus_content() {
		return qus_content;
	}
	public void setQus_content(String qus_content) {
		this.qus_content = qus_content;
	}
	public String getQus_state() {
		return qus_state;
	}
	public void setQus_state(String qus_state) {
		this.qus_state = qus_state;
	}
	public Date getQus_date() {
		return qus_date;
	}
	public void setQus_date(Date qus_date) {
		this.qus_date = qus_date;
	}
	
	
}
