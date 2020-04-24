/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverclientstopuhr;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;
import java.util.ArrayList;

/**
 *
 * @author maxim
 */
public class Server {
    private ServerSocket serverSocket;
    private final ArrayList<ConnectionHandler> handlers = new ArrayList<ConnectionHandler>();
    private long timeOffset;
    private long startMillis;
    
    public void start(int port) throws IOException{
        timeOffset = 0;
        startMillis = System.currentTimeMillis();
        serverSocket = new ServerSocket(port);
        final Socket clientSocket = serverSocket.accept();
        ConnectionHandler handler = new ConnectionHandler(clientSocket);
        Socket socket = null;
        
        while(true){
            socket = serverSocket.accept();
            new Thread(new ConnectionHandler(socket)).start();
            new Thread(handler).start();
            handlers.add(handler);
            if(handlers.size() == 3){
                serverSocket.close();
            }else{
                serverSocket.accept();
            }
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public ArrayList<ConnectionHandler> getHandlers() {
        return handlers;
    }

    public long getTimeOffset() {
        return timeOffset;
    }

    public long getStartMillis() {
        return startMillis;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void setTimeOffset(long timeOffset) {
        this.timeOffset = timeOffset;
    }

    public void setStartMillis(long startMillis) {
        this.startMillis = startMillis;
    }
    
    public boolean isTimerRunningreturn(){
        return false;
    }
    public long getTimerMillis(){
        return timeOffset; 
    }
    
    public static void main(String[] args) {
        new Server();
    }
}
