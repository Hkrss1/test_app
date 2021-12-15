import java.net.*;
import java.io.*;
import java.util.*;

//creating server
public class Server{

    ServerSocket server;
    Socket socket;
    BufferedReader br;
    PrintWriter out;

    //constructor
    public Server()
    {
        try {
          server = new ServerSocket(7777);    //server socket
          System.out.println("Server is ready.");
          System.out.println("Waiting for Connection....");
          socket = server.accept();     //incoming request stored to socket
          //read Input
          br =
          new BufferedReader(new InputStreamReader(socket.getInputStream()));
          //write input
          out =
          new PrintWriter(socket.getOutputStream());

          startReading();
          startWriting();


        }
        catch(Exception e){
          e.printStackTrace();    //exception
        }
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
                        socket.close();
                        break;
                    }

                    System.out.println("Client: "+message);
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
              while(true && !socket.isClosed())
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

          }
          catch(Exception e)
          {
            e.printStackTrace();
          }

        };

        new Thread(r2).start();
    }

    public static void main(String[] args)
    {

        System.out.println("Hello, This is server. Starting Server.../.");
        new Server();

    }

}
