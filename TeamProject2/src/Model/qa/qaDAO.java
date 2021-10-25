package Model.qa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import Utility.DBManager;

public class qaDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static qaDAO instance;

	private qaDAO() {
	}

	public static qaDAO getInstance() {
		if (instance == null)
			instance = new qaDAO();
		return instance;
	}

	// 시퀀스를 가져온다.
	public int getSeq() {
		int result = 1;

		try {
			conn = DBManager.getConnection();

			// 시퀀스 값을 가져온다. (DUAL : 시퀀스 값을 가져오기위한 임시 테이블)
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT pr_question_seq_idx.NEXTVAL FROM DUAL");

			pstmt = conn.prepareStatement(sql.toString());
			// 쿼리 실행
			rs = pstmt.executeQuery();

			if (rs.next())
				result = rs.getInt(1);

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

		close();
		return result;
	} // end getSeq

	// 글 삽입
	public boolean qaInsert(qaVO board) {
		boolean result = false;

		try {
			conn = DBManager.getConnection();

			// 자동 커밋을 false로 한다.
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO pr_question");
			sql.append("(idx, userid, kinds, pass, subject, contents, filename");
			sql.append(", repnum, readcnt, regdate, qaparent)");
			sql.append(" VALUES(?,?,?,?,?,?,?,?,?,sysdate,?)");

			int num = board.getIdx(); // 글번호(시퀀스 값)
			int ref = board.getRepnum(); // 그룹번호
			int parent = board.getQaparent(); // 부모글번호

			// 부모글일 경우 그룹번호와 글번호 동일
			if (parent == 0)
				ref = num;

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getUserid());
			pstmt.setString(3, board.getKinds());
			pstmt.setString(4, board.getPass());
			pstmt.setString(5, board.getSubject());
			pstmt.setString(6, board.getContents());
			pstmt.setString(7, board.getFilename());
			pstmt.setInt(8, ref);
			pstmt.setInt(9, board.getReadcnt());
			pstmt.setInt(10, parent);

			int flag = pstmt.executeUpdate();
			if (flag > 0) {
				result = true;
				conn.commit(); // 완료시 커밋
			}

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

		close();
		return result;
	} // end boardInsert();

	// 글목록 가져오기
	public ArrayList<qaVO> getQaList(HashMap<String, Object> listOpt) {
		ArrayList<qaVO> list = new ArrayList<qaVO>();

		String opt = (String) listOpt.get("opt");
		String condition = (String) listOpt.get("condition");
		int start = (Integer) listOpt.get("start");

		try {
			conn = DBManager.getConnection();
			StringBuffer sql = new StringBuffer();

			// 글목록 전체를 보여줄 때
			if (opt == null) {
				sql.append("SELECT * FROM");
				sql.append(" (SELECT  ROWNUM AS rnum, data.* FROM ");
				sql.append("    (SELECT LEVEL, kinds, idx, userid,  subject,");
				sql.append("            contents, filename,readcnt,");
				sql.append("            repnum, qaparent, regdate");
				sql.append("    FROM pr_question");
				sql.append("    START WITH qaparent = 0");
				sql.append("    CONNECT BY PRIOR idx = qaparent");
				sql.append("    ORDER SIBLINGS BY repnum desc)");
				sql.append(" data) ");
				sql.append("WHERE rnum>=? and rnum<=?");

				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, start);
				pstmt.setInt(2, start + 9);

				// StringBuffer를 비운다.
				sql.delete(0, sql.toString().length());
			} else if (opt.equals("0")) // 제목으로 검색
			{
				sql.append("SELECT * FROM");
				sql.append(" (SELECT  ROWNUM AS rnum, data.* FROM ");
				sql.append("    (SELECT LEVEL, kinds, idx, userid,  subject,");
				sql.append("            contents, filename, readcnt,");
				sql.append("            repnum, qaparent, regdate");
				sql.append("    FROM pr_question");
				sql.append("     WHERE subject like ?");
				sql.append("    START WITH qaparent = 0");
				sql.append("    CONNECT BY PRIOR idx = qaparent");
				sql.append("    ORDER SIBLINGS BY repnum desc)");
				sql.append(" data) ");
				sql.append("WHERE rnum>=? and rnum<=?");

				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, "%" + condition + "%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, start + 9);

				sql.delete(0, sql.toString().length());
			} else if (opt.equals("1")) // 내용으로 검색
			{
				sql.append("SELECT * FROM");
				sql.append(" (SELECT  ROWNUM AS rnum, data.* FROM ");
				sql.append("    (SELECT LEVEL, kinds, idx, userid,  subject,");
				sql.append("            contents, filename,readcnt,");
				sql.append("            repnum, qaparent, regdate");
				sql.append("    FROM pr_question");
				sql.append("     WHERE contents like ?");
				sql.append("    START WITH qaparent = 0");
				sql.append("    CONNECT BY PRIOR idx = qaparent");
				sql.append("    ORDER SIBLINGS BY repnum desc)");
				sql.append(" data) ");
				sql.append("WHERE rnum>=? and rnum<=?");

				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, "%" + condition + "%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, start + 9);

				sql.delete(0, sql.toString().length());
			} else if (opt.equals("2")) // 제목+내용으로 검색
			{
				sql.append("SELECT * FROM");
				sql.append(" (SELECT  ROWNUM AS rnum, data.* FROM ");
				sql.append("    (SELECT LEVEL, kinds, idx, userid,  subject,");
				sql.append("            contents, filename,readcnt,");
				sql.append("            repnum, qaparent, regdate");
				sql.append("    FROM pr_question");
				sql.append("     WHERE subject like ?");
				sql.append("     OR contents like ?");
				sql.append("    START WITH qaparent = 0");
				sql.append("    CONNECT BY PRIOR idx = qaparent");
				sql.append("    ORDER SIBLINGS BY repnum desc)");
				sql.append(" data) ");
				sql.append("WHERE rnum>=? and rnum<=?");

				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, "%" + condition + "%");
				pstmt.setString(2, "%" + condition + "%");
				pstmt.setInt(3, start);
				pstmt.setInt(4, start + 9);

				sql.delete(0, sql.toString().length());
			} else if (opt.equals("3")) // 글쓴이로 검색
			{
				sql.append("SELECT * FROM");
				sql.append(" (SELECT  ROWNUM AS rnum, data.* FROM ");
				sql.append("    (SELECT LEVEL, kinds, idx, userid, subject,");
				sql.append("            contents, filename,readcnt,");
				sql.append("            repnum, qaparent, regdate");
				sql.append("    FROM pr_question");
				sql.append("     WHERE userid like ?");
				sql.append("    START WITH qaparent = 0");
				sql.append("    CONNECT BY PRIOR idx = qaparent");
				sql.append("    ORDER SIBLINGS BY repnum desc)");
				sql.append(" data) ");
				sql.append("WHERE rnum>=? and rnum<=?");

				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, "%" + condition + "%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, start + 9);

				sql.delete(0, sql.toString().length());
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				qaVO board = new qaVO();
				board.setRelev(rs.getInt("LEVEL"));
				board.setIdx(rs.getInt("idx"));
				board.setKinds(rs.getString("kinds"));
				board.setUserid(rs.getString("userid"));
				board.setSubject(rs.getString("subject"));
				board.setContents(rs.getString("contents"));
				board.setFilename(rs.getString("filename"));
				board.setReadcnt(rs.getInt("readcnt"));
				board.setRepnum(rs.getInt("repnum"));
				board.setQaparent(rs.getInt("qaparent"));
				board.setRegdate(rs.getString("regdate"));
				list.add(board);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

		close();
		return list;
	} // end getBoardList

	// 글의 개수를 가져오는 메서드
	public int getQaListCount(HashMap<String, Object> listOpt) {
		int result = 0;
		String opt = (String) listOpt.get("opt");
		String condition = (String) listOpt.get("condition");

		try {
			conn = DBManager.getConnection();
			StringBuffer sql = new StringBuffer();

			if (opt == null) // 전체글의 개수
			{
				sql.append("select count(*) from pr_question");
				pstmt = conn.prepareStatement(sql.toString());

				// StringBuffer를 비운다.
				sql.delete(0, sql.toString().length());
			} else if (opt.equals("0")) // 제목으로 검색한 글의 개수
			{
				sql.append("select count(*) from pr_question where subject like ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%' + condition + '%');

				sql.delete(0, sql.toString().length());
			} else if (opt.equals("1")) // 내용으로 검색한 글의 개수
			{
				sql.append("select count(*) from pr_question where contents like ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%' + condition + '%');

				sql.delete(0, sql.toString().length());
			} else if (opt.equals("2")) // 제목+내용으로 검색한 글의 개수
			{
				sql.append("select count(*) from pr_question ");
				sql.append("where subject like ? or contents like ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%' + condition + '%');
				pstmt.setString(2, '%' + condition + '%');

				sql.delete(0, sql.toString().length());
			} else if (opt.equals("3")) // 글쓴이로 검색한 글의 개수
			{
				sql.append("select count(*) from pr_question where userid like ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%' + condition + '%');

				sql.delete(0, sql.toString().length());
			}

			rs = pstmt.executeQuery();
			if (rs.next())
				result = rs.getInt(1);

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

		close();
		return result;
	} // end getBoardListCount

	// 상세보기
	public qaVO getDetail(int boardNum) {
		qaVO board = null;

		try {
			conn = DBManager.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("select * from pr_question where idx = ?");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, boardNum);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				board = new qaVO();
				board.setIdx(boardNum);
				board.setUserid(rs.getString("userid"));
				board.setKinds(rs.getString("kinds"));
				board.setSubject(rs.getString("subject"));
				board.setContents(rs.getString("contents"));
				board.setFilename(rs.getString("filename"));
				board.setReadcnt(rs.getInt("readcnt"));
				board.setRepnum(rs.getInt("repnum"));
				board.setRegdate(rs.getString("regdate"));
				board.setQaparent(rs.getInt("qaparent"));
			}

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

		close();
		return board;
	} // end getDetail()

	// 조회수 증가
	public boolean updateCount(int boardNum) {
		boolean result = false;

		try {
			conn = DBManager.getConnection();

			// 자동 커밋을 false로 한다.
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			sql.append("update pr_question set readcnt = readcnt+1 ");
			sql.append("where idx = ?");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, boardNum);

			int flag = pstmt.executeUpdate();
			if (flag > 0) {
				result = true;
				conn.commit(); // 완료시 커밋
			}
		} catch (Exception e) {
			try {
				conn.rollback(); // 오류시 롤백
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			throw new RuntimeException(e.getMessage());
		}

		close();
		return result;
	} // end updateCount
		// 삭제할 파일명을 가져온다.

	public String getFileName(int boardNum) {
		String fileName = null;

		try {
			conn = DBManager.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT filename from pr_question where idx=?");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, boardNum);

			rs = pstmt.executeQuery();
			if (rs.next())
				fileName = rs.getString("filename");

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

		close();
		return fileName;
	} // end getFileName

	// 게시글 삭제
	public boolean deleteBoard(int boardNum, String pass) {
		boolean result = false;

		try {
			conn = DBManager.getConnection();
			conn.setAutoCommit(false); // 자동 커밋을 false로 한다.

			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM pr_question");
			sql.append(" WHERE idx IN");
			sql.append(" (SELECT idx");
			sql.append(" FROM pr_question");
			sql.append(" START WITH idx = ?");
			sql.append(" CONNECT BY PRIOR idx = qaparent) and pass=? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, boardNum);
			pstmt.setString(2, pass);
			int flag = pstmt.executeUpdate();
			if (flag > 0) {
				result = true;
				conn.commit(); // 완료시 커밋
			}

		} catch (Exception e) {
			try {
				conn.rollback(); // 오류시 롤백
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			throw new RuntimeException(e.getMessage());
		}

		close();
		return result;
	} // end deleteBoard
		// 글 수정

	public boolean updateBoard(qaVO border) 
    {
        boolean result = false;
        
        try{
            conn = DBManager.getConnection();
            conn.setAutoCommit(false); // 자동 커밋을 false로 한다.
            
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE pr_question SET");
            sql.append(" subject=?");
            sql.append(" ,contents=?");
            sql.append(" ,filename=?");
            sql.append(" ,regdate=SYSDATE ");
            sql.append("WHERE idx=? and pass=?");
 
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, border.getSubject());
            pstmt.setString(2, border.getContents());
            pstmt.setString(3, border.getFilename());
            pstmt.setInt(4, border.getIdx());
            pstmt.setString(5, border.getPass());
            
            int flag = pstmt.executeUpdate();
            if(flag > 0){
                result = true;
                conn.commit(); // 완료시 커밋
            }
            
        } catch (Exception e) {
            try {
                conn.rollback(); // 오류시 롤백
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            throw new RuntimeException(e.getMessage());
        }
    
        close();
        return result;
    } // end updateBoard

	private void close() {
		try {
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	} // end close()

}
