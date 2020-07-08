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
		PreparedStatement kpstmt = null;

		String fkeyoff = "SET FOREIGN_KEY_CHECKS =0";
		String fkeyon = "SET FOREIGN_KEY_CHECKS =1";


		String sql = "UPDATE users SET loginId=?, password=?, userName=?, icon=?, profile=? WHERE loginId=?";

		try {
			//データベース接続情報取得
			conn = getConnection();

			kpstmt = conn.prepareStatement(fkeyoff);	//外部キー無効化
			kpstmt.executeUpdate();

			//SELECT文の登録と実行
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, upuser.getLoginId());
			pstmt.setString(2, upuser.getPassword());
			pstmt.setString(3, upuser.getUserName());
			pstmt.setString(4, upuser.getIcon());
			pstmt.setString(5, upuser.getProfile());
			pstmt.setString(6, olduserId);
			pstmt.executeUpdate();

			kpstmt = conn.prepareStatement(fkeyon);		//外部キー有効化
			kpstmt.executeUpdate();


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
		PreparedStatement kpstmt = null;

		String fkeyoff = "SET FOREIGN_KEY_CHECKS =0";
		String fkeyon = "SET FOREIGN_KEY_CHECKS =1";

		String sql = "UPDATE shouts SET loginId=?, userName=?, icon=? WHERE loginId=?";

		try {
			//データベース接続情報取得
			conn = getConnection();

			kpstmt = conn.prepareStatement(fkeyoff);  //外部キー無効化
			kpstmt.executeUpdate();

			//SELECT文の登録と実行
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, upuser.getLoginId());
			pstmt.setString(2, upuser.getUserName());
			pstmt.setString(3, upuser.getIcon());
			pstmt.setString(4, olduserId);
			pstmt.executeUpdate();

			kpstmt = conn.prepareStatement(fkeyon);  //外部キー有効化
			kpstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//データベース切断処理
			close(pstmt);
			close(conn);
		}

	}


}
