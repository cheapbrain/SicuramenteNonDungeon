package happypotatoes.slickgame.server;

import happypotatoes.slickgame.network.User;

import java.net.ServerSocket;
import java.util.concurrent.LinkedBlockingQueue;

public class ServerAccept extends Thread{
	ServerSocket serverSocket;
	
	public ServerAccept(LinkedBlockingQueue<User> users) {
		
	}
	
	@Override
	public void run() {
		while(true) {
			
		}
	}
}
