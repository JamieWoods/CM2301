import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;

public class login extends JPanel
{
  JButton registerButton, submitButton;
	JTextField username, password;
	//declaring image
	BufferedImage loginImage;

	//constructor
	public login(BufferedImage loginImage)
	{
		this.loginImage = loginImage;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//setting width and height of image.
        int x = (getWidth() - loginImage.getWidth())/4;
        int y = (getHeight() - loginImage.getHeight())/4;
		g.drawImage(loginImage, x, y, this);
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

	//register frame currently in register.java

	/*public void newFrame()
	{
		JFrame registerFrame = new JFrame("Register! ");
		registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Setting size of applet.
		registerFrame.setSize(800,600);
		//defining my own positions
		registerFrame.setLayout(null);
		//define location on screen;
		registerFrame.setLocation(200,100);
		//preventing user from changing the size
		registerFrame.setResizable(false);

		registerFrame.setVisible(true);
	}*/

	public static void main(String args[]) throws IOException
	{
		//image path
		String location = "LogIn_Bare.png";
		String location2 = "background.png";

		BufferedImage loginImage = ImageIO.read(new File(location));
		BufferedImage backgroundImage = ImageIO.read(new File(location2));

        login loginScreen = new login(loginImage);

        loginScreen.setOpaque(true);
        loginScreen.setLayout(new GridBagLayout());

		JFrame jf= new JFrame("Login ");

		jf.setContentPane(loginScreen);
		//enable close tool.
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Setting size of applet.
		jf.setSize(800,600);
		//defining my own positions
		jf.setLayout(null);
		//define location on screen;
		jf.setLocation(200,100);
		//preventing user from changing the size
		jf.setResizable(false);
		//All above set to visible
		loginScreen.components();
		jf.setVisible(true);
	}
}
