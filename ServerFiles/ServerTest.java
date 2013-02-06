import javax.swing.JFrame;

public class ServerTest {
   public static void main(String[] args) {
      Server lapis = new Server();
      lapis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      lapis.startRunning();
   }
}