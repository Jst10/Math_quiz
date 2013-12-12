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

import java.util.ArrayList;

import android.database.Cursor;
import android.util.Log;

import com.my.math_quiz.ApplicationClass;
import com.my.math_quiz.database.DatabaseHelper.FeedLevelData;
import com.my.math_quiz.database.DatabaseHelper.FeedScoreData;
import com.my.math_quiz.utils.LevelDescripction;


public class DAOImplementation {

	DatabaseHelper databaseHelper;

	boolean wasBestScoresAlreadySet=false;
	
	public DAOImplementation(){
		databaseHelper=new DatabaseHelper();
	}
	public void markLevelAsOpend(int level){
		databaseHelper.setLevelDataToAlreadyOpend(level);
	}
	public long insertLevelEntityToDatabase(LevelEntity entity){
		return databaseHelper.insertScoreData(entity);
	}
	
	public ArrayList<Boolean> getOpendIndicatorsFromAllLevels(){
		ArrayList<Boolean> array=new ArrayList<Boolean>();
		Cursor cursor=databaseHelper.getCursorFromAllLevelDatas();
		int columnIndex=cursor.getColumnIndex(FeedLevelData.COLUMN_WAS_LEVEL_ALREADY_OPEND);
		cursor.moveToFirst();
		while(cursor.isAfterLast()==false){
			array.add(cursor.getInt(columnIndex)==1);
			cursor.moveToNext();
		}
		return array;
	}
	
	/**This method read scores from DB, and set them to levels description, which receive from application class
	 * @return true if we first time request to read score from DB, else if we already request for feeling best score method do nothing but just return false*/
	public boolean fealLevelsDescriptionsWithScores(){
		Log.d("databsS","before if");
		if(wasBestScoresAlreadySet==false){
			Log.d("databsS","before query");
			LevelDescripction[] levelDescripctions=ApplicationClass.getLevelDescriptions();
			Cursor cursor=databaseHelper.getCursorFromBestScoresFromAllLevels();
			int columnLevelId=cursor.getColumnIndex(FeedScoreData.COLUMN_LEVEL_ID);
			int columnNumberOfTest=cursor.getColumnIndex(FeedScoreData.COLUMN_NUMBER_OF_TESTS);
			int columnScoreAchived=cursor.getColumnIndex(FeedScoreData.COLUMN_SCORE_ACHIVED);
			int columnTimeSpent=cursor.getColumnIndex(FeedScoreData.COLUMN_TIME_SPENT);
			cursor.moveToFirst();
			while(cursor.isAfterLast()==false){
				Log.d("databsS","in while query: "+cursor.getInt(columnLevelId)+"/"+cursor.getInt(columnNumberOfTest)+"/"+cursor.getInt(columnScoreAchived)+"/"+cursor.getInt(columnTimeSpent));
				levelDescripctions[(cursor.getInt(columnLevelId))].setBestTimeScore(cursor.getInt(columnNumberOfTest),cursor.getInt(columnScoreAchived),cursor.getInt(columnTimeSpent));
				cursor.moveToNext();
			}
			wasBestScoresAlreadySet=true;
			return true;
		}
		return false;
	}
	
	public void updateSpecificLevelDescriptionWithScore(LevelDescripction description){
		Cursor cursor=databaseHelper.getCursorFromBestScoresFromSpecificLevel(description.getLevel());
		int columnLevelId=cursor.getColumnIndex(FeedScoreData.COLUMN_LEVEL_ID);
		int columnNumberOfTest=cursor.getColumnIndex(FeedScoreData.COLUMN_NUMBER_OF_TESTS);
		int columnScoreAchived=cursor.getColumnIndex(FeedScoreData.COLUMN_SCORE_ACHIVED);
		int columnTimeSpent=cursor.getColumnIndex(FeedScoreData.COLUMN_TIME_SPENT);
		cursor.moveToFirst();
		while(cursor.isAfterLast()==false){
			description.setBestTimeScore(cursor.getInt(columnNumberOfTest),cursor.getInt(columnScoreAchived),cursor.getInt(columnTimeSpent));
			cursor.moveToNext();
		}
	}
}
