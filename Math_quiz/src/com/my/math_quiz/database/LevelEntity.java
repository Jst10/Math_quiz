/*

    Copyright 2014 Jože Kulovic

    This file is part of Math-quiz.

    Math-quiz is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Math-quiz is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Math-quiz.  If not, see http://www.gnu.org/licenses

*/
package com.my.math_quiz.database;

import java.util.Calendar;
import java.util.Date;

import android.util.Log;

import com.my.math_quiz.ApplicationClass;

public class LevelEntity {


	private long id=-1;
	
	private int level;
	private int numberOfGames;
	private int timeInMIliseconds;
	private int score;
	
	private Date date;
	
	public LevelEntity(int id,int level,int numberOfGames,int timeInMiliseconds, int score,Date date){
		this.id=id;
		setData(level, numberOfGames, timeInMiliseconds, score,date);
	}
	
//	public LevelEntity(int level,int numberOfGames,int timeInMiliseconds, int score,Date date){
//		id=-1;
//		setData(level, numberOfGames, timeInMiliseconds, score,date);
//	}
	public LevelEntity(int level,int numberOfGames,int timeInMiliseconds, int score){
		id=-1;
		setData(level, numberOfGames, timeInMiliseconds, score,Calendar.getInstance().getTime());
	}
	
	public void setData(int level,int numberOfGames,int timeInMiliseconds, int score,Date date){
//		if(date==null)
//			date=Calendar.getInstance().getTime();
//		else
//			this.date=date;
		this.date=date;
		this.level=level;
		this.numberOfGames=numberOfGames;
		this.timeInMIliseconds=timeInMiliseconds;
		this.score=score;
	}

	public long getId() {
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
	
	/**This method save current entity to DB if it hasn't goot ID already*/
	public boolean saveToDB(){
		if(id!=-1){
			//TOODO maaybe change it to update
			return false;
		}
		else{
			this.id=ApplicationClass.dao.insertLevelEntityToDatabase(this);
			return true;
		}
	}
	

}
