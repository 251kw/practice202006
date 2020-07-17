package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
	 * 叫び更新メソッド
	 * 叫び内容と日付更新
	 *
	 * @param shoutId
	 * @param writing 	書き込み内容
	 */
	public void shoutUpDate(int shoutId, String writing) {
		Connection conn = null; //データベース接続情報
		PreparedStatement pstmt = null; //SQL管理情報

		String sql = "UPDATE shouts SET writing=?, date=? WHERE shoutsId=?";

		try {
			//データベース接続情報取得
			conn = getConnection();

			//SELECT文の登録と実行
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writing);
			Calendar calender = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pstmt.setString(2, sdf.format(calender.getTime()));
			pstmt.setInt(3, shoutId);
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
