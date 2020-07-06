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

public class DBManager extends SnsDAO{

	//ログインIDとパスワードを受け取り、登録ユーザ一覧に一致したものがあるか検索
	//SnsDAOでJDBCドライバの読み込みは終わってる。継承してるからしなくていい。
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

	//ユーザID重複チェック
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


	//書き込み内容リストのゲッター
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
				shout.setUserName(rset.getString(2));
				shout.setIcon(rset.getString(3));
				shout.setDate(rset.getString(4));
				shout.setWriting(rset.getString(5));

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


	//ログインユーザ情報と書き込み内容リストを受け取り、リストにする
	//booleanメソッドは、戻り値が固定の数字か否かを調べるのに最適
	public boolean setWriting(UserDTO user, String writing) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			//データベースに接続
			conn = getConnection();

			//INSERT文の登録と実行
			String sql = "INSERT INTO shouts(userName, icon, date, writing) VALUES(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getIcon());
			//現在日時の取得と日付の書式の指定
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pstmt.setString(3, sdf.format(calendar.getTime()));
			pstmt.setString(4, writing);

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


	//入力チェッククリアしてユーザから再確認もOKだったらDBに登録。
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

/*
	//ログイン後、検索画面でのユーザ検索
	public UserDTO getSearchUser(String loginId, String password, String userName, String icon, String profile ) {
		Connection conn = null;	//データベース接続情報
		Statement pstmt = null;	//SQL管理情報(プレースホルダー使わないのでStatement型)
		ResultSet rset = null;	//検索結果

		//検索値に値があるものだけ、sql文に部分一致のSELECT文を追加していく
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM users WHERE");
		if(loginId != "") { sb.append(" loginId = " + loginId + " AND");};
		if(password != null) { sb.append(" password %" + password + "% AND"); }
		if(userName != "") { sb.append(" userName %" + userName + "% AND"); }
		if(icon != null) { sb.append(" icon=" + icon + " AND"); }	//iconのみ完全一致
		if(profile != "") { sb.append(" profile %" + profile + "%"); }

		//文末に"AND"がついているか調べる
		String regex = "AND$";
		Pattern p = Pattern.compile(regex); //正規表現のコンパイル
		Matcher m = p.matcher(sb);

		//文末の"AND"を消す
		if(m.find()) {
			int len = sb.length(); // sqlのlength(長さ)をint型変数のsizeに格納
			sb.delete(len - 3, len);	//文末から3文字(="AND")を削除
		}

		//文末に"END"を追加
		sb.append(" END;");
		System.out.println(sb.toString());

		//StringBuilder型からString型に変換
		String sql = sb.toString();

		//登録ユーザ情報
		UserDTO user = null;
		//検索結果いれるためのArrayList
		ArrayList<User> list = new ArrayList<>();

		try {
			//データベース接続情報取得
			conn = getConnection();

			//.createStatement()はパラメータなしのSQL実行に使用する
			pstmt = conn.createStatement();

			//SQL文の実行
			rset = pstmt.executeQuery(sql);

			//検索結果があれば ここも繰り返す
			if(rset.next()) {
				//必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user = new UserDTO();
				user.setLoginId(rset.getString(2));
				user.setPassword(rset.getString(3));
				user.setUserName(rset.getString(4));
				user.setIcon(rset.getString(5));
				user.setProfile(rset.getString(6));

				//list.add(user);

				System.out.println(user);

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

*/

	//お試し！--------------------------
	//ログイン後、検索画面でのユーザ検索
	public ArrayList<UserDTO> getSearchList(String loginId, String userName, String icon, String profile){
		Connection conn = null;	//データベース接続情報
		Statement pstmt = null;	//SQL管理情報
		ResultSet rset = null;	//検索結果

		ArrayList<UserDTO> list = new ArrayList<UserDTO>();

		//検索値に値があるものだけ、sql文に部分一致のSELECT文を追加していく
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM users WHERE");
		if(loginId != "") { sb.append(" loginId LIKE '%" + loginId + "%' AND");};
		//if(loginId != "") { sb.append(" loginId='" + loginId + "' AND");};
		//if(password != null) { sb.append(" password LIKE '%" + password + "%' AND"); }	//初期値null
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
			sb.delete(len - 4, len);	//文末から4文字(=" AND")を削除
		}

		//文末に"' "を追加
		//sb.append(" ");
		System.out.println(sb.toString());

		//StringBuilder型からString型に変換
		String sql = sb.toString();

		try {

			//データベース接続処理
			conn = getConnection();
			//.createStatement()はパラメータなしのSQL実行に使用する
			pstmt = conn.createStatement();
			//SQL文の実行
			rset = pstmt.executeQuery(sql);

			System.out.println(rset);

			//検索結果の数だけ繰り返す
			while (rset.next()) {
				//必要な列から値を取り出し、書き込み内容オブジェクトを生成
				UserDTO user = new UserDTO();
				user.setLoginId(rset.getString(2));
				//user.setPassword(rset.getString(3));
				user.setUserName(rset.getString(4));
				user.setIcon(rset.getString(5));
				user.setProfile(rset.getString(6));

				//書き込み内容をリストに追加
				list.add(user);
				System.out.println(user.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		System.out.println(list);

		return list;
	}
}
