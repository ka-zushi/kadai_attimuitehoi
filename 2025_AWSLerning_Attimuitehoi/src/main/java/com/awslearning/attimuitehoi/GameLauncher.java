package com.awslearning.attimuitehoi;

import java.sql.SQLException;

import com.awslearning.service.AttimuitehoiService;

/**
 * 「あっちむいてほい」を実行するクラス
 */
public class GameLauncher {

	public static void main(String[] args) throws SQLException {
		AttimuitehoiService attimuitehoi = new AttimuitehoiService();

		attimuitehoi.startAttimuitehoi();
	}
}