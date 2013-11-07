package com.my.math_quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class LaunchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launch, menu);
		return true;
	}
	
	public void launcherActivityButtonClick(View v){
		if(v.getId()==R.id.ALmultiPLayer){
			Intent intent = new Intent(this, MultiPlayerActivity.class);
			startActivity(intent);
		} else if(v.getId()==R.id.ALsinglePLayer){
			Intent intent = new Intent(this, SinglePlayerActivity.class);
			startActivity(intent);
		} else if(v.getId()==R.id.ALclassMode){
			Intent intent = new Intent(this, ClassActivity.class);
			startActivity(intent);
		}else if(v.getId()==R.id.ALtutorial){
			Intent intent = new Intent(this, TutorialActivity.class);
			startActivity(intent);
		}
		
	}

}
