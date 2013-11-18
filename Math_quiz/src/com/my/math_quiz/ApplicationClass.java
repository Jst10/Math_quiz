/**
 * 
 * 
 * 
 * 
 * */

package com.my.math_quiz;

import java.util.ArrayList;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Point;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;

import com.my.math_quiz.database.DAOImplementation;
import com.my.math_quiz.utils.LevelDescripction;

public class ApplicationClass extends Application{

	
	public static final int NUMBER_OF_LEVELS=20;
	
	private static LevelDescripction[] levelsDescriptions=new LevelDescripction[NUMBER_OF_LEVELS];
	private static Point displaySize=null;

	public static final int NUMBER_TASK_LEVEL_MINIMUM=10;
	public static final int NUMBER_TASK_LEVEL_MEDINUM=20;
	public static final int NUMBER_TASK_LEVEL_MAXIMUM=30;
	
	public static Context applicationContext;

	public static DAOImplementation dao;
	
	@Override
	public void onCreate() {
		super.onCreate();
		applicationContext = getApplicationContext();
		MyPreferences.reloadPreferences();
		dao=new DAOImplementation();
		
		/**We read from DB all indicators if specific level was opened or not*/
		ArrayList<Boolean> levelsIndicators=dao.getOpendIndicatorsFromAllLevels();
		for(int i=0; i<NUMBER_OF_LEVELS; i++){
			levelsDescriptions[i]=new LevelDescripction(i,levelsIndicators.get(i));
		}
		
	}
	
	public static int getCurrentNumberOfGamesInOneRound(){
		return MyPreferences.numberOfGames;
	}
	public static int getMaximumNumberOfGamesInOneRound(){
		return NUMBER_TASK_LEVEL_MAXIMUM;
	}
	public static LevelDescripction[] getLevelDescriptions() {
		return levelsDescriptions;
	}
	public static int getDelayOnCorrectAnswerInMiliS(){
		return MyPreferences.dellayCorrect;
	}
	public static int getDelayOnWrongAnswerInMiliS(){
		return MyPreferences.dellayWrong;
	}
	public static String getNickName(){
		return MyPreferences.nicKName;
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

	public static void settingsChanged() {
		MyPreferences.reloadPreferences();
	}
	
	
	static class MyPreferences{
		public static int dellayCorrect;
		public static int dellayWrong;
		public static int numberOfGames;
		public static String nicKName="";
		
	
		public static void reloadPreferences(){
			if(applicationContext!=null){
				SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(applicationContext);
				dellayCorrect=Integer.parseInt(sharedPrefs.getString("pref_key_delay_correct","0"));
				dellayWrong=Integer.parseInt(sharedPrefs.getString("pref_key_delay_wrong","500"));
				numberOfGames=Integer.parseInt(sharedPrefs.getString("pref_key_number_of_test","10"));
				nicKName=sharedPrefs.getString("pref_key_nickname", "neznano");
			}
		}
		
		
	}
	
}
