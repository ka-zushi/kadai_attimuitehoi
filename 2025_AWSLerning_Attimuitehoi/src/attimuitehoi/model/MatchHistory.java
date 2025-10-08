package attimuitehoi.model;

/**
 * テーブルのカラム
 */

public class MatchHistory {

	private String matchDate;
	private String result;

	/**
	 * @return matchDate
	 */
	public String getMatchDate() {
		return matchDate;
	}

	/**
	 * @param matchDate セットする matchDate
	 */
	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
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

}
