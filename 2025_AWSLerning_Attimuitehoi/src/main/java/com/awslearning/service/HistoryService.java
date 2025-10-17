package com.awslearning.service;

import java.sql.SQLException;
import java.util.List;

import com.awslearning.dto.MatchTenHistoryDto;
import com.awslearning.dto.MaxWinningStreakDto;
import com.awslearning.dto.MonthlyResultDto;
import com.awslearning.repository.MatchTenHistoryDao;
import com.awslearning.repository.MaxWinningStreakDao;
import com.awslearning.repository.MonthlyResultDao;

/**
 * 対戦履歴に対するサービスを提供する
 */
public class HistoryService {

	/**
	 * 過去10回の対戦履歴を表示するメソッド
	 * @throws SQLException データベース接続に失敗した場合
	 */
	public void showMatchTenResult() throws SQLException {

		MatchTenHistoryDao matchTenHistoryDao = new MatchTenHistoryDao();
		
		System.out.println("過去10回分の対戦結果を表示いたします。");
		System.out.println();

		//過去10回の対戦履歴が格納されたListを受け取る
		List<MatchTenHistoryDto> tenResultList = matchTenHistoryDao.getMatchTenResults();

		for (MatchTenHistoryDto tenResults : tenResultList) {
			System.out.println("対戦日:" + tenResults.getMatchDate() + "　結果:" + tenResults.getResult());
		}

	}

	/**
	 * 月ごとの対戦成績を表示するメソッド
	 * @throws SQLException データベース接続に失敗した場合
	 */
	public void showMonthlyResult() throws SQLException {

		MonthlyResultDao monthlyResultDao = new MonthlyResultDao();

		System.out.println();
		System.out.println("月ごとの対戦結果を表示します。");
		System.out.println();

		//月ごとの対戦成績が格納されたListを受け取る
		List<MonthlyResultDto> monthlyResultList = monthlyResultDao.getMonthlyResults();

		for (MonthlyResultDto monthlyResults : monthlyResultList) {
			System.out.println(monthlyResults.getYear() + "-" + monthlyResults.getMonth());
			System.out.println(monthlyResults.getPlayCount() + "戦" + monthlyResults.getWinTotal() + "勝");
			System.out.println();
		}

	}

	/**
	 * 過去の連勝記録を表示するメソッド
	 * @throws SQLException データベース接続に失敗した場合
	 */
	public void showMaxWinningStreak() throws SQLException {

		MaxWinningStreakDao maxWinningStreakDao = new MaxWinningStreakDao();

		int winningStreak = 0; //連勝数を格納する変数
		int maxStreak = 0; //最多連勝数を格納する変数

		//勝敗が格納されたmaxWinningStreakListを受け取る
		List<MaxWinningStreakDto> maxWinningStreakList = maxWinningStreakDao.getMaxWinningStreak();

		for (MaxWinningStreakDto List : maxWinningStreakList) {
			if (List.getResult().equals("勝利")) {
				winningStreak++;
				if (winningStreak > maxStreak) {
					maxStreak = winningStreak;
				}
			} else {
				winningStreak = 0; //負けの場合は連勝リセット
			}
		}

		System.out.println(maxStreak + " 連勝。");
	}
}
