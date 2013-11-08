package com.my.math_quiz;

import java.util.ArrayList;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;

import com.my.math_quiz.database.DAOImplementation;
import com.my.math_quiz.utils.LevelDescripction;

public class ApplicationClass extends Application{

	
	public static final int NUMBER_OF_LEVELS=20;
	
	private static LevelDescripction[] levelsDescriptions=new LevelDescripction[NUMBER_OF_LEVELS];
	private static Point displaySize=null;
	public static final int SINGLE_PLAYER_TIME_CORRECT=2;
	public static final int SINGLE_PLAYER_TIME_WRONG=5;
	
	
	public static final int NUMBER_TASK_LEVEL_MINIMUM=10;
	public static final int NUMBER_TASK_LEVEL_MEDINUM=20;
	public static final int NUMBER_TASK_LEVEL_MAXIMUM=30;
	
	public static Context applicationContext;

	public static DAOImplementation dao;
	
	@Override
	public void onCreate() {
		super.onCreate();
		applicationContext = getApplicationContext();
		dao=new DAOImplementation();
		
		/**We read from DB all indicators if specific level was opened or not*/
		ArrayList<Boolean> levelsIndicators=dao.getOpendIndicatorsFromAllLevels();
		for(int i=0; i<NUMBER_OF_LEVELS; i++){
			levelsDescriptions[i]=new LevelDescripction(i,levelsIndicators.get(i));
		}
		
	}
	
	public static int getCurrentNumberOfGamesInOneRound(){
		return NUMBER_TASK_LEVEL_MINIMUM;
	}
	public static int getMaximumNumberOfGamesInOneRound(){
		return NUMBER_TASK_LEVEL_MAXIMUM;
	}
	public static LevelDescripction[] getLevelDescriptions() {
		return levelsDescriptions;
	}
	
	public static Point getDisplaySize(){
		if(displaySize==null){
			DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
			displaySize=new Point(metrics.widthPixels,metrics.heightPixels);
		}
		return displaySize;
	}
	public static LevelDescripction getLevelDescription(int level) {
		return levelsDescriptions[level];
	}
	
	
}
