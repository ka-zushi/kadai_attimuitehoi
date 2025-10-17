package com.awslearning.model;

/**
 * 対戦相手の情報を持つクラス
 */
public class Enemy {
	Direction[] direction = Direction.values();
	private Direction enemyDirection;
	private String enemyDisplay;

	public Direction[] getDirection() {
		return direction;
	}

	public void setDirection(Direction[] direction) {
		this.direction = direction;
	}

	public Direction getEnemyDirection() {
		return enemyDirection;
	}

	public void setEnemyDirection(Direction enemyDirection) {
		this.enemyDirection = enemyDirection;
	}

	public String getEnemyDisplay() {
		return enemyDisplay;
	}

	public void setEnemyDisplay(String enemyDisplay) {
		this.enemyDisplay = enemyDisplay;
	}

}
