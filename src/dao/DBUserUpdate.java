package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.UserDTO;

/**
 * データベース上の情報を
 * 更新するクラス
 *
 * userUpDate
 * shoutsUpDate
 *
 * @author y.sato
 *
 */
public class DBUserUpdate extends SnsDAO{
	/**
	 * ユーザー情報を更新するメソッド
	 *
	 * @param olduserId 更新する前のid
	 * @param upuser 更新情報
	 *
	 */
	public void userUpDate(String olduserId, UserDTO upuser) {
		Connection conn = null; //データベース接続情報
		PreparedStatement pstmt = null; //SQL管理情報

		String sql = "UPDATE users SET password=?, userName=?, icon=?, profile=? WHERE loginId=?";

		try {
			//データベース接続情報取得
			conn = getConnection();

			//SELECT文の登録と実行
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, upuser.getPassword());
			pstmt.setString(2, upuser.getUserName());
			pstmt.setString(3, upuser.getIcon());
			pstmt.setString(4, upuser.getProfile());
			pstmt.setString(5, olduserId);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//データベース切断処理
			close(pstmt);
			close(conn);
		}

	}

	/**
	 * shouts情報を更新するメソッド
	 *
	 * @param olduserId 更新する前のid
	 * @param upuser 更新情報
	 *
	 */
	public void shoutsUpDate(String olduserId, UserDTO upuser) {
		Connection conn = null; //データベース接続情報
		PreparedStatement pstmt = null; //SQL管理情報

		String sql = "UPDATE shouts SET userName=?, icon=? WHERE loginId=?";

		try {
			//データベース接続情報取得
			conn = getConnection();

			//SELECT文の登録と実行
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, upuser.getUserName());
			pstmt.setString(2, upuser.getIcon());
			pstmt.setString(3, olduserId);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//データベース切断処理
			close(pstmt);
			close(conn);
		}
	}

	public void shoutUpDate(int shoutId, String writing) {
		Connection conn = null; //データベース接続情報
		PreparedStatement pstmt = null; //SQL管理情報

		String sql = "UPDATE shouts SET writing=? WHERE shoutsId=?";

		try {
			//データベース接続情報取得
			conn = getConnection();

			//SELECT文の登録と実行
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writing);
			pstmt.setInt(2, shoutId);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//データベース切断処理
			close(pstmt);
			close(conn);
		}

	}

}
