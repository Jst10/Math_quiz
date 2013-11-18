package com.my.math_quiz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.my.math_quiz.views.TitleBar;
import com.my.math_quiz.views.TitleBar.TitleBarListener;

public class MultiPlayerActivity extends Activity implements TitleBarListener {

	TitleBar titleBar=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multi_player);
		
		titleBar=(TitleBar)findViewById(R.id.TBtitleBar);
		titleBar.setTitleBarListener(this);
		titleBar.setTitle("Multi player");
		titleBar.setRightImage(BitmapFactory.decodeResource(getResources(), R.drawable.action_settings));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.launch, menu);
		return true;
	}
	
	public void MultiPlayerActivityButtonClick(View v){
		if(v.getId()==R.id.MPhost){
//			Intent intent = new Intent(this, MultiPlayerActivity.class);
//			startActivity(intent);
		} else if(v.getId()==R.id.MPJoin){
//			Intent intent = new Intent(this, SinglePlayerActivity.class);
//			startActivity(intent);
		} else if(v.getId()==R.id.MPOneDevice){
			Intent intent = new Intent(this, LevelsDisplayedActivity.class);
			intent.putExtra(LevelsDisplayedActivity.KEY_FOR_MODE_PARAMETER, LevelsDisplayedActivity.MODE_MULTIPLAYER_SELECTION_ONE_DEVICE);
			startActivity(intent);
		}
		
	}

	@Override
	public void onLeftButtonClick() {
		this.finish();
	}

	@Override
	public void onRightButtonClick() {
		// TODO Auto-generated method stub
		
	}

}
