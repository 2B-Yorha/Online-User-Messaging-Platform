# Online-User-Messaging-Platform
A Java socket Multi chat application that makes use of  Java, Socket based Server and JavaFx.

Features of this program Inlcudes:
 * Real Time messaging using TCP sockets;
 * Multiple clients at once;
 * Java Fx Gui
 * server side time stamps;


the layout of this program:
 * src-Client:
    -chatClient.java is responsible for creating the JavaFx GUI for the user. it establish the connection to the server.
    -MessageListener.java is responsible for handling all incoming messages without freezing the GUI.
 * src-server:
   -chatServer.java is Responsible for opening the server socket on a specific port. it 'Listens' for incoming messages and it creates a new thread every time a user connects.
   -UserManager.java adds users in a Safe thread manner and it stores active users and their output streams.
   -ClientHandler.java sends messages to the server for broadcasting and notifies users when another user joins.



*** How To start the program. ***
1) Open Netbeans IDE 8.2
2) you must have Java JDK install. (Any version, but JDK 8+ );
3) rename both src files to 'src';
4) open both projects
5) run the server 'chatSever.java' first
6) then run the client, 'chatClient.java'
7) to run multiple users right click on any open code space and click 'Run File'




