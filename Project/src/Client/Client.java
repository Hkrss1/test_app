/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;


import java.io.*;
import java.net.*;


/**
 *
 * @author risha
 */
public class Client {
    
    String Username;
    String Message;
    Status status;
    Socket socket;
    BufferedReader br;
    PrintWriter out;
    
    public Client(String Username)
    {
        this.Username = Username;
        try{

            System.out.println("Sending Request to server.../.");

            socket = new Socket("127.0.0.1",7777);

            System.out.println("Connection Done.");

            br =
          new BufferedReader(new InputStreamReader(socket.getInputStream()));
          //write input
          out =
          new PrintWriter(socket.getOutputStream());
        }
        catch(IOException e){
        }
    }
    
   
    
}
