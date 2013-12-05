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
import android.widget.TextView;

import com.my.math_quiz.LevelsDisplayedActivity;
import com.my.math_quiz.R;
import com.my.math_quiz.utils.OneAnimationStep;
import com.my.math_quiz.views.TitleBar;
import com.my.math_quiz.views.TitleBar.TitleBarListener;



public class TutorialLevelEquationType extends Activity implements AnimationListener,TitleBarListener{
	
	
	
	ArrayList<OneAnimationStep> animationsProcedure;
	

	
	
	private int step=0;
	TitleBar titleBar;
	int selectedMode;
	Button okButton;
	int level;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tutorial_equation_type);
		
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
				TutorialLevelEquationType.this.finish();
			}
		});
		
		
		animationsProcedure=new ArrayList<OneAnimationStep>();
		
		
		
		TextView tmp;
		OneAnimationStep oneStep;
		
		switch (level) {
		case 1:
			tmp=(TextView)findViewById(R.id.TLETtext1);
			tmp.setText("15 + 17 =");
			animationsProcedure.add(new OneAnimationStep(tmp,true,this));
			tmp=(TextView)findViewById(R.id.TLETtext2);
			tmp.setText("\u00A0\u00A0\u00A015");
			animationsProcedure.add(new OneAnimationStep(tmp,true,this));
			tmp=(TextView)findViewById(R.id.TLETtext3);
			tmp.setText("\u00A0+17");
			animationsProcedure.add(new OneAnimationStep(tmp,true,findViewById(R.id.TLETseperator2),false,this));
			tmp=(TextView)findViewById(R.id.TLETtext41);
			tmp.setText("12");
			animationsProcedure.add(new OneAnimationStep(tmp,true,this));

			tmp=(TextView)findViewById(R.id.TLETtext61);
			tmp.setText("1");
			tmp=(TextView)findViewById(R.id.TLETtext5);
			tmp.setText("2");
			oneStep=new OneAnimationStep(findViewById(R.id.TLETtext61),false,tmp,true,this);
			oneStep.addItemToHide(findViewById(R.id.TLETtext41),false);
			animationsProcedure.add(oneStep);


			tmp=(TextView)findViewById(R.id.TLETtext7);
			tmp.setText("3");
			animationsProcedure.add(new OneAnimationStep(tmp,true,this));
			tmp=(TextView)findViewById(R.id.TLETtext9);
			tmp.setText("15 + 17 = 22");
			animationsProcedure.add(new OneAnimationStep(tmp,true,this));
			
			break;
		case 2:
			tmp=(TextView)findViewById(R.id.TLETtext1);
			tmp.setText("37 + 89 =");
			animationsProcedure.add(new OneAnimationStep(tmp,true,this));
			tmp=(TextView)findViewById(R.id.TLETtext2);
			tmp.setText("\u00A0\u00A0\u00A037");
			animationsProcedure.add(new OneAnimationStep(tmp,true,this));
			tmp=(TextView)findViewById(R.id.TLETtext3);
			tmp.setText("\u00A0+89");
			animationsProcedure.add(new OneAnimationStep(tmp,true,findViewById(R.id.TLETseperator2),false,this));
			tmp=(TextView)findViewById(R.id.TLETtext41);
			tmp.setText("16");
			animationsProcedure.add(new OneAnimationStep(tmp,true,this));

			tmp=(TextView)findViewById(R.id.TLETtext61);
			tmp.setText("1");
			tmp=(TextView)findViewById(R.id.TLETtext5);
			tmp.setText("6");
			oneStep=new OneAnimationStep(findViewById(R.id.TLETtext61),false,tmp,true,this);
			oneStep.addItemToHide(findViewById(R.id.TLETtext41),false);
			animationsProcedure.add(oneStep);
			tmp=(TextView)findViewById(R.id.TLETtext42);
			tmp.setText("12");
			animationsProcedure.add(new OneAnimationStep(tmp,true,this));
			tmp=(TextView)findViewById(R.id.TLETtext7);
			tmp.setText("2");
			oneStep=new OneAnimationStep(findViewById(R.id.TLETtext62),false,tmp,true,this);
			oneStep.addItemToHide(findViewById(R.id.TLETtext42),false);
			animationsProcedure.add(oneStep);
			tmp=(TextView)findViewById(R.id.TLETtext8);
			tmp.setText("\u00A01");
			animationsProcedure.add(new OneAnimationStep(tmp,true,this));
			tmp=(TextView)findViewById(R.id.TLETtext9);
			tmp.setText("37 + 89 = 126");
			animationsProcedure.add(new OneAnimationStep(tmp,true,this));
			break;
		case 4:
			tmp=(TextView)findViewById(R.id.TLETtext1);
			tmp.setText("25 - 18 =");
			animationsProcedure.add(new OneAnimationStep(tmp,true,this));
			tmp=(TextView)findViewById(R.id.TLETtext2);
			tmp.setText("\u00A0\u00A0\u00A025");
			animationsProcedure.add(new OneAnimationStep(tmp,true,this));
			tmp=(TextView)findViewById(R.id.TLETtext3);
			tmp.setText("\u00A0-18");
			animationsProcedure.add(new OneAnimationStep(tmp,true,findViewById(R.id.TLETseperator2),false,this));
			tmp=(TextView)findViewById(R.id.TLETtext41);
			tmp.setText("-3");
			animationsProcedure.add(new OneAnimationStep(tmp,true,this));

			tmp=(TextView)findViewById(R.id.TLETtext61);
			tmp.setText("1");
			tmp=(TextView)findViewById(R.id.TLETtext5);
			tmp.setText("7");
			oneStep=new OneAnimationStep(findViewById(R.id.TLETtext61),false,tmp,true,this);
			oneStep.addItemToHide(findViewById(R.id.TLETtext41),false);
			animationsProcedure.add(oneStep);


			tmp=(TextView)findViewById(R.id.TLETtext7);
			tmp.setText("0");
			animationsProcedure.add(new OneAnimationStep(tmp,true,this));
			tmp=(TextView)findViewById(R.id.TLETtext9);
			tmp.setText("25 - 16 = 7");
			animationsProcedure.add(new OneAnimationStep(tmp,true,this));
			break;	
		default:
			break;
		}
		
		
		
		

		
		step=0;
		animationsProcedure.get(step).executeAnimation();
		step++;
	}
//	
//	Animation getFadeInAnimation(true) = new AlphaAnimation(0, 1);
//	Animation getFadeInAnimation(false) = new AlphaAnimation(0, 1);
//	@Override
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
