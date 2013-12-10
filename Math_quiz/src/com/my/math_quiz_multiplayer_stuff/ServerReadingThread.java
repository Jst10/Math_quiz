package com.my.math_quiz_multiplayer_stuff;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.os.Message;

class ServerReadingThread implements Runnable{

	/**
	 * This class is for reading data from server and then send message to TCPIPClient
	 * */
	/**
	 * XXX-......................................
	 * first 3 is type
	 * then is message text
	 * */
	private boolean work;
//	private InputStream inputSream;
	BufferedReader inputBuffer;
	public ServerReadingThread(InputStream inputSream){
		work=true;
		inputBuffer= new BufferedReader(new InputStreamReader(inputSream));
	}
	
	public void kill(){
		work=false;
		try{
			inputBuffer.close();
		}catch(Exception e){
			inputBuffer=null;
		}
	}
	@Override
	public void run() {
		while(work==true&&inputBuffer!=null){
			try{
				String line=inputBuffer.readLine();
				if(line!=null){
					Message tmp=TCPIPServer.handler.obtainMessage();
					tmp.what=Integer.parseInt(line.substring(0,3));
					tmp.obj=line.substring(3);
					TCPIPClient.handler.sendMessage(tmp);
				}
			}catch(Exception e){
				
			}
		}
	}

}