package com.my.math_quiz_multiplayer_stuff;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import android.widget.TabHost;

public class Client {

	private int playerId;
	private String nickname;
	
	Socket socket;
	OutputStream outputStream;
	ClientReadingThread readingRunable;
	Thread readingThread;
	
	public Client(int playerId,Socket socket,ClientReadingThread readingRunable,Thread readingThread){
		this.playerId=playerId;
		this.socket=socket;
		try{
			this.outputStream=socket.getOutputStream();
		}catch(Exception e){}
		this.readingRunable=readingRunable;
		this.readingThread=readingThread;
	}
	public void killClient(){
		try{
			readingRunable.kill();
		}catch(Exception e){}
		try{
			outputStream.close();
		}catch(Exception e){}
		try{
			socket.close();
		}catch(Exception e){}
		try{
			readingThread.stop();
		}catch(Exception e){}
		
	}
	
}
