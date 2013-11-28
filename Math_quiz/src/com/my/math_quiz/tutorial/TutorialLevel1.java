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



public class TutorialLevel1 extends Activity implements AnimationListener,TitleBarListener{

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
		setContentView(R.layout.tutorial_level_1);
		
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
					TutorialLevel1.this.finish();
				else
					//TODO must change to close or start
					;
			}
		});
		
		
		animationsProcedure=new ArrayList<OneAnimationStep>();
		
		animationsProcedure.add(new OneAnimationStep(findViewById(R.id.TL1text1),true,this));
		animationsProcedure.add(new OneAnimationStep(findViewById(R.id.TL1text2),true,this));
		animationsProcedure.add(new OneAnimationStep(findViewById(R.id.TL1text3),true,findViewById(R.id.TL1seperator2),false,this));
		animationsProcedure.add(new OneAnimationStep(findViewById(R.id.TL1text4),true,this));
//		
		OneAnimationStep oneStep=new OneAnimationStep(findViewById(R.id.TL1text6),false,findViewById(R.id.TL1text5),true,this);
		oneStep.addItemToHide(findViewById(R.id.TL1text4),false);
		animationsProcedure.add(oneStep);
//		
//		
//		
		animationsProcedure.add(new OneAnimationStep(findViewById(R.id.TL1text7),true,this));
		animationsProcedure.add(new OneAnimationStep(findViewById(R.id.TL1text8),true,this));
//
//		animationsProcedure.add(new OneAnimationStep(findViewById(R.id.TL1text1),true,this));
//		animationsProcedure.add(new OneAnimationStep(findViewById(R.id.TL1text2),true,this));
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
