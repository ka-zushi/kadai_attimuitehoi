package attimuitehoi.main;

import java.time.LocalDate;

import attimuitehoi.management.JudgeResult;
import attimuitehoi.model.Enemy;
import attimuitehoi.model.Player;
import attimuitehoi.util.GameResultWriter;

/**
 * 「あっちむいてほい」を実行するクラス
 */
public class GameLauncher {
	/*
	 * mainメソッド
	 */
	public static void main(String[] args) {

		//クラスのインスタンス化
		Player player = new Player();
		Enemy enemy = new Enemy();
		JudgeResult judgeResult = new JudgeResult(player, enemy);
		GameResultWriter gameResultWriter = new GameResultWriter(player,enemy, judgeResult);
		LocalDate localDate = LocalDate.now();

		boolean playAgainFlag = true; 
		
		while(playAgainFlag) {
		//開始の合図
		System.out.println("「あっち向いて、、、」");

		//ユーザー側の入力を行う
		player.changePlayerDirection();

		//対戦相手の方向
		enemy.inputEnemyDirection();
		enemy.changeEnemyDirection();


		//勝敗の判定を行う
		judgeResult.getJudgeResult();

		//コンソールに出力
		System.out.println(); //空白
		System.out.println("日付" + localDate);
		System.out.println("「ほいっ！」");
		System.out.println("相手: " + enemy.getEnemyDisplay());
		System.out.println("あなた: " + player.getPlayerDisplay());
		System.out.println("結果:" + judgeResult.getResult());

		//ファイルに対戦情報の書き込みを行う
		gameResultWriter.inputFile();
		

		System.out.println();
		System.out.println("続けて対戦を行う場合は「y」を入力してください。");
		String inputPlayAgain = player.getScanner().nextLine();
		if(inputPlayAgain.equals("y")) {
			System.out.println();
			System.out.println("続けて対戦を行います。");
			System.out.println();
		}else {
			System.out.println();
			System.out.println("あっちむいてほいを終了します。");
			playAgainFlag = false;
		}
		}
		// Scannerを閉じる
		player.getScanner().close();
		
	}
}