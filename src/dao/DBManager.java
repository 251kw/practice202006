package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.mysql.jdbc.Connection;

import dto.ShoutDTO;
import dto.UserDTO;

public class DBManager extends SnsDAO {
	//ログインIDとパスワードを受け取り、登録ユーザー一覧に一致したものがあるか検索
	public UserDTO getLoginUser(String loginId, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM users WHERE loginId=? AND password=?";
		UserDTO user = null; //登録ユーザー情報

		try {
			//データベース接続情報取得
			conn = getConnection();

			//SELECT文の登録と実行
			pstmt = conn.clientPrepareStatement(sql);
			pstmt.setString(1, loginId);
			pstmt.setString(2, password);
			rset = pstmt.executeQuery();

			//検索結果があれば
			if (rset.next()) {
				//必要な値を取り出し
				user = new UserDTO();
				user.setLoginId(rset.getString(2));
				user.setPassword(rset.getString(3));
				user.setUserName(rset.getString(4));
				user.setIcon(rset.getString(5));
				user.setProfile(rset.getString(6));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}
		return user;
	}

	// 書き込み内容リストの getter
	public ArrayList<ShoutDTO> getShoutList() {
		Connection conn = null;
		Statement pstmt = null;
		ResultSet rset = null;

		ArrayList<ShoutDTO> list = new ArrayList<ShoutDTO>();

		try {
			// データベース接続処理
			conn = getConnection();
			pstmt = conn.createStatement();

			//SELECT　分の実行
			String sql = "SELECT * FROM Shouts ORDER BY date DESC";
			rset = pstmt.executeQuery(sql);

			//検索結果の数だけ繰り返す
			while(rset.next()) {
				// 必要な列から値を取り出し、書き込み内容オブジェクトを生成
				ShoutDTO shout = new ShoutDTO();
				shout.setUserName(rset.getString(2));
				shout.setIcon(rset.getString(3));
				shout.setDate(rset.getString(4));
				shout.setWriting(rset.getString(5));

				// 書き込みリストに追加
				list.add(shout);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//データベース切断
			close(rset);
			close(pstmt);
			close(conn);
		}

		return list;

	}
	//ログインユーザー情報
	public boolean setWriting(UserDTO user, String writing) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			conn = getConnection();

			// INSERT文
			String sql = "INSERT INTO shouts(userName,icon,date, writing) VALUES(?,?,?,?)";
			pstmt = conn.clientPrepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getIcon());
			// 日時取得
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			pstmt.setString(3, sdf.format(calendar.getTime()));
			pstmt.setString(4, writing);

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
