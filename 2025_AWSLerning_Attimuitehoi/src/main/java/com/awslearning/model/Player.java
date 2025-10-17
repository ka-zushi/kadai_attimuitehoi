package com.awslearning.model;

/**
 * プレイヤーの情報を持つクラス
 */
public class Player {
	Direction[] direction = Direction.values();
	private String playerDirection;
	private String playerDisplay;

	public Direction[] getDirection() {
		return direction;
	}

	public void setDirection(Direction[] direction) {
		this.direction = direction;
	}

	public String getPlayerDirection() {
		return playerDirection;
	}

	public void setPlayerDirection(String playerDirection) {
		this.playerDirection = playerDirection;
	}

	public String getPlayerDisplay() {
		return playerDisplay;
	}

	public void setPlayerDisplay(String playerDisplay) {
		this.playerDisplay = playerDisplay;
	}

}
