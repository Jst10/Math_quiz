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
