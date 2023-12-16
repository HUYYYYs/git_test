package com.entity;

import java.util.Date;

public class Records {
	private Integer id;
	private String playName;//玩家
	private Integer numbers;//赌注
	private Integer money;//赢的钱
	private String state;
	private Date addTime;
	private String againUser;//对手
	public Records() {}
	public Records(String playName, Integer numbers, Integer money, String state,
			String againUser) {
		this.playName = playName;
		this.numbers = numbers;
		this.money = money;
		this.state = state;
		this.againUser = againUser;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPlayName() {
		return playName;
	}
	public void setPlayName(String playName) {
		this.playName = playName;
	}
	public Integer getNumbers() {
		return numbers;
	}
	public void setNumbers(Integer numbers) {
		this.numbers = numbers;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public String getAgainUser() {
		return againUser;
	}
	public void setAgainUser(String againUser) {
		this.againUser = againUser;
	}
	
}
