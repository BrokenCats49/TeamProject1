package Model.Gallery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Utility.DBManager;

public class galleryDAO {
	private galleryDAO() {
	}

	private static galleryDAO instance = new galleryDAO();

	public static galleryDAO getInstance() {
		return instance;
	}

	// 자료실 등록 메소드
	public int galleryWrite(galleryVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		String query = "insert into pr_gallery(idx,name,pass,subject,contents,filename) \r\n"
				+ "values(tbl_pds_seq_idx.nextval,?,?,?,?,?)";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPasswd());
			pstmt.setString(3, vo.getSubject());
			pstmt.setString(4, vo.getContents());
			pstmt.setString(5, vo.getFilename());
			row = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}

	// 전체 자료실 카운트 메소드(검색없음)
	public int galleryCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		String query = "select count(*) from pr_gallery";
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
	public List<galleryVO> galleryList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// int row=0;
		List<galleryVO> list = new ArrayList();

		String query = "select * from pr_gallery order by idx desc";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				galleryVO vo = new galleryVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setSubject(rs.getString("subject"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setReadcnt(rs.getInt("readcnt"));
				vo.setFilename(rs.getString("filename"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	// 전체 게시글 카운트(검색기능 추가)
	public int galleryCount(String s_query) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		String sql = "select count(*) from pr_gallery where " + s_query;
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
	public List<galleryVO> galleryList(int startpage, int endpage) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<galleryVO> list = new ArrayList();
		String query = "select X.* from( select rownum as rum, A.* from \r\n"
				+ "(select * from pr_gallery order by idx desc ) A \r\n" + "where rownum <= ? ) X where X.rum >= ?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, endpage);
			pstmt.setInt(2, startpage);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				galleryVO vo = new galleryVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setSubject(rs.getString("subject"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setReadcnt(rs.getInt("readcnt"));
				vo.setFilename(rs.getString("filename"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	// 전체 자료실 게시글 목록(검색없음, 페이지 O)
	public List<galleryVO> galleryList(String s_query, int startpage, int endpage) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<galleryVO> list = new ArrayList();
		String query = "select X.* from( select rownum as rum, A.* from \r\n"
				+ "(select * from pr_gallery order by idx desc ) A \r\n" + "where " + s_query
				+ " and rownum <= ? ) X where \r\n" + s_query + " and X.rum >= ?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, endpage);
			pstmt.setInt(2, startpage);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				galleryVO vo = new galleryVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setSubject(rs.getString("subject"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setReadcnt(rs.getInt("readcnt"));
				vo.setFilename(rs.getString("filename"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	public int galleryHits(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		String query1 = "update pr_gallery set readcnt=readcnt+1 where idx=?";
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

	public galleryVO galleryView(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from pr_gallery where idx=?";
		galleryVO vo = new galleryVO();
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			rs.next();
			vo.setIdx(rs.getInt("idx"));
			vo.setName(rs.getString("name"));
			vo.setSubject(rs.getString("subject"));
			vo.setContents(rs.getString("contents"));
			vo.setPasswd(rs.getString("pass"));
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
	public int galleryDelete(galleryVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		String sql = "delete from pr_gallery where idx=? and pass=?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareCall(sql);
			pstmt.setInt(1, vo.getIdx());
			pstmt.setString(2, vo.getPasswd());
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}

	public int galleryModify(galleryVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		String sql = "update pr_gallery set name=?,subject=?,contents=?, \r\n" + "filename=? where idx=? and pass=?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getSubject());
			pstmt.setString(3, vo.getContents());
			pstmt.setString(4, vo.getFilename());
			pstmt.setInt(5, vo.getIdx());
			pstmt.setString(6, vo.getPasswd());
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}
}
