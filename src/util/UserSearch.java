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

	public ArrayList<SearchDTO> SearchloginId(String loginId) {
		try {
			conn = getConnection();
			// sql文全件検索
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
}


