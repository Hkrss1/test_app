package com;


import com.MessageThread;
import com.GUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    public static Socket socket;
    public static String Username = null;
    private static PrintWriter sender;
    private static BufferedReader reciever;
    private static ExecutorService pool = Executors.newFixedThreadPool(6);
    public static GUI clientUI;
    
    
    


    public static void main(String[] args) throws IOException {


        clientUI = new GUI();
        clientUI.createGUI();
        Username = clientUI.usernameField.getText();
        if(Username != null)
        {
            clientUI.UpdateList(Username);
            socket = new Socket("127.0.0.1", 7778);
        }
        
        
        MessageThread msgThread = new MessageThread(socket,clientUI);
        pool.execute(msgThread);
        sender = new PrintWriter(socket.getOutputStream(), true);


        while (true) {



            
            String msg = clientUI.msgInput.getText();


            if (msg.equals("quit")) {
                clientUI.broadcast("Server Ended Connection");
                sender.close();
                socket.close();
                System.exit(0);
            }
            else if(msg.equals(null))
            {
                //do nothing
            }
            else {
                sender.println(Username + ": " + msg);
            }


        }


    }

}