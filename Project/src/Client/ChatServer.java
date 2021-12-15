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
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author risha
 */



    public class ChatServer {

        ServerSocket ss;
        Socket s;


        public ChatServer(){

           try{
               ss = new ServerSocket(7777);
               
               

               while(true)
               {
                   s = ss.accept();
                   Runnable r = new ClientHandler(this.s);
                   Thread t = new Thread(r);
                   t.start();
               }
           }
           catch(IOException e){
           }
           
        }
        
        
        
        public static void main(String [] args){new ChatServer();}

    
}
    

