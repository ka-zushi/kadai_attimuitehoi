package com.awslearning.repository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * データベース接続を管理するユーティリティークラス
 */
public class DbUtil {

	private static final Logger logger = LogManager.getLogger(DbUtil.class);
	
	private static String URL;
	private static String USERNAME;
	private static String PASSWORD;

	private static Properties properties = new Properties();

	/**
	 * データーベース接続を行うメソッド
	 * @return [conn] データベース接続用のConnectionオブジェクト(自動コミットはオフ)
	 * @throws SQLException データベース接続に失敗した場合	
	 */
	public static Connection getConnection() throws SQLException {
		init();
		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		//自動コミットをオフでデータベース接続を行う
		conn.setAutoCommit(false);
		return conn;
	}

	/** 
	 * トランザクションをコミットするメソッド
	 * @param connection データベース接続用のConnectionオブジェクト
	 * @throws SQLException コミット処理に失敗した場合
	 */
	public static void commit(Connection conn) throws SQLException {
			conn.commit();
	}

	/**
	 * データベース接続をクローズするメソッド
	 * @param connection クローズするConnectionオブジェクト。nullの場合は何もしない。
	 */
	public static void close(Connection conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	/**
	 * トランザクションをロールバックするメソッド
	 * @param connection ロールバックするConnectionオブジェクト。nullの場合は何もしない。
	 */
	public static void rollback(Connection conn) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				logger.error("ロールバックに失敗しました: ");
			}
		}

	/**
	 * プロパティファイルから設定を読み込むメソッド
	 */
	public static void init() {

		try (InputStream input = DbUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
			// プロパティファイルを読み込む
			properties.load(input);
			// プロパティからデータベース接続情報を取得
			URL = properties.getProperty("spring.datasource.url");
			USERNAME = properties.getProperty("spring.datasource.username");
			PASSWORD = properties.getProperty("spring.datasource.password");

		} catch (IOException e) {
			logger.error("プロパティファイルの読み込みエラー: ");
		}

	}
}
