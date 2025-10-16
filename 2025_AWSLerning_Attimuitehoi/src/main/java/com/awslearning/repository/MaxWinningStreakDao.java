package com.awslearning.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.awslearning.dto.MaxWinningStreakDto;

public class MaxWinningStreakDao {

	/**
	 * 今までの勝敗をsql文で抽出し、Listに格納するメソッド
	 * @return [maxWinningStreakList] 今までの勝敗が格納されているList
	 * @throws SQLException データベース接続に失敗した場合	
	 */
	public List<MaxWinningStreakDto> getMaxWinningStreak() throws SQLException {

		// 取得した勝敗をリストに格納するため用意
		List<MaxWinningStreakDto> maxWinningStreakList = new ArrayList<MaxWinningStreakDto>();

		try (Connection conn = DbUtil.getConnection();) {

			if (conn != null) {

				//ステートメント作成
				Statement stmt = conn.createStatement();
				String sqlSelect = "select result from match_history ORDER BY MATCH_DATE ASC;";

				//ステートメントを実行
				ResultSet rs = stmt.executeQuery(sqlSelect);

				while (rs.next()) {
					MaxWinningStreakDto maxWinningStreakDto = new MaxWinningStreakDto();

					//抽出した勝敗をmaxWinningStreakDtoに一時格納
					maxWinningStreakDto.setResult(rs.getString("RESULT"));

					//一時格納したデータをmaxWinningStreakListに格納
					maxWinningStreakList.add(maxWinningStreakDto);
				}

				rs.close();
				stmt.close();
			}
		}
		return maxWinningStreakList;
	}
}
