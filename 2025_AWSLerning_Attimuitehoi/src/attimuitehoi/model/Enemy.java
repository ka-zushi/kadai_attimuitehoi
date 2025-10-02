package attimuitehoi.model;

import java.util.Random;

/**
 * 対戦相手の方向を決める・絵文字への変換を行うクラス
 */
public class Enemy {
	Direction[] direction = Direction.values();
	private Direction enemyDirection;
	private String enemyDisplay;

	/**
	 * @return [enemyDirection] 対戦相手の方向(w.a.s.d)
	 */
	public Direction getEnemyDirection() {
		return enemyDirection;
	}

	/**
	 * @param direction Direction型の配列
	 */
	public void setEnemyDirection(Direction direction) {
		this.enemyDirection = direction;
	}

	/**
	 * @return [enemyDisplay] 対戦相手の方向(↑,←,↓,→)
	 */
	public String getEnemyDisplay() {
		return enemyDisplay;
	}

	/**
	 * 対戦相手の方向をランダムで定義する
	 */
	public void inputEnemyDirection() {
		Random random = new Random();
		setEnemyDirection(direction[random.nextInt(4)]);
	}

	/**
	 * 対戦相手の方向を絵文字へ変換する
	 */
	public void changeEnemyDirection() {
		switch (getEnemyDirection()) {
		case w:
			enemyDisplay = "↑";
			break;
		case a:
			enemyDisplay = "←";
			break;
		case s:
			enemyDisplay = "↓";
			break;
		case d:
			enemyDisplay = "→";
			break;
		}
	}

}
