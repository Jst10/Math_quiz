package com.my.math_quiz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.my.math_quiz.adapters.SinglePlayerAdapter;
import com.my.math_quiz.utils.LevelDescripction;
import com.my.math_quiz.views.TitleBar;
import com.my.math_quiz.views.TitleBar.TitleBarListener;

public class SinglePlayerActivity  extends Activity implements TitleBarListener{

	
	TitleBar titleBar=null;
	ListView list=null;
	
	SinglePlayerAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_player);
		
		titleBar=(TitleBar)findViewById(R.id.TBtitleBar);
		titleBar.setTitleBarListener(this);
		titleBar.setTitle("Single player");
		titleBar.setRightImage(BitmapFactory.decodeResource(getResources(), R.drawable.action_settings));
		
		list=(ListView)findViewById(R.id.SPlistView);
		
		adapter=new SinglePlayerAdapter(this,0,ApplicationClass.getLevelDescriptions());
		list.setAdapter(adapter);
	
		list.setOnItemClickListener(onItemClickListene);
		ApplicationClass.dao.fealLevelsDescriptionsWithScores();
	}
	
	OnItemClickListener onItemClickListene=new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
			
			LevelDescripction lds=ApplicationClass.getLevelDescription(position);
//			if(lds.wasAlreadyOpend()==true){
				Intent intent = new Intent(SinglePlayerActivity.this, SingelPlayerGameActivity.class);
				intent.putExtra("EXTRA_SELECTED_LEVEL", position);
				startActivityForResult(intent,55);
//			}
//			else{
//				//we must show tutorial
//				
//				
//			}
		}
	};
	public static final String KEY_FOR_RESULT="finishdLevel";
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 55) {
		     if(resultCode == RESULT_OK){      
		         int result=data.getIntExtra(KEY_FOR_RESULT,-1);
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
		// TODO Auto-generated method stub
		
	}

}
