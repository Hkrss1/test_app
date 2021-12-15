package Client.messages;

public enum MessageType {
    DISCONNECTED,
    CONNECTED,
    STATUS,
    USER,
    SERVER;

    private MessageType() {
    }
}
