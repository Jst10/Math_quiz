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
package com.my.math_quiz;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.my.math_quiz.interfaces.LevelDataIN;
import com.my.math_quiz.utils.LevelDescripction;
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
	TextView ipAdress;
	EditText portNumber;
	TextView numberOfPlayers;
	
	LevelDescripction levelDescripction;
	LevelDataIN levelData;
	ArrayList<Task> tasks;

	int numberOfTasksInRound;
	int selectedLevel;
	
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multi_player_wlan_game);
		
		titleBar=(TitleBar)findViewById(R.id.TBtitleBar);
		titleBar.setTitleBarListener(this);
		titleBar.setTitle(getString(R.string.multi_player_join));
		titleBar.setRightImage(BitmapFactory.decodeResource(getResources(), R.drawable.action_settings));
		
		taskIndicatorCorrectAnswer=BitmapFactory.decodeResource(getResources(), R.drawable.task_indicator_correct_answer);
		taskIndicatorWrongAnswer=BitmapFactory.decodeResource(getResources(), R.drawable.task_indicator_wrong_answer);
		taskIndicatorNotSelectedAnswer=BitmapFactory.decodeResource(getResources(), R.drawable.task_indicator_not_selected);
		taskIndicatorCurrent=BitmapFactory.decodeResource(getResources(), R.drawable.task_indicator_current);
		
		
		bottomButtonsAnswer=(InGameBottomButtoms)this.findViewById(R.id.MPODGVbootomButtons);
		texViewForTaskText=(TextView)this.findViewById(R.id.MPODGVtextView);
		layoutForIndicators=(LinearLayout)this.findViewById(R.id.MPODGlayoutForIndicator);
		gameViewContainer=(RelativeLayout)this.findViewById(R.id.MPODGgameModeStuff);
		scoreViewContainer=(RelativeLayout)this.findViewById(R.id.MPODScoreModeStuff);
		scoreText=(TextView)this.findViewById(R.id.MPODGScoretextView1);
		((ResultBottomButtoms)this.findViewById(R.id.MPODGVscoreButtons)).setListener(this);
		bottomButtonsAnswer.setListener(this);
		
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
		Intent intent = new Intent(MultiPlayerActivityJoinGame.this, PreferenceActivity.class);
		startActivity(intent);
	}
	/**END the title bar listener methods*/

	/**BEGIN the TCPIPClientListenerBeforeGame methods*/
	@Override
	public void onRequestingClientNickname() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onNumberOfGames(int numberOfGames) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onRequestToClearAllDataFromOldTasks() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onUserScoresRecived(int userId, int score) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onUserDataRecived(int userId, String nickname) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onCommandToDisplaySpecificTask(int taskNumber) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onCommandToDisplayCorrectAnswer(int taskNumber) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onSelectedAnswerOfOtherUser(int task, int userId, int answer) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onTaskReciveFromServer(int taskNumber, Task task) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onServerStoped() {
		this.finish();
		
	}

	@Override
	public void onRequestToDisplayEndScreen(String text) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onRequestToDisplayGameScreen() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}
	/**END the BottomButtonListener methods*/


}
