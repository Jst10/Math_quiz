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

import java.lang.ref.WeakReference;
import java.net.ServerSocket;
import java.util.HashMap;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;

public class TCPIPServer {

	/**
	 * This is listeners for all action that server activity need
	 * */
	public interface TCPIPServerListener{
		
	}
	
	static int portTCPIP;
    static HashMap<Integer,Client> clietns=null;
    static WeakReference<TCPIPServerListener> listener;
    static ClientAcceptorThread serverThreadRunable;
    static Thread serverThread;
    static ServerSocket socket;
    private static  int port = 21111;
   
    /**
     * This method save port number to use it you must call restart or start server method
     * @param port is the port number which you want to set
     * */
    public static void setPort(int port){
    	portTCPIP=port;
    }
    
    /**
     * This method restart server
     * First try to kill sever then create insance of clients
     * then create socket and thread in which accept clients 
     * Time out is set to 10000ms
     * */
    public static void restartTcpServer() {
    	killServer();
    	clietns=new HashMap<Integer, Client>();
    	try{
	    	socket=new ServerSocket(port);
	    	socket.setSoTimeout(10000);
	    	serverThreadRunable=new ClientAcceptorThread(socket);
	    	serverThread=new Thread(serverThreadRunable);
	    	serverThread.start();
    	}catch(Exception e){
    	
    	}
    }
    /**
     * Method to start server,
     * is the same functionality like restartTcpServer
     * in booth way method try to kill server first just to avoid memory leaks and others problems
     * */
    public static void startTcpServer(){
    	restartTcpServer();
    }

    /**This method kill server
     * first is trying to kill all clients then server socket
     * then runable class and at leas the tread whit method stop */
    public static void killServer(){
    	if(clietns!=null){
	    	for(Client client:clietns.values()){
	    		client.killClient();
	    	}
    	}
    	clietns=null;
    	try{
			socket.close();
		}catch(Exception e){}
		try{
			serverThreadRunable.kill();
		}catch(Exception e){}
		try{
			serverThread.stop();
		}catch(Exception e){}
    }
    
    /**
     * This method save TCPIPServerListener as week reference.
     * So user don't need to wory about memory leaks.
     * */
    public static void setTCPIPServerListener(TCPIPServerListener list){
    	listener=new WeakReference<TCPIPServer.TCPIPServerListener>(list);
    }
  
    /**
     * This is handler which handle all messages from accepting new clients thread
     * and form thread which read all data from clients 
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
				case 1010: //mean that we accept new client 
					Client cl=(Client)msg.obj;
					clietns.put(cl.getPlayerId(),cl);
					break;
				default: break;
			}
			return false;
		}
	});
}