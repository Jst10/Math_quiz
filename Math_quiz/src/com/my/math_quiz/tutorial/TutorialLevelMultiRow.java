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

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.my.math_quiz.LevelsDisplayedActivity;
import com.my.math_quiz.R;
import com.my.math_quiz.utils.OneAnimationStep;
import com.my.math_quiz.views.TitleBar;
import com.my.math_quiz.views.TitleBar.TitleBarListener;



public class TutorialLevelMultiRow extends Activity implements AnimationListener,TitleBarListener{

	
	
	
	ArrayList<OneAnimationStep> animationsProcedure;
	
	TextView text1;
	TextView text2;
	TextView text3;
	TextView text4;
	
	
	private int step=0;
	TitleBar titleBar;
	int selectedMode;
	Button okButton;
	int level;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tutorial_multi_row);
		
		Intent myIntent = getIntent();
		selectedMode = myIntent.getIntExtra(LevelsDisplayedActivity.KEY_FOR_MODE_PARAMATER,0);
		level=2;
		level = myIntent.getIntExtra(LevelsDisplayedActivity.KEY_FOR_SELECTED_LEVEL,0);

		
		titleBar=(TitleBar)findViewById(R.id.TBtitleBar);
		titleBar.setTitleBarListener(this);
		titleBar.setTitle("Tutorial "+(level+1));
		titleBar.setRightImage(null);
		
		okButton=(Button)findViewById(R.id.TL0doneButton);
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TutorialLevelMultiRow.this.finish();
			}
		});
		
		
		text1=(TextView)findViewById(R.id.TLMtext1);
		text2=(TextView)findViewById(R.id.TLMtext2);
		text3=(TextView)findViewById(R.id.TLMtext3);
		text4=(TextView)findViewById(R.id.TLMtext4);
		
		
		switch (level) {
		case 5:
			text1.setText("5 + 2 - 3 =");
			text2.setText("5 + 2 = 7");
			text3.setText("7 - 3 = 4");
			text4.setText("5 + 2 - 3 = 4");
			break;
		case 6:
			text1.setText("15 + (8 - 4) = ");
			text2.setText("(8 - 4) = 4");
			text3.setText("15 + 4 = 19");
			text4.setText("15 + (8 - 4) = 19");
			break;
		case 7:
			text1.setText("5 * 3 =");
			text2.setText("5 + 5 + 5 = 15");
			text3.setText("5 * 3 = 15");
			setNoTextInText4View();
			
			break;
		case 8:
			text1.setText("15 * 5=");
			text2.setText("15 + 15 + 15 + 15 + 15 = 75");
			text3.setText("15 * 5 = 75");
			setNoTextInText4View();
			break;
		case 9:
			text1.setText("2 + 5 * 8 =");
			text2.setText("5 *8 = 40");
			text3.setText("2 +40 = 42");
			text4.setText("2 + 5 * 8 = 42");
			break;
		case 10:
			text1.setText("15 / 5 =");
			text2.setText("15 = 5 * 3");
			text3.setText("=> 3");
			text4.setText("15 /  5 = 3");
			break;
		case 11:
			text1.setText("15 *4  / 3 =");
			text2.setText("15 * 4  = 60");
			text3.setText("60 /3 = 20");
			text4.setText("15 * 4 * 3 = 20");
			break;
		case 12:
			text1.setText("(8 + 2) * 3 =");
			text2.setText("(8 + 2) = 10");
			text3.setText("10 * 3 = 30");
			text4.setText("(8 + 2) * 3 = 30");
			break;
		case 13:
			text1.setText("26 * (8 - 6)=");
			text2.setText("(8 - 6) = 2");
			text3.setText("26 * 2 = 52");
			text4.setText("26 * (8 - 6) = 52");
			break;
		case 14:
			text1.setText(Html.fromHtml("5<sup><small>2</small></sup>="));
			text2.setText("5 * 5 = 25");
			text3.setText(Html.fromHtml("5<sup><small>2</small></sup> = 25"));
			setNoTextInText4View();
			break;
		case 15:
			text1.setText(Html.fromHtml("16<sup><small>2</small></sup> ="));
			text2.setText("16 * 16 = 256");
			text3.setText(Html.fromHtml("16<sup><small>2</small></sup> = 256"));
			setNoTextInText4View();
			break;
		case 16:
			text1.setText(Html.fromHtml("\u221a<small>64</small>="));
			text2.setText("8 * 8 = 64");
			text3.setText("=> 8");
			text4.setText(Html.fromHtml("\u221a<small>64</small> = 8"));
			break;
		case 17:
			text1.setText(Html.fromHtml("\u221a<small>144</small>="));
			text2.setText("12 * 12 = 144");
			text3.setText("=> 12");
			text4.setText(Html.fromHtml("\u221a<small>144</small> = 12"));
			break;
		case 18:
			text1.setText(Html.fromHtml("\u221A(5<sup><small>2</small></sup>)="));
			text2.setText(Html.fromHtml("5<sup><small>2</small></sup> = 25"));
			text3.setText(Html.fromHtml("\u221a<small>25</small> = 5"));
			text4.setText(Html.fromHtml("\u221A(5<sup><small>2</small></sup>) = 5"));
			break;
		case 19:
			text1.setText(Html.fromHtml("\u221a<small>(8 + 2)</small> * 10 ="));
			text2.setText("(8 + 2) = 10");
			text3.setText(Html.fromHtml("\u221a<small>(10 * 10)</small> = 10"));
			text4.setText(Html.fromHtml("\u221a<small>(8 + 2)</small> * 10 = 10"));
			break;
		default:
			break;
		}
		
		
		
		
		
		
		
		
		
		
		
		animationsProcedure=new ArrayList<OneAnimationStep>();
		
		animationsProcedure.add(new OneAnimationStep(text1,true,this));
		animationsProcedure.add(new OneAnimationStep(text2,true,this));
		animationsProcedure.add(new OneAnimationStep(text3,true,this));
		if(text4.getText().length()>0)
			animationsProcedure.add(new OneAnimationStep(text4,true,this));
		
		
		step=0;
		animationsProcedure.get(step).executeAnimation();
		step++;
	}

	@Override
	public void finish() {
		if(selectedMode==LevelsDisplayedActivity.MODE_START_BEFORE_GAME){
			Intent returnIntent = new Intent();
			returnIntent.putExtra(LevelsDisplayedActivity.KEY_FOR_LEVEL_WHICH_TUTORIAL_WAS_DISPLAYED,level);
			setResult(RESULT_OK,returnIntent);  
		}
		super.finish();
	}

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
	private void setNoTextInText4View(){
		text4.setText("");
		RelativeLayout.LayoutParams params=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.BELOW,  R.id.TLMseperator2);
		text3.setLayoutParams(params);
		
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
