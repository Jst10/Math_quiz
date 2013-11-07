package com.my.math_quiz;

import android.app.Application;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;

import com.my.math_quiz.utils.LevelData;

public class ApplicationClass extends Application{

	private static LevelData[]levels=new LevelData[20];
	private static Point displaySize=null;
	public static final int SINGLE_PLAYER_TIME_CORRECT=2;
	public static final int SINGLE_PLAYER_TIME_WRONG=5;
	
	
	public static final int NUMBER_TASK_LEVEL_MINIMUM=10;
	public static final int NUMBER_TASK_LEVEL_MEDINUM=20;
	public static final int NUMBER_TASK_LEVEL_MAXIMUM=30;
	
	@Override
	public void onCreate() {
		super.onCreate();
	
		for(int i=0; i<20; i++){
			levels[i]=new LevelData(i);
		}
	}
	public static int getNumberOfGamesInOneRound(){
		return NUMBER_TASK_LEVEL_MINIMUM;
	}
	public static int getMaximumNumberOfGamesInOneRound(){
		return NUMBER_TASK_LEVEL_MAXIMUM;
	}
	public static LevelData[] getLevelData() {
//		Long now=System.currentTimeMillis();
//		levels[19].generateTask(20, 50);
//		Log.d("timing","time is: "+(System.currentTimeMillis()-now)+"");
		return levels;
	}
	
	public static Point getDisplaySize(){
		if(displaySize==null){
			DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
			displaySize=new Point(metrics.widthPixels,metrics.heightPixels);
		}
		return displaySize;
	}
	public static LevelData getLevelData(int level) {
		return levels[level];
	}
	
	
}
