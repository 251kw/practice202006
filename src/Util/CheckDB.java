package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.UserDTO;

public class CheckDB {

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
				// TODO 自動生成された catch ブロック
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
}