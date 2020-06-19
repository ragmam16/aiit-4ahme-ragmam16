/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stopwatchraggammaximilian;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;
import javax.swing.SwingWorker;

/**
 *
 * @author maximilian
 */
public class ConnectionWorker extends SwingWorker<Object, Response>{
    private Socket socket;

    public ConnectionWorker(int port, String hostName) throws IOException{
    }

    @Override
    protected String doInBackground() throws Exception{
         
        final Gson gson = new Gson();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        final OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
        while(true){
            try{
                final Request request = new Request();
                final String sRequest = gson.toJson(request);
                writer.write(sRequest);
                writer.flush();
                 
                final String sResponse = reader.readLine();
                final Response response = gson.fromJson(sResponse, Response.class);
                publish(response);
                
                Thread.sleep(1000);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
}
