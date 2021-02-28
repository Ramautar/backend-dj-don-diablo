package nl.novi.backenddjdondiablo.payload.request;

import nl.novi.backenddjdondiablo.domain.Demo;

import java.util.List;

public class DemoRequest {
    private String username;

    public DemoRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
