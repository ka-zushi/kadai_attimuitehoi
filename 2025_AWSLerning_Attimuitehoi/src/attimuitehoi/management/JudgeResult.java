package attimuitehoi.management;

import attimuitehoi.model.Enemy;
import attimuitehoi.model.Player;

/**
 * 勝敗の判定を行うクラス
 */
public class JudgeResult {

	private String result;
	private Player player;
	private Enemy enemy;

	/*
	 * コンストラクタの定義
	 */
	public JudgeResult(Player player, Enemy enemy) {
		this.player = player;
		this.enemy = enemy;
	}

	/**
	 * @return result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result セットする result
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * 勝敗の判定を行うgetJudgeResultメソッド
	 */
	public void getJudgeResult() {
		if (player.getPlayerDirection().equals(enemy.getEnemyDirection().toString())) {
            setResult("勝利") ;
        } else {
        	setResult("敗北");
        }

	}
}