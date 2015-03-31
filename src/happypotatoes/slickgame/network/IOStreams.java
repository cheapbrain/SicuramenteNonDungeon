package happypotatoes.slickgame.network;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;


public class IOStreams extends Thread{
	private Socket socket;
	private DataOutputStream out;
	private DataInputStream in;
	private LinkedBlockingQueue<String> inputData;
	private boolean connected = true;
	
	public IOStreams(Socket socket) {
		super();
		
		try {
			this.socket = socket;
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			connected = false;
		}
		inputData = new LinkedBlockingQueue<String>();
		
		start();
	}
	
	public void sendMessage(String message) {
		try {
			out.writeUTF(message);
		} catch (IOException e) {
			e.printStackTrace();
			connected = false;
		}
	}

	public boolean isEmpty() {
		return inputData.isEmpty();
	}
	
	public String getMessage() {
		return inputData.poll();
	}
		
	public boolean isConnected() {
		return connected;
	}
	
	public void disconnect() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		connected = false;
	}
	
	public void run() {
		
		try {
			while(true) {
				inputData.add(in.readUTF());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			connected = false;
		}
		
	}
	
}
