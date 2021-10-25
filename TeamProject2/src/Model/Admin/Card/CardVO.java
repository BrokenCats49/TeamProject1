package Model.Admin.Card;

public class CardVO {
	private String card_code;   // 카드넘버
	private String charge_date; // 마지막충전일
	private int now_fee;	    // 현재금액 
	private String user_code;   // 회원코드
	
	private String charge_day;  // 충전일
	private int charge;
	private int cash;
	
	public int getCharge() {
		return charge;
	}
	public void setCharge(int charge) {
		this.charge = charge;
	}
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	public String getCharge_day() {
		return charge_day;
	}
	public void setCharge_day(String charge_day) {
		this.charge_day = charge_day;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getCard_code() {
		return card_code;
	}
	public void setCard_code(String card_code) {
		this.card_code = card_code;
	}
	public String getCharge_date() {
		return charge_date;
	}
	public void setCharge_date(String charge_date) {
		this.charge_date = charge_date;
	}
	public int getNow_fee() {
		return now_fee;
	}
	public void setNow_fee(int now_fee) {
		this.now_fee = now_fee;
	}
	
}
