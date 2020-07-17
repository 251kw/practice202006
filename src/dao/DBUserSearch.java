package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.ShoutDTO;
import dto.UserDTO;

/**
 * データベース
 * ユーザー情報検索クラス
 *
 * userSearch
 * userIdSearch
 * getAllId
 *
 * @author y.sato
 *
 */

public class DBUserSearch extends SnsDAO {
	/**
	 * 検索情報をもとに
	 * データベース上にある登録情報を検索する
	 * メソッド
	 *
	 * @param loginId ログインId
	 * @param userName ユーザー名
	 * @param profile プロフィール
	 * @param icon 選択されたアイコンの配列
	 * @return list 検索結果が格納されたarrylist
	 *
	 */
	public  ArrayList<UserDTO> userSearch(String loginId, String userName, String profile, String[] icon) {
		Connection conn = null; //データベース接続情報
		PreparedStatement pstmt = null; //SQL管理情報
		ResultSet rset = null; //検索結果

		ArrayList<UserDTO> list = new ArrayList<UserDTO>();		//検索結果格納list
		String where = " WHERE ";									//sql文材料
		String and = " AND ";

		String sql = "SELECT * FROM users";

		//アイコン以外の検索条件でsql文を作る
		if(loginId!=null) {	//loginidがあったら
			sql += where+"loginId LIKE '%" +loginId+ "%'";

			if(userName!=null) {
				sql += and+"userName LIKE '%" +userName+ "%'";
			}
			if(profile!=null) {
				sql += and+"profile LIKE '%" +profile+ "%'";
			}

		} else {				 //loginIdがなかったら

			if(userName!=null) {
				sql += where+"userName LIKE '%" +userName+ "%'";

				if(profile!=null) {
					sql += and+"profile LIKE '%" +profile+ "%'";
				}

			} else {
				if(profile!=null){
					sql += where+"profile LIKE '%" +profile+ "%'";
				}
			}
		}

		//アイコンが選択されたら、選択数分をsql文に
		if(icon!=null) {
			String icons="";
			int i;
			for(i=0; i<icon.length-1; i++) {
				icons += "'" +icon[i]+ "',";
			}
			icons += "'" +icon[i++]+ "'";			//最後は「,」つけない

			String iconsql = "icon IN (" +icons+ ")";			//アイコンsql完成

			if(loginId==null && userName==null && profile==null) {

				if(icon!=null) {		//検索条件がアイコンのみ
					sql += where+iconsql;
				}
			} else {

				if(icon!=null) {		//検索条件がアイコン以外にもある
					sql += and+iconsql;
				}
			}
		}

		try {
			//データベース接続情報取得
			conn = getConnection();

			//SELECT文の登録と実行
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			//全件検索
			while (rset.next()) {
				//必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				UserDTO user = new UserDTO();
				user.setLoginId(rset.getString(2));
				user.setPassword(rset.getString(3));
				user.setUserName(rset.getString(4));
				user.setIcon(rset.getString(5));
				user.setProfile(rset.getString(6));
				user.setD_flg(rset.getInt(7));

				list.add(user);		//ユーザー情報をリストに追加

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
	 * loginIdでユーザー情報を
	 * 取得するメソッド
	 *
	 * @param loginId
	 * @return user ユーザー情報
	 */
	public  UserDTO userIdSearch(String loginId){
		Connection conn = null; //データベース接続情報
		PreparedStatement pstmt = null; //SQL管理情報
		ResultSet rset = null; //検索結果

		UserDTO user = new UserDTO();
		try {
			//データベース接続情報取得
			conn = getConnection();

			//SELECT文の作成と実行
			String sql = "SELECT * FROM users WHERE loginId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);

			rset = pstmt.executeQuery();

			//全件検索
			while (rset.next()) {
				//必要な列から値を取り出し、ユーザ情報オブジェクトを生成
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
	 * すべてのloginIdを
	 * 取得するメソッド
	 *
	 * @param 無し
	 * @return loginIdの配列
	 */
	public String[] getAllId() {
		ArrayList<String> list = new ArrayList<>();
		String[] all = null;
		Connection conn = null; //データベース接続情報
		PreparedStatement pstmt = null; //SQL管理情報
		ResultSet rset = null; //検索結果

		String sql = "SELECT loginId FROM sns.users";

		try {
			//データベース接続情報取得
			conn = getConnection();

			//SELECT文の登録と実行
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			//検索結果があれば
			while(rset.next()) {
				list.add(rset.getString(1));
			}

			all = list.toArray(new String[list.size()]);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return all;
	}

	/**
	 * すべてのshoutsIdを
	 * 取得するメソッド
	 *
	 * @param 無し
	 * @return shoutsIdの配列
	 */
	public String[] getAllShoutId() {
		ArrayList<String> list = new ArrayList<>();
		String[] all = null;
		Connection conn = null; //データベース接続情報
		PreparedStatement pstmt = null; //SQL管理情報
		ResultSet rset = null; //検索結果

		String sql = "SELECT shoutsId FROM sns.shouts";

		try {
			//データベース接続情報取得
			conn = getConnection();

			//SELECT文の登録と実行
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			//検索結果があれば
			while(rset.next()) {
				list.add(rset.getString(1));
			}

			all = list.toArray(new String[list.size()]);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return all;
	}

	/**
	 * shoutsIdでユーザー情報を
	 * 取得するメソッド
	 *
	 * @param shoutsId 一意のshoutsId
	 * @return shout 叫び情報
	 */
	public  ShoutDTO shoutIdSearch(int shoutsId){
		Connection conn = null; //データベース接続情報
		PreparedStatement pstmt = null; //SQL管理情報
		ResultSet rset = null; //検索結果

		ShoutDTO shout = new ShoutDTO();
		try {
			//データベース接続情報取得
			conn = getConnection();

			//SELECT文の作成と実行
			String sql = "SELECT s.shoutsId,u.userName,u.icon,s.writing,s.d_flg\r\n" +
						 "FROM sns.users u, sns.shouts s \r\n" +
						 "WHERE s.loginId=u.loginId AND s.shoutsId=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, shoutsId);

			rset = pstmt.executeQuery();

			//全件検索
			while (rset.next()) {
				//必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				shout.setShoutsId(rset.getInt(1));
				shout.setUserName(rset.getString(2));
				shout.setIcon(rset.getString(3));
				shout.setWriting(rset.getString(4));
				shout.setD_flg(rset.getInt(5));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return shout;
	}
}
