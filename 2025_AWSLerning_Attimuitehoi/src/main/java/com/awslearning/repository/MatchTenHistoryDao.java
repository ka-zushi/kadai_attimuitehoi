package com.awslearning.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.awslearning.dto.MatchTenHistoryDto;

public class MatchTenHistoryDao {
	/**
	 * * 過去10回分の対戦結果をsql文で抽出し、結果をListに格納するメソッド
	 * @return [matchTenList] 過去10回の対戦履歴をリストに格納したもの。
	 * @throws SQLException データベース接続に失敗した場合	
	 */
	public List<MatchTenHistoryDto> getMatchTenResults() throws SQLException {

		// 取得した対戦履歴をリストに格納するため用意
		List<MatchTenHistoryDto> matchTenList = new ArrayList<MatchTenHistoryDto>();

		final String tenResultSql = "SELECT MATCH_DATE, RESULT FROM match_history ORDER BY ID DESC LIMIT 10;";

		int count = 0;

		try (Connection conn = DbUtil.getConnection()) {
			if (conn != null) {
				try (Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery(tenResultSql);) {

					while (rs.next()) {

						//MatchTenHistoryDtoをインスタンス化
						MatchTenHistoryDto matchTenHistoryDto = new MatchTenHistoryDto();

						//抽出した対戦履歴をmatchTenHistoryDtoに一時格納
						matchTenHistoryDto.setMatchDate(rs.getDate("MATCH_DATE"));
						matchTenHistoryDto.setResult(rs.getString("RESULT"));

						//一時格納したデータをmatchTenListに格納
						matchTenList.add(matchTenHistoryDto);

						count++;
					}

					if (count > 10) {
						System.out.println("過去にあっち向いてほいが" + count + "回しか行われていませんでした。");
					}
				}
			}
		}
		return matchTenList;
	}
}
