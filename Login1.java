import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;

public class login1 extends JFrame //implements ActionListener
{
  JButton registerButton, submitButton;
	JTextField username, password;
	//declaring image
	BufferedImage backgroundImage;
	//constructor
	public login1(BufferedImage backgroundImage)
	{
		this.backgroundImage = backgroundImage;
	}
	public login1()
	{
		//image path
		String location = "LogIn_Bare.png";
		//Added try and Catch to avoid errors
		try
		{
			 backgroundImage = ImageIO.read(new File(location));
		}
			catch(IOException e)
		{
			System.out.println(e);
		}
		setTitle("Login ");
		//enable close tool.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Setting size of applet.
		setSize(800,600);
		//defining my own positions
		//setLayout(null);
		//define location on screen;
		setLocation(200,100);
		//preventing user from changing the size
		setResizable(false);
		//All above set to visible
		setVisible(true);
		//Add the background
		backgroundPane bp = new backgroundPane();
		add(bp);
		bp.setOpaque(true);
		bp.repaint();
	}

	public void components()
	{
		username = new JTextField(10);
		username.setBounds(220,270,325,40);
		username.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
		username.setText("Username ");
		//Adds button to screen.
		add(username);

		password = new JTextField(10);
		//bounds = horizontal, vertical, height, width
		password.setBounds(220,320,325,40);
		password.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
		password.setText("Password ");
		add(password);

		JButton registerButton = new JButton("Register");
		registerButton.setBounds(220,380,110,40);
		registerButton.setBackground(Color.white);
		add(registerButton);

		//Low Level Mouse listener, opens new frame when button is clicked.
		registerButton.addMouseListener(new java.awt.event.MouseAdapter()
		{
    		public void mouseClicked(java.awt.event.MouseEvent evt)
    		{
				//newFrame();
				//calling constructor from register.java
        		register registerScreen = new register();
    		}
		});


		JButton submitButton = new JButton("Sign-in");
		submitButton.setBounds(435,380,110,40);
		submitButton.setBackground(Color.white);
		add(submitButton);



	}
	public static void main(String args[]) throws IOException
	{
		//This doesn't get triggerd as nothing is looking for main
		//This line will allow this file to be standalone as
		//well as intergratable
		new login1();
	}
	//Subclass that will be used for drawing
	class backgroundPane extends JPanel
  	{
  		public void paint(Graphics g)
  		{
		super.paint(g);
		//setting width and height of image.
        int x = (getWidth() - backgroundImage.getWidth())/4;
        int y = (getHeight() - backgroundImage.getHeight())/4;
		g.drawImage(backgroundImage, x, y, this);
		components();
  		}
  	}
}
