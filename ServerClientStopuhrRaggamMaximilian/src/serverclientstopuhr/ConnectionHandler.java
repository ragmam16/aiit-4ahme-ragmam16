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
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maxim
 */
public class ConnectionHandler implements Runnable{
    private Socket socket;
    private boolean master;
    Server s;
    BufferedReader reader = null;
    String read = null;
    int count = 0;
    
    
    public ConnectionHandler(Socket socket){
        this.socket = socket;
    }
    
    public boolean isClosed(){
        return socket.isClosed();   
    }
    
    public boolean isMaster(){
        return master; 
    }

    @Override
    public void run() {
        while(true){
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                read = reader.readLine();
                count++;
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            Gson gson = new Gson();
            Request request = gson.fromJson(read, Request.class);


            if(request.isMaster()){
                boolean setMasterTrue = true;
                for(ConnectionHandler h : s.getHandlers()){
                    if(h != this && h.isMaster() == true){
                        setMasterTrue = false;
                    }
                }
                master = setMasterTrue;
            }
            if(request.isMaster()){
                if(request.isStart()){

                }
                if(request.isClear()){
                    s.setTimeOffset(0);
                }
                if(request.isStop()){
                    s.setStartMillis(-1);
                }else{
                    s.setTimeOffset(System.currentTimeMillis() - s.getStartMillis());
                }
                if(request.isEnd()){
                    try {
                        socket.close();
                        s.getHandlers().remove(this);
                        return;
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            try {
                Response response = new Response(master, count,s.isTimerRunning(), s.getTimerMillis());
                String message = gson.toJson(response);
                OutputStreamWriter write = new OutputStreamWriter(socket.getOutputStream());
                write.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
