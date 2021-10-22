package Model.Admin.Acount;

public class AccountVO {
	public String getOrder_code() {
		return order_code;
	}
	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}
	public String getAccount_day() {
		return account_day;
	}
	public void setAccount_day(String account_day) {
		this.account_day = account_day;
	}
	public String getGame_type() {
		return game_type;
	}
	public void setGame_type(String game_type) {
		this.game_type = game_type;
	}
	public String getPart_name() {
		return part_name;
	}
	public void setPart_name(String part_name) {
		this.part_name = part_name;
	}
	public int getRepair_fee() {
		return repair_fee;
	}
	public void setRepair_fee(int repair_fee) {
		this.repair_fee = repair_fee;
	}
	public int getTuning_fee() {
		return tuning_fee;
	}
	public void setTuning_fee(int tuning_fee) {
		this.tuning_fee = tuning_fee;
	}
	public int getMaintain_fee() {
		return maintain_fee;
	}
	public void setMaintain_fee(int maintain_fee) {
		this.maintain_fee = maintain_fee;
	}
	public int getEquip_rental_fee() {
		return equip_rental_fee;
	}
	public void setEquip_rental_fee(int equip_rental_fee) {
		this.equip_rental_fee = equip_rental_fee;
	}
	public int getCash_sales() {
		return cash_sales;
	}
	public void setCash_sales(int cash_sales) {
		this.cash_sales = cash_sales;
	}
	public int getCard_sales() {
		return card_sales;
	}
	public void setCard_sales(int card_sales) {
		this.card_sales = card_sales;
	}
	public int getAccount_total() {
		return account_total;
	}
	public void setAccount_total(int account_total) {
		this.account_total = account_total;
		
	}
	private String order_code;
	private String account_day;
	private String game_type;
	private String part_name;
	private int order_fee;
	private int repair_fee;
	private int tuning_fee;
	private int maintain_fee;
	private int equip_rental_fee;
	private int cash_sales;
	private int card_sales;
	private int account_total;
	private String startdate;
	
	private String repair_machine;
	private String repair_comment;
	private int repair_pay;
	
	private String machine_code;
	private int cash;
	private int card;
	
	private int card_charge;
	private int cash_charge;
	
	private int m_plus;
	private int m_minus;
	private int y_plus;
	private int y_minus;
	
	
	
	
	public int getM_plus() {
		return m_plus;
	}
	public void setM_plus(int m_plus) {
		this.m_plus = m_plus;
	}
	public int getM_minus() {
		return m_minus;
	}
	public void setM_minus(int m_minus) {
		this.m_minus = m_minus;
	}
	public int getY_plus() {
		return y_plus;
	}
	public void setY_plus(int y_plus) {
		this.y_plus = y_plus;
	}
	public int getY_minus() {
		return y_minus;
	}
	public void setY_minus(int y_minus) {
		this.y_minus = y_minus;
	}
	public int getOrder_fee() {
		return order_fee;
	}
	public void setOrder_fee(int order_fee) {
		this.order_fee = order_fee;
	}
	public int getCard_charge() {
		return card_charge;
	}
	public void setCard_charge(int card_charge) {
		this.card_charge = card_charge;
	}
	public int getCash_charge() {
		return cash_charge;
	}
	public void setCash_charge(int cash_charge) {
		this.cash_charge = cash_charge;
	}
	public String getMachine_code() {
		return machine_code;
	}
	public void setMachine_code(String machine_code) {
		this.machine_code = machine_code;
	}
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	public int getCard() {
		return card;
	}
	public void setCard(int card) {
		this.card = card;
	}
	public String getRepair_machine() {
		return repair_machine;
	}
	public void setRepair_machine(String repair_machine) {
		this.repair_machine = repair_machine;
	}
	public String getRepair_comment() {
		return repair_comment;
	}
	public void setRepair_comment(String repair_comment) {
		this.repair_comment = repair_comment;
	}
	public int getRepair_pay() {
		return repair_pay;
	}
	public void setRepair_pay(int repair_pay) {
		this.repair_pay = repair_pay;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public int getOH() {
		return OH;
	}
	public void setOH(int oH) {
		OH = oH;
	}
	public int getFH() {
		return FH;
	}
	public void setFH(int fH) {
		FH = fH;
	}
	public int getOT() {
		return OT;
	}
	public void setOT(int oT) {
		OT = oT;
	}
	public int getFT() {
		return FT;
	}
	public void setFT(int fT) {
		FT = fT;
	}
	public int getTT() {
		return TT;
	}
	public void setTT(int tT) {
		TT = tT;
	}
	public int getFTT() {
		return FTT;
	}
	public void setFTT(int fTT) {
		FTT = fTT;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	private int OH;
	private int FH;
	private int OT;
	private int FT;
	private int TT;
	private int FTT;
	private int total;
	
	
}
