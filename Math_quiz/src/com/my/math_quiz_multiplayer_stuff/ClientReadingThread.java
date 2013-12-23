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

import java.io.DataInputStream;
import java.io.InputStream;

import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.my.math_quiz.ApplicationClass;

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
	DataInputStream dataInputStream;
	String readingBuffer="";
	
	int clientId;
	public ClientReadingThread(InputStream inputSream,int clientId){
		work=true;
//		this.inputSream=inputSream;
		dataInputStream= new DataInputStream(inputSream);
		this.clientId=clientId;
	}
	
	public void kill(){
//		Log.d("srDebuging","killing input buffer");
		work=false;
		try{
			dataInputStream.close();
		}catch(Exception e){
			dataInputStream=null;
			Log.d("srDebuging","killing input buffer error: "+e);
		}
		Log.d("srDebuging","killing input buffer end method");
	}
	@Override
	public void run() {
		while(work==true&&dataInputStream!=null){
			try{ 
				byte[] info=new byte[1024];
				Log.d("srDebuging","start reading");
				int number= dataInputStream.read(info);
				Log.d("srDebuging","number on reading:"+number);
				if(number==-1){
					work=false;
					dataInputStream.close();
					
					//message mean that client is not accessible
					Message tmp=TCPIPServer.handler.obtainMessage();
					tmp.what=1009;
					tmp.obj=clientId;
					TCPIPServer.handler.sendMessage(tmp); 
					
				}else{
					readingBuffer+=new String(info);
					int possition=readingBuffer.indexOf(ApplicationClass.endCharacters);
					if(possition>-1){
						String line=readingBuffer.substring(0,possition);
						readingBuffer=readingBuffer.substring(possition+4);
						if(line!=null){
							Message tmp=TCPIPServer.handler.obtainMessage();
							tmp.what=Integer.parseInt(line.substring(0,3));
							//we append id of client to message
							tmp.obj=new ObjectForMessageFromClient(clientId,line.substring(3));
							TCPIPServer.handler.sendMessage(tmp);
						}
					}
				}
				
			}catch(Exception e){
				Log.d("srDebuging","error while reading from client: "+e);
			}
			Log.d("srDebuging","end reading");
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