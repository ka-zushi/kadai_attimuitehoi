package com.awslearning.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.awslearning.dto.MonthlyResultDto;

public class MonthlyResultDao {
	/**
	 * 月ごとの対戦結果をsql文で抽出し、結果をListに格納するメソッド
	 * @return [monthlyResuktList] 月ごとの対戦成績を格納したリスト。
	 * @throws SQLException データベース接続に失敗した場合
	 */
	public List<MonthlyResultDto> getMonthlyResults() throws SQLException {

		final String resultMounth = "SELECT YEAR(MATCH_DATE) AS 年, MONTH(MATCH_DATE) AS 月, COUNT(*) AS 対戦回数,SUM(CASE WHEN RESULT = '勝利' THEN 1 ELSE 0 END) AS 勝利数 FROM match_history GROUP BY YEAR(MATCH_DATE), MONTH(MATCH_DATE);";

		// 取得した対戦履歴をリストに格納するため用意
		List<MonthlyResultDto> monthlyResuktList = new ArrayList<MonthlyResultDto>();

		try (Connection conn = DbUtil.getConnection();) {

			if (conn != null) {
				try (Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery(resultMounth);) {

					while (rs.next()) {
						MonthlyResultDto monthlyResultDto = new MonthlyResultDto();

						//抽出した対戦履歴をDtoに一時格納
						monthlyResultDto.setYear(rs.getInt("年"));
						monthlyResultDto.setMonth(rs.getInt("月"));
						monthlyResultDto.setPlayCount(rs.getInt("対戦回数"));//月ごとの対戦回数
						monthlyResultDto.setWinTotal(rs.getInt("勝利数"));//月ごとの勝利数

						//一時格納したデータをListに格納
						monthlyResuktList.add(monthlyResultDto);
					}
				}
			}
			return monthlyResuktList;
		}
	}
}
