package nl.novi.backenddjdondiablo.service;

import nl.novi.backenddjdondiablo.BackendDjDonDiabloApplication;
import nl.novi.backenddjdondiablo.domain.Demo;
import nl.novi.backenddjdondiablo.domain.User;
import nl.novi.backenddjdondiablo.payload.request.DemoRequest;
import nl.novi.backenddjdondiablo.payload.request.UserRequest;
import nl.novi.backenddjdondiablo.repository.DemoRepository;
import nl.novi.backenddjdondiablo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@ContextConfiguration(classes={BackendDjDonDiabloApplication.class})
class DemoServiceImplTest {

    @Autowired
    private DemoService demoService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private DemoRepository demoRepository;

    @Mock
    private DemoRequest demoRequest;

    @Test
    void createDemo() {
        Demo newDemo = new Demo(
                        1L,
                        "songTitle",
                        "artistName",
                        "songName",
                        "personalMessage",
                        "Novi",
                        "demoURL"
                        );

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
                .when(userRepository.findByUsername(newDemo.getUsername()))
                .thenReturn(java.util.Optional.of(user));

        Optional<User> foundUser = userRepository.findByUsername(newDemo.getUsername());
        foundUser.get().addDemo(newDemo);

        assertEquals(1,user.getDemos().size());
    }

    @Test
    void getDemosListByUser() {
        demoRequest = new DemoRequest("Novi");
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
                .when(userRepository.findByUsername(demoRequest.getUsername()))
                .thenReturn(java.util.Optional.of(user));

        Optional<User> found = userRepository.findByUsername(demoRequest.getUsername());

        String expected = "Radjes";

        assertEquals(expected,found.get().getFirstName());
    }

    @Test
    void getAllDemos() {
        Demo demoA = new Demo(
                1L,
                "songTitle",
                "artistName",
                "songName",
                "personalMessage",
                "Novi",
                "demoURL"
        );

        Demo demoB = new Demo(
                2L,
                "songTitle",
                "artistName",
                "songName",
                "personalMessage",
                "Novi",
                "demoURL"
        );

        List<Demo> demo_list = new ArrayList<>();
        demo_list.add(demoA);
        demo_list.add(demoB);

        Mockito
                .when(demoRepository.findAll())
                .thenReturn(demo_list);

        List <Demo> found = demoRepository.findAll();

        assertEquals(2, found.size());

    }

    @Test
    void partialUpdateDemo() {
    }
}