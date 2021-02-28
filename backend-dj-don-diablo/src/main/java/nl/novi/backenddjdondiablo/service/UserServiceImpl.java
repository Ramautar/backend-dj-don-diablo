package nl.novi.backenddjdondiablo.service;

import nl.novi.backenddjdondiablo.domain.ERole;
import nl.novi.backenddjdondiablo.domain.Role;
import nl.novi.backenddjdondiablo.domain.User;
import nl.novi.backenddjdondiablo.payload.request.UserRequest;
import nl.novi.backenddjdondiablo.payload.request.UserUpdateRequest;
import nl.novi.backenddjdondiablo.payload.response.AllUsersResponse;
import nl.novi.backenddjdondiablo.payload.response.UserResponse;
import nl.novi.backenddjdondiablo.repository.RoleRepository;
import nl.novi.backenddjdondiablo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    private static final String ROLE_NOT_FOUND_ERROR = "Error: Role is not found.";

    @Autowired
    UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<UserResponse> getUserByUsername(UserRequest userRequest) {
        User user = userRepository.findByUsername(userRequest.getUsername()).orElse(null);

        if(user==null){
            throw new RuntimeException("Can not find user");
        }

        return ResponseEntity.ok(new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getCountry(),
                user.getFacebook(),
                user.getInstagram(),
                user.getUsername(),
                user.getEmail(),
                user.getRoles()
        ));
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void partialUpdateUser(long id, UserUpdateRequest userUpdateRequest) {
        if (!userRepository.existsById(id)) throw new RuntimeException("Can not find user");
        User user = userRepository.findById(id).get();
        if(!userUpdateRequest.getFirstName().isEmpty()){
            user.setFirstName(userUpdateRequest.getFirstName());
        }
        if(!userUpdateRequest.getLastName().isEmpty()){
            user.setLastName(userUpdateRequest.getLastName());
        }
        if(!userUpdateRequest.getCountry().isEmpty()){
            user.setCountry(userUpdateRequest.getCountry());
        }
        if(!userUpdateRequest.getFacebook().isEmpty()){
            user.setFacebook(userUpdateRequest.getFacebook());
        }
        if(!userUpdateRequest.getInstagram().isEmpty()){
            user.setEmail(userUpdateRequest.getEmail());
        }
        if(!userUpdateRequest.getUsername().isEmpty()){
            user.setUsername(userUpdateRequest.getUsername());
        }

        Set<String> strRoles = userUpdateRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_ERROR));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_ERROR));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_ERROR));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_ERROR));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<AllUsersResponse> getUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(new AllUsersResponse(users));
    }


}
