package Client;

import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JTextArea;;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Event;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Event;


//creating client
public class Client extends JFrame {

    Socket socket;
    BufferedReader br;
    PrintWriter out;

    //GUI components

    private JLabel header = new JLabel("Client Side");
    private JTextArea messageArea = new JTextArea();
    private JScrollPane scrollPane = new JScrollPane(messageArea);
    private JTextField messageInput = new JTextField();
    private Font hfont = new Font("Roboto",Font.BOLD,20);
    private Font font = new Font("Roboto",Font.PLAIN,20);


    public Client()
    {

        try{

            System.out.println("Sending Request to server.../.");

            socket = new Socket("127.0.0.1",7777);

            System.out.println("Connection Done.");

            br =
          new BufferedReader(new InputStreamReader(socket.getInputStream()));
          //write input
          out =
          new PrintWriter(socket.getOutputStream());

          createGUI();
          handleEvents();
          startReading();


        }
        catch(Exception e){
          e.printStackTrace();
        }

    }

    public void createGUI()
    {
        //gui code
        this.setTitle("Client Messenger[END]");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        //components code
        header.setFont(hfont);
        messageArea.setFont(font);
        messageInput.setFont(font);
        //set Layout
        this.setLayout(new BorderLayout());

        this.add(header,BorderLayout.NORTH);
        this.add(messageArea,BorderLayout.CENTER);
        this.add(messageInput,BorderLayout.SOUTH);

        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        messageArea.setBackground(Color.BLUE);
        messageArea.setForeground(Color.WHITE);





    }

    public void handleEvents()
    {
        messageInput.addKeyListener( new java.awt.event.KeyListener(){
          @Override
          public void keyTyped(java.awt.event.KeyEvent e)
          {

          }

          @Override
          public void keyPressed(java.awt.event.KeyEvent e)
          {

          }

          @Override
          public void keyReleased(java.awt.event.KeyEvent e)
          {
            if(e.getKeyCode() == 10)
            {
              String contentToSend = messageInput.getText();
              messageArea.append("Me: " + contentToSend+"\n");
              out.println(contentToSend);
              out.flush();
              messageInput.setText("");
              messageInput.requestFocus();
            }
          }
        });



    }

    public void startReading()
    {
        Runnable r1=()->{

          System.out.println("Reader Started.");

          try{
              while(true)
              {


                    String message = br.readLine();
                    if(message.equals("exit"))
                    {
                        System.out.println("Chat Terminated");
                        JOptionPane.showMessageDialog(this, "Chat terminated.");
                        messageInput.setEnabled(false);
                        socket.close();
                        break;
                    }

                    messageArea.append("Server: "+message+"\n");


              }
            }
          catch(Exception e)
          {
            e.printStackTrace();
          }

        };

        new Thread(r1).start();
    }

    public void startWriting()
    {
        Runnable r2=()->{

          System.out.println("Writer Started.");

          try{
            while(true)
            {


                BufferedReader in =
                new BufferedReader(new InputStreamReader(System.in));
                String data = in.readLine();
                out.println(data);
                out.flush();
                if(data.equals("exit"))
                {
                  socket.close();
                  break;
                }


            }
          }catch(Exception e){
            e.printStackTrace();
          }

        };

        new Thread(r2).start();
    }



    public static void main(String[] args) {


      System.out.println("This is Client.");
      new Client();

    }
}
