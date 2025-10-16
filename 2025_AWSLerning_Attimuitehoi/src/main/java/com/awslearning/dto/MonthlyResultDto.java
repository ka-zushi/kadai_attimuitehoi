package com.awslearning.dto;

/**
 * 月ごとの対戦成績を格納するDTO
 */
public class MonthlyResultDto {

	private int year; //対戦年
	private int month; //対戦月
	private int playCount; //対戦回数
	private int winTotal; //勝利数

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getPlayCount() {
		return playCount;
	}

	public void setPlayCount(int playCount) {
		this.playCount = playCount;
	}

	public int getWinTotal() {
		return winTotal;
	}

	public void setWinTotal(int winTotal) {
		this.winTotal = winTotal;
	}

}
