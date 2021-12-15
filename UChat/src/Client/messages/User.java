package Client.messages;

import java.io.Serializable;

public class User implements Serializable {
    String name;
    Status status;

    public User() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
