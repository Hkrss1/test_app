/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;
import GUI.GUI.*;
import GUI.*;
import LoginPage.LoginPage;

import java.net.*;
import java.io.*;

/**
 *
 * @author risha
 */
public class Client  {
    
    String Username ;
    String IP;
    Socket socket;
    BufferedReader br;
    PrintWriter out;
    GUI gui;
    
    
    public Client(/*String Username, String IP*/)
    {
       
        
    
        try{

            

            socket = new Socket(IP,7777);
           

            

            br =
          new BufferedReader(new InputStreamReader(socket.getInputStream()));
          //write input
          out =
          new PrintWriter(socket.getOutputStream());
          
          
            gui = new GUI();
            gui.CreateGUI();
          
          
          
          
                                     
          

        }
        catch(Exception e){
          
        }
    }  
    
    
    public static void main(String[] args)
    {
        new Client();
    }
     
    
    
}




