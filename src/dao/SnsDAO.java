package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SnsDAO {
	final String DSN = "jdbc:mysql://localhost:3306/sns?useSSL=false";
	final String USER = "root";
	final String PASSWORD = "root";

	//データベースの接続情報を返す
	public Connection getConnection() {
		Connection conn = null;

		try {
			// JDBCドライバロード
			Class.forName("com.mysql.jdbc.Driver");

			//データベースへ接続
			conn = DriverManager.getConnection(DSN, USER, PASSWORD);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	// Connection 型変換が持つデータベースと　JDBC　リソースの解放
		public void close(Connection conn) {
			if(conn != null){
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// preparedStatement 型変数が持つデータベースとJDBCリソースの解放
		public void close(PreparedStatement stmt) {
			if(stmt != null) {
				try {
					stmt.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// ResultSet 型変数が持つデータベースとJDBCリソース解放
		public void close(ResultSet rset) {
			if(rset != null) {
				try {
					rset.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
}
