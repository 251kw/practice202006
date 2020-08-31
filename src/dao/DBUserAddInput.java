package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import dto.UserDTO;

public class DBUserAddInput extends SnsDAO {

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
			String sql = "INSERT INTO sns.users(loginId,password,userName,icon ,profile,d_flag) VALUES(?,?,?,?,?,0)";
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
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	UserDTO user = null;
	ArrayList<UserDTO> dlist = new ArrayList<UserDTO>();
	/**
	 * @return
	 * 削除済みユーザーの全検索
	 */
	public ArrayList<UserDTO> SearchDelAllUser() {
		try {
			conn = getConnection();
			// sql文全件検索
			String sql = "SELECT * FROM sns.users WHERE d_flag = 1";
			pstmt = conn.clientPrepareStatement(sql);
			//sql文の実行
			rset = pstmt.executeQuery();

			//検索結果があれば
			while (rset.next()) {
				//必要な値を取り出し
				user = new UserDTO();
				user.setLoginId(rset.getString(2));
				user.setPassword(rset.getString(3));
				user.setUserName(rset.getString(4));
				user.setIcon(rset.getString(5));
				user.setProfile(rset.getString(6));
				//リストに追加
				dlist.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}
		return dlist;
	}
	/**
	 * @param loginId
	 * @param userName
	 * @param profile
	 * @param icon
	 * @return
	 * 削除済みユーザーの条件検索
	 */
	public ArrayList<UserDTO> SearchDelloginIDlUser(String loginId, String userName,String profile, String[] icon) {
		try {
			int counter = 0;
			conn = getConnection();
			// sql文 SLECT文の登録
			String sql = "SELECT * FROM sns.users WHERE 1=1 AND d_flag = 1 ";

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
				user = new UserDTO();
				user.setLoginId(rset.getString(2));
				user.setPassword(rset.getString(3));
				user.setUserName(rset.getString(4));
				user.setIcon(rset.getString(5));
				user.setProfile(rset.getString(6));
				//配列に追加
				dlist.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}
		return dlist;
		}

}
