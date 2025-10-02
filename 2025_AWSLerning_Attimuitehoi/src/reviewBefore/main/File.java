package reviewBefore.main;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

/*
 * ファイルに対する処理を行うFileクラス
 */
public class File {

	/*
	 * Userクラスのインスタンスを格納するuser
	 * Eserクラスのインスタンスを格納するenemy
	 * JudgeResultクラスのインスタンスを格納するjudgeResult
	 */
    private User user;
    private Enemy enemy;
    private JudgeResult judgeResult;

    /*
     * コンストラクタ
     */
    public File(User user, Enemy enemy, JudgeResult judgeResult) {
        this.user = user;
        this.enemy = enemy;
        this.judgeResult = judgeResult;
    }

	/*
	 * ファイルに書き込みを行うinputFileメソッド
	 */
	public void inputFile() {
		try {

			// FileWriterクラスのオブジェクトを生成。ファイルの絶対パスの指定と、「true」で「追記」を指定。
			FileWriter fileWriter = new FileWriter("C:\\AWS勉強会\\課題1\\Q1_log.txt", true);
			// PrintWriterクラスとBufferedWriterをインスタンス化
			PrintWriter printWriter = new PrintWriter(new BufferedWriter(fileWriter));
			// LocalDateをインスタンス化(当日の日付を取得)
			LocalDate localDate = LocalDate.now();
			// 日付をQ1_log.txtに出力
			printWriter.println("日付：" + localDate);
			//対戦情報をQ1_log.txtに出力
			printWriter.println("相手：" + enemy.getEnemyDirection());
			printWriter.println("プレイヤー：" + user.getUserDirection());
			//対戦結果をQ1_log.txtに出力
			printWriter.println("結果:" + judgeResult.getResult());

			printWriter.println();
			//printWriterを閉じる
			printWriter.close();

		} catch (IOException e) {
			System.out.println("対戦結果の保存にてエラーが発生しました。");
		}
	}
}
