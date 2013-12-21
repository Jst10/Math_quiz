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
package com.my.math_quiz_multiplayer_stuff;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

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
	BufferedWriter writer;
	ClientReadingThread readingRunable;
	Thread readingThread;
	
	public Client(int playerId,Socket socket,ClientReadingThread readingRunable,Thread readingThread){
		this.playerId=playerId;
		this.socket=socket;
		try{
			this.outputStream=socket.getOutputStream();
			this.writer=new BufferedWriter(new OutputStreamWriter(outputStream));
		}catch(Exception e){}
		this.readingRunable=readingRunable;
		this.readingThread=readingThread;
	}
	public void killClient(){
		try{
			readingRunable.kill();
		}catch(Exception e){}
		try{
			writer.close();
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
	public void sendData(String data){
		if(writer!=null){
			try{
				writer.write(data);
				writer.newLine();
				writer.flush();
			}catch(Exception e){}
		}
	}
}
