/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverclientstopuhr;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Christoph-PC
 */
public class Server {
    private ServerSocket serversocket;
    private final List<ConnectionHandler> handlers = new ArrayList<>();
    private long timeOffset;
    private long startMillis;

    public void start(int port) throws IOException {        
        serversocket = new ServerSocket(port);
        while(true) {
            final Socket socket = serversocket.accept();
            for(ConnectionHandler h : handlers) {
                if(h.isClosed()) {
                    handlers.remove(h);
                }
            }
            if(handlers.size() == 3) {
            	socket.close();
                continue;
            }
            final ConnectionHandler handler = new ConnectionHandler(socket);
            new Thread(handler).start();
            handlers.add(handler);
        }
    }

    public long getTimeOffset() {
        return timeOffset;
    }

    public long getStartMillis() {
        return startMillis;
    }
    

    public List<ConnectionHandler> getHandlers() {
        return handlers;
    }

    public void setServersocket(ServerSocket serversocket) {
        this.serversocket = serversocket;
    }

    public void setTimeOffset(long timeOffset) {
        this.timeOffset = timeOffset;
    }

    public void setStartMillis(long startMillis) {
        this.startMillis = startMillis;
    }
    
    
    
    public boolean isTimerRunning() {
        return startMillis > 0;
    }
    
    public long getTimerMillis() {
    	if(startMillis > 0) {
            return System.currentTimeMillis() - startMillis + timeOffset;
        } else {
            return timeOffset;
        }
    }
    
    public static void main(String[] args) throws IOException {
        new Server().start(8080);
    }    
}
