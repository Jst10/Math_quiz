package com.my.math_quiz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.my.math_quiz.views.TitleBar;
import com.my.math_quiz.views.TitleBar.TitleBarListener;

public class MultiPlayerActivityJoin extends Activity implements TitleBarListener {

	TitleBar titleBar=null;
	EditText ipAdress;
	EditText portNumber;
	
	Button connectButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multi_player_w_join);
		
		titleBar=(TitleBar)findViewById(R.id.TBtitleBar);
		titleBar.setTitleBarListener(this);
		titleBar.setTitle("Multi player join");
		titleBar.setRightImage(BitmapFactory.decodeResource(getResources(), R.drawable.action_settings));
		
		
		ipAdress=(EditText)findViewById(R.id.MPWJWEditIp);
		portNumber=(EditText)findViewById(R.id.MPWJWEditPort);
		
		ipAdress.setText(ApplicationClass.getLastIPAdress());
		portNumber.setText(ApplicationClass.getLastPortNumber()+"");
		
		
		ipAdress.addTextChangedListener(new TextWatcher() {
			@Override	public void onTextChanged(CharSequence s, int start, int before, int count) {	}
			@Override	public void beforeTextChanged(CharSequence s, int start, int count,	int after) {	}
			
			@Override
			public void afterTextChanged(Editable s) {
					ApplicationClass.saveIPAdress(s.toString());
			}
		});
		
		portNumber.addTextChangedListener(new TextWatcher() {
			@Override	public void onTextChanged(CharSequence s, int start, int before, int count) {	}
			@Override	public void beforeTextChanged(CharSequence s, int start, int count,	int after) {	}
			
			@Override
			public void afterTextChanged(Editable s) {
				try{
				ApplicationClass.savePortNumber(Integer.parseInt(s.toString()));
				}catch(Exception e){}
			}
		});
		
		
		
		
		connectButton=(Button)findViewById(R.id.MPWJConnect);
	}
	/**
	 * On click method defined in .xml 
	 * */
	public void MPWJbuttonClicked(View v){

	}

	@Override
	public void onLeftButtonClick() {
		this.finish();
	}

	@Override
	public void onRightButtonClick() {
		Intent intent = new Intent(MultiPlayerActivityJoin.this, PreferenceActivity.class);
		startActivity(intent);
	}

}
