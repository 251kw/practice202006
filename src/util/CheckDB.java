package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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

		// 検索条件に入力がある項目を繋げて1つのsql文をつくる
		if(!(sloginId.equals(""))) {
			idstr = "where loginId like '%" + sloginId + "%'";
			sqlList.add(idstr);
		}

		// suserName以降は&&以降の可能性があるため条件分岐
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
					user.setD_flg(rset.getString(7));
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

			// 削除対象のユーザーの書き込みを削除してからユーザーを削除する
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DSN,USER,PASSWORD);
			String sql1 = "delete from shouts where loginId = ?";
			String sql2 = "update users set d_flg=? where loginId=?";
			pstmt1 = conn.prepareStatement(sql1);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt1.setString(1, dloginId);
			pstmt2.setInt(1, 1);
			pstmt2.setString(2, dloginId);
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

			// ログインIDを元にユーザーを検索
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DSN,USER,PASSWORD);
			String sql = "select * from users where loginId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);
			rset = pstmt.executeQuery();

			// 見つけたユーザーの情報を格納
			if(rset.next()) {
				user = new UserDTO();
				user.setLoginId(rset.getString(2));
				user.setPassword(rset.getString(3));
				user.setUserName(rset.getString(4));
				user.setIcon(rset.getString(5));
				user.setProfile(rset.getString(6));
				user.setD_flg(rset.getString(7));
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
	 * 変更されたデータがshoutsテーブルにも関係がある場合はshoutsテーブル上のデータも更新する
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
			PreparedStatement pstmt2 = null;

			conn = DriverManager.getConnection(DSN, USER, PASSWORD);

			// userstable用sql
			String sqlepass = "update users set password=? where loginId=?";
			String sqlename = "update users set userName=? where loginId=?";
			String sqleicon = "update users set icon=? where loginId=?";
			String sqleprofile = "update users set profile=? where loginId=?";

			// shoutstable用sql
			String sqlesname = "update shouts set username=? where loginId=?";
			String sqlesicon = "update shouts set icon=? where loginId=?";

			// 変更された内容がある場合は更新していく
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

				pstmt2 = conn.prepareStatement(sqlesname);
				pstmt2.setString(1, euser.getUserName());
				pstmt2.setString(2, originaluser.getLoginId());
				pstmt2.executeUpdate();
			}
			if(!(euser.getIcon().equals(originaluser.getIcon()))) {
				pstmt1 = conn.prepareStatement(sqleicon);
				pstmt1.setString(1, euser.getIcon());
				pstmt1.setString(2, originaluser.getLoginId());
				pstmt1.executeUpdate();

				pstmt2 = conn.prepareStatement(sqlesicon);
				pstmt2.setString(1, euser.getIcon());
				pstmt2.setString(2, originaluser.getLoginId());
				pstmt2.executeUpdate();
			}
			if(!(euser.getProfile().equals(originaluser.getProfile()))) {
				pstmt1 = conn.prepareStatement(sqleprofile);
				pstmt1.setString(1, euser.getProfile());
				pstmt1.setString(2, originaluser.getLoginId());
				pstmt1.executeUpdate();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}

	}


	/**
	 * チェックボックスにチェックを入れたユーザーを元に削除リストを作成する
	 * @param select チェックを入れたユーザーを格納した配列
	 * @return 削除するユーザーの情報を格納したリスト
	 */
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

			// 配列に入っているID全員分の情報をリストに追加
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


	/**
	 * 引数によって特定される書き込み情報を取得する
	 * @param shoutsId
	 * @return 書き込み情報を持ったオブジェクト
	 */
	public static ShoutDTO SearchShouts(String shoutsId) {

		final String DSN = "jdbc:mysql://localhost:3306/sns?useSSL=false";
		final String USER = "root";
		final String PASSWORD = "root";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ShoutDTO shoutinfo = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DSN,USER,PASSWORD);

			// shoutsIdで特定される書き込みを検索
			String sql = "SELECT * FROM users INNER JOIN shouts ON users.loginId = shouts.loginId where shoutsId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, shoutsId);
			rset = pstmt.executeQuery();

			// 検索結果をオブジェクトに保持
			if(rset.next()) {
				shoutinfo = new ShoutDTO();
				shoutinfo.setShoutsId(rset.getString(1));
				shoutinfo.setLoginId(rset.getString(2));
				shoutinfo.setUserName(rset.getString(4));
				shoutinfo.setIcon(rset.getString(5));
				shoutinfo.setDate(rset.getString(10));
				shoutinfo.setWriting(rset.getString(11));
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
		return shoutinfo;
	}


	/**
	 * 変更された書き込み内容と更新された時点での時間をDBに登録する
	 * @param shoutsId
	 * @param 変更する書き込み内容
	 */
	public static void EditShouts(String shoutsId, String writing) {

		final String DSN = "jdbc:mysql://localhost:3306/sns?useSSL=false";
		final String USER = "root";
		final String PASSWORD = "root";

		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DSN, USER, PASSWORD);

			// 書き込み内容を更新
			String sql1 = "update shouts set writing=? where shoutsId=?";
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, writing);
			pstmt1.setString(2, shoutsId);
			pstmt1.executeUpdate();

			// 時間を更新
			String sql2 = "update shouts set date=? where shoutsId=?";
			pstmt2 = conn.prepareStatement(sql2);
			Calendar calender = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pstmt2.setString(1, sdf.format(calender.getTime()));
			pstmt2.setString(2, shoutsId);
			pstmt2.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}

		return;
	}


	/**
	 * 特定の書き込みを削除する
	 * @param shoutsId
	 */
	public static void DeleteShout(String shoutsId) {

		final String DSN = "jdbc:mysql://localhost:3306/sns?useSSL=false";
		final String USER = "root";
		final String PASSWORD = "root";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DSN,USER,PASSWORD);
			// 該当する書き込みを削除
			String sql = "delete from shouts where shoutsId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, shoutsId);
			pstmt.executeUpdate();

		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			}catch(SQLException e) {}
		}

		return;
	}


	/**
	 * 論理削除中のユーザーを復活させる
	 * @param shoutsId
	 */
	public static void RevivalUser() {

		final String DSN = "jdbc:mysql://localhost:3306/sns?useSSL=false";
		final String USER = "root";
		final String PASSWORD = "root";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DSN,USER,PASSWORD);
			// フラグを１から０に変更
			String sql = "update users set d_flg=0 where d_flg=1";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();

		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			}catch(SQLException e) {}
		}

		return;
	}


	// TODO 未使用の関数
	/**
	 * 物理削除を実行する関数
	 * @param dloginId
	 * @return 更新された書き込み
	 */
	public static ArrayList<ShoutDTO> ExecuteUser(String dloginId) {

		final String DSN = "jdbc:mysql://localhost:3306/sns?useSSL=false";
		final String USER = "root";
		final String PASSWORD = "root";

		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;

		DBManager dbm = new DBManager();

		try {

			// 削除対象のユーザーの書き込みを削除してからユーザーを削除する
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DSN,USER,PASSWORD);
			String sql1 = "delete from shouts where loginId = ?";
			String sql2 = "delete from users where loginId=?";
			pstmt1 = conn.prepareStatement(sql1);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt1.setString(1, dloginId);
			pstmt2.setInt(1, 1);
			pstmt2.setString(2, dloginId);
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
}