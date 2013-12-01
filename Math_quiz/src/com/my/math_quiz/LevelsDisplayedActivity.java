package com.my.math_quiz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.my.math_quiz.adapters.LevelsDisplayedAdapter;
import com.my.math_quiz.tutorial.TutorialLevel0;
import com.my.math_quiz.tutorial.TutorialLevelEquationType;
import com.my.math_quiz.tutorial.TutorialLevelMultiRow;
import com.my.math_quiz.utils.LevelDescripction;
import com.my.math_quiz.views.TitleBar;
import com.my.math_quiz.views.TitleBar.TitleBarListener;

public class LevelsDisplayedActivity extends Activity implements TitleBarListener{
	TitleBar titleBar=null;
	ListView list=null;
	
	
	public static final int MODE_BEFORE_SINGLE_PLAYER_GAME=1;
	public static final int MODE_MULTIPLAYER_SELECTION_ONE_DEVICE=2;
	public static final int MODE_MULTIPLAYER_SELECTION_WLAN=3;
	public static final int MODE_TUTORIAL_SELECTION=4;
	
	public static final String KEY_FOR_MODE_PARAMETER="displayingLevelsMode";
	public static final String KEY_FOR_SINGLE_PLAYER_RESULT="finishdLevel";
	
	private int selectedMode;
	
	LevelsDisplayedAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_player);
		
		Intent myIntent = getIntent();
		selectedMode = myIntent.getIntExtra(KEY_FOR_MODE_PARAMETER,0);
		
		
		
		titleBar=(TitleBar)findViewById(R.id.TBtitleBar);
		titleBar.setTitleBarListener(this);
		titleBar.setTitle("Single player");
		titleBar.setRightImage(BitmapFactory.decodeResource(getResources(), R.drawable.action_settings));
		
		list=(ListView)findViewById(R.id.SPlistView);
		
		adapter=new LevelsDisplayedAdapter(this,0,ApplicationClass.getLevelDescriptions(),selectedMode);
		list.setAdapter(adapter);
	
		list.setOnItemClickListener(onItemClickListene);
		ApplicationClass.dao.fealLevelsDescriptionsWithScores();
	}
	
	OnItemClickListener onItemClickListene=new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
			LevelDescripction lds=ApplicationClass.getLevelDescription(position);
			Intent intent;
			switch (selectedMode) {
				case MODE_BEFORE_SINGLE_PLAYER_GAME:
//				TODO you have to count the tutorial if user first time open the level
//					if(lds.wasAlreadyOpend()==true){
						intent = new Intent(LevelsDisplayedActivity.this, SingelPlayerGameActivity.class);
						intent.putExtra("EXTRA_SELECTED_LEVEL", position);
						startActivityForResult(intent,55);
//					}
//					else{
//						//we must show tutorial
//					}
					
				break;
				case MODE_MULTIPLAYER_SELECTION_ONE_DEVICE:
					Intent intent2 = new Intent(LevelsDisplayedActivity.this, MultiPlayerOneDeviceGameActivity.class);
					startActivity(intent2);
				break;
				case MODE_MULTIPLAYER_SELECTION_WLAN:
				
				break;
				case MODE_TUTORIAL_SELECTION:
					if(position==0){
						intent = new Intent(LevelsDisplayedActivity.this, TutorialLevel0.class);
						intent.putExtra(TutorialLevel0.KEY_FOR_MODE_PARAMATER, TutorialLevel0.MODE_START_FROM_TUTORIAL);
						startActivity(intent);
					
					}
//					else if(positOion==1){
//						intent = new Intent(LevelsDisplayedActivity.this, TutorialLevel1.class);
//						intent.putExtra(TutorialLevel1.KEY_FOR_MODE_PARAMATER, TutorialLevel1.MODE_START_FROM_TUTORIAL);
//						startActivity(intent);
//					
//					}
					else if(position==1||position==2){
//						intent = new Intent(LevelsDisplayedActivity.this, TutorialLevel2.class);
//						intent.putExtra(TutorialLevel2.KEY_FOR_MODE_PARAMATER, TutorialLevel2.MODE_START_FROM_TUTORIAL);
						intent = new Intent(LevelsDisplayedActivity.this, TutorialLevelEquationType.class);
						intent.putExtra(TutorialLevelEquationType.KEY_FOR_MODE_PARAMATER, TutorialLevelEquationType.MODE_START_FROM_TUTORIAL);
						intent.putExtra(TutorialLevelEquationType.KEY_FOR_SELECTED_LEVEL, position);
						startActivity(intent);
					
					}else if(position>=5){
						intent = new Intent(LevelsDisplayedActivity.this, TutorialLevelMultiRow.class);
						intent.putExtra(TutorialLevelMultiRow.KEY_FOR_MODE_PARAMATER, TutorialLevelMultiRow.MODE_START_FROM_TUTORIAL);
						intent.putExtra(TutorialLevelMultiRow.KEY_FOR_SELECTED_LEVEL, position);
						startActivity(intent);
					}
				break;	
			default:
				break;
			}

		}
	};
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 55) {
		     if(resultCode == RESULT_OK){      
		         int result=data.getIntExtra(KEY_FOR_SINGLE_PLAYER_RESULT,-1);
		         if(result!=-1){
		        	 ApplicationClass.getLevelDescription(result).updateMaximumScores();
		        	 adapter.notifyDataSetChanged();
		         }
		     }
		     if (resultCode == RESULT_CANCELED) {    
		         //Write your code if there's no result
		     }
		  }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.launch, menu);
		return true;
	}

	@Override
	public void onLeftButtonClick() {
		this.finish();
	}

	@Override
	public void onRightButtonClick() {
		Log.d("starting","start preference activity");
		Intent intent = new Intent(LevelsDisplayedActivity.this, PreferenceActivity.class);
		startActivity(intent);
		
	}



}
