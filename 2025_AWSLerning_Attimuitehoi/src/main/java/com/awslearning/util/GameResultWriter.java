package com.awslearning.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import com.awslearning.model.Enemy;
import com.awslearning.model.Judge;
import com.awslearning.model.Player;

/**
 * ファイル操作に対するユーティリティークラス
 */
public class GameResultWriter {

	/**
	 * 対戦結果をファイルへ記録する
	 * @param player プレイヤーの方向が格納されている変数(playerDirection)があるクラス
	 * @param enemy 対戦相手の方向が格納されている変数(enemyDirection)があるクラス
	 * @param judge 対戦結果が格納されている変数(return)があるクラス
	 */
	public void inputFile(Player player, Enemy enemy, Judge judge) {
		LocalDate localDate = LocalDate.now();
		try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(
				new FileWriter("C:\\AWS勉強会\\課題1\\Q1_log.txt", true)))) {

			printWriter.println("日付：" + localDate);
			printWriter.println("相手：" + enemy.getEnemyDisplay());
			printWriter.println("プレイヤー：" + player.getPlayerDisplay());
			printWriter.println("結果:" + judge.getResult());
			printWriter.println();

		} catch (IOException e) {
			System.out.println("対戦結果の保存にてエラーが発生しました。");
			e.printStackTrace();
		}
	}

}
