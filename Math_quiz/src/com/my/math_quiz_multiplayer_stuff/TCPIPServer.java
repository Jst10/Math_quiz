package com.my.math_quiz_multiplayer_stuff;

import java.net.ServerSocket;
import java.util.HashMap;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;

public class TCPIPServer {

	
	public interface TCPIPServerListener{
		
	}
	
    int portTCPIP;
    HashMap<Integer,Client> clietns=null;

    ClientAcceptorThread serverThreadRunable;
    Thread serverThread;
    private static  int port = 21111;
    public TCPIPServer(int port){
    	this.port=port;
    	
    }
    
    
    
    public void restartTcpServer() {
    	killServer();
    	clietns=new HashMap<Integer, Client>();
    	try{
	    	ServerSocket socket=new ServerSocket(port);
	    	socket.setSoTimeout(10000);
	    	serverThreadRunable=new ClientAcceptorThread(socket);
	    	serverThread=new Thread(serverThreadRunable);
	    	serverThread.start();
    	}catch(Exception e){
    	
    	}
    }

    public void killServer(){
    	for(Client client:clietns.values()){
    		client.killClient();
    	}
    	clietns=null;
    	
		try{
			serverThreadRunable.kill();
		}catch(Exception e){}
		try{
			serverThread.stop();
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
				case 1010: //mean that we accept new client 
					Client cl=(Client)msg.obj;
					
					break;
				default: break;
			}
			return false;
		}
	});
}