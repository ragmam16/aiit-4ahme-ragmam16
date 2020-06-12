/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stopwatchraggammaximilian;

import com.google.gson.Gson;
import java.io.OutputStreamWriter;
import java.net.Socket;
import javax.swing.SwingWorker;

/**
 *
 * @author maximilian
 */
public class ConnectionWorker extends SwingWorker<String, Object>{
    private final Socket socket;

    public ConnectionWorker(Socket socket) {
        this.socket = socket;
    }
    
    
    @Override
    public String doInBackground() throws Exception{
        try{
            final OutputStreamWriter w = new OutputStreamWriter(socket.getOutputStream());
            final Gson file = new Gson();
        }catch(Exception ex){
            ex.getMessage();
        }
    }

    @Override
    protected void done() {
        try {
            System.out.println("done");
        } catch (Exception ignore) {
        }
    }
}
