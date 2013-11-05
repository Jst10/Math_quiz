package com.my.math_quiz.utilist;

public class Equations {
	String text;
	int result;
	public Equations(String text,int result){
		setData(text, result);
	}
	public void setData(String text,int result){
		this.text=text;
		this.result=result;
	}
	public int getValue(){
		return result;
	}
	public String getText(){
		return text;
	}
	public void addBracked() {
		text="("+text+")";
		
	}
}