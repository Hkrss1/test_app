/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author risha
 */
public class ClientHandler implements Runnable {
    
    Socket client;
    BufferedReader in;
    PrintWriter out;
    String Message;
    
    public ClientHandler(Socket s) 
    {
        this.client = s;
        try {
            in =
                    new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            out =
                    new PrintWriter(client.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void run() {
         try {
                Message = in.readLine();
                out.write(Message);
                out.flush();
            } catch (IOException ex) {
                Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
}
