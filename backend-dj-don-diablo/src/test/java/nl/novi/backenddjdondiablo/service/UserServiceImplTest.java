package nl.novi.backenddjdondiablo.service;

import nl.novi.backenddjdondiablo.BackendDjDonDiabloApplication;
import nl.novi.backenddjdondiablo.domain.User;
import nl.novi.backenddjdondiablo.payload.request.UserRequest;
import nl.novi.backenddjdondiablo.repository.RoleRepository;
import nl.novi.backenddjdondiablo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@ContextConfiguration(classes={BackendDjDonDiabloApplication.class})
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @MockBean
    UserRepository userRepository;

    @Mock
    UserRequest userRequest;

    @Test
    void testGetUserByUsername() {
        userRequest = new UserRequest("Novi");
        User user = new User(
                "Novi",
                "Radjes",
                "Ramautar",
                "country",
                "facebook",
                "instagram",
                "email",
                "password"
        );

        Mockito
                .when(userRepository.findByUsername(userRequest.getUsername()))
                .thenReturn(java.util.Optional.of(user));

        String expected = "Radjes";

        Optional<User> found = userRepository.findByUsername(userRequest.getUsername());

        assertEquals(expected, found.get().getFirstName());
    }

    @Test
    void partialUpdateUser() {
        Long receiving_id = 5L;

        User user = new User(
                "Novi",
                "Radjes",
                "Ramautar",
                "country",
                "facebook",
                "instagram",
                "email",
                "password"
        );

        user.setId(receiving_id);

        Mockito
                .when(userRepository.findById(user.getId()))
                .thenReturn(java.util.Optional.of(user));

        Optional<User> found = userRepository.findById(user.getId());

        String expected = "Radjes";

        assertEquals(expected, found.get().getFirstName());

    }

    @Test
    void getUsers() {
        User userA = new User(
                "Ndfddovi",
                "Radjes",
                "Ramautar",
                "country",
                "facebook",
                "instagram",
                "dfsddff",
                "password"
        );
        userA.setId(1L);

        User userB = new User(
                "Novi",
                "Radjes",
                "Ramautar",
                "country",
                "facebook",
                "instagram",
                "djkdjlfkasdf",
                "password"
        );
        userA.setId(2L);

        List<User> users_list = new ArrayList<>();
        users_list.add(userA);
        users_list.add(userB);

        Mockito
                .when(userRepository.findAll())
                .thenReturn(users_list);

        List <User> found = userRepository.findAll();

        assertEquals(2, found.size());

    }
}