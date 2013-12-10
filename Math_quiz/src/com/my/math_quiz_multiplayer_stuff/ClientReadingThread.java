package com.my.math_quiz_multiplayer_stuff;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.os.Message;

class ClientReadingThread implements Runnable{

	/**
	 * This class is for reading data from client and then send message to TCPIPServer
	 * */
	/**
	 * First three characters will be number and that will tell me for what this message is and what is his meaning
	 * XXX-......................................
	 * first 3 is type
	 * then is message text
	 * */
	private boolean work;
//	private InputStream inputSream;
	BufferedReader inputBuffer;
	int clientId;
	public ClientReadingThread(InputStream inputSream,int clientId){
		work=true;
//		this.inputSream=inputSream;
		inputBuffer= new BufferedReader(new InputStreamReader(inputSream));
		this.clientId=clientId;
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
					//we append id of client to message
					tmp.obj=new ObjectForMessageFromClient(clientId,line.substring(3));
					TCPIPServer.handler.sendMessage(tmp);
				}
			}catch(Exception e){
				
			}
		}
	}
	class ObjectForMessageFromClient{
		int clientId;
		String text;
		public ObjectForMessageFromClient(int clientId,String text){
			this.clientId=clientId;
			this.text=text;
		}
	}
}