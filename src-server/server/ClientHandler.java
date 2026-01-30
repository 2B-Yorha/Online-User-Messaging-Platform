package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ClientHandler implements Runnable {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String username;

    private static DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    
    public ClientHandler(Socket socket) {
        
        
        this.socket = socket;
    }
    
    
    private String timeStamp(){
        
        return "[" + LocalTime.now().format(TIME_FORMAT) +"]";
    }
    

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            
            out.println("");
            username = in.readLine();
            
            
            UserManager.addClient(this);
            UserManager.broadcast(timeStamp()+" * " + username+ " joined the chat");
            
       

            String message;
            while ((message = in.readLine()) != null) {
                
                UserManager.broadcast(timeStamp()+"["+ username+"]: "+message);
            }

        } catch (IOException e) {
            
            System.out.println("Client disconnected.");
            
        } finally {
            
            UserManager.removeClient(this);
            try {       
                socket.close();
                
            } catch (IOException e) {
            
            
            }
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }
}
