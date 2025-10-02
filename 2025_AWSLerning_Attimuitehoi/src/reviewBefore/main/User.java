package reviewBefore.main;

import java.util.Scanner;

/*
 * ユーザー側の入力と入力の判定を行うUserDirectionクラス
 */
public class User {
	
	/**
	 * ユーザーの指す方向を格納するuserDirection
	 * コンソールに表示する絵文字を格納するuserDisplay
	 */
	private String userDirection;
	private String userDisplay;
	

	/**
	 * @return userDirection
	 */
	public String getUserDirection() {
		return userDirection;
	}


	/**
	 * @param userDirection セットする userDirection
	 */
	public void setUserDirection(String userDirection) {
		this.userDirection = userDirection;
	}


	/**
	 * @return userDisplay
	 */
	public String getUserDisplay() {
		return userDisplay;
	}


	/**
	 * @param userDisplay セットする userDisplay
	 */
	public void setUserDisplay(String userDisplay) {
		this.userDisplay = userDisplay;
	}


	/**
	 * ユーザー側の入力を行うinputUserDirectionメソッド
	 */
	public void inputUserDirection() {
		//Scannerクラスのインスタンス化を行う
		Scanner scanner = new Scanner(System.in);
		//入力を促すメッセージ
		System.out.println("あなたの指す方向を選んでください。:(上/右/下/左)");
		//ユーザー側の入力
		userDirection = scanner.nextLine();	
		scanner.close();
	}
	
	
	/**
	 * ユーザー側の入力の判定を行うinputUserDirectionメソッド
	 */
	public void judgeUserDirection(){
		switch(userDirection) {
		case "上":
		userDisplay = "☝";
		break;
		
		case "右":
		userDisplay = "☞";
		break;
		
		case "下":
		userDisplay = "☟";
		break;
		
		case "左":
		userDisplay = "☜";
		break;
		
		default:
		System.out.println("指定されているものを入力してください");
		System.exit(0);		//システムの正常終了
		}
		
		
	}
	
}
