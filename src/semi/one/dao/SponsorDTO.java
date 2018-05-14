package semi.one.dao;

import java.sql.Date;

public class SponsorDTO {
	private int spon_no;
	private int prj_no;
	private String id;
	private String spon_item;
	private int spon_don;
	private Date spon_date;

	
	
	public int getSpon_no() {
		return spon_no;
	}
	public void setSpon_no(int spon_no) {
		this.spon_no = spon_no;
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
	public String getSpon_item() {
		return spon_item;
	}
	public void setSpon_item(String spon_item) {
		this.spon_item = spon_item;
	}

	public int getSpon_don() {
		return spon_don;
	}
	public void setSpon_don(int spon_don) {
		this.spon_don = spon_don;
	}
	public Date getSpon_date() {
		return spon_date;
	}
	public void setSpon_date(Date spon_date) {
		this.spon_date = spon_date;
	}
	
	
}
