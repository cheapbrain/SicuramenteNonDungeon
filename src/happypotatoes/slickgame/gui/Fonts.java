package happypotatoes.slickgame.gui;

import java.awt.Font;

import org.newdawn.slick.TrueTypeFont;

public class Fonts {
	public static TrueTypeFont font1;
	
	public static void init() {
		Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
		font1 = new TrueTypeFont(awtFont, true);
	}
}
