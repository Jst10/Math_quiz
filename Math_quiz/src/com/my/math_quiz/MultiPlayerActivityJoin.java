/*

    Copyright 2014 Jo�e Kulovic

    This file is part of Math-quiz.

    Math-quiz is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Math-quiz is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Math-quiz.  If not, see http://www.gnu.org/licenses

*/
package com.my.math_quiz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.my.math_quiz.views.TitleBar;
import com.my.math_quiz.views.TitleBar.TitleBarListener;
import com.my.math_quiz_multiplayer_stuff.TCPIPClient;
import com.my.math_quiz_multiplayer_stuff.TCPIPClient.TCPIPClientListenerBeforeGame;
import com.my.math_quiz_multiplayer_stuff.TCPIPServer;

public class MultiPlayerActivityJoin extends Activity implements TitleBarListener,TCPIPClientListenerBeforeGame {

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
		titleBar.setTitle(getString(R.string.multi_player_join));
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
	
	@Override
	protected void onResume() {
		super.onResume();
		TCPIPClient.setTCPIPClientListener(this);
	}
	
	
	/**
	 * On click method defined in .xml 
	 * */
	public void MPWJbuttonClicked(View v){
			Log.d("clDebuging","connect cliekce");
			TCPIPClient.setIpAdressAndPort(ipAdress.getText()+"", Integer.parseInt(portNumber.getText()+""));
			Log.d("clDebuging","connecting to server: "+ipAdress.getText()+":"+ portNumber.getText()+"");
			TCPIPClient.connectToServer();
		
	}
	/**BEGIN the title bar listener methods*/
	@Override
	public void onLeftButtonClick() {
		this.finish();
	}

	@Override
	public void onRightButtonClick() {
		Intent intent = new Intent(MultiPlayerActivityJoin.this, PreferenceActivity.class);
		startActivity(intent);
	}
	/**END the title bar listener methods*/
}
