package com.my.math_quiz;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;

import com.my.math_quiz.utils.LevelDescripction;

public class ApplicationClass extends Application{

	private static LevelDescripction[] levelsDescriptions=new LevelDescripction[20];
	private static Point displaySize=null;
	public static final int SINGLE_PLAYER_TIME_CORRECT=2;
	public static final int SINGLE_PLAYER_TIME_WRONG=5;
	
	
	public static final int NUMBER_TASK_LEVEL_MINIMUM=10;
	public static final int NUMBER_TASK_LEVEL_MEDINUM=20;
	public static final int NUMBER_TASK_LEVEL_MAXIMUM=30;
	
	public static Context applicationContext;
	@Override
	public void onCreate() {
		super.onCreate();
		
		applicationContext = getApplicationContext();
		for(int i=0; i<20; i++){
			levelsDescriptions[i]=new LevelDescripction(i);
		}
	}
	public static int getCurrentNumberOfGamesInOneRound(){
		return NUMBER_TASK_LEVEL_MINIMUM;
	}
	public static int getMaximumNumberOfGamesInOneRound(){
		return NUMBER_TASK_LEVEL_MAXIMUM;
	}
	public static LevelDescripction[] getLevelDescriptions() {
//		Long now=System.currentTimeMillis();
//		levels[19].generateTask(20, 50);
//		Log.d("timing","time is: "+(System.currentTimeMillis()-now)+"");
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
