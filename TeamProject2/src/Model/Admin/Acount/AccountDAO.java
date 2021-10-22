package Model.Admin.Acount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Utility.DBManager;

public class AccountDAO {

	private AccountDAO() {
	}

	private static AccountDAO instance = new AccountDAO();

	public static AccountDAO getInstance() {
		return instance;
	}
	
	
	public List<AccountVO> MCList(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AccountVO> list = new ArrayList<AccountVO>();
		String sql = "select * from bc_machine";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AccountVO vo = new AccountVO();
				vo.setMachine_code(rs.getString("machine_code"));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<AccountVO> DayList(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AccountVO> list = new ArrayList<AccountVO>();
		String sql = "select to_char(account_day,'yymmdd') from bc_account";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AccountVO vo = new AccountVO();
				vo.setAccount_day(rs.getString(1));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public List<AccountVO> MonthList(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AccountVO> list = new ArrayList<AccountVO>();
		String sql = "select to_char(account_month,'yyyy/mm'), (cash_sales+card_sales), (order_fee+repair_fee+tuning_fee+maintain_fee)  from bc_account_m";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AccountVO vo = new AccountVO();
				vo.setAccount_day(rs.getString(1));
				vo.setM_plus(rs.getInt(2));
				vo.setM_minus(rs.getInt(3));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public List<AccountVO> YearList(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AccountVO> list = new ArrayList<AccountVO>();
		String sql = "select to_char(account_year,'yyyy'), (cash_sales+card_sales), (order_fee+repair_fee+tuning_fee+maintain_fee)  from bc_account_y";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AccountVO vo = new AccountVO();
				vo.setAccount_day(rs.getString(1));
				vo.setY_plus(rs.getInt(2));
				vo.setY_minus(rs.getInt(3));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

	
	
	
	
	public int Startlist() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		String query = "select * from bc_start";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				row = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
	}
	
	
	public int StartMonth() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		String query = "select count(*) from bc_account_m where to_char(account_month,'yyyy/mm') = to_char(sysdate,'yyyy/mm')";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				row = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
	}
	
	public int StartYear() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		String query = "select count(*) from bc_account_y where to_char(account_year,'yyyy') = to_char(sysdate,'yyyy')";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				row = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
	}
	
	
	
	
	
	public int Startinsert(AccountVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		String query = "insert into bc_start(OH, FH, OT, FT, TT, FTT, total, startdate) values(?,?,?,?,?,?,?,sysdate)";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, vo.getOH());
			pstmt.setInt(2, vo.getFH());
			pstmt.setInt(3, vo.getOT());
			pstmt.setInt(4, vo.getFT());
			pstmt.setInt(5, vo.getTT());
			pstmt.setInt(6, vo.getFTT());
			pstmt.setInt(7, vo.getTotal());
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
		
	}
	
	public int Repairinsert(AccountVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		int row = 0;
		int row2 = 0;
		String query = "insert into bc_repair(repair_date, repair_machine, repair_comment, repair_pay) values(sysdate,?,?,?)";
		String sql  = "update bc_account set repair_fee = repair_fee+? where to_char(account_day,'yymmdd') = to_char(sysdate,'yymmdd')";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt2 = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getRepair_machine());
			pstmt.setString(2, vo.getRepair_comment());
			pstmt.setInt(3, vo.getRepair_pay());
			
			pstmt2.setInt(1, vo.getRepair_pay());
			
			row = pstmt.executeUpdate();
			row2 = pstmt2.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
		
	}
	
	public int Tuninginsert(AccountVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		int row = 0;
		int row2 = 0;
		String query = "insert into bc_repair(repair_date, repair_machine, repair_comment, repair_pay) values(sysdate,?,?,?)";
		String sql  = "update bc_account set tuning_fee = tuning_fee+? where to_char(account_day,'yymmdd') = to_char(sysdate,'yymmdd')";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt2 = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getRepair_machine());
			pstmt.setString(2, vo.getRepair_comment());
			pstmt.setInt(3, vo.getRepair_pay());
			
			pstmt2.setInt(1, vo.getRepair_pay());
			
			row = pstmt.executeUpdate();
			row2 = pstmt2.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
		
	}
	
	public int Gameinsert(AccountVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		int row = 0;
		int row2 = 0;
		String query = "insert into bc_mcsales(machine_code, cash, card, insert_date) values(?,?,?,sysdate)";
		String sql  = "update bc_account set cash_sales = cash_sales+?, card_sales = card_sales+? where to_char(account_day,'yymmdd') = to_char(sysdate,'yymmdd')";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt2 = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getMachine_code());
			pstmt.setInt(2, vo.getCash());
			pstmt.setInt(3, vo.getCard());
			
			pstmt2.setInt(1, vo.getCash());
			pstmt2.setInt(2, vo.getCard());
			row = pstmt.executeUpdate();
			row2 = pstmt2.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
		
	}
	
	public int Shopinsert(AccountVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		int row = 0;
		int row2 = 0;
		String query = "insert into bc_sales(insert_date, card_charge, cash, card, cash_charge) values(sysdate,?,?,?,?)";
		String sql  = "update bc_account set cash_sales = cash_sales+?+?, card_sales = card_sales+?+? where to_char(account_day,'yymmdd') = to_char(sysdate,'yymmdd')";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt2 = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getCard_charge());
			pstmt.setInt(2, vo.getCash());
			pstmt.setInt(3, vo.getCard());
			pstmt.setInt(4, vo.getCash_charge());
			
			pstmt2.setInt(3, vo.getCard_charge());
			pstmt2.setInt(1, vo.getCash());
			pstmt2.setInt(4, vo.getCard());
			pstmt2.setInt(2, vo.getCash_charge());
			row = pstmt.executeUpdate();
			row2 = pstmt2.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
		
	}
	
	public int Totalinsert(AccountVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		int row = 0;
		int yow = 0;
		int mow = 0;
		String query = "update bc_account set order_fee = ?, repair_fee = ?, tuning_fee = ?, maintain = ?, cash_sales = ?, card_sales = ? acoount_total = ? where to_char(account_day,'yymmdd') = to_char(sysdate,'yymmdd')";
		String query_m = "update bc_account_m set order_fee = order_fee+?, repair_fee = repair_fee+?, tuning_fee = tuning_fee+?, maintain = maintain+?, cash_sales = cash_sales+?, card_sales = card_sales+? acoount_total = acoount_total+? where to_char(account_day,'yyyy/mm') = to_char(sysdate,'yyyy/mm')";
		String query_y = "update bc_account_y set order_fee = order_fee+?, repair_fee = repair_fee+?, tuning_fee = tuning_fee+?, maintain = maintain+?, cash_sales = cash_sales+?, card_sales = card_sales+? acoount_total = acoount_total+? where to_char(account_day,'yyyy') = to_char(sysdate,'yyyy')";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt2 = conn.prepareStatement(query_m);
			pstmt3 = conn.prepareStatement(query_y);
			
			pstmt.setInt(1, vo.getOrder_fee());
			pstmt.setInt(2, vo.getRepair_fee());
			pstmt.setInt(3, vo.getTuning_fee());
			pstmt.setInt(4, vo.getMaintain_fee());
			pstmt.setInt(5, vo.getCash_sales());
			pstmt.setInt(6, vo.getCard_sales());
			pstmt.setInt(7, vo.getAccount_total());
			row = pstmt.executeUpdate();
			mow = pstmt2.executeUpdate();
			yow = pstmt3.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
		
	}
	public int insertMonth() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		String query = "insert into bc_account_m(account_month) values(sysdate)";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			row = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
		
	}
	
	public int insertYear() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		String query = "insert into bc_account_y(account_year) values(sysdate)";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			row = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
		
	}
	
	
	
	public int StardDay() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		String query = "insert into bc_account(account_day,order_fee, repair_fee, tuning_fee, maintain_fee, cash_sales, card_sales, account_total) values(sysdate,0,0,0,0,0,0,0)";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			row = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
		
	}
	
	
	public int Startmoney(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		String sql = "select * from bc_start where startdate = sysdate";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				row = rs.getInt("total");
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return row;
	}
	
	public AccountVO totalsearch() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		AccountVO vo = new AccountVO();
		String sql = "select * from bc_account where to_char(account_day,'yymmdd') = to_char(sysdate,'yymmdd')";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo.setOrder_fee(rs.getInt("order_fee"));
				vo.setRepair_fee(rs.getInt("repair_fee"));
				vo.setTuning_fee(rs.getInt("tuning_fee"));
				vo.setMaintain_fee(rs.getInt("maintain_fee"));
				vo.setCash_sales(rs.getInt("cash_sales"));
				vo.setCard_sales(rs.getInt("card_sales"));
		
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	public AccountVO totalsearch(String date) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		AccountVO vo = new AccountVO();
		String sql = "select * from bc_account where to_char(account_day,'yymmdd') = "+date;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo.setOrder_fee(rs.getInt("order_fee"));
				vo.setRepair_fee(rs.getInt("repair_fee"));
				vo.setTuning_fee(rs.getInt("tuning_fee"));
				vo.setMaintain_fee(rs.getInt("maintain_fee"));
				vo.setCash_sales(rs.getInt("cash_sales"));
				vo.setCard_sales(rs.getInt("card_sales"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	public List<AccountVO> totalmsearch() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		List<AccountVO> list = new ArrayList<AccountVO>();
		String sql = "select (cash_sales+card_sales),(order_fee+repair_fee+tuning_fee+maintain_fee) from bc_account_m ";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AccountVO vo = new AccountVO();
				vo.setM_plus(rs.getInt(1));
				vo.setM_minus(rs.getInt(2));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<AccountVO> totalmsearch(String month) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		List<AccountVO> list = new ArrayList<AccountVO>();
		String sql = "select (cash_sales + card_sales), (order_fee + repair_fee + tuning_fee + maintain_fee) from bc_account_m where to_char(account_month,'yyyy/mm') = '"+month+"' order by to_char(account_month,'yyyymmdd') desc";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AccountVO vo = new AccountVO();
				vo.setM_plus(rs.getInt(1));
				vo.setM_minus(rs.getInt(2));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<AccountVO> totalysearch() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		List<AccountVO> list = new ArrayList<AccountVO>();
		String sql = "select (cash_sales + card_sales)- (order_fee + repair_fee + tuning_fee + maintain_fee) from bc_account_y order by to_char(account_year,'yyyymm') desc";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AccountVO vo = new AccountVO();
				vo.setY_plus(rs.getInt(1));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<AccountVO> totalysearch(String year) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		List<AccountVO> list = new ArrayList<AccountVO>();
		String sql = "select (cash_sales + card_sales)- (order_fee + repair_fee + tuning_fee + maintain_fee) from bc_account_y where to_char(account_year,'yyyy') = '"+year+"' order by to_char(account_year,'yyyymm') desc";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AccountVO vo = new AccountVO();
				vo.setY_plus(rs.getInt(1));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
