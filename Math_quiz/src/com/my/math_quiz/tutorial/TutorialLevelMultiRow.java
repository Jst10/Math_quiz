package com.my.math_quiz.tutorial;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

import com.my.math_quiz.R;
import com.my.math_quiz.utils.OneAnimationStep;
import com.my.math_quiz.views.TitleBar;
import com.my.math_quiz.views.TitleBar.TitleBarListener;



public class TutorialLevelMultiRow extends Activity implements AnimationListener,TitleBarListener{

	public static final String KEY_FOR_MODE_PARAMATER="keyformodeinonetutorial";
	public static final String KEY_FOR_SELECTED_LEVEL="selectedlevel";
	public static final int MODE_START_BEFORE_GAME=1;
	public static final int MODE_START_FROM_TUTORIAL=2;
	
	
	
	ArrayList<OneAnimationStep> animationsProcedure;
	
	TextView text1;
	TextView text2;
	TextView text3;
	TextView text4;
	
	
	private int step=0;
	TitleBar titleBar;
	int selectedMode;
	Button okButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tutorial_milti_row);
		
		Intent myIntent = getIntent();
		selectedMode = myIntent.getIntExtra(KEY_FOR_MODE_PARAMATER,0);
		int level=2;
		level = myIntent.getIntExtra(KEY_FOR_SELECTED_LEVEL,0);

		
		titleBar=(TitleBar)findViewById(R.id.TBtitleBar);
		titleBar.setTitleBarListener(this);
		titleBar.setTitle("Tutorial 01");
		titleBar.setRightImage(null);
		
		okButton=(Button)findViewById(R.id.TL0doneButton);
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(selectedMode==MODE_START_FROM_TUTORIAL)
					TutorialLevelMultiRow.this.finish();
				else
					//TODO must change to close or start
					;
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
