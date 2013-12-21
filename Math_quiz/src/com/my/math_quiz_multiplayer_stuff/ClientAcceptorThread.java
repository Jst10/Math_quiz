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

import java.net.ServerSocket;
import java.net.Socket;

import android.os.Message;
import android.util.Log;

class ClientAcceptorThread implements Runnable
{

	private static int clientID=0;
	public boolean work;
	ServerSocket serversocketTCPIP;
	public ClientAcceptorThread( ServerSocket serversocketTCPIP){
		work=true;
		this.serversocketTCPIP=serversocketTCPIP;
	}
	
	public void kill(){
		work=false;
		try{
			serversocketTCPIP.close();
		}catch(Exception e){
			
		}
	}
	
	@Override
	public void run() {
		while(work&&serversocketTCPIP!=null){
			Log.d("srDebuging","while client accepting");
			try{
				Socket client=serversocketTCPIP.accept();
				if(client!=null){
					clientID++;
					ClientReadingThread clR=new ClientReadingThread(client.getInputStream(),clientID);
					Thread thredTCPIP=new Thread(clR);
					Message tmp=TCPIPServer.handler.obtainMessage();
					tmp.what=1010;
					//we append id of client to message
					tmp.obj=new Client(clientID,client,clR,thredTCPIP);
					TCPIPServer.handler.sendMessage(tmp); 
					thredTCPIP.start();
					
				}
			}catch(Exception e){
				Log.d("srDebuging","error client accepting "+e);
			}
		}
	}
	

}