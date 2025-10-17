package com.awslearning.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

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

		Connection conn = DbUtil.getConnection();

		int countRows = 0;

		try {

			if (conn != null) {
				LocalDate today = LocalDate.now(); // 今日の日付（例: 2025-09-05）
				Date sqlDate = Date.valueOf(today); // SQL用の日付に変換

				//対戦結果をテーブルに保存するINSERT文を作成
				String insert = "INSERT INTO match_history (MATCH_DATE, ENEMY_DIRECTION, PLAYER_DIRECTION, RESULT) VALUES (?, ?, ?, ?)";

				//ステートメントを生成
				PreparedStatement pstmt = conn.prepareStatement(insert);

				pstmt.setDate(1, sqlDate);
				pstmt.setString(2, enemy.getEnemyDisplay());
				pstmt.setString(3, player.getPlayerDisplay());
				pstmt.setString(4, judge.getResult());

				//更新を行った行数をcountRowsに格納
				countRows = pstmt.executeUpdate();

				System.out.println("テーブルに" + countRows + " 行が挿入されました");
				System.out.println();

				//commitを行う
				DbUtil.commit(conn);
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