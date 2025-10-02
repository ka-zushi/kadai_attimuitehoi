package attimuitehoi.management;

import attimuitehoi.model.Enemy;
import attimuitehoi.model.Player;

/**
 * 勝敗の判定を行うクラス
 */
public class Judge {

	private String result;

	/**
	 * resultを受け取る
	 * @return [result] 対戦結果
	 */
	public String getResult() {
		return result;
	}

	/**
	 * 引数をもとに勝敗の判定を行う
	 * @param player プレイヤーの方向が格納されている変数(playerDirection)があるクラス
	 * @param enemy 対戦相手の方向が格納されている変数(enemyDirection)があるクラス
	 */
	public void judgeResult(Player player, Enemy enemy) {
		if (player.getPlayerDirection().equals(enemy.getEnemyDirection().toString())) {
			result = "勝利";
		} else {
			result = "敗北";
		}

	}
}