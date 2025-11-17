package com.awslearning.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.awslearning.model.Direction;
import com.awslearning.model.Enemy;
import com.awslearning.model.Judge;
import com.awslearning.model.Player;
import com.awslearning.repository.MatchResultDao;
import com.awslearning.util.GameResultWriter;

/**
 * あっちむいてほいを行うサービスを提供する
 */
public class AttimuitehoiService {
	GameResultWriter gameResultWriter = new GameResultWriter();
	MatchResultDao matchResultDao = new MatchResultDao();
	HistoryService historyService = new HistoryService();
	LocalDate localDate = LocalDate.now();
	Scanner scanner = new Scanner(System.in);
	Direction[] direction = Direction.values();
	Enemy enemy = new Enemy();
	Player player = new Player();
	Judge judge = new Judge();
	
	private static final Logger logger = LogManager.getLogger(AttimuitehoiService.class);

	/**
	 * あっちむいてほいを行うメソッド
	 * @throws SQLException データベース接続に失敗した場合
	 */
	public void startAttimuitehoi() throws SQLException {
		boolean playAgainFlag = true;

		logger.info("日付: " + localDate);
		logger.info("あっちむいてほいを行います!");
		System.out.println();

		while (playAgainFlag) {
			//ユーザー側の入力を行う
			changePlayerDirection();

			//対戦相手の方向を定義
			changeEnemyDirection();

			//勝敗の判定を行う
			judgeResult(player, enemy);

			//コンソールに出力
			System.out.println(); //空白
			logger.info("「あっち向いてほいっ！」");
			logger.info("相手: " + enemy.getEnemyDisplay());
			logger.info("あなた: " + player.getPlayerDisplay());
			logger.info("結果:" + judge.getResult());
			System.out.println();

			//ファイルに対戦情報の書き込みを行う
			gameResultWriter.inputFile(player, enemy, judge);

			//dbを更新
			matchResultDao.insertMatchResult(player, enemy, judge);

			//過去10回分の対戦結果を表示
			historyService.showMatchTenResult();

			//月ごとの対戦成績を表示
			historyService.showMonthlyResult();

			//最多連勝記録を表示
			historyService.showMaxWinningStreak();

			//対戦を終了するかを選択
			System.out.println();
			logger.info("続けて対戦を行う場合は「y」を入力してください。");

			String inputPlayAgain = scanner.nextLine();

			if (inputPlayAgain.equals("y")) {
				System.out.println();
				logger.info("続けて対戦を行います。");
				System.out.println();
			} else {
				System.out.println();
				logger.info("「y」以外が入力されたのであっちむいてほいを終了します。");
				playAgainFlag = false;
			}
		}
		scanner.close();
	}

	/**
	 *  対戦相手の方向をランダムで定義し、絵文字に変換するメソッド
	 *  @return [EnemyDisplay]
	 */
	public void changeEnemyDirection() {
		Random random = new Random();

		enemy.setEnemyDirection(direction[random.nextInt(4)]);

		switch (enemy.getEnemyDirection()) {
		case w:
			enemy.setEnemyDisplay("↑");
			break;
		case a:
			enemy.setEnemyDisplay("←");
			break;
		case s:
			enemy.setEnemyDisplay("↓");
			break;
		case d:
			enemy.setEnemyDisplay("→");
			break;
		}
	}

	/**
	 * ユーザー側の入力を行うメソッド
	 * @return [true] プレイヤーの入力が正しい
	 */
	public boolean inputPlayerDirection() {

		System.out.println("あなたの指す方向を選んでください。:(w(☝)/a(☜)/s(☟)/d(☞))");

		while (true) {
			String input = scanner.nextLine();

			player.setPlayerDirection(input);
			for (Direction dir : direction) {
				if (player.getPlayerDirection().equals(dir.toString())) {
					return true;
				}
			}
			// 不正な入力だった場合
			System.out.println("入力が正しくありません。もう一度入力してください。:(w(☝)/a(☜)/s(☟)/d(☞))");
		}
	}

	/**
	 * ユーザー側の入力を行い、入力をもとに文字(w.a.s.d)を対象の絵文字(☝,☜,☟,☞)に変換する
	 */
	public void changePlayerDirection() {

		if (inputPlayerDirection()) {
			switch (player.getPlayerDirection()) {
			case "w":
				player.setPlayerDisplay("☝");
				break;
			case "a":
				player.setPlayerDisplay("☜");
				break;
			case "s":
				player.setPlayerDisplay("☟");
				break;
			case "d":
				player.setPlayerDisplay("☞");
				break;
			}
		}
	}

	/**
	 * 引数をもとに勝敗の判定を行う
	 * @param player プレイヤーの方向が格納されている変数(playerDirection)があるクラス
	 * @param enemy 対戦相手の方向が格納されている変数(enemyDirection)があるクラス
	 */
	public void judgeResult(Player player, Enemy enemy) {
		if (player.getPlayerDirection().equals(enemy.getEnemyDirection().toString())) {
			judge.setResult("勝利");
		} else {
			judge.setResult("敗北");
		}

	}
}
