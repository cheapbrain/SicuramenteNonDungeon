package happypotatoes.slickgame;

import java.awt.*; import java.awt.event.*;

public class Main extends java.applet.Applet implements ActionListener { Picture pic;

public void init () { setLayout (new BorderLayout ());

Image im = createImage (200, 150); pic = new Picture (im);

Panel p = new Panel (); p.add (pic); add (p, BorderLayout.NORTH);

Button b = new Button ("Draw"); b.addActionListener (this);

p = new Panel (); p.add (b); add (p, BorderLayout.SOUTH); }

public void actionPerformed (ActionEvent e) { pic.draw (); } }

class Picture extends Canvas { private Image buffer; private Graphics context; private int height, width;

Picture (Image buffer) { this.buffer = buffer; context = buffer.getGraphics ();

width = buffer.getWidth (this); height = buffer.getHeight (this); }

public void draw () { for (int row = 0; row < height; row++) for (int col = 0; col < width; col++) { context.setColor (new Color (rnd (256), rnd (256), rnd (256)));

context.drawLine (col, row, col, row); }

repaint (); }

public Dimension getPreferredSize () { return new Dimension (width, height); }

public void paint (Graphics g) { g.drawImage (buffer, 0, 0, this); }

public void update (Graphics g) { paint (g); }

private int rnd (int limit) { return (int) (Math.random () * limit); } }