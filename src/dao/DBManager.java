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

		String sql = "SELECT * FROM users WHERE loginId=? AND password=? AND d_flg=0";

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

		String sql = "SELECT * FROM users WHERE loginId=? AND d_flg=0";

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


	public ShoutDTO getShout(String sql) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ShoutDTO shout = null;

		try {

			//データベース接続情報取得
			conn = getConnection();

			//SERECT文の登録と実行
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			// 検索結果があれば
			if (rset.next()) {
				//必要な列から値を取り出し、書き込み内容オブジェクトを生成
				shout = new ShoutDTO();
				shout.setShoutsId(String.valueOf(rset.getInt(1)));
				shout.setUserName(rset.getString(2));
				shout.setIcon(rset.getString(3));
				String str = rset.getString(4);
				shout.setDate(str.substring(0, str.indexOf('.')));
				shout.setWriting(rset.getString(5));
				shout.setLoginId(rset.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return shout;
	}

	/**
	 * @param udto 追加したいユーザー情報
	 * @return INSERT文の実行結果が1ならTRUE
	 */
	public boolean setUser(UserDTO udto) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			//INSERT文の登録と実行
			String sql = "INSERT INTO users(loginId,password,userName,icon,profile,d_flg) VALUES(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, udto.getLoginId());
			pstmt.setString(2, udto.getPassword());
			pstmt.setString(3, udto.getUserName());
			pstmt.setString(4, udto.getIcon());
			pstmt.setString(5, udto.getProfile());
			pstmt.setInt(6, 0);

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
	 * UPDATE文の実行結果が1なら登録成功
	 * @param udto 更新されたユーザー情報
	 * @param loginId 更新前のログインID
	 * @return UPDATE文の実行結果が1ならTRUE
	 */
	public boolean uppdateUser(UserDTO udto,String loginId) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			//UPDATE文の登録と実行
			String sql = "UPDATE users SET password=? ,userName=? ,icon=?,profile=? WHERE loginId =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, udto.getPassword());
			pstmt.setString(2, udto.getUserName());
			pstmt.setString(3, udto.getIcon());
			pstmt.setString(4, udto.getProfile());
			pstmt.setString(5, loginId);

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
	 * @param udto 更新されたユーザー情報
	 * @return UPDATE文の実行結果が1ならTRUE
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
	 * 書き込み内容リストの getter
	 * @return ShoutDTOのリスト
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
			String sql = "SELECT * FROM shouts WHERE d_flg=0 ORDER BY date DESC";
			rset = pstmt.executeQuery(sql);

			//検索結果(shouts)の数だけ繰り返す
			while (rset.next()) {
				//必要な列から値を取り出し、書き込み内容オブジェクトを生成
				ShoutDTO shout = new ShoutDTO();
				shout.setShoutsId(String.valueOf(rset.getInt(1)));
				shout.setUserName(rset.getString(2));
				shout.setIcon(rset.getString(3));
				String str = rset.getString(4);
				shout.setDate(str.substring(0, str.indexOf('.')));
				shout.setWriting(rset.getString(5));
				shout.setLoginId(rset.getString(6));

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
	 * @return tf INSERT文の実行結果が1ならTRUE
	 * ログインユーザ情報と書き込み内容を受け取り、リストに追加する
	 */
	public boolean setWriting(UserDTO user, String writing) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			conn = getConnection();

			//INSERT文の登録と実行
			String sql = "INSERT INTO shouts(userName,icon,date,writing,loginId,d_flg) VALUES(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getIcon());
			//現在日時の取得と日付の書式設定
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pstmt.setString(3, sdf.format(calendar.getTime()));
			pstmt.setString(4, writing);
			pstmt.setString(5, user.getLoginId());
			pstmt.setInt(6, 0);

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

	public boolean updateWriting(String writing,String shoutsId) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			conn = getConnection();

			//UPDATE文の登録と実行
			String sql = "UPDATE shouts SET writing=? ,date=? WHERE loginId =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writing);
			//現在日時の取得と日付の書式設定
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pstmt.setString(2, sdf.format(calendar.getTime()));
			pstmt.setString(3, shoutsId);

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
	 * @param loginId 重複を確かめたい新規ログインID
	 * @return tf 重複がなければTRUE
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

	/**
	 * @param sql 検索条件を設定したSQL
	 * @return UserDTOのリスト
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

				//検索結果(users)の数だけ繰り返す6
				while (rset.next()) {
					//必要な列から値を取り出し、書き込み内容オブジェクトを生成
					UserDTO searchUser = new UserDTO();
					searchUser.setLoginId(rset.getString(2));
					searchUser.setPassword(rset.getString(3));
					searchUser.setUserName(rset.getString(4));
					searchUser.setIcon(rset.getString(5));
					searchUser.setProfile(rset.getString(6));
					searchUser.setD_flg(rset.getInt(7));

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

	public ArrayList<ShoutDTO> getSearchShoutsList(String sql) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<ShoutDTO> list = new ArrayList<ShoutDTO>(); //UserDTOをインスタンス化してリスト化

		try {
			//データベース接続情報取得
			conn = getConnection();

			//SERECT文の登録と実行
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

				//検索結果(users)の数だけ繰り返す6
				while (rset.next()) {
					//必要な列から値を取り出し、書き込み内容オブジェクトを生成
					ShoutDTO shout = new ShoutDTO();
					shout.setShoutsId(String.valueOf(rset.getInt(1)));
					shout.setUserName(rset.getString(2));
					shout.setIcon(rset.getString(3));
					String str = rset.getString(4);
					shout.setDate(str.substring(0, str.indexOf('.')));
					shout.setWriting(rset.getString(5));
					shout.setLoginId(rset.getString(6));

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

	//ログインIDを受け取りユーザー情報を返す
		/**
		 * @param loginId 削除したいユーザーのログインID
		 * @return tf DELETE文の実行結果が1ならTRUE
		 */
		@Deprecated
		public boolean deleteUser(String loginId) {
			boolean result = false;

			Connection conn = null;
			PreparedStatement pstmt = null;

			try {
				conn = getConnection();

				//DELETE文の登録と実行
				String sql = "DELETE FROM users WHERE loginId=? AND d_flg=0";
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
		 * @return tf DELETE文の実行結果が1ならTRUE
		 */
		@Deprecated
		public boolean deleteUserShout(String loginId) {
			boolean result = false;

			Connection conn = null;
			PreparedStatement pstmt = null;

			try {
				conn = getConnection();

				//DELETE文の登録と実行
				String sql = "DELETE FROM shouts WHERE loginId=? ";
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
		 * @param sql 作成したSQLの実行
		 * @return tf INSERT文の実行結果が1ならTRUE
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
