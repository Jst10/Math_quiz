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
package com.my.math_quiz_multiplayer_stuff;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

import com.my.math_quiz.ApplicationClass;

import android.util.Log;

public class Client {

	
	private int playerId;
	public int getPlayerId() {
		return playerId;
	}
	public String getNickname() {
		return nickname;
	}
	private String nickname;
	
	Socket socket;
	OutputStream outputStream;
//	BufferedReader(new InputStreamReader(inputSream));
	DataOutputStream dataOutputStream;
	ClientReadingThread readingRunable;
	Thread readingThread;
	
	public Client(int playerId,Socket socket,ClientReadingThread readingRunable,Thread readingThread){
		this.playerId=playerId;
		this.socket=socket;
		try{
			this.outputStream=socket.getOutputStream();
			this.dataOutputStream=new DataOutputStream(outputStream);
		}catch(Exception e){}
		this.readingRunable=readingRunable;
		this.readingThread=readingThread;
	}
	public void killClient(){
		Log.d("srDebuging","klients killed1");
		try{
			readingRunable.kill();
		}catch(Exception e){}
		Log.d("srDebuging","klients killed2");
		try{
			dataOutputStream.close();
		}catch(Exception e){}
		Log.d("srDebuging","klients killed3");
		try{
			outputStream.close();
		}catch(Exception e){}
		Log.d("srDebuging","klients killed4");
		try{
			socket.close();
		}catch(Exception e){}
		Log.d("srDebuging","klients killed5");
		try{
			readingThread.stop();
		}catch(Exception e){}
		Log.d("srDebuging","klients killed6");
	}
	public void sendData(String data){
		if(dataOutputStream!=null){
			try{
				data+=ApplicationClass.endCharacters;
				dataOutputStream.write(data.getBytes(ApplicationClass.charset));
				dataOutputStream.flush();
			}catch(Exception e){}
		}
	}
	int[]scores;
	public void resetScore(int numberOfTasksInRound) {
		/**
		 * score two dimension table for two player 
		 * -1 wrong
		 * 0 not answered yet
		 * 1 correct answer
		 * */
		scores=new int[numberOfTasksInRound];
		
	}
	public int getSumScore() {
		int tmp=0;
		for(int i=0; i<scores.length; i++){
			if(scores[i]>0)
				tmp+=scores[i];
		}
		return tmp;
	}
	public void setScores(int currentTaskPosition, int i) {
		scores[currentTaskPosition]=i;
		
	}
}
