package com.my.math_quiz.tutorial;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;

import com.my.math_quiz.R;
import com.my.math_quiz.utils.OneAnimationStep;
import com.my.math_quiz.views.TitleBar;
import com.my.math_quiz.views.TitleBar.TitleBarListener;



public class TutorialLevel2 extends Activity implements AnimationListener,TitleBarListener{

	public static final String KEY_FOR_MODE_PARAMATER="keyformodeinonetutorial";
	public static final int MODE_START_BEFORE_GAME=1;
	public static final int MODE_START_FROM_TUTORIAL=2;
	
	
	
	ArrayList<OneAnimationStep> animationsProcedure;
	

	
	
	private int step=0;
	TitleBar titleBar;
	int selectedMode;
	Button okButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tutorial_level_2);
		
		Intent myIntent = getIntent();
		selectedMode = myIntent.getIntExtra(KEY_FOR_MODE_PARAMATER,0);
		
		
		titleBar=(TitleBar)findViewById(R.id.TBtitleBar);
		titleBar.setTitleBarListener(this);
		titleBar.setTitle("Tutorial 01");
		titleBar.setRightImage(null);
		
		okButton=(Button)findViewById(R.id.TL0doneButton);
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(selectedMode==MODE_START_FROM_TUTORIAL)
					TutorialLevel2.this.finish();
				else
					//TODO must change to close or start
					;
			}
		});
		
		
		animationsProcedure=new ArrayList<OneAnimationStep>();
		
		animationsProcedure.add(new OneAnimationStep(findViewById(R.id.TL2text1),true,this));
		animationsProcedure.add(new OneAnimationStep(findViewById(R.id.TL2text2),true,this));
		animationsProcedure.add(new OneAnimationStep(findViewById(R.id.TL2text3),true,findViewById(R.id.TL2seperator2),false,this));
		animationsProcedure.add(new OneAnimationStep(findViewById(R.id.TL2text41),true,this));
//		
		OneAnimationStep oneStep=new OneAnimationStep(findViewById(R.id.TL2text61),false,findViewById(R.id.TL2text5),true,this);
		oneStep.addItemToHide(findViewById(R.id.TL2text41),false);
		animationsProcedure.add(oneStep);
//		
		animationsProcedure.add(new OneAnimationStep(findViewById(R.id.TL2text42),true,this));
		oneStep=new OneAnimationStep(findViewById(R.id.TL2text62),false,findViewById(R.id.TL2text7),true,this);
		oneStep.addItemToHide(findViewById(R.id.TL2text42),false);
		animationsProcedure.add(oneStep);
		
//		
		animationsProcedure.add(new OneAnimationStep(findViewById(R.id.TL2text8),true,this));
		animationsProcedure.add(new OneAnimationStep(findViewById(R.id.TL2text9),true,this));
//		animationsProcedure.add(new OneAnimationStep(findViewById(R.id.TL2text1),true,this));
//		animationsProcedure.add(new OneAnimationStep(findViewById(R.id.TL2text2),true,this));
//		
		

		
		step=0;
		animationsProcedure.get(step).executeAnimation();
		step++;
	}
//	
//	Animation getFadeInAnimation(true) = new AlphaAnimation(0, 1);
//	Animation getFadeInAnimation(false) = new AlphaAnimation(0, 1);
//	
	


	/**
	 * Start aniamtionListener method
	 * */
	@Override public void onAnimationRepeat(Animation animation) {	}
	@Override public void onAnimationStart(Animation animation) {	}

	@Override
	public void onAnimationEnd(Animation animation) {
		if(step<animationsProcedure.size()){
			animationsProcedure.get(step).executeAnimation();
			step++;
		}
		
	}

	/**
	 * End aniamtionListener method
	 * */
	
	
	/**
	 * Start title bar listener
	 * */
	@Override
	public void onLeftButtonClick() {
		this.finish();
	}
	@Override public void onRightButtonClick() {	}
	/**
	 * End title bar listener
	 * */

	
}
