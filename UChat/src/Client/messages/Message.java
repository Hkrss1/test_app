package Client.messages;

//package imports


//lib imports
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class Message implements Serializable {



        String name;
        String msg;
        private MessageType type;
        int count;
        ArrayList<User> users;
        ArrayList<User> list;
        Status status;

        //constructor
        public Message()
        {
              //empty
        }



        //encapsulation
        public void setName(String name){this.name = name;}
        public String getName(){return this.name;}
        public void setMessage(String msg){this.msg = msg;}
        public String getMessage(){return this.msg;}
        public void setOnlineCount(int count){this.count = count;}
        public int getOnlineCount(){return this.count;}public ArrayList<User> getUsers() {
                return this.users;
        }
        public ArrayList<User> getUserlist() {
                return this.list;
        }
        public void setUserlist(HashMap<String, User> userList) {
                this.list = new ArrayList(userList.values());
        }
        public void setUsers(ArrayList<User> users) {
                this.users = users;
        }
        public void setStatus(Status status) {
                this.status = status;
        }
        public Status getStatus() {
                return this.status;
        }
        public MessageType getType() {
                return this.type;
        }
        public void setType(MessageType type) {
                this.type = type;
        }

        //Events in Client

}
