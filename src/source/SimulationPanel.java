package source;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;

import vectors.*;

public class SimulationPanel extends JPanel
		implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener, Runnable{
	/**
	 * @author Maxwell Tang
	 */
	private static final long serialVersionUID = 1L;

	protected boolean[] ispressed = new boolean[65536];
	protected boolean[] isheld = new boolean[65536];
	protected BufferedImage screen;
	protected Point2D Mouse;
	int screenwidth;
	int screenheight;
	int screenarea;
	int loopnum;
	double angle;
	Matrix2D view = Matrix2D.identity();
	BufferedImage[] textures;

	public SimulationPanel(int width, int height, JFrame frame) {
		screenwidth = width;
		screenheight = height;
		screenarea = screenwidth * screenheight;
		screen = new BufferedImage(screenwidth, screenheight, BufferedImage.TYPE_INT_ARGB);
		initListeners(frame);
		initSwing();
		initThreads();
	}

	private void initListeners(JFrame frame) {
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		frame.addKeyListener(this);
		frame.addMouseListener(this);
		frame.addMouseMotionListener(this);
		frame.addMouseWheelListener(this);
	}

	private void initSwing() {
		setPreferredSize(new Dimension(screenwidth, screenheight));
	}
	
	private void initThreads(){
		loopnum = 0;
		new Thread(this).start();
	}
	private void loadimages() {
		URL txt = getClass().getClassLoader().getResource("images/meta.txt");
		int imagecount = 0;
		try {
			Scanner in = new Scanner(txt.openStream());
			imagecount = Integer.parseInt(in.nextLine());
			in.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textures = new BufferedImage[imagecount];
		for (int i = 0; i < imagecount; i++) {
			URL image = getClass().getClassLoader().getResource("images/" + i + ".png");
			try {
				textures[i] = ImageIO.read(image);
				System.out.println("images/" + i + ".png");
			} catch (IOException e) {
			}
		}

	}

	public void paint(Graphics g) {
		g.drawImage(screen, 0, 0, null);
	}

	public void graphicsupdate() {
		for(int i = 0;i<screenarea;i++){
			int r=255;
			int g=0;
			int b=255;
			int a=2;
			screen.setRGB(i%screenwidth, i/screenwidth, (b)+(g<<8)+(r<<16)+(a<<24));
		}
	}

	public void contentupdate() {

	}

	public void run() {
		if(loopnum == 0){
			loopnum = 1;
			new Thread(this).start();
		graphicsLoop();
		}
		if(loopnum == 1){
		contentLoop();
		}
	}
	protected void graphicsLoop(){
		while(true){
			repaint();
			graphicsupdate();
		}
	}
	protected void contentLoop(){
		while(true){
			contentupdate();
		}
	}
	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		ispressed[e.getKeyCode()] = true;

	}

	public void keyReleased(KeyEvent e) {
		ispressed[e.getKeyCode()] = false;

	}

	public void mouseWheelMoved(MouseWheelEvent e) {

	}

	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);

	}

	public void mouseMoved(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		isheld[e.getButton()] = true;

	}

	public void mouseReleased(MouseEvent e) {
		isheld[e.getButton()] = false;

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

}
