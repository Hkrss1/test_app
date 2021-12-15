package Client.ChatWindow;

import java.io.*;
import java.net.Socket;
import Client.messages.*;
import Client.LoginWindow.LoginController;

public class Listener implements Runnable {

    private static final String HASCONNECTED = "has connected";
    private static String picture;
    private Socket socket;
    public String hostname;
    public int port;
    public static String username;
    public ChatController controller;
    private static ObjectOutputStream oos;
    private InputStream is;
    private ObjectInputStream input;
    private OutputStream outputStream;

    //contructor
    public Listener(String hostname, int port, String username, String picture, ChatController controller) {
        this.hostname = hostname;
        this.port = port;
        Listener.username = username;
        this.controller = controller;
    }


    //run method
    public void run() {
        try {
            this.socket = new Socket(this.hostname, this.port);
            LoginController.getInstance().loadChat();
            this.outputStream = this.socket.getOutputStream();
            oos = new ObjectOutputStream(this.outputStream);
            this.is = this.socket.getInputStream();
            this.input = new ObjectInputStream(this.is);
        } catch (IOException var2) {

        } catch (Exception e) {

        }


        try {
            connect();


            while(this.socket.isConnected()) {
                Message message = null;
                message = (Message)this.input.readObject();
                if (message != null) {

                    switch(message.getType()) {
                        case USER:
                            this.controller.addToChat(message);
                            break;
                        case SERVER:
                            this.controller.addAsServer(message);
                            break;
                        case CONNECTED:
                            this.controller.setUserList(message);
                            break;
                        case DISCONNECTED:
                            this.controller.setUserList(message);
                            break;
                        case STATUS:
                            this.controller.setUserList(message);
                    }
                }
            }
        } catch (ClassNotFoundException | IOException var3) {

        }

    }



    //listener Methods
    public static void send(String msg) throws IOException {
        Message createMessage = new Message();
        createMessage.setName(username);
        createMessage.setType(MessageType.USER);
        createMessage.setStatus(Status.AWAY);
        createMessage.setMessage(msg);
        oos.writeObject(createMessage);
        oos.flush();
    }


    public static void sendStatusUpdate(Status status) throws IOException {
        Message createMessage = new Message();
        createMessage.setName(username);
        createMessage.setType(MessageType.STATUS);
        createMessage.setStatus(status);
        oos.writeObject(createMessage);
        oos.flush();
    }

    public static void connect() throws IOException {
        Message createMessage = new Message();
        createMessage.setName(username);
        createMessage.setType(MessageType.CONNECTED);
        createMessage.setMessage("has connected");
        oos.writeObject(createMessage);
    }
}


