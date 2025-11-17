package com.awslearning.entity;

import java.sql.Date;
import java.sql.Time;

public class MatchHistoryTable {
	/**
	 * あっちむいてほいの履歴テーブルのエンティティクラス
	 */

	private Date matchDate; //対戦日
	private Time matchTime; //対戦時刻
	private String result; //対戦結果

	public Date getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}

	public Time getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(Time matchTime) {
		this.matchTime = matchTime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
