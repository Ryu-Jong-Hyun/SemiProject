package semi.one.dto;

import java.util.Date;

public class CoinDTO {

	private String id;
	private String coin_list;
	private int coin_don;
	private Date coin_date;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCoin_list() {
		return coin_list;
	}
	public void setCoin_list(String coin_list) {
		this.coin_list = coin_list;
	}
	public int getCoin_don() {
		return coin_don;
	}
	public void setCoin_don(int coin_don) {
		this.coin_don = coin_don;
	}
	public Date getCoin_date() {
		return coin_date;
	}
	public void setCoin_date(Date coin_date) {
		this.coin_date = coin_date;
	}

	
}
