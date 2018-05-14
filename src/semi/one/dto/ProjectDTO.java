package semi.one.dto;

import java.sql.Date;

public class ProjectDTO {

   private int prj_no;
   private String pd_id;
   private String prj_cat;
   private String prj_title;
   private String prj_content;
   private long prj_goal;
   private long prj_curr;
   private String prj_account;
   private Date prj_due;
   private Date prj_date;
   private int prj_picks;
   private String prj_state;
   private String prj_url;
   private String prj_bank;
   private String prj_comment;
   //사진
   private String prj_photo;
   
   
   public int getPrj_no() {
      return prj_no;
   }
   public void setPrj_no(int prj_no) {
      this.prj_no = prj_no;
   }

   public String getPd_id() {
      return pd_id;
   }
   public void setPd_id(String pd_id) {
      this.pd_id = pd_id;
   }
   public String getPrj_cat() {
      return prj_cat;
   }
   public void setPrj_cat(String prj_cat) {
      this.prj_cat = prj_cat;
   }
   public String getPrj_title() {
      return prj_title;
   }
   public void setPrj_title(String prj_title) {
      this.prj_title = prj_title;
   }
   public String getPrj_content() {
      return prj_content;
   }
   public void setPrj_content(String prj_content) {
      this.prj_content = prj_content;
   }
   public long getPrj_goal() {
      return prj_goal;
   }
   public void setPrj_goal(long prj_goal) {
      this.prj_goal = prj_goal;
   }
   public long getPrj_curr() {
      return prj_curr;
   }
   public void setPrj_curr(long prj_curr) {
      this.prj_curr = prj_curr;
   }
   public String getPrj_account() {
      return prj_account;
   }
   public void setPrj_account(String prj_account) {
      this.prj_account = prj_account;
   }
   public Date getPrj_due() {
      return prj_due;
   }
   public void setPrj_due(Date prj_due) {
      this.prj_due = prj_due;
   }
   public Date getPrj_date() {
      return prj_date;
   }
   public void setPrj_date(Date prj_date) {
      this.prj_date = prj_date;
   }
   public int getPrj_picks() {
      return prj_picks;
   }
   public void setPrj_picks(int prj_picks) {
      this.prj_picks = prj_picks;
   }
   public String getPrj_state() {
      return prj_state;
   }
   public void setPrj_state(String prj_state) {
      this.prj_state = prj_state;
   }
   public String getPrj_url() {
      return prj_url;
   }
   public void setPrj_url(String prj_url) {
      this.prj_url = prj_url;
   }
   public String getPrj_photo() {
      return prj_photo;
   }
   public void setPrj_photo(String prj_photo) {
      this.prj_photo = prj_photo;
   }
   public String getPrj_comment() {
      return prj_comment;
   }
   public void setPrj_comment(String prj_comment) {
      this.prj_comment = prj_comment;
   }
   public String getPrj_bank() {
      return prj_bank;
   }
   public void setPrj_bank(String prj_bank) {
      this.prj_bank = prj_bank;
   }

}