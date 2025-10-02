package attimuitehoi.model;

import java.util.Random;

/**
 * 対戦相手の方向を決めるクラス
 */
public class Enemy {
	Direction[] direction = Direction.values();
	private Direction enemyDirection;
	private String enemyDisplay;

	/**
	 * @return enemyDirection
	 */
	public Direction getEnemyDirection() {
		return enemyDirection;
	}

	/**
	 * @param direction セットする enemyDirection
	 */
	public void setEnemyDirection(Direction direction) {
		this.enemyDirection = direction;
	}

	/**
	 * @return [enemyDisplay] 
	 */
	public String getEnemyDisplay() {
		return enemyDisplay;
	}

	/**
	 * @param enemyDisplay セットする enemyDisplay
	 */
	public void setEnemyDisplay(String enemyDisplay) {
		this.enemyDisplay = enemyDisplay;
	}

	/**
	 * 対戦相手の方向をランダムに定義するメソッド
	 */
	public void inputEnemyDirection() {
		//Randomクラスのインスタンス化
		Random random = new Random();
		//directionType[0～3]をランダムに定義
		setEnemyDirection(direction[random.nextInt(4)]);
	}

	/**
	 * 対戦相手の入力情報を
	 */
	public void changeEnemyDirection() {
		switch (getEnemyDirection()) {
		case w:
			setEnemyDisplay(Direction.w.getDisplaySymbol());
			return;
		case a:
			setEnemyDisplay(Direction.a.getDisplaySymbol());
			return;
		case s:
			setEnemyDisplay(Direction.s.getDisplaySymbol());
			return;
		case d:
			setEnemyDisplay(Direction.d.getDisplaySymbol());
			return;
		}
	}

}
