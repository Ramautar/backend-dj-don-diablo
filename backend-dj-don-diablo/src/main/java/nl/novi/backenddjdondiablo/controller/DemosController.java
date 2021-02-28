package nl.novi.backenddjdondiablo.controller;


import nl.novi.backenddjdondiablo.payload.request.DemoRequest;
import nl.novi.backenddjdondiablo.payload.response.DemoResponse;
import nl.novi.backenddjdondiablo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/demos")
public class DemosController {

    @Autowired
    private DemoService demoService;

    @GetMapping(value = "/{username}")
    public ResponseEntity <DemoResponse> getDemosListByUser(@PathVariable("username") String username) {
      return demoService.getDemosListByUser(new DemoRequest(username));
    }

    @GetMapping(value = "/all-demos")
    public ResponseEntity <DemoResponse> getAllDemos(){
        return demoService.getAllDemos();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity <Object> updateDemoPartial(@PathVariable("id") long id, @RequestBody String comment){
        demoService.partialUpdateDemo(id, comment);
        return ResponseEntity.noContent().build();
    }
}
