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

import com.my.math_quiz.utils.Task;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;

public class TCPIPServer {
	
//TODO on start game I must end accepting new clients and on end game I must resume accepting new clients
	
	
	
	/**
	 * This is listeners for all action that server activity need before game start
	 * */
	public interface TCPIPServerListenerBeforeGame{
		
	}
	/**
	 * This is listeners for all action that server activity need when game already run
	 * */
	public interface TCPIPServerListenerInGame{
		
	}
    static HashMap<Integer,Client> clietns=null;
    static WeakReference<TCPIPServerListenerBeforeGame> listenerBG;
    static WeakReference<TCPIPServerListenerInGame> listenerIG;
    static ClientAcceptorThread serverThreadRunable;
    static Thread serverThread;
    static ServerSocket socket;
    private static  int port = 21111;
   
    /**
     * This method save port number to use it you must call restart or start server method
     * @param portt is the port number which you want to set
     * */
    public static void setPort(int portt){
    	port=portt;
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
	    	Log.d("srDebuging","start server on port: "+port);
	    	socket.setSoTimeout(10000);
	    	serverThreadRunable=new ClientAcceptorThread(socket);
	    	serverThread=new Thread(serverThreadRunable);
	    	serverThread.start();
    	}catch(Exception e){
    		{Log.d("srDebuging","error on trying start server: "+e);}
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
     * This method save TCPIPServerListener before game as week reference.
     * So user don't need to wory about memory leaks.
     * */
    public static void setTCPIPServerListener(TCPIPServerListenerBeforeGame list){
    	listenerBG=new WeakReference<TCPIPServer.TCPIPServerListenerBeforeGame>(list);
    }
    /**
     * This method save TCPIPServerListener in game as week reference.
     * So user don't need to wory about memory leaks.
     * */
    public static void setTCPIPServerListener(TCPIPServerListenerInGame list){
    	listenerIG=new WeakReference<TCPIPServer.TCPIPServerListenerInGame>(list);
    }
    
    /**
     * This is handler which handle all messages from accepting new clients thread
     * and form thread which read all data from clients 
     * This handler tigers the listener methods
     * */
    public static Handler handler=new Handler(new Callback() {
		
		@Override
		public boolean handleMessage(Message msg) {
			Log.d("reciveServer","I recive: "+msg.obj);
			switch(msg.what){
				case 1:
					//we receive request data for specific task
				    //|taskID| request data for specific task
					break;
				case 2:
					//we receive selected answer of one task
				    //|taskId|answer| {selected answer at one task}
					break;
				case 3:
					//we receive request for number of players
				    //{request for number of players}
					break;
				case 4:
					//we receive command for leaving game
				    //{command for leaving game}
					break;
				case 5:
					//we receive request for nickname
				    // playerId {request for nickname}
					break;
				case 6:
					//we receive request for numberOfGames
				    //{request for number of games}
					break;
				case 1010: //mean that we accept new client 
					Log.d("reciveServer","accepted client");
					Client cl=(Client)msg.obj;
					clietns.put(cl.getPlayerId(),cl);
					break;
				default: break;
			}
			return false;
		}
	});
    
    public static void sendTaskToAllClients(Task task){
    	// id=1
	    //|taskNumber|expressiont|answer1|answer2|answer3|answer4|correctNumber
    	for(Client cl :clietns.values()){
    		cl.sendData("bla bla bla");
    	}
    }
    public static void sendSelectdAnswerOfUserToOClients(int taskNumber,int userId,int selectedAnswer){
    	// id=2
	    //|taskNumber|userId|selectedAnswer|
    }
    public static void sendSignalToDisplayCorrectAnsswer(int taskNumber){
    	// id=3
	    // |taskNumber|  {this is signal to display correct result for specific task}
    }
    public static void sendSignalToSwitchToOtherTask(int taskNumber){
    	// id=4
	    //|taskNumber|  {this is signal to switch to specific task in general to new one}
    }
    public static void sendUserDataToClients(int userId,String nickname){
    	// id=5
	    //|userID|nickname|
    }
    public static void sendUserScoreToClients(int userId,int score){
    	// id=6
	    //|userId|score|
    }
    public static void sendRequestToDisplayEndScreen(){
    	// id=7
	    //{nothing else just command to display end screen}
    }
    public static void sendRequestToClearAllDataFromOldTasks(){
    	// id=8
	    //{nothing else just command to clear all data from odl tasks}
    }
    public static void sendNotifyThatServerStoped(){
    	// id=9
	    //{nothing else just command to end server stoped}
    }
    public static void requestClientsNickname(){
    	// id=10
	    //{request for nickname}
    }
    public static void sendNumberOfGames(){
    	// id=11
	    //|numberOfGames|
    }
    
    
    
    
    
}