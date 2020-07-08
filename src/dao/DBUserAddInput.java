package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DBUserAddInput extends SnsDAO {

	/**
	 *
	 * @param loginId
	 * @return
	 * ログインIdの重複チェックを行うメソッド
	 */
	public boolean UserCheck(String loginId)  {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		boolean result = false;
		try {
			conn = getConnection();
			// sql文countで重複チェック
			String sql = "SELECT COUNT(*) FROM sns.users WHERE loginId=?";
			pstmt = conn.clientPrepareStatement(sql);
			pstmt.setString(1, loginId);

			ResultSet rset = pstmt.executeQuery();
			if(rset.next()) {
				count = rset.getInt(1);
			}
			if(count == 1) {
				result = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}
	/**
	 *
	 * @param loginId
	 * @param password
	 * @param userName
	 * @param icon
	 * @param profile
	 * @return
	 * 新規ユーザーのインサートを行うメソッド
	 */
	public boolean setNewUser(String loginId, String password, String userName, String icon ,String profile) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			conn = getConnection();

			// INSERT文
			String sql = "INSERT INTO sns.users(loginId,password,userName,icon ,profile) VALUES(?,?,?,?,?)";
			pstmt = conn.clientPrepareStatement(sql);
			pstmt.setString(1, loginId);
			pstmt.setString(2, password);
			pstmt.setString(3, userName);
			pstmt.setString(4, icon);
			pstmt.setString(5, profile);

			int cnt = pstmt.executeUpdate();
			if(cnt == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(conn);
		}

		return result;
	}

}
