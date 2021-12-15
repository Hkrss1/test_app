package com;

import com.GUI;
import java.io.*;
import java.net.Socket;


public class MessageThread implements Runnable{
    BufferedReader reciever;
    GUI clientUI;
    

    public MessageThread(Socket client,GUI clientUI) throws IOException {
        reciever = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.clientUI = clientUI;      
    }


    @Override
    public void run() {
        while(true)
        {
            try {
                String msg = reciever.readLine();
                if(msg == null)
                {

                }
                else{
                    clientUI.broadcast(msg);
                }
            } catch (IOException e) {
                
            }

        }
    }
}
