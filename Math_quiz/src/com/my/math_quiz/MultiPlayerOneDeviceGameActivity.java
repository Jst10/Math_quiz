package com.my.math_quiz;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

import com.my.math_quiz.interfaces.LevelDataIN;
import com.my.math_quiz.utils.LevelDescripction;
import com.my.math_quiz.utils.Task;
import com.my.math_quiz.views.InGameBottomButtoms;
import com.my.math_quiz.views.InGameBottomButtoms.BottomButtonListener;
import com.my.math_quiz.views.ResultBottomButtoms;
import com.my.math_quiz.views.ResultBottomButtoms.ResultBottomButtonListener;

public class MultiPlayerOneDeviceGameActivity extends Activity  implements ResultBottomButtonListener{

	LevelDescripction levelDescripction;
	LevelDataIN levelData;
	ArrayList<Task> tasks;
	LayoutInflater inflater;
	
	int numberOfTasksInRound;
	int selectedLevel;
	
	RelativeLayout pageContainer=null;
	int currentTaskPosition;
	
	Bitmap taskIndicatorCorrectAnswer;
	Bitmap taskIndicatorWrongAnswer;
	Bitmap taskIndicatorNotSelectedAnswer;
	Bitmap taskIndicatorCurrent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		
		setContentView(R.layout.activity_multi_player_one_device_game);
		pageContainer=(RelativeLayout)this.findViewById(R.id.MPODGContainer);
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		
		Intent myIntent = getIntent();
		selectedLevel = myIntent.getIntExtra("EXTRA_SELECTED_LEVEL",0);
		levelDescripction= ApplicationClass.getLevelDescription(selectedLevel);
		levelData=levelDescripction.getLevelData();

		taskIndicatorCorrectAnswer=BitmapFactory.decodeResource(getResources(), R.drawable.task_indicator_correct_answer);
		taskIndicatorWrongAnswer=BitmapFactory.decodeResource(getResources(), R.drawable.task_indicator_wrong_answer);
		taskIndicatorNotSelectedAnswer=BitmapFactory.decodeResource(getResources(), R.drawable.task_indicator_not_selected);
		taskIndicatorCurrent=BitmapFactory.decodeResource(getResources(), R.drawable.task_indicator_current);
		
		setGameViewToContainerAndRestart();
	}
	
//	ProgressBar progressBar1=null;
//	ProgressBar progressBar2=null;
	TextView texView1ForTaskText=null;
	TextView texView2ForTaskText=null;
	
	InGameBottomButtoms bottomButtons1Answer=null;
	InGameBottomButtoms bottomButtons2Answer=null;
	
//	boolean hasAnyoneAnswerAtThisTask=false;
//	boolean wasAnswerAnswerCorrectAnswer=false;
//	
	LinearLayout layoutForIndicators1;
	LinearLayout layoutForIndicators2;
	
	ImageView[] imageViews1;
	ImageView[] imageViews2;
	
	
	RelativeLayout gameViewContainer1;
	RelativeLayout gameViewContainer2;
	RelativeLayout scoreViewContainer1;
	RelativeLayout scoreViewContainer2;
	TextView scoreText1;
	TextView scoreText2;
	ResultBottomButtoms resultButtons1;
	ResultBottomButtoms resultButtons2;
	
	/**
	 * score two dimension table for two player 
	 * -1 wrong
	 * 0 not answered yet
	 * 1 correct answer
	 * */
	int[][] score;
	private void setGameViewToContainerAndRestart(){
		/**
		 * I'm adding dinamicly view because I was thinking to have multiple screens even for score, not all in one like I have now maybe I will change this someday 
		 * */
		pageContainer.removeAllViews();
		View v = inflater.inflate(R.layout.view_multi_player_game_one_device_one_page, null);
		pageContainer.addView(v);
		numberOfTasksInRound=ApplicationClass.getMPCurrentNumberOfGamesInOneRound();
		score=new int[2][numberOfTasksInRound];
		levelData.clearLevelData();
		tasks=	levelData.getTests(numberOfTasksInRound);
		
		
		bottomButtons1Answer=(InGameBottomButtoms)v.findViewById(R.id.MPODGVbootomButtons1);
		bottomButtons2Answer=(InGameBottomButtoms)v.findViewById(R.id.MPODGVbootomButtons2);
		
		texView1ForTaskText=(TextView)v.findViewById(R.id.MPODGVtextView1);
		texView2ForTaskText=(TextView)v.findViewById(R.id.MPODGVtextView2);

		layoutForIndicators1=(LinearLayout)v.findViewById(R.id.MPODGlayoutForIndicator1);
		layoutForIndicators2=(LinearLayout)v.findViewById(R.id.MPODGlayoutForIndicator2);
		
//		progressBar1.setMax(numberOfTasksInRound);
//		progressBar2.setMax(numberOfTasksInRound);
		gameViewContainer1=(RelativeLayout)v.findViewById(R.id.MPODGgameModeStuff1);
		gameViewContainer2=(RelativeLayout)v.findViewById(R.id.MPODGgameModeStuff2);
		scoreViewContainer1=(RelativeLayout)v.findViewById(R.id.MPODScoreModeStuff1);
		scoreViewContainer2=(RelativeLayout)v.findViewById(R.id.MPODScoreModeStuff2);
		
		scoreText1=(TextView)v.findViewById(R.id.MPODGScoretextView1);
		scoreText2=(TextView)v.findViewById(R.id.MPODGScoretextView2);
		
		resultButtons1=(ResultBottomButtoms)v.findViewById(R.id.MPODGVscoreButtons1);
		resultButtons2=(ResultBottomButtoms)v.findViewById(R.id.MPODGVscoreButtons2);
	
		resultButtons1.setListener(this);
		resultButtons2.setListener(this);
		
		layoutForIndicators1.removeAllViews();
		imageViews1=new ImageView[numberOfTasksInRound];
		layoutForIndicators2.removeAllViews();
		imageViews2=new ImageView[numberOfTasksInRound];
		int oneIndicatorWidth=ApplicationClass.getDisplaySize().x/numberOfTasksInRound;
		int oneIndicatorHeight=ApplicationClass.getDisplaySize().x/ApplicationClass.getMaximumNumberOfGamesInOneRound();
		LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(oneIndicatorWidth,oneIndicatorHeight);
		
		for(int i=0; i<imageViews1.length; i++){
			imageViews1[i]=new ImageView(this);
			imageViews1[i].setLayoutParams(layoutParams);
			imageViews1[i].setImageBitmap(taskIndicatorNotSelectedAnswer);
			imageViews1[i].setScaleType(ScaleType.CENTER_INSIDE);
			layoutForIndicators1.addView(imageViews1[i]);
			
			imageViews2[i]=new ImageView(this);
			imageViews2[i].setLayoutParams(layoutParams);
			imageViews2[i].setImageBitmap(taskIndicatorNotSelectedAnswer);
			imageViews2[i].setScaleType(ScaleType.CENTER_INSIDE);
			layoutForIndicators2.addView(imageViews2[i]);
		}
		imageViews1[0].setImageBitmap(taskIndicatorCurrent);
		imageViews2[0].setImageBitmap(taskIndicatorCurrent);
		
		
		bottomButtons1Answer.setListener(bottomButtonListener1);
		bottomButtons2Answer.setListener(bottomButtonListener2);
		
		displayDataFromSpecificTest(0);
	}
	
	
	/**This method display text of task and set answers to buttons and also set progress bars to right position*/
	private void displayDataFromSpecificTest(int position){
		if(position<numberOfTasksInRound)
		{
			currentTaskPosition=position;
	//		progressBar1.setProgress(position);
	//		progressBar2.setProgress(position);	
			
			Task currentTask=tasks.get(currentTaskPosition);
			texView1ForTaskText.setText(currentTask.getText());
			texView2ForTaskText.setText(currentTask.getText());
			
			bottomButtons1Answer.seButtontTexts(currentTask.getAnswers());
			bottomButtons2Answer.seButtontTexts(currentTask.getAnswers());
	
			bottomButtons1Answer.resetFirstSelectedAnswer();
			bottomButtons2Answer.resetFirstSelectedAnswer();
			
			
			
			imageViews1[position].setImageBitmap(taskIndicatorCurrent);
			imageViews2[position].setImageBitmap(taskIndicatorCurrent);
			
			if(position>0){
				imageViews1[position-1].setImageBitmap(score[0][position-1]==-1?taskIndicatorWrongAnswer:(score[0][position-1]==0?taskIndicatorNotSelectedAnswer:taskIndicatorCorrectAnswer));
				imageViews2[position-1].setImageBitmap(score[1][position-1]==-1?taskIndicatorWrongAnswer:(score[1][position-1]==0?taskIndicatorNotSelectedAnswer:taskIndicatorCorrectAnswer));
			}
			
			
//			hasAnyoneAnswerAtThisTask=false;
//			wasAnswerAnswerCorrectAnswer=false;
		}
		else{
			gameViewContainer1.setVisibility(View.INVISIBLE);
			gameViewContainer2.setVisibility(View.INVISIBLE);
			scoreViewContainer1.setVisibility(View.VISIBLE);
			scoreViewContainer2.setVisibility(View.VISIBLE);
			
			int tmp0=0;
			for(int i=0; i<score[0].length; i++){
				tmp0+=score[0][i];
			}
			int tmp1=0;
			for(int i=0; i<score[0].length; i++){
				tmp1+=score[1][i];
			}
			
			if(tmp0<=tmp1){
				scoreText1.setText("Žal ste izgubili.\nDosegli ste "+tmp0+" točk.");
				scoreText2.setText("Čestitamo, vi ste zamgovalec.\nDosegli ste "+tmp1+" točk.");
			}
			else if(tmp0==tmp1){
				scoreText1.setText("Odlična borba, vendar pa sta dosagla izenačen rezultat s "+tmp0+" točkami.");
				scoreText2.setText("Odlična borba, vendar pa sta dosagla izenačen rezultat s "+tmp1+" točkami.");
			}
			else{
				scoreText2.setText("Žal ste izgubili.\nDosegli ste "+tmp1+" točk.");
				scoreText1.setText("Čestitamo, vi ste zamgovalec.\nDosegli ste "+tmp0+" točk.");
			}
		}
	}
	private BottomButtonListener bottomButtonListener1=new BottomButtonListener() {
		/**@param position the position of button on which user clicked*/
		@Override
		public void onButtonClick(InGameBottomButtoms buttoms, int position) {
			//if I didnt answer yet and other guy isn't answer correct
			if(score[0][currentTaskPosition]==0&&score[1][currentTaskPosition]!=1){
				Task t=tasks.get( currentTaskPosition);
				if(buttoms.setCollors(position, t.getCorrectAnswer())){
					onAnswer(0,position==t.getCorrectAnswer());
				}
			}
		}
	};
	
	private BottomButtonListener bottomButtonListener2=new BottomButtonListener() {
		/**@param position the position of button on which user clicked*/
		@Override
		public void onButtonClick(InGameBottomButtoms buttoms, int position) {
			//if I didnt answer yet and other guy isn't answer correct
			if(score[0][currentTaskPosition]!=1&&score[1][currentTaskPosition]==0){
				Task t=tasks.get( currentTaskPosition);
				if(buttoms.setCollors(position, t.getCorrectAnswer())){
					onAnswer(1,position==t.getCorrectAnswer());
				}
			}
			
		}
	};
	
	private void onAnswer(int player,boolean wasCorrect){
//		Log.d("onbuttonclick","buttonclickes");
//		displayDataFromSpecificTest(++currentTaskPosition);
		score[player][currentTaskPosition]=wasCorrect?1:-1;
		handler.removeCallbacks(runablePageSwitching);
		if(wasCorrect){
			//we clicke correct solution and even if we frt clicked or second we go to next round
			moveDelayToPage(ApplicationClass.getMPDelayOnCorrectAnswerInMiliS());
			
		}else if(score[(player+1)%2][currentTaskPosition]==0){
			//we clicked first and wrong
			moveDelayToPage(ApplicationClass.getMPRemainTimeToAnswer());
		}
		else{
			//we clicked second and wrong
			moveDelayToPage(ApplicationClass.getMPDelayOnWrongAnswerInMiliS());
		}
	
	}
	
	
	final Handler handler = new Handler();
	private void moveDelayToPage(int delay){
		handler.postDelayed(runablePageSwitching, delay);
	}
	final Runnable runablePageSwitching=new Runnable() {
		  @Override
		  public void run() {
			displayDataFromSpecificTest(++currentTaskPosition);
		  }
	};

	
	/**
	 * start ResultBottomButtonListener methods 
	 * */
	@Override
	public void onAgainButtonClicked() {
		setGameViewToContainerAndRestart();
	}


	@Override
	public void onFinishButtonClicked() {
		this.finish();
		
	}


	@Override
	public void onShareButtonClicked() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * end ResultBottomButtonListener methods 
	 * */

}
