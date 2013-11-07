package com.my.math_quiz.utils;

import java.util.ArrayList;

import junit.framework.Test;

public class LevelData{
	public String levelName;
	private int level;
	public String levelDescription;
	/**Here I have times and scores for each number of test in round seperate like for 20 for 30 for 50 test in round*/
	public float[] levelTimes=new float[3];
	public int[] levelScores=new int[3];
	/**This I have to know if I should show tutorial or not*/
	public boolean wasAlreadyOpend;
	ArrayList<Task> tasks;
	
	private long startTime;
	private long endTime;
	private long length;
	
	
	public LevelData(int level){
		this.level=level;
		switch(level){
			case 0: levelName=Generator.get1levelName();levelDescription=Generator.get1levelDescription();break;
			case 1: levelName=Generator.get2levelName();levelDescription=Generator.get2levelDescription();break;
			case 2: levelName=Generator.get3levelName();levelDescription=Generator.get3levelDescription();break;
			case 3: levelName=Generator.get4levelName();levelDescription=Generator.get4levelDescription();break;
			case 4: levelName=Generator.get5levelName();levelDescription=Generator.get5levelDescription();break;
			case 5: levelName=Generator.get6levelName();levelDescription=Generator.get6levelDescription();break;
			case 6: levelName=Generator.get7levelName();levelDescription=Generator.get7levelDescription();break;
			case 7: levelName=Generator.get8levelName();levelDescription=Generator.get8levelDescription();break;
			case 8: levelName=Generator.get9levelName();levelDescription=Generator.get9levelDescription();break;
			case 9: levelName=Generator.get10levelName();levelDescription=Generator.get10levelDescription();break;
			case 10: levelName=Generator.get11levelName();levelDescription=Generator.get11levelDescription();break;
			case 11: levelName=Generator.get12levelName();levelDescription=Generator.get12levelDescription();break;
			case 12: levelName=Generator.get13levelName();levelDescription=Generator.get13levelDescription();break;
			case 13: levelName=Generator.get14levelName();levelDescription=Generator.get14levelDescription();break;
			case 14: levelName=Generator.get15levelName();levelDescription=Generator.get15levelDescription();break;
			case 15: levelName=Generator.get16levelName();levelDescription=Generator.get16levelDescription();break;
			case 16: levelName=Generator.get17levelName();levelDescription=Generator.get17levelDescription();break;
			case 17: levelName=Generator.get18levelName();levelDescription=Generator.get18levelDescription();break;
			case 18: levelName=Generator.get19levelName();levelDescription=Generator.get19levelDescription();break;
			case 19: levelName=Generator.get20levelName();levelDescription=Generator.get20levelDescription();break;
		default:break;
		}
	}
	public ArrayList<Task> getTests(int number){
		if(tasks==null||tasks.size()!=number){
			tasks=LevelData.generateTask(level, number);
		}
		return tasks;
	}
	
	public  int getNumberOfUnsolvedTests(){
		int numberOfUnsolved=0;
		for(Task task:tasks){
			if(task.getSelectedAnswer()==-1){
				numberOfUnsolved++;
			}
		}
		return numberOfUnsolved;
	}
	
	private boolean isTiming=false;
	private boolean isStopTiming=true;
	/**startTimingLevel start timing time from zero. The start timer will only star 
	 * if clocked is stopped previous or. isen't start already. 
	 * The time will be set to zero*/
	public void startTimingLevel(){
		if(isStopTiming==true){
			length=0;
			startTime=System.currentTimeMillis();
			isTiming=true;
			isStopTiming=false;
		}
	}
	/**pauseTimingLevel stop clock, and save current length for continue timing,
	 *  clock must already run else is no effect from this method (start should be called before)*/
	public void pauseTimingLevel(){
		if(isTiming&&isStopTiming==false){
			endTime=System.currentTimeMillis();
			length+=endTime-startTime;
			isTiming=false;
		}
	}
	
	/**resumTimingLevel  continue timing form last saved time,
	 *  clock must already run else is no effect from this method (start should be called before)*/
	public void resumTimingLevel(){
		if(isTiming==false&&isStopTiming==false){
			startTime=System.currentTimeMillis();
			isTiming=true;
		}
	}
	
	/**stopTimingLevel if timer is running we now stop timer and we can continue him only with method start
	 * clock must already run else is no effect from this method (start should be called before)*/
	public void stopTimingLevel(){
		if(isTiming==true&&isStopTiming==false){
			endTime=System.currentTimeMillis();
			length+=endTime-startTime;
			isTiming=false;
			isStopTiming=true;
		}
	}
	
	
	
	private static ArrayList<Task> generateTask(int level,int numberOfTasks){
		ArrayList<Task> tasks=new ArrayList<Task>();
		for(int i=0; i<numberOfTasks; i++){
			switch(level){
			case 0: tasks.add(Generator.get1levelTask());	break;
			case 1: tasks.add(Generator.get2levelTask());	break;
			case 2: tasks.add(Generator.get3levelTask());	break;
			case 3: tasks.add(Generator.get4levelTask());	break;
			case 4: tasks.add(Generator.get5levelTask());	break;
			case 5: tasks.add(Generator.get6levelTask());	break;
			case 6: tasks.add(Generator.get7levelTask());	break;
			case 7: tasks.add(Generator.get8levelTask());	break;
			case 8: tasks.add(Generator.get9levelTask());	break;
			case 9: tasks.add(Generator.get10levelTask());	break;
			case 10: tasks.add(Generator.get11levelTask());	break;
			case 11: tasks.add(Generator.get12levelTask());	break;
			case 12: tasks.add(Generator.get13levelTask());	break;
			case 13: tasks.add(Generator.get14levelTask());	break;
			case 14: tasks.add(Generator.get15levelTask());	break;
			case 15: tasks.add(Generator.get16levelTask());	break;
			case 16: tasks.add(Generator.get17levelTask());	break;
			case 17: tasks.add(Generator.get18levelTask());	break;
			case 18: tasks.add(Generator.get19levelTask());	break;
			case 19: tasks.add(Generator.get20levelTask());	break;
			default:break;
			}
		}
		return tasks;
	}
	
	

	
}
