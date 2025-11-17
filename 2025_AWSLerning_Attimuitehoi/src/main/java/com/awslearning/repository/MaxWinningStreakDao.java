package com.awslearning.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.awslearning.entity.MatchHistoryTable;

public class MaxWinningStreakDao {

	/**
	 * 今までの勝敗をsql文で抽出し、Listに格納するメソッド
	 * @return [maxWinningStreakList] 今までの勝敗が格納されているList
	 * @throws SQLException データベース接続に失敗した場合	
	 */
	public List<MatchHistoryTable> getMaxWinningStreak() throws SQLException {

		final String resultSelect = "select result from match_history;";

		// 取得した勝敗をリストに格納するため用意
		List<MatchHistoryTable> maxWinningStreakList = new ArrayList<MatchHistoryTable>();

		try (Connection conn = DbUtil.getConnection();) {

				//ステートメントの生成及び、ResultSetでresultSelectを実行した結果を格納
				try (Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery(resultSelect);) {
					//検索結果を1行ずつ読み取る
					while (rs.next()) {
						MatchHistoryTable matchTenHistorytbl = new MatchHistoryTable();

						//抽出した勝敗をmatchHistoryResultに一時格納
						matchTenHistorytbl.setResult(rs.getString("RESULT"));

						//一時格納したデータをmaxWinningStreakListに格納
						maxWinningStreakList.add(matchTenHistorytbl);
					}
				}
			}
		return maxWinningStreakList;
	}
}
