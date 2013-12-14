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
import java.lang.ref.WeakReference;
import java.net.Socket;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;

public class TCPIPClient {
	
	/**
	 * This is listeners for all action that client activity need
	 * */
	public interface TCPIPClientListener{
		
	}
	
	
	static String ipAdress;
	static int port;
	static WeakReference<TCPIPClientListener> listener;
	static Socket socket;
	static BufferedWriter out;
	static ServerReadingThread serverReadingRunable;
	static Thread serverReadingThread;
	
/**
 * This method save port number and ipAdress to use it you must call connectToServer or reconnectToServer method
 *  @param ipAdresss is ipAdress of server, to which you want to connect
 *  @param port is the port number of server, to which you want to connect
 * */
	public static void setIpAdressAndPort(String ipAdresss,int portt){
		ipAdress=ipAdresss;
		port=portt;
	}
	
	/**This method kill connection to server
     * first is trying to close socket then killing runableFor reading class 
     * and then the tread whit method stop and also close output stream
     * 
     */
	public static void killConnection(){
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
	
	/**
	 * This method reconnect this device to server
	 * First is trying to kill old connection if is any then is setting socket and thread for reading
	 * */
	public static void reconnectToServer(){
		killConnection();
		try{
			 socket = new Socket(ipAdress, port);
		     out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
		     serverReadingRunable=new ServerReadingThread(socket.getInputStream());
		     serverReadingThread=new Thread(serverReadingRunable);
		}catch(Exception e){}
	}

	/**
	 * This method functionality is the same as of method reconnectToServer
	 * So first is trying to killConnection and the creating new connection to server
	 */
	public static void connectToServer(){
		reconnectToServer();
	}
	 /**
     * This method save TCPIPServerListener as week reference.
     * So user don't need to wory about memory leaks.
     * */
    public static void setTCPIPServerListener(TCPIPClientListener list){
    	listener=new WeakReference<TCPIPClientListener>(list);
    }
	/**
     * This is handler which handle all messages from reading thread from server
     * This handler tigers the listener methods
     * */
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
