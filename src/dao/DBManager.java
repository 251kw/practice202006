package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.UserDTO;

public class DBManager extends SnsDAO {
	//ログインIDとパスワードを受け取り、登録ユーザー一覧に一致したものがあるか検索
	public UserDTO getLoginUser(String loginId , String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM users WHERE loginId=? AND password=?";

	UserDTO user = null;

	try {
		//データベース接続情報取得
		conn = getConnection();

		//SERECT文の登録と実行
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, loginId);
		pstmt.setString(2, password);
		rset = pstmt.executeQuery();

		// 検索結果があれば
					if(rset.next()) {
						//必要な列から値を取り出し、ユーザ情報オブジェクトの生成
						user = new UserDTO();
						user.setLoginId(rset.getString(2));
						user.setPassword(rset.getString(3));
						user.setUserName(rset.getString(4));
						user.setIcon(rset.getString(5));
						user.setProfile(rset.getString(6));
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}finally {
					// データベース切断処理
					close(rset);
					close(pstmt);
					close(conn);
				}

				return user;
	}
}
