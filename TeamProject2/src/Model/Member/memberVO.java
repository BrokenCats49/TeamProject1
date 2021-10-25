package Model.Member;

public class memberVO {
	private String userid;
	private String passwd;
	private String name;
	private String email;
	private String first_time;
	private String last_time;
	private String cardnum;
	private int charge;
	private int cash;
	private String chargeday;
	
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	public String getChargeday() {
		return chargeday;
	}
	public void setChargeday(String chargeday) {
		this.chargeday = chargeday;
	}
	public int getCharge() {
		return charge;
	}
	public void setCharge(int charge) {
		this.charge = charge;
	}
	public String getCardnum() {
		return cardnum;
	}
	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}
	public String getFirst_time() {
		return first_time;
	}
	public void setFirst_time(String first_time) {
		this.first_time = first_time;
	}
	public String getLast_time() {
		return last_time;
	}
	public void setLast_time(String last_time) {
		this.last_time = last_time;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
