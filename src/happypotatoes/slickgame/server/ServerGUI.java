package happypotatoes.slickgame.server;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class ServerGUI extends JFrame{
	private static final long serialVersionUID = -2874249336216270780L;
	
	JTextArea area;
	
	public ServerGUI() {
		super("meglio di EUW");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		area = new JTextArea();
		area.setFont(new Font("Consolas", Font.PLAIN, 14));
		area.setEditable(false);
		PrintStream printStream = new PrintStream(new CustomOutputStream(area));
		System.setOut(printStream);
		System.setErr(printStream);
		JScrollPane scroll = new JScrollPane(area);
		add(scroll, BorderLayout.CENTER);
		setSize(400, 600);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private class CustomOutputStream extends OutputStream {
		private JTextArea textArea;

		public CustomOutputStream(JTextArea textArea) {
			this.textArea = textArea;
		}

		@Override
		public void write(int b) throws IOException {
	        textArea.append(String.valueOf((char)b));
	        textArea.setCaretPosition(textArea.getDocument().getLength());
		}
	}
}
