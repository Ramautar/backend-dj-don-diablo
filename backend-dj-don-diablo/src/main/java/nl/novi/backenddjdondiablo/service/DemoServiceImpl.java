package nl.novi.backenddjdondiablo.service;

import nl.novi.backenddjdondiablo.domain.Demo;
import nl.novi.backenddjdondiablo.domain.User;
import nl.novi.backenddjdondiablo.payload.request.DemoRequest;
import nl.novi.backenddjdondiablo.payload.response.DemoResponse;
import nl.novi.backenddjdondiablo.repository.DemoRepository;
import nl.novi.backenddjdondiablo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class DemoServiceImpl implements DemoService{

    @Autowired
    DemoRepository demoRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public long createDemo(Demo demo) {
        demoRepository.save(demo);
        User user = userRepository.findByUsername(demo.getUsername()).orElse(null);
        user.addDemo(demo);
        userRepository.save(user);
      return demo.getId();
    }

      @Override
      @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
      public ResponseEntity<DemoResponse> getDemosListByUser(DemoRequest demoRequest){
        User user = userRepository.findByUsername(demoRequest.getUsername()).orElse(null);

        if(user==null){
            throw new RuntimeException("Can not find user");
        }

        Set<Demo> listOfDemos = user.getDemos();
        return ResponseEntity.ok(new DemoResponse(listOfDemos));
      }

      @Override
      @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
      public ResponseEntity<DemoResponse> getAllDemos(){
        List<Demo> listOfDemos = demoRepository.findAll();
        Set<Demo> setOfDemos = new HashSet<>(listOfDemos);
        return ResponseEntity.ok(new DemoResponse(setOfDemos));
      }

    @Override
    public void partialUpdateDemo(long id, String comment) {
        if (!demoRepository.existsById(id)) throw new RuntimeException("Issue with creating file directory");
        Demo demo = demoRepository.findById(id).get();
        demo.setStatus("reviewed");
        demo.setComment(comment);
        demoRepository.save(demo);
    }

}
