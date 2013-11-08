package com.my.math_quiz.database;

import java.util.ArrayList;

import com.my.math_quiz.database.DatabaseHelper.FeedLevelData;

import android.database.Cursor;


public class DAOImplementation {

	DatabaseHelper databaseHelper;
	public DAOImplementation(){
		databaseHelper=new DatabaseHelper();
	}
	public void markLevelAsOpend(int level){
		databaseHelper.setLevelDataToAlreadyOpend(level);
	}
	public void insertLevelEntityToDatabase(LevelEntity entity){
		databaseHelper.insertScoreData(entity);
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
	
}
