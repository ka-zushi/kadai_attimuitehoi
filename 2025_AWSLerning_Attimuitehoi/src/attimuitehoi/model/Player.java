package attimuitehoi.model;

import java.util.Scanner;

/**
 * プレイヤーの入力・絵文字への変換を行うクラス
 */
public class Player {
	Direction[] direction = Direction.values();
	private String playerDirection;
	private String playerDisplay;
	private Scanner scanner = new Scanner(System.in);

	/**
	 * @return [userDirection] プレイヤーの方向(w.a.s.d)
	 */
	public String getPlayerDirection() {
		return playerDirection;
	}

	/**
	 * @return [userDisplay] ユーザーの方向(☝,☜,☟,☞)
	 */
	public String getPlayerDisplay() {
		return playerDisplay;
	}

	/**
	 * @return [scanner] Sccanerクラスのインスタンス
	 */
	public Scanner getScanner() {
		return scanner;
	}

	/**
	 * ユーザー側の入力を行うメソッド
	 * @return [true] プレイヤーの入力が正しい
	 */
	public boolean inputPlayerDirection() {
		System.out.println("あなたの指す方向を選んでください。:(w(☝)/a(☜)/s(☟)/d(☞))");
		while (true) {
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
	 * ユーザー側の入力を行い、入力をもとに文字(w.a.s.d)を対象の絵文字(☝,☜,☟,☞)に変換する
	 */
	public void changePlayerDirection() {

		if (inputPlayerDirection()) {
			switch (getPlayerDirection()) {
			case "w":
				playerDisplay = "☝";
				break;
			case "a":
				playerDisplay = "☜";
				break;
			case "s":
				playerDisplay = "☟";
				break;
			case "d":
				playerDisplay = "☞";
				break;
			}
		}
	}

}
