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

/**
 * @author t.kurihara
 * データベースに接続し、sql文実行、データ取得などのメソッドを保管
 */
public class DBManager extends SnsDAO {
	/**
	 * ログインID、パスワードに合致した値をとってくる
	 * @param loginId 入力されたログインID
	 * @param password 入力されたパスワード
	 * @return	ユーザー情報
	 */
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

	/**
	 * ログインIDを受け取り、登録ユーザー一覧に一致したものがあるか検索
	 * @param loginId 入力されたログインID
	 * @return 合致したユーザー情報
	 */
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

	/**
	 * select文を作り、検索する機能
	 * @param sql文の条件部分
	 * @return 検索に合致したユーザー情報
	 */
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

	/**
	 * delete文を作り、削除する機能
	 * @param str sql文の条件部分
	 * @return 削除に成功したかどうか
	 */
	public boolean deleteUser(String str) {
		Connection conn = null; //データベース接続情報
		PreparedStatement pstmt = null; //SQL 管理情報
		boolean result = false;


		String sql = "DELETE FROM users WHERE loginId="+str;

		try {
			//データベース接続情報取得
			conn = getConnection();

			//delete 文の登録と実行
			pstmt = conn.prepareStatement(sql); //delete 構文登録


			int cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				//delete文の実行結果が１なら削除成功
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

	/**
	 * update文を作り、更新する機能
	 * @param str1 sql文の条件部分
	 * @param str2 ログインID
	 * @return 更新に成功したかどうか
	 */
	public boolean updateUser(String str1, String str2) {
		Connection conn = null; //データベース接続情報
		PreparedStatement pstmt = null; //SQL 管理情報
		boolean result = false;
		int cnt = 0;


		String sql = "UPDATE users SET "+str1+" WHERE loginId='"+str2+"'";

		try {
			//データベース接続情報取得
			conn = getConnection();

			//update 文の登録と実行
			pstmt = conn.prepareStatement(sql); //update 構文登録


			cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				//update文の実行結果が１なら更新成功
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

	/**
	 * 書き込み内容リストのgetter
	 * @return shoutsテーブルにある情報をまとめたリスト
	 */
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
				shout.setUserName(rset.getString(3));
				shout.setIcon(rset.getString(4));
				String str = rset.getString(5);
				shout.setDate(str.substring(0, str.indexOf('.')));
				shout.setWriting(rset.getString(6));

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

	/**
	 * ログインユーザー情報と書き込み内容を受け取り、リストに追加する
	 * @param user ログインユーザー情報
	 * @param writing 叫びの内容
	 * @return 成功したかどうか
	 */
	public boolean setWriting(UserDTO user, String writing) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			conn = getConnection();

			//INSErt文の登録と実行
			String sql = "INSERT INTO shouts(loginId, userName, icon, date, writing) VALUES(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getLoginId());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getIcon());
			//現在日時の日付の書式指定
			Calendar calender = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			pstmt.setString(4, sdf.format(calender.getTime()));
			pstmt.setString(5, writing);

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

	/**
	 * 情報を受け取り新規登録する
	 * @param loginId 登録したいユーザーのID
	 * @param password 登録したいユーザーのパスワード
	 * @param userName 登録したいユーザーの名前
	 * @param icon 登録したいユーザーのアイコン
	 * @param profile 登録したいユーザーのプロフィール
	 * @return 成功したかどうか
	 */
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
