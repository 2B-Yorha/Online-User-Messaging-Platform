package server;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class UserManager {

    private static Set<ClientHandler> clients =ConcurrentHashMap.newKeySet();

    public static void addClient(ClientHandler client) {
        clients.add(client);
    }

    public static void removeClient(ClientHandler client) {
        
        clients.remove(client);
    }


    public static void broadcast(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }
}
