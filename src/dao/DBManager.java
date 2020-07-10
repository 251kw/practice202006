package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dto.ShoutDTO;
import dto.UserDTO;

/*
 * DBを操作するDAO
 */
public class DBManager extends SnsDAO{

	/**
	 * ログインIDとパスワードを受け取り、登録ユーザ一覧に一致したものがあるか検索
	 * @param loginId	//ログイン時に入力されたログインID
	 * @param password	//ログイン時に入力されたパスワード
	 * @return	user	//戻り値はユーザ情報
	 */
	public UserDTO getLoginUser(String loginId, String password) {
		Connection conn = null;	//データベース接続情報
		PreparedStatement pstmt = null;	//SQL管理情報
		ResultSet rset = null;	//検索結果

		String sql = "SELECT * FROM users WHERE loginId=? AND password=?";
		UserDTO user = null;	//登録ユーザ情報

		try {
			//データベース接続情報取得
			conn = getConnection();

			//SELECT文の登録と実行
			pstmt = conn.prepareStatement(sql);	//SELECT構成登録　データベース接続情報をオブジェクトにして(conn)、ParameterStatement型の変数を受けとる
			//プレースホルダーへの値のセット
			pstmt.setString(1, loginId);
			pstmt.setString(2, password);
			//SQL文の実行
			rset = pstmt.executeQuery();

			//検索結果があれば
			if(rset.next()) {
				//必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user = new UserDTO();
				user.setLoginId(rset.getString(2));
				user.setPassword(rset.getString(3));
				user.setUserName(rset.getString(4));
				user.setIcon(rset.getString(5));
				user.setProfile(rset.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//データベース切断処理　これつないだ時と逆の順番から切っていくんか
			close(rset);
			close(pstmt);
			close(conn);
		}
		return user;
	}

	/**
	 * ユーザID重複チェック
	 * @param loginId	//新規登録時、入力されたユーザID
	 * @return	user	//戻り値は該当するログインIDが入った変数user
	 */
	public UserDTO getCheckUser(String loginId) {
		Connection conn = null;	//データベース接続情報
		PreparedStatement pstmt = null;	//SQL管理情報
		ResultSet rset = null;	//検索結果

		String sql = "SELECT * FROM users WHERE loginId=?";
		UserDTO user = null;	//登録ユーザ情報

		try {
			//データベース接続情報取得
			conn = getConnection();

			//SELECT文の登録と実行
			pstmt = conn.prepareStatement(sql);	//SELECT構成登録　データベース接続情報をオブジェクトにして(conn)、ParameterStatement型の変数を受けとる
			//プレースホルダーへの値のセット
			pstmt.setString(1, loginId);
			//SQL文の実行
			rset = pstmt.executeQuery();

			//検索結果があれば
			if(rset.next()) {
				//必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user = new UserDTO();
				user.setLoginId(rset.getString(2));
				//TODO 7.10に追加
				user.setPassword(rset.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//データベース切断処理　これつないだ時と逆の順番から切っていくんか
			close(rset);
			close(pstmt);
			close(conn);
		}
		return user;
	}

	/**
	 * 書き込み内容リストのゲッター
	 * @return	list	//戻り値は該当情報をshoutDTOでとってきたリスト
	 */
	public ArrayList<ShoutDTO> getShoutList(){
		Connection conn = null;	//データベース接続情報
		Statement pstmt = null;	//SQL管理情報
		ResultSet rset = null;	//検索結果

		ArrayList<ShoutDTO> list = new ArrayList<ShoutDTO>();

		try {
			//データベース接続処理
			conn = getConnection();
			//.createStatement()はパラメータなしのSQL実行に使用する
			pstmt = conn.createStatement();

			//SELECT文の登録と実行　パラメータなしの、SQL文だけ突っ込んでる
			String sql = "SELECT * FROM shouts ORDER BY date DESC";
			rset = pstmt.executeQuery(sql);

			//検索結果の数だけ繰り返す
			while (rset.next()) {
				//必要な列から値を取り出し、書き込み内容オブジェクトを生成
				ShoutDTO shout = new ShoutDTO();
				shout.setUserName(rset.getString(3));
				shout.setIcon(rset.getString(4));
				shout.setDate(rset.getString(5));
				shout.setWriting(rset.getString(6));

				//書き込み内容をリストに追加
				list.add(shout);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}
		return list;
	}

	/**
	 * ログインユーザ情報と書き込み内容リストを受け取り、shoutsテーブルへの追加する
	 * @param user		//UserDTOにセットしたユーザ情報
	 * @param writing	//叫ぶテキストに入力された内容
	 * @return	result	//戻り値はshoutsテーブルへのINSERT文が成功したかどうかのboolean型
	 */
	public boolean setWriting(UserDTO user, String writing) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			//データベースに接続
			conn = getConnection();

			//INSERT文の登録と実行
			String sql = "INSERT INTO shouts(loginId, userName, icon, date, writing) VALUES(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getLoginId());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getIcon());
			//現在日時の取得と日付の書式の指定
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pstmt.setString(4, sdf.format(calendar.getTime()));
			pstmt.setString(5, writing);

			int cnt = pstmt.executeUpdate();
			if(cnt == 1) {
				//INSERT文の実行結果が1なら登録成功
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//データベース切断処理
			close(pstmt);
			close(conn);
		}
		return result;
	}

	/**
	 * 登録ユーザ情報のDB更新
	 * @param loginId	//更新時、新しく入力されたログインID
	 * @param password	//更新時、新しく入力されたパスワード
	 * @param userName	//更新時、新しく入力されたユーザネーム
	 * @param icon		//更新時、新しく入力されたアイコン
	 * @param profile	//更新時、新しく入力されたプロフィール
	 * @param logId		//更新前のログインID
	 * @return	result	//戻り値はUPDATE文が成功したかどうかのboolean型
	 */
	@SuppressWarnings("resource")
	public boolean updateUser(String loginId, String password, String userName, String icon, String profile, String logId) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		//送信情報の取得
		String getLogId = loginId;
		String getPass = password;
		String getUName = userName;
		String getIcon = icon;
		String getProf = profile;
		//変更前の該当ユーザのログインID
		String getLog = null;

		ResultSet rset = null;	//該当ユーザ検索結果
		boolean result = false; //更新結果

		String sqlSel = "SELECT * FROM users WHERE loginId=?";
		UserDTO user = null;	//登録ユーザ情報

		try {
			//データベースに接続
			conn = getConnection();

			//SELECT文の登録と実行
			pstmt = conn.prepareStatement(sqlSel);	//SELECT構成登録
			//プレースホルダーへの値のセット （変更前のログインIDをセット）
			pstmt.setString(1, logId);
			//SQL文の実行
			rset = pstmt.executeQuery();

			if(rset.next()) {
				//必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user = new UserDTO();
				user.setLoginId(rset.getString(2));
				getLog = user.getLoginId();
			}

			//UPDATE文の登録と実行
			String sql = "UPDATE users SET loginId=?, password=?, userName=?, icon=?, profile=? WHERE LoginId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, getLogId);
			pstmt.setString(2, getPass);
			pstmt.setString(3, getUName);
			pstmt.setString(4, getIcon);
			pstmt.setString(5, getProf);
			pstmt.setString(6, getLog);	//変更前のログインID

			int cnt = pstmt.executeUpdate();
			if(cnt == 1) {
				//INSERT文の実行結果が1なら登録成功
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//データベース切断処理
			close(pstmt);
			close(conn);
		}
		return result;
	}

	/**
	 * 登録ユーザのDB削除
	 * @param loginId	//削除するログインID
	 * @return	result	//戻り値はDELETE文が成功したかどうかのboolean型
	 */
	public boolean deleteUser(String loginId) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		//送信情報の取得
		String getLogId = loginId;
		boolean result = false; //削除結果

		try {
			//データベースに接続
			conn = getConnection();

			//shoutsテーブルのDELETE文の登録と実行
			String sqlShouts = "DELETE FROM Shouts WHERE LoginId=?";
			pstmt = conn.prepareStatement(sqlShouts);
			pstmt.setString(1, getLogId);
			int cnt1 = pstmt.executeUpdate();

			//userテーブルのDELETE文の登録と実行
			String sqlUser = "DELETE FROM users WHERE LoginId=?";
			pstmt = conn.prepareStatement(sqlUser);
			pstmt.setString(1, getLogId);
			int cnt2 = pstmt.executeUpdate();

			if(cnt2 == 1) {
				//DELETE文の実行結果が1なら登録成功
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//データベース切断処理
			close(pstmt);
			close(conn);
		}
		return result;
	}

	/**
	 * ユーザの新規登録情報をDBへ追加する
	 * @param loginId	//新規登録するログインID
	 * @param password	//新規登録するパスワード
	 * @param userName	//新規登録する名前
	 * @param icon		//新規登録するアイコン
	 * @param profile	//新規登録するプロフィール
	 * @return	result	//userテーブルのINSERT文が成功したかどうかをboolean型で返す
	 */
	public boolean getEndUser(String loginId, String password, String userName, String icon, String profile) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String getLogId = loginId;
		String getPass = password;
		String getUName = userName;
		String getIcon = icon;
		String getProf = profile;

		boolean result = false;
		try {
			//データベースに接続
			conn = getConnection();

			//INSERT文の登録と実行
			String sql = "INSERT INTO users (loginId, password, userName, icon, profile) VALUES(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, getLogId);
			pstmt.setString(2, getPass);
			pstmt.setString(3, getUName);
			pstmt.setString(4, getIcon);
			pstmt.setString(5, getProf);

			int cnt = pstmt.executeUpdate();
			if(cnt == 1) {
				//INSERT文の実行結果が1なら登録成功
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//データベース切断処理
			close(pstmt);
			close(conn);
		}
		return result;
	}

	/**
	 * ログイン後、検索画面でのユーザ検索
	 * @param loginId	//検索画面で入力されたログインID
	 * @param userName	//検索画面で入力された名前
	 * @param icon		//検索画面で入力されたアイコン
	 * @param profile	//検索画面で入力されたプロフィール
	 * @return	list	//検索したユーザをいれたリスト
	 */
	public ArrayList<UserDTO> getSearchList(String loginId, String userName, String icon, String profile){
		Connection conn = null;	//データベース接続情報
		Statement pstmt = null;	//SQL管理情報
		ResultSet rset = null;	//検索結果

		ArrayList<UserDTO> list = new ArrayList<UserDTO>();

		//検索値に値があるものだけ、sql文に部分一致のSELECT文を追加していく
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM users WHERE");
		if(loginId != "") { sb.append(" loginId LIKE '%" + loginId + "%' AND");};
		if(userName != "") { sb.append(" userName LIKE '%" + userName + "%' AND"); }
		if(icon != null) { sb.append(" icon LIKE '%" + icon + "%' AND"); }	//初期値null
		if(profile != "") { sb.append(" profile LIKE '%" + profile + "%' AND"); }

		//文末に"AND"がついているか調べる
		String regex = "AND$";
		Pattern p = Pattern.compile(regex); //正規表現のコンパイル
		Matcher m = p.matcher(sb);

		//文末の" AND"を消す
		if(m.find()) {
			int len = sb.length(); // sqlのlength(長さ)をint型変数のsizeに格納
			int i = 4;	//(" AND")は4文字
			sb.delete(len - i, len);	//文末から4文字(=" AND")を削除
		}

		// sqlのlength(長さ)をint型変数のsizeに格納
		int len = sb.length();
		int i = 25;	//(SELECT * FROM users WHERE)は25文字

		//検索条件が何もなかった場合は全件検索のSQL(SELECT * FROM users)にする
		if(len == i) {
			//文末の" WHERE"を消す
			int a = 6;	//(" WHERE")は6文字
			sb.delete(len - a, len);	//文末から6文字(=" WHERE")を削除
		}

		//StringBuilder型からString型に変換
		String sql = sb.toString();

		try {
			//データベース接続処理
			conn = getConnection();
			//.createStatement()はパラメータなしのSQL実行に使用する
			pstmt = conn.createStatement();
			//SQL文の実行
			rset = pstmt.executeQuery(sql);

			//検索結果の数だけ繰り返す
			while (rset.next()) {
				//必要な列から値を取り出し、書き込み内容オブジェクトを生成
				UserDTO user = new UserDTO();
				user.setLoginId(rset.getString(2));
				//ユーザ情報変更時にpasswordも必要なため取得する
				user.setPassword(rset.getString(3));
				user.setUserName(rset.getString(4));
				user.setIcon(rset.getString(5));
				user.setProfile(rset.getString(6));

				//書き込み内容をリストに追加
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}
		return list;
	}
}
