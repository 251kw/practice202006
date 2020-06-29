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
	public UserDTO getloginUser(String loginId, String password) {
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
			if (rset.next()) {
				//必要な列から値を取り出し、ユーザ情報オブジェクトの生成
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
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return user;
	}

	public boolean setUser(UserDTO udto) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			//INSERT文の登録と実行
			String sql = "INSERT INTO users(loginId,password,userName,icon,profile) VALUES(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,udto.getLoginId());
			pstmt.setString(2,udto.getPassword());
			pstmt.setString(3,udto.getUserName());
			pstmt.setString(4,udto.getIcon());
			pstmt.setString(5,udto.getProfile());

			int cnt = pstmt.executeUpdate();  //実行
			if(cnt == 1) {
				//INSERT文の実行結果が1なら登録成功
				result = true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// データベース切断処理
						close(pstmt);
						close(conn);
		}

		return result;
	}

	// 書き込み内容リストの getter
	public ArrayList<ShoutDTO> getShoutList() {
		Connection conn = null;  //データベースへ接続して、接続情報を返す
		Statement pstmt = null;  //SQL文を作成して、ＳＱＬの管理情報を取得
		ResultSet rset = null;  //SQLを実行する

		ArrayList<ShoutDTO> list = new ArrayList<ShoutDTO>(); //ShoutDTOをインスタンス化してリスト化

		try {
			conn = getConnection();  //接続メソッド(SnsDAOへ)
			pstmt = conn.createStatement();  //SQL実行準備(JAVAのメソッド)

			//SELECT文の実行(日付順にshoutsを取得)
			String sql = "SELECT * FROM shouts ORDER BY date DESC";
			rset = pstmt.executeQuery(sql);

			//検索結果(shouts)の数だけ繰り返す
			while (rset.next()) {
				//必要な列から値を取り出し、書き込み内容オブジェクトを生成
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
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return list;
	}

	// ログインユーザ情報と書き込み内容を受け取り、リストに追加する
	public boolean setWriting (UserDTO user , String writing) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			conn = getConnection();

			//INSERT文の登録と実行
			String sql = "INSERT INTO shouts(userName,icon,date,writing) VALUES(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getIcon());
			//現在日時の取得と日付の書式設定
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pstmt.setString(3, sdf.format(calendar.getTime()));
			pstmt.setString(4, writing);

			int cnt = pstmt.executeUpdate();  //実行
			if(cnt == 1) {
				//INSERT文の実行結果が1なら登録成功
				result = true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// データベース切断処理
						close(pstmt);
						close(conn);
		}

		return result;
	}

	// ログインIDの重複チェック
		public boolean checkID(String password) {
			boolean result = true;

			Connection conn = null;  //データベースへ接続して、接続情報を返す
			Statement pstmt = null;  //SQL文を作成して、ＳＱＬの管理情報を取得
			ResultSet rset = null;  //SQLを実行する

			try {
				conn = getConnection();  //接続メソッド(SnsDAOへ)
				pstmt = conn.createStatement();  //SQL実行準備(JAVAのメソッド)

				//SELECT文の実行()
				String sql = "SELECT loginId FROM users";
				rset = pstmt.executeQuery(sql);

				//検索結果(shouts)の数だけ繰り返す
				while (rset.next()) {
					//必要な列から値を取り出し、書き込み内容オブジェクトを生成
					if(password.equals(rset.getString(1))) {
						result = false;
					}


				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// データベース切断処理
				close(rset);
				close(pstmt);
				close(conn);
			}

			return result;
		}
}
