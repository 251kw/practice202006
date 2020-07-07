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
 * データベースとのやり取りをするメソッド
 */
public class DBManager extends SnsDAO {


	/**
	 * @param loginId
	 * @param password
	 * @return user
	 * ログインIDとパスワードを受け取り、登録ユーザー一覧に一致したものがあるか検索
	 */
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

	/**
	 * @param loginId
	 * @return ユーザー情報
	 * ログインIDを受け取りユーザー情報を返す
	 */
	public UserDTO getUser(String loginId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM users WHERE loginId=?";

		UserDTO user = null;

		try {
			//データベース接続情報取得
			conn = getConnection();

			//SERECT文の登録と実行
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);
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

	/**
	 * @param udto
	 * @return 成否
	 */
	public boolean setUser(UserDTO udto) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			//INSERT文の登録と実行
			String sql = "INSERT INTO users(loginId,password,userName,icon,profile) VALUES(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, udto.getLoginId());
			pstmt.setString(2, udto.getPassword());
			pstmt.setString(3, udto.getUserName());
			pstmt.setString(4, udto.getIcon());
			pstmt.setString(5, udto.getProfile());

			int cnt = pstmt.executeUpdate(); //実行
			if (cnt == 1) {
				//INSERT文の実行結果が1なら登録成功
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(pstmt);
			close(conn);
		}

		return result;
	}

	/**
	 * UPDATE文を実行するメソッド
	 * 引数には更新内容を持つUserDTOと更新前のloginIdを渡す
	 */
	public boolean uppdateUser(UserDTO udto,String loginId) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			//UPDATE文の登録と実行
			String sql = "UPDATE users SET loginId=? ,password=? ,userName=? ,icon=?,profile=? WHERE loginId =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, udto.getLoginId());
			pstmt.setString(2, udto.getPassword());
			pstmt.setString(3, udto.getUserName());
			pstmt.setString(4, udto.getIcon());
			pstmt.setString(5, udto.getProfile());
			pstmt.setString(6, loginId);

			int cnt = pstmt.executeUpdate(); //実行
			if (cnt == 1) {
				//UPDATE文の実行結果が1なら登録成功
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(pstmt);
			close(conn);
		}

		return result;
	}

	/**
	 * @param udto
	 * @return 成否
	 */
	public boolean uppdateShouts(UserDTO udto) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			//UPDATE文の登録と実行
			String sql = "UPDATE shouts SET userName=? ,icon=? WHERE loginId =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, udto.getUserName());
			pstmt.setString(2, udto.getIcon());
			pstmt.setString(3, udto.getLoginId());

			int cnt = pstmt.executeUpdate(); //実行
			if (cnt == 1) {
				//UPDATE文の実行結果が1なら登録成功
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(pstmt);
			close(conn);
		}

		return result;
	}

	/**
	 * @return
	 * 書き込み内容リストの getter
	 */
	public ArrayList<ShoutDTO> getShoutList() {
		Connection conn = null; //データベースへ接続して、接続情報を返す
		Statement pstmt = null; //SQL文を作成して、ＳＱＬの管理情報を取得
		ResultSet rset = null; //SQLを実行する

		ArrayList<ShoutDTO> list = new ArrayList<ShoutDTO>(); //ShoutDTOをインスタンス化してリスト化

		try {
			conn = getConnection(); //接続メソッド(SnsDAOへ)
			pstmt = conn.createStatement(); //SQL実行準備(JAVAのメソッド)

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

	/**
	 * @param user ユーザー情報
	 * @param writing 叫び内容
	 * @return 成否
	 * ログインユーザ情報と書き込み内容を受け取り、リストに追加する
	 */
	public boolean setWriting(UserDTO user, String writing) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			conn = getConnection();

			//INSERT文の登録と実行
			String sql = "INSERT INTO shouts(userName,icon,date,writing,loginId) VALUES(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getIcon());
			//現在日時の取得と日付の書式設定
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pstmt.setString(3, sdf.format(calendar.getTime()));
			pstmt.setString(4, writing);
			pstmt.setString(5, user.getLoginId());

			int cnt = pstmt.executeUpdate(); //実行
			if (cnt == 1) {
				//INSERT文の実行結果が1なら登録成功
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(pstmt);
			close(conn);
		}

		return result;
	}

	/*
	 * ログインIDの重複チェックメソッド
	 * 引数に調査したいloginIDを渡すと、
	 * 重複を検知するとBoolean型でfalseを返す
	 * 重複がなければtrueを返す
	 */
	public boolean checkID(String loginId) {
		boolean result = true;

		Connection conn = null; //データベースへ接続して、接続情報を返す
		Statement pstmt = null; //SQL文を作成して、ＳＱＬの管理情報を取得
		ResultSet rset = null; //SQLを実行する

		try {
			conn = getConnection(); //接続メソッド(SnsDAOへ)
			pstmt = conn.createStatement(); //SQL実行準備(JAVAのメソッド)

			//SELECT文の実行()
			String sql = "SELECT loginId FROM users";
			rset = pstmt.executeQuery(sql);

			//検索結果(shouts)の数だけ繰り返す
			while (rset.next()) {
				//必要な列から値を取り出し、書き込み内容オブジェクトを生成
				if (loginId.equals(rset.getString(1))) {
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

	/*
	 * ユーザー検索用のメソッド
	 * あらかじめ作成されたSELECTのSQL文を引数に渡すと、
	 * UserDTOのリスト型で取得した情報を返す
	 */
	public ArrayList<UserDTO> getSearchUserList(String sql) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<UserDTO> list = new ArrayList<UserDTO>(); //UserDTOをインスタンス化してリスト化

		try {
			//データベース接続情報取得
			conn = getConnection();

			//SERECT文の登録と実行
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

				//検索結果(users)の数だけ繰り返す
				while (rset.next()) {
					//必要な列から値を取り出し、書き込み内容オブジェクトを生成
					UserDTO searchUser = new UserDTO();
					searchUser.setLoginId(rset.getString(2));
					searchUser.setPassword(rset.getString(3));
					searchUser.setUserName(rset.getString(4));
					searchUser.setIcon(rset.getString(5));
					searchUser.setProfile(rset.getString(6));

					//書き込み内容をリストに追加
					list.add(searchUser);
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

	//ログインIDを受け取りユーザー情報を返す
		public boolean deleteUser(String loginId) {
			boolean result = false;

			Connection conn = null;
			PreparedStatement pstmt = null;

			try {
				conn = getConnection();

				//DELETE文の登録と実行
				String sql = "DELETE FROM users WHERE loginId=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, loginId);

				int cnt = pstmt.executeUpdate(); //実行
				if (cnt == 1) {
					//DELETE文の実行結果が1なら登録成功
					result = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// データベース切断処理
				close(pstmt);
				close(conn);
			}

			return result;

		}

		/**
		 * @param loginId 削除するshoutsのログインID
		 * @return tf
		 */
		public boolean deleteUserShout(String loginId) {
			boolean result = false;

			Connection conn = null;
			PreparedStatement pstmt = null;

			try {
				conn = getConnection();

				//DELETE文の登録と実行
				String sql = "DELETE FROM shouts WHERE loginId=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, loginId);

				int cnt = pstmt.executeUpdate(); //実行
				if (cnt == 1) {
					//DELETE文の実行結果が1なら登録成功
					result = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// データベース切断処理
				close(pstmt);
				close(conn);
			}

			return result;

		}

		/**
		 * @param sql 作成したDELETEのSQL
		 * @return 成否
		 */
		public boolean deleteSQL(String sql) {
			boolean result = false;

			Connection conn = null;
			PreparedStatement pstmt = null;

			try {
				conn = getConnection();

				//DELETE文の登録と実行
				pstmt = conn.prepareStatement(sql);

				int cnt = pstmt.executeUpdate(); //実行
				if (cnt <= 1) {
					//DELETE文の実行結果が1なら登録成功
					result = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// データベース切断処理
				close(pstmt);
				close(conn);
			}

			return result;

		}

}
