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

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;

public class TCPIPClient {

	String ipAdress;
	int port;

	Socket socket;
	BufferedWriter out;
	ServerReadingThread serverReadingRunable;
	Thread serverReadingThread;
	
	public TCPIPClient(String ipAdress,int port){
		this.ipAdress=ipAdress;
		this.port=port;
		
		reconnectToServer();
	}
	  public void killConnection(){
		  try{
			  serverReadingRunable.kill();
			}catch(Exception e){}
			try{
				out.close();
			}catch(Exception e){}
			try{
				socket.close();
			}catch(Exception e){}
			try{
				serverReadingThread.stop();
			}catch(Exception e){}
    }
	public void reconnectToServer(){
		try{
			 socket = new Socket(ipAdress, port);
		     out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
		     serverReadingRunable=new ServerReadingThread(socket.getInputStream());
		     serverReadingThread=new Thread(serverReadingRunable);
		}catch(Exception e){}
	}
	
	  public static Handler handler=new Handler(new Callback() {
			
			@Override
			public boolean handleMessage(Message msg) {
				switch(msg.what){
					case 0:break;
					case 1:break;
					case 2:break;
					case 3:break;
					case 4:break;
					case 5:break;
					default: break;
				}
				return false;
			}

	  });
}
