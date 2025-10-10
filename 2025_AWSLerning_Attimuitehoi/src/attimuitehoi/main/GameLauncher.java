package attimuitehoi.main;

import java.sql.SQLException;
import java.time.LocalDate;

import attimuitehoi.db.GameResultDao;
import attimuitehoi.db.GameResultService;
import attimuitehoi.management.Judge;
import attimuitehoi.model.Enemy;
import attimuitehoi.model.Player;
import attimuitehoi.util.GameResultWriter;

/**
 * 「あっちむいてほい」を実行するクラス
 */
public class GameLauncher {

	public static void main(String[] args) throws SQLException {

		Player player = new Player();
		Enemy enemy = new Enemy();
		Judge judge = new Judge();
		GameResultWriter gameResultWriter = new GameResultWriter();
		LocalDate localDate = LocalDate.now();
		GameResultDao gameResultDao = new GameResultDao();
		GameResultService gameResultService = new GameResultService();

		boolean playAgainFlag = true;

		System.out.println("日付: " + localDate);
		System.out.println("あっちむいてほいを行います!");
		System.out.println();
		while (playAgainFlag) {

			//ユーザー側の入力を行う
			player.changePlayerDirection();

			//対戦相手の方向を定義
			enemy.inputEnemyDirection();
			enemy.changeEnemyDirection();

			//勝敗の判定を行う
			judge.judgeResult(player, enemy);

			//コンソールに出力
			System.out.println(); //空白
			System.out.println("「あっち向いてほいっ！」");
			System.out.println("相手: " + enemy.getEnemyDisplay());
			System.out.println("あなた: " + player.getPlayerDisplay());
			System.out.println("結果:" + judge.getResult());
			System.out.println();

			//ファイルに対戦情報の書き込みを行う
			gameResultWriter.inputFile(player, enemy, judge);

			//dbを更新
			gameResultDao.insertMatchResult(player, enemy, judge);

			//過去10回分の対戦結果を表示する
			gameResultService.getLast10Results();

			//月ごとの対戦成績を表示
			gameResultService.getMonthlyResults();

			//最多連勝記録を表示
			gameResultService.getMaxWinningStreak();

			//対戦を終了するかを選択
			System.out.println();
			System.out.println("続けて対戦を行う場合は「y」を入力してください。");
			String inputPlayAgain = player.getScanner().nextLine();
			if (inputPlayAgain.equals("y")) {
				System.out.println();
				System.out.println("続けて対戦を行います。");
				System.out.println();
			} else {
				System.out.println();
				System.out.println("「y」以外が入力されたのであっちむいてほいを終了します。");
				playAgainFlag = false;
			}
		}
		// Scannerを閉じる
		player.getScanner().close();

	}
}