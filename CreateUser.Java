//prototype
//user
import java.net.*;
import java.io.*;
import java.util.*;
import java.net.InetAddress;
public class CreateUser {
    static String userName;
    static String ip;


    //constructor
    public CreateUser()
    {
        setUserName();
        fetchIP();
    }
    public void setUserName()
    {
        System.out.println("Set a User Name: ");
        Scanner nScan = new Scanner(System.in);
        userName = nScan.nextLine();
    }
    public void fetchIP() throws IOException
    {
        // Returns the instance of InetAddress containing
        // local host name and address
        InetAddress localhost = InetAddress.getLocalHost();

        // Find public IP address
        String systemipaddress = "";
        try
        {
            URL url_name = new URL("http://bot.whatismyipaddress.com");

            BufferedReader sc =
            new BufferedReader(new InputStreamReader(url_name.openStream()));

            // reads system IPAddress
            systemipaddress = sc.readLine().trim();
            ip = systemipaddress;
        }
        catch(Exception e)
        {
            systemipaddress = "Cannot Execute Properly";
            e.printStackTrace();
        }


    }

    public String getUsername()
    {
        return userName;
    }

    public String getIP()
    {
        return ip;
    }

    public static void main(String[] args) throws Exception{

       CreateUser user1 = new CreateUser();
       user1.setUserName();
       user1.fetchIP();
       System.out.println("New User: "+ userName + "\nIP: "+ip+"\nhas signed Up." );
    }
}
