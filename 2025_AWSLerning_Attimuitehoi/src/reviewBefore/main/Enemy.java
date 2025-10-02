package reviewBefore.main;


import java.util.Random;

/**
 * 対戦相手の情報を持つEnemyクラス
 */
public class Enemy {
	/**
	 * 対戦相手の向きを格納するenemyDirection
	 * 対戦相手の向く方向を格納した配列
	 */
	private String enemyDirection;
	private String[] enemyes = { "上", "右", "下", "左" };

	/**
	 * @return enemy
	 */
	public String[] getEnemyes() {
		return enemyes;
	}


	/**
	 * @param enemy セットする enemy
	 */
	public void setEnemy(String[] enemy) {
		this.enemyes = enemy;
	}


	/**
	 * @return enemyDirection
	 */
	public String getEnemyDirection() {
		return enemyDirection;
	}


	/**
	 * @param enemyDirection セットする enemyDirection
	 */
	public void setEnemyDirection(String enemyDirection) {
		this.enemyDirection = enemyDirection;
	}


	/**
	 * 対戦相手の入力情報をランダムに受け取るinputEnemyDirectionメソッド
	 */
	public void inputEnemyDirection() {
		//Randomクラスのインスタンス化を行う。
		Random random = new Random();
		//enemy[0～3]をランダムに取得
		enemyDirection = enemyes[random.nextInt(4)];
	}
}
