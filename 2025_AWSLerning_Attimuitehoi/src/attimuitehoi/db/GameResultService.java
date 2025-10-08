package attimuitehoi.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * ロジック（連勝記録や月ごとの成績の計算など）
 */
public class GameResultService {
	DbUtil dbUtil = new DbUtil();

	/**
	 * 過去10回分の対戦結果を取得する
	 */
	public void getLast10Results() {
		try (Connection conn = dbUtil.getConnection()) {

			if (conn != null) {
				Statement stmt = conn.createStatement();
				String tenResultSql = "SELECT * FROM match_results ORDER BY ID DESC LIMIT 10;";

				ResultSet rs = stmt.executeQuery(tenResultSql);
				System.out.println("過去10回分の対戦成績を表示します。");
				System.out.println();
				int dbCount = 0;
				while (rs.next()) {

					Date machDate = rs.getDate("MATCH_DATE");
					String result = rs.getString("RESULT");

					System.out.println(machDate + ":" + result);
					System.out.println();

					dbCount++;

				}
				if (dbCount > 10) {
					System.out.println("過去にあっち向いてほいが" + dbCount + "回しか行われていませんでした。");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 月ごとの対戦成績を表示
	 */
	public void getMonthlyResults() {
		try (Connection conn = dbUtil.getConnection()) {

			if (conn != null) {
				Statement stmt = conn.createStatement();

				System.out.println("月ごとの対戦結果を表示します。");
				System.out.println();

				String resultMounth = "SELECT YEAR(MATCH_DATE) AS 年, MONTH(MATCH_DATE) AS 月, COUNT(*) AS 対戦回数,SUM(CASE WHEN RESULT = '勝利' THEN 1 ELSE 0 END) AS 勝利数 FROM match_results GROUP BY YEAR(MATCH_DATE), MONTH(MATCH_DATE) ORDER BY 年 DESC, 月 DESC;";
				ResultSet rs = stmt.executeQuery(resultMounth);

				while (rs.next()) {
					int year = rs.getInt("年");
					int month = rs.getInt("月");
					int playCount = rs.getInt("対戦回数");
					int winTotal = rs.getInt("勝利数");

					System.out.println(year + "-" + month);
					System.out.println(playCount + "戦" + winTotal + "勝");
					System.out.println();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 最多連勝記録を表示
	 */
	public void getMaxWinningStreak() {
		try (Connection conn = dbUtil.getConnection()) {

			if (conn != null) {
				Statement stmt = conn.createStatement();
				
				System.out.println("最多連勝記録を表示します。");
				System.out.println("");
				
				String sqlSelect = "select result from match_results ORDER BY MATCH_DATE ASC;";
				ResultSet rs = stmt.executeQuery(sqlSelect);

				int winningStreak = 0;
				int maxStreak = 0;

				while (rs.next()) {

					String result = rs.getString("RESULT");

					if (result.equals("勝利")) {
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
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
