package util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import dao.SnsDAO;
import dto.SearchDTO;


/**
 * @author d.ito
 *
 */
public class UserSearch extends SnsDAO {
	//文字化け対策
	Connection conn = null;
	PreparedStatement pstmt = null;
	SearchDTO user = null;
	ResultSet rset = null;
	//リスト取得
	ArrayList<SearchDTO> list = new ArrayList<SearchDTO>();

	//全件検索
	/**
	 * @return
	 */
	public ArrayList<SearchDTO> SearchAllUser() {
		try {
			conn = getConnection();
			// sql文全件検索
			String sql = "SELECT * FROM sns.users";
			pstmt = conn.clientPrepareStatement(sql);
			//sql文の実行
			rset = pstmt.executeQuery();

			//検索結果があれば
			while (rset.next()) {
				//必要な値を取り出し
				user = new SearchDTO();
				user.setLoginId(rset.getString(2));
				user.setPassword(rset.getString(3));
				user.setUserName(rset.getString(4));
				user.setIcon(rset.getString(5));
				user.setProfile(rset.getString(6));
				//リストに追加
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
	 * @param loginId
	 * @param userName
	 * @param profile
	 * @param icon
	 * @return
	 * 検索するsql文の生成と実行を行う
	 */
	public ArrayList<SearchDTO> SearchloginIDlUser(String loginId, String userName,String profile, String[] icon) {
		try {
			int counter = 0;
			conn = getConnection();
			// sql文 SLECT文の登録
			String sql = "SELECT * FROM sns.users WHERE 1=1 ";

			//ログインIDが空白でなければ
			if(!loginId.equals("")){
				sql += "AND loginId LIKE ?" ;
			}

			//名前が空白でなければWHERE句追加
			if(!userName.equals("")) {
				sql += "AND userName LIKE ?";
			}

			//プロフィールが空白でなければWHERE句追加
			if(!profile.equals("")) {
				sql += "AND profile LIKE ?";
			}


			//アイコンが空白でなければWHERE句追加
			if(icon != null) {
				if(icon.length == 1) {
					sql += "AND icon LIKE ?";
				}
				//アイコンが二つ以上選択されている
				if(icon.length >= 2) {
					sql += "AND (icon LIKE ?";
					//iconの配列数だけ繰り返す
					for(int i = 0; i < icon.length - 1; i++) {
						sql += "OR icon LIKE ?)";
					}
				}
			}

			pstmt = conn.clientPrepareStatement(sql);

			//プレースホルダにセット
			if(!loginId.equals("")) {
				pstmt.setString(++counter,"%" +loginId + "%" );
			}
			if(!userName.equals("")) {
				pstmt.setString(++counter,"%" +userName + "%" );
			}

			if(!profile.equals("")) {
				pstmt.setString(++counter,"%" +profile + "%" );
			}

			if(icon != null) {
				if(icon.length == 1) {
					pstmt.setString(++counter,"%" +icon[0] + "%" );
				}


				if(icon.length >= 2) {
					for(int i = 0; i <= icon.length - 1; i++) {
						pstmt.setString(++counter,"%" +icon[i] + "%" );
					}
				}
			}
			//sql文の実行
			rset = pstmt.executeQuery();

			//検索結果があれば
			while (rset.next()) {
				//必要な値を取り出し
				user = new SearchDTO();
				user.setLoginId(rset.getString(2));
				user.setPassword(rset.getString(3));
				user.setUserName(rset.getString(4));
				user.setIcon(rset.getString(5));
				user.setProfile(rset.getString(6));
				//配列に追加
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

	/**
	 * @param loginId
	 * @return
	 * ユーザー削除のメソッド
	 */
	public boolean deleteUser(String loginId){
		boolean result = false;
		try {
			conn = getConnection();
			// sql文全件検索
			String sql = "DELETE FROM sns.users WHERE loginId=?";
			pstmt = conn.clientPrepareStatement(sql);
			pstmt.setString(1, loginId);
			//sql文の実行
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

	/**
	 * @param loginId
	 * @return
	 * ユーザーの複数削除
	 */
	public boolean multideleteUser(String[] loginId){
		boolean result = false;
		try {
			conn = getConnection();
			//配列の数を数えるカウンター
			int counter = 0;
			// sql文全件検索
			String sql = "DELETE FROM sns.users WHERE ";

			//ioginidが空でなければWHERE句追加
			if(loginId.length == 1) {
				sql += "loginId=? " ;
			}

			if(loginId.length >= 2) {
				sql += "loginId=? " ;
				for (int i = 0; i < loginId.length - 1; i++) {
					sql += "OR loginId=?" ;
				}
			}

			pstmt = conn.clientPrepareStatement(sql);

			//プレースホルダにセット
			if(loginId != null) {
				for (int i = 0; i <= loginId.length - 1; i++) {
					pstmt.setString(++counter,loginId[i]);
				}
			}

			//sql文の実行
			int cnt = pstmt.executeUpdate();
			if(cnt != 0) {
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
	/**
	 * @param loginId
	 * @return
	 * 叫びの削除を行うメソッド
	 */
	public boolean multideleteshouts(String[] loginId){
		boolean result = false;
		try {
			conn = getConnection();
			//配列の数を数えるカウンター
			int counter = 0;
			// sql文全件検索
			String sql = "DELETE FROM sns.shouts WHERE ";

			//ioginidが空でなければWHERE句追加
			if(loginId.length == 1) {
				sql += "loginId=? " ;
			}

			if(loginId.length >= 2) {
				sql += "loginId=? " ;
				for (int i = 0; i < loginId.length - 1; i++) {
					sql += "OR loginId=?" ;
				}
			}

			pstmt = conn.clientPrepareStatement(sql);

			//プレースホルダにセット
			if(loginId != null) {
				for (int i = 0; i <= loginId.length - 1; i++) {
					pstmt.setString(++counter,loginId[i]);
				}
			}

			//sql文の実行
			int cnt = pstmt.executeUpdate();
			if(cnt != 0) {
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


	/**
	 * @param loginId ログインID
	 * @return
	 * ログインIDから検索するメソッド
	 */
	public ArrayList<SearchDTO> SearchloginId(String loginId) {
		try {
			conn = getConnection();
			// sql文
			String sql = "SELECT * FROM sns.users WHERE loginId=?";
			pstmt = conn.clientPrepareStatement(sql);
			pstmt.setString(1, loginId);
			//sql文の実行
			rset = pstmt.executeQuery();

			//検索結果があれば
			while (rset.next()) {
				//必要な値を取り出し
				user = new SearchDTO();
				user.setLoginId(rset.getString(2));
				user.setPassword(rset.getString(3));
				user.setUserName(rset.getString(4));
				user.setIcon(rset.getString(5));
				user.setProfile(rset.getString(6));
				//リストに追加
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
	 * @param loginId ログインID
	 * @return
	 * ログインIDから複数検索するメソッド
	 */
	public ArrayList<SearchDTO> searchMultiloginId(String[] loginId) {
		try {
			conn = getConnection();
			//配列の数を数えるカウンター
			int counter = 0;

			// sql文
			String sql = "SELECT * FROM sns.users WHERE ";

			//ioginidが空でなければWHERE句追加
			if(loginId.length == 1) {
				sql += "loginId=? " ;
			}

			if(loginId.length >= 2) {
				sql += "loginId=? " ;
				for (int i = 0; i < loginId.length - 1; i++) {
					sql += "OR loginId=?" ;
				}
			}

			pstmt = conn.clientPrepareStatement(sql);

			//プレースホルダにセット
			if(loginId != null) {
				for (int i = 0; i <= loginId.length - 1; i++) {
					pstmt.setString(++counter,loginId[i]);
				}
			}

			//sql文の実行
			rset = pstmt.executeQuery();

			//検索結果があれば
			while (rset.next()) {
				//必要な値を取り出し
				user = new SearchDTO();
				user.setLoginId(rset.getString(2));
				user.setPassword(rset.getString(3));
				user.setUserName(rset.getString(4));
				user.setIcon(rset.getString(5));
				user.setProfile(rset.getString(6));
				//リストに追加
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

	//ユーザー名を更新するメソッド
	/**
	 * @param userName 新しいユーザー名
	 * @param sloginId	更新するユーザーのログインID
	 * @return
	 */
	public boolean updateUserName(String userName, String sloginId){
		boolean result = false;
		try {
			conn = getConnection();
			// sql文
			String sql = "UPDATE sns.users SET userName=? WHERE loginId=?";
			pstmt = conn.clientPrepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, sloginId);
			//sql文の実行
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

	/**
	 * @param loginId 新しいログインID
	 * @param sloginId	更新するユーザーのログインID
	 * @return
	 * ログインIDを更新するメソッド
	 */
	public boolean updateloginId(String loginId, String sloginId){
		boolean result = false;
		try {
			conn = getConnection();
			// sql文
			String sql = "UPDATE sns.users SET loginid=? WHERE loginId=?";
			pstmt = conn.clientPrepareStatement(sql);
			pstmt.setString(1, loginId);
			pstmt.setString(2, sloginId);
			//sql文の実行
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

	/**
	 * @param password 新しいパスワード
	 * @param sloginId　更新するユーザーのログインID
	 * @return
	 * パスワードの更新
	 */
	public boolean updatepassword(String password, String sloginId){
		boolean result = false;
		try {
			conn = getConnection();
			// sql文
			String sql = "UPDATE sns.users SET password=? WHERE loginId=?";
			pstmt = conn.clientPrepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2, sloginId);
			//sql文の実行
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

	/**
	 * @param profile 新しいプロフィール
	 * @param sloginId	更新するユーザーのログインID
	 * @return
	 * プロフィールを更新するメソッド
	 */
	public boolean updateprofile(String profile, String sloginId){
		boolean result = false;
		try {
			conn = getConnection();
			// sql文
			String sql = "UPDATE sns.users SET profile=? WHERE loginId=?";
			pstmt = conn.clientPrepareStatement(sql);
			pstmt.setString(1, profile);
			pstmt.setString(2, sloginId);
			//sql文の実行
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

	public boolean updateicon(String icon, String sloginId){
		boolean result = false;
		try {
			conn = getConnection();
			// sql文
			String sql = "UPDATE sns.users SET icon=? WHERE loginId=?";
			pstmt = conn.clientPrepareStatement(sql);
			pstmt.setString(1, icon);
			pstmt.setString(2, sloginId);
			//sql文の実行
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

	public boolean updateShoutsName(String userName, String sloginId){
		boolean result = false;
		try {
			conn = getConnection();
			// sql文
			String sql = "UPDATE sns.shouts SET userName=? WHERE loginId=?";
			pstmt = conn.clientPrepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, sloginId);
			//sql文の実行
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


