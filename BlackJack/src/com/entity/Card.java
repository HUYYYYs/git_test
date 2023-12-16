package com.entity;

public class Card {
	static final long serialVersionUID = 1;
	private String suit;
	private String name;
	private int value;
	private String ico;

	public Card(String s,String n,int v){
		suit = s;
		name = n;
		value = v;
		ico = this.toString() + ".png";
	}
	
	public String getName(){
		return name;
	}
	
	public String getIco(){
		return this.ico;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String toString(){
		return this.suit + this.name; 
	}
	
}
