package nl.novi.backenddjdondiablo.controller;


import nl.novi.backenddjdondiablo.payload.request.UserRequest;
import nl.novi.backenddjdondiablo.payload.request.UserUpdateRequest;
import nl.novi.backenddjdondiablo.payload.response.AllUsersResponse;
import nl.novi.backenddjdondiablo.payload.response.UserResponse;
import nl.novi.backenddjdondiablo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{username}")
    public ResponseEntity<UserResponse> getUserByUsername1(@PathVariable("username") String username) {
        return userService.getUserByUsername(new UserRequest(username));
    }

    @GetMapping(value = "/all-users")
    public ResponseEntity<AllUsersResponse> getAllUsers(){
        return userService.getUsers();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity <Object> updateUserPartial(@PathVariable("id") long id, @RequestBody UserUpdateRequest userUpdateRequest) {
        userService.partialUpdateUser(id, userUpdateRequest);
        return ResponseEntity.noContent().build();
    }
}
