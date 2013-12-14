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
package com.my.math_quiz.tutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.my.math_quiz.ApplicationClass;
import com.my.math_quiz.LevelsDisplayedActivity;
import com.my.math_quiz.R;
import com.my.math_quiz.views.TitleBar;
import com.my.math_quiz.views.TitleBar.TitleBarListener;



public class TutorialLevel3 extends Activity implements AnimationListener,TitleBarListener{

	ImageView[] firstPartApples=new ImageView[5];
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
		setContentView(R.layout.tutorial_level_3);
		
		Intent myIntent = getIntent();
		selectedMode = myIntent.getIntExtra(LevelsDisplayedActivity.KEY_FOR_MODE_PARAMATER,0);
		
		
		titleBar=(TitleBar)findViewById(R.id.TBtitleBar);
		titleBar.setTitleBarListener(this);
		titleBar.setTitle(getString(R.string.tutorial)+" 4");
		titleBar.setRightImage(null);
		
		okButton=(Button)findViewById(R.id.TL3doneButton);
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(selectedMode==LevelsDisplayedActivity.MODE_START_FROM_TUTORIAL)
					TutorialLevel3.this.finish();
				else
					//TODO must change to close or start
					;
			}
		});
		

		firstPartApples[0]=(ImageView)findViewById(R.id.TL3apple00);
		firstPartApples[1]=(ImageView)findViewById(R.id.TL3apple01);
		firstPartApples[2]=(ImageView)findViewById(R.id.TL3apple0);
		firstPartApples[3]=(ImageView)findViewById(R.id.TL3apple1);
		firstPartApples[4]=(ImageView)findViewById(R.id.TL3apple2);
		secondPartApples[0]=(ImageView)findViewById(R.id.TL3apple3);
		secondPartApples[1]=(ImageView)findViewById(R.id.TL3apple4);
		thirdPartApples[0]=(ImageView)findViewById(R.id.TL3apple6);
		thirdPartApples[1]=(ImageView)findViewById(R.id.TL3apple7);
		thirdPartApples[2]=(ImageView)findViewById(R.id.TL3apple8);
		thirdPartApples[3]=(ImageView)findViewById(R.id.TL3apple9);
		thirdPartApples[4]=(ImageView)findViewById(R.id.TL3apple10);
		
		firstNumber=(TextView)findViewById(R.id.TL3text1);
		secondNumber=(TextView)findViewById(R.id.TL3text3);
		thirdNumber=(TextView)findViewById(R.id.TL3text5);
		
		sumSign[0]=(TextView)findViewById(R.id.TL3upSumChar);
		sumSign[1]=(TextView)findViewById(R.id.TL3text2);
		
		equalSign[0]=(TextView)findViewById(R.id.TL3upEaqChar);
		equalSign[1]=(TextView)findViewById(R.id.TL3text4);
		
		
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
		firstPartApples[0].startAnimation(ApplicationClass.getFadeInAnimation(this));
//		firstNumber.setAnimation(animationWithListener);
		firstNumber.startAnimation(ApplicationClass.getFadeInAnimation(null));
	}
//	
//	Animation getFadeInAnimation(true) = new AlphaAnimation(0, 1);
//	Animation getFadeInAnimation(false) = new AlphaAnimation(0, 1);
//	
	@Override
	public void finish() {
		if(selectedMode==LevelsDisplayedActivity.MODE_START_BEFORE_GAME){
			Intent returnIntent = new Intent();
			returnIntent.putExtra(LevelsDisplayedActivity.KEY_FOR_LEVEL_WHICH_TUTORIAL_WAS_DISPLAYED,3);
			setResult(RESULT_OK,returnIntent);  
		}
		super.finish();
	}
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
//				firstPartApples[1].startAnimation(getFadeInAnimation(true));
				firstPartApples[1].startAnimation(ApplicationClass.getHalfFadeInAnimation(true,this));
				step++; break;
			case 1: 
				firstNumber.setText("2");
				firstPartApples[1].startAnimation(ApplicationClass.getHalfFadeInAnimation(false,this));
				step++; break;
			case 2: 
				firstPartApples[2].startAnimation(ApplicationClass.getHalfFadeInAnimation(true,this));
				step++; break;
			case 3: 
				firstNumber.setText("3");
				firstPartApples[2].startAnimation(ApplicationClass.getHalfFadeInAnimation(false,this));
				step++; break;
			case 4: 
				firstPartApples[3].startAnimation(ApplicationClass.getHalfFadeInAnimation(true,this));
				step++; break;
			case 5: 
				firstNumber.setText("4");
				firstPartApples[3].startAnimation(ApplicationClass.getHalfFadeInAnimation(false,this));
				step++; break;	
			case 6: 
				firstPartApples[4].startAnimation(ApplicationClass.getHalfFadeInAnimation(true,this));
				step++; break;
			case 7: 
				firstNumber.setText("5");
				firstPartApples[4].startAnimation(ApplicationClass.getHalfFadeInAnimation(false,this));
				step++; break;	
			case 8: 
				sumSign[0].startAnimation(ApplicationClass.getFadeInAnimation(this));
				sumSign[1].startAnimation(ApplicationClass.getFadeInAnimation(null));
				step++; break;
			case 9: 
				secondPartApples[0].startAnimation(ApplicationClass.getFadeInAnimation(this));
				secondNumber.startAnimation(ApplicationClass.getFadeInAnimation(null));
				step++; break;
			case 10: 
				secondPartApples[1].startAnimation(ApplicationClass.getHalfFadeInAnimation(true,this));
				step++; break;
			case 11: 
				secondNumber.setText("2");
				secondPartApples[1].startAnimation(ApplicationClass.getHalfFadeInAnimation(false,this));
				step++; break;
			case 12: 
				equalSign[0].startAnimation(ApplicationClass.getFadeInAnimation(this));
				equalSign[1].startAnimation(ApplicationClass.getFadeInAnimation(null));
				step++; break;
			case 13: 
				thirdPartApples[0].startAnimation(ApplicationClass.getFadeInAnimation(this));	
				thirdPartApples[1].startAnimation(ApplicationClass.getFadeInAnimation(null));	
				thirdPartApples[2].startAnimation(ApplicationClass.getFadeInAnimation(null));	
				thirdPartApples[3].startAnimation(ApplicationClass.getFadeInAnimation(null));	
				thirdPartApples[4].startAnimation(ApplicationClass.getFadeInAnimation(null));	
				thirdNumber.startAnimation(ApplicationClass.getFadeInAnimation(null));
				step++; break;
				
			case 14: 
				thirdPartApples[4].startAnimation(ApplicationClass.getHalfFadeOutAnimation(true,this));				
				step++; break;
			case 15: 
				thirdNumber.setText("4");
				thirdPartApples[4].startAnimation(ApplicationClass.getHalfFadeOutAnimation(false,this));				
				step++; break;
			case 16: 
				thirdPartApples[3].startAnimation(ApplicationClass.getHalfFadeOutAnimation(true,this));				
				step++; break;
			case 17: 
				thirdNumber.setText("3");
				thirdPartApples[3].startAnimation(ApplicationClass.getHalfFadeOutAnimation(false,this));		
				step++; break;
			case 18:
				int difference=(int)ApplicationClass.convertDpToPixel(17.5f);
				TranslateAnimation anim=new TranslateAnimation(0,0,0,difference);
				anim.setDuration(1000);
				anim.setFillEnabled(true);
				anim.setFillAfter(true);
				thirdPartApples[2].startAnimation(anim);
				break;
				
			default: break;
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
