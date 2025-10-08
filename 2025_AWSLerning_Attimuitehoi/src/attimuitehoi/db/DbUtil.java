package attimuitehoi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * データベース接続を管理するクラス
 */
public class DbUtil {
	private static String URL = "jdbc:hsqldb:hsql://localhost/AWSLERNINGDB";
	private static String USERNAME = "SA";
	private static String PASSWORD = "";


	/**
	 * Connectionを取得し、db接続を行う。
	 * @return [conn] データベース接続用のConnectionオブジェクト
	 * @throws SQLException データベース接続に失敗した場合	
	 */
	public Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		return conn;
	}

	/** 
	 * トランザクションをコミットする
	 * @param connection
	 * @throws SQLException コミット処理に失敗した場合
	 */
	public void commit(Connection connection) throws SQLException {
		if (connection != null) {
			connection.commit();
		}
	}

	/**
	 * データベース接続をクローズする。
	 * @param connection クローズするConnectionオブジェクト。nullの場合は何もしない。
	 */
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
