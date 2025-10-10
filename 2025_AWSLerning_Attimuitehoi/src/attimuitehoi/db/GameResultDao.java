package attimuitehoi.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import attimuitehoi.management.Judge;
import attimuitehoi.model.Enemy;
import attimuitehoi.model.Player;

/**
 * DBへの保存
 */
public class GameResultDao {

	/**
	 * テーブルに対戦結果をインサートする
	 * @param player
	 * @param enemy
	 * @param judge
	 * @throws SQLException データベース接続に失敗した場合	
	 */
	public void insertMatchResult(Player player, Enemy enemy, Judge judge) throws SQLException {

		Connection conn = DbUtil.getConnection();
		try {

			if (conn != null) {
				LocalDate today = LocalDate.now(); // 今日の日付（例: 2025-09-05）
				Date sqlDate = Date.valueOf(today); // SQL用の日付に変換

				//対戦結果をテーブルに保存するINSERT文を作成
				String insert = "INSERT INTO match_results (MATCH_DATE, ENEMY_DIRECTION, PLAYER_DIRECTION, RESULT) VALUES (?, ?, ?, ?)";

				//ステートメントを生成
				PreparedStatement pstmt = conn.prepareStatement(insert);
				pstmt.setDate(1, sqlDate);
				pstmt.setString(2, enemy.getEnemyDisplay());
				pstmt.setString(3, player.getPlayerDisplay());
				pstmt.setString(4, judge.getResult());

				int countRows = pstmt.executeUpdate();
				System.out.println("テーブルに" + countRows + " 行が挿入されました");
				System.out.println();
				//commitを行う
				DbUtil.commit(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
			//ロールバックを行う
			DbUtil.rollback(conn);
		} finally {
			DbUtil.close(conn);
		}

	}
}