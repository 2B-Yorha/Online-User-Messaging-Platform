package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;






public class chatServer {
    public static void main(String[] args){
        
        System.out.println("Chat server has Started");
        
        try(ServerSocket serverSocket = new ServerSocket(2008)){
            
            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("New Client Connected");
                ClientHandler handler = new ClientHandler(socket);
                
               new Thread(handler).start(); 
            }
            
            
        } catch (IOException e) {
            e.printStackTrace();
            
        }
        
    }
    
    
    
    
}
