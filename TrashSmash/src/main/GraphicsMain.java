package main;

import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import java.net.URL;

import basicplayer1.BasicPlayer;
import res.ImagePanel;
import listeners.ButtonListener;
import listeners.KeyboardListener;

/**
 * Main graphics class for Trash Smash, generates window, starts render thread, creates main menu
 * @author Ben Pinhorn
 */
public class GraphicsMain {
	
	//graphics objects should not be stored here, for drawing game stuff, go to Render.java
	
	//window variables
	public JFrame window = new JFrame("Trash Smash");
	public final static int WIDTH = 1024;
	public final static int HEIGHT = 768;
	
	//music variables
	public BasicPlayer player = new BasicPlayer();
	
	//thread variables
	private Thread renderThread;
	public Render render;
	
	//listeners
	private ButtonListener l;
	private KeyboardListener kl;
	
	//button variables
	private ImageIcon sButton, qButton, iButton;
	
	/**
	 * Constructor for graphics main, opens the window
	 * @param k
	 */
	public GraphicsMain(KeyboardListener k) {
		l = new ButtonListener();
		kl = k;
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		window.setResizable(false);
		window.pack();
		window.setFocusable(true);
		window.addKeyListener(kl);
		init();
	}
	
	private void init() {
		sButton = new ImageIcon(getClass().getClassLoader().getResource("MenuandUI/stbutton.png"));
		qButton = new ImageIcon(getClass().getClassLoader().getResource("MenuandUI/qbutton.png"));
		iButton = new ImageIcon(getClass().getClassLoader().getResource("MenuandUI/infoButton.png"));
	}
	

	/**
	 * Starts render thread 
	 */
	public synchronized void start() {
		renderThread = new Thread(render, "Render Thread");
		renderThread.start();
		try {
			player.stop();
		} catch (basicplayer1.BasicPlayerException e) {
			e.printStackTrace();
		}
	}
	
	
	private void playMusic() {
		try {
			player.stop();
		    player.open(getClass().getClassLoader().getResource("Music/Menu2.mp3"));
		    player.play();
		} catch (basicplayer1.BasicPlayerException e) {
		    e.printStackTrace();
		}
	}
	
	/**
	 * Generates the main menu
	 */
	public JPanel createContentPane() {
		playMusic();
		window.setVisible(false);
		ImagePanel imgPanel = new ImagePanel("MenuandUI/MenuMockup.png");
		
		JPanel contentPane = new JPanel();
		JPanel controlPane = new JPanel();
		JPanel animatePane = new JPanel();
		JPanel buttonsPane = new JPanel();
		
		contentPane.setLayout(new OverlayLayout(contentPane));

		URL url = GraphicsMain.class.getResource("SpinningEarth.gif");
		ImageIcon imageIcon = new ImageIcon(url);
		JLabel label = new JLabel(imageIcon);
		
		JButton startButton = new JButton();
		JButton quitButton = new JButton();
		JButton infoButton = new JButton();
		
		
		startButton.setIcon(sButton);
		startButton.setBorder(null);
		startButton.addActionListener(l);
		startButton.setActionCommand("start");
		
		infoButton.setIcon(iButton);
		infoButton.setBorder(null);
		infoButton.addActionListener(l);
		infoButton.setActionCommand("info");
		
		quitButton.setIcon(qButton);
		quitButton.setBorder(null);		
		quitButton.addActionListener(l);
		quitButton.setActionCommand("quit");
		
		
		controlPane.setLayout(new BoxLayout(controlPane, BoxLayout.Y_AXIS));
		buttonsPane.setLayout(new BoxLayout(buttonsPane, BoxLayout.X_AXIS));
		
		controlPane.add(Box.createRigidArea(new Dimension(20, 170)));
		controlPane.add(startButton);
		
		controlPane.add(Box.createRigidArea(new Dimension(0, 40)));
		controlPane.add(infoButton);
		
		controlPane.add(Box.createRigidArea(new Dimension(0, 40)));
		controlPane.add(quitButton);
		
		buttonsPane.add(controlPane);
		buttonsPane.add(Box.createRigidArea(new Dimension(670, 0)));
		animatePane.add(label);
		
		controlPane.setOpaque(false);
		animatePane.setOpaque(false);
		buttonsPane.setOpaque(false);
		
		animatePane.add(Box.createRigidArea(new Dimension(0,800)));
		
		
		contentPane.add(buttonsPane);
		contentPane.add(animatePane);
		contentPane.add(imgPanel);
		
		window.setContentPane(contentPane);
		window.setVisible(true);
		return contentPane;

	}
	
	/**
	 * Changes the contentPane of the window to the game pane, repacks the window
	 */
	public void gameStart() {
		window.remove(window.getContentPane());
		JPanel gamePanel = new JPanel();
		window.setContentPane(gamePanel);
		window.pack();
		render = new Render((Graphics2D) window.getGraphics());
	}
	
	/**
	 * Removes the game pane
	 */
	public void menuStart() {
		window.remove(window.getContentPane());
	}
}
