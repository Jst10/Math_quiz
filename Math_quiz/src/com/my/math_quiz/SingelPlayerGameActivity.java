package com.my.math_quiz;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.my.math_quiz.utils.LevelData;
import com.my.math_quiz.utils.Task;
import com.my.math_quiz.views.BottomButtoms;
import com.my.math_quiz.views.BottomButtoms.BottomButtonListener;
import com.my.math_quiz.views.TitleBar;
import com.my.math_quiz.views.TitleBar.TitleBarListener;

public class SingelPlayerGameActivity extends Activity implements BottomButtonListener,TitleBarListener{

	ViewPager pager;
	LevelData levelData;
	ArrayList<Task> tasks;
	LayoutInflater inflater;
	
	
	ImageView[] imageViews;
	int numberOfTasksInRound;
	TitleBar titleBar=null;
	
	Bitmap taskIndicatorCorrectAnswer;
	Bitmap taskIndicatorWrongAnswer;
	Bitmap taskIndicatorNotSelectedAnswer;
	Bitmap taskIndicatorCurrent;
	
	
	MyAdapterForSingelPLayerGameActivity adapterForViewPager;
	/**additionalPage mean number of pages after last test, that we have for displaying scores if user answer to all tasks in one group of them*/
	int additionalPage=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_player_game);
		
		Intent myIntent = getIntent();
		int selectedLevel = myIntent.getIntExtra("EXTRA_SELECTED_LEVEL",0);
		numberOfTasksInRound=ApplicationClass.getNumberOfGamesInOneRound();
		
		
		levelData= ApplicationClass.getLevelData(selectedLevel);
		levelData.startTimingLevel();
		tasks=	levelData.getTests(numberOfTasksInRound);
		
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		titleBar=(TitleBar)findViewById(R.id.TBtitleBar);
		titleBar.setTitleBarListener(this);
		titleBar.setRightImage(BitmapFactory.decodeResource(getResources(), R.drawable.action_settings));
		titleBar.setTitle(1+"/"+numberOfTasksInRound);
		
		pager=(ViewPager)findViewById(R.id.ASPGViewPager);
		adapterForViewPager=new MyAdapterForSingelPLayerGameActivity(numberOfTasksInRound);
		pager.setAdapter(adapterForViewPager);
		
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				Task t;
				if(position<numberOfTasksInRound){
					titleBar.setTitle((position+1)+"/"+numberOfTasksInRound);
					imageViews[position].setImageBitmap(taskIndicatorCurrent);
				}
				if(position>0){
					t=tasks.get(position-1);
					if(t.getSelectedAnswer()==-1)
						imageViews[position-1].setImageBitmap(taskIndicatorNotSelectedAnswer);
					else if(t.getSelectedAnswer()==t.getCorrectAnswer())
						imageViews[position-1].setImageBitmap(taskIndicatorCorrectAnswer);
					else
						imageViews[position-1].setImageBitmap(taskIndicatorWrongAnswer);
				}
				if(position<numberOfTasksInRound-1){
					t=tasks.get(position+1);
					if(t.getSelectedAnswer()==-1)
						imageViews[position+1].setImageBitmap(taskIndicatorNotSelectedAnswer);
					else if(t.getSelectedAnswer()==t.getCorrectAnswer())
						imageViews[position+1].setImageBitmap(taskIndicatorCorrectAnswer);
					else
						imageViews[position+1].setImageBitmap(taskIndicatorWrongAnswer);
				}
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});
		
		
		
		taskIndicatorCorrectAnswer=BitmapFactory.decodeResource(getResources(), R.drawable.task_indicator_correct_answer);
		taskIndicatorWrongAnswer=BitmapFactory.decodeResource(getResources(), R.drawable.task_indicator_wrong_answer);
		taskIndicatorNotSelectedAnswer=BitmapFactory.decodeResource(getResources(), R.drawable.task_indicator_not_selected);
		taskIndicatorCurrent=BitmapFactory.decodeResource(getResources(), R.drawable.task_indicator_current);
		
		LinearLayout layoutForIndicators=(LinearLayout)findViewById(R.id.ASPGRlayoutBelowTitleBar);
		imageViews=new ImageView[numberOfTasksInRound];
		int oneIndicatorWidth=ApplicationClass.getDisplaySize().x/ApplicationClass.getNumberOfGamesInOneRound();
		int oneIndicatorHeight=ApplicationClass.getDisplaySize().x/ApplicationClass.getMaximumNumberOfGamesInOneRound();
		LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(oneIndicatorWidth,oneIndicatorHeight);
		
		for(int i=0; i<imageViews.length; i++){
			imageViews[i]=new ImageView(this);
			imageViews[i].setLayoutParams(layoutParams);
			imageViews[i].setImageBitmap(taskIndicatorNotSelectedAnswer);
			imageViews[i].setScaleType(ScaleType.CENTER_INSIDE);
			layoutForIndicators.addView(imageViews[i]);
		}
		imageViews[pager.getCurrentItem()].setImageBitmap(taskIndicatorCurrent);
		
		levelData.startTimingLevel();
	}
	
	@Override
	protected void onDestroy() {
		levelData.stopTimingLevel();
		super.onDestroy();
	}
	@Override
	protected void onPause() {
		levelData.pauseTimingLevel();
		super.onPause();
	}
	@Override
	protected void onPostResume() {
		levelData.resumTimingLevel();
		super.onPostResume();
	}
	@Override
	protected void onResume() {
		levelData.resumTimingLevel();
		super.onResume();
	}

	class MyAdapterForSingelPLayerGameActivity extends PagerAdapter{
		int numbrOfTests;
		View resultPage=null;
		public MyAdapterForSingelPLayerGameActivity(int numberOfTests){
			this.numbrOfTests=numberOfTests;
		}
		@Override
		public int getCount() {
			return numbrOfTests+additionalPage;
		}

		@Override
		public boolean isViewFromObject(View view, Object arg1) {
			return view==arg1;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
//			return super.instantiateItem(container, position);
			if(position<numberOfTasksInRound){
				View v = inflater.inflate(R.layout.view_single_player_game_one_page, null);
				TextView text=(TextView)v.findViewById(R.id.VSPGOPtextView);
				Task currentTask=tasks.get(position);
				
				text.setText(currentTask.getText());
				BottomButtoms buttoms;
				buttoms=(BottomButtoms)v.findViewById(R.id.BBBottomBUttons);
				buttoms.seButtontTexts(currentTask.getAnswers());
				buttoms.setListener(SingelPlayerGameActivity.this);
				buttoms.setCollors(currentTask.getSelectedAnswer(), currentTask.getCorrectAnswer());
				pager.addView(v);
				return v;
			}
			else{
				if(resultPage==null){
					
					resultPage=new RelativeLayout(SingelPlayerGameActivity.this);
					resultPage.setBackgroundColor(0xF0F0F0);
					TextView t=new TextView(SingelPlayerGameActivity.this);
					t.setText(levelData.get)
					((RelativeLayout)resultPage).addView(t);
				
				}
				pager.addView(resultPage);
				return resultPage;
			}
		}
		 @Override
		 public void destroyItem(View collection, int position, Object view) {
		           ((ViewPager) collection).removeView((View) view);
		 }
	}
	/**@param position the position of button on which user clicked*/
	@Override
	public void onButtonClick(BottomButtoms buttoms,int position) {
		Task t=tasks.get( pager.getCurrentItem());
		if(t.setSelectedAnswer(position)){
			buttoms.setCollors(t.getSelectedAnswer(), t.getCorrectAnswer());
			if(additionalPage==0&&levelData.getNumberOfUnsolvedTests()==0){
				//now we finish all tasks in that level so we can calculate results
				levelData.stopTimingLevel();
				additionalPage=1;
				adapterForViewPager.notifyDataSetChanged();
				pager.setCurrentItem(numberOfTasksInRound,true);
				
			}else if(t.getSelectedAnswer()==t.getCorrectAnswer()){
				//we answer correct to answer an we move forward to next task  or back to previous if isn't solved already
				pager.setCurrentItem(pager.getCurrentItem()+1,true);
			}
		}
	}
	@Override
	public void onLeftButtonClick() {
		// we finish this activity
		SingelPlayerGameActivity.this.finish();
		
	}
	
	@Override
	public void finish() {
		levelData.stopTimingLevel();
		super.finish();
	}

	@Override
	public void onRightButtonClick() {
		//TODO we must do smth. don't know yet	
	}

	

}
