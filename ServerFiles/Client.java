import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Client extends JFrame{

   private JTextArea statusWindow;
   private ObjectOutputStream output;
   private ObjectInputStream input;
   private String message = "";
   private String serverIP;
   private Socket connection;
   
   // Constructor & GUI
   public Client(String host){
      super("Client");
      serverIP = host;
      statusWindow = new JTextArea();
      add(new JScrollPane(statusWindow), BorderLayout.CENTER);
      setSize(300,150);
      setVisible(true);
   }
   
   // Connect to server
   public void startRunning(){
      try{
         connectToServer();
         setupStreams();
         timeout(30);
         // ************* client methods here
      }catch(EOFException eofException){
         showMessage("\n Client terminated connection");
      }catch(IOException ioException){ 
         ioException.printStackTrace(); // Fatal Error
      }finally{
         closeConnection();
      }
   }
   
   // Connect to server
   private void connectToServer() throws IOException{
      showMessage("Attempting connection... \n");
      connection = new Socket(InetAddress.getByName(serverIP), 6789);
      showMessage("Connected to: " + connection.getInetAddress().getHostName() );
   }
   
   // Set up streams to send and receive messages
   private void setupStreams() throws IOException{
      output = new ObjectOutputStream(connection.getOutputStream());
      output.flush();
      input = new ObjectInputStream(connection.getInputStream());
      showMessage("\n Input & Output Streams ready to rock and roll \n");
   }
   
   // Close the streams and sockets
   private void closeConnection(){
      showMessage("\n Closing Connections... \n");
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
