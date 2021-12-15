import java.net.*;
import java.io.*;
import java.util.*;



//creating client
public class Client{

    Socket socket;
    BufferedReader br;
    PrintWriter out;

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

          startReading();
          startWriting();

        }
        catch(Exception e){
          e.printStackTrace();
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

                    System.out.println("Server: "+message);


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
