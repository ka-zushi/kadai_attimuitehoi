package attimuitehoi.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import attimuitehoi.management.JudgeResult;
import attimuitehoi.model.Enemy;
import attimuitehoi.model.Player;

public class GameResultWriter {

	/*
	 * Userクラスのインスタンスを格納するuser
	 * Eserクラスのインスタンスを格納するenemy
	 * JudgeResultクラスのインスタンスを格納するjudgeResult
	 */
	private Player player;
	private Enemy enemy;
	private JudgeResult judgeResult;

	/*
	 * コンストラクタ
	 */
	public GameResultWriter(Player player, Enemy enemy, JudgeResult judgeResult) {
		this.player = player;
		this.enemy = enemy;
		this.judgeResult = judgeResult;
	}

	/*
	 * ファイルに書き込みを行うinputFileメソッド
	 */

	public void inputFile() {
		LocalDate localDate = LocalDate.now();
		try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(
				new FileWriter("C:\\AWS勉強会\\課題1\\Q1_log.txt", true)))) {

			printWriter.println("日付：" + localDate);
			printWriter.println("相手：" + enemy.getEnemyDisplay());
			printWriter.println("プレイヤー：" + player.getPlayerDisplay());
			printWriter.println("結果:" + judgeResult.getResult());
			printWriter.println();

		} catch (IOException e) {
			System.out.println("対戦結果の保存にてエラーが発生しました。");
			e.printStackTrace(); 
		}
	}

}
