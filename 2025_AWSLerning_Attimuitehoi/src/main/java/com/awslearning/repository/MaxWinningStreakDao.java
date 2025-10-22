package com.awslearning.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.awslearning.entity.MatchHistoryTbl;

public class MaxWinningStreakDao {

	/**
	 * 今までの勝敗をsql文で抽出し、Listに格納するメソッド
	 * @return [maxWinningStreakList] 今までの勝敗が格納されているList
	 * @throws SQLException データベース接続に失敗した場合	
	 */
	public List<MatchHistoryTbl> getMaxWinningStreak() throws SQLException {

		final String sqlSelect = "select result from match_history;";

		// 取得した勝敗をリストに格納するため用意
		List<MatchHistoryTbl> maxWinningStreakList = new ArrayList<MatchHistoryTbl>();

		try (Connection conn = DbUtil.getConnection();) {

			if (conn != null) {

				try (Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery(sqlSelect);) {

					while (rs.next()) {
						MatchHistoryTbl matchTenHistorytbl = new MatchHistoryTbl();

						//抽出した勝敗をmatchHistoryResultに一時格納
						matchTenHistorytbl.setResult(rs.getString("RESULT"));

						//一時格納したデータをmaxWinningStreakListに格納
						maxWinningStreakList.add(matchTenHistorytbl);
					}
				}
			}
		}
		return maxWinningStreakList;
	}
}
