package nl.novi.backenddjdondiablo.controller;


import nl.novi.backenddjdondiablo.payload.request.LoginRequest;
import nl.novi.backenddjdondiablo.payload.request.SignupRequest;
import nl.novi.backenddjdondiablo.payload.response.JwtResponse;
import nl.novi.backenddjdondiablo.payload.response.MessageResponse;
import nl.novi.backenddjdondiablo.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthorizationService authorizationService;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return authorizationService.authenticateUser(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody SignupRequest signUpRequest) {
        return authorizationService.registerUser(signUpRequest);
    }
}
