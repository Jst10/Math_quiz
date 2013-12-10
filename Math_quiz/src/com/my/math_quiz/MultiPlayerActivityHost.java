package com.my.math_quiz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.my.math_quiz.views.TitleBar;
import com.my.math_quiz.views.TitleBar.TitleBarListener;

public class MultiPlayerActivityHost extends Activity implements TitleBarListener {

	TitleBar titleBar=null;
	TextView ipAdress;
	EditText portNumber;
	TextView numberOfPlayers;
	
	Button serverButton;
	Button nextButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multi_player_w_host);
		
		titleBar=(TitleBar)findViewById(R.id.TBtitleBar);
		titleBar.setTitleBarListener(this);
		titleBar.setTitle("Multi player host");
		titleBar.setRightImage(BitmapFactory.decodeResource(getResources(), R.drawable.action_settings));
		
		
		ipAdress=(TextView)findViewById(R.id.MPWHValueIp);
		portNumber=(EditText)findViewById(R.id.MPWHWEditPort);
		
		ipAdress.setText(ApplicationClass.getLastIPAdress());
		portNumber.setText(ApplicationClass.getLastPortNumber()+"");
		
		numberOfPlayers=(TextView)findViewById(R.id.MPWHValueNumPlayers);
		numberOfPlayers.setText("0");
		
		
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
		
		serverButton=(Button)findViewById(R.id.MPWHStartServer);
		nextButton=(Button)findViewById(R.id.MPWJStartGame);
	}

	/**
	 * On click method defined in .xml for booth of the buttons is the same method
	 * */
	public void MPWHbuttonClicked(View v){

	}

	@Override
	public void onLeftButtonClick() {
		this.finish();
	}

	@Override
	public void onRightButtonClick() {
		Intent intent = new Intent(MultiPlayerActivityHost.this, PreferenceActivity.class);
		startActivity(intent);
	}

}
