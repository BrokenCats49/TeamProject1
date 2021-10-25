package Model.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Utility.DBManager;

public class memberDAO {
	private memberDAO() {
	}

	private static memberDAO instance = new memberDAO();

	public static memberDAO getInstance() {
		return instance;
	}

	public String id_search2(String name, String email) { // 이름,이메일로 찾기
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null; // 보안적으로좋다
		String userid = null; // 찾을아이디

		String sql = "select userid from pr_member where name=? and email=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql); // 쿼리
			pstmt.setString(1, name); // 첫번째 ?를 스트링 id로 넣음
			pstmt.setString(2, email); // 두번째 ?에 스트링 pw 넣음

			rs = pstmt.executeQuery(); // 쿼리를 실행해서 결과값을 rs로 저장
			while (rs.next()) { // rs가 끝날때까지 반복
				userid = rs.getString("userid"); // cnt를 디비에서 가져온 cnt에 저장
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBManager.close(conn, pstmt);
		}
		return userid;
	}

	public String email_search(String userid, String email) { // 이름,이메일로 찾기
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null; // 보안적으로좋다
		String email2 = null; // 찾을아이디

		String sql = "select email from pr_member where userid=? and email=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql); // 쿼리
			pstmt.setString(1, userid); // 첫번째 ?를 스트링 id로 넣음
			pstmt.setString(2, email); // 두번째 ?에 스트링 pw 넣음

			rs = pstmt.executeQuery(); // 쿼리를 실행해서 결과값을 rs로 저장
			while (rs.next()) { // rs가 끝날때까지 반복
				email2 = rs.getString("email"); // cnt를 디비에서 가져온 cnt에 저장
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBManager.close(conn, pstmt);
		}
		return email2;
	}

	// 회원가입 등록
	public int memberWR(memberVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int row = 0;
		String sql = "insert into pr_member(name,userid,passwd,cardnum,email) values(?,?,?,?,?)";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getUserid());
			pstmt.setString(3, vo.getPasswd());
			if(vo.getCardnum()==null) {
				pstmt.setString(4, "");
			}else {
				pstmt.setString(4, vo.getCardnum());
			}
			pstmt.setString(5, vo.getEmail());
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}

	// list 목록 확인
	public List<memberVO> memberList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<memberVO> mList = new ArrayList();
		String sql = "select * from pr_member";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				memberVO vo = new memberVO();
				vo.setUserid(rs.getString("userid"));
				vo.setName(rs.getString("name"));
				vo.setFirst_time(rs.getString("first_time"));
				vo.setLast_time(rs.getString("Last_time"));
				mList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return mList;
	}

	// 아이디 체크
	public int UseridCheck(String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		String sql = "select userid from pr_member where userid=?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				row = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
	}

	// 멤버 로그인
	public int Userlogin(String userid, String passwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select passwd from pr_member where userid= ?";
		int row = 0;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbpass = rs.getString("passwd");
				if (dbpass.equals(passwd)) {
					sql = "update pr_member set last_time=sysdate where userid= ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, userid);
					pstmt.executeUpdate();
					row = 1;
				} else {
					row = 0;
				}
			} else {
				row = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
	}

	// 특정 ID 검색(상세정보보기, 수정)
	public memberVO memberSelect(String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		memberVO vo = new memberVO();
		try {
			conn = DBManager.getConnection();
			query = "select * from pr_member where userid = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo.setUserid(rs.getString("userid"));
				vo.setName(rs.getString("name"));
				vo.setCardnum(rs.getString("cardnum"));
				vo.setPasswd(rs.getString("passwd"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return vo;
	}
	//잔액 확인
	public memberVO cashCheck(String cardnum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		memberVO vo = new memberVO();
		try {
			conn = DBManager.getConnection();
			query = "select * from pr_card where cardnum = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cardnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo.setCardnum(rs.getString(1));
				vo.setChargeday(rs.getString(2));
				vo.setCharge(rs.getInt(3));
				vo.setCash(rs.getInt(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return vo;
	}

	// 특정 ID 검색(session)
	public memberVO memberSession(String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		memberVO vo = new memberVO();
		String sql = "select * from pr_member where userid=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo.setUserid(rs.getString("userid"));
				vo.setName(rs.getString("name"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setPasswd(rs.getString("cardnum"));
				vo.setFirst_time(rs.getString("first_time"));
				vo.setLast_time(rs.getString("Last_time"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return vo;
	}

	// 회원정보 수정
	public int memberUpdate(String userid, memberVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		int row = 0;
		try {
			conn = DBManager.getConnection();
			query = "update pr_member set email = ?, passwd = ?, cardnum = ? ";
			query = query + " where userid = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getPasswd());
			pstmt.setString(3, vo.getCardnum());
			pstmt.setString(4, userid);
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
	}
	//비밀번호 찾을때 임시비밀번호로 바꿔놓기
	public int passUpdate(memberVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		int row = 0;
		try {
			conn = DBManager.getConnection();
			query = "update pr_member set passwd = ?";
			query = query + " where userid = ? and email = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getPasswd());
			pstmt.setString(2, vo.getUserid());
			pstmt.setString(3, vo.getEmail());
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
	}
	// 탈퇴
	public int Delete(memberVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		String sql = "delete from pr_member where userid=? and passwd=?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareCall(sql);
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, vo.getPasswd());
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}
	public int cash(String cardnum , int cash) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select cardnum from pr_card where cardnum= ?";
		int row = 0;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cardnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbpass = rs.getString("cardnum");
				if (dbpass.equals(cardnum)) {
					sql = "update pr_card set chargeday=sysdate, cash=cash+"+cash+", charge=charge+"+cash+"where cardnum=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, cardnum);
					pstmt.executeUpdate();
					row = 1;
				} else {
					row = 0;
				}
			} else {
				
				row = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
	}
}
