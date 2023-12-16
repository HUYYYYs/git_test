package com.entity;

public class Users {
	static final long serialVersionUID = 1;
	private Integer userId;
	private String name;
	private String ico;
	private int money;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIco() {
		return ico;
	}
	public void setIco(String ico) {
		this.ico = ico;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public Users() {}
	public Users(String name, String ico, int money) {
		this.name = name;
		this.ico = ico;
		this.money = money;
	}
	
}	
