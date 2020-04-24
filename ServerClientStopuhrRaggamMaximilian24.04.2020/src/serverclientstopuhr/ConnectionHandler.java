/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverclientstopuhr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String read = reader.readLine();
        } catch (IOException ex) {
            throw new IllegalArgumentException();
        }
        Gson datei = new Gson();
        Request r = datei.fromJson(read, Request.class);
        
        
        if(r.isMaster()){
            boolean setMasterTrue = true;
            for(ConnectionHandler h : s.getHandlers()){
                if(h != this && h.isMaster() == true){
                    setMasterTrue = false;
                }
            }
            master = setMasterTrue;
        }
        if(r.isMaster()){
            if(r.isStart()){

            }
            if(r.isClear()){
                s.setTimeOffset(0);
            }
            if(r.isStop()){
                s.setStartMillis(-1);
            }else{
                s.setTimeOffset(System.currentTimeMillis() - s.getStartMillis());
            }
            if(r.isEnd()){
                try {
                    socket.close();
                } catch (IOException ex) {
                    throw new IllegalArgumentException();
                }
            }
        }
        Response reponse = null;
        reponse = new Response(master, reponse.isCount(), r.isStop(), s.getTimeOffset());
    }
    
}
