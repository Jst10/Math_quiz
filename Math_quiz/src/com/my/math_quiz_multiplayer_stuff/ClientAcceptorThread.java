package com.my.math_quiz_multiplayer_stuff;

import java.net.ServerSocket;
import java.net.Socket;

import android.os.Message;

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
				
			}
		}
	}
	

}