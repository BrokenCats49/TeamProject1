package Model.Admin.Card;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Utility.DBManager;

public class CardDAO {
	private CardDAO() {}
	private static CardDAO instance = new CardDAO();
	public static CardDAO getInstance() {
		return instance;
	}
	
	// count
	public int card_count() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		String query = "select count(*) from BC_CARD";
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
	
	public int card_count(String s_query) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int row = 0;
		String query = "select count(*) as counter from BC_CARD";
		
		try {
			conn = DBManager.getConnection();
			if (s_query.equals("")) {
				query = "";
			} else {
				query = "select count(*) as counter from BC_CARD where " + s_query;
			}
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next())
				row = rs.getInt("counter");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
	}
	
	// 카드데이터 리스트 (1)
	public List<CardVO> card_list() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select C.card_code, now_fee, user_code " + 
							"from BC_CARD C, BC_MEMBER M " + 
								"where C.card_code =  M.card_code";
		List<CardVO> list = new ArrayList();
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CardVO vo = new CardVO();
				vo.setCard_code(rs.getString("card_code"));
				vo.setNow_fee(rs.getInt("now_fee"));
				vo.setUser_code(rs.getString(3));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	// 카드데이터 리스트 (2)
	public List<CardVO> card_list(String s_query) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select C.card_code, now_fee, user_code " + 
					   		"from BC_CARD C, BC_MEMBER M " + 
					   			"where C.card_code =  M.card_code and " + s_query;
		List<CardVO> list = new ArrayList();
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CardVO vo = new CardVO();
				vo.setCard_code(rs.getString("card_code"));
				vo.setNow_fee(rs.getInt("now_fee"));
				vo.setUser_code(rs.getString(3));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	// count
	public int card_record_count() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		String query = "select count(*) from BC_CARD_RECORD";
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
	
	public int card_record_count(String s_query) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int row = 0;
		String query = "select count(*) as counter from BC_CARD_RECORD";
		
		try {
			conn = DBManager.getConnection();
			if (s_query.equals("")) {
				query = "";
			} else {
				query = "select count(*) as counter from BC_CARD_RECORD where " + s_query;
			}
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next())
				row = rs.getInt("counter");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
	}

	// 카드충전이력 리스트 (1)
	public List<CardVO> card_record_list() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select * from BC_CARD_RECORD";
		List<CardVO> list = new ArrayList();
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CardVO vo = new CardVO();
				vo.setCard_code(rs.getString("card_code"));
				vo.setCharge_day(rs.getString("charge_day"));
				vo.setCharge(rs.getInt("charge"));
				vo.setCash(rs.getInt("cash"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	// 카드충전이력 리스트 (2)
	public List<CardVO> card_record_list(String s_query) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select * from BC_CARD_RECORD where " + s_query;
		List<CardVO> list = new ArrayList();
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CardVO vo = new CardVO();
				vo.setCard_code(rs.getString("card_code"));
				vo.setCharge_day(rs.getString("charge_day"));
				vo.setCharge(rs.getInt("charge"));
				vo.setCash(rs.getInt("cash"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
}
