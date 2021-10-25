package Model.Notic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Utility.DBManager;

public class noticeDAO {
	private noticeDAO() {
	}

	private static noticeDAO instance = new noticeDAO();

	public static noticeDAO getInstance() {
		return instance;
	}

	// 전체 자료실 카운트 메소드(검색없음)
	public int noticeCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		String query = "select count(*) from pr_gongji";
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

	// 전체 자료실 게시글 목록(검색없음, 페이지 없음)
	public List<noticeVO> noticeList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// int row=0;
		List<noticeVO> list = new ArrayList();

		String query = "select * from pr_gongji order by idx desc";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				noticeVO vo = new noticeVO();
				vo.setGubun(rs.getString("gubun"));
				vo.setJuyodo(rs.getString("juyodo"));
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setSubject(rs.getString("subject"));
				vo.setContents(rs.getString("contents"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setReadcnt(rs.getInt("readcnt"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	// 자료실 등록 메소드
	public int noticeWrite(noticeVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		String query = "insert into pr_gongji(idx,userid,gubun,juyodo,name,pass,subject,contents,filename) \r\n"
				+ "values(pr_gongji_seq_idx.nextval,?,?,?,?,?,?,?,?)";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, vo.getGubun());
			pstmt.setString(3, vo.getJuyodo());
			pstmt.setString(4, vo.getName());
			pstmt.setString(5, vo.getPass());
			pstmt.setString(6, vo.getSubject());
			pstmt.setString(7, vo.getContents());
			pstmt.setString(8, vo.getFilename());
			row = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}

	// 전체 게시글 카운트(검색기능 추가)
	public int noticeCount(String s_query) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		String sql = "select count(*) from pr_gongji where " + s_query;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
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

	// 전체 자료실 게시글 목록(검색없음, 페이지 O)
	public List<noticeVO> noticeList(int startpage, int endpage) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<noticeVO> list = new ArrayList();
		String query = "select X.* from( select rownum as rum, A.* from \r\n"
				+ "(select * from pr_gongji order by idx desc ) A \r\n" + "where rownum <= ? ) X where X.rum >= ?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, endpage);
			pstmt.setInt(2, startpage);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				noticeVO vo = new noticeVO();
				vo.setGubun(rs.getString("gubun"));
				vo.setJuyodo(rs.getString("juyodo"));
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setSubject(rs.getString("subject"));
				vo.setContents(rs.getString("contents"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setReadcnt(rs.getInt("readcnt"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	// 전체 자료실 게시글 목록(검색O, 페이지 O)
	public List<noticeVO> noticeList(String s_query, int startpage, int endpage) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<noticeVO> list = new ArrayList();
		String query = "select X.* from( select rownum as rum, A.* from \r\n"
				+ "(select * from pr_gongji order by idx desc ) A \r\n" + "where " + s_query
				+ " and rownum <= ? ) X where \r\n" + s_query + " and X.rum >= ?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, endpage);
			pstmt.setInt(2, startpage);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				noticeVO vo = new noticeVO();
				vo.setGubun(rs.getString("gubun"));
				vo.setJuyodo(rs.getString("juyodo"));
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setSubject(rs.getString("subject"));
				vo.setContents(rs.getString("contents"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setReadcnt(rs.getInt("readcnt"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	public int noticeHits(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		String query1 = "update pr_gongji set readcnt=readcnt+1 where idx=?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query1);
			pstmt.setInt(1, idx);
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
	}

	public noticeVO noticeview(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from pr_gongji where idx=?";
		noticeVO vo = new noticeVO();
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			rs.next();
			vo.setGubun(rs.getString("gubun"));
			vo.setJuyodo(rs.getString("juyodo"));
			vo.setIdx(rs.getInt("idx"));
			vo.setName(rs.getString("name"));
			vo.setSubject(rs.getString("subject"));
			vo.setContents(rs.getString("contents"));
			vo.setRegdate(rs.getString("regdate"));
			vo.setReadcnt(rs.getInt("readcnt"));
			vo.setFilename(rs.getString("filename"));
		} catch (Exception z) {
			z.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return vo;
	}

	// 삭제
	public int noticeDelete(noticeVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		String sql = "delete from pr_gongji where idx=? and userid=? and pass=?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareCall(sql);
			pstmt.setInt(1, vo.getIdx());
			pstmt.setString(2, vo.getUserid());
			pstmt.setString(3, vo.getPass());
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}

	public int noticeModify(noticeVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		String sql = "update pr_gongji set gubun=?,juyodo=?,name=?,subject=?,contents=?, \r\n"
				+ "filename=? where idx=? and userid=? and pass=?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getGubun());
			pstmt.setString(2, vo.getJuyodo());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getSubject());
			pstmt.setString(5, vo.getContents());
			pstmt.setString(6, vo.getFilename());
			pstmt.setInt(7, vo.getIdx());
			pstmt.setString(8, vo.getUserid());
			pstmt.setString(9, vo.getPass());
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}
}
