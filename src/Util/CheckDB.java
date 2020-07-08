package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DBManager;
import dto.ShoutDTO;
import dto.UserDTO;

public class CheckDB {

	/**
	 * 検索入力欄に入力された情報を元にsql文を作成
	 * @param 検索入力欄に入力された文字列
	 * @return 条件に沿ったユーザーを格納したリスト
	 */

	public static ArrayList<UserDTO> joinsql(String sloginId, String suserName, String sicon, String sprofile) {

		final String DSN = "jdbc:mysql://localhost:3306/sns?useSSL=false";
		final String USER = "root";
		final String PASSWORD = "root";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<UserDTO> resultList = new ArrayList<UserDTO>();
		ArrayList<String> sqlList = new ArrayList<String>();

		String sql = "select * from users ";
		String idstr = null;
		String namestr = null;
		String iconstr = null;
		String profstr = null;

		if(!(sloginId.equals(""))) {
			idstr = "where loginId like '%" + sloginId + "%'";
			sqlList.add(idstr);
		}

		if(!(suserName.equals(""))) {
			if(sqlList.isEmpty()) {
				namestr = "where userName like '%" + suserName + "%'";
			}else {
				namestr = "&& userName like '%" + suserName + "%'";
			}
			sqlList.add(namestr);
		}

		if(!(sicon.equals(""))) {
			if(sqlList.isEmpty()) {
				iconstr = "where icon='" + sicon + "'";
			}else {
				iconstr = "&& icon='" + sicon + "'";
			}
			sqlList.add(iconstr);
		}

		if(!(sprofile.equals(""))) {
			if(sqlList.isEmpty()) {
				profstr = "where profile like '%" + sprofile + "%'";
			}else {
				profstr = "&& profile like '%" + sprofile + "%'";
			}
			sqlList.add(profstr);
		}

		try {

			// 条件に沿ったsql文を繋ぐ
			for(String mysql : sqlList){
				sql = sql+" "+mysql;
			}

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DSN,USER,PASSWORD);

			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();

			try {
				// ヒットした全てのユーザー情報をリストに格納
				while(rset.next()) {
					UserDTO user = new UserDTO();
					user.setLoginId(rset.getString(2));
					user.setUserName(rset.getString(4));
					user.setIcon(rset.getString(5));
					user.setProfile(rset.getString(6));
					resultList.add(user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			}catch(SQLException e) {}
		}
		return resultList;
	}



	/**
	 * ログインIDを参照し、特定のユーザーとそのユーザーの書き込みを削除する
	 * @param ログインID
	 * @return 削除したユーザーの書き込みを削除したShoutsリスト
	 */
	public static ArrayList<ShoutDTO> DeleteUser(String dloginId) {

		final String DSN = "jdbc:mysql://localhost:3306/sns?useSSL=false";
		final String USER = "root";
		final String PASSWORD = "root";

		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;

		DBManager dbm = new DBManager();

		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DSN,USER,PASSWORD);
			String sql1 = "delete from shouts where loginId = ?";
			String sql2 = "delete from users where loginId = ?";
			pstmt1 = conn.prepareStatement(sql1);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt1.setString(1, dloginId);
			pstmt2.setString(1, dloginId);
			pstmt1.executeUpdate();
			pstmt2.executeUpdate();

		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt1.close();
				pstmt2.close();
			}catch(SQLException e) {}
		}

		// 書き込み内容削除後のリストを取得
		ArrayList<ShoutDTO> list = dbm.getShoutList();

		return list;
	}


	/**
	 * ログインIDを参照し、特定のユーザーを検索する
	 * @param ログインID
	 * @return ユーザー情報を持ったUserDTO型のオブジェクト
	 */
	public static UserDTO SearchUser(String loginId) {

		final String DSN = "jdbc:mysql://localhost:3306/sns?useSSL=false";
		final String USER = "root";
		final String PASSWORD = "root";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		UserDTO user = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DSN,USER,PASSWORD);
			String sql = "select * from users where loginId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);
			rset = pstmt.executeQuery();

			if(rset.next()) {
				user = new UserDTO();
				user.setLoginId(rset.getString(2));
				user.setPassword(rset.getString(3));
				user.setUserName(rset.getString(4));
				user.setIcon(rset.getString(5));
				user.setProfile(rset.getString(6));
			}

		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			}catch(SQLException e) {}
		}
		return user;
	}



	/**
	 * 元々のユーザー情報と入力された変更内容を比較し、変更されている箇所のみをsqlで更新する
	 * @param originaluser 特定のユーザーが持つ元々の情報
	 * @param euser 変更入力欄に入力された値
	 */
	public static void EditUser(UserDTO originaluser, UserDTO euser) {

		try {
			Class.forName("com.mysql.jdbc.Driver");

			final String DSN = "jdbc:mysql://localhost:3306/sns?useSSL=false";
			final String USER = "root";
			final String PASSWORD = "root";

			Connection conn = null;
			PreparedStatement pstmt1 = null;

			conn = DriverManager.getConnection(DSN, USER, PASSWORD);

			String sqleId = "update users set loginId=? where loginId=?";
			String sqlepass = "update users set password=? where loginId=?";
			String sqlename = "update users set userName=? where loginId=?";
			String sqleicon = "update users set icon=? where loginId=?";
			String sqleprofile = "update users set profile=? where loginId=?";

			// ログインIDが変更される場合
			if(!(euser.getLoginId().equals(originaluser.getLoginId()))) {
				pstmt1 = conn.prepareStatement(sqleId);
				pstmt1.setString(1, euser.getLoginId());
				pstmt1.setString(2, originaluser.getLoginId());
				pstmt1.executeUpdate();
				if(!(euser.getPassword().equals(originaluser.getPassword()))) {
					pstmt1 = conn.prepareStatement(sqlepass);
					pstmt1.setString(1, euser.getPassword());
					pstmt1.setString(2, euser.getLoginId());
					pstmt1.executeUpdate();
				}
				if(!(euser.getUserName().equals(originaluser.getUserName()))) {
					pstmt1 = conn.prepareStatement(sqlename);
					pstmt1.setString(1, euser.getUserName());
					pstmt1.setString(2, euser.getLoginId());
					pstmt1.executeUpdate();
				}
				if(!(euser.getIcon().equals(originaluser.getIcon()))) {
					pstmt1 = conn.prepareStatement(sqleicon);
					pstmt1.setString(1, euser.getIcon());
					pstmt1.setString(2, euser.getLoginId());
					pstmt1.executeUpdate();
				}
				if(!(euser.getProfile().equals(originaluser.getProfile()))) {
					pstmt1 = conn.prepareStatement(sqleprofile);
					pstmt1.setString(1, euser.getProfile());
					pstmt1.setString(2, euser.getLoginId());
					pstmt1.executeUpdate();
				}
			// ログインIDが変更されない場合
			}else {
				if(!(euser.getPassword().equals(originaluser.getPassword()))) {
					pstmt1 = conn.prepareStatement(sqlepass);
					pstmt1.setString(1, euser.getPassword());
					pstmt1.setString(2, originaluser.getLoginId());
					pstmt1.executeUpdate();
				}
				if(!(euser.getUserName().equals(originaluser.getUserName()))) {
					pstmt1 = conn.prepareStatement(sqlename);
					pstmt1.setString(1, euser.getUserName());
					pstmt1.setString(2, originaluser.getLoginId());
					pstmt1.executeUpdate();
				}
				if(!(euser.getIcon().equals(originaluser.getIcon()))) {
					pstmt1 = conn.prepareStatement(sqleicon);
					pstmt1.setString(1, euser.getIcon());
					pstmt1.setString(2, originaluser.getLoginId());
					pstmt1.executeUpdate();
				}
				if(!(euser.getProfile().equals(originaluser.getProfile()))) {
					pstmt1 = conn.prepareStatement(sqleprofile);
					pstmt1.setString(1, euser.getProfile());
					pstmt1.setString(2, originaluser.getLoginId());
					pstmt1.executeUpdate();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}

	}


	public static ArrayList<UserDTO> MakedeleteList(String[] select) {

		final String DSN = "jdbc:mysql://localhost:3306/sns?useSSL=false";
		final String USER = "root";
		final String PASSWORD = "root";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		UserDTO user = null;

		ArrayList<UserDTO> deleteList = new ArrayList<UserDTO>();

		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DSN,USER,PASSWORD);
			String sql = "select * from users where loginId = ?";
			pstmt = conn.prepareStatement(sql);

			for (String selectId: select){
				pstmt.setString(1, selectId);
				rset = pstmt.executeQuery();

				if(rset.next()) {
					user = new UserDTO();
					user.setLoginId(rset.getString(2));
					user.setPassword(rset.getString(3));
					user.setUserName(rset.getString(4));
					user.setIcon(rset.getString(5));
					user.setProfile(rset.getString(6));
				}
				deleteList.add(user);
			}

		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			}catch(SQLException e) {}
		}
		return deleteList;
	}
}