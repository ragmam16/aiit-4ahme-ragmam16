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
    
    public ConnectionHandler(Socket socket){
        
    }
    
    public boolean isClosed(){
        return false;   
    }
    
    public boolean isMaster(){
        return master; 
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStream);
        String request;
        try {
             request = reader.readLine();
        } catch (IOException ex) {
            throw new IllegalArgumentException();
        }
        Gson datei = new Gson();
        datei.toJason(request);
        
    }
    
}
