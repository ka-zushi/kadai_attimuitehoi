package com.awslearning.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.awslearning.entity.MatchHistoryTbl;

public class MatchTenHistoryDao {
	/**
	 * * 過去10回分の対戦結果をsql文で抽出し、結果をListに格納するメソッド
	 * @return [matchTenList] 過去10回の対戦履歴をリストに格納したもの。
	 * @throws SQLException データベース接続に失敗した場合	
	 */
	public List<MatchHistoryTbl> getMatchTenResults() throws SQLException {

		// 取得した対戦履歴をリストに格納するため用意
		List<MatchHistoryTbl> matchTenList = new ArrayList<MatchHistoryTbl>();

		final String tenResultSql = "SELECT MATCH_DATE, MATCH_TIME, RESULT FROM match_history ORDER BY ID DESC LIMIT 10;";

		//コネクションの取得
		try (Connection conn = DbUtil.getConnection()) {
			if (conn != null) {
				//ステートメントの生成及び、ResultSetのインスタンス化
				try (Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery(tenResultSql);) {

					//データーベースから結果を取得
					while (rs.next()) {

						//MatchTenHistoryDtoをインスタンス化
						MatchHistoryTbl matchTenHistorytbl = new MatchHistoryTbl();

						//抽出した対戦履歴をMatchHistoryTblに一時格納
						matchTenHistorytbl.setMatchDate(rs.getDate("MATCH_DATE"));
						matchTenHistorytbl.setMatchTime(rs.getTime("MATCH_TIME"));
						matchTenHistorytbl.setResult(rs.getString("RESULT"));

						//一時格納したデータをmatchTenListに格納
						matchTenList.add(matchTenHistorytbl);

					}

					if (matchTenList.size() < 10) {
						System.out.println("過去にあっち向いてほいが" + matchTenList.size() + "回しか行われていませんでした。");
					}
				}
			}
		}
		return matchTenList;
	}
}
