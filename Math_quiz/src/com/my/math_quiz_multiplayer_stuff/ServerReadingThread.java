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
	DataInputStream dataInputStream;
	String readingBuffer="";
	public ServerReadingThread(InputStream inputSream){
		work=true;
		dataInputStream= new DataInputStream(inputSream);
	}
	
	public void kill(){
		work=false;
		try{
			dataInputStream.close();
		}catch(Exception e){
			dataInputStream=null;
		}
	}
	@Override
	public void run() {
		while(work==true&&dataInputStream!=null){
			try{
				byte[] info=new byte[1024];
				Log.d("clDebuging","STARTreading");
				int number= dataInputStream.read(info);
				Log.d("clDebuging","number on reading:"+number);
				if(number==-1){
					work=false;
					dataInputStream.close();
//					Log.d("clDebuging","before toast:"+number);
//					Toast.makeText(ApplicationClass.applicationContext, "Connection to servere lose", Toast.LENGTH_SHORT).show();

					Message tmp=TCPIPClient.handler.obtainMessage();
					tmp.what=1009;
					TCPIPClient.handler.sendMessage(tmp);
				
				}else{
					readingBuffer+=new String(info);
					int possition=readingBuffer.indexOf(ApplicationClass.endCharacters);
					if(possition>-1){
						String line=readingBuffer.substring(0,possition);
						Log.d("clDebuging","readed: "+line);
						readingBuffer=readingBuffer.substring(possition+ApplicationClass.endCharacters.length());
						if(line!=null){
							Message tmp=TCPIPServer.handler.obtainMessage();
							tmp.what=Integer.parseInt(line.substring(0,3));
							tmp.obj=line.substring(3);
							TCPIPClient.handler.sendMessage(tmp);
						}
					}
				}
			}catch(Exception e){
				Log.d("clDebuging","exception while reading"+e);
			}
			Log.d("clDebuging","ENDreading");

		}
	}

}
