/*

    Copyright 2014 Jo�e Kulovic

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
package com.my.math_quiz;

import java.util.HashMap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.my.math_quiz.utils.Task;
import com.my.math_quiz.views.InGameBottomButtoms;
import com.my.math_quiz.views.InGameBottomButtoms.BottomButtonListener;
import com.my.math_quiz.views.ResultBottomButtoms;
import com.my.math_quiz.views.ResultBottomButtoms.ResultBottomButtonListener;
import com.my.math_quiz.views.TitleBar;
import com.my.math_quiz.views.TitleBar.TitleBarListener;
import com.my.math_quiz_multiplayer_stuff.TCPIPClient;
import com.my.math_quiz_multiplayer_stuff.TCPIPClient.TCPIPClientListenerInGame;

public class MultiPlayerActivityJoinGame extends Activity implements TitleBarListener,TCPIPClientListenerInGame,ResultBottomButtonListener,BottomButtonListener  {

	TitleBar titleBar=null;
	

	int numberOfTasksInRound;
	int currentTaskPosition;
	
	Bitmap taskIndicatorCorrectAnswer;
	Bitmap taskIndicatorWrongAnswer;
	Bitmap taskIndicatorNotSelectedAnswer;
	Bitmap taskIndicatorCurrent;
	
	
	TextView texViewForTaskText=null;
	InGameBottomButtoms bottomButtonsAnswer=null;
	LinearLayout layoutForIndicators;
	ImageView[] imageViews;
	RelativeLayout gameViewContainer;
	RelativeLayout scoreViewContainer;
	TextView scoreText;
	
	boolean wasDisplyedCorrectAnswer=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multi_player_wlan_game);
		
		titleBar=(TitleBar)findViewById(R.id.TBtitleBar);
		titleBar.setTitleBarListener(this);
		titleBar.setTitle(getString(R.string.multi_player_join));
		titleBar.setRightImage(null);
		
		taskIndicatorCorrectAnswer=BitmapFactory.decodeResource(getResources(), R.drawable.task_indicator_correct_answer);
		taskIndicatorWrongAnswer=BitmapFactory.decodeResource(getResources(), R.drawable.task_indicator_wrong_answer);
		taskIndicatorNotSelectedAnswer=BitmapFactory.decodeResource(getResources(), R.drawable.task_indicator_not_selected);
		taskIndicatorCurrent=BitmapFactory.decodeResource(getResources(), R.drawable.task_indicator_current);
		
		
		bottomButtonsAnswer=(InGameBottomButtoms)this.findViewById(R.id.MPODGVbootomButtons);
		texViewForTaskText=(TextView)this.findViewById(R.id.MPODGVtextView);
		layoutForIndicators=(LinearLayout)this.findViewById(R.id.MPODGlayoutForIndicator);
		gameViewContainer=(RelativeLayout)this.findViewById(R.id.MPODGgameModeStuff);
		scoreViewContainer=(RelativeLayout)this.findViewById(R.id.MPODScoreModeStuff);
		scoreText=(TextView)this.findViewById(R.id.MPODGScoretextView);
		((ResultBottomButtoms)this.findViewById(R.id.MPODGVscoreButtons)).setListener(this);
		((ResultBottomButtoms)this.findViewById(R.id.MPODGVscoreButtons)).disableAgainButton();
		bottomButtonsAnswer.setListener(this);
		
		texViewForTaskText.setText("Waiting server to start game");
	}

	int[] myScores;
	private int getSumScore() {
		int tmp=0;
		for(int i=0; i<myScores.length; i++){
			if(myScores[i]>0)
				tmp+=myScores[i];
		}
		return tmp;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		TCPIPClient.setTCPIPClientListener(this);
	}
	@Override
	public void finish() {
		TCPIPClient.removeTCPIPClientListener(this);
		super.finish();
	}
	/**BEGIN the title bar listener methods*/
	@Override
	public void onLeftButtonClick() {
		this.finish();
	}

	@Override
	public void onRightButtonClick() {
		
	}
	/**END the title bar listener methods*/

	/**BEGIN the TCPIPClientListenerBeforeGame methods*/
	@Override
	public void onRequestingClientNickname() {
		TCPIPClient.sendNickname(ApplicationClass.getNickName());
	}


	HashMap<Integer, Task> tasks;
	@Override
	public void onNumberOfGames(int numberOfGames) {
		Log.d("clientRecive","number of games "+numberOfGames);
		this.numberOfTasksInRound=numberOfGames;
	}


	@Override
	public void onRequestToClearAllDataFromOldTasks() {
		Log.d("clientRecive","request to clear all data");
		tasks=new HashMap<Integer, Task>();
		texViewForTaskText.setText("Waiting server to start game");
		
	}


	@Override
	public void onUserScoresRecived(int userId, int score) {
		// TODO Auto-generated method stub
		Log.d("clientRecive","user score");
		
	}

	@Override
	public void onUserDataRecived(int userId, String nickname) {
		// TODO Auto-generated method stub
		Log.d("clientRecive","ruser data");
	}

	@Override
	public void onCommandToDisplaySpecificTask(int taskNumber) {
		Log.d("clientRecive","command to display specific task");
		wasDisplyedCorrectAnswer=false;
		if(taskNumber>0){
			imageViews[taskNumber-1].setImageBitmap(myScores[taskNumber-1]==-1?taskIndicatorWrongAnswer:(myScores[taskNumber-1]==0?taskIndicatorNotSelectedAnswer:taskIndicatorCorrectAnswer));
		}
		currentTaskPosition=taskNumber;
		Task currentTask=tasks.get(currentTaskPosition);

		texViewForTaskText.setText(currentTask.getText());
		bottomButtonsAnswer.seButtontTexts(currentTask.getAnswers());
		bottomButtonsAnswer.resetFirstSelectedAnswer();
		
		imageViews[currentTaskPosition].setImageBitmap(taskIndicatorCurrent);
	}


	@Override
	public void onCommandToDisplayCorrectAnswer(int taskNumber) {
		Log.d("clientRecive","command to display correct answer");
		bottomButtonsAnswer.setCorrectCollorToSpecificButton(tasks.get(taskNumber).getCorrectAnswer());
		wasDisplyedCorrectAnswer=true;
	}


	@Override
	public void onSelectedAnswerOfOtherUser(int task, int userId, int answer) {
		// TODO Auto-generated method stub
		Log.d("clientRecive","selected answer from other users");
	}


	@Override
	public void onTaskReciveFromServer(int taskNumber, Task task) {
		Log.d("clientRecive","task recvive");
		tasks.put(taskNumber,task);
		
	}


	@Override
	public void onServerStoped() {
		this.finish();
		
	}

	@Override
	public void onRequestToDisplayEndScreen(String text) {
		Log.d("clientRecive","request to display end screen");
		gameViewContainer.setVisibility(View.INVISIBLE);
		scoreViewContainer.setVisibility(View.VISIBLE);
		scoreText.setText("You ahived: "+getSumScore()+"\n\n"+Html.fromHtml(text));
	}


	@Override
	public void onRequestToDisplayGameScreen() {
		Log.d("clientRecive","request to diasplay game screen");
		gameViewContainer.setVisibility(View.VISIBLE);
		scoreViewContainer.setVisibility(View.INVISIBLE);
		
		layoutForIndicators.removeAllViews();
		imageViews=new ImageView[numberOfTasksInRound];
		myScores=new int[numberOfTasksInRound];
		int oneIndicatorWidth=ApplicationClass.getDisplaySize().x/numberOfTasksInRound;
		int oneIndicatorHeight=ApplicationClass.getDisplaySize().x/ApplicationClass.getMaximumNumberOfGamesInOneRound();
		LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(oneIndicatorWidth,oneIndicatorHeight);
		
		for(int i=0; i<imageViews.length; i++){
			imageViews[i]=new ImageView(this);
			imageViews[i].setLayoutParams(layoutParams);
			imageViews[i].setImageBitmap(taskIndicatorNotSelectedAnswer);
			imageViews[i].setScaleType(ScaleType.CENTER_INSIDE);
			layoutForIndicators.addView(imageViews[i]);
			
		}
		imageViews[0].setImageBitmap(taskIndicatorCurrent);
		
	}
	/**END the TCPIPClientListenerBeforeGame methods*/
	/**START the ResultBottomButtonListener methods*/
	@Override
	public void onAgainButtonClicked() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinishButtonClicked() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onShareButtonClicked() {
		// TODO Auto-generated method stub
		
	}
	/**END the ResultBottomButtonListener methods*/

	
	/**START the BottomButtonListener methods*/
	@Override
	public void onButtonClick(InGameBottomButtoms buttoms, int position) {
		if(wasDisplyedCorrectAnswer==false){
			if(myScores[currentTaskPosition]==0){
				Task t=tasks.get( currentTaskPosition);
				Log.d("onClickButton","posisiton: "+position+" correct position; "+t.getCorrectAnswer());
				if(buttoms.setCollors(position, t.getCorrectAnswer(),false)){
					TCPIPClient.sendSelectedAnswer(currentTaskPosition, position);
					myScores[currentTaskPosition]=(position==t.getCorrectAnswer())?1:-1;
				}
			}
		}
	}
	/**END the BottomButtonListener methods*/


}
