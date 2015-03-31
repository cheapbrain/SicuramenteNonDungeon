package happypotatoes.slickgame.network;

import java.net.Socket;

public class User extends IOStreams{
	private String username;
	private boolean logged = false;

	public User(Socket socket) {
		super(socket);
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}
	
	

}
