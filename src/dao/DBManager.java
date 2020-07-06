package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import dto.ShoutDTO;
import dto.UserDTO;

public class DBManager extends SnsDAO {
	//ログインIDとパスワードを受け取り、登録ユーザー一覧に一致したものがあるか検索
	public UserDTO getLoginUser(String loginId, String password) {
		Connection conn = null; //データベース接続情報
		PreparedStatement pstmt = null; //SQL 管理情報
		ResultSet rset = null; //検索結果

		String sql = "SELECT * FROM users WHERE loginId=? AND password=?";
		UserDTO user = null; //登録ユーザー情報

		try {
			//データベース接続情報取得
			conn = getConnection();

			//SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql); //SELeCT 構文登録
			pstmt.setString(1, loginId);
			pstmt.setString(2, password);
			rset = pstmt.executeQuery();

			//検索結果があれば
			if (rset.next()) {
				//必要な列から値を取り出し、ユーザー情報オブジェクトを生成
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

	//ログインIDを受け取り、登録ユーザー一覧に一致したものがあるか検索
	public UserDTO checkUser(String loginId) {
		Connection conn = null; //データベース接続情報
		PreparedStatement pstmt = null; //SQL 管理情報
		ResultSet rset = null; //検索結果

		String sql = "SELECT * FROM users WHERE loginId=?";
		UserDTO user = null; //登録ユーザー情報

		try {
			//データベース接続情報取得
			conn = getConnection();

			//SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql); //SELeCT 構文登録
			pstmt.setString(1, loginId);
			rset = pstmt.executeQuery();

			//検索結果があれば
			if (rset.next()) {
				//必要な列から値を取り出し、ユーザー情報オブジェクトを生成
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

	public ArrayList<UserDTO> searchUser(String str) {
		Connection conn = null; //データベース接続情報
		PreparedStatement pstmt = null; //SQL 管理情報
		ResultSet rset = null; //検索結果

		ArrayList<UserDTO> list = new ArrayList<UserDTO>();

		String sql = "SELECT * FROM users "+str;

		try {
			//データベース接続情報取得
			conn = getConnection();

			//SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql); //SELeCT 構文登録
			rset = pstmt.executeQuery();

			//検索結果の数だけ繰り返す
			while (rset.next()) {
				//必要なれつから値を取り出し、書き込み内容オブジェクトを生産
				UserDTO user = new UserDTO();
				user.setLoginId(rset.getString(2));
				user.setPassword(rset.getString(3));
				user.setUserName(rset.getString(4));
				user.setIcon(rset.getString(5));
				user.setProfile(rset.getString(6));

				//書き込み内容をリストに追加
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return list;
	}

	//書き込み内容リストのgetter
	public ArrayList<ShoutDTO> getShoutList() {
		Connection conn = null;
		Statement pstmt = null;
		ResultSet rset = null;

		ArrayList<ShoutDTO> list = new ArrayList<ShoutDTO>();

		try {
			//データベース接続処理
			conn = getConnection();
			pstmt = conn.createStatement();

			//SELECT文の実行
			String sql = "SELECT * FROM shouts ORDER BY date DESC";
			rset = pstmt.executeQuery(sql);

			//検索結果の数だけ繰り返す
			while (rset.next()) {
				//必要なれつから値を取り出し、書き込み内容オブジェクトを生産
				ShoutDTO shout = new ShoutDTO();
				shout.setUserName(rset.getString(2));
				shout.setIcon(rset.getString(3));
				String str = rset.getString(4);
				shout.setDate(str.substring(0, str.indexOf('.')));
				shout.setWriting(rset.getString(5));

				//書き込み内容をリストに追加
				list.add(shout);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return list;
	}

	//ログインユーザー情報と書き込み内容を受け取り、リストに追加する
	public boolean setWriting(UserDTO user, String writing) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			conn = getConnection();

			//INSErt文の登録と実行
			String sql = "INSERT INTO shouts(userName, icon, date, writing) VALUES(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getIcon());
			//現在日時の日付の書式指定
			Calendar calender = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			pstmt.setString(3, sdf.format(calender.getTime()));
			pstmt.setString(4, writing);

			int cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				//INSERT文の実行結果が１なら登録成功
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//データベース切断処理
			close(pstmt);
			close(conn);
		}

		return result;
	}

	//情報を受け取り新規登録する
	public boolean setNewUser(String loginId, String password, String userName, String icon, String profile) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			conn = getConnection();

			//INSErt文の登録と実行
			String sql = "INSERT INTO users(loginId, password, userName, icon, profile) VALUES(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);
			pstmt.setString(2, password);
			pstmt.setString(3, userName);
			pstmt.setString(4, "icon-" + icon);
			pstmt.setString(5, profile);

			int cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				//INSERT文の実行結果が１なら登録成功
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//データベース切断処理
			close(pstmt);
			close(conn);
		}

		return result;
	}
}
