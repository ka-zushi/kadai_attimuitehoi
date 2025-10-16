package com.awslearning.dto;

import java.sql.Date;

/**
 * 過去10回文の対戦結果を格納するDto
 */
public class MatchTenHistoryDto {
	private Date matchDate; //対戦日
	private String result; //対戦結果

	public Date getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(Date date) {
		this.matchDate = date;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
