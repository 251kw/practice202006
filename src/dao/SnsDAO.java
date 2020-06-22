package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SnsDAO {
	//データベース接続に必要な情報
	//DSN(データソース名)　Final修飾子ついてるから他から変更できない。
	private final String DSN = "jdbc:mysql://localhost:3306/sns?useSSL=false";
	private final String USER = "root";
	private final String PASSWORD = "root";

	//データベースの接続情報を返す
	public Connection getConnection() {
		Connection conn = null;

		try {
			//JDBDドライバのロード
			Class.forName("com.mysql.jdbc.Driver");

			//データベースへ接続　一番上でDSNとかにログインパスいれてる
		conn = DriverManager.getConnection(DSN, USER, PASSWORD);

		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}

		return conn;
	}

	//Connection型変数が持つデータベースとJDBCリソースの解放
	public void close(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();

			}
		}
	}

	//Statement型変数が持つデータベースとJDBCリソースの開放
	public void close(Statement stmt) {
		if(stmt != null) {
			try {
				stmt.close();
			}catch(SQLException e){
				e.printStackTrace();

			}
		}
	}

	//ResulutSet型変数が持つデータベースとJDBCリソースの開放
	public void close(ResultSet rset) {
		if(rset != null) {
			try {
				rset.close();
			}catch(SQLException e){
				e.printStackTrace();

			}
		}
	}

}
