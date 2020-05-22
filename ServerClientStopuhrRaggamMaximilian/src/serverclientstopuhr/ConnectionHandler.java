/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverclientstopuhr;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import static java.lang.System.in;
import static java.lang.module.ModuleDescriptor.read;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maxim
 */
public class ConnectionHandler implements Runnable{
        private final Socket socket;
        private boolean master;
        public Server s;
        private ServerSocket serversocket;

         
        public ConnectionHandler(Socket socket) {
             this.socket = socket;
        }
         
        public boolean isClosed() {
             return socket.isClosed();
        }
         
        public boolean isMaster() {
            return master;
        }
         
        @Override
        public synchronized void run() {
            int count = 0;
            while(true) {
                try {
                    final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    final OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
                    final String req = reader.readLine();
                    
                    count++;
                    final Gson gson = new Gson();
                    final Request r = gson.fromJson(req, Request.class);

                    if(r.isMaster()) {
                        boolean setMasterTrue = true;
                        synchronized(s.getHandlers()){
                            for(ConnectionHandler h : s.getHandlers()) {
                                if(!h.equals(this) && h.isMaster() == true) {
                                    setMasterTrue = false;
                                    break;
                                }
                            }
                        }
                        master = setMasterTrue;
                    }
                    if(req == null) {
                        socket.close();
                        break;
                    }
                    if(r.isMaster()) {
                        if(r.isStart()) {
                            s.setStartMillis(System.currentTimeMillis());
                        }

                        if(r.isStop()) {
                            s.setStartMillis(0);
                        } else {
                            s.setTimeOffset(System.currentTimeMillis() - s.getStartMillis() + s.getTimeOffset());
                        }

                        if(r.isClear()) {
                            s.setTimeOffset(0);
                            if(s.isTimerRunning()) {
                                s.setStartMillis(System.currentTimeMillis());
                            } else {
                                s.setStartMillis(0);
                            } 
                        }
                        if(r.isEnd()) {
                            serversocket.close();
                            socket.close();
                            synchronized(socket){
                            }
                            s.getHandlers().remove(this);
                            return;
                        }        
                    }
                    final Response response = new Response(master, count, s.isTimerRunning(), s.getTimerMillis());
                    final String gsonString = gson.toJson(response);
                    writer.write(gsonString);
                    writer.flush();
                } catch(Exception ex) {
                    ex.printStackTrace();
                } 
            }
        }
    }
    
}
