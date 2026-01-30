package client;

import java.io.BufferedReader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class chatClient extends Application {

    private TextArea chatArea;
    private TextField messageField;
    private Button sendButton;

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Stage primaryStage;
    private String username;
    

    @Override
    public void start(Stage stage) {
        
        this.primaryStage = stage;
        
        
        chatArea = new TextArea();
        chatArea.setEditable(false);
        chatArea.setWrapText(true);

        messageField = new TextField();
        messageField.setPromptText("Type a message");

        sendButton = new Button("Send");

        sendButton.setOnAction(e -> sendMessage());
        messageField.setOnAction(e -> sendMessage());

        HBox bottom = new HBox(10, messageField, sendButton);
        bottom.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setCenter(chatArea);
        root.setBottom(bottom);
        
        

        stage.setTitle("Chat Client");
        stage.setScene(new Scene(root, 400, 500));
        stage.show();

        connectToServer();

        stage.setOnCloseRequest(e -> closeConnection());
    }

    private void connectToServer() {
        try {
            socket = new Socket("localhost", 2008);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            String serverMsg = in.readLine();
            
            
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Username");
            dialog.setHeaderText("Enter Your Username");
            String Username = dialog.showAndWait().orElse("Guest");
            
            
            out.println(Username);
            Platform.runLater(() -> primaryStage.setTitle("Chat - " + username));
            

            MessageListener listener = new MessageListener(socket, chatArea);
            
            
            
            new Thread(listener).start();

        } catch (IOException e) {
            showError("Cannot connect to server");
        }
    }

    private void sendMessage() {
        String message = messageField.getText().trim();
        
        
        if (!message.isEmpty()) {
            out.println(message);
            messageField.clear();
        }
        
    }

    private void closeConnection() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            
        }
        
        
        Platform.exit();
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg);
        alert.showAndWait();
        Platform.exit();
    }

    public static void main(String[] args) {
        
        
        
        launch(args);
    }

  
}
