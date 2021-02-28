package nl.novi.backenddjdondiablo.service;

import nl.novi.backenddjdondiablo.payload.request.UserRequest;
import nl.novi.backenddjdondiablo.payload.request.UserUpdateRequest;
import nl.novi.backenddjdondiablo.payload.response.AllUsersResponse;
import nl.novi.backenddjdondiablo.payload.response.UserResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<UserResponse> getUserByUsername(UserRequest userRequest);
    ResponseEntity<AllUsersResponse> getUsers();
    void partialUpdateUser(long id, UserUpdateRequest userUpdateRequest);
}
