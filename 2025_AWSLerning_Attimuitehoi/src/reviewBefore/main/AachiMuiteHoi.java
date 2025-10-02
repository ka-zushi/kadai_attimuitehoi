package reviewBefore.main;

import java.time.LocalDate;

/*
 * 「あっちむいてほい」を実行するクラス
 * ※プレイヤーは指を動かす側
 */
public class AachiMuiteHoi {

	/*
	 * mainメソッド
	 */
	public static void main(String[] args) {
		
		//クラスのインスタンス化
		User user = new User();
		Enemy enemy = new Enemy();
		JudgeResult judgeResult = new JudgeResult(user, enemy);
		File file = new File(user, enemy, judgeResult);
		LocalDate localDate = LocalDate.now();
		
		//開始の合図
		System.out.println("「あっち向いて、、、」");
		
		//UserクラスのinputUserDirectionメソッドを使用してユーザー側の入力を行う
		user.inputUserDirection();
		//UserクラスのjudgeUserDirectionメソッドを使用してユーザー側の入力の判定を行う
		user.judgeUserDirection();
		
		//EnemyクラスのinputEnemyDirectionメソッドを使用して対戦相手の入力情報を受け取る
		enemy.inputEnemyDirection();
		
		//JudgeResultクラスのgetJudgeResultメソッドを使用して勝敗の判定を行う
		judgeResult.getJudgeResult();
		
		//コンソールに出力
		System.out.println();	//空白
		System.out.println("日付" + localDate);
		System.out.println("「ほいっ！」");
		System.out.println("相手: " + enemy.getEnemyDirection());
		System.out.println("あなた: " + user.getUserDisplay());
		System.out.println("結果:" + judgeResult.getResult());
		
		//FileクラスのinputFileメソッドを使用してファイルに対戦情報の書き込みを行う
		file.inputFile();
		
	}
}
