package nl.novi.backenddjdondiablo.payload.response;

import nl.novi.backenddjdondiablo.domain.User;

import java.util.List;
import java.util.Set;

public class AllUsersResponse {
    private List<User> users;

    public AllUsersResponse(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
