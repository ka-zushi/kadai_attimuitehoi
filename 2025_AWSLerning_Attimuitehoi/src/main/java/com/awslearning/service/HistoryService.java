package com.awslearning.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.awslearning.dto.MonthlyResultDto;
import com.awslearning.entity.MatchHistoryTable;
import com.awslearning.repository.MatchTenHistoryDao;
import com.awslearning.repository.MaxWinningStreakDao;
import com.awslearning.repository.MonthlyResultDao;

/**
 * 対戦履歴に対するサービスを提供する
 */
public class HistoryService {

	private static final Logger logger = LogManager.getLogger(HistoryService.class);
	
	/**
	 * List<MatchHistoryTbl>を使用し、過去10回の対戦履歴を表示するメソッド
	 * @throws SQLException データベース接続に失敗した場合
	 */
	public void showMatchTenResult() throws SQLException {

		MatchTenHistoryDao matchTenHistoryDao = new MatchTenHistoryDao();

		//過去10回の対戦履歴が格納されたListを受け取る
		List<MatchHistoryTable> tenResultList = matchTenHistoryDao.getMatchTenResults();

		logger.info("過去"+ tenResultList.size() + "回分の対戦結果を表示いたします。");
		System.out.println();
		
		for (MatchHistoryTable tenResults : tenResultList) {
			logger.info("対戦日時:" + tenResults.getMatchDate() + "　 "+ tenResults.getMatchTime() + "　結果:"
					+ tenResults.getResult());
		}

	}

	/**
	 * List<MonthlyResultDto>を使用し、月ごとの対戦成績を表示するメソッド
	 * @throws SQLException データベース接続に失敗した場合
	 */
	public void showMonthlyResult() throws SQLException {

		MonthlyResultDao monthlyResultDao = new MonthlyResultDao();

		System.out.println();
		logger.info("月ごとの対戦結果を表示します。");
		System.out.println();

		//月ごとの対戦成績が格納されたListを受け取る
		List<MonthlyResultDto> monthlyResultList = monthlyResultDao.getMonthlyResults();

		for (MonthlyResultDto monthlyResults : monthlyResultList) {
			logger.info(monthlyResults.getYear() + "年" + monthlyResults.getMonth() + "月");
			logger.info(monthlyResults.getPlayCount() + "戦" + monthlyResults.getWinTotal() + "勝");
			System.out.println();
		}

	}

	/**
	 * List<MatchHistoryTbl>から過去の連勝記録を求め、表示するメソッド
	 * @throws SQLException データベース接続に失敗した場合
	 */
	public void showMaxWinningStreak() throws SQLException {

		MaxWinningStreakDao maxWinningStreakDao = new MaxWinningStreakDao();

		int winningStreak = 0; //連勝数を格納する変数
		int maxStreak = 0; //最多連勝数を格納する変数

		//勝敗が格納されたmaxWinningStreakListを受け取る
		List<MatchHistoryTable> maxWinningStreakList = maxWinningStreakDao.getMaxWinningStreak();

		//最多連勝数を求める処理
		for (MatchHistoryTable List : maxWinningStreakList) {
			if (List.getResult().equals("勝利")) {
				winningStreak++;
				if (winningStreak > maxStreak) {
					maxStreak = winningStreak;
				}
			} else {
				winningStreak = 0; //負けの場合は連勝リセット
			}
		}
		logger.info("最多連勝記録は" + maxStreak + "連勝です。");
	}
}
