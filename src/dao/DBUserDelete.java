package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * データベース
 * ユーザー情報削除クラス
 * SnsDAO継承
 *
 * userDelete
 *
 * @author y.sato
 *
 */
public class DBUserDelete extends SnsDAO{
	/**
	 * ユーザー情報削除メソッド
	 * 削除完了で真を返す
	 *
	 * @param loginIds ログインId（複数）
	 * @return result 真偽値
	 *
	 */
	public  boolean usersDelete(String loginId) {
		boolean result = false;
		Connection conn = null; //データベース接続情報
		PreparedStatement pstmt = null; //SQL管理情報
		ResultSet rset = null; //検索結果

		try {
			//データベース接続情報取得
			conn = getConnection();

			//SELECT文の登録と実行
			String sql = "DELETE FROM users WHERE loginId=? LIMIT 1";		//一応ひとつづつ削除
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, loginId);
			pstmt.executeUpdate();

			result = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return result;
	}

	/**
	 * shouts情報削除メソッド
	 * 削除完了で真を返す
	 *
	 * @param loginIds ログインId（複数）
	 * @return result 真偽値
	 *
	 */
	public  boolean shoutsDelete(String loginId) {
		boolean result = false;
		Connection conn = null; //データベース接続情報
		PreparedStatement pstmt = null; //SQL管理情報
		ResultSet rset = null; //検索結果

		try {
			//データベース接続情報取得
			conn = getConnection();

			//SELECT文の登録と実行
			String sql = "DELETE FROM shouts WHERE loginId=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, loginId);
			pstmt.executeUpdate();

			result = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return result;
	}


}
