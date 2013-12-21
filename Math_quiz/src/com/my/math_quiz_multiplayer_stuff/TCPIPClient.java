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
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;

public class TCPIPClient {
	
	/**
	 * This is listeners for all action that client activity need before game
	 * */
	public interface TCPIPClientListenerBeforeGame{
		
	}
	/**
	 * This is listeners for all action that client activity need in game
	 * */
	public interface TCPIPClientListenerInGame{
		
	}
	
	static String ipAdress;
	static int port;
	static WeakReference<TCPIPClientListenerBeforeGame> listenerBG;
	static WeakReference<TCPIPClientListenerInGame> listenerIG;

	static Socket socket=new Socket();;
	static BufferedWriter out;
	static ServerReadingThread serverReadingRunable;
	static Thread serverReadingThread;
	
	static Thread threadForOpeningConnection;
	static RunableForOpeningConnection runableForConnectionOpening;
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
				threadForOpeningConnection.stop();
			}catch(Exception e){}
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
			  runableForConnectionOpening=new RunableForOpeningConnection();
			  threadForOpeningConnection=new Thread(runableForConnectionOpening);
			  threadForOpeningConnection.start();
	          
		}catch(Exception e){Log.d("clDebuging","error on trying connecting1: "+e);}
		
	}

	
	
	//I must add this to background because android don't allow to do this in main thread
	 private static class RunableForOpeningConnection implements Runnable
	 {
		
		 public RunableForOpeningConnection()
		 {		 }
        public  void run()
        {
        	try{
        		socket=new Socket();
//        		socket.bind(null);
        	 socket.connect(new InetSocketAddress(ipAdress, port));
//   			 socket = new Socket(ipAdress, port);
        	
        		
//        		SocketAddress sockaddr = new InetSocketAddress("192.168.1.10", 1234);
//            	socket=new Socket();//adrr,Integer.parseInt(IpPort));
//            	socket.connect(sockaddr,5000);
            	
   		     out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
   			
   		     serverReadingRunable=new ServerReadingThread(socket.getInputStream());
   		     serverReadingThread=new Thread(serverReadingRunable);
        	}catch(Exception e){Log.d("clDebuging","error on trying connecting2: "+e);}
        }
     }
	
	
	
	
	
	
	
	
	/**
	 * This method functionality is the same as of method reconnectToServer
	 * So first is trying to killConnection and the creating new connection to server
	 */
	public static void connectToServer(){
		reconnectToServer();
	}
	 /**
     * This method save TCPIPServerListener before game as week reference.
     * So user don't need to wory about memory leaks.
     * */
    public static void setTCPIPClientListener(TCPIPClientListenerBeforeGame list){
    	listenerBG=new WeakReference<TCPIPClientListenerBeforeGame>(list);
    }
    /**
     * This method save TCPIPServerListener in game as week reference.
     * So user don't need to wory about memory leaks.
     * */
    public static void setTCPIPClientListener(TCPIPClientListenerInGame list){
    	listenerIG=new WeakReference<TCPIPClientListenerInGame>(list);
    }
	/**
     * This is handler which handle all messages from reading thread from server
     * This handler tigers the listener methods
     * */
	  public static Handler handler=new Handler(new Callback() {
			
			@Override
			public boolean handleMessage(Message msg) {
				Log.d("reciveClient","I recive: "+msg.obj);
				switch(msg.what){
					case 1:
						//we receive data of one task
					    //|taskNumber|expressiont|answer1|answer2|answer3|answer4|correctNumber
						break;
					case 2:
						//we receive answer of one user
					    //|taskNumber|userId|selectedAnswer|
						break;
					case 3:
						//we receive signal to display correct answer
					    // |taskNumber|  {this is signal to display correct result for specific task}
						break;
					case 4:
						//we receive signal to switch to specific task probably next
					    //|taskNumber|  {this is signal to switch to specific task in general to new one}
						break;
					case 5:
						//we receive nickname of one user
					    //|userID|nickname|
						break;
					case 6:
						//we receive score of one user
					    //|userId|score|
						break;
					case 7:
						//we receive command to display end screen
					    //{nothing else just command to display end screen}
						break;
					case 8:
						//we receive to clear all data of old tasks
					    //{nothing else just command to clear all data from odl tasks}
						break;
					case 9:
						//we receive command to end server stoped (server has been shutdown)
					    //{nothing else just command to end server stoped}
						break;
					case 10:
						//we receive request for nickname
					    //{request for nickname}
					case 11:
						//we receive number of games
					    // |numberOfGames|
						break;
					default: break;
				}
				return false;
			}

	  });
	  
	  public static void sendRequestForTaskDescription(int taskNumber){
		// id=1
		//|taskID| request data for specific task
	  }
	  public static void sendSelectedAnswer(int taskNumber,int selectedAnswer){
		// id=2
		//|taskId|answer| {selected answer at one task}
	  }
	  public static void sendCommandForLeavingGame(){
		// id=3
	    //{request for number of players}
	  }
	  public static void sendRequestForNumberOfPlayers(){
		// id=4
	    //{command for leaving game}
	  }
	  public static void sendRequestForPlayerNickname(int playerID){
		// id=5
	    // playerId {request for nickname}
	  }
	  public static void sendRequestForNumberOfGames(){
		// id=6
	    //{request for number of games}
	  }
	  
}
