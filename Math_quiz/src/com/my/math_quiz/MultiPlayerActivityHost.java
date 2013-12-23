/*

    Copyright 2014 Jože Kulovic

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
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.my.math_quiz.views.TitleBar;
import com.my.math_quiz.views.TitleBar.TitleBarListener;
import com.my.math_quiz_multiplayer_stuff.TCPIPClient;
import com.my.math_quiz_multiplayer_stuff.TCPIPServer;
import com.my.math_quiz_multiplayer_stuff.TCPIPServer.TCPIPServerListenerBeforeGame;

public class MultiPlayerActivityHost extends Activity implements TitleBarListener, TCPIPServerListenerBeforeGame{

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
		titleBar.setTitle(getString(R.string.multi_player_host));
		titleBar.setRightImage(BitmapFactory.decodeResource(getResources(), R.drawable.action_settings));
		
		
		ipAdress=(TextView)findViewById(R.id.MPWHValueIp);
		portNumber=(EditText)findViewById(R.id.MPWHWEditPort);
		
		WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
		String ip = ApplicationClass.intToIp(wm.getConnectionInfo().getIpAddress());
		
		ipAdress.setText(ip);
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

	

	@Override
	protected void onResume() {
		super.onResume();
		TCPIPServer.setTCPIPServerListener(this);
	}



	/**
	 * On click method defined in .xml for booth of the buttons is the same method
	 * */
	public void MPWHbuttonClicked(View v){
		Log.d("srDebuging","bitton clicekd");
		if(v.getId()==R.id.MPWHStartServer){
			Log.d("srDebuging","restart server");
			TCPIPServer.setPort(Integer.parseInt(portNumber.getText()+""));
			TCPIPServer.restartTcpServer();
		}else{
			TCPIPServer.sendTaskToAllClients(null);//connectToServer();
		}
	}

	/**BEGIN the title bar listener methods*/
	@Override
	public void onLeftButtonClick() {
		this.finish();
	}

	@Override
	public void onRightButtonClick() {
		Intent intent = new Intent(MultiPlayerActivityHost.this, PreferenceActivity.class);
		startActivity(intent);
	}
	/**END the title bar listener methods*/


	/**BEGIN the TCPIPServerListenerBeforeGame methods*/
	@Override
	public void onNumberOfClientsChanged(int number, boolean accepted) {
		// TODO Auto-generated method stub
		
	}
	/**END the TCPIPServerListenerBeforeGame methods*/
}
