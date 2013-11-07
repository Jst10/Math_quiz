package com.my.math_quiz.database;

import java.util.Date;
import java.util.Calendar;

public class LevelEntity {


	private int id;
	
	private int level;
	private int numberOfGames;
	private int timeInMIliseconds;
	private int score;
	
	private Date date;
	
	public LevelEntity(int id,int level,int numberOfGames,int timeInMiliseconds, int score,Date date){
		this.id=id;
		setData(level, numberOfGames, timeInMiliseconds, score,date);
	}
	
	public LevelEntity(int level,int numberOfGames,int timeInMiliseconds, int score,Date date){
		setData(level, numberOfGames, timeInMiliseconds, score,date);
	}
	
	public void setData(int level,int numberOfGames,int timeInMiliseconds, int score,Date date){
		if(date==null)
			date=Calendar.getInstance().getTime();
		else
			this.date=date;
		this.level=level;
		this.numberOfGames=numberOfGames;
		this.timeInMIliseconds=timeInMiliseconds;
		this.score=score;
	}

	public int getId() {
		return id;
	}

	public int getLevel() {
		return level;
	}

	public int getNumberOfGames() {
		return numberOfGames;
	}

	public int getTimeInMIliseconds() {
		return timeInMIliseconds;
	}

	public int getScore() {
		return score;
	}

	public Date getDate() {
		return date;
	}
	
	

}
