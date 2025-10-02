package attimuitehoi.model;

import java.util.Scanner;

public class Player {
	Direction[] direction = Direction.values();
	private String playerDirection;
	private String playerDisplay;
	private Scanner scanner = new Scanner(System.in);

	/**
	 * @return userDirection
	 */
	public String getPlayerDirection() {
		return playerDirection;
	}

	/**
	 * @return userDisplay
	 */
	public String getPlayerDisplay() {
		return playerDisplay;
	}

	/**
	 * @param userDisplay セットする userDisplay
	 */
	public void setPlayerDisplay(String playerDisplay) {
		this.playerDisplay = playerDisplay;
	}

	/**
	 * @return scanner
	 */
	public Scanner getScanner() {
		return scanner;
	}

	/**
	 * ユーザー側の入力を行うメソッド
	 * @return [true] 
	 */
	public boolean inputPlayerDirection() {
		System.out.println("あなたの指す方向を選んでください。:(w(☝)/a(☜)/s(☟)/d(☞))");
		while(true) {
		playerDirection = scanner.nextLine();
		for (Direction dir : direction) {
			if (playerDirection.equals(dir.toString())) {
				return true;
			}
		}
		// 不正な入力だった場合
		System.out.println("入力が正しくありません。もう一度入力してください。:(w(☝)/a(☜)/s(☟)/d(☞))");
	}
	}

	/**
	 * ユーザー側の入力をもとに文字を絵文字に変換を行うメソッド
	 */
	public void changePlayerDirection() {

		if (inputPlayerDirection()) {
			switch (getPlayerDirection()) {
			case "w":
				setPlayerDisplay("☝");
				break;
			case "a":
				setPlayerDisplay("☜");
				break;
			case "s":
				setPlayerDisplay("☟");
				break;
			case "d":
				setPlayerDisplay("☞");
				break;
			}
		}
	}

}
