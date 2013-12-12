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