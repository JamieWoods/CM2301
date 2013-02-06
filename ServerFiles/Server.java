import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Server extends JFrame{
   
   private JTextArea statusWindow;
   private ObjectOutputStream output;  
   private ObjectInputStream input;
   private ServerSocket server;
   private Socket connection;
   
   // Constructor & GUI
   public Server(){
      super("Online Casino Server Connection");
      statusWindow = new JTextArea();
      add(new JScrollPane(statusWindow));
      setSize(300,150);
      setVisible(true);
   }
   
   // Set up and run the server
   public void startRunning(){
      try{
         server = new ServerSocket(6789, 100); // port no, max users
         while(true){
            try{
               waitForConnection();
               setupStreams();
               timeout(30);
               //  Continuous Methods run here
            }catch(EOFException eofException){
               showMessage("\n Server ended the connection! ");
            }finally{
               closeConnection();
            }
         }
      }catch(IOException ioException){
         ioException.printStackTrace();
      }
   }
   
   // Wait for connection
   private void waitForConnection() throws IOException{
      showMessage("Attempting connection... \n");
      connection = server.accept();
      showMessage("Connected to: " + connection.getInetAddress().getHostName() );
   }
   
   // Get stream to send and receive data
   private void setupStreams() throws IOException{
      output = new ObjectOutputStream(connection.getOutputStream());
      output.flush();
      input = new ObjectInputStream(connection.getInputStream());
      showMessage("\n Input & Output Streams ready to rock and roll \n");
   }
   
   // Close streams and sockets 
   private void closeConnection(){
      showMessage("\n Closing connections... \n");
      try{
         output.close();
         input.close();
         connection.close();
      }catch(IOException ioException){
         ioException.printStackTrace();
      }
   }

   // Update Status Window
   private void showMessage(final String m){
      SwingUtilities.invokeLater(
         new Runnable(){
            public void run(){
               statusWindow.append(m);
            }
         }
      );
   }

   // Waits for n seconds 
   public static void timeout (int n){

      long t0, t1;
      t0 =  System.currentTimeMillis();
      do{
         t1 = System.currentTimeMillis();
      }
      while ((t1 - t0) < (n * 1000));
   }
   
}