package com.awslearning.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import com.awslearning.model.Enemy;
import com.awslearning.model.Judge;
import com.awslearning.model.Player;

/**
 * DBへの保存を行うクラス
 */
public class MatchResultDao {
	// 取得した対戦履歴をリストに格納するため用意

	/**
	 * テーブルに対戦結果をインサートする
	 * @return [countRows] INSERTを行った行数
	 * @param player プレイヤーの方向が格納されている変数(playerDsplay)があるクラス
	 * @param enemy 対戦相手の方向が格納されている変数(enemyDsplay)があるクラス
	 * @param judge 対戦結果が格納されている変数(return)があるクラス
	 * @throws SQLException データベース接続に失敗した場合	
	 */
	public void insertMatchResult(Player player, Enemy enemy, Judge judge) throws SQLException {

		//DbUtil.getConnection();を使用し、オートコミットモードをオフでコネクションの取得を行う。
		Connection conn = DbUtil.getConnection();

		//対戦結果をテーブルに保存するINSERT文
		final String insert = "INSERT INTO match_history (MATCH_DATE, MATCH_TIME, ENEMY_DIRECTION, PLAYER_DIRECTION, RESULT) VALUES (?, ?, ?, ?, ?)";

		try {

			if (conn != null) {
				//ステートメントを生成
				try (PreparedStatement pstmt = conn.prepareStatement(insert);) {
					LocalDate today = LocalDate.now(); // 今日の日付（例: 2025-09-05）
					Date sqlDate = Date.valueOf(today); // SQL用の日付に変換
					LocalTime now = LocalTime.now(); //現在の時刻

					//カラムに各項目を指定
					pstmt.setDate(1, sqlDate);
					pstmt.setObject(2, now);
					pstmt.setString(3, enemy.getEnemyDisplay());
					pstmt.setString(4, player.getPlayerDisplay());
					pstmt.setString(5, judge.getResult());

					//ステートメントの実行
					pstmt.executeUpdate();

					System.out.println();
					System.out.println("DBに対戦情報を保存しました");

					//ステートメントを閉じる
					pstmt.close();

					//commitを行う
					DbUtil.commit(conn);
				}
			}
		} catch (Exception e) {
			//ロールバックを行う
			DbUtil.rollback(conn);
			e.printStackTrace();
		} finally {
			//コネクションを閉じる
			DbUtil.close(conn);
		}
	}
}