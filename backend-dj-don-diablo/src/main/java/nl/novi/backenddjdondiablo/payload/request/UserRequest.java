package nl.novi.backenddjdondiablo.payload.request;

public class UserRequest {
    private String username;

    public UserRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
