package com.my.math_quiz.tutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.my.math_quiz.R;
import com.my.math_quiz.views.TitleBar;
import com.my.math_quiz.views.TitleBar.TitleBarListener;



public class TutorialLevel0 extends Activity implements AnimationListener,TitleBarListener{

	public static final String KEY_FOR_MODE_PARAMATER="keyformodeinonetutorial";
	public static final int MODE_START_BEFORE_GAME=1;
	public static final int MODE_START_FROM_TUTORIAL=2;
	
	
	ImageView[] firstPartApples=new ImageView[3];
	ImageView[] secondPartApples=new ImageView[2];
	ImageView[] thirdPartApples=new ImageView[5];
	
	
	TextView firstNumber;
	TextView secondNumber;
	TextView thirdNumber;
	TextView[] sumSign=new TextView[2];
	TextView[] equalSign=new TextView[2];
	
	
	
	TitleBar titleBar;
	int selectedMode;
	Button okButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tutorial_level_0);
		
		Intent myIntent = getIntent();
		selectedMode = myIntent.getIntExtra(KEY_FOR_MODE_PARAMATER,0);
		
		
		titleBar=(TitleBar)findViewById(R.id.TBtitleBar);
		titleBar.setTitleBarListener(this);
		titleBar.setTitle("Tutorial 00");
		titleBar.setRightImage(null);
		
		okButton=(Button)findViewById(R.id.TL0doneButton);
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(selectedMode==MODE_START_FROM_TUTORIAL)
					TutorialLevel0.this.finish();
				else
					//TODO must change to close or start
					;
			}
		});
		

		firstPartApples[0]=(ImageView)findViewById(R.id.TL0apple0);
		firstPartApples[1]=(ImageView)findViewById(R.id.TL0apple1);
		firstPartApples[2]=(ImageView)findViewById(R.id.TL0apple2);
		secondPartApples[0]=(ImageView)findViewById(R.id.TL0apple3);
		secondPartApples[1]=(ImageView)findViewById(R.id.TL0apple4);
		thirdPartApples[0]=(ImageView)findViewById(R.id.TL0apple6);
		thirdPartApples[1]=(ImageView)findViewById(R.id.TL0apple7);
		thirdPartApples[2]=(ImageView)findViewById(R.id.TL0apple8);
		thirdPartApples[3]=(ImageView)findViewById(R.id.TL0apple9);
		thirdPartApples[4]=(ImageView)findViewById(R.id.TL0apple10);
		
		firstNumber=(TextView)findViewById(R.id.TL0text1);
		secondNumber=(TextView)findViewById(R.id.TL0text3);
		thirdNumber=(TextView)findViewById(R.id.TL0text5);
		
		sumSign[0]=(TextView)findViewById(R.id.TL0upSumChar);
		sumSign[1]=(TextView)findViewById(R.id.TL0text2);
		
		equalSign[0]=(TextView)findViewById(R.id.TL0upEaqChar);
		equalSign[1]=(TextView)findViewById(R.id.TL0text4);
		
		
//		fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
//		getFadeInAnimation(true).setDuration(1000);
//		getFadeInAnimation(true).setFillEnabled(true);
//		getFadeInAnimation(true).setFillAfter(true);
//		getFadeInAnimation(true).setAnimationListener(this);
//		
//		getFadeInAnimation(false).setDuration(1000);
//		getFadeInAnimation(false).setFillEnabled(true);
//		getFadeInAnimation(false).setFillAfter(true);
//		
//		
//		
		step=0;
		firstPartApples[0].startAnimation(getFadeInAnimation(true));
//		firstNumber.setAnimation(animationWithListener);
		firstNumber.startAnimation(getFadeInAnimation(false));
	}
//	
//	Animation getFadeInAnimation(true) = new AlphaAnimation(0, 1);
//	Animation getFadeInAnimation(false) = new AlphaAnimation(0, 1);
//	
	
	private int step=0;

	/**
	 * Start aniamtionListener method
	 * */
	@Override public void onAnimationRepeat(Animation animation) {	}
	@Override public void onAnimationStart(Animation animation) {	}

	@Override
	public void onAnimationEnd(Animation animation) {
		switch (step){
			case 0: 
				firstPartApples[1].startAnimation(getFadeInAnimation(true));
				step++; break;
			case 1: 
				firstNumber.setText("2");
				firstPartApples[2].startAnimation(getFadeInAnimation(true));
				step++; break;
			case 2: 
				firstNumber.setText("3");
				sumSign[0].startAnimation(getFadeInAnimation(true));
				sumSign[1].startAnimation(getFadeInAnimation(false));
				step++; break;
			case 3: 
				secondPartApples[0].startAnimation(getFadeInAnimation(true));
				secondNumber.startAnimation(getFadeInAnimation(false));
				step++; break;
			case 4: 
				secondPartApples[1].startAnimation(getFadeInAnimation(true));
				step++; break;
			case 5: 
				secondNumber.setText("2");
				equalSign[0].startAnimation(getFadeInAnimation(true));
				equalSign[1].startAnimation(getFadeInAnimation(false));
				step++; break;
			case 6: 
				thirdPartApples[0].startAnimation(getFadeInAnimation(true));				
				thirdNumber.startAnimation(getFadeInAnimation(false));
				step++; break;
			case 7: 
				thirdPartApples[1].startAnimation(getFadeInAnimation(true));				
				step++; break;
			case 8: 
				thirdNumber.setText("2");
				thirdPartApples[2].startAnimation(getFadeInAnimation(true));				
				step++; break;
			case 9: 
				thirdNumber.setText("3");
				thirdPartApples[3].startAnimation(getFadeInAnimation(true));				
				step++; break;
			case 10:
				thirdNumber.setText("4");
				thirdPartApples[4].startAnimation(getFadeInAnimation(true));				
				step++; break;
			case 11: 
				thirdNumber.setText("5");
				step++; break;
			default: break;
		}
		
	}

	/**
	 * End aniamtionListener method
	 * */
	
	private Animation getFadeInAnimation(boolean mustContainLister){
		Animation fadeIn = new AlphaAnimation(0, 1);
		fadeIn.setDuration(1000);
		fadeIn.setFillEnabled(true);
		fadeIn.setFillAfter(true);
		if(mustContainLister)
			fadeIn.setAnimationListener(this);
		return fadeIn;
	}
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
