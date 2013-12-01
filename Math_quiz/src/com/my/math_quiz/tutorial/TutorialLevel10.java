package com.my.math_quiz.tutorial;

import android.R.layout;
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
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.my.math_quiz.ApplicationClass;
import com.my.math_quiz.R;
import com.my.math_quiz.views.TitleBar;
import com.my.math_quiz.views.TitleBar.TitleBarListener;



public class TutorialLevel10 extends Activity implements AnimationListener,TitleBarListener{

	public static final String KEY_FOR_MODE_PARAMATER="keyformodeinonetutorial";
	public static final int MODE_START_BEFORE_GAME=1;
	public static final int MODE_START_FROM_TUTORIAL=2;
	
	
	ImageView[] appels=new ImageView[12];
	
	
	TextView firstText;
	TextView secondText;
	TextView[] texts=new TextView[4];
	
	
	final int spaceBetweenDP=200;
	final int oneAppleSIzeDP=35;
    int spaceBetweenPx=0;
	int oneAppleSIzePx=0;
	int oneQutorOfScreenWidth;
	
	TitleBar titleBar;
	int selectedMode;
	Button okButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tutorial_level_10);
		
		Intent myIntent = getIntent();
		selectedMode = myIntent.getIntExtra(KEY_FOR_MODE_PARAMATER,0);
		
		
		titleBar=(TitleBar)findViewById(R.id.TBtitleBar);
		titleBar.setTitleBarListener(this);
		titleBar.setTitle("Tutorial 00");
		titleBar.setRightImage(null);
		
		okButton=(Button)findViewById(R.id.TL10doneButton);
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(selectedMode==MODE_START_FROM_TUTORIAL)
					TutorialLevel10.this.finish();
				else
					//TODO must change to close or start
					;
			}
		});
		
		spaceBetweenPx=(int)ApplicationClass.convertDpToPixel(spaceBetweenDP);
		oneAppleSIzePx=(int)ApplicationClass.convertDpToPixel(oneAppleSIzeDP);
		oneQutorOfScreenWidth=ApplicationClass.getDisplaySize().x/4;
		
		
		appels[0]=(ImageView)findViewById(R.id.TL10apple0);
		appels[1]=(ImageView)findViewById(R.id.TL10apple1);
		appels[2]=(ImageView)findViewById(R.id.TL10apple2);
		appels[3]=(ImageView)findViewById(R.id.TL10apple3);
		appels[4]=(ImageView)findViewById(R.id.TL10apple4);
		appels[5]=(ImageView)findViewById(R.id.TL10apple5);
		appels[6]=(ImageView)findViewById(R.id.TL10apple6);
		appels[7]=(ImageView)findViewById(R.id.TL10apple7);
		appels[8]=(ImageView)findViewById(R.id.TL10apple8);
		appels[9]=(ImageView)findViewById(R.id.TL10apple9);
		appels[10]=(ImageView)findViewById(R.id.TL10apple10);
		appels[11]=(ImageView)findViewById(R.id.TL10apple11);
		
		firstText=(TextView)findViewById(R.id.TL10text1);
		secondText=(TextView)findViewById(R.id.TL10text2);
		
		texts[0]=(TextView)findViewById(R.id.TL10Tmpt0);
		texts[1]=(TextView)findViewById(R.id.TL10Tmpt1);
		texts[2]=(TextView)findViewById(R.id.TL10Tmpt2);
		texts[3]=(TextView)findViewById(R.id.TL10Tmpt3);
		

		
		step=0;
		firstText.startAnimation(ApplicationClass.getFadeInAnimation(this));
	}
	
	private int step=0;

	/**
	 * Start aniamtionListener method
	 * */
	@Override public void onAnimationRepeat(Animation animation) {	}
	@Override public void onAnimationStart(Animation animation) {	}

	
	@Override
	public void onAnimationEnd(Animation animation) {
		/*
		  my situation
		 	* * * * * *
		    * * * * * *
		    //one is 35dp *35dp
			|
		    | 
		    | (this space between is 200dp)
		    |
		    |
		  ---------------
		  t1  t2  t3  t3 
		 * */
		View element=null;
		int dx=0;
		int dy=0; 
		
		int toCenter;

		
		switch (step){
		case 0:
				for(int i=1; i<appels.length; i++){
					appels[i].startAnimation(ApplicationClass.getFadeInAnimation(null));
				}
				appels[0].startAnimation(ApplicationClass.getFadeInAnimation(TutorialLevel10.this));
				step++;
				break;
		case 1: 
//				firstPartApples[1].startAnimation(getFadeInAnimation(true));
				texts[0].startAnimation(ApplicationClass.getFadeInAnimation(null));
				element=appels[11];
				dy=spaceBetweenPx;
				toCenter=-2*oneAppleSIzePx;
				dx=toCenter-oneQutorOfScreenWidth-oneQutorOfScreenWidth/2-oneAppleSIzePx/2;
				step++; break;
			case 2: 
				texts[1].startAnimation(ApplicationClass.getFadeInAnimation(null));
				element=appels[10];
				dy=spaceBetweenPx;
				toCenter=-1*oneAppleSIzePx;
				dx=toCenter-oneQutorOfScreenWidth/2-oneAppleSIzePx/2;
				step++; break;
			case 3: 
				texts[2].startAnimation(ApplicationClass.getFadeInAnimation(null));
				element=appels[9];
				dy=spaceBetweenPx;
				toCenter=0;
				dx=toCenter+oneQutorOfScreenWidth/2-oneAppleSIzePx/2;
				step++; break;
			case 4: 
				texts[3].startAnimation(ApplicationClass.getFadeInAnimation(null));
				element=appels[8];
				dy=spaceBetweenPx;
				toCenter=oneAppleSIzePx;
				dx=toCenter+oneQutorOfScreenWidth+oneQutorOfScreenWidth/2-oneAppleSIzePx/2;
				step++; break;
			case 5: 
				element=appels[7];
				dy=spaceBetweenPx-oneAppleSIzePx;
				toCenter=2*oneAppleSIzePx;
				dx=toCenter-oneQutorOfScreenWidth-oneQutorOfScreenWidth/2-oneAppleSIzePx/2;
				step++; break;	
			case 6: 
				texts[0].setText("2");
				element=appels[6];
				dy=spaceBetweenPx-oneAppleSIzePx;
				toCenter=3*oneAppleSIzePx;
				dx=toCenter-oneQutorOfScreenWidth/2-oneAppleSIzePx/2;
				step++; break;	
			case 7: 
				texts[1].setText("2");
				element=appels[5];
				dy=spaceBetweenPx;
				toCenter=-2*oneAppleSIzePx;
				dx=toCenter+oneQutorOfScreenWidth/2-oneAppleSIzePx/2;
				step++;
				break;
			case 8: 
				texts[2].setText("2");
				element=appels[4];
				dy=spaceBetweenPx;
				toCenter=-1*oneAppleSIzePx;
				dx=toCenter+oneQutorOfScreenWidth+oneQutorOfScreenWidth/2-oneAppleSIzePx/2;
				step++;
				break;
			case 9:
				texts[3].setText("2");
				element=appels[3];
				dy=spaceBetweenPx-oneAppleSIzePx;
				toCenter=0;
				dx=toCenter-oneQutorOfScreenWidth-oneQutorOfScreenWidth/2-oneAppleSIzePx/2;
				step++;
				break;
			case 10:
				texts[0].setText("3");
				element=appels[2];
				dy=spaceBetweenPx-oneAppleSIzePx;
				toCenter=oneAppleSIzePx;
				dx=toCenter-oneQutorOfScreenWidth/2-oneAppleSIzePx/2;
				step++;
				break;
			case 11:
				texts[1].setText("3");
				element=appels[1];
				dy=spaceBetweenPx-oneAppleSIzePx;
				toCenter=2*oneAppleSIzePx;
				dx=toCenter+oneQutorOfScreenWidth/2-oneAppleSIzePx/2;
				step++;
				break;
			case 12:
				texts[2].setText("3");
				element=appels[0];
				dy=spaceBetweenPx-oneAppleSIzePx;
				toCenter=3*oneAppleSIzePx;
				dx=toCenter+oneQutorOfScreenWidth+oneQutorOfScreenWidth/2-oneAppleSIzePx/2;
				step++;
				break;
			case 13:
				texts[3].setText("3");
				secondText.startAnimation(ApplicationClass.getFadeInAnimation(null));
				break;
				
			default: break;
		}
		TranslateAnimation anim=new TranslateAnimation(0,dx,0,dy);
		anim.setDuration(1000);
		anim.setFillEnabled(true);
		anim.setFillAfter(true);
		anim.setAnimationListener(TutorialLevel10.this);
		if(element!=null)
		element.startAnimation(anim);
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
