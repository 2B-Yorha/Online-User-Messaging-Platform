package client;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MessageListener implements Runnable {

    private Socket socket;
    private TextArea chatArea;

    public MessageListener(Socket socket, TextArea chatArea) {
        
        this.socket = socket;
        this.chatArea = chatArea;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message;
            while ((message = in.readLine()) != null) {
                String msg = message;
                Platform.runLater(() ->chatArea.appendText(msg + "\n"));
            }

        } catch (IOException e) {
            Platform.runLater(() ->chatArea.appendText("Disconnected from server.\n"));
         
        }
    }
}
