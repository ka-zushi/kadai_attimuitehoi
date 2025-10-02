package reviewBefore.main;


/**
 * 勝敗の判定を行うJudgeResultクラス
 */
public class JudgeResult {
	/*
	 * 勝敗の結果を格納するresult変数
	 * Userクラスのインスタンスを格納するuser
	 * Eserクラスのインスタンスを格納するenemy
	 */
	private String result;
    private User user;
    private Enemy enemy;

    /*
     * コンストラクタの定義
     */
    public JudgeResult(User user, Enemy enemy) {
        this.user = user;
        this.enemy = enemy;
    }



	/**
	 * @return result
	 */
	public String getResult() {
		return result;
	}



	/**
	 * @param result セットする result
	 */
	public void setResult(String result) {
		this.result = result;
	}



	/**
	 * 勝敗の判定を行うgetJudgeResultメソッド
	 */
	public void getJudgeResult() {
		//UserDirectionクラスのインスタンス化
		if (user.getUserDirection().equals(enemy.getEnemyDirection())) {
            result = "勝利";
        } else {
            result = "敗北";
        }

	}
}
